package screen1.view;

import screen1.controller.StatusController;

public class StatusView {

    private static final StatusView instance = new StatusView();

    public static StatusView getinstance() {
        return instance;
    }

    private StatusController stac = new StatusController();

    public static int day = 0;

    public void printline() {
        String result = "";
        for (int i = 0; i <= 67; i++) {
            result += "=";
        }
        result += "\n";
        System.out.println(result);
    }

    // 일차
    public void getDay() {
        day = stac.getDay();
        System.out.printf("                 [ DAY %d - DAILY REPORT & STATS ]\n", day);
    }

    // 매출
    public void getSales() {
        int result = stac.getSales(day);
        System.out.printf(" - 총 매출액 \t\t : + %,10d 원 \n", result);
    }

    // 지출
    public void getExpense() {
        int result = stac.getExpense(day);
        System.out.printf(" - 재고 지출 \t\t : - %,10d 원 \n", result);
    }
}
