package screen1.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusDao extends IBaseDao {
    // 현재 일차 가져오기
    public int getDay() {
        int result = 0;
        String sql = "SELECT CURRENT_DAY FROM GAMESTATE WHERE GAMESTATE_ID = 1;";
        try {
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

    // 총 매출(손님이 계산한 돈)
    public int getSales(int day) {
        int result = 0;
        String sql = "SELECT SUM(CURRENT_GOLD) TOTALGOLD FROM CUSTOMERLOG WHERE CUSTOMERLOG_DAY = ? AND CUSTOMER_STATE = 'SERVED'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, day);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt("TOTALGOLD");
            }

        } catch (SQLException e) {
            System.out.printf("매출 : %d\n", result);
            System.out.println(e);
        }
        return result;
    }

    // 전날 지출 (재고 지출)
    public int getExpense(int day) {
        int result = 0;
        String sql = "SELECT SUM(PRODUCTLOG_PRICE) FROM PRODUCTLOG WHERE CUSTOMERLOG_DAY = ? AND PRODUCT_CONDITION = '발주'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, day);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt("SUM(PRODUCTLOG_PRICE)");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
}
