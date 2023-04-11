package questgame.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import questgame.domain.User;
import questgame.service.Service;

import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class LeaderboardController implements Initializable {
    private Service service;

    public void setService(Service service) {
        this.service = service;
    }

    @FXML
    private TableView<User> leaderboardTable;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, Integer> tokensColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        tokensColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("tokens"));

    }

    public void fillLeaderboardTable(){
        List<User> users = service.findAll();

        Collections.sort(users, new Comparator<User>() {

            public int compare(User o1, User o2) {
                // compare two instance of `Score` and return `int` as result.
                return o2.getTokens() - o1.getTokens();
            }
        });

        ObservableList<User> list = FXCollections.observableList(users);
        leaderboardTable.setItems(list);
    }
}
