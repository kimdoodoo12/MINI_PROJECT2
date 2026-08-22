package screen2.controller;

import screen2.model.dao.KitchenDao;

public class KitchenController {
    private static final KitchenController instance = new KitchenController();
    private KitchenController() {}
    public static KitchenController getInstance() {
        return instance;
    }
    private KitchenDao kd = KitchenDao.getInstance();


}
