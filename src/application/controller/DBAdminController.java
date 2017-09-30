package application.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.database.DBConnector;
import application.model.TableAddDetailsModel;
import application.model.TableDataModel;
import application.model.TableModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DBAdminController {

    @FXML
    private TableView<TableModel> Table;
    @FXML
    private TableColumn<TableModel, Integer> col_id_o;
    @FXML
    private TableColumn<TableModel, String> col_nameO;
    @FXML
    private TableColumn<TableModel, String> col_town;
    @FXML
    private Button btn_show;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_edit;
    @FXML
    private Button btn_detail;
    @FXML
    private Button btn_quit;

    public DBConnector db;
    public ObservableList<TableModel> centre;
    public ObservableList<TableAddDetailsModel> info;
    public static int id_export;
    
    // wypisanie listy pozycji z bazy sql 
    @FXML
    void btnSelectAction(ActionEvent event) throws ClassNotFoundException, SQLException {
    	Connection conn = db.Connection();
    	centre = FXCollections.observableArrayList();
    	ResultSet rs = conn.createStatement().executeQuery("select * from centre");
    	while(rs.next()) {
    		centre.add(new TableModel(rs.getInt(1),rs.getString(2),rs.getString(3)));
    	}
    	col_id_o.setCellValueFactory(new PropertyValueFactory<TableModel,Integer>("id_c"));
    	col_town.setCellValueFactory(new PropertyValueFactory<TableModel,String>("townName"));
    	col_nameO.setCellValueFactory(new PropertyValueFactory<TableModel,String>("centreName"));
    	Table.setItems(null);
    	Table.setItems(centre);
    }  
    
    // dodanie nowego centrum
    @FXML
    void btnAddAction(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
    	Stage stage = new Stage();
    	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/DBAddCentreView.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Dodawanie nowego oddzia³u");
		stage.getIcons().add(new Image("https://vignette.wikia.nocookie.net/awesomeanimeandmanga/images/d/d4/Hello_Kitty%21.png/revision/latest?cb=20110525032048"));
		stage.show();
    }
    
    // edytowanie informacji dotycz¹cych wybrango centrum
    @FXML
    void btnEditAction(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
    	try {
	    	int id = Table.getSelectionModel().getSelectedItem().getId_c();
	    	id_export = id;
	    	//System.out.println(id_export);
	    	Stage stage = new Stage();
	    	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/DBEditView.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("Edycja informacji o oddziale");
			stage.getIcons().add(new Image("https://vignette.wikia.nocookie.net/awesomeanimeandmanga/images/d/d4/Hello_Kitty%21.png/revision/latest?cb=20110525032048"));
			stage.show();
			// b³¹d nie wybrania ¿adnego centrum
    	} catch(NullPointerException exception) {
    		Alert alert = new Alert (AlertType.INFORMATION);
    		alert.setContentText("Jeœli chcesz edytowaæ informacje dotycz¹ce konkretnego centrum, musisz wpierw to konkretne centrum zaznaczyæ");
    		alert.setHeaderText("Nie wybra³eœ ¿adnego centrum do edycji");
    		alert.setTitle("B£¥D");
    		alert.showAndWait();
    	}
    }
    
    // wyœwietlenie informacji dotycz¹cych wybranego centrum
    @FXML
    void btnDetailAction(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
    	try {
	    	int id = Table.getSelectionModel().getSelectedItem().getId_c();
	    	id_export = id;
	    	Stage stage = new Stage();
	    	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/DBDataView.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("Informacje o oddziale");
			stage.getIcons().add(new Image("https://vignette.wikia.nocookie.net/awesomeanimeandmanga/images/d/d4/Hello_Kitty%21.png/revision/latest?cb=20110525032048"));
			stage.show();
			// b³¹d nie wybrania ¿adnego centrum
    	} catch(NullPointerException exception) {
    		Alert alert = new Alert (AlertType.INFORMATION);
    		alert.setContentText("Jeœli chcesz zobaczyæ informacje dotycz¹ce konkretnego centrum, musisz wpierw to konkretne centrum zaznaczyæ");
    		alert.setHeaderText("Nie wybra³eœ ¿adnego centrum do wgl¹du");
    		alert.setTitle("B£¥D");
    		alert.showAndWait();
    	}
    }

    // usuniêcie wybranego centrum
    @FXML
    void btnDeleteAction(ActionEvent event) throws ClassNotFoundException, SQLException {	    	
    	Connection conn = db.Connection();
    	try {
	    	int id_delete = Table.getSelectionModel().getSelectedItem().getId_c();
	    	//delete from table info
	    	String delSQL="delete from info where id_c="+id_delete+";";
	    	PreparedStatement ps = conn.prepareStatement(delSQL);
	    	ps.executeUpdate();
	    	//delete from table centre
	    	String delSQL2="delete from centre where id_c="+id_delete+";";
	    	PreparedStatement ps2 = conn.prepareStatement(delSQL2);
	    	ps2.executeUpdate();
	    	btnSelectAction(event);
    	} catch(NullPointerException exeption) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setContentText("Jeœli chcesz usun¹æ konkretne centrum, musisz wpierw to konkretne centrum zaznaczyæ");
    		alert.setTitle("B£¥D");
    		alert.setHeaderText("Nie wybra³eœ ¿adnego centrum do usuniêcia");
    		alert.showAndWait();
    		}
    	}
    
    @FXML
    void initialize() {
    	db = new DBConnector();
    }   
}
