package screen1.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import screen1.model.dto.ProductStatusDto;

public class ProductStatusDao extends IBaseDao {
    private ProductStatusDao() {
    }

    private final static ProductStatusDao instance = new ProductStatusDao();

    public static ProductStatusDao getInstance() {
        return instance;
    }

    // 재고명 + 사용량 집계
    public ArrayList<ProductStatusDto> getUsed(int day) {
        ArrayList<ProductStatusDto> list = new ArrayList<>();

        try {
            String sql = "SELECT PRODUCT_NAME, SUM(-PRODUCT_QTY) AS USED"
                    + " FROM PRODUCTLOG PL1 JOIN PRODUCT P1 ON PL1.PRODUCT_ID = P1.PRODUCT_NO"
                    + " WHERE PL1.CUSTOMERLOG_DAY = ? AND PL1.PRODUCT_CONDITION = 'USED'"
                    + " GROUP BY P1.PRODUCT_NAME";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, day);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductStatusDto productStatusDto = new ProductStatusDto();
                productStatusDto.setProductName(rs.getString("product_name"));
                productStatusDto.setUsed(rs.getInt("USED"));
                productStatusDto.setRemain(0);
                list.add(productStatusDto);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        // System.out.println("사용량 + 재고명 " + list.toString());
        return list;
    }

    // 재고명 + 잔여량
    public ArrayList<ProductStatusDto> getRemain(int day) {
        ArrayList<ProductStatusDto> list = new ArrayList<>();
        // System.out.println("컨트롤러 실행");
        try {
            String sql = "SELECT PRODUCT_NAME, SUM(PRODUCT_QTY) AS REMAIN "
                    + "FROM PRODUCTLOG PL1 JOIN PRODUCT P1 ON PL1.PRODUCT_ID = P1.PRODUCT_NO "
                    + "GROUP BY P1.PRODUCT_NAME;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductStatusDto productStatusDto = new ProductStatusDto();
                productStatusDto.setProductName(rs.getString("product_name"));
                productStatusDto.setUsed(0);
                productStatusDto.setRemain(rs.getInt("REMAIN"));
                list.add(productStatusDto);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        // System.out.println("잔여량 + 재고명 " + list.toString());
        return list;
    }
}
