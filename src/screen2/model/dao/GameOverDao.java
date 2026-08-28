package screen2.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import screen2.model.dto.RankDto;

public class GameOverDao extends IBaseDao {
    private GameOverDao() {
    }

    private static final GameOverDao instance = new GameOverDao();

    public static GameOverDao getInstance() {
        return instance;
    }

    // 게임 기록 추가
    public boolean gameRankInsert(String userName) {
        try {
            String sql = "INSERT INTO GameLog (user_name, current_gold, max_date) "
                    + "SELECT ?, "
                    + "(SELECT SUM(current_gold)) FROM CustomerLog "
                    + "current_day FROM GameState WHERE gameState_id = 1";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            int result = ps.executeUpdate();
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    // 게임 기록(랭킹) 조회
    public ArrayList<RankDto> gameRankList() {
        ArrayList<RankDto> list = new ArrayList<>();
        try {
            String sql = "SELECT USER_NAME, CURRENT_GOLD, MAX_DATE FROM GameLog "
                    + "ORDER BY MAX_DATE DESC, CURRENT_GOLD DESC, gameLog_no ASC LIMIT 10";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RankDto rankDto = new RankDto();
                rankDto.setUserName(rs.getString("USER_NAME"));
                rankDto.setCurrentGold(rs.getInt("CURRENT_GOLD"));
                rankDto.setMaxDate(rs.getInt("MAX_DATE"));
                list.add(rankDto);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

}
