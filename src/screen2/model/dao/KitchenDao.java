package screen2.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KitchenDao extends IBaseDao{
    private static final  KitchenDao instance = new KitchenDao();
    private KitchenDao (){};
    public static KitchenDao getInstance() {
        return instance;
    }

    public ArrayList<Integer> takeRecipe(int menuChoice) {
        ArrayList<Integer> recipeList = new ArrayList<>();
        String sql = "Select product_no from recipe where menu_no = ? order by recipe_order"; // menuChoice로 재료번호 오름차순으로 가져오기
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,menuChoice);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                recipeList.add(rs.getInt("product_no")); // 해당하는 요리의 요리재료번호 순서대로 담기
            }
        } catch (SQLException e) {
            System.out.println("예외 발생 : " + e);
        }
        return recipeList;
    }
}
