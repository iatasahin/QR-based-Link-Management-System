package se360.shortlinker.restserver.dao;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import se360.shortlinker.restserver.RestserverApplication;
import se360.shortlinker.restserver.model.Link;
import se360.shortlinker.restserver.model.User;
import se360.shortlinker.restserver.util.DBUtil;

import java.sql.*;
import java.util.List;

@Component
@NoArgsConstructor
public class UserDAO {
    private static final String INSERT_USER_SQL = "INSERT INTO users (id, username, password, email, created_at) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID_SQL = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_USER_BY_USERNAME_SQL = "SELECT * FROM users WHERE username = ?";

    public void saveUser(User user) {
        if(user.getId() == null) user.setId(DBUtil.getNextUserId());
        try (Connection connection = DriverManager.getConnection(DBUtil.jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setTimestamp(5, Timestamp.from(user.getCreatedAt()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(Long id) {
        User user;
        try (Connection connection = DriverManager.getConnection(DBUtil.jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                user =  mapToUser(resultSet);
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        List<Link> links = RestserverApplication.linksDAO.getLinksByUserId(user.getId());
        user.setLinks(links);
        return user;
    }

    public User getUserByUsername(String username) {
        User user;
        try (Connection connection = DriverManager.getConnection(DBUtil.jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME_SQL)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                user =  mapToUser(resultSet);
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        List<Link> links = RestserverApplication.linksDAO.getLinksByUserId(user.getId());
        for (Link link : links)
            link.setUser(user);
        user.setLinks(links);
        return user;
    }

    private User mapToUser(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String email = resultSet.getString("email");
        Timestamp createdAt = resultSet.getTimestamp("created_at");
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .email(email)
                .createdAt(createdAt.toInstant())
                .build();
    }
}
