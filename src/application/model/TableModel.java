package application.model;

public class TableModel {
	private int id_c;
	private String townName;
	private String centreName;
	public int getId_c() {
		return id_c;
	}
	public void setId_c(int id_c) {
		this.id_c = id_c;
	}
	public String getTownName() {
		return townName;
	}
	public void setTownName(String townName) {
		this.townName = townName;
	}
	public String getCentreName() {
		return centreName;
	}
	public void setCentreName(String centreName) {
		this.centreName = centreName;
	}
	public TableModel() {}
	public TableModel(int id_c, String townName, String centreName) {
		super();
		this.id_c = id_c;
		this.townName = townName;
		this.centreName = centreName;
	}
}
