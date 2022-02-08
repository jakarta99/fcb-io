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










 


















