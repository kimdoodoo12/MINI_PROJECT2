package screen2.controller;

import screen2.model.dao.KitchenDao;

import java.util.ArrayList;

public class KitchenController {
    private static final KitchenController instance = new KitchenController();
    private KitchenController() {}
    public static KitchenController getInstance() {
        return instance;
    }
    private KitchenDao kd = KitchenDao.getInstance();

    ArrayList<Integer> productList = new ArrayList<>();

    public ArrayList<Integer> addProductList(int productChoice) {
        productList.add(productChoice);
        return productList;
    }

    public void takeRecipe(int menuChoice) {
        kd.takeRecipe(menuChoice);
    }
}
