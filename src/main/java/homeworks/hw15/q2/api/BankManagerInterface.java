package homeworks.hw15.q2.api;

import java.util.List;

import homeworks.hw15.q2.api.entities.Bank;
import homeworks.hw15.q2.api.entities.Branch;
import homeworks.hw15.q2.utilities.BankRandomDataProducer;

public interface BankManagerInterface {

	void insertData(BankRandomDataProducer data);

	List<Bank> getAllBanksList();

	List<Branch> getAllBranchesList();

}
