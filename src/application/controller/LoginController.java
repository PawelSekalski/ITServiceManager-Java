package application.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.database.DBConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {
	private static final AlertType Warning = null;
    @FXML
    private TextField tf_log;
    @FXML
    private PasswordField pf_pass;
    @FXML
    private TextField tf_pass;
    @FXML
    private Button btn_show;
    @FXML
    private Button btn_log;
    @FXML
    private Button btn_quit;

    public DBConnector db;
    String logowanie;
    String uprawnienia;
    static int idPracownika;
    
    @FXML
    void showAction(MouseEvent event) {
    	pf_pass.setVisible(false);
    	tf_pass.setVisible(true);
    	String pass = pf_pass.getText();
    	tf_pass.setText(pass);
    	pf_pass.setText("");
    }
    @FXML
    void hideAction(MouseEvent event) {
    	pf_pass.setVisible(true);
    	tf_pass.setVisible(false);
    	String pass = tf_pass.getText();
    	pf_pass.setText(pass);
    	tf_pass.setText("");
    }
    
    // logowanie siê do bazy. do wyboru: admin zarz¹dzaj¹cy informacjami centrów oraz konto goœcia, który ma ograniczony wgl¹d do podstawowych informacji o centrach
    @FXML
    void loginDBAction(MouseEvent event) throws ClassNotFoundException, SQLException, IOException {
    	Connection conn = db.Connection();
    	Statement statement = conn.createStatement();
    	try {
	    	ResultSet result = statement.executeQuery("select * from users where log='"+tf_log.getText()+"'and pass='"+pf_pass.getText()+"';");
	    	boolean next = result.next();
	    	if(result.getString("perm").equals("101")) {	
	    		if (next) {
		    		Stage stageInfo = new Stage();
		    		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/application/view/DBAdminView.fxml"));
		    		Scene sceneInfo = new Scene(parent);
		    		stageInfo.setScene(sceneInfo);
		    		stageInfo.setTitle("HELL KITTY!");
		    		stageInfo.getIcons().add(new Image("https://vignette.wikia.nocookie.net/awesomeanimeandmanga/images/d/d4/Hello_Kitty%21.png/revision/latest?cb=20110525032048"));
		    		((Node) event.getSource()).getScene().getWindow().hide();
		    		stageInfo.show();
		    	} else {
		    		Alert alert = new Alert (AlertType.INFORMATION);
		    		alert.setContentText("Z³y login lub has³o");
		    		alert.setTitle("FATALNY ERROR");
		    		alert.setHeaderText("ERROR");
		    		alert.showAndWait();
		    	}
	    		} else {
	    			if(next) {
	        			idPracownika = result.getInt("id_u");
	        			logowanie = result.getString("log");
	    	    		Stage stageTable = new Stage();
	    	    		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/application/view/DBGuestView.fxml"));
	    	    		Scene sceneTable = new Scene(parent);
	    	    		stageTable.setScene(sceneTable);
	    	    		stageTable.setTitle("You are just a guest here");
	    	    		stageTable.getIcons().add(new Image("https://vignette.wikia.nocookie.net/awesomeanimeandmanga/images/d/d4/Hello_Kitty%21.png/revision/latest?cb=20110525032048"));
	    	    		stageTable.show();
	    	    		((Node) event.getSource()).getScene().getWindow().hide(); //zrzucenie okna po przejœciu 
	    	    	} else {
	    	    		Alert alert = new Alert (AlertType.INFORMATION);
	    	    		alert.setContentText("Z³y login lub has³o");
	    	    		alert.setTitle("FATALNY ERROR");
	    	    		alert.setHeaderText("ERROR");
	    	    		alert.showAndWait();
	    	    	}   
	        	}
        	}catch(SQLException exception) {
        		Alert alert = new Alert (AlertType.INFORMATION);
	    		alert.setContentText("Z³y login lub has³o");
	    		alert.setTitle("FATALNY ERROR");
	    		alert.setHeaderText("ERROR");
	    		alert.showAndWait();
        	}
    	}
    public void initialize() {
    	db = new DBConnector();
    } 
    
    @FXML
    void closeAction(MouseEvent event) {
    	System.exit(0);
    }
  
}
