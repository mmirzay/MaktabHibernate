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
		System.out.println("5- show customers list");
		System.out.println("6- show accounts list");
		System.out.println("7- show credit cards list");
		System.out.println("8- back");
	}

	public int getIntInputValue() {
		return Input.getIntInputValue("->");
	}

	public void showInvalidInputMessage() {
		Printer.printInvalidInputMessage();
	}

	public String getOptionalStringInputValue(String string) {
		return Input.getOptionalStringInputValue(string);
	}

	public void showTitle(String string) {
		Printer.printTitle(string);
	}

	public <T> void showList(List<T> list) {
		for (T t : list)
			System.out.println(t);
		Printer.printLongSeperatorLine();
	}
}
