package questgame.repo.db;

import questgame.domain.User;
import questgame.repo.UserRepo;

import java.sql.*;
import java.util.*;

import java.util.UUID;

public class UserDB implements UserRepo {

    private String url;
    private String dbusername;
    private String dbpassword;


    public UserDB(String url, String username, String password) {
        this.url = url;
        this.dbusername = username;
        this.dbpassword = password;
    }
    @Override
    public User findOne(UUID uuid) {
        String sql = String.format("SELECT * FROM \"user\" WHERE id = '" + uuid.toString() + "'");
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()){
            rs.next();
            UUID id = (UUID) rs.getObject("id");
            String username1 = rs.getString("username");
            String password1 = rs.getString("password");
            int tokens = rs.getInt("tokens");

            User user = new User(id, username1, password1, tokens);

            return user;
        } catch (SQLException throwable) {
            throwable.printStackTrace();

        }
        return null;
    }

    @Override
    public Iterable<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"user\" ");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                UUID id = (UUID) resultSet.getObject("id");
                String username1 = resultSet.getString("username");
                String password1 = resultSet.getString("password");
                int tokens = resultSet.getInt("tokens");
                User user = new User(id, username1, password1, tokens);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO \"user\" (id, username,password, tokens) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, user.getId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getTokens());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findOneByUsername(String usernameP) {
        String sql = String.format("SELECT * FROM \"user\" WHERE username = '" + usernameP + "'");
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()){
            rs.next();
            UUID id = (UUID) rs.getObject("id");
            String username1 = rs.getString("username");
            String password1 = rs.getString("password");
            int tokens = rs.getInt("tokens");

            User user = new User(id, username1, password1, tokens);

            return user;
        } catch (SQLException throwable) {
            throwable.printStackTrace();

        }
        return null;
    }

    @Override
    public void updateup(User user) {
        if(user != null) {
            user.setTokens(user.getTokens() + 100);
            String sql = String.format("UPDATE \"user\" SET tokens = '" +
                    user.getTokens() +
                    "' WHERE id = '" +
                    user.getId().toString() + "'");
            try (Connection connection = DriverManager.getConnection(this.url, this.dbusername, this.dbpassword);
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeUpdate();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }

    @Override
    public void updatedown(User user) {
        if(user != null) {
            user.setTokens(user.getTokens() - 100);
            String sql = String.format("UPDATE \"user\" SET tokens = '" +
                    user.getTokens() +
                    "' WHERE id = '" +
                    user.getId().toString() + "'");
            try (Connection connection = DriverManager.getConnection(this.url, this.dbusername, this.dbpassword);
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeUpdate();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }
}
