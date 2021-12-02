package homeworks.hw15.q2.api;

import java.util.List;

import homeworks.hw15.q2.api.entities.Account;
import homeworks.hw15.q2.api.entities.Bank;
import homeworks.hw15.q2.api.entities.Branch;
import homeworks.hw15.q2.api.entities.CreditCardPassword;
import homeworks.hw15.q2.api.entities.Customer;
import homeworks.hw15.q2.api.entities.Employee;
import homeworks.hw15.q2.utilities.BankRandomDataProducer;

public interface BankManagerInterface {

	void insertData(BankRandomDataProducer data);

	List<Bank> getAllBanksList();

	List<Branch> getAllBranchesList();

	List<Employee> getAllEmployeesList();

	List<Customer> getAllCustomers();

	Customer getCustomerById(long nationalId);

	void addCustomer(Customer customer);

	Account getAccountByCustomerIdAndBranchId(Customer customer, Branch branch);

	void addAccount(CreditCardPassword passwords);

	CreditCardPassword getCardByCustomerIdAndBranchId(Customer customer, Branch branch);

	void depositeInAccount(Account account, int amount);

}
