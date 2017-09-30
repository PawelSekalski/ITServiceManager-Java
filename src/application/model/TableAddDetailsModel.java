package application.model;

public class TableAddDetailsModel {
	
	private int id_c;
	private String IPadress;
    private String VPLSid;
    private String Bandwidth;
    private String PRAid;
    private int PRAline;
    private String DDI;
    private double BandwidthPrice;
    private double PRAPrice;
    
    public int getId_c() {
		return id_c;
	}
	public void setId_c(int id_c) {
		this.id_c = id_c;
	}
	public String getIPadress() {
		return IPadress;
	}
	public void setIPadress(String iPadress) {
		this.IPadress = iPadress;
	}
	public String getVPLSid() {
		return VPLSid;
	}
	public void setVPLSid(String vPLSid) {
		this.VPLSid = vPLSid;
	}
	public String getBandwidth() {
		return Bandwidth;
	}
	public void setBandwidth(String bandwidth) {
		this.Bandwidth = bandwidth;
	}
	public String getPRAid() {
		return PRAid;
	}
	public void setPRAid(String pRAid) {
		this.PRAid = pRAid;
	}
	public int getPRAline() {
		return PRAline;
	}
	public void setPRAline(int pRAline) {
		this.PRAline = pRAline;
	}
	public String getDDI() {
		return DDI;
	}
	public void setDDI(String dDI) {
		this.DDI = dDI;
	}
	public double getBandwidthPrice() {
		return BandwidthPrice;
	}
	public void setBandwidthPrice(double bandwidthPrice) {
		this.BandwidthPrice = bandwidthPrice;
	}
	public double getPRAPrice() {
		return PRAPrice;
	}
	public void setPRAPrice(double pRAPrice) {
		this.PRAPrice = pRAPrice;
	}
	
	public TableAddDetailsModel() {}
	public TableAddDetailsModel(int id_c, String iPadress, String vPLSid, String bandwidth, String pRAid, int pRAline, String dDI,
			double bandwidthPrice, double pRAPrice) {
		super();
		this.id_c = id_c;
		this.IPadress = iPadress;
		this.VPLSid = vPLSid;
		this.Bandwidth = bandwidth;
		this.PRAid = pRAid;
		this.PRAline = pRAline;
		this.DDI = dDI;
		this.BandwidthPrice = bandwidthPrice;
		this.PRAPrice = pRAPrice;
	} 
}
