package tw.com.fcb.sample.io.jilldolala25;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaskMedicalRepository {

    public Connection getConnection() throws SQLException {
        String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
        String username = "postgres";
        String password = "postgres";
        return DriverManager.getConnection(dbUrl,username,password);

    }

    public List<MaskMedical> getByMedicalCode(String code, Connection conn) throws SQLException {

        Statement stmt = conn.createStatement();

        String sqlCmd = "Select * from maskmedical  where substring (medical_code,1,3) = '" + code + "'";
        ResultSet rs = stmt.executeQuery(sqlCmd);


        List<MaskMedical> resultSet = new ArrayList<>();
        while (rs.next()){
            MaskMedical maskMedical = new MaskMedical();
            maskMedical.setMedicalcode(rs.getString("medical_code"));
            maskMedical.setMedicalname(rs.getString("medical_name"));
            maskMedical.setMedicaladdress(rs.getString("medical_address"));
            maskMedical.setMedicalphone(rs.getString("medical_phone"));
            maskMedical.setAldultcount(rs.getInt("aldult_count"));
            maskMedical.setKidscount(rs.getInt("kids_count"));
            maskMedical.setDate(rs.getString("update_date"));
            resultSet.add(maskMedical);
        }
        rs.close();
        stmt.close();
        System.out.println("resultSet = " + resultSet);
        return resultSet;
    }

    public void insert(MaskMedical maskMedical, Connection conn) throws SQLException {


//        Statement stmt = conn.createStatement();
//        String sqlCmd = "INSERT INTO maskmedical values ('"
//                + maskMedical.getMedicalcode()+ "',"
//                + "'" + maskMedical.getMedicalname()+ "',"
//                + "'" + maskMedical.getMedicaladdress()+ "',"
//                + "'" + maskMedical.getMedicalphone() + "',"
//                + "'" + maskMedical.getAldultcount() + "',"
//                + "'" + maskMedical.getKidscount() + "',"
//                + "'" + maskMedical.getDate()+ "')";
//
//        stmt.executeUpdate(sqlCmd);
//
//        stmt.close();

        //嘗試使用PreparedStatment

        PreparedStatement stmt = conn.prepareStatement("INSERT INTO maskmedical VALUES (?,?,?,?,?,?,?)");
        stmt.setString(1,maskMedical.getMedicalcode());
        stmt.setString(2,maskMedical.getMedicalname());
        stmt.setString(3,maskMedical.getMedicaladdress());
        stmt.setString(4,maskMedical.getMedicalphone());
        stmt.setInt(5,maskMedical.getAldultcount());
        stmt.setInt(6,maskMedical.getKidscount());
        stmt.setString(7, maskMedical.getDate());
        stmt.executeUpdate();
        stmt.clearParameters();
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
