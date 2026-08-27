package screen1.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TotalStatusDao extends IBaseDao {
    private TotalStatusDao() {
    }

    private static final TotalStatusDao instance = new TotalStatusDao();

    public static TotalStatusDao getInstance() {
        return instance;
    }

    // 최종 일자 가져오기 * GameStateDTO 로 이전 예정
    public int getDay() {
        int result = 0;
        try {
            String sql = "SELECT CURRENT_DAY FROM GameState WHERE GAMESTATE_ID = 1;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt("current_day");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    // 최종 자금 가져오기 * GameStateDTO 로 이전 예정
    public int getGold() {
        int result = 0;
        try {
            String sql = "SELECT CURRENT_GOLD FROM GameState WHERE GAMESTATE_ID = 1;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt("CURRENT_GOLD");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    // 전체 누적 매출
    public int getTotalSales() {
        int result = 0;
        try {
            String sql = "SELECT SUM(CURRENT_GOLD) TOTAL_GOLD FROM CustomerLog WHERE CUSTOMER_STATE = 'SERVED'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt("TOTAL_GOLD");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    // 전체 지출
    public int getTotalExpense() {
        int result = 0;
        String sql = "SELECT SUM(PRODUCTLOG_PRICE) FROM productLog WHERE PRODUCT_CONDITION = 'ORDER'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt("SUM(PRODUCTLOG_PRICE)");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    // 전체 방문 손님
    public int getTotalCustomer() {
        int result = 0;
        String sql = "SELECT COUNT(*) TOTAL_CUSTOMER FROM CustomerLog WHERE CUSTOMER_STATE = 'SERVED' OR CUSTOMER_STATE = 'LEFT'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt("TOTAL_CUSTOMER");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        // System.out.println("총 방문 손님" + result);
        return result;
    }

    // 전체 식사완료 손님
    public int getTotalServed() {
        int result = 0;
        String sql = "SELECT COUNT(*) TOTAL_SERVED FROM CustomerLog WHERE CUSTOMER_STATE = 'SERVED'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt("TOTAL_SERVED");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        // System.out.println("식사 완료 손님" + result);
        return result;
    }

}
