package app;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class PlaySnake extends Application {
	

	public static void main(String[] args) {
		launch(PlaySnake.class, args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("snake.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Snake");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
	