package application.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.database.DBConnector;
import application.model.TableModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DBGuestController {

    @FXML
    private TableView<TableModel> Table;
    @FXML
    private TableColumn<TableModel, Integer> col_id_o;
    @FXML
    private TableColumn<TableModel, String> col_nameO;
    @FXML
    private TableColumn<TableModel, String> col_town;
        
    
    	// widok dla goœcia ograniczony do id, nazwy centrum i miasta
    @FXML
    void initialize() throws ClassNotFoundException, SQLException {
    	
    	DBConnector db = new DBConnector();
    	TableModel tm = new TableModel();
    	Connection conn = db.Connection();
    	ObservableList<TableModel> centre = FXCollections.observableArrayList();

    	ResultSet rs = conn.createStatement().executeQuery("select id_c, townName, centreName from centre");
    	while(rs.next()) {
    		centre.add(new TableModel(rs.getInt(1),rs.getString(2),rs.getString(3)));
    	}
    	col_id_o.setCellValueFactory(new PropertyValueFactory<TableModel,Integer>("id_c"));
    	col_town.setCellValueFactory(new PropertyValueFactory<TableModel,String>("townName"));
    	col_nameO.setCellValueFactory(new PropertyValueFactory<TableModel,String>("centreName"));
    	Table.setItems(null);
    	Table.setItems(centre);
    	
	}
}
