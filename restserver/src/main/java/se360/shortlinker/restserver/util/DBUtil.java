package se360.shortlinker.restserver.util;

import se360.shortlinker.restserver.RestserverApplication;
import se360.shortlinker.restserver.dao.UserDAO;
import se360.shortlinker.restserver.dao.LinksDAO;
import se360.shortlinker.restserver.model.Link;
import se360.shortlinker.restserver.model.User;

import java.sql.*;
import java.time.Instant;


public class DBUtil {
    public static final String jdbcUrl = "jdbc:sqlite:/home/ias/code/" + "qr.db";
    private static final UserDAO userDAO = RestserverApplication.userDAO;
    private static final LinksDAO linksDAO = RestserverApplication.linksDAO;

    public static void createTables() {
        executeStatement(sqlCreateUserTable);
        executeStatement(sqlCreateLinkTable);
        String sqlEmptyTables = "DELETE FROM users WHERE true;";
        executeStatement(sqlEmptyTables);
        sqlEmptyTables = "DELETE FROM links WHERE true;";
        executeStatement(sqlEmptyTables);
    }

    public static void createDummyData() {
        for (int i = 0; i < 10; i++) {
            User user = User.builder()
                    .id((long) i + 1)
                    .username("user" + i)
                    .password("password" + i)
                    .email("user" + i + "@qwe" + i + ".com")
                    .createdAt(Instant.now())
                    .build();
            userDAO.saveUser(user);
        }

        for (int i = 0; i < 100; i++) {
            Link link = Link.builder()
                    .url("https://www.google.com/" + i)
                    .name("linkName" + i)
                    .clicks((long) i * i)
                    .createdAt(Instant.now())
                    .user(User.builder().id((long) i % 10 + 1).build())
                    .build();
            linksDAO.saveLink(link);
        }
    }

    private static final String sqlCreateUserTable = """
            CREATE TABLE IF NOT EXISTS users (
            	id integer PRIMARY KEY,
            	username text NOT NULL,
            	password text NOT NULL,
            	email text NOT NULL,
            	created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
            );
            """;

    private static final String sqlCreateLinkTable = """
            CREATE TABLE IF NOT EXISTS links (
                id integer PRIMARY KEY,
                name text NOT NULL,
                url text NOT NULL,
                clicks integer NOT NULL DEFAULT 0,
                user_id integer NOT NULL,
                created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (user_id) REFERENCES users (id)
            );
            """;
    private static boolean executeStatement(String sql) {
        try (Connection conn = DriverManager.getConnection(jdbcUrl);
             java.sql.Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private static final String GET_NEXT_USER_ID_SQL = "SELECT MAX(id) + 1 FROM users";
    private static final String GET_NEXT_LINK_ID_SQL = "SELECT MAX(id) + 1 FROM links";

    public static Long getNextUserId() {
        return queryLong(GET_NEXT_USER_ID_SQL);
    }

    public static Long getNextLinkId() {
        return queryLong(GET_NEXT_LINK_ID_SQL);
    }

    private static Long queryLong(String sql) {
        try (Connection connection = DriverManager.getConnection(jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getLong(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
