package screen2.view;

import screen2.controller.KitchenController;

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
                System.out.println(">> 선택 : ");
                String ch = sc.next();
            switch (ch){
                case "1":
                    cook();
                    break;
                case "2":
                    // 재고확인
                    break;
                case "3":
                    System.out.println("종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다(1~3)");
            }

        }
    }

    private void cook() {
    }
}
