package application.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import application.database.DBConnector;
import application.model.TableDataModel;
import application.model.TableModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class DBEditController {
	
    @FXML
    private TableView<TableDataModel> Table;
	@FXML
    private Label lbl_m;
	@FXML
    private Label lbl_o;
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
    private Button btn_acc;
    @FXML
    private Button btn_bck;

    public DBAdminController dba = new DBAdminController();
    public TableDataModel edit = new TableDataModel();
    public DBConnector db = new DBConnector();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
    	Connection conn = db.Connection();
    	ObservableList<TableDataModel> editL = FXCollections.observableArrayList();
    	ResultSet rs = conn.createStatement().executeQuery("select id_c, townName, centreName, IPadress, VPLSid, Bandwidth, PRAid, PRAline, DDI, BandwidthPrice, PRAPrice from info natural join centre where id_c="+dba.id_export);
	    	if(rs.next()) {
	    		edit = new TableDataModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
	    				rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), 
	    				rs.getString(9), rs.getDouble(10), rs.getDouble(11));
	    	} else {
	    		//...
	    	}
	    lbl_m.setText(edit.getTownName());
	    lbl_o.setText(edit.getCentreName());
    	IPadress.setText(edit.getIPadress());
    	VPLSid.setText(edit.getVPLSid());
    	Bandwidth.setText(edit.getBandwidth());
    	PRAid.setText(edit.getPRAid());
    	PRAline.setText(String.valueOf(edit.getPRAline()));
    	DDI.setText(edit.getDDI());
    	BandwidthPrice.setText(String.valueOf(edit.getBandwidthPrice()));
    	PRAPrice.setText(String.valueOf(edit.getPRAPrice()));
    	}

	@FXML
	void btnAcceptAction(ActionEvent event) throws ClassNotFoundException, SQLException {
		ObservableList<TableDataModel> info;
		Connection conn = db.Connection();
		try {
	    	ObservableList<TableDataModel> editL = FXCollections.observableArrayList();
	    		    	
	    	int id_edit = Table.getSelectionModel().getSelectedItem().getId_c();
	    	String editSql="update info set PRIPadressAid='"+IPadress.getText()+"', VPLSid='"+VPLSid.getText()+"', Bandwidth='"+Bandwidth.getText()+
	    			"', PRAid='"+PRAid.getText()+"', PRAline='"+String.valueOf(PRAline.getText())+"', DDI='"+DDI.getText()+
	    			"', BandwidthPrice='"+String.valueOf(BandwidthPrice.getText())+"', PRAPrice='"+String.valueOf(PRAPrice.getText())+
	    			"' where id_u="+id_edit+";";	    	
	    	PreparedStatement ps = conn.prepareStatement(editSql);
	    	ps.executeUpdate();
	    	btnAcceptAction(event);
    	}catch(Exception exception) {
    		((Node) event.getSource()).getScene().getWindow().hide();
    		}
		}
	
	@FXML
	void btnBackAction(ActionEvent event) {
		((Node) event.getSource()).getScene().getWindow().hide();
		}
	}

