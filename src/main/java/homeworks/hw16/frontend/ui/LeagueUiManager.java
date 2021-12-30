package homeworks.hw16.frontend.ui;

import java.util.List;

import homeworks.hw16.backend.entities.TeamLeagueInfo;
import homeworks.hw16.util.Printer;

public class LeagueUiManager {
	public void showMenu() {
		System.out.println("*** FOOTBALL SIMPLE LEAGUE ***");
		System.out.println("1- show league table");
		System.out.println("2- show highest paid coach");
		System.out.println("3- show top 10 highest paid players");
		System.out.println("4- show top 10 skilled players");
		System.out.println("5- show top players goals");
		System.out.println("6- show top players assists");
		System.out.println("7- show cities teams number");
		System.out.println("8- show all derbies sorted by goals");
		System.out.println("9- exit");
	}

	public void showLeageTable(List<TeamLeagueInfo> table) {
		StringBuilder result = new StringBuilder();
		result.append(Printer.formatter("Team", 13));
		result.append(" | ");
		result.append(Printer.formatter("Win"));
		result.append(" | ");
		result.append(Printer.formatter("Draw"));
		result.append(" | ");
		result.append(Printer.formatter("Loss"));
		result.append(" | ");
		result.append(Printer.formatter("SG"));
		result.append(" | ");
		result.append(Printer.formatter("RG"));
		result.append(" | ");
		result.append(Printer.formatter("GD"));
		result.append(" | ");
		result.append(Printer.formatter("Score"));
		result.append(System.lineSeparator());
		result.append("-".repeat(75));
		System.out.println(String.format("%-4s %s", "   ", result.toString()));
		int position = 1;
		for (TeamLeagueInfo teamLeagueInfo : table)
			System.out.println(String.format("%-4s %s", "#" + position++ + "-", teamLeagueInfo.toString()));
		System.out.println("-".repeat(75));
		Printer.printWaitingMessage();
	}

	public void showMostPaidCoach(String highestPaidCoach) {
		Printer.printInfoMessage(highestPaidCoach);
	}

	public void showStringList(List<String> list) {
		int maxLen = 0;
		for (int i = 0; i < list.size(); i++)
			for (String s : list.get(i).split(","))
				if (s.length() > maxLen)
					maxLen = s.length();

		int row = 0;
		for (String l : list) {
			if (row == 0) {
				System.out.print(Printer.formatter("", 4));
				row++;
			} else
				System.out.print(Printer.formatter("#" + row++,4));

			for (String s : l.split(","))
				System.out.print(Printer.formatter(s, maxLen + 2));
			System.out.println();
		}
		Printer.printWaitingMessage();
	}

}
