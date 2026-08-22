package screen2.model.dao;

public class KitchenDao extends IBaseDao{
    private static final  KitchenDao instance = new KitchenDao();
    private KitchenDao (){};
    public static KitchenDao getInstance() {
        return instance;
    }
}
