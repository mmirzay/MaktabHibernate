package homeworks.hw16.backend.database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DbConnection {
	private static DataSource dataSource;

	public static Connection getConnection() throws SQLException {
		if (dataSource == null) {
			dataSource = new MysqlDataSource();
			((MysqlDataSource) dataSource).setUrl(DbConfig.URL);
			((MysqlDataSource) dataSource).setUser(DbConfig.USERNAME);
			((MysqlDataSource) dataSource).setPassword(DbConfig.PASSWORD);
		}
		return dataSource.getConnection();
	}
}
