package se360.shortlinker.restserver.dao;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import se360.shortlinker.restserver.model.Link;
import se360.shortlinker.restserver.model.User;
import se360.shortlinker.restserver.util.DBUtil;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class LinksDAO {
    private static final String INSERT_LINK_SQL = "INSERT INTO links (id, name, url, clicks, user_id, created_at) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_LINKS_BY_USER_ID_SQL = "SELECT * FROM links WHERE user_id = ?";
    private static final String INCREMENT_CLICK_COUNT_SQL = "UPDATE links SET clicks = clicks + 1 WHERE id = ?";
    private static final String SELECT_LINK_BY_ID_SQL = "SELECT * FROM links WHERE id = ?";
    private static final String DELETE_LINK_SQL = "DELETE FROM links WHERE id = ?";

    public Link saveLink(Link link) {
        if (link.getId() == null) link.setId(DBUtil.getNextLinkId());
        if (link.getCreatedAt() == null) link.setCreatedAt(Instant.now());
        try (Connection connection = DriverManager.getConnection(DBUtil.jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LINK_SQL)) {
            preparedStatement.setLong(1, link.getId());
            preparedStatement.setString(2, link.getName());
            preparedStatement.setString(3, link.getUrl());
            preparedStatement.setLong(4, link.getClicks());
            preparedStatement.setLong(5, link.getUser().getId());
            preparedStatement.setTimestamp(6, Timestamp.from(link.getCreatedAt()));
            preparedStatement.executeUpdate();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return link;
    }

    public void incrementClickCount(Long id) {
        try (Connection connection = DriverManager.getConnection(DBUtil.jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(INCREMENT_CLICK_COUNT_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public Link getLink(Long id) {
        try (Connection connection = DriverManager.getConnection(DBUtil.jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LINK_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) return mapToLink(resultSet);
            else return null;
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Link> getLinksByUserId(Long id) {
        try (Connection connection = DriverManager.getConnection(DBUtil.jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LINKS_BY_USER_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToLinks(resultSet);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteLink(Long id) {
        try (Connection connection = DriverManager.getConnection(DBUtil.jdbcUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LINK_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    private Link mapToLink(ResultSet resultSet) {
        try {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String url = resultSet.getString("url");
            Long clicks = resultSet.getLong("clicks");
            Long userId = resultSet.getLong("user_id");
            Timestamp createdAt = resultSet.getTimestamp("created_at");
            return Link.builder()
                    .id(id)
                    .name(name)
                    .url(url)
                    .clicks(clicks)
                    .user(User.builder().id(userId).build())
                    .createdAt(createdAt.toInstant())
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Link> mapToLinks(ResultSet resultSet) throws SQLException {
        List<Link> links = new ArrayList<>();
        while (resultSet.next()) {
            links.add(mapToLink(resultSet));
        }
        return links;
    }
}
