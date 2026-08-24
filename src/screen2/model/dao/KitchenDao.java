package screen2.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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

    public void addCookTable(int productChoice, String state) {
        String sql = "Insert into cook (menu_id, cook_state) values (?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productChoice);
            ps.setString(2, state);
            int result = ps.executeUpdate();
            if(result != 1){
                System.out.println("sql 오류");
            }
        } catch (SQLException e) {
            System.out.println("예외 발생 : " + e);
        }
    }

    public void addProductLogUsed(int menuChoice) {
        String sql = "insert into productLog (product_id, product_qty, product_condition, productLog_price, customerLog_day) values (?,-1,'used',0,(select current_day from GameState where gameState_id = 1))";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,menuChoice);
            int result = ps.executeUpdate();
            if(result!=1){
                System.out.println("sql 오류");
            }
        } catch (SQLException e) {
            System.out.println("예외 발생 : " + e);
        }
    }

    public Map<String, ArrayList<String >> findAllRecipes() {
        Map<String, ArrayList<String>> map = new LinkedHashMap<>(); // 그냥 HashMap은 순서를 보장하지않는다!!!
        String sql = "select m.menu_name , p.product_name from recipe r join menu m on r.menu_no = m.menu_id " +
                "join product p on r.product_no = p.product_no order by r.menu_no , r.recipe_order";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String menuName = rs.getString("menu_name");
                String productName = rs.getString("product_name");
                if (!map.containsKey(menuName)){ // map에 키가 꺼내온 메뉴 이름이 없으면 키 추가하고 재료 담을 ArrayList 추가
                    map.put(menuName, new ArrayList<String>());
                }
                // 키가 존재하면 메뉴이름 키에 해당하는 값꺼내와서 ArrayList.add 통해 맨뒤에 재료 이름 추가
                ArrayList<String> list = map.get(menuName);
                list.add(productName);
            }

        } catch (SQLException e) {
            System.out.println("예외 발생 : " + e);
        }
        return map;
    }
}
