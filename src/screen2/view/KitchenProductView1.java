package screen2.view;

import screen2.controller.KitchenController;

import java.util.ArrayList;
import java.util.Scanner;

public class KitchenProductView1 {
    private static final KitchenProductView1 instance = new KitchenProductView1();
    private KitchenProductView1(){}
    public static KitchenProductView1 getInstance() {
        return instance;
    }
    private KitchenController kc = KitchenController.getInstance();

    Scanner sc = new Scanner(System.in);
    public void run(){
        while (true){
                System.out.println("1.음식 조리 2.재고 확인 3.종료");
                System.out.print(">> 선택 : ");
                String ch = sc.next();
                System.out.println("=================================================================");
            switch (ch){
                case "1":
                    cook(); // 음식 조리
                    break;
                case "2":
                    // 재고 확인
                    break;
                case "3":
                    System.out.println("종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 입력(1~3)");
            }

        }
    }

    private void cook() {
        System.out.println("어떤메뉴를 만드시겠습니까?");
        System.out.println("1.치즈버거 2.불고기버거 3.더블불고기버거 4.새우버거 5.베이컨치즈버거");
        System.out.println("6.클래식버거 7.더블치즈버거 8.스파이시버거 9.치킨버거 10.프리미엄버거");
        System.out.print(">>선택 : ");
        int menuChoice = sc.nextInt();
        System.out.println("=================================================================");
        if (menuChoice >= 1 && menuChoice <= 10){
            while (true){
                System.out.println("[재료 선택]");
                System.out.println("현재 재료");
                System.out.println("1.햄버거빵 2.소고기패티 3.불고기패티 4.치즈 5.양상추 6.토마토");
                System.out.println("7.피클 8.베이컨 9.새우패티 10.치킨패티 11.양파 12.스파이시소스 0.완료");
                System.out.print(">>순서대로 번호 입력 : ");
                int productChoice = sc.nextInt();
                if (productChoice < 0 || productChoice > 12){
                    System.out.println("잘못된 입력(0~12)");
                    continue;
                }
                if (productChoice >= 0 && productChoice <= 12){
                    // 재료로그추가 메소드 추가
                    ArrayList<Integer> productList = kc.addProductList(productChoice);// 재료list에 담기
                    if(productChoice == 0){
                        ArrayList<Integer> recipeList = kc.takeRecipe(menuChoice);// db에서 해당 메뉴번호 레시피 가져오기
                        boolean result = kc.checkRecipe(productList, recipeList);
                        if(result){
                            System.out.println("요리가 완성되었습니다.");
                        } else {
                            System.out.println("재료가 맞지 않습니다.");
                        }
                    }
                }
            }
        } else {
            System.out.println("잘못된 입력(1~10)");
        }

    }

}
