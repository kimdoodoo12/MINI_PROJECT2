package screen1.model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GameStateDao extends IBaseDao {
    private GameStateDao() {
    }

    private static final GameStateDao instance = new GameStateDao();

    public static GameStateDao getInstance() {
        return instance;
    }

    // 게임 영업 OFF
    public boolean changeGameState() {
        try {
            String sql = "UPDATE gamestate SET restaurant_state = FALSE WHERE gameState_id = 1;";
            PreparedStatement ps = conn.prepareStatement(sql);
            int result = ps.executeUpdate();
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean minusGold() {
        try {
            String sql = "UPDATE gamestate SET current_gold = current_gold - 20000 WHERER gameState_id = 1;";
            PreparedStatement ps = conn.prepareStatement(sql);
            int result = ps.executeUpdate();
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
}
