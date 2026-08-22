package screen2.view;

import screen2.controller.KitchenController;

public class KitchenProductView1 {
    private static final KitchenProductView1 instance = new KitchenProductView1();
    private KitchenProductView1(){}
    public static KitchenProductView1 getInstance() {
        return instance;
    }
    private KitchenController kc = KitchenController.getInstance();
}
