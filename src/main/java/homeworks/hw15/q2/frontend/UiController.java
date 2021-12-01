package homeworks.hw15.q2.frontend;

import java.util.List;

import homeworks.hw15.q2.api.BankManagerInterface;
import homeworks.hw15.q2.api.entities.Bank;
import homeworks.hw15.q2.api.entities.Branch;
import homeworks.hw15.q2.backend.BankManager;
import homeworks.hw15.q2.utilities.BankRandomDataProducer;
//import homeworks.hw15.q2.utilities.Input;
//import homeworks.hw15.q2.utilities.Printer;

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
		case 4 -> isRunning = false;
		default -> ui.showInvalidInputMessage();
		}
	}

	private void loginAsAdmin() {
		String password = ui.getOptionalStringInputValue("Enter password (admin):");
		if (password.equals("admin"))
			showAdminPage();
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
		case 8:
			return;
		default:
			ui.showInvalidInputMessage();
		}
		showAdminPage();
	}

	private void showBankList() {
		ui.showTitle("Banks List");
		List<Bank> banks = bankManager.getAllBanksList();
		ui.showList(banks);
	}

	private void showBranchList() {
		ui.showTitle("Branch List");
		List<Branch> branches = bankManager.getAllBranchesList();
		ui.showList(branches);

	}
}
