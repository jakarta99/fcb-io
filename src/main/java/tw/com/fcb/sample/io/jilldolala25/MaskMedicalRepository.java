package tw.com.fcb.sample.io.jilldolala25;

import java.sql.*;

public class MaskMedicalRepository {

    public Connection getConnection() throws SQLException {
        String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
        String username = "postgres";
        String password = "postgres";
        return DriverManager.getConnection(dbUrl,username,password);

    }

    public MaskMedical getByMedicalCode(String code,Connection conn) throws SQLException {

        Statement stmt = conn.createStatement();

        String sqlCmd = "Select * from maskmedical where medicalcode = '" + code + "'";
        ResultSet rs = stmt.executeQuery(sqlCmd);
        int totcount = 0;
        MaskMedical maskMedical = new MaskMedical();
        while (rs.next()){
            maskMedical.setMedicalcode(rs.getString("medical_code"));
            maskMedical.setMedicalname(rs.getString("medical_name"));
            maskMedical.setMedicaladdress(rs.getString("medical_address"));
            maskMedical.setMedicalphone(rs.getString("medical_phone"));
            maskMedical.setAldultcount(rs.getString("aldult_count"));
            maskMedical.setKidscount(rs.getString("kids_count"));
            maskMedical.setDate(rs.getString("update_date"));
            totcount ++;
        }

        return maskMedical;
    }

    public void insert(MaskMedical maskMedical, Connection conn) throws SQLException {


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



    }

    public int count(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        String sqlCmd = "Select count(*) from maskmedical";
        ResultSet rs = stmt.executeQuery(sqlCmd);
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }
    public void delete(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        String sqlCmd = "DELETE FROM maskmedical";
        stmt.executeUpdate(sqlCmd);
        stmt.close();

    }

}
