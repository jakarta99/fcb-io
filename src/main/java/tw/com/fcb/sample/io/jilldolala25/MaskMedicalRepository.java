package tw.com.fcb.sample.io.jilldolala25;

import org.apache.commons.collections.map.StaticBucketMap;

import java.sql.*;

public class MaskMedicalRepository {

    public Connection getConnection() throws SQLException {
        String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
        String username = "postgres";
        String password = "postgres";
        return DriverManager.getConnection(dbUrl,username,password);

    }

    public MaskMedical getByMedicalCode(String code) throws SQLException {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();

        String sqlCmd = "Select * from maskmedical where medicalcode = '" + code + "'";
        ResultSet rs = stmt.executeQuery(sqlCmd);
        int totcount = 0;
        MaskMedical maskMedical = new MaskMedical();
        while (rs.next()){
            maskMedical.setMedicalcode(rs.getString("medicalcode"));
            maskMedical.setMedicalname(rs.getString("medicalname"));
            maskMedical.setMedicaladdress(rs.getString("medicaladdress"));
            maskMedical.setMedicalphone(rs.getString("medicalphone"));
            maskMedical.setAldultcount(rs.getString("aldultcount"));
            maskMedical.setKidscount(rs.getString("kidscount"));
            maskMedical.setDate(rs.getString("updatedate"));
            totcount ++;
        }

        return maskMedical;
    }

    public int insert(MaskMedical maskMedical,Connection conn) throws SQLException {


        Statement stmt = conn.createStatement();
        String sqlCmd = "INSERT INTO maskmedical values ('"
                + maskMedical.getMedicalcode()+ "',"
                + "'" + maskMedical.getMedicalname()+ "',"
                + "'" + maskMedical.getMedicaladdress()+ "',"
                + "'" + maskMedical.getMedicalphone() + "',"
                + "'" + maskMedical.getAldultcount() + "',"
                + "'" + maskMedical.getKidscount() + "',"
                + "'" + maskMedical.getDate()+ "')";
        stmt.executeUpdate(sqlCmd);
        stmt.close();

        return  -1;

    }
    public void delete(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        String sqlCmd = "DELETE FROM maskmedical";
        stmt.executeUpdate(sqlCmd);
        stmt.close();

    }

}
