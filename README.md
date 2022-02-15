# Course 4 Java IO 與 Exception Handling

## Java I/O
I/O, 就是 Input 與 Output, 如果資料源的對象是檔案, 我們讀取檔案就是 Input, 我們寫出為檔案就是 Output.
在 Java 之中, 有區分 Stream 與 Reader 來控制, 差別是 Stream 以 byte 取值, Reader 以 word 取值.
無論是 Stream 或是 Reader, 我們都是採用 read(), 以及判斷是否為 -1 來看看是否已經是 EOF (End of File).


```java
FileInputStream fis = new FileInputStream();
int ch = 0;
while( (ch = fis.read()) != -1) {
  System.out.println((char)ch);
}
```

```java
FileReader fr = new FileReader();
int ch = 0;
while( (ch = fr.read()) != -1) {
  System.out.println((char)ch);
}
```
當 一個 word 就是一個 byte 時, 不會有太大的問題，　
如果中文字是 UTF-8 編碼時, 一個 word 就是 3bytes,
使用 Reader 會採用你電腦預設的 encoding 取值,

所以, 我們通常會是用 Reader 處理文字檔. 使用 Stream 處理 Binary 檔案。

## ReadLine
通常, 我們得到一份檔案要進行處理, 每一行代表的是該物件各屬性的相關數值, 所以我們會想要每次讀取一行資料.
這時候, 就是用 BufferedReader 緩衝處理, java 會根據 contructor 中的 Reader 將資料讀取放在緩衝區進行處理, 接著我們就可以用 readLine() 來取值.

```java
BufferedReader br = new BufferedReader(new FileReader(file)); // 採用 FileReader 放到緩衝區
String lineData;
while( (lineDate = br.readLine()) != null ) {
   System.out.println(lineData);
}
```
當我們有了一筆資料是 String 的 type, 若恰巧是以 "," 為區隔的 CSV 檔案, 就可以透過 String[] String.split(lineData) 進行切割.
接著, 就把切割出來的數值放入到目標物件之中 new SomeObject().


## Exception
使用 Java I/O 時, 就會遇到 IOException

如果在最外層(static void main) 的 App 或 Web Controller 之中, 我們就不會再把 Exception 繼續往外丟了, 
就一定會 try catch 相關的 Exception, 去處理訊息.

```java
try {
   // 做一些事情
   // 正常完成, 就不會到 Exception 區塊
} catch (Exception ex) {
   // 當例外發生時
} finally {
  // 事情結束時 **一定會來此**
}

```

如果我們在 Service method 之中要丟出一些 Exception(有意義的) 讓前端知道, 是在 method 後面加上 throws

```java
public void insertData(SomeData data) throws DataDuplicatedException {
   //.....
   if( dao.findByKey(data.getKey()) != null ) {
      throw new DataDuplicatedException();
   }
   
   
}
```

## IOUtils
Apache commons-io 之中, 有 IOUtils 與 FileUtils 。可以簡化許多 I/O 存取的. 可以多加利用.
https://commons.apache.org/proper/commons-io/

```xml
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.11.0</version>
</dependency>
```

## Reference

Java 技術手冊 Chapter 10 I/O 與 Chapter 14 NIO/NIO2

## Homework
到 https://data.gov.tw/ 開放政府平台抓下一個 csv 檔案, 將之轉為 List<物件> .

建立一個 Service 與對應的 Type Class.

```java
public List<SexRatio> loadFromFile() throws IOException { 
  List<SexRatio> result = new ArrayList<SexRatio>();

  // 讀檔
  
  // 迴圈讀一行資料
  // split 切割
  // 設值
  // 放到 List 之中

  return result;
}
```

# Course 5 Database Connection
## 安裝 Database Server
Relational Opensource Database Server 有幾個選擇
* MySQL https://www.mysql.com/ 吉祥物 : 海豚
* PostgreSQL https://www.postgresql.org/ 吉祥物 : 大象
* MariaDB https://mariadb.org/ 吉祥物 : 海獅

可以挑選其中之一, 作為練習之用. 這次我們安裝 postgresql 作為範例資料庫, 在 windows 安裝時, locale 請選擇 default.
先透過 pgAdmin 建立一個 testdb database.

## 連結資料庫
當我們要連結到一台資料庫, 我們必須要知道幾個資訊
* 連線資料庫的種類與版本
* 連線的 ip 與 Port
* 連線的帳號與密碼

根據資料庫的種類與版本, 我們專案必須要設定相關的 JDBC Driver (基本上就是一個 jar 檔案) 在 classpath 之中,
可以直接在 maven pom.xml 檔案之中, 直接設定 dependency 即可.
* MySQL https://mvnrepository.com/artifact/mysql/mysql-connector-java
* Postgresql https://mvnrepository.com/artifact/org.postgresql/postgresql
* MariaDB https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client

接著, 要知道相關的 JDBC URL 的設定方式,
* MySQL jdbc:mysql://localhost:3306/testdb
* Postgresql jdbc:postgresql://localhost:5432/testdb
* MariaDB jdbc:mariadb://localhost:3306/testdb

在 Java 之中, 只需要透過 DriverManager 就可以建立連線

````java
String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
String username = "postgres";
String password = "postgres";
Connection conn = DriverManager.getConnection(dbUrl, username, password);
````
## 建立 Statement
透過 Connection 可以建立 Statement, 而 Statement 就是執行 SQL 的物件
````java
Statement stmt = conn.createStatement();
String sqlCmd = "SELECT * FROM FRUIT";
ResultSet rs = stmt.executeQuery(sqlCmd);

````
Statement 執行的方式主要有兩種
* executeQuery
* executeUpdate
透過 executeQuery 是查詢用, 其他 insert/update/delete 是 executeUpdate,
這兩種的 return 物件也有點不同, executeQuery 回傳是 ResultSet 結果集合, executeUpdate 則是 int 就是影響的筆數.


## 取值
通常我們會取得 ResultSet 之後, 進行資料轉換
````java
Fruit fruit = new Fruit();
while(rs.next()) {
   fruit.setCode(rs.getString("CODE"));
   fruit.setName(rs.getString("NAME"));
   fruit.setPrice(rs.getInt("PRICE"));
}
````

## Exception Handling
我們會盡可能在 Repository 往外部丟出 Exception, 讓 App 去 try / catch .
所以 methods 之後會 throws SQLException.

## Reference
請參照 Java 技術手冊 第16章


# Course 6 Repository Standard Methods
## 建立物件都具有 ID
````java
public class Model {
   Long id;
   
   // getters and setters
   
}
````
  所以我們在建立 table 時, 在 postgresql 之中可以用 serial 的類型來建構 id
  ````sql
  create FRUIT (
    id serial,
    code varchar(50),
    name varchar(200),
    price integer
  )
 ````
  
  
  
## 建立標準的 Repository methods

* List<Model> findAll();
* Model findById(Long id);
* void insert(Model model);
* void update(Model model);
* void delete(Long id);
	
	
	
## 不要使用 Statement 避免 SQL-Injection

改用 PreparedStatement
````java
String sqlCmd = "SELECT * FROM FRUIT WHERE ID = ?";
PreparedStatement pstmt = conn.prepareStatement(sqlCmd);
pstmt.setLong(1, id);
ResultSet rs = pstmt.executeQuery();
````

## 取得 auto increment ID 的回傳值
因為我們交給資料庫去產生相關的 id, 那麼, 剛剛 insert 進去的資料,
我們需要 model.setId( rs.getLong("id")); 回傳告訴前端的使用者,
每個資料庫的設計有點不同, 以 postgres 來說,
是透過 insert into ..... returning id; 

如果我們是使用 PreparedStatement, 建立 statement 時, 就可以指定我們需要取得產生的 id, **Statement.RETURN_GENERATED_KEYS**,
  再使用 pstmt.getGeneratedKeys(); 也可以取出產生的 id 值.

````java
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
````


# Course 7 Use Opensources

## 學習查找與使用 opensources
以 database connection pooling 為概念, 我們需要一個 connection pooling, 
如何上網 Goolge 查找發現比較適合的 connection pooling, 並且使用 maven pom.xml 定義相關 dependencies,
我們可以發現這篇文章(https://medium.com/@jeevanpaatil/database-connection-pool-analysis-92d50ba4bd06)提到了許多 connection pooling 機制,
此時, 我們可以選擇比較推薦的 HikariCP(https://github.com/brettwooldridge/HikariCP) 作為範例測試。
	
根據相關的說明, 我們可以很快地定義好相關的程式碼
````java
	HikariDataSource ds;
	
	public FruitRepository() {
		
		
		
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:postgresql://localhost:5432/testdb");
		config.setUsername("postgres");
		config.setPassword("postgres");
		config.addDataSourceProperty("minimumIdle", "10");
		config.addDataSourceProperty("maximumPoolSize", "30");
		
		
		
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
````
比較了運行的結果, 比起原本 Connection 不斷 I/O 重新連線花上 41 秒, 到不用 1 秒鐘, 大幅提升了運作效能。
	
## Date 的使用

先說結論, 在 java8 之後, 應該大量使用 java.time.* 的日期與時間, java.time package 大量參考了 jodatime 的設計, JDBC 4.2 版本之後也完全支持 java.time .
	
* java.time.LocalDate
* java.time.LocalDateTime
	
可以透過 of(...) 的 method 進行預設值的配置. 

````java
// 計算 do something 的時間範例(msec級)	
LocalDateTime startTime = LocalDateTime.now();
// do something
LocalDateTime stopTime = LocalDateTime.now();
Long diff = ChronoUnit.MILLIS.between(startTime, stopTime);
System.out.println("total "+diff+" msec");
````
	
以往 java 內建的 java.util.Date 與 java.util.Calendar, 簡單來說, 太難用, 根本就直接可以捨棄. 不然就是搭配 java.text.DateFormat 一併寫 Utils 來處理, 還是太麻煩.
	
	
## Annotation 的概念
Annotation 是 java 5 開始用的設計, 主要是參考 .net 的設計, 作為 **描述用**
	
@Target : 描述的目標有
* ElementType.TYPE : 能描述 class, interface, enum 等
* ElementType.FIELD : 能描述相關的變數
* ElementType.METHOD : 能描述方法
* ElementType.PARAMETER : 能描述方法內的參數值
* ElementType.CONSTRUCTOR : 能描述建構子
* ElementType.LOCAL_VARIABLE : 能描述局部變數
* ElementType.ANNOTATION_TYPE : 能描述其他的 annotation
* ElementType.PACKAGE : 能描述 package
	
@Retention : 表示這個 annotation 的生命週期
可選的值有三種
* RetentionPolicy.SOURCE 表示此 annotation 只在 source java 中有效果，不會編譯進class文件, @Override就是這種注解
* RetentionPolicy.CLASS  表示此 annotation 除了在 source java 有效果，也會編譯進class文件，但是在 runtime 是無效果的, @Retention的默認值，即是當沒有指定@Retention的時候，就會是這種類型
* RetentionPolicy.RUNTIME 表示此 annotation 從 source java 到 runtime 一直存在, 程序可以透過 reflection 獲取這個 annotation 的訊息 
	
annotation 就是描述而已, 該怎麼表現和應用, 都是其他程式發現該 annotation 給予相關的行為。

## Annotation Processing Tool (高階研究)
https://docs.oracle.com/javase/7/docs/technotes/guides/apt/GettingStarted.html
	
## Project Lombok (https://projectlombok.org/)
lombok 就是在 source 上面定義了一些 annotation, 在 javac compile 真正產生 class 之前, 
把相關的 java code 產出中繼的 java code, 簡化我們實際撰寫 javabean 的內容. 
我們通常會用到
	* @Data
	* @Builder
	* @Log (暫時不練習) 
或是要細膩到控制 @Getter @Setter @ToString 及 @EqualsAndHashCode
另外, 在某些特殊狀況, 我們也會控制建構子 @NoArgsConstructor, @RequiredArgsConstructor and @AllArgsConstructor 
大家可以多練習與比較差異.
	
## enum 
java 5 之前沒有 enum, 幾乎都是用 String 來接值, 接著就得去判斷該 String 是否符合規範. 而 enum 就是明定只能接受這些數值的定義.
	

````java
public enum Sex {
  M,
  F,
}
````
	
可以利用 Sex sex = Sex.valueOf("M"); 來指定該 sex 的內容. 

## Homework 
練習定義一個 enum 並加入到自己的 object 之中.
	
		















