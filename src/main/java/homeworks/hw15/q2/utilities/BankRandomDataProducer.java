package homeworks.hw15.q2.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import homeworks.hw15.q2.api.entities.Bank;
import homeworks.hw15.q2.api.entities.Branch;
import homeworks.hw15.q2.api.entities.Employee;

public final class BankRandomDataProducer {
	private final Set<Bank> banks = new HashSet<>();
	private final Map<Bank, Set<Branch>> branches = new HashMap<>();
	private final Map<Branch, Set<Employee>> employees = new HashMap<>();
	private final Map<Branch, Set<Integer>> accountNumbers = new HashMap<>();
	private final Map<Integer, String> cardNumbers = new HashMap<>();
	private static int employeeId = 1001;
	private static int branchCode = 101;

	public BankRandomDataProducer() {
		initBanksList();
		initBranchesList();
		initEmployeesList();
		initAccountNumbers();
	}

	private void initBanksList() {
		banks.add(new Bank("Tejart"));
		banks.add(new Bank("Mellat"));
		banks.add(new Bank("Sepah"));
		banks.add(new Bank("Saderat"));

	}

	private void initBranchesList() {
		for (Bank bank : banks)
			branches.put(bank, initBankBranches(bank));
	}

	private Set<Branch> initBankBranches(Bank bank) {
		Set<Branch> result = new HashSet<>();
		int maxNumberOfBranches = RandomGenerator.getRandomInt(1, 10);

		while (maxNumberOfBranches > 0) {
			result.add(new Branch(bank, branchCode++));
			maxNumberOfBranches--;
		}
		return result;
	}

	private void initEmployeesList() {
		for (Entry<Bank, Set<Branch>> entries : branches.entrySet())
			for (Branch branch : entries.getValue())
				employees.put(branch, initBranchEmployees(branch));
	}

	private Set<Employee> initBranchEmployees(Branch branch) {
		Set<Employee> result = new HashSet<>();
		int maxNumberOfEmployees = RandomGenerator.getRandomInt(5, 15);
		String employeeName = "employee" + RandomGenerator.getRandomInt(1, maxNumberOfEmployees);
		Employee manager = new Employee(employeeId++, employeeName, branch);
		result.add(manager);
		for (int i = 1; i < maxNumberOfEmployees; i++) {
			employeeName = "employee" + RandomGenerator.getRandomInt(1, maxNumberOfEmployees);
			Employee emp = new Employee(employeeId++, employeeName, branch);
			emp.setManager(manager);
			result.add(emp);
		}
		return result;
	}

	private void initAccountNumbers() {
		for (Entry<Bank, Set<Branch>> entries : branches.entrySet())
			for (Branch branch : entries.getValue())
				accountNumbers.put(branch, initBranchAccountsRandomNumber(branch.getBank().getName()));
	}

	private Set<Integer> initBranchAccountsRandomNumber(String bankName) {
		Set<Integer> result = new HashSet<>();
		int maxNumberOfAccountNumber = RandomGenerator.getRandomInt(1, 100);
		while (maxNumberOfAccountNumber > 0) {
			int accountNumber = RandomGenerator.getRandomInt(111111, 99999999);
			if (cardNumbers.containsKey(accountNumber))
				continue;
			String cardNumber = initRandomCardNumber(bankName);
			if (cardNumbers.containsValue(cardNumber))
				continue;
			cardNumbers.put(accountNumber, cardNumber);
			result.add(accountNumber);
			maxNumberOfAccountNumber--;
		}
		return result;
	}

	private String initRandomCardNumber(String bankName) {
		StringBuilder result = new StringBuilder();
		int numberOfDigits = RandomGenerator.getRandomInt(0, 1) == 0 ? 16 : 12;
		String first4Digits = switch (bankName) {
		case "Tejarat" -> "5859";
		case "Mellat" -> "6104";
		case "Sepah" -> "5892";
		case "Saderat" -> "6037";
		default -> "0000";
		};
		result.append(first4Digits);
		for (int i = 1; i < numberOfDigits / 4; i++)
			result.append(RandomGenerator.getRandomInt(1111, 9999));
		return result.toString();
	}

	public List<Bank> getAllBanks() {
		return new ArrayList<>(banks);
	}

	public List<Branch> getAllBranches() {
		List<Branch> result = new ArrayList<>();
		for (Entry<Bank, Set<Branch>> entries : branches.entrySet())
			result.addAll(entries.getValue());
		return result;
	}

	public List<Employee> getAllEmployees() {
		List<Employee> result = new ArrayList<>();
		for (Entry<Branch, Set<Employee>> entries : employees.entrySet())
			result.addAll(entries.getValue());
		return result;
	}

	public List<Integer> getAllAccountNumbers(Branch branch) {
		return new ArrayList<>(accountNumbers.get(branch));
	}

	public String getCardNumber(Integer accountNumber) {
		return cardNumbers.get(accountNumber);
	}
}
