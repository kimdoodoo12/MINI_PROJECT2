package screen2;

import screen2.controller.ProductController;
import screen2.view.KitchenView;
import screen2.view.ProductView;

public class AppStart {

    public static void main(String[] args) {

        while(true){

            // GameState 게임상태가 True(ON) 이면 Kitchen 프로그램 실행.
            if (ProductController.getInstance().checkState()){KitchenView.getInstance().run();}

            // GameState 게임상태가 flase(OFF) 이면 product 프로그램 실행.
            else{ProductView.getInstance().run();}
            
        }
    
    }
    
}
