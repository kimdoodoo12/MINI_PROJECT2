package screen2.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import db.DBConfig;

public class IBaseDao {

    private String url = "jdbc:mysql://127.0.0.1:3306/MINI2";
    private String user = "root";
    private String pwd = "1234";
    // 2. 연동 인터페이스 , protected : 상속 관계이면 다른 패키지도 접근 허용
    protected Connection conn;
    // 3. 연동 메소드
    private void connect(){
        try{
            // 3-1: mysql Driver 클래스 로드 한다
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 3-2: db.properties에서 연동 정보 로드 후 서버와 연동, 성공하면 conn(인터페이스) 대입
            // Properties prop = DBConfig.load();
            // conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("pwd"));
            conn = DriverManager.getConnection(url, user, pwd);
        }catch(Exception e){System.out.println(e);}

    }
    // 4. 기본 생성자에 연동메소드 실행, BaseDao 클래스로부터 상속받은 DAO들은 자동 connect
    protected IBaseDao(){connect();}

}
