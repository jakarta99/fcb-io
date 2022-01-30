package tw.com.fcb.sample.io.sinjen;

public class StoreRow {

	private String zone;		//商圈名稱
	private String store;		//特色商家
	private String storeTel;	//店家電話
	private String storeAdd;	//店家地址
	private String storeProduct;//店家商品
	private String twd97X;		//twd97緯度
	private String twd97Y;		//twd97經度
	private String wgs84aX;		//wgs84a緯度
	private String wgs84aY;		//wgs84a經度
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getStoreTel() {
		return storeTel;
	}
	public void setStoreTel(String storeTel) {
		this.storeTel = storeTel;
	}
	public String getStoreAdd() {
		return storeAdd;
	}
	public void setStoreAdd(String storeAdd) {
		this.storeAdd = storeAdd;
	}
	public String getStoreProduct() {
		return storeProduct;
	}
	public void setStoreProduct(String storeProduct) {
		this.storeProduct = storeProduct;
	}
	public String getTwd97X() {
		return twd97X;
	}
	public void setTwd97X(String twd97x) {
		twd97X = twd97x;
	}
	public String getTwd97Y() {
		return twd97Y;
	}
	public void setTwd97Y(String twd97y) {
		twd97Y = twd97y;
	}
	public String getWgs84aX() {
		return wgs84aX;
	}
	public void setWgs84aX(String wgs84aX) {
		this.wgs84aX = wgs84aX;
	}
	public String getWgs84aY() {
		return wgs84aY;
	}
	public void setWgs84aY(String wgs84aY) {
		this.wgs84aY = wgs84aY;
	}
	
	@Override
	public String toString() {
		return "StoreRow [zone=" + zone + ", store=" + store + ", storeTel=" + storeTel + ", storeAdd=" + storeAdd
				+ ", storeProduct=" + storeProduct + ", twd97X=" + twd97X + ", twd97Y=" + twd97Y + ", wgs84aX="
				+ wgs84aX + ", wgs84aY=" + wgs84aY + "]";
	}
	
}
