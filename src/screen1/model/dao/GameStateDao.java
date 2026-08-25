package screen1.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

public class GameStateDao extends IBaseDao {
    private GameStateDao() {
    }

    private static final GameStateDao instance = new GameStateDao();

    public static GameStateDao getInstance() {
        return instance;
    }

    // 게임 OFF 업데이트
    public boolean setGameOff() {
        boolean result = false;
        try {
            String sql = "UPDATE gamestate SET restaurant_state = FALSE WHERE gameState_id = 1;";
            PreparedStatement ps = conn.prepareStatement(sql);
            result = ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    // 하루 지나면 골드 차감
    public boolean minusGold() {
        boolean result = false;
        try {
            String sql = "UPDATE gamestate SET current_gold = current_gold - 20000 WHERE gameState_id = 1;";
            PreparedStatement ps = conn.prepareStatement(sql);
            result = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
}
