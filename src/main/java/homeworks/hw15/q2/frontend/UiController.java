package homeworks.hw15.q2.frontend;

import java.util.List;
import java.util.stream.Collectors;

import homeworks.hw15.q2.api.BankManagerInterface;
import homeworks.hw15.q2.api.entities.Account;
import homeworks.hw15.q2.api.entities.Bank;
import homeworks.hw15.q2.api.entities.Branch;
import homeworks.hw15.q2.api.entities.CreditCard;
import homeworks.hw15.q2.api.entities.CreditCardPassword;
import homeworks.hw15.q2.api.entities.Customer;
import homeworks.hw15.q2.api.entities.Employee;
import homeworks.hw15.q2.backend.BankManager;
import homeworks.hw15.q2.utilities.BankRandomDataProducer;
import homeworks.hw15.q2.utilities.RandomGenerator;
import homeworks.hw15.q2.utilities.Validator;

public class UiController {
	private final BankManagerInterface bankManager = new BankManager();
	private BankRandomDataProducer data;
	private UiViewer ui = new UiViewer();
	private boolean isRunning;

	public UiController() {
		System.out.println("Initializing random data...");
		initData();
		System.out.println("Data initialized.");
	}

	private void initData() {
		data = new BankRandomDataProducer();
		bankManager.insertData(data);
	}

	public void run() {
		System.out.println("Running Bank Manager.");
		isRunning = true;
		while (isRunning)
			showMenu();
		System.out.println("Bank manager is closed.");
	}

	private void showMenu() {
		ui.showMainMenu();
		switch (ui.getIntInputValue()) {
		case 1 -> loginAsAdmin();
		case 2 -> loginAsEmployee();
		case 3 -> loginAsCustomer();
		case 4 -> isRunning = false;
		default -> ui.showInvalidInputMessage();
		}
	}

	private void loginAsCustomer() {
		long nationalId = ui.getLongInputValue("Enter national id:");
		if (Validator.validateId(nationalId)) {
			Customer customer = getCustomerById(nationalId);
			if (customer == null) {
				String name = ui.getStringInputValue("Enter your name:");
				customer = new Customer(nationalId, name);
				addCustomer(customer);
			}
			selectBank(customer);
		} else
			ui.showInvalidInputMessage();
	}

	private void addCustomer(Customer customer) {
		bankManager.addCustomer(customer);
	}

	private Customer getCustomerById(long nationalId) {
		return bankManager.getCustomerById(nationalId);
	}

	private void selectBank(Customer customer) {
		List<Bank> banks = showBankList();
		String bankName = ui.getStringInputValue("Enter bank name:");
		Bank bank = banks.stream().filter(b -> b.getName().equalsIgnoreCase(bankName)).findAny().orElse(null);
		if (bank != null) {
			List<Branch> branches = bankManager.getAllBranchesList().stream().filter(b -> b.getBank().equals(bank))
					.collect(Collectors.toList());
			Branch randomBranch = branches.get(RandomGenerator.getRandomInt(0, branches.size() - 1));
			showCustomerPage(randomBranch, customer);
		} else {
			ui.showInvalidInputMessage();
			selectBank(customer);
		}

	}

	private void showCustomerPage(Branch branch, Customer customer) {
		ui.showCustomerMenu();
		switch (ui.getIntInputValue()) {
		case 1:
			showCustomerAccount(branch, customer);
			break;
		case 2:
			showCustomerCard(branch, customer);
			break;
		case 3:
			showAccountDeposit(branch, customer);
		case 8:
			return;
		default:
			ui.showInvalidInputMessage();
		}
		showCustomerPage(branch, customer);
	}

	private void showAccountDeposit(Branch branch, Customer customer) {
		Account account = showCustomerAccount(branch, customer);
		if (account == null)
			return;
		int amount = ui.getIntInputValue("Enter amount:");
		if (Validator.validateDepositAmount(amount) == false) {
			ui.showInvalidInputMessage();
			return;
		}
		System.out.println("account before depo: " + account.getBalance());
		bankManager.depositeInAccount(account, amount);
		ui.showInfoMessage("Deposit done. Account balance: " + account.getBalance());
	}

	private void showCustomerCard(Branch branch, Customer customer) {
		CreditCardPassword card = bankManager.getCardByCustomerIdAndBranchId(customer, branch);
		if (card != null)
			ui.showInfoMessage(card.getInfo());
		else
			ui.showInfoMessage("No card info. Creat an account.");
	}

	private Account showCustomerAccount(Branch branch, Customer customer) {
		Account account = bankManager.getAccountByCustomerIdAndBranchId(customer, branch);
		if (account == null) {
			ui.showInfoMessage("You don't have any account in this bank.");
			String answer = ui.getOptionalStringInputValue("Do you want to create an account (Y/N) ?");
			if (answer.startsWith("y") == false)
				return null;
			int accountNumber = data.getValidAccountNumberForBranch(branch);
			account = new Account(accountNumber, customer, branch);
			String cardNumber = data.getCardNumber(accountNumber);
			CreditCard card = new CreditCard(cardNumber, account);
			short firstPassword = (short) RandomGenerator.getRandomInt(1111, 9999);
			short expirationYear = (short) RandomGenerator.getRandomInt(1400, 1410);
			short expirationMonth = (short) RandomGenerator.getRandomInt(1, 12);
			short cvv = (short) RandomGenerator.getRandomInt(111, 9999);
			CreditCardPassword passwords = new CreditCardPassword(card, firstPassword, expirationYear, expirationMonth,
					cvv);
			bankManager.addAccount(passwords);
			ui.showLineMessage("Account Number: " + account.getNumber());
			ui.showLineMessage("Card Number: " + card.getNumber());
			ui.showInfoMessage("Account added. Please remember your card password: " + firstPassword);
		} else
			ui.showInfoMessage(account.toString());
		return account;
	}

	private void loginAsEmployee() {
		String password = ui.getOptionalStringInputValue("Enter password (admin):");
		if (password.equals("admin"))
			showEmployeePage();
		else
			ui.showInvalidInputMessage();
	}

	private void showEmployeePage() {
		ui.showEmployeeMenu();
	}

	private void loginAsAdmin() {
		String password = ui.getOptionalStringInputValue("Enter password (admin):");
		if (password.equals("admin"))
			showAdminPage();
		else
			ui.showInvalidInputMessage();
	}

	private void showAdminPage() {
		ui.showAdminMenu();
		switch (ui.getIntInputValue()) {
		case 1:
			showBankList();
			break;
		case 2:
			showBranchList();
			break;
		case 3:
			showEmployeeList();
			break;
		case 4:
			showCustomerList();
			break;
		case 7:
			return;
		default:
			ui.showInvalidInputMessage();
		}
		showAdminPage();
	}

	private void showCustomerList() {
		ui.showTitle("Cutomer List");
		List<Customer> customers = bankManager.getAllCustomers();
		ui.showList(customers);
	}

	private List<Bank> showBankList() {
		ui.showTitle("Banks List");
		List<Bank> banks = bankManager.getAllBanksList();
		ui.showList(banks);
		return banks;
	}

	private void showBranchList() {
		ui.showTitle("Branch List");
		List<Branch> branches = bankManager.getAllBranchesList();
		ui.showList(branches);

	}

	private void showEmployeeList() {
		ui.showTitle("Employee List");
		List<Employee> employees = bankManager.getAllEmployeesList();
		ui.showList(employees);
	}
}
