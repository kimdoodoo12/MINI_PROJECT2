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

    // 순서가 처음으로 어긋난 인덱스를 반환 , 재료 개수가 다르면 짧은 쪽 길이를 반환, 다 맞으면 -1
    public int findMissmatch(ArrayList<Integer> productList, ArrayList<Integer> recipeList) {
        int size = productList.size() < recipeList.size() ? productList.size() : recipeList.size();

        for (int i = 0; i < size; i++) {
            if (!productList.get(i).equals(recipeList.get(i))) {
                return i;
            }
        }
        // 재료순서는 맞는데 재료 개수가 부족하면 길이를 반환
        return productList.size() != recipeList.size() ? size : -1;
    }

    public void addCookTable(int menuChoice, String state) {
        kd.addCookTable(menuChoice, state);
    }

    public void addProductLogUsed(int menuChoice) {
        kd.addProductLogUsed(menuChoice);
    }

    public Map<String, ArrayList<String>> findAllRecipes() {
        return kd.findAllRecipes();
    }

    public boolean checkProductQty(int productChoice) {
        return kd.checkProductQty(productChoice);
    }

    public boolean checkRestaurantState() {
        return kd.checkRestaurantState();
    }

    public void clearProductList(ArrayList<Integer> productList) {
        productList.clear();
    }


}
