package screen1.controller;

import java.io.ObjectInputFilter.Status;
import java.util.ArrayList;

import screen1.model.dao.MenuStatusDao;
import screen1.model.dao.ProductStatusDao;
import screen1.model.dao.StatusDao;
import screen1.model.dto.MenuStatusDto;
import screen1.model.dto.ProductStatusDto;
import screen1.model.dto.StatusDto;

public class StatusController {
    private StatusController() {
    }

    private static final StatusController instance = new StatusController();

    public static StatusController getInstance() {
        return instance;
    }

    private StatusDao stad = StatusDao.getInstance();
    private ProductStatusDao productd = ProductStatusDao.getInstance();
    private MenuStatusDao menud = MenuStatusDao.getInstance();

    // [0] 게임 정보 로직

    // 일차 가져오기
    public int getDay() {
        int result = stad.getDay();
        return result;
    }

    // [1] 매출 로직

    // 매출 가져오기
    public int getSales(int day) {
        int result = stad.getSales(day);
        return result;
    }

    // 지출 가져오기
    public int getExpense(int day) {
        int result = stad.getExpense(day);
        return result;
    }

    // 순이익 계산
    public int getNetProfit(int sales, int productExpense) {
        int result = sales - productExpense;
        return result;
    }

    // [2] 손님 로직

    // 총 방문 손님 가져오기
    public int getTotalCustomer(int day) {
        int result = stad.getTotalCustomer(day);
        // System.out.println("컨트롤러 총 방문손님 :" + result);
        return result;
    }

    // 서빙된 손님 가져오기
    public int getServedCustomer(int day) {
        int result = stad.getServed(day);
        // System.out.println("컨트롤러 서빙손님 :" + result);
        return result;
    }

    // 떠난 손님 계산
    public int getLeftCustomer(int totalCustomer, int servedCustomer) {
        return totalCustomer - servedCustomer;
    }

    // 재고명 + 사용량 + 잔여량 가져오기 및 병합
    public ArrayList<ProductStatusDto> getProductStatus(int day) {
        ArrayList<ProductStatusDto> usedList = productd.getUsed(day);
        ArrayList<ProductStatusDto> remainList = productd.getRemain(day);
        ArrayList<ProductStatusDto> result = new ArrayList<>();

        for (ProductStatusDto used2 : usedList) {
            int remain = 0;
            String pName = used2.getProductName();
            int used = used2.getUsed();
            for (ProductStatusDto remain2 : remainList) {
                if (used2.getProductName().equals(remain2.getProductName())) {
                    // System.out.println("조건 걸림");
                    remain = remain2.getRemain();
                }

            }
            ProductStatusDto productStatusDto = new ProductStatusDto(pName, used, remain);
            // System.out.println("컨트롤러 출력");
            // System.out.println(pName);
            // System.out.println(used);
            // System.out.println(remain);
            // System.out.println(productStatusDto);
            result.add(productStatusDto);
        }
        // System.out.println("컨트롤러 객체 " + result);
        return result;
    }

    public String judgeProduct(int remain, int used) {
        if (remain >= used * 2) {
            return "여유";
        }
        if (remain < used) {
            return "위험";
        }
        if (remain < used * 2) {
            return "여유";
        }
        return "여유";
    }

    // 팔린 메뉴 이름, 수량 가져오기

    public ArrayList<MenuStatusDto> getMenuSales(int day) {
        ArrayList<MenuStatusDto> result = menud.getMenuSales(day);
        return result;
    }
}
