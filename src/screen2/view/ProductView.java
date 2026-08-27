package screen2.view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import screen2.controller.GameOverController;
import screen2.controller.ProductController;
import screen2.model.dto.CountProductDTO;
import screen2.model.dto.ProductDTO;
import screen2.model.dto.ProductLogDTO;
import screen2.model.dto.RankDto;

public class ProductView {

    private ProductView() {
    };

    private static final ProductView instance = new ProductView();

    public static ProductView getInstance() {
        return instance;
    }

    private ProductController pc = ProductController.getInstance();
    private GameOverController gc = GameOverController.getInstance();

    Scanner scan = new Scanner(System.in);

    // Product 메인 run 함수
    public void run() {

        // 일차 증가
        pc.addDay();

        int i = 0;

        while (true) {

            // 현재 일차 조회
            int currentDay = pc.currentDay();

            // 현재 자금 조회
            int currentGold = pc.currentGold();
            if (currentGold > 0) {
                // 터미널에 보일 화면 출력
                System.out.println("======================================================================");
                System.out.printf("              [ DAY %d - 재고 보충  ]  | 자금 : %,d 원                 \n", currentDay,
                        currentGold);
                System.out.println("======================================================================");

                if(i == 0){autoOrderAsk(); i++;}
                else{i++;}

                System.out.printf("1. 재고보충  2. 재고확인  3. %d일차 영업 시작하기\n", currentDay);

                int ch = scan.nextInt();

                // 1번 선택 시 재고보충 함수 실행
                if (ch == 1) {
                    addProductLog_order();
                }

                // 2번 선택 시 재고확인 함수 실행
                else if (ch == 2) {
                    countProductLog();
                }

                // 3번 선택 시 영업시작 함수 실행
                // 영업시작함수 = GameState 를 true 로 변환 후 무한반복문 탈출
                // ==>> main 함수로 이동
                else if (ch == 3) {
                    startDay();
                    return;
                }

                // 이외의 번호 입력 시 다시 반복문 무한루프 실행
                else {
                    System.out.println("번호를 다시 제대로 입력해 주세요");
                }
            } else {
                System.out.println("이름을 입력해주세요:");
                String name = scan.next();
                gameRankInsert(name);
                gameRankList();
                System.exit(0);
            }
        }

    }

    // 재고 발주 로그 추가 함수
    public void addProductLog_order() {

        System.out.println(
                "---------------------------------------------------------------------------------------------------");
        System.out.println("1. 햄버거빵 : 500원  ,  2. 소고기패티 : 1200원 ,  3. 불고기패티 : 1000원 ,  4. 치즈 : 300원");
        System.out.println("5. 양상추 : 200원    ,  6. 토마토 : 300원     ,  7. 피클 : 100원       ,  8. 베이컨 : 500원");
        System.out.println("9. 새우패티 : 1300원 ,  10. 치킨패티 : 1100원  ,  11. 양파 : 150원      ,  12. 스파이시소스 : 200원");
        System.out.println("0. 메뉴로 돌아가기");
        System.out.println(
                "---------------------------------------------------------------------------------------------------");

        while (true) {

            System.out.println("발주할 재료의 번호를 입력하세요");
            System.out.print(">> 선택 : ");
            int productNumber = scan.nextInt();

            if (productNumber == 0) {
                return;
            } else {

                System.out.println("발주할 재료의 수량을 입력하세요");
                System.out.print(">> 입력 : ");
                int productCount = scan.nextInt();

                ProductLogDTO productLogDTO = new ProductLogDTO(productNumber, productCount);

                boolean result = pc.addProductLog_order(productLogDTO);

                if (result) {
                    System.out.println("발주 성공");
                    System.out.println("-------------------------------");
                    System.out.println("구매 후 남은 자금 : " + pc.currentGold());
                    System.out.println("-------------------------------");
                } else {
                    System.out.println("발주 실패");
                }

            }

        }

    }

    // 영업 시작하기 함수
    public void startDay() {

        boolean result = pc.startDay();

        if (result == false) {
            System.out.println("영업 시작 실패");
        }

    }

    // 재고 수량 확인 함수
    public void countProductLog() {

        ArrayList<CountProductDTO> result = pc.countProductLog();

        System.out.println("no | name             | qty    ");

        for (int i = 0; i <= result.size() - 1; i++) {

            if (i == result.size() - 1) {
                System.out.printf("%-4d %s\t%d\n", result.get(i).getProduct_id(), result.get(i).getProduct_name(),
                        result.get(i).getProduct_totalQty());
            } else {
                System.out.printf("%-4d %s\t\t%d\n", result.get(i).getProduct_id(), result.get(i).getProduct_name(),
                        result.get(i).getProduct_totalQty());
            }

        }

    }

    // 게임 기록(랭킹) 추가
    public void gameRankInsert(String userName) {
        gc.gameRankInsert(userName);
    }

    // 게임 랭킹 조회
    public void gameRankList() {
        ArrayList<RankDto> list = gc.gameRankList();
        int rank = 1;
        System.out.println("rank  |  user name\t|  final gold\t| max day    ");
        for (RankDto list2 : list) {
            System.out.printf(" %2d위    %2s\t  %,8d원\t  %2d일차\n",
                    rank++, list2.getUserName(), list2.getCurrentGold(), list2.getMaxDate());
        }
    }

    // 자동 발주 묻기 함수 
    public void autoOrderAsk(){

        ArrayList<CountProductDTO> result = pc.autoOrderAsk();

        System.out.println("--------------- 주의 ---------------");

        for (int i = 0; i <= result.size() - 1; i++) {

            if (result.get(i).getProduct_id()==12) {
                System.out.printf("%s 수량이\t %d 입니다.\n",
                        result.get(i).getProduct_name(),
                        result.get(i).getProduct_totalQty());
            } else {
                System.out.printf("%s 수량이 %d 입니다.\n",
                        result.get(i).getProduct_name(),
                        result.get(i).getProduct_totalQty());
            }

        }

        // 재고 0개인 품목들 2개씩 자동구매여부 묻기

        System.out.println("해당 재료들을 2개씩 자동 구매 하시겠습니까?  ( Y/N )");

        while(true){

            System.out.print(">> 입력 : ");

            String ch = scan.next();
            if (ch.equals("Y")||ch.equals("y")) {
                pc.autoOrder();
                System.out.println("자동 구매가 완료되었습니다.");
                countProductLog();
                return;
            }else if (ch.equals("N")||ch.equals("n")) {
                return;
            }else{ System.out.println("자동구매 여부를 먼저 선택해 주세요.");}

        }

    }

    // 자동 발주 함수
    public void autoOrder(){

        boolean result = pc.autoOrder();

        if (result) {
            System.out.println("발주 성공");
            System.out.println("-------------------------------");
            System.out.println("구매 후 남은 자금 : " + pc.currentGold());
            System.out.println("-------------------------------");
        } else {
            System.out.println("발주 실패");
        }

    }



} // class END
