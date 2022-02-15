package tw.com.fcb.sample.io.gary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class FruitRepository {
	
	HikariDataSource ds;
	
	public FruitRepository() {
		
		LocalDateTime startTime = LocalDateTime.now();
		
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:postgresql://localhost:5432/testdb");
		config.setUsername("postgres");
		config.setPassword("postgres");
		config.addDataSourceProperty("minimumIdle", "10");
		config.addDataSourceProperty("maximumPoolSize", "30");
		
		LocalDateTime stopTime = LocalDateTime.now();
		Long diff = ChronoUnit.MILLIS.between(startTime, stopTime);
		System.out.println("total "+diff+" msec");
		
		this.ds = new HikariDataSource(config);
	}
	
	
	private Connection getConnection() throws SQLException {
		return ds.getConnection();
//		
//		
//		//Class.forName("org.postgresql.Driver");
//		String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
//		String username = "postgres";
//		String password = "postgres";
//		return DriverManager.getConnection(dbUrl, username, password);
			
	}
	
	public List<Fruit> findAll() throws SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		
		String sqlCmd = "select * from fruit";
		ResultSet rs = stmt.executeQuery(sqlCmd);
		
		List<Fruit> fruits = new ArrayList<Fruit>();

		Fruit fruit;
		while(rs.next()) {
			fruit = new Fruit();
			fruit.setCode(rs.getString("code"));
			fruit.setName(rs.getString("name"));
			fruit.setPrice(rs.getInt("price"));
			
			fruits.add(fruit);
		}
		
		return fruits;
	}
	
	
	public Fruit getById(Long id) throws SQLException {
		
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		
		String sqlCmd = "select * from fruit where id = '"+id+"'";
		ResultSet rs = stmt.executeQuery(sqlCmd);
		
		Fruit fruit = new Fruit();
		if(rs.next()) {
			fruit.setCode(rs.getString("code"));
			fruit.setName(rs.getString("name"));
			fruit.setPrice(rs.getInt("price"));
		}
		
		return fruit;
	}
	
	
	public void insert(Fruit fruit) throws SQLException {
		
		Connection conn = getConnection();
		String sqlCmd = "INSERT INTO FRUIT(code, name, price) VALUES (?,?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sqlCmd, Statement.RETURN_GENERATED_KEYS);
		
		pstmt.setString(1, fruit.getCode());
		pstmt.setString(2, fruit.getCode());
		pstmt.setInt(3, fruit.getPrice());
		
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if(rs.next()) {
			int id = rs.getInt("id");
			fruit.setId(Long.valueOf(id));
		}

		rs.close();
		pstmt.close();
		conn.close();
		
	}
	
	
	public void update(Fruit fruit) throws SQLException {
		
		
	}
	
	public void delete(Long id) throws SQLException {
		
	}
	
	
	
	
	
	

}
