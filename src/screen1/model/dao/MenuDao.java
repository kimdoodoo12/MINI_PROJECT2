package screen1.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class MenuDao extends IBaseDao{
    private MenuDao(){};
    private static final MenuDao instance = new MenuDao();
    public static MenuDao getInstance() {return instance;}
    
    public int getPrice(int menu_id){
        try{
            String sql = "SELECT menu_price FROM menu WHERE menu_id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, menu_id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return rs.getInt("menu_price");
            }
            

        }catch(SQLException e){System.out.println(e);}

        return 0;
    }

    public String getMenu(int menu_id){
        try{
            String sql = "SELECT menu_name FROM menu WHERE menu_id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, menu_id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return rs.getString("menu_name");
            }
        }catch(SQLException e){System.out.println(e);}

        return null;
    }

    public int getRandomMenu(){
        try{
            String sqlMenu = "SELECT menu_id FROM menu ORDER BY RAND() LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sqlMenu);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return rs.getInt("menu_id");
            }
        }catch(SQLException e){System.out.println(e);}
        
        return 0;
    }
}
