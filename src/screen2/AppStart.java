package screen2;

import screen2.controller.ProductController;
import screen2.view.KitchenView;
import screen2.view.ProductView;

public class AppStart {
    public static void main(String[] args) {

        while(true){

            if (ProductView.getInstance().checkState()){KitchenView.getInstance().run();}
            else{ProductView.getInstance().run();}
        }
    
    }
    
}
