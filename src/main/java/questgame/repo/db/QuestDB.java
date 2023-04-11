package questgame.repo.db;

import questgame.domain.Quest;
import questgame.repo.QuestRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class QuestDB implements QuestRepo {

    private String url;
    private String dbusername;
    private String dbpassword;


    public QuestDB(String url, String username, String password) {
        this.url = url;
        this.dbusername = username;
        this.dbpassword = password;
    }
    @Override
    public void delete(UUID id) {
        Quest quest = findOne(id);
        if(quest != null) {
            String sql = String.format("DELETE FROM \"quest\" WHERE id = '" + id.toString() + "'");
            try (Connection connection = DriverManager.getConnection(this.url, this.dbusername, this.dbpassword);
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeUpdate();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<Quest> findByGame(String gameP) {
        ArrayList<Quest> quests = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"quest\" WHERE game = '" + gameP + "'");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                UUID id = (UUID) resultSet.getObject("id");
                String name = resultSet.getString("name");
                String question = resultSet.getString("question");
                String wrong1 = resultSet.getString("wrong1");
                String wrong2 = resultSet.getString("wrong2");
                String correct = resultSet.getString("correct");
                String game = resultSet.getString("game");
                Quest quest = new Quest(id, name, question, wrong1, wrong2, correct, game);
                quests.add(quest);
            }
            return quests;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quests;
    }

    @Override
    public Quest findOne(UUID uuid) {
        String sql = String.format("SELECT * FROM \"quest\" WHERE id = '" + uuid.toString() + "'");
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()){
            rs.next();
            UUID id = (UUID) rs.getObject("id");
            String name = rs.getString("name");
            String question = rs.getString("question");
            String wrong1 = rs.getString("wrong1");
            String wrong2 = rs.getString("wrong2");
            String correctAnswer = rs.getString("correct");
            String game = rs.getString("game");

            Quest quest = new Quest(id, name, question, wrong1, wrong2, correctAnswer, game);

            return quest;
        } catch (SQLException throwable) {
            throwable.printStackTrace();

        }
        return null;
    }

    @Override
    public Iterable<Quest> findAll() {
        ArrayList<Quest> quests = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"quest\" ");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                UUID id = (UUID) resultSet.getObject("id");
                String name = resultSet.getString("name");
                String question = resultSet.getString("question");
                String wrong1 = resultSet.getString("wrong1");
                String wrong2 = resultSet.getString("wrong2");
                String correct = resultSet.getString("correct");
                String game = resultSet.getString("game");
                Quest quest = new Quest(id, name, question, wrong1, wrong2, correct, game);
                quests.add(quest);
            }
            return quests;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quests;
    }

    @Override
    public void save(Quest quest) {
        String sql = "INSERT INTO \"quest\" (id, name,question, wrong1, wrong2, correct, game) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, quest.getId());
            ps.setString(2, quest.getName());
            ps.setString(3, quest.getQuestion());
            ps.setString(4, quest.getWrong1());
            ps.setString(5, quest.getWrong2());
            ps.setString(6, quest.getCorrect());
            ps.setString(7, quest.getGame());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
