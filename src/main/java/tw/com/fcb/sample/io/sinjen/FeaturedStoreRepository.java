package tw.com.fcb.sample.io.sinjen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FeaturedStoreRepository {
	
	private Connection getConnection() {
		String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
		String username = "postgres";
		String password = "postgres";
		
        try{
        	Class.forName("org.postgresql.Driver");
            try{
                Connection conn = DriverManager.getConnection(dbUrl, username, password);
                if (conn != null){
                    return conn;   
                }
            }catch(SQLException sqlEx){
            	sqlEx.printStackTrace();
            }
        }catch(ClassNotFoundException cnfEx){
        	cnfEx.printStackTrace();
        }
        return null;
	}
	
	// 讀取資料庫featured_store
	public List<StoreRow> getDBQueryResult() {
		List<StoreRow> arrayList = new ArrayList<StoreRow>();
		Connection conn = getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			String sqlCmd = "select * from featured_store";
			rs = stmt.executeQuery(sqlCmd);
			while (rs.next()) {
				StoreRow row = new StoreRow();
				row.setZone(rs.getString("zone"));
				row.setStore(rs.getString("store"));
				row.setStoreTel(rs.getString("store_tel"));
				row.setStoreAdd(rs.getString("store_add"));
				row.setStoreProduct(rs.getString("store_product"));
				row.setTwd97X(rs.getString("twd97X"));
				row.setTwd97Y(rs.getString("twd97Y"));
				row.setWgs84aX(rs.getString("wgs84aX"));
				row.setWgs84aY(rs.getString("wgs84aY"));
				//System.out.println(row);
				arrayList.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeResultSet(rs);
		closeStatment(stmt);
		closeConnection(conn);
		System.out.println("讀取DB:featured_store完成，共" + arrayList.size() + "筆資料");
		return arrayList;
	}
	
	// 寫入資料庫featured_store
	public void insertDB_featured_store(List<StoreRow> listResult) {
		Connection conn = getConnection();
		Statement stmt = null;
		StoreRow row = null;
		int size = listResult.size();

		try {
			stmt = conn.createStatement();
			for (int i = 0; i < size; i++) {
				row = (StoreRow) listResult.get(i);
				String sqlCmd = "INSERT INTO featured_store values ("
						+ "'" + row.getZone() + "', " 
						+ "'" + row.getStore() + "', " 
						+ "'" + row.getStoreTel() + "', " 
						+ "'" + row.getStoreAdd() + "', " 
						+ "'" + row.getStoreProduct() + "', "
						+ "'" + row.getTwd97X() + "', " 
						+ "'" + row.getTwd97Y() + "', " 
						+ "'" + row.getWgs84aX() + "', "
						+ "'" + row.getWgs84aY() + "'"
						+ ")";
				stmt.executeUpdate(sqlCmd);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeStatment(stmt);
		closeConnection(conn);
		System.out.println("寫入DB:featured_store完成，共" + size + "筆資料");
	}

	// Close ResultSet
	public static void closeResultSet(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	// Close Statement
	public static void closeStatment(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	// Close Connection
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
    }
}
