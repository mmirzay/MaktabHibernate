package homeworks.hw15.q2.utilities;

public class Printer {
	public static String formatter(Object str) {
		return String.format("%-5s", str + "");
	}

	public static String formatter(Object str, int spaces) {
		return String.format("%-" + spaces + "s", str + "");
	}

	public static void printErrorMessage(String msg) {
		System.out.println();
		System.out.println("| Error: " + msg + " |");
		printWaitingMessage();
	}

	public static void printInvalidInputMessage() {
		printErrorMessage("Invalid Input");
	}

	public static void printInfoMessage(String msg) {
		System.out.println();
		System.out.println(">>> " + msg + " <<<");
		printWaitingMessage();
	}

	public static void printLineMessage(String msg) {
		System.out.println();
		System.out.println(">>> " + msg);
	}

	public static void printWaitingMessage() {
		System.out.println("_____________ press Enter to continue...");
		Input.getOptionalStringInputValue("");
	}

	public static void printTitle(String string) {
		int length = string.length() * 2;
		printDashedLine(length);
		printLineWithIndent(string, length / 4);
		printDashedLine(length);
	}

	private static void printLineWithIndent(String string, int indent) {
		System.out.print(" ".repeat(indent));
		System.out.println(string);

	}

	private static void printDashedLine(int length) {
		System.out.println("-".repeat(length));
	}

	public static void printLongSeperatorLine() {
		System.out.println("_".repeat(100));
	}
}
