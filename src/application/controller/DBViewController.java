package application.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.database.DBConnector;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DBViewController {
    
    @FXML
    private Label IPAdress;
    @FXML
    private Label VPLSid;
    @FXML
    private Label Bandwidth;
    @FXML
    private Label PRAid;
    @FXML
    private Label PRAline;
    @FXML
    private Label DDI;
    @FXML
    private Label BandwidthPrice;
    @FXML
    private Label PRAPrice;
    @FXML
    private Label lbl_m;
    @FXML
    private Label lbl_o;
    @FXML
    private Button btn_quit;
    
    // wyœwietlenie informacji dotycz¹cych wybranego wczeœniej centrum
    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
    	DBAdminController dba = new DBAdminController();
    	TableDataModel td = new TableDataModel();
    	//System.out.println(dba.id_export);
    	DBConnector db = new DBConnector();
    	Connection conn = db.Connection();
    	ObservableList<TableDataModel> viewL = FXCollections.observableArrayList();
    	ResultSet rs = conn.createStatement().executeQuery("select id_c, townName, centreName, IPadress, VPLSid, Bandwidth, PRAid, PRAline, DDI, BandwidthPrice, PRAPrice from info natural join centre where id_c="+dba.id_export);
	    	if(rs.next()) {
	    		td = new TableDataModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
	    				rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), 
	    				rs.getString(9), rs.getDouble(10), rs.getDouble(11));
	    	} else {
	    		//...
	    	}
		lbl_m.setText(td.getTownName());
		lbl_o.setText(td.getCentreName());
    	IPAdress.setText(td.getIPadress());
    	VPLSid.setText(td.getVPLSid());
    	Bandwidth.setText(td.getBandwidth());
    	PRAid.setText(td.getPRAid());
    	PRAline.setText(String.valueOf(td.getPRAline()));
    	DDI.setText(td.getDDI());
    	BandwidthPrice.setText(String.valueOf(td.getBandwidthPrice()));
    	PRAPrice.setText(String.valueOf(td.getPRAPrice()));
    }
    
    @FXML
    void btnQuitAction(ActionEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide(); //zrzucenie okna po przejœciu 
    }
}
