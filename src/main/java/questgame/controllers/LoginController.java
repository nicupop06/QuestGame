package questgame.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import questgame.domain.User;
import questgame.service.Service;

import java.io.IOException;

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Service service;

    public void setService(Service service) {
        this.service = service;
    }

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField passwordTF;

    @FXML
    private TextField usernameTF;

    @FXML
    void handleLogin(ActionEvent event) throws IOException {
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        User user = service.findOneByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            PopupMessagesController.showMessage(null, Alert.AlertType.INFORMATION, "Info", "Parola incorecta!");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/mainpage.fxml"));

            root = loader.load();

            MainpageController mainpageController = loader.getController();
            mainpageController.setUser(user);
            mainpageController.setService(service);
            mainpageController.setConnectedUser(user);
            mainpageController.setGameCB();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

}
