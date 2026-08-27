package db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfig {
    public static Properties load() {
        Properties prop = new Properties();
        try (InputStream in = DBConfig.class.getResourceAsStream("db.properties")) {
            if (in == null) {
                throw new IOException("src/db/db.properties 파일이 없습니다. db.properties.example을 복사해서 값을 채워주세요.");
            }
            prop.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }
}
