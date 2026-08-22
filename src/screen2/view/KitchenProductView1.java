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
            System.out.println("만들 음식 선택 : 1.");
        }
    }
}
