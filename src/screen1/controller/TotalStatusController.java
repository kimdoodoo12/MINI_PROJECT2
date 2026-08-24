package screen1.controller;

import screen1.model.dao.TotalStatusDao;
import screen1.model.dto.TotalStatusDto;

public class TotalStatusController {
    private TotalStatusController() {
    }

    private static final TotalStatusController instance = new TotalStatusController();

    public static TotalStatusController getInstance() {
        return instance;
    }

    private TotalStatusDao totalStad = TotalStatusDao.getInstance();

    // [0] 게임 정보 로직

    // 일차 가져오기
    public int getDay() {
        int result = totalStad.getDay();
        return result;
    }

    // 최종 자금 가져오기
    public int getGold() {
        int result = totalStad.getGold();
        System.out.println(result);
        return result;
    }

    // [1] 매출 로직

    // 전체 매출 가져오기
    public int getSales() {
        int result = totalStad.getTotalSales();
        return result;
    }

    // 전체 지출 가져오기
    public int getExpense() {
        int result = totalStad.getTotalExpense();
        return result;
    }

    // [2] 손님 로직

    // 전체 총 방문 손님 가져오기
    public int getTotalCustomer() {
        int result = totalStad.getTotalCustomer();
        // System.out.println("컨트롤러 총 방문손님 :" + result);
        System.out.println(result);
        return result;
    }

    // 전체 서빙된 손님 가져오기
    public int getServedCustomer() {
        int result = totalStad.getTotalServed();
        // System.out.println("컨트롤러 서빙손님 :" + result);
        return result;
    }

    // 컨트롤러에서 전부 모아서 뷰에게 전달.
    public TotalStatusDto clacFianlStatus() {
        int finalDay = getDay();
        int finalGold = getGold();
        int totalSales = getSales();
        int totalExpense = getExpense();
        int totalCustomer = getTotalCustomer();
        int totalServed = getServedCustomer();

        return new TotalStatusDto(finalDay, finalGold, totalSales, totalExpense, totalCustomer, totalServed);
    }
}
