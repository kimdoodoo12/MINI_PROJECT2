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

    // Product 메인 run 함수
    public void run(){

        // 일차 증가 
        pc.addDay();

        while(true){

            // 현재 일차 조회
            int currentDay = pc.currentDay();

            // 현재 자금 조회
            int currentGold = pc.currentGold();

            // 터미널에 보일 화면 출력
            System.out.println("======================================================================");
            System.out.printf("        [ DAY %d - 종료 재고 보충  ]  | 자금 : %,d 원                 \n", currentDay-1 , currentGold);
            System.out.println("======================================================================");

            System.out.printf("1. 재고보충  2. 재고확인  3. %d일차 영업 시작하기  4. 종료\n", currentDay);

            int ch = scan.nextInt();

            // 1번 선택 시 재고보충 함수 실행
            if(ch == 1){addProductLog_order();}

            // 2번 선택 시 재고확인 함수 실행
            else if(ch == 2){countProductLog();}

            // 3번 선택 시 영업시작 함수 실행
            // 영업시작함수 = GameState 를 true 로 변환 후 무한반복문 탈출  
            // ==>>  main 함수로 이동
            else if(ch == 3){startDay(); return;}

            // 4번 선택 시 아직 미정
            else if(ch == 4){}

            // 이외의 번호 입력 시 다시 반복문 무한루프 실행
            else{break;}

        }

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


    // 영업 시작하기 함수
    public void startDay(){

        boolean result = pc.startDay();

        if(result == false){System.out.println("영업 시작 실패");}

    }


    // 재고 확인 함수
    public void countProductLog(){

        ArrayList<CountProductDTO> result = pc.countProductLog();

        System.out.println("|no | name    | qty    ");

        for(CountProductDTO countProductDTO : result){

            System.out.printf("%-3d %s %d\n", countProductDTO.getProduct_id(), countProductDTO.getProduct_name(), countProductDTO.getProduct_totalQty());
        
        }

    }



} // class END
