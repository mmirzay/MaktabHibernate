package homeworks.hw15.q2.frontend;

import java.util.List;

import homeworks.hw15.q2.utilities.Input;
import homeworks.hw15.q2.utilities.Printer;

public class UiViewer {
	void showMainMenu() {
		System.out.println("1- login as admin");
		System.out.println("2- login as employee");
		System.out.println("3- login as customer");
		System.out.println("4- exit");
	}

	public void showAdminMenu() {
		System.out.println("1- show banks list");
		System.out.println("2- show branches list");
		System.out.println("3- show employees list");
		System.out.println("4- show customers list");
		System.out.println("5- show accounts list");
		System.out.println("6- show credit cards list");
		System.out.println("7- back");
	}

	public void showEmployeeMenu() {
		System.out.println("1- show customer account");
		System.out.println("2- show account credit card");
		System.out.println("3- show account transactions");
		System.out.println("4- reactive blocked credit cards");
		System.out.println("5- back");
	}

	public void showCustomerMenu() {
		System.out.println("1- show account");
		System.out.println("2- show credit card info");
		System.out.println("3- deposit");
		System.out.println("4- withdraw");
		System.out.println("5- card to card");
		System.out.println("6- change 1st password");
		System.out.println("7- change 2nd password");
		System.out.println("8- back");
	}

	public int getIntInputValue() {
		return getIntInputValue("->");
	}

	public int getIntInputValue(String msg) {
		return Input.getIntInputValue(msg);
	}

	public long getLongInputValue(String msg) {
		return Input.getLongInputValue(msg);
	}

	public void showInvalidInputMessage() {
		Printer.printInvalidInputMessage();
	}

	public String getOptionalStringInputValue(String string) {
		return Input.getOptionalStringInputValue(string);
	}

	public String getStringInputValue(String string) {
		return Input.getStringInputValue(string);
	}

	public void showInfoMessage(String msg) {
		Printer.printInfoMessage(msg);
	}
	public void showLineMessage(String msg) {
		Printer.printLineMessage(msg);
	}

	public void showTitle(String string) {
		Printer.printTitle(string);
	}

	public <T> void showList(List<T> list) {
		if (list == null || list.isEmpty()) {
			Printer.printErrorMessage("List is empty");
			return;
		}
		for (T t : list)
			System.out.println(t);
		Printer.printLongSeperatorLine();
	}

}
