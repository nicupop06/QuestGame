package questgame.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import questgame.domain.Quest;
import questgame.domain.User;
import questgame.service.Service;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainpageController implements Initializable {

    private Service service;
    private User user;

    public void setService(Service service) {
        this.service = service;
    }

    @FXML
    private ImageView badgeImg;

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private Label connectedasLabel;

    @FXML
    private Label tokensLabel;

    @FXML
    private Button imgBtn;

    @FXML
    private ComboBox<String> gameCB;

    @FXML
    private TableColumn<Quest, String> questsColumn;

    @FXML
    private TableView<Quest> questsTable;

    @FXML
    private TextField wa1TF;
    @FXML
    private TextField wa2TF;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField questionTF;
    @FXML
    private TextField caTF;
    @FXML
    private TextField gameTF;


    @FXML
    void handleCreate(ActionEvent event) {
        if(!nameTF.getText().equals("") && !questionTF.getText().equals("") && !wa1TF.getText().equals("") && !wa2TF.getText().equals("") && !caTF.getText().equals("") && !gameTF.getText().equals("") && user.getTokens() >= 100){
          service.save(new Quest(nameTF.getText(), questionTF.getText(), wa1TF.getText(), wa2TF.getText(), caTF.getText(), gameTF.getText()));
          PopupMessagesController.showMessage(null, Alert.AlertType.INFORMATION, "Info", "Created! It was 100 tokens.");
          service.updatedown(user);
          setGameCB();
          setConnectedUser(user);
        }
        else if(user.getTokens() < 100)
            PopupMessagesController.showMessage(null, Alert.AlertType.INFORMATION, "Info", "Not enough tokens, do some quests!");
        else
                PopupMessagesController.showMessage(null, Alert.AlertType.INFORMATION, "Info", "Please fill all data!");

    }

    public void setConnectedUser(User user){

        connectedasLabel.setText("Hello, " + user.getUsername() + "! :)");
        tokensLabel.setText("Tokens " + user.getTokens());
        if(user.getTokens() <= 500)
            badgeImg = new ImageView(getClass().getResource("/img/bronze.png").toExternalForm());
        else if(user.getTokens() > 500 && user.getTokens() <= 1000)
            badgeImg = new ImageView(getClass().getResource("/img/silver.png").toExternalForm());
        else
            badgeImg = new ImageView(getClass().getResource("/img/gold.png").toExternalForm());

        badgeImg.setFitHeight(26);
        badgeImg.setFitWidth(28);
        imgBtn.setGraphic(badgeImg);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        questsColumn.setCellValueFactory(new PropertyValueFactory<Quest, String>("name"));

        questsTable.setRowFactory( tv -> {
            TableRow<Quest> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Quest rowData = row.getItem();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/quest.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    QuestController questController = fxmlLoader.getController();
                    questController.setService(service);
                    questController.setUser(user);
                    questController.setMainpageController(this);
                    questController.setQuest(rowData);
                    questController.setItems();

                    Stage stage = new Stage();
                    stage.setTitle(rowData.getName());
                    stage.setScene(scene);
                    stage.show();
                }
            });
            return row ;
        });
    }

    public void fillQuestTable(){
        String game = gameCB.getValue();
        List<Quest> questsToShow = service.findByGame(game);

        ObservableList<Quest> list = FXCollections.observableList(questsToShow);
        questsTable.setItems(list);
    }
    public void setGameCB(){
        gameCB.getItems().clear();
        service.findAllQuests().stream().forEach(quest -> {
            if(!gameCB.getItems().contains(quest.getGame()))
                gameCB.getItems().add(quest.getGame());
        });

        gameCB.setOnAction(e -> {
            fillQuestTable();
        });

    }

    @FXML
    void handleLeaderboard(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/leaderboard.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LeaderboardController leaderboardController = fxmlLoader.getController();
        leaderboardController.setService(service);
        leaderboardController.fillLeaderboardTable();



        Stage stage = new Stage();
        stage.setTitle("Leaderboard");
        stage.setScene(scene);
        stage.show();
    }

}
