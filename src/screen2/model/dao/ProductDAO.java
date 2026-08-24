package screen2.model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import screen2.controller.ProductController;
import screen2.model.dto.ProductDTO;

public class ProductDAO extends IBaseDao{

    private ProductDAO(){};
    private static final ProductDAO instance = new ProductDAO();
    public static ProductDAO getInstance(){return instance;}

    
    public boolean addProductLog_order(ProductDTO productDTO){

        try{
            // 1-1. SQL 작성  ,  값에 와일드카드(?) 이용한 매개변수 대입
            String sql = "insert into productLog( product_id , product_qty , product_condition , productLog_price , customerLog_day ) values( ? , ? , '발주' , ? , ? )";

            // 1-2. 연동된  데이터베이스에 SQL 기재
            // conn 멤버변수는 BaseDao 에게 물려받음
            PreparedStatement ps = conn.prepareStatement(sql);

            // 1-3. 기재된 SQL 문법 안에 ?(와일드카드) 매개변수 값 대입  ==>>  ps.set타입( ?번호 , 값 )
            ps.setInt(1, productDTO.get재료번호());  // 1(첫번째 ?)에 Dto content 대입
            ps.setInt(2, productDTO.get수량());   // 2(두번째 ?)에 Dto writer 대입

            

            // 1-4. 기재된 SQL 실행 ,   .executeUpdate()  insert/update/delete  에서 사용
            int result = ps.executeUpdate();  // 실행 후 처리된 레코드 수 반환

            // 1-5. SQL 결과
            if(result == 1){ return true;}

        }catch( SQLException e){ System.out.println(e);}


        return true;
    }
}
