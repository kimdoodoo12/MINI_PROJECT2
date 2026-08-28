package screen2.controller;

import java.util.ArrayList;

import screen2.model.dao.ProductDAO;
import screen2.model.dto.CountProductDTO;
import screen2.model.dto.ProductDTO;
import screen2.model.dto.ProductLogDTO;

public class ProductController{
    
    private ProductController(){};
    private static final ProductController instance = new ProductController();
    public static ProductController getInstance(){return instance;}

    private ProductDAO pd = ProductDAO.getInstance();

    
    // 재고 발주 로그 추가 함수
    public boolean addProductLog_order(ProductLogDTO productLogDTO) throws Exception{
        
        // 현재 자금 불러오기
        int currentGold = pd.currentGold();

        // 현재 productlog 불러오기
        ArrayList<ProductDTO> productDTO = pd.findProductLog();

        // 유효성 검사 : 재료 주문금액이 자본을 초과하지 않는지.
        for(int i = 0 ; i <= productDTO.size()-1 ; i++){

            if (productDTO.get(i).getProduct_no() == productLogDTO.getProduct_id()) {

                if (currentGold < productDTO.get(i).getProduct_price()*productLogDTO.getProduct_qty()) {

                    System.out.println("해당 재료를 수량에 맞게 주문하기에 자금이 부족합니다.");

                    return false;

                }

            }

        }

        boolean result = pd.addProductLog_order(productLogDTO);

        return result;

    }


    // 재료 가격 조회 함수
    public ArrayList<ProductDTO> findProductLog (){

        ArrayList<ProductDTO> result = pd.findProductLog();
        
        return result;

    }


    // 자본 금액 조회 함수
    public int currentGold(){

        int result = pd.currentGold();

        return result;

    }


    // 재료 발주 금액 자본 차감 함수
    public boolean buyProductLog() {

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


    // 영업 상태 확인 함수
    public boolean checkState(){

        boolean result = pd.checkState();

        return result;

    }


    // 영업 일차 증가 함수
    public boolean addDay(){

        boolean result = pd.addDay();
        
        return result;
    }


    // 초기 자금 설정 함수
    public boolean setGold(int current_gold){ 

        boolean result = pd.setGold(current_gold);

        return result;
    }
    

    // 게임 초기화 설정 함수
    public boolean reset(){

        boolean result = pd.reset();

        return result;
    }


    // 자동 발주 묻기 함수 
    public ArrayList<CountProductDTO> autoOrderAsk(){

        ArrayList<CountProductDTO> list = pd.countProductLog();

        // 재고 체크 = 0 개인 거 확인

        for(int i = list.size()-1 ; i >= 0 ; i--){

            if (list.get(i).getProduct_totalQty() != 0) {

                list.remove(i);
                
            }

        }

        // 자동구매여부에 따라 출금 or 다음 화면으로 넘어가기

        return list;
    }

    // 자동 발주 함수 
    public boolean autoOrder()throws Exception{
    
        boolean result = false;

        ArrayList<CountProductDTO> list = autoOrderAsk();

        for(int i = 0 ; i <= list.size()-1 ; i++){ 

            ProductLogDTO productLogDTO = new ProductLogDTO();

            productLogDTO.setProduct_id(list.get(i).getProduct_id());
            productLogDTO.setProduct_qty(2);

            result = addProductLog_order(productLogDTO);

        }

        return result;

    }

}