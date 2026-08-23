package screen1.view;

import java.util.ArrayList;

import screen1.controller.MenuController;
import screen1.controller.StatusController;
import screen1.model.dto.MenuStatusDto;
import screen1.model.dto.StatusDto;

public class StatusView {
    private StatusView() {
    }

    private static final StatusView instance = new StatusView();

    public static StatusView getinstance() {
        return instance;
    }

    private StatusController stac = StatusController.getInstance();
    private MenuController menuc = MenuController.getInstance();

    public static int day = 0;

    public void printline() {
        String result = "";
        for (int i = 0; i <= 67; i++) {
            result += "=";
        }
        result += "\n";
        System.out.println(result);
    }

    public void printDailySatus() {
        StatusDto stadto = stac.clacDailyStatus();
        int day = stadto.getDay();

        System.out.println("일차 : " + day);
        System.out.println("매출 : " + stadto.getSales());
        System.out.println("지출 : " + stadto.getExpense());
        System.out.println("순이익 : " + stadto.getNetProfit());
        System.out.println("총 방문손님 : " + stadto.getTotalCustomer());
        System.out.println("식사 완료 손님 : " + stadto.getServedCustomer());
        System.out.println("놓친 손님 : " + stadto.getLeftCustomer());
        printDailyMenuSales(day);

    }

    public void printDailyMenuSales(int day) {
        ArrayList<MenuStatusDto> menuList = menuc.getMenuSales(day);
        for (MenuStatusDto list : menuList) {
            System.out.printf("팔린 메뉴 : " + list.getMenuName());
            System.out.println(" 메뉴 개수 : " + list.getCount());
        }
    }

}
