package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		Parent parent =(Parent) FXMLLoader.load(getClass().getResource("/application/view/MainView.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Opowieœci z IT budki");
		stage.getIcons().add(new Image("https://vignette.wikia.nocookie.net/awesomeanimeandmanga/images/d/d4/Hello_Kitty%21.png/revision/latest?cb=20110525032048"));
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}