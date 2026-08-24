package screen2.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import screen2.controller.ProductController;
import screen2.model.dto.GameStateDTO;
import screen2.model.dto.ProductDTO;
import screen2.model.dto.ProductLogDTO;

public class ProductDAO extends IBaseDao {

    private ProductDAO() {
    };

    private static final ProductDAO instance = new ProductDAO();
    public static ProductDAO getInstance(){return instance;}

    // 재고 발주 로그 추가 함수
    public boolean addProductLog_order(ProductLogDTO productLogDTO){

        // 재료의 가격 가져오기
        int product_price = 0;

        ArrayList<ProductDTO> list = findProductLog();

        for(int i = 0 ; i <= list.size()-1 ; i++){
            if(list.get(i).getProduct_no() == productLogDTO.getProduct_id()){
                product_price = list.get(i).getProduct_price();
            }
        }

        try{
            // 1-1. SQL 작성  ,  값에 와일드카드(?) 이용한 매개변수 대입
            String sql = "insert into productLog( product_id , product_qty , product_condition , productLog_price , customerLog_day ) values( ? , ? , 'order' , ? , (select current_day from GameState where gameState_id = 1) )";

            // 1-2. 연동된 데이터베이스에 SQL 기재
            // conn 멤버변수는 BaseDao 에게 물려받음
            PreparedStatement ps = conn.prepareStatement(sql);

            // 1-3. 기재된 SQL 문법 안에 ?(와일드카드) 매개변수 값 대입  ==>>  ps.set타입( ?번호 , 값 )
            ps.setInt(1, productLogDTO.getProduct_id());  // 1(첫번째 ?)에 Dto content 대입
            ps.setInt(2, productLogDTO.getProduct_qty());   // 2(두번째 ?)에 Dto writer 대입
            ps.setInt(3, product_price * productLogDTO.getProduct_qty());
            


            // 1-4. 기재된 SQL 실행 ,   .executeUpdate()  insert/update/delete  에서 사용
            int result = ps.executeUpdate();  // 실행 후 처리된 레코드 수 반환

            // 재료 발주 금액 자본 차감 함수
            buyProductLog();            

            // 1-5. SQL 결과
            if(result == 1){ return true;}

        }catch( SQLException e){ System.out.println(e);}


        return false;
    }


    // 재고 가격 조회 함수
    public ArrayList<ProductDTO> findProductLog(){

        ArrayList<ProductDTO> list = new ArrayList<>();  // 레코드 정보들을 담을 리스트 생성

        try{
            // 2-1. SQL 작성한다.
            String sql = "select * from product";  

            // 2-2. SQL 기재한다.
            PreparedStatement ps = conn.prepareStatement(sql);  // *예외 발생

            // 2-3. ?매개변수 대입한다. <생략>

            // 2-4. 기재된 SQL 실행  ,  executeQuery() 는 select(조회) 문에서 사용
            ResultSet rs = ps.executeQuery();  // 

            // 2-5. SQL 결과( select 조회 결과는 항상 테이블로 반환한다. ) 즉, 레코드 하나씩 타입변환
            // rs.next() : 다음 레코드(행) 이동 , 마지막 레코드까지 하나씩 이동 반복
            while (rs.next()) { 
                
                // 2-6. 현재 레코드 필드(속성/정보)들을 --> DTO 변환
                ProductDTO productDTO = new ProductDTO();
                productDTO.setProduct_no(rs.getInt("product_no")); // rs.get타입("가져올 속성명")
                productDTO.setProduct_price(rs.getInt("product_price"));

                // 2-7. 변환한 DTO --> 리스트에 담기
                list.add(productDTO);
            }

        }catch(SQLException e){ System.out.println(e);}

        // 2-8. 리스트 반환
        return list;

    } // [2] END


    // 자본 금액 조회 함수
    public int currentGold(){

        GameStateDTO gameStateDTO = new GameStateDTO();  // 레코드 정보들을 담을 리스트 생성

        try{
            // 2-1. SQL 작성한다.
            String sql = "select * from GameState";  

            // 2-2. SQL 기재한다.
            PreparedStatement ps = conn.prepareStatement(sql);  // *예외 발생

            // 2-3. ?매개변수 대입한다. <생략>

            // 2-4. 기재된 SQL 실행  ,  executeQuery() 는 select(조회) 문에서 사용
            ResultSet rs = ps.executeQuery();  // 

            // 2-5. SQL 결과( select 조회 결과는 항상 테이블로 반환한다. ) 즉, 레코드 하나씩 타입변환
            // rs.next() : 다음 레코드(행) 이동 , 마지막 레코드까지 하나씩 이동 반복
            while (rs.next()) { 
                
                // 2-6. 현재 레코드 필드(속성/정보)들을 --> DTO 변환
                gameStateDTO.setGameState_id(rs.getInt("gameState_id")); // rs.get타입("가져올 속성명")
                gameStateDTO.setCurrent_day(rs.getInt("current_day"));
                gameStateDTO.setCurrent_gold(rs.getInt("current_gold"));
                gameStateDTO.setRestaurant_state(rs.getBoolean("restaurant_state"));

            }

        }catch(SQLException e){ System.out.println(e);}

        int result = gameStateDTO.getCurrent_gold();

        return result;

    }


    // 재료 발주 금액 자본 차감 함수
    public boolean buyProductLog(){

        ProductLogDTO productLogDTO = new ProductLogDTO();

        try{
            // 2-1. SQL 작성한다.
            String sql = "SELECT * FROM productLog ORDER BY productLog_no DESC LIMIT 1";  

            // 2-2. SQL 기재한다.
            PreparedStatement ps = conn.prepareStatement(sql);  // *예외 발생

            // 2-3. ?매개변수 대입한다. <생략>

            // 2-4. 기재된 SQL 실행  ,  executeQuery() 는 select(조회) 문에서 사용
            ResultSet rs = ps.executeQuery();  // 

            // 2-5. SQL 결과( select 조회 결과는 항상 테이블로 반환한다. ) 즉, 레코드 하나씩 타입변환
            // rs.next() : 다음 레코드(행) 이동 , 마지막 레코드까지 하나씩 이동 반복
                
            // 2-6. 현재 레코드 필드(속성/정보)들을 --> DTO 변환
            productLogDTO.setProduct_id(rs.getInt("product_id")); // rs.get타입("가져올 속성명")
            productLogDTO.setProduct_qty(rs.getInt("product_qty"));
            productLogDTO.setProduct_condition(rs.getString("product_condition"));
            productLogDTO.setProductLog_price(rs.getInt("productLog_price"));
            productLogDTO.setCustomerLog_day(rs.getInt("customerLog_day"));

        }catch(SQLException e){ System.out.println(e);}

        int currentGold = currentGold();

        try{
            // 1-1. SQL 작성  ,  값에 와일드카드(?) 이용한 매개변수 대입
            String sql = "update GameState set current_gold = ?";

            // 1-2. 연동된  데이터베이스에 SQL 기재
            // conn 멤버변수는 BaseDao 에게 물려받음
            PreparedStatement ps = conn.prepareStatement(sql);

            // 1-3. 기재된 SQL 문법 안에 ?(와일드카드) 매개변수 값 대입  ==>>  ps.set타입( ?번호 , 값 )
            ps.setInt(1, currentGold - productLogDTO.getProductLog_price());  // 1(첫번째 ?)에 Dto content 대입
            

            // 1-4. 기재된 SQL 실행 ,   .executeUpdate()  insert/update/delete  에서 사용
            int result = ps.executeUpdate();  // 실행 후 처리된 레코드 수 반환

            // 1-5. SQL 결과
            if (result == 1) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return false;
        
    }


}
