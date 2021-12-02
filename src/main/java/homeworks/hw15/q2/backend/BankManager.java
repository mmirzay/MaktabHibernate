package homeworks.hw15.q2.backend;

import java.util.List;

import homeworks.hw15.q2.api.BankManagerInterface;
import homeworks.hw15.q2.api.entities.Account;
import homeworks.hw15.q2.api.entities.Bank;
import homeworks.hw15.q2.api.entities.Branch;
import homeworks.hw15.q2.api.entities.CreditCardPassword;
import homeworks.hw15.q2.api.entities.Customer;
import homeworks.hw15.q2.api.entities.Employee;
import homeworks.hw15.q2.utilities.BankRandomDataProducer;

public class BankManager implements BankManagerInterface {
	private BankManagerDatabase bankManagerDatabase = new BankManagerDatabase();

	@Override
	public void insertData(BankRandomDataProducer data) {
		List<Bank> banks = data.getAllBanks();
		bankManagerDatabase.addAllBanks(banks);
		List<Branch> branches = data.getAllBranches();
		bankManagerDatabase.addAllBranches(branches);
		List<Employee> employees = data.getAllEmployees();
		bankManagerDatabase.addAllEmployees(employees);
	}

	@Override
	public List<Bank> getAllBanksList() {
		return bankManagerDatabase.fetchAllBanks();
	}

	@Override
	public List<Branch> getAllBranchesList() {
		return bankManagerDatabase.fetchAllBranches();
	}

	@Override
	public List<Employee> getAllEmployeesList() {
		return bankManagerDatabase.fetchAllEmployees();
	}

	@Override
	public List<Customer> getAllCustomers() {
		return bankManagerDatabase.fetchAllCustomers();
	}

	@Override
	public Customer getCustomerById(long nationalId) {
		return bankManagerDatabase.fetchCustomerById(nationalId);
	}

	@Override
	public void addCustomer(Customer customer) {
		bankManagerDatabase.insertCustomer(customer);

	}

	@Override
	public Account getAccountByCustomerIdAndBranchId(Customer customer, Branch branch) {
		return bankManagerDatabase.fetchAccountByCustomerIdAndBranchId(customer.getNationalId(), branch.getCode());
	}

	@Override
	public void addAccount(CreditCardPassword passwords) {
		bankManagerDatabase.insertAccount(passwords);

	}

	@Override
	public CreditCardPassword getCardByCustomerIdAndBranchId(Customer customer, Branch branch) {
		return bankManagerDatabase.fetchCardByCustomerIdAndBranchId(customer.getNationalId(), branch.getCode());
	}

	@Override
	public void depositeInAccount(Account account, int amount) {
		account.increaseBalance(amount);
		updateAccount(account, null, amount);
	}

	private void updateAccount(Account source, Account destination, int amount) {
		bankManagerDatabase.updateAccount(source, destination, amount);
	}
}
