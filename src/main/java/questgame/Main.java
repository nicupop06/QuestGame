package questgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import questgame.controllers.LoginController;
import questgame.domain.Quest;
import questgame.domain.User;
import questgame.repo.db.QuestDB;
import questgame.repo.db.UserDB;
import questgame.service.Service;


public class Main extends Application {

    private Service service = new Service(new UserDB("jdbc:postgresql://localhost:5432/QuizGame", "postgres", "postgres"),
            new QuestDB("jdbc:postgresql://localhost:5432/QuizGame", "postgres", "postgres"));

    private Scene scene;
    private Parent root;

    @Override
    public void start(Stage primaryStage) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/login.fxml"));
            root = loader.load();
            LoginController loginController = loader.getController();
            loginController.setService(service);
            scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
