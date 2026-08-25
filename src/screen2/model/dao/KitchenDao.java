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

    // 해당 menuChoice 레시피 가져오는 메소드
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

    // 완성된 요리 'ready' 상태로 추가 메소드
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

    // 재료 로그 'used' 추가 메소드
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

    // 모든 레시피 보여주는 함수
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

    // 재료 재고 확인 메소드
    public boolean checkProductQty(int productChoice) {
        String sql = "select sum(product_qty) stock from productlog where product_id = ?";
        int result = 1;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,productChoice);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int stock = rs.getInt("stock");
                return stock >= result; // 해당 재료의 갯수가 1 이상이면 true
            }
        } catch (SQLException e) {
            System.out.println("예외 발생 : " + e);
        }
        return false;
    }

    // 영업 중인지 확인 메소드
    public boolean checkRestaurantState() {
        String sql = "select restaurant_state from gamestate where gamestate_id = 1";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return rs.getBoolean("restaurant_state");
            }
        } catch (SQLException e) {
            System.out.println("영업 종료 : " + e);
        }
        return false;
    }
}
