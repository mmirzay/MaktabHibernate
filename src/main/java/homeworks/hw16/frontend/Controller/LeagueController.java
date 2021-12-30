package homeworks.hw16.frontend.Controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import homeworks.hw16.backend.entities.City;
import homeworks.hw16.backend.entities.Coach;
import homeworks.hw16.backend.entities.Contract;
import homeworks.hw16.backend.entities.Match;
import homeworks.hw16.backend.entities.Player;
import homeworks.hw16.backend.entities.PlayerMatchInfo;
import homeworks.hw16.backend.entities.PlayerPosition;
import homeworks.hw16.backend.entities.Stadium;
import homeworks.hw16.backend.entities.Team;
import homeworks.hw16.backend.entities.TeamMatchInfo;
import homeworks.hw16.backend.exceptions.ServiceExeption;
import homeworks.hw16.backend.services.LeagueManager;
import homeworks.hw16.frontend.ui.LeagueUiManager;
import homeworks.hw16.util.Input;
import homeworks.hw16.util.Printer;
import homeworks.hw16.util.RandomGenerator;

public class LeagueController {
	private LeagueManager leagueManager;
	private LeagueUiManager ui;
	private boolean isRunning;

	public LeagueController() {
		leagueManager = new LeagueManager();
		ui = new LeagueUiManager();
	}

	public void run() {
		isRunning = true;
		try {
			leagueManager.initializeDatabase();
			initRandomLeague();
			while (isRunning)
				showMenu();
		} catch (ServiceExeption e) {
			e.printStackTrace();
			return;
		}
		System.out.println("finished...");
	}

	private void initRandomLeague() throws ServiceExeption {

		System.out.println("Football League is loading. Please wait...");
		String statusBar = "=";
		System.out.print("Progress Status:" + statusBar);
		City tehran = new City("tehran");
		leagueManager.addCity(tehran);
		City esfahan = new City("esfahan");
		leagueManager.addCity(esfahan);
		City shiraz = new City("shiraz");
		leagueManager.addCity(shiraz);
		City mashad = new City("mashad");
		leagueManager.addCity(mashad);
		City tabriz = new City("tabriz");
		leagueManager.addCity(tabriz);
		City khuzestan = new City("khuzestan");
		leagueManager.addCity(khuzestan);
		City qaemshahr = new City("qaemshahr");
		leagueManager.addCity(qaemshahr);
		City arak = new City("arak");
		leagueManager.addCity(arak);
		City sirjan = new City("sirjan");
		leagueManager.addCity(sirjan);
		City masjedSoleiman = new City("masjed soleiman");
		leagueManager.addCity(masjedSoleiman);
		City abadan = new City("abadan");
		leagueManager.addCity(abadan);
		City rafsanjan = new City("rafsanjan");
		leagueManager.addCity(rafsanjan);

		System.out.print(statusBar);
		List<Stadium> stadiums = new ArrayList<>(15);
		Stadium azadi = new Stadium(tehran, "azadi", 100);
		stadiums.add(azadi);
		Stadium emamkhomeini = new Stadium(arak, "emamkhomeini", 15);
		stadiums.add(emamkhomeini);
		Stadium emamreza = new Stadium(mashad, "emamreza", 25);
		stadiums.add(emamreza);
		Stadium shahreQods = new Stadium(tehran, "shahreqods", 25);
		stadiums.add(shahreQods);
		Stadium yadegaremam = new Stadium(tabriz, "yadegaremam", 66);
		stadiums.add(yadegaremam);
		Stadium fooladshahr = new Stadium(esfahan, "fooladshahr", 15);
		stadiums.add(fooladshahr);
		Stadium naghshejahan = new Stadium(esfahan, "naghshe jahan", 75);
		stadiums.add(naghshejahan);
		Stadium takhti = new Stadium(abadan, "takhti", 20);
		stadiums.add(takhti);
		Stadium pars = new Stadium(shiraz, "pars", 50);
		stadiums.add(pars);
		Stadium fooladArena = new Stadium(khuzestan, "foolad arena", 40);
		stadiums.add(fooladArena);
		Stadium soleimani = new Stadium(sirjan, "soleimani", 8);
		stadiums.add(soleimani);
		Stadium sanatMes = new Stadium(rafsanjan, "sanate mes", 10);
		stadiums.add(sanatMes);
		Stadium shahna = new Stadium(qaemshahr, "shahna", 15);
		stadiums.add(shahna);
		Stadium behnam = new Stadium(masjedSoleiman, "behnam mohamadi", 8);
		stadiums.add(behnam);
		Stadium ghadir = new Stadium(tehran, "ghadir", 10);
		stadiums.add(ghadir);
		for (Stadium s : stadiums)
			leagueManager.addStadium(s);

		System.out.print(statusBar);
		Coach[] coaches = new Coach[16];
		List<Integer> coachIds = RandomGenerator.getRandomIntArray(coaches.length, 1, 100);
		for (int i = 1; i <= coaches.length; i++) {
			coaches[i - 1] = new Coach(coachIds.get(i - 1), "coach" + i);
			int payment = RandomGenerator.getRandomInt(100, 10000);
			int year = 1400;
			int length = 1;
			coaches[i - 1].setContract(new Contract(payment, year, length));
			leagueManager.addCoach(coaches[i - 1]);
		}

		List<Team> teamList = new ArrayList<>(16);
		Team aluminium = new Team("aluminium", arak);
		teamList.add(aluminium);
		Team esteghlal = new Team("esteghlal", tehran);
		teamList.add(esteghlal);
		Team padide = new Team("padide", mashad);
		teamList.add(padide);
		Team perspolis = new Team("perspolis", tehran);
		teamList.add(perspolis);
		Team peykan = new Team("peykan", tehran);
		teamList.add(peykan);
		Team teraktor = new Team("teraktor", tabriz);
		teamList.add(teraktor);
		Team zobahan = new Team("zobahan", esfahan);
		teamList.add(zobahan);
		Team sepahan = new Team("sepahan", esfahan);
		teamList.add(sepahan);
		Team sanatNaft = new Team("sanate naft", abadan);
		teamList.add(sanatNaft);
		Team fajrSepasi = new Team("fajre sepasi", shiraz);
		teamList.add(fajrSepasi);
		Team foolad = new Team("foolad", khuzestan);
		teamList.add(foolad);
		Team golgohar = new Team("gol gohar", sirjan);
		teamList.add(golgohar);
		Team mes = new Team("mes", rafsanjan);
		teamList.add(mes);
		Team nasaji = new Team("nasaji", qaemshahr);
		teamList.add(nasaji);
		Team naftMasjed = new Team("naft masjed", masjedSoleiman);
		teamList.add(naftMasjed);
		Team havadar = new Team("havadar", tehran);
		teamList.add(havadar);

		int counter = 0;
		for (int i = 0; i < teamList.size(); i++) {
			if(++counter % (teamList.size() / 4) == 0)
				System.out.print(statusBar);
			teamList.get(i).setCoach(coaches[i]);
			leagueManager.addTeam(teamList.get(i));
		}

		System.out.print(statusBar);
		Player[] players = new Player[16 * 11];
		List<Integer> playersIds = RandomGenerator.getRandomIntArray(players.length, 100, 1000);
		HashMap<String, List<Player>> teamPlayers = new HashMap<>(16);
		List<Player> team11Players = new ArrayList<>(11);

		int teamSelector = 0;
		counter = 0;
		for (int i = 1; i <= players.length; i++) {
			if(++counter % (players.length / 4) == 0)
				System.out.print(statusBar);
			int id = playersIds.get(i - 1);
			int skillLevel = RandomGenerator.getRandomInt(5, 10);
			Team team = teamList.get(teamSelector);
			String name = "player" + i;
			int seperator = 11 + 11 * teamSelector;
			PlayerPosition position = PlayerPosition.get(i % 11 == 0 ? 11 : i % 11);
			players[i - 1] = new Player(id, name, position, skillLevel, team);
			int payment = RandomGenerator.getRandomInt(100, 10000);
			int year = 1400;
			int length = 1;
			players[i - 1].setContract(new Contract(payment, year, length));
			leagueManager.addPlayer(players[i - 1]);
			team11Players.add(players[i - 1]);
			if (i % seperator == 0) {
				teamPlayers.put(team.getName(), team11Players);
				team11Players = new ArrayList<>(11);
				teamSelector++;
			}
		}

		List<Match> matchesList = new ArrayList<>(30);
		int matchId = 1;
		for (int i = 0; i < teamList.size(); i++) {
			Team home = teamList.get(i);
			for (int j = 0; j < teamList.size(); j++) {
				if (i == j)
					continue;
				int id = matchId++;
				Team away = teamList.get(j);
				Stadium stadium = stadiums.get(RandomGenerator.getRandomInt(0, 14));
				int day = RandomGenerator.getRandomInt(1, 28);
				int month = RandomGenerator.getRandomInt(1, 12);
				int year = 1400;
				Date date = Date.valueOf(LocalDate.of(year, month, day));
				matchesList.add(new Match(id, home, away, stadium, date));
			}
		}

		counter=0;
		for (Match match : matchesList) {
			if(++counter % (teamList.size() / 2) == 0)
				System.out.print(statusBar);
			leagueManager.addMatch(match);
		}

		counter=0;
		for (Match match : matchesList) {
			if(++counter % (matchesList.size() / 16) == 0)
				System.out.print(statusBar);
			Team home = match.getHomeTeam();
			List<Player> homeTeamPlayers = teamPlayers.get(home.getName());
			int homePlayersSkill = 0;
			for (Player player : homeTeamPlayers)
				homePlayersSkill += player.getSkillLevel();

			Team away = match.getAwayTeam();
			List<Player> awayTeamPlayers = teamPlayers.get(away.getName());
			int awayPlayersSkill = 0;
			for (Player player : awayTeamPlayers)
				awayPlayersSkill += player.getSkillLevel();

			float probabilityHome = 100.0f * homePlayersSkill / (homePlayersSkill + awayPlayersSkill);
			float probabilityAway = 100.0f * awayPlayersSkill / (homePlayersSkill + awayPlayersSkill);
			int homeTeamScoredGoals = RandomGenerator.getRandomInt(0, Math.round(probabilityHome)) / 10;
			int awayTeamScoredGoals = RandomGenerator.getRandomInt(0, Math.round(probabilityAway)) / 10;
			int homeTeamRecievedGoals = awayTeamScoredGoals;
			int awayTeamRecievedGoals = homeTeamScoredGoals;

			int homeTeamScore = homeTeamRecievedGoals == homeTeamScoredGoals ? 1
					: homeTeamScoredGoals > homeTeamRecievedGoals ? 3 : 0;
			int awayTeamScore = awayTeamRecievedGoals == awayTeamScoredGoals ? 1
					: awayTeamScoredGoals > awayTeamRecievedGoals ? 3 : 0;

			TeamMatchInfo homeTeamMatchInfo = new TeamMatchInfo(home, match, homeTeamScore, homeTeamScoredGoals,
					homeTeamRecievedGoals);
			TeamMatchInfo awayTeamMatchInfo = new TeamMatchInfo(away, match, awayTeamScore, awayTeamScoredGoals,
					awayTeamRecievedGoals);

			leagueManager.addTeamMatchInfo(homeTeamMatchInfo);
			leagueManager.addTeamMatchInfo(awayTeamMatchInfo);

			HashMap<String, Integer> playerScores = new HashMap<>(homeTeamScoredGoals);
			HashMap<String, Integer> playerAssists = new HashMap<>(homeTeamScoredGoals);
			while (homeTeamScoredGoals > 0) {
				Player goal = homeTeamPlayers.get(RandomGenerator.getRandomInt(1, 10));
				Player assist = homeTeamPlayers.get(RandomGenerator.getRandomInt(1, 10));
				if (playerScores.containsKey(goal.getName()) == false)
					playerScores.put(goal.getName(), 1);
				else
					playerScores.put(goal.getName(), playerScores.get(goal.getName()) + 1);

				if (playerAssists.containsKey(assist.getName()) == false)
					playerAssists.put(assist.getName(), 1);
				else
					playerAssists.put(assist.getName(), playerAssists.get(assist.getName()) + 1);

				homeTeamScoredGoals--;
			}

			for (Player player : homeTeamPlayers) {
				int numberOfGoals = 0;
				int numberOfAssists = 0;
				if (playerScores.containsKey(player.getName()))
					numberOfGoals = playerScores.get(player.getName());
				if (playerAssists.containsKey(player.getName()))
					numberOfAssists = playerAssists.get(player.getName());
				if (numberOfGoals != 0 || numberOfAssists != 0) {
					PlayerMatchInfo playerMatchInfo = new PlayerMatchInfo(player, match, numberOfGoals,
							numberOfAssists);
					leagueManager.addPlayerMatchInfo(playerMatchInfo);
				}
			}

			playerScores = new HashMap<>(awayTeamScoredGoals);
			playerAssists = new HashMap<>(awayTeamScoredGoals);
			while (awayTeamScoredGoals > 0) {
				Player goal = awayTeamPlayers.get(RandomGenerator.getRandomInt(1, 10));
				Player assist = awayTeamPlayers.get(RandomGenerator.getRandomInt(1, 10));
				if (playerScores.containsKey(goal.getName()) == false)
					playerScores.put(goal.getName(), 1);
				else
					playerScores.put(goal.getName(), playerScores.get(goal.getName()) + 1);

				if (playerAssists.containsKey(assist.getName()) == false)
					playerAssists.put(assist.getName(), 1);
				else
					playerAssists.put(assist.getName(), playerAssists.get(assist.getName()) + 1);

				awayTeamScoredGoals--;
			}

			for (Player player : awayTeamPlayers) {
				int numberOfGoals = 0;
				int numberOfAssists = 0;
				if (playerScores.containsKey(player.getName()))
					numberOfGoals = playerScores.get(player.getName());
				if (playerAssists.containsKey(player.getName()))
					numberOfAssists = playerAssists.get(player.getName());
				if (numberOfGoals != 0 || numberOfAssists != 0) {
					PlayerMatchInfo playerMatchInfo = new PlayerMatchInfo(player, match, numberOfGoals,
							numberOfAssists);
					leagueManager.addPlayerMatchInfo(playerMatchInfo);
				}
			}
		} // matches

		System.out.println("100%\n");
	}

	private void showMenu() throws ServiceExeption {
		ui.showMenu();
		switch (Input.getIntInputValue(">>")) {
		case 1 -> ui.showLeageTable(leagueManager.getLeagueTable().getTeamsTable());
		case 2 -> ui.showMostPaidCoach(leagueManager.getHighestPaidCoach());
		case 3 -> ui.showStringList(leagueManager.getMostPaidPlayersList());
		case 4 -> ui.showStringList(leagueManager.getTopSkilledPlayersList());
		case 5 -> ui.showStringList(leagueManager.getTopPlayersGoalList());
		case 6 -> ui.showStringList(leagueManager.getTopPlayersAssistList());
		case 7 -> ui.showStringList(leagueManager.getCityTeamsNumberList());
		case 8 -> ui.showStringList(leagueManager.getAllDerbiesList());
		case 9 -> isRunning = false;
		default -> Printer.printErrorMessage("invalid selection");
		}
	}
}
