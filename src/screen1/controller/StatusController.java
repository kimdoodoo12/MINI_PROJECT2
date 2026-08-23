package screen1.controller;

import screen1.model.dao.StatusDao;

public class StatusController {
    StatusDao stad = new StatusDao();

    // 일차 가져오기
    public int getDay() {
        int result = stad.getDay();
        return result;
    }

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
}
