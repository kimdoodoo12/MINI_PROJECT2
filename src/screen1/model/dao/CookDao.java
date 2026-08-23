package screen1.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import screen1.model.dto.CookDto;

public class CookDao extends IBaseDao{

    private CookDao(){};
    private static final CookDao instance = new CookDao();
    public static CookDao getInstance(){return instance;}

    public ArrayList<CookDto> findAllCook(){

        ArrayList <CookDto> cookDtos = new ArrayList<>();
        try{
            String sql = "SELECT * FROM Cook WHERE cook_state = 'ready';";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                CookDto cookDto = new CookDto();
                cookDto.setCook_id(rs.getInt("cook_id"));
                cookDto.setMenu_id(rs.getInt("menu_id"));
                cookDto.setCook_state(rs.getString("cook_state"));

                cookDtos.add(cookDto);
            }

        }catch(SQLException e){System.out.println(e);}
        
        return cookDtos;
    }

    public boolean setCook(int cook_id, boolean isServed){
        try{
            String sql = "UPDATE Cook SET cook_state = ? WHERE cook_id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);

            // 서빙여부에 따라 UPDATE 결정(SERVED 또는 DISPOSE)
            if (isServed == true){
                ps.setString(1, "SERVED");
            }
            else if (isServed == false){
                ps.setString(1, "DISPOSE");
            }
            ps.setInt(2, cook_id);

            int result = ps.executeUpdate();
            if (result == 1){
                return true;
            }        
        }catch(SQLException e){System.out.println(e);}

        return false;
    }
}
