package tw.com.fcb.sample.io.jilldolala25;

import org.postgresql.gss.MakeGSS;

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

        PreparedStatement stmt = conn.prepareStatement("INSERT INTO maskmedical (medical_code,medical_name,medical_address,medical_phone,aldult_count,kids_count,update_date)" +
                "VALUES (?,?,?,?,?,?,?)");
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

    public List<MaskMedical> findALl() throws SQLException {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        String sqlCmd = "Select * from maskmedical";
        ResultSet rs = stmt.executeQuery(sqlCmd);

        List<MaskMedical> maskMedicals = new ArrayList<>();

        MaskMedical maskMedical;
        while(rs.next()){
            maskMedical = new MaskMedical();
            maskMedical.setMedicalcode(rs.getString("medical_code"));
            maskMedical.setMedicalname(rs.getString("medical_name"));
            maskMedical.setMedicaladdress(rs.getString("medical_address"));
            maskMedical.setMedicalphone(rs.getString("medical_phone"));
            maskMedical.setAldultcount(rs.getInt("aldult_count"));
            maskMedical.setKidscount(rs.getInt("kids_count"));
            maskMedical.setDate(rs.getString("update_date"));

            maskMedicals.add(maskMedical);

        }
        return maskMedicals;
    }

    public void insertOneRecord(MaskMedical maskMedical) throws SQLException {

        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement
                ("INSERT INTO maskmedical(medical_code,medical_name,medical_address,medical_phone,aldult_count,kids_count,update_date)" +
                        "VALUES(?,?,?,?,?,?,?) returning id");
        stmt.setString(1,maskMedical.getMedicalcode());
        stmt.setString(2, maskMedical.getMedicalname());
        stmt.setString(3, maskMedical.getMedicaladdress());
        stmt.setString(4, maskMedical.getMedicalphone());
        stmt.setInt(5,maskMedical.getAldultcount());
        stmt.setInt(6,maskMedical.getKidscount());
        stmt.setString(7, maskMedical.getDate());
//        stmt.clearParameters();

        ResultSet rs = stmt.executeQuery();
        if (rs.next()){
            int id = rs.getInt("id");
            maskMedical.setId(Long.valueOf(id));
        }else{
            System.out.println("inset not success");
        }


    }

    public MaskMedical getById(Long id) throws SQLException {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        String sqlCmd = "Select * from maskmedical where id = '" + id + "'";
        ResultSet rs = stmt.executeQuery(sqlCmd);

        MaskMedical maskMedical = new MaskMedical();
        if (rs.next()){
            maskMedical.setMedicalcode(rs.getString("medical_code"));
            maskMedical.setMedicalname(rs.getString("medical_name"));
            maskMedical.setMedicaladdress(rs.getString("medical_address"));
            maskMedical.setMedicalphone(rs.getString("medical_phone"));
            maskMedical.setAldultcount(rs.getInt("aldult_count"));
            maskMedical.setKidscount(rs.getInt("kids_count"));
            maskMedical.setDate(rs.getString("update_date"));
        }
        return maskMedical;
    }
    public void updateByKey(Long id) throws SQLException {
        Connection conn = getConnection();
        String sqlCmd = "update maskmedical set update_date = '2022/02/11' where id = '"+ id + "'";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sqlCmd);
        System.out.println("已將id = " + id + "日期更新為'2022/02/11'");


    }

    public void deleteByKey(Long id) throws SQLException {
        Connection conn = getConnection();
        String sqlCmd = "delete from  maskmedical where id = '"+ id + "'";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sqlCmd);
        System.out.println("已將id = " + id + "刪除");
    }
    public void delete(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        String sqlCmd = "DELETE FROM maskmedical";
        stmt.executeUpdate(sqlCmd);
        stmt.close();

    }

}
