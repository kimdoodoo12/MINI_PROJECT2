package screen2.view;

import screen2.controller.KitchenController;
import screen2.controller.ProductController;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class KitchenView {
    private static final KitchenView instance = new KitchenView();
    private KitchenView(){}
    public static KitchenView getInstance() {
        return instance;
    }
    private KitchenController kc = KitchenController.getInstance();

    private ProductController pd = ProductController.getInstance();

    Scanner sc = new Scanner(System.in);
    public void run(){
        int currentDay = pd.currentDay();
        System.out.println("======================================================================");
        System.out.printf("        [ DAY %d - 영업 시작  ]                   \n", currentDay);
        System.out.println("======================================================================");
        while (true){
            boolean isOpen = kc.checkRestaurantState();
            if (!isOpen){
                System.out.println("** 영업이 종료되어 발주 화면으로 이동합니다 **");
                return;
            }
            System.out.println("1.음식 조리 2.레시피 확인 3.종료");
            System.out.print(">> 선택 : ");
            String ch = sc.next();
            System.out.println("=================================================================");
            switch (ch){
                case "1":
                    cook(); // 음식 조리
                    break;
                case "2":
                    findAllRecipes(); // 레시피 확인
                    break;
                case "3":
                    System.out.println("종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 입력(1~3)");
            }

        }
    }

    private void findAllRecipes() {
        Map<String, ArrayList<String>> allRecipes = kc.findAllRecipes();
        for (String menuName : allRecipes.keySet()) {
            System.out.println("[" + menuName + "]");
            ArrayList<String> list = allRecipes.get(menuName); // 해당 menu의 재료가 담긴 list
            String result = "";
            for (int i = 0; i < list.size(); i++) {
                result += list.get(i);
                if (i < list.size()-1){ // 마지막은 -> 빼기
                    result += " -> ";
                }
            }
            System.out.println(result);

        }
        System.out.println("=================================================================");
    }

    private void cook() {
        while (true){
            System.out.println("어떤메뉴를 만드시겠습니까? (0 입력시 메인메뉴)");
            System.out.println("1.치즈버거 2.불고기버거 3.더블불고기버거 4.새우버거 5.베이컨치즈버거");
            System.out.println("6.클래식버거 7.더블치즈버거 8.스파이시버거 9.치킨버거 10.프리미엄버거");
            System.out.print(">> 선택 : ");
            int menuChoice = sc.nextInt();
            System.out.println("=================================================================");
            if (menuChoice == 0){
                return;
            }
            if (menuChoice >= 1 && menuChoice <= 10){
                // 만들 메뉴 입력시 sql조회해서 영업중인 확인
                boolean isOpen1 = kc.checkRestaurantState();
                if (!isOpen1){
                    return;
                }

                ArrayList<Integer> productList = new ArrayList<>();
                System.out.println("[재료 선택]");
                System.out.println("1.햄버거빵 2.소고기패티 3.불고기패티 4.치즈 5.양상추 6.토마토");
                System.out.println("7.피클 8.베이컨 9.새우패티 10.치킨패티 11.양파 12.스파이시소스 0.완료");

                while (true){
                    System.out.print(">> 순서대로 번호 입력(0 입력시 완료) : ");
                    int productChoice = sc.nextInt();

                    // 재료번호 0~12 입력하지 않을때 예외발생
                    if (productChoice < 0 || productChoice > 12){
                        System.out.println("잘못된 재료 선택(0~12)");
                        continue;
                    }

                    // 재료번호 0번 입력시
                    if(productChoice == 0){
                        // 만들 메뉴 입력시 sql조회해서 영업중인 확인
                        boolean isOpen2 = kc.checkRestaurantState();
                        if (!isOpen2){
                            return;
                        }
                        ArrayList<Integer> recipeList = kc.takeRecipe(menuChoice);// db에서 해당 메뉴번호 레시피 가져오기
                        boolean result = kc.checkRecipe(productList, recipeList);
                        if(result){
                            System.out.println("** 요리가 완성되었습니다 **");
                            kc.addCookTable(menuChoice, "READY");
                            kc.clearProductList(productList);
                            return;
                        } else {
                            System.out.println("** 재료가 맞지 않습니다 **");
                            kc.clearProductList(productList);
                            return;
                        }
                    }

                    // 재료번호 1~12 입력시
                    boolean result = kc.checkProductQty(productChoice);  // 입력받을때마다 재고 확인
                    if(!result){
                        System.out.println("** 재고 부족 **");
                        continue;
                    }

                    kc.addProductLogUsed(productChoice);
                    productList = kc.addProductList(productChoice);
                }
            } else {
                System.out.println("잘못된 메뉴 선택(1~10)");
            }
        }
    }
}
