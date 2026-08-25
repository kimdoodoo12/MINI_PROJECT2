package screen2;

import screen2.controller.ProductController;
import screen2.view.KitchenProductView1;
import screen2.view.ProductView;

public class AppStart {
    public static void main(String[] args) {

        while(true){

            if (ProductView.getInstance().checkState()){KitchenProductView1.getInstance().run();}
            else{ProductView.getInstance().run();}
        }
    
    }
    
}
