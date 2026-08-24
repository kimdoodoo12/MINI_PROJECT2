package screen2.controller;

import screen2.model.dao.ProductDAO;
import screen2.model.dto.ProductDTO;
import screen2.view.ProductView;

public class ProductController{
    
    private ProductController(){};
    private static final ProductController instance = new ProductController();
    public static ProductController getInstance(){return instance;}

    private ProductDAO pd = ProductDAO.getInstance();


    public boolean addProductLog_order(ProductDTO productDTO){

        boolean result = pd.addProductLog_order(productDTO);


        return true;
    }

}