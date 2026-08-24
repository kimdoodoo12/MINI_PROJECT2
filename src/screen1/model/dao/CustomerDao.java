package screen1.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import screen1.model.dto.CustomerDto;

public class CustomerDao extends IBaseDao{
    private CustomerDao(){};
    private static final CustomerDao instance = new CustomerDao();
    public static CustomerDao getInstance(){return instance;}


    public boolean createCustomer(CustomerDto customerDto){
        try{
            String sqlCustomer = "INSERT INTO CustomerLog (menu_id , customerLog_day) VALUES ( ? , ? )";
            String sqlGameDay = "SELECT current_day FROM GameState;";

            
            PreparedStatement ps = conn.prepareStatement(sqlGameDay);
            PreparedStatement ps2 = conn.prepareStatement(sqlCustomer, Statement.RETURN_GENERATED_KEYS);
            

            
            ResultSet rs = ps.executeQuery();

            int menuId = 0;
            int gameDay = 0;

            menuId = customerDto.getMenu_id();
            ps2.setInt(1, menuId);
            if (rs.next()){
                gameDay = rs.getInt("current_day");
                ps2.setInt(2, gameDay);
            }


            int result = ps2.executeUpdate();
            if (result == 1){
                // 생성된 customer_no를 손님 객체에 채워넣어서, 이 스레드가 자기 자신을 DB에서 조회할 수 있게 함
                ResultSet keys = ps2.getGeneratedKeys();
                if (keys.next()){
                    customerDto.setCustomer_no(keys.getInt(1));
                }
                // 로컬 객체에도 설정
                customerDto.setCustomer_log_day(gameDay);
                return true;
            }
        }catch(SQLException e){System.out.println(e);}

        return false;
    }

    // 손님 스레드가 자신의 현재 상태를 DB에서 확인(폴링)하기 위한 조회
    public String getState(int customer_no){
        try{
            String sql = "SELECT customer_state FROM CustomerLog WHERE customer_no = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, customer_no);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return rs.getString("customer_state");
            }
        }catch(SQLException e){System.out.println(e);}

        return null;
    }

    // 제한시간이 지나도록 서빙되지 않은 손님을 이탈 처리
    public boolean setLeft(int customer_no){
        try{
            String sql = "UPDATE CustomerLog SET customer_state = 'left' WHERE customer_no = ? AND customer_state = 'waiting'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, customer_no);
            int result = ps.executeUpdate();
            if (result == 1){
                return true;
            }
        }catch(SQLException e){System.out.println(e);}

        return false;
    }

    // 식당영업상태를 DB에서 갖고오기 (GameState쪽 DAO 역할)
    public boolean isOpen(){
        try{
        String sql = "SELECT restaurant_state FROM GameState";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            return rs.getBoolean("restaurant_state"); 
        }         
        }catch(SQLException e){System.out.println(e);}

        return false;
    }
    // 식당영업일 DB에서 갖고오기









    public ArrayList<CustomerDto> findAllCustomer(){
        ArrayList <CustomerDto> customerDtos = new ArrayList<>();
        try{
            String sql = "SELECT * FROM CustomerLog WHERE customer_state = 'waiting';";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                CustomerDto customerDto = new CustomerDto();
                customerDto.setCustomer_no(rs.getInt("customer_no"));
                customerDto.setMenu_id(rs.getInt("menu_id"));
                customerDto.setCustomer_log_day(rs.getInt("customerLog_day"));
                customerDto.setCurrent_gold(rs.getInt("current_gold"));
                customerDto.setCustomer_state(rs.getString("customer_state"));
                
                customerDtos.add(customerDto);
            }
        }catch(SQLException e){System.out.println(e);}

        return customerDtos;
    }

    public boolean checkBill(int customer_no, int price){

        try{
            String sql = "UPDATE CustomerLog SET customer_state = 'served', current_gold = ? WHERE customer_no = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, price);
            ps.setInt(2, customer_no);            
            int result = ps.executeUpdate();
            if (result == 1){
                return true;                
            }

        }catch(SQLException e){System.out.println(e);}

        return false;
    }
    

}
