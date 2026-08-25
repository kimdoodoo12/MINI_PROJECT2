package screen1.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TotalProductStatusDao extends IBaseDao {
    private TotalProductStatusDao() {
    }

    private final static TotalProductStatusDao instance = new TotalProductStatusDao();

    public static TotalProductStatusDao getInstance() {
        return instance;
    }

    // 총 발주량
    public int getTotalOrder() {
        int result = 0;
        String sql = "SELECT SUM(PRODUCT_QTY) Total FROM PRODUCTLOG"
                + " WHERE PRODUCT_CONDITION = 'ORDER'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                result = rs.getInt("Total");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    // 총 소모 수량
    public int getTotalUsed() {
        int result = 0;
        String sql = "SELECT SUM(-PRODUCT_QTY) Total FROM PRODUCTLOG"
                + " WHERE PRODUCT_CONDITION = 'USE'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                result = rs.getInt("Total");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    // 총 잔여 수량
    public int getTotalRemain() {
        int result = 0;
        String sql = "SELECT SUM(PRODUCT_QTY) Total FROM PRODUCTLOG";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                result = rs.getInt("Total");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
}
