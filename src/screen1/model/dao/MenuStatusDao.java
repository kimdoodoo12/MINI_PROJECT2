package screen1.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import screen1.model.dto.MenuStatusDto;

public class MenuStatusDao extends IBaseDao {
    private MenuStatusDao() {
    }

    private static final MenuStatusDao instance = new MenuStatusDao();

    public static MenuStatusDao getInstance() {
        return instance;
    }

    // 팔린 메뉴 이름, 수량 가져오기
    public ArrayList<MenuStatusDto> getMenuSales(int day) {
        ArrayList<MenuStatusDto> list = new ArrayList<>();
        String sql = "SELECT MENU_NAME, COUNT(*) COUNT "
                + "FROM CUSTOMERLOG C1 JOIN MENU M1 ON C1.MENU_ID = M1.MENU_ID "
                + "WHERE C1.CUSTOMERLOG_DAY = ? AND C1.CUSTOMER_STATE = 'SERVED' "
                + "GROUP BY M1.menu_id;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, day);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MenuStatusDto menuStatusDto = new MenuStatusDto();
                menuStatusDto.setMenuName(rs.getNString("MENU_NAME"));
                menuStatusDto.setCount(rs.getInt("COUNT"));

                list.add(menuStatusDto);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        // System.out.println(list);
        return list;
    }
}
