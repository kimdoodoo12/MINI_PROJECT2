package screen2.controller;

import java.util.ArrayList;

import screen2.model.dao.ProductDAO;
import screen2.model.dto.CountProductDTO;
import screen2.model.dto.ProductDTO;
import screen2.model.dto.ProductLogDTO;
import screen2.view.ProductView;

public class ProductController{
    
    private ProductController(){};
    private static final ProductController instance = new ProductController();
    public static ProductController getInstance(){return instance;}

    private ProductDAO pd = ProductDAO.getInstance();

    
    // 재고 발주 로그 추가 함수
    public boolean addProductLog_order(ProductLogDTO productLogDTO){
        
        boolean result = pd.addProductLog_order(productLogDTO);

        return true;

    }

    // 재료 가격 조회 함수
    public ArrayList<ProductDTO> findProductLog(){

        ArrayList<ProductDTO> result = pd.findProductLog();
        
        return result;

    }

    // 자본 금액 조회 함수
    public int currentGold(){

        int result = pd.currentGold();

        return result;
    }

    // 재료 발주 금액 자본 차감 함수
    public boolean buyProductLog(){

        Boolean result = pd.buyProductLog();

        return result;

    }

    // 일차 조회 함수
    public int currentDay(){

        int result = pd.currentDay();

        return result;        
    }

    // 영업 시작하기 함수
    public boolean startDay(){

        boolean result = pd.startDay();

        return result;

    }

    // 재고 확인 함수
    public ArrayList<CountProductDTO> countProductLog(){

        ArrayList<CountProductDTO> result = pd.countProductLog();

        return result;
    }





}