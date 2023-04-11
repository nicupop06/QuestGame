package questgame.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import questgame.domain.Quest;
import questgame.domain.User;
import questgame.service.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestController {
    private Quest quest;
    private User user;

    private Service service;
    private MainpageController mainpageController;

    public void setMainpageController(MainpageController mainpageController){
        this.mainpageController = mainpageController;
    }

    public void setUser(User user){
        this.user = user;
    }
    public void setService(Service service){
        this.service = service;
    }
    public void setQuest(Quest quest){
        this.quest = quest;
    }

    @FXML
    private Label questionLabel;

    @FXML
    private RadioButton radio1;

    @FXML
    private RadioButton radio2;

    @FXML
    private RadioButton radio3;

    @FXML
    private Button submitBtn;

    @FXML
    void handleSubmit(ActionEvent event) {
        if(
                radio1.isSelected() && radio1.getText().equals(quest.getCorrect()) ||
                radio2.isSelected() && radio2.getText().equals(quest.getCorrect()) ||
                radio3.isSelected() && radio3.getText().equals(quest.getCorrect())
        ){
            PopupMessagesController.showMessage(null, Alert.AlertType.INFORMATION, "Info", "Good! +100 tokens");
            service.updateup(user);
            System.out.println("updated");
            mainpageController.setConnectedUser(user);
            System.out.println("set connected user");
            service.delete(quest.getId());
            System.out.println("deleted quest");
            mainpageController.setGameCB();
            System.out.println("set combo box");



        }
        else
            PopupMessagesController.showMessage(null, Alert.AlertType.INFORMATION, "Info", "Wrong! Try again later :D");


        Stage stage = (Stage) questionLabel.getScene().getWindow();
        stage.close();
    }

    public void setItems(){


        questionLabel.setText(quest.getQuestion());

        ToggleGroup question= new ToggleGroup();

        List<String> answers = new ArrayList<>();
        answers.add(quest.getWrong1());
        answers.add(quest.getWrong2());
        answers.add(quest.getCorrect());

        Collections.shuffle(answers);

        radio1.setToggleGroup(question);
        radio2.setToggleGroup(question);
        radio3.setToggleGroup(question);



        radio1.setText(answers.get(0));
        radio2.setText(answers.get(1));
        radio3.setText(answers.get(2));


    }
}
