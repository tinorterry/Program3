package Program3GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Program3GUI.fxml"));
        primaryStage.setTitle("Tuition Manager");
        Scene primaryScene = new Scene(root, 300, 275);
        primaryScene.getStylesheets().add(getClass().getResource("Program3GUI.css").toExternalForm());
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
