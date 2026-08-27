package screen1.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import screen1.model.dto.MenuStatusDto;
import screen1.model.dto.TotalMenuStatusDto;

public class TotalMenuStatusDao extends IBaseDao {
    private TotalMenuStatusDao() {
    }

    private final static TotalMenuStatusDao instance = new TotalMenuStatusDao();

    public static TotalMenuStatusDao getInstance() {
        return instance;
    }

    public ArrayList<TotalMenuStatusDto> getTotalMenuSales() {
        ArrayList<TotalMenuStatusDto> list = new ArrayList<>();
        String sql = "SELECT MENU_NAME, COUNT(*) CNT"
                + " FROM CustomerLog C1 JOIN MENU M1 ON C1.MENU_ID = M1.MENU_ID"
                + " WHERE C1.CUSTOMER_STATE = 'SERVED'"
                + " GROUP BY M1.MENU_ID, M1.MENU_NAME"
                + " ORDER BY CNT DESC LIMIT 3";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); // setInt 없음
            while (rs.next()) {
                list.add(new TotalMenuStatusDto(
                        rs.getString("MENU_NAME"),
                        rs.getInt("CNT")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
