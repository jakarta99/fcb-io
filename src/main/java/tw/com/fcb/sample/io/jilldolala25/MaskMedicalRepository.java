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

    

    

    public List<MaskMedical> findALl() throws SQLException {
        Connection conn = getConnection();
        String sqlCmd = "Select * from maskmedical";
        PreparedStatement stmt = conn.prepareStatement(sqlCmd);
        ResultSet rs = stmt.executeQuery();

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
        rs.close();
        stmt.close();
        conn.close();
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


        ResultSet rs = stmt.executeQuery();
        if (rs.next()){
            int id = rs.getInt("id");
            maskMedical.setId(Long.valueOf(id));
        }else{
            System.out.println("inset is not success");
        }
        rs.close();
        stmt.clearParameters();
    }

    public MaskMedical getById(Long id) throws SQLException {

        Connection conn = getConnection();
        String sqlCmd = "Select * from maskmedical where id = ? ";
        PreparedStatement stmt = conn.prepareStatement(sqlCmd);
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();

        MaskMedical maskMedical = new MaskMedical();
        if (rs.next()) {
            maskMedical.setId(id);
            maskMedical.setMedicalcode(rs.getString("medical_code"));
            maskMedical.setMedicalname(rs.getString("medical_name"));
            maskMedical.setMedicaladdress(rs.getString("medical_address"));
            maskMedical.setMedicalphone(rs.getString("medical_phone"));
            maskMedical.setAldultcount(rs.getInt("aldult_count"));
            maskMedical.setKidscount(rs.getInt("kids_count"));
            maskMedical.setDate(rs.getString("update_date"));
        }
        rs.close();
        stmt.close();
        conn.close();
        return maskMedical;
    }
    public void updateDateByKey(Long id, String formatDate) throws SQLException {

        Connection conn = getConnection();

        String sqlCmd = "update maskmedical set update_date = '" + formatDate +"' where id = " + id;
        PreparedStatement stmt = conn.prepareStatement(sqlCmd);

        stmt.executeUpdate();
        System.out.println("已將id = " + id + " 日期更新為" +formatDate);


    }

    public void deleteByKey(Long id) throws SQLException {

        Connection conn = getConnection();
        String sqlCmd = "delete from  maskmedical where id = ? ";
        PreparedStatement stmt = conn.prepareStatement(sqlCmd);
        stmt.setLong(1,id);
        stmt.executeUpdate();


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
    public void deleteAll(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        String sqlCmd = "DELETE FROM maskmedical";
        stmt.executeUpdate(sqlCmd);
        stmt.close();

    }


    public List<MaskMedical> getByMedicalCode(String code) throws SQLException {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();

        String sqlCmd = "Select * from maskmedical  where  medical_code like '" + code + "%'" ;

//        stmt.setString(1,code);
        ResultSet rs = stmt.executeQuery(sqlCmd);


        List<MaskMedical> resultSet = new ArrayList<>();
        while (rs.next()){
            MaskMedical maskMedical = new MaskMedical();
            maskMedical.setId(rs.getLong("id"));
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

        return resultSet;
    }

    public void insert(MaskMedical maskMedical, Connection conn) throws SQLException {

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


}
