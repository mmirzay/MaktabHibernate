package homeworks.hw16.backend.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import homeworks.hw16.backend.entities.City;
import homeworks.hw16.backend.entities.Coach;
import homeworks.hw16.backend.entities.Match;
import homeworks.hw16.backend.entities.PersonRole;
import homeworks.hw16.backend.entities.Player;
import homeworks.hw16.backend.entities.PlayerMatchInfo;
import homeworks.hw16.backend.entities.PlayerPosition;
import homeworks.hw16.backend.entities.Stadium;
import homeworks.hw16.backend.entities.Team;
import homeworks.hw16.backend.entities.TeamLeagueInfo;
import homeworks.hw16.backend.entities.TeamMatchInfo;
import homeworks.hw16.backend.exceptions.DbException;

public class DbManager {
	private static class Statements {
		private static class Schema {
			private static String CREATE = "CREATE DATABASE IF NOT EXISTS maktab_league_schema";
		}

		private static class City {
			private static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS maktab_league_schema.city (name varchar(25) NOT NULL,  PRIMARY KEY (name)) ENGINE=InnoDB";
			private static String INSERT_CITY = "INSERT INTO maktab_league_schema.city (name) VALUES(?);";
			private static String FETCH_CITY_TEAM_NUMBERS = "SELECT" + "	city.name," + "	count(*) AS number"
					+ " FROM" + "	maktab_league_schema.city," + "	maktab_league_schema.team" + " WHERE"
					+ "	team.city_name = city.name" + " GROUP BY city.name" + " ORDER BY number DESC;";
			private static String DELETE_ALL = "DELETE FROM maktab_league_schema.city;";
		}

		private static class Stadium {
			private static String CREATE_TABLE = " CREATE TABLE IF NOT EXISTS maktab_league_schema.stadium ("
					+ " name varchar(25) NOT NULL," + " capacity int NOT NULL," + " city_name varchar(25) NOT NULL,"
					+ " PRIMARY KEY (name)," + " KEY stadium_city_FK (city_name),"
					+ " CONSTRAINT stadium_city_FK FOREIGN KEY (city_name) REFERENCES city (name) ON DELETE CASCADE ON UPDATE CASCADE"
					+ " )" + " ENGINE=InnoDB;";

			private static String INSERT_STADIUM = "INSERT INTO maktab_league_schema.stadium (name, capacity, city_name) VALUES(?, ?, ?);";
			private static String DELETE_ALL = "DELETE FROM maktab_league_schema.stadium;";
		}

		private static class Coach {
			private static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS maktab_league_schema.coach ("
					+ " id int NOT NULL," + " name varchar(25) NOT NULL," + " PRIMARY KEY (id)" + ")"
					+ " ENGINE=InnoDB;";
			private static String INSERT_COACH = "INSERT INTO maktab_league_schema.coach (id, name) VALUES(?, ?);";
			private static String DELETE_ALL = "DELETE FROM maktab_league_schema.coach;";
		}

		private static class Team {
			private static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS maktab_league_schema.team ("
					+ " name varchar(25) NOT NULL," + " coach_id int NOT NULL," + " captain_id int NOT NULL,"
					+ " city_name varchar(25) NOT NULL," + " PRIMARY KEY (name)," + " KEY team_city_FK (city_name),"
					+ " KEY team_coach_FK (coach_id),"
					+ " CONSTRAINT team_city_FK FOREIGN KEY (city_name) REFERENCES city (name) ON DELETE CASCADE ON UPDATE CASCADE,"
					+ " CONSTRAINT team_coach_FK FOREIGN KEY (coach_id) REFERENCES coach (id) ON DELETE CASCADE ON UPDATE CASCADE"
					+ " ) ENGINE=InnoDB;";
			private static String INSERT_TEAM = "INSERT INTO maktab_league_schema.team (name, coach_id, captain_id, city_name) VALUES(?, ?, ?, ?);";
			private static String DELETE_ALL = "DELETE FROM maktab_league_schema.team;";
		}

		private static class Player {
			private static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS maktab_league_schema.player ("
					+ " id int NOT NULL," + " name varchar(25) NOT NULL," + " position varchar(3) NOT NULL,"
					+ " skill_level int NOT NULL," + " team_name varchar(100) NOT NULL," + " PRIMARY KEY (id),"
					+ " KEY player_FK (team_name),"
					+ " CONSTRAINT player_FK FOREIGN KEY (team_name) REFERENCES team (name) ON DELETE CASCADE ON UPDATE CASCADE"
					+ " ) ENGINE=InnoDB;";
			private static String INSERT_PLAYER = "INSERT INTO maktab_league_schema.player (id, name, position, skill_level, team_name) VALUES(?, ?, ?, ?, ?);";
			private static String FETCH_TOP_SKILLED_PLAYERS = "SELECT *" + " FROM maktab_league_schema.player"
					+ " GROUP BY name" + " ORDER BY skill_level DESC" + " LIMIT 10;";
			private static String DELETE_ALL = "DELETE FROM maktab_league_schema.player;";
		}

		private static class Match {
			private static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS maktab_league_schema.match ("
					+ " id int NOT NULL," + " home_team_name varchar(25) NOT NULL,"
					+ " away_team_name varchar(25) NOT NULL," + " date date NOT NULL,"
					+ " stadium_name varchar(25) NOT NULL," + " PRIMARY KEY (id),"
					+ " KEY match_stadium_FK (stadium_name)," + " KEY match_hometeam_FK (home_team_name),"
					+ " KEY match_awayteam_FK (away_team_name),"
					+ " CONSTRAINT match_awayteam_FK FOREIGN KEY (away_team_name) REFERENCES team (name) ON DELETE CASCADE ON UPDATE CASCADE,"
					+ " CONSTRAINT match_hometeam_FK FOREIGN KEY (home_team_name) REFERENCES team (name) ON DELETE CASCADE ON UPDATE CASCADE,"
					+ " CONSTRAINT match_stadium_FK FOREIGN KEY (stadium_name) REFERENCES stadium (name) ON DELETE CASCADE ON UPDATE CASCADE"
					+ " ) ENGINE=InnoDB;";
			private static String INSERT_MATCH = "INSERT INTO maktab_league_schema.match (id, home_team_name, away_team_name, date, stadium_name) VALUES(?, ?, ?, ?, ?);";
			private static String DELETE_ALL = "DELETE FROM maktab_league_schema.match;";
		}

		private static class Contract {
			private static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS maktab_league_schema.contract ("
					+ " id int NOT NULL AUTO_INCREMENT," + " team_name varchar(25) NOT NULL,"
					+ " person_id int NOT NULL," + " person_role varchar(25) NOT NULL," + " payment int NOT NULL,"
					+ " year int NOT NULL," + " length int NOT NULL," + " PRIMARY KEY (id),"
					+ " KEY contract_FK (team_name),"
					+ " CONSTRAINT contract_FK FOREIGN KEY (team_name) REFERENCES team (name) ON DELETE CASCADE ON UPDATE CASCADE"
					+ ") ENGINE=InnoDB;";
			private static String INSERT_CONTRACT = "INSERT INTO maktab_league_schema.contract (team_name, person_id, person_role, payment, year, length) VALUES(?, ?, ?, ?, ?, ?);";
			private static String FETCH_HIGEST_PAID_COACH = "SELECT" + "	coach.id," + "	coach.name AS coach_name,"
					+ "	team.name AS team_name," + "	contract.payment" + " FROM" + "	maktab_league_schema.coach,"
					+ "	maktab_league_schema.contract," + "	maktab_league_schema.team" + " WHERE"
					+ "	coach.id = contract.person_id" + "	AND" + "	team.coach_id = coach.id"
					+ "	ORDER BY contract.payment DESC" + " LIMIT 1;";
			private static String FETCH_HIGEST_PAID_PLAYERS_LIST = "SELECT" + "	player.id,"
					+ "	player.name AS player_name," + "	team.name AS team_name," + "	contract.payment" + " FROM"
					+ "	maktab_league_schema.player," + "	maktab_league_schema.contract,"
					+ "	maktab_league_schema.team" + " WHERE" + "	player.id = contract.person_id"
					+ "	AND team.name = player.team_name" + " ORDER BY" + "	contract.payment DESC" + " LIMIT 10;";
			private static String DELETE_ALL = "DELETE FROM maktab_league_schema.contract;";

		}

		private static class PlayerMatchInfo {
			private static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS maktab_league_schema.player_match_info ("
					+ " player_id int NOT NULL," + " match_id int NOT NULL," + " number_of_goals int NOT NULL,"
					+ " number_of_assists int NOT NULL," + " PRIMARY KEY (player_id,match_id),"
					+ " KEY player_match_info_match_FK (match_id),"
					+ " CONSTRAINT player_match_info_player_FK FOREIGN KEY (player_id) REFERENCES player (id) ON DELETE CASCADE ON UPDATE CASCADE,"
					+ " CONSTRAINT player_match_info_match_FK FOREIGN KEY (match_id) REFERENCES maktab_league_schema.match (id) ON DELETE CASCADE ON UPDATE CASCADE"
					+ ") ENGINE=InnoDB;";
			private static String INSERT_MATCH_INFO = "INSERT INTO maktab_league_schema.player_match_info (player_id, match_id, number_of_goals, number_of_assists) VALUES(?, ?, ?, ?);";
			private static String FETCH_TOP_PLAYERS_GOAL = "SELECT" + "	p.name," + "	p.position,"
					+ "	p.team_name," + "	sum(pmi.number_of_goals) AS goals"
					+ " FROM maktab_league_schema.player p,	maktab_league_schema.player_match_info pmi"
					+ " WHERE p.id = pmi.player_id" + " GROUP BY p.id" + " ORDER BY goals DESC" + " LIMIT 10;";
			private static String FETCH_TOP_PLAYERS_ASSIST = "SELECT" + "	p.name," + "	p.position,"
					+ "	p.team_name," + "	sum(pmi.number_of_assists) AS assists"
					+ " FROM maktab_league_schema.player p,	maktab_league_schema.player_match_info pmi"
					+ " WHERE p.id = pmi.player_id" + " GROUP BY p.id" + " ORDER BY assists DESC" + " LIMIT 10;";
			private static String DELETE_ALL = "DELETE FROM maktab_league_schema.player_match_info;";
		}

		private static class TeamMatchInfo {
			private static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS maktab_league_schema.team_match_info ("
					+ " team_name varchar(25) NOT NULL," + " match_id int NOT NULL," + " score int NOT NULL,"
					+ " number_of_scored_goals int NOT NULL," + " number_of_received_goals int NOT NULL,"
					+ " PRIMARY KEY (team_name,match_id)," + " KEY team_match_info_match_FK (match_id),"
					+ " CONSTRAINT team_match_info_match_FK FOREIGN KEY (match_id) REFERENCES maktab_league_schema.match (id) ON DELETE CASCADE ON UPDATE CASCADE,"
					+ " CONSTRAINT team_match_info_team_FK FOREIGN KEY (team_name) REFERENCES team (name) ON DELETE CASCADE ON UPDATE CASCADE"
					+ ") ENGINE=InnoDB;";
			private static String INSERT_MATCH_INFO = "INSERT INTO maktab_league_schema.team_match_info (team_name, match_id, score, number_of_scored_goals, number_of_received_goals) VALUES(?, ?, ?, ?, ?);";
			private static String FETCH_ALL_TEAM_LEAGUE_INFO = "SELECT" + "	team_name ," + "	sum(score = 3) AS win,"
					+ "	sum(score = 1) AS draw," + "	sum(score = 0) AS loss,"
					+ "	sum(number_of_scored_goals) AS scored_goals,"
					+ "	sum(number_of_received_goals)AS received_goals," + "	sum(score) AS score" + " FROM"
					+ "	maktab_league_schema.team_match_info" + " GROUP BY team_name" + " ORDER BY score DESC;";
			private static String FETCH_ALL_DERBIES = "SELECT *,"
					+ "	number_of_scored_goals + number_of_received_goals AS goals" + " FROM"
					+ " maktab_league_schema.team_match_info tmi" + " WHERE tmi.match_id IN ( SELECT m.id"
					+ "	FROM maktab_league_schema.match m" + "	WHERE m.away_team_name IN ("
					+ "	SELECT t.name FROM maktab_league_schema.team t, maktab_league_schema.team t2"
					+ " WHERE t.city_name = t2.city_name" + "	AND t.name != t2.name"
					+ " AND m.home_team_name = t2.name))" + " ORDER BY goals DESC;";
			private static String DELETE_ALL = "DELETE FROM maktab_league_schema.team_match_info;";
		}
	}

	private Connection getConnection() throws SQLException {
		return DbConnection.getConnection();
	}

	public void initializeDatabase() throws DbException {
		try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
			try {
				statement.execute(Statements.Schema.CREATE);
			} catch (SQLException e) {
				throw new DbException("Error while creating Schema", e);
			}

			try {
				statement.execute(Statements.City.CREATE_TABLE);
			} catch (SQLException e) {
				throw new DbException("Error while creating table (city)", e);
			}

			try {
				statement.execute(Statements.City.DELETE_ALL);
			} catch (SQLException e) {
				throw new DbException("Error while deleting table contents (city)", e);
			}

			try {
				statement.execute(Statements.Stadium.CREATE_TABLE);
			} catch (SQLException e) {
				throw new DbException("Error while creating table (stadium)", e);
			}

			try {
				statement.execute(Statements.Stadium.DELETE_ALL);
			} catch (SQLException e) {
				throw new DbException("Error while deleting table contents (stadium)", e);
			}

			try {
				statement.execute(Statements.Coach.CREATE_TABLE);
			} catch (SQLException e) {
				throw new DbException("Error while creating table (coach)", e);
			}

			try {
				statement.execute(Statements.Coach.DELETE_ALL);
			} catch (SQLException e) {
				throw new DbException("Error while deleting table contents (coach)", e);
			}

			try {
				statement.execute(Statements.Team.CREATE_TABLE);
			} catch (SQLException e) {
				throw new DbException("Error while creating table (team)", e);
			}

			try {
				statement.execute(Statements.Team.DELETE_ALL);
			} catch (SQLException e) {
				throw new DbException("Error while deleting table contents (team)", e);
			}

			try {
				statement.execute(Statements.Player.CREATE_TABLE);
			} catch (SQLException e) {
				throw new DbException("Error while creating table (player)", e);
			}
			try {
				statement.execute(Statements.Player.DELETE_ALL);
			} catch (SQLException e) {
				throw new DbException("Error while deleting table contents (player)", e);
			}

			try {
				statement.execute(Statements.Match.CREATE_TABLE);
			} catch (SQLException e) {
				throw new DbException("Error while creating table (match)", e);
			}

			try {
				statement.execute(Statements.Match.DELETE_ALL);
			} catch (SQLException e) {
				throw new DbException("Error while deleting table contents (match)", e);
			}

			try {
				statement.execute(Statements.Contract.CREATE_TABLE);
			} catch (SQLException e) {
				throw new DbException("Error while creating table (contract)", e);
			}

			try {
				statement.execute(Statements.Contract.DELETE_ALL);
			} catch (SQLException e) {
				throw new DbException("Error while deleting table contents (contract)", e);
			}

			try {
				statement.execute(Statements.PlayerMatchInfo.CREATE_TABLE);
			} catch (SQLException e) {
				throw new DbException("Error while creating table (player_match_info)", e);
			}

			try {
				statement.execute(Statements.PlayerMatchInfo.DELETE_ALL);
			} catch (SQLException e) {
				throw new DbException("Error while deleting table contents (player_match_info)", e);
			}

			try {
				statement.execute(Statements.TeamMatchInfo.CREATE_TABLE);
			} catch (SQLException e) {
				throw new DbException("Error while creating table (team_match_info)", e);
			}
			try {
				statement.execute(Statements.TeamMatchInfo.DELETE_ALL);
			} catch (SQLException e) {
				throw new DbException("Error while deleting table contents (team_match_info)", e);
			}

		} catch (SQLException e) {
			throw new DbException("Error while connecting to database", e);
		} catch (DbException e) {
			throw e;
		}
	}

	public void clearDatabase() throws DbException {
		try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
			try {
				statement.execute(Statements.Schema.CREATE);
			} catch (SQLException e) {
				throw new DbException("Error while creating Schema", e);
			}

			try {
				statement.execute(Statements.City.CREATE_TABLE);
			} catch (SQLException e) {
				throw new DbException("Error while creating table (city)", e);
			}

			try {
				statement.execute(Statements.Stadium.CREATE_TABLE);
			} catch (SQLException e) {
				throw new DbException("Error while creating table (stadium)", e);
			}

			try {
				statement.execute(Statements.Coach.CREATE_TABLE);
			} catch (SQLException e) {
				throw new DbException("Error while creating table (coach)", e);
			}

			try {
				statement.execute(Statements.Team.CREATE_TABLE);
			} catch (SQLException e) {
				throw new DbException("Error while creating table (team)", e);
			}

			try {
				statement.execute(Statements.Player.CREATE_TABLE);
			} catch (SQLException e) {
				throw new DbException("Error while creating table (player)", e);
			}

			try {
				statement.execute(Statements.Match.CREATE_TABLE);
			} catch (SQLException e) {
				throw new DbException("Error while creating table (match)", e);
			}

			try {
				statement.execute(Statements.Contract.CREATE_TABLE);
			} catch (SQLException e) {
				throw new DbException("Error while creating table (contract)", e);
			}

			try {
				statement.execute(Statements.PlayerMatchInfo.CREATE_TABLE);
			} catch (SQLException e) {
				throw new DbException("Error while creating table (player_match_info)", e);
			}

			try {
				statement.execute(Statements.TeamMatchInfo.CREATE_TABLE);
			} catch (SQLException e) {
				throw new DbException("Error while creating table (team_match_info)", e);
			}

		} catch (SQLException e) {
			throw new DbException("Error while connecting to database", e);
		} catch (DbException e) {
			throw e;
		}
	}

	public void insertCity(City city) throws DbException {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(Statements.City.INSERT_CITY);) {
			statement.setString(1, city.getName());
			statement.execute();

		} catch (SQLException e) {
			throw new DbException("Error while inserting city: " + city.toString(), e);
		}
	}

	public void insertStadium(Stadium stadium) throws DbException {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(Statements.Stadium.INSERT_STADIUM);) {
			statement.setString(1, stadium.getName());
			statement.setInt(2, stadium.getCapacity());
			statement.setString(3, stadium.getCity().getName());
			statement.execute();

		} catch (SQLException e) {
			throw new DbException("Error while inserting stadium: " + stadium.toString(), e);
		}
	}

	public void insertCoach(Coach coach) throws DbException {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(Statements.Coach.INSERT_COACH);) {
			statement.setInt(1, coach.getId());
			statement.setString(2, coach.getName());
			statement.execute();

		} catch (SQLException e) {
			throw new DbException("Error while inserting coach: " + coach.toString(), e);
		}
	}

	public void insertTeam(Team team) throws DbException {
		try {
			Connection connection = getConnection();
			connection.setAutoCommit(false);

			try (PreparedStatement insertTeamStatement = connection.prepareStatement(Statements.Team.INSERT_TEAM);
					PreparedStatement insertCoachContract = connection
							.prepareStatement(Statements.Contract.INSERT_CONTRACT);) {

				// inserting team
				insertTeamStatement.setString(1, team.getName());
				insertTeamStatement.setInt(2, team.getCoach().getId());
				insertTeamStatement.setInt(3, team.getCapitan() == null ? 0 : team.getCapitan().getId());
				insertTeamStatement.setString(4, team.getCity().getName());
				insertTeamStatement.execute();

				// inserting contract of team's coach
				insertCoachContract.setString(1, team.getName());
				insertCoachContract.setInt(2, team.getCoach().getId());
				insertCoachContract.setString(3, PersonRole.COACH.name());
				insertCoachContract.setInt(4, team.getCoach().getContract().getPayment());
				insertCoachContract.setInt(5, team.getCoach().getContract().getYear());
				insertCoachContract.setInt(6, team.getCoach().getContract().getLength());
				insertCoachContract.execute();

			} catch (SQLException e) {
				connection.rollback();
				throw new DbException("Error while inserting team: " + team.toString(), e);
			}

			connection.commit();
		} catch (SQLException e) {
			throw new DbException("Error while getting connection to inserting team: " + team.toString(), e);
		}

	}

	public void insertPlayer(Player player) throws DbException {
		try {
			Connection connection = getConnection();
			connection.setAutoCommit(false);

			try (PreparedStatement insertPlayer = connection.prepareStatement(Statements.Player.INSERT_PLAYER);
					PreparedStatement insertPlayerContract = connection
							.prepareStatement(Statements.Contract.INSERT_CONTRACT);) {
				// insertting player
				insertPlayer.setInt(1, player.getId());
				insertPlayer.setString(2, player.getName());
				insertPlayer.setString(3, player.getPosition().name());
				insertPlayer.setInt(4, player.getSkillLevel());
				insertPlayer.setString(5, player.getTeam().getName());
				insertPlayer.execute();

				// inserting contract of player
				insertPlayerContract.setString(1, player.getTeam().getName());
				insertPlayerContract.setInt(2, player.getId());
				insertPlayerContract.setString(3, PersonRole.PLAYER.name());
				insertPlayerContract.setInt(4, player.getContract().getPayment());
				insertPlayerContract.setInt(5, player.getContract().getYear());
				insertPlayerContract.setInt(6, player.getContract().getLength());
				insertPlayerContract.execute();

			} catch (SQLException e) {
				connection.rollback();
				throw new DbException("Error while inserting player: " + player.toString(), e);
			}

			connection.commit();
		} catch (SQLException e) {
			throw new DbException("Error while getting connection to inserting player: " + player.toString(), e);
		}

	}

	public void insertMatch(Match match) throws DbException {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(Statements.Match.INSERT_MATCH);) {
			statement.setInt(1, match.getId());
			statement.setString(2, match.getHomeTeam().getName());
			statement.setString(3, match.getAwayTeam().getName());
			statement.setDate(4, match.getDate());
			statement.setString(5, match.getStadium().getName());
			statement.execute();

		} catch (SQLException e) {
			throw new DbException("Error while inserting match: " + match.toString(), e);
		}

	}

	public void insertTeamMatchInfo(TeamMatchInfo teamMatchInfo) throws DbException {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection
						.prepareStatement(Statements.TeamMatchInfo.INSERT_MATCH_INFO);) {
			statement.setString(1, teamMatchInfo.getTeam().getName());
			statement.setInt(2, teamMatchInfo.getMatch().getId());
			statement.setInt(3, teamMatchInfo.getScore());
			statement.setInt(4, teamMatchInfo.getScoredGoals());
			statement.setInt(5, teamMatchInfo.getReceivedGoals());
			statement.execute();

		} catch (SQLException e) {
			throw new DbException("Error while inserting team match info: " + teamMatchInfo.toString(), e);
		}

	}

	public void insertPlayerMatchInfo(PlayerMatchInfo playerMatchInfo) throws DbException {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection
						.prepareStatement(Statements.PlayerMatchInfo.INSERT_MATCH_INFO);) {
			statement.setInt(1, playerMatchInfo.getPlayer().getId());
			statement.setInt(2, playerMatchInfo.getMatch().getId());
			statement.setInt(3, playerMatchInfo.getNumberOfScoredGoals());
			statement.setInt(4, playerMatchInfo.getNumberOfAssists());
			statement.execute();

		} catch (SQLException e) {
			throw new DbException("Error while inserting player match info: " + playerMatchInfo.toString(), e);
		}

	}

	public List<TeamLeagueInfo> fetchAllTeamsLeagueInfo() throws DbException {
		List<TeamLeagueInfo> result = new ArrayList<>(16);
		try (Connection connection = getConnection();
				PreparedStatement statement = connection
						.prepareStatement(Statements.TeamMatchInfo.FETCH_ALL_TEAM_LEAGUE_INFO);) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String teamName = resultSet.getString("team_name");
				int win = resultSet.getInt("win");
				int draw = resultSet.getInt("draw");
				int loss = resultSet.getInt("loss");
				int scoredGoals = resultSet.getInt("scored_goals");
				int receivedGoals = resultSet.getInt("received_goals");
				int score = resultSet.getInt("score");
				result.add(new TeamLeagueInfo(teamName, win, draw, loss, scoredGoals, receivedGoals, score));
			}

		} catch (SQLException e) {
			throw new DbException("Error while fetching all team league info", e);
		}
		return result;
	}

	public String fetchHigestPaidCoach() throws DbException {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection
						.prepareStatement(Statements.Contract.FETCH_HIGEST_PAID_COACH);) {
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int coachId = resultSet.getInt("id");
				String coachName = resultSet.getString("coach_name");
				String teamName = resultSet.getString("team_name");
				int payment = resultSet.getInt("payment");
				return "id: " + coachId + " name: " + coachName + " team: " + teamName + " payment: " + payment;
			}

		} catch (SQLException e) {
			throw new DbException("Error while fetching highest paid coach", e);
		}
		return "";
	}

	public List<String> fetchMostPaidPlayersList() throws DbException {
		List<String> result = new ArrayList<>();
		result.add("ID,Team,Name,Payment");
		try (Connection connection = getConnection();
				PreparedStatement statement = connection
						.prepareStatement(Statements.Contract.FETCH_HIGEST_PAID_PLAYERS_LIST);) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int playerId = resultSet.getInt("id");
				String playerName = resultSet.getString("player_name");
				String teamName = resultSet.getString("team_name");
				int payment = resultSet.getInt("payment");
				result.add(playerId + "," + teamName + "," + playerName + "," + payment);
			}

		} catch (SQLException e) {
			throw new DbException("Error while fetching highest paid players list", e);
		}
		return result;
	}

	public List<String> fetchCitiesTeamNumberList() throws DbException {
		List<String> result = new ArrayList<>();
		result.add("City,Teams#");
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(Statements.City.FETCH_CITY_TEAM_NUMBERS);) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String cityName = resultSet.getString("name");
				int number = resultSet.getInt("number");
				result.add(cityName + "," + number);
			}

		} catch (SQLException e) {
			throw new DbException("Error while fetching cities list", e);
		}
		return result;
	}

	public List<String> fetchAllDerbiesList() throws DbException {
		List<String> result = new ArrayList<>();
		result.add("Match ID,Home Team,Away Team,Goals");
		try (Connection connection = getConnection();
				PreparedStatement statement = connection
						.prepareStatement(Statements.TeamMatchInfo.FETCH_ALL_DERBIES);) {
			ResultSet resultSet = statement.executeQuery();
			String homeTeamName = null;
			while (resultSet.next())
				if (homeTeamName == null)
					homeTeamName = resultSet.getString("team_name");
				else {
					String awayTeamName = resultSet.getString("team_name");
					int goals = resultSet.getInt("goals");
					int matchId = resultSet.getInt("match_id");
					result.add(matchId + "," + homeTeamName + "," + awayTeamName + "," + goals);
					homeTeamName = null;
				}

		} catch (SQLException e) {
			throw new DbException("Error while fetching all derbies list", e);
		}
		return result;
	}

	public List<String> fetchTopSkilledPlayersList() throws DbException {
		List<String> result = new ArrayList<>();
		result.add("Team,Player,Position,Skill Level");
		try (Connection connection = getConnection();
				PreparedStatement statement = connection
						.prepareStatement(Statements.Player.FETCH_TOP_SKILLED_PLAYERS);) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String playerName = resultSet.getString("name");
				PlayerPosition position = PlayerPosition.valueOf(resultSet.getString("position"));
				int skillLevel = resultSet.getInt("skill_level");
				String teamName = resultSet.getString("team_name");
				result.add(teamName + "," + playerName + "," + position.toString() + "," + skillLevel);
			}

		} catch (SQLException e) {
			throw new DbException("Error while fetching top skilled players list", e);
		}
		return result;
	}

	public List<String> fetchTopPlayersGoalsList() throws DbException {
		List<String> result = new ArrayList<>();
		result.add("Team,Player,Position,Goals");
		try (Connection connection = getConnection();
				PreparedStatement statement = connection
						.prepareStatement(Statements.PlayerMatchInfo.FETCH_TOP_PLAYERS_GOAL);) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String playerName = resultSet.getString("name");
				PlayerPosition position = PlayerPosition.valueOf(resultSet.getString("position"));
				String teamName = resultSet.getString("team_name");
				int goals = resultSet.getInt("goals");
				result.add(teamName + "," + playerName + "," + position.toString() + "," + goals);
			}

		} catch (SQLException e) {
			throw new DbException("Error while fetching top players goal list", e);
		}
		return result;
	}

	public List<String> fetchTopPlayersAssistsList() throws DbException {
		List<String> result = new ArrayList<>();
		result.add("Team,Player,Position,Assists");
		try (Connection connection = getConnection();
				PreparedStatement statement = connection
						.prepareStatement(Statements.PlayerMatchInfo.FETCH_TOP_PLAYERS_ASSIST);) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String playerName = resultSet.getString("name");
				PlayerPosition position = PlayerPosition.valueOf(resultSet.getString("position"));
				String teamName = resultSet.getString("team_name");
				int assists = resultSet.getInt("assists");
				result.add(teamName + "," + playerName + "," + position.toString() + "," + assists);
			}

		} catch (SQLException e) {
			throw new DbException("Error while fetching top players assist list", e);
		}
		return result;
	}

}