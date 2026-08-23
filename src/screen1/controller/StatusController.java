package screen1.controller;

import screen1.model.dao.StatusDao;
import screen1.model.dto.StatusDto;

public class StatusController {
    StatusDao stad = new StatusDao();

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

    // [2] 손님 로직

    // 총 방문 손님 가져오기
    public int getTotalCustomer(int day) {
        int result = stad.getTotalCustomer(day);
        return result;
    }

    // 서빙된 손님 가져오기
    public int getServedCustomer(int day) {
        int result = stad.getServed(day);
        return result;
    }

    // 컨트롤러에서 전부 모아서 뷰에게 전달.
    public StatusDto clacDailyStatus() {
        int day = stad.getDay();
        int sales = stad.getSales(day);
        int expense = stad.getExpense(day);
        int totalCustomer = stad.getTotalCustomer(day);
        int servedCustomer = stad.getServed(day);

        return new StatusDto(day, sales, expense, totalCustomer, servedCustomer);
    }
}
