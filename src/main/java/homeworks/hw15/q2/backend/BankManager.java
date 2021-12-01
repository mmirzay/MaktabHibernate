package homeworks.hw15.q2.backend;

import java.util.List;

import homeworks.hw15.q2.api.BankManagerInterface;
import homeworks.hw15.q2.api.entities.Bank;
import homeworks.hw15.q2.api.entities.Branch;
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
}
