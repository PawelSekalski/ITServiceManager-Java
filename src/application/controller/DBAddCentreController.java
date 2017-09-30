package application.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import application.database.DBConnector;
import application.model.TableAddDetailsModel;
import application.model.TableDataModel;
import application.model.TableModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DBAddCentreController {
	
    @FXML
    private TextField townName;
    @FXML
    private TextField centreName; //ind
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_bck;
    
    DBConnector db = new DBConnector();
    TableModel addC = new TableModel();
    public static int id_export;
    
    // dodanie nowego centrum, przez które przechodzi siê do okna, w którym wprowadza siê informacje dotycz¹ce nowego centrum 
    @FXML
    void btnAddAction(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
    	try {    		
    	Connection conn = db.Connection();
    	ObservableList<TableModel> acm = FXCollections.observableArrayList();
    	//----------------
    		if (townName.getText().equals("") || centreName.getText().equals("")) {
    			Alert a = new Alert(AlertType.INFORMATION);
	    		a.setContentText("Chyba czegoœ tu brakuje");
	    		a.setTitle("FATALNY ERROR");
	    		a.setHeaderText("WSZYSCY ZGINIEMY");
	    		a.showAndWait();
    		} else {	
    			String sqlTown="insert into centre (townName, centreName) values ('"+townName.getText()+"','"+centreName.getText()+"');";
    			PreparedStatement ps = conn.prepareStatement(sqlTown);
    			ps.executeUpdate();
    			btnAddAction(event);   		
    		}
    	} catch (Exception exception) {
    		Stage details = new Stage();
    		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/application/view/DBAddDetailsView.fxml"));
    		Scene sceneD = new Scene(parent);
    		details.setScene(sceneD);
    		details.setTitle("HELL KITTY!");
    		details.getIcons().add(new Image("https://vignette.wikia.nocookie.net/awesomeanimeandmanga/images/d/d4/Hello_Kitty%21.png/revision/latest?cb=20110525032048"));
    		details.show();
    		((Node) event.getSource()).getScene().getWindow().hide();
    	}
    }
    
    @FXML
    void btnBackAction(ActionEvent event) {
    	((Node) event.getSource()).getScene().getWindow().hide(); //zrzucenie okna po przejœciu 
    }

}
