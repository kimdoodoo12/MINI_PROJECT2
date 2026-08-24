package screen2.view;

import java.util.Scanner;

import screen2.controller.ProductController;
import screen2.model.dto.ProductDTO;

public class ProductView {

    private ProductView(){};
    private static final ProductView instance = new ProductView();
    public static ProductView getInstance(){return instance;}

    private ProductController pc = ProductController.getInstance();

    Scanner scan = new Scanner(System.in);

    public void run(){

        while(true){

            System.out.println("======================================================================");
            System.out.println("        [ DAY 1 - 종료 재고 보충  ]  | 자금 : 150,000 원                 ");
            System.out.println("======================================================================");

            System.out.println("1. 재고보충  2. 재고 확인  3. 2일차 영업 시작하기  4. 종료");

            String ch = scan.next();

            if(ch == "1"){}
            else if(ch == "2"){}
            else if(ch == "3"){}
            else if(ch == "4"){}
            else{break;}

        }

    /* 
        ===== [ DAY 01 ]  [ RESTAURANT Kitchen ]  |  자금 : 150,000 원 ====

        [ 재고 현황 ]  

        번호  재료명      수량    상태 
        1    김치        6개 
        2    돼지고기     4개 
        3    두부        5개 
        ... 
        6    대파        0개   [소진] 
        
        * 소진 재료: 대파  (보충은 영업 종료 후 가능합니다)
        ======================================================================
                [ DAY 1 - 종료 재고 보충  ]  | 자금 : 150,000 원                   
        ======================================================================
        재고보충 2. 재고 확인 3. 2일차 영업 시작하기 4. 종료
        선택> 1
        보충할 재료:
        선택> 1
        보충할 수량 : 
        선택> 6
        보충 완료
        소지금액 130,000원
        ======================================================================
                [ DAY 1 - 종료 재고 보충  ]  | 자금 : 130,000 원                   
        ======================================================================
        재고보충 2. 재고 확인 3. 2일차 영업 시작하기 4. 종료
        선택>

    */

    }


    public void addProductLog_order(){

        System.out.println("1. 햄버거빵  2. 소고기패티  3. 불고기패티  4. 치즈  5. 양상추  6. 토마토");
        System.out.println("7. 피클  8. 베이컨  9. 새우패티  10. 치킨패티  11. 양파  12. 스파이시소스");

        System.out.println("발주할 재료의 번호를 입력하세요");
        int productNumber = scan.nextInt();

        System.out.println("발주할 재료의 수량을 입력하세요");
        int productCount = scan.nextInt();

        ProductDTO productDTO = new ProductDTO(productNumber, productCount);

        boolean result = pc.addProductLog_order(productDTO);

        if (result) {System.out.println("발주 성공");}
        else {System.out.println("발주 실패");}

    }



} // class END
