package screen2.view;

import java.util.ArrayList;
import java.util.Scanner;

import screen2.controller.ProductController;
import screen2.model.dto.CountProductDTO;
import screen2.model.dto.ProductDTO;
import screen2.model.dto.ProductLogDTO;

public class ProductView {

    private ProductView(){};
    private static final ProductView instance = new ProductView();
    public static ProductView getInstance(){return instance;}

    private ProductController pc = ProductController.getInstance();

    Scanner scan = new Scanner(System.in);

    public void run(){

        int currentDay = currentDay();

        int currentGold = currentGold();

        while(true){

            System.out.println("======================================================================");
            System.out.printf("        [ DAY %d - 종료 재고 보충  ]  | 자금 : %,d 원                 ", currentDay-1 , currentGold);
            System.out.println("======================================================================");

            System.out.printf("1. 재고보충  2. 재고 확인  3. %d일차 영업 시작하기  4. 종료", currentDay);

            String ch = scan.next();

            if(ch == "1"){addProductLog_order();}
            else if(ch == "2"){}
            else if(ch == "3"){startDay();}
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

    // 재고 발주 로그 추가 함수
    public void addProductLog_order(){

        System.out.println("1. 햄버거빵  2. 소고기패티  3. 불고기패티  4. 치즈  5. 양상추  6. 토마토");
        System.out.println("7. 피클  8. 베이컨  9. 새우패티  10. 치킨패티  11. 양파  12. 스파이시소스");

        System.out.println("발주할 재료의 번호를 입력하세요");
        int productNumber = scan.nextInt();

        System.out.println("발주할 재료의 수량을 입력하세요");
        int productCount = scan.nextInt();

        ProductLogDTO productLogDTO = new ProductLogDTO(productNumber, productCount);

        boolean result = pc.addProductLog_order(productLogDTO);

        if (result) {System.out.println("발주 성공");}
        else {System.out.println("발주 실패");}

    }

    // 재료 가격 조회 함수
    public ArrayList<ProductDTO> findProductLog(){

        ArrayList<ProductDTO> result = pc.findProductLog();
        
        return result;
    }

    // 자본 금액 조회 함수
    public int currentGold(){

        int result = pc.currentGold();

        return result;
    }

    // 재료 발주 금액 자본 차감 함수
    public void buyProductLog(){

        Boolean result = pc.buyProductLog();

    }

    // 일차 조회 함수
    public int currentDay(){

        int result = pc.currentDay();

        return result;        
    }

    // 영업 시작하기 함수
    public void startDay(){

        boolean result = pc.startDay();

        if(result == false){System.out.println("영업 시작 실패");}
    }

    // 재고 확인 함수
    public void countProductLog(){

        ArrayList<CountProductDTO> result = pc.countProductLog();

        for(CountProductDTO countProductDTO : result){
            System.out.printf("%d %s %d", countProductDTO.getProduct_id(), countProductDTO.getProduct_name(), countProductDTO.getProduct_totalQty());
        }
        
    }


} // class END
