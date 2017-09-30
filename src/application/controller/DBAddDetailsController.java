package application.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DBAddDetailsController {

    @FXML
    private TextField IPadress;
    @FXML
    private TextField VPLSid;
    @FXML
    private TextField Bandwidth;
    @FXML
    private TextField PRAid;
    @FXML
    private TextField PRAline;
    @FXML
    private TextField DDI;
    @FXML
    private TextField BandwidthPrice;
    @FXML
    private TextField PRAPrice;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_bck;

    // okno, w któym wprowadza siê dodatkowe informacje o nowym centrum
    @FXML
    void btnAddAction(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
    	try {
    		    		
    		DBAdminController dbac = new DBAdminController();
    		TableDataModel addC = new TableDataModel();
	    	DBConnector db = new DBConnector();
	    	Connection conn = db.Connection();
	    	ObservableList<TableDataModel> acm = FXCollections.observableArrayList();
			String sqlInfo="insert into info (IPadress, VPLSid, bandwidth, PRAid, PRAline, DDI, BandwidthPrice, PRAPrice) values ('"+IPadress.getText()+
					"','"+VPLSid.getText()+"','"+Bandwidth.getText()+"','"+PRAid.getText()+"','"+String.valueOf(PRAline.getText())+"','"+DDI.getText()+
					"','"+String.valueOf(BandwidthPrice.getText())+"','"+String.valueOf(PRAPrice.getText())+"' where id_c = '"+dbac.id_export+"');";
			PreparedStatement ps = conn.prepareStatement(sqlInfo);
			ps.executeUpdate();
			
    	} catch (Exception ex) {
    		((Node) event.getSource()).getScene().getWindow().hide();
    	}
    }
    
    @FXML
    void btnBackAction(ActionEvent event) {
		((Node) event.getSource()).getScene().getWindow().hide();
    }
}
