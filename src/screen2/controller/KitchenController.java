package screen2.controller;

import screen2.model.dao.KitchenDao;

import java.util.ArrayList;
import java.util.Map;

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

    public ArrayList<Integer> takeRecipe(int menuChoice) {
        return kd.takeRecipe(menuChoice);
    }

    public boolean checkRecipe(ArrayList<Integer> productList, ArrayList<Integer> recipeList) {
        return productList.equals(recipeList);
    }

    public void addCookTable(int menuChoice, String state) {
        kd.addCookTable(menuChoice, state);
    }

    public void addProductLogUsed(int productChoice) {
        kd.addProductLogUsed(productChoice);
    }

    public Map<String, ArrayList<String>> findAllRecipes() {
        return kd.findAllRecipes();
    }

    public boolean checkProductQty(int productChoice) {
        return kd.checkProductQty(productChoice);
    }
}
