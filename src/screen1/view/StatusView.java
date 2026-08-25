package screen1.view;

import java.util.ArrayList;

import screen1.controller.StatusController;
import screen1.model.dto.MenuStatusDto;
import screen1.model.dto.ProductStatusDto;
import screen1.model.dto.StatusDto;

public class StatusView {
    private StatusView() {
    }

    private static final StatusView instance = new StatusView();

    public static StatusView getinstance() {
        return instance;
    }

    private StatusController stac = StatusController.getInstance();

    public static int day = 0;

    public void printline() {
        String result = "";
        for (int i = 0; i <= 73; i++) {
            result += "=";
        }
        System.out.println(result);
    }

    public void printline2() {
        String result = "";
        for (int i = 0; i <= 73; i++) {
            result += "─";
        }
        System.out.println(result);
    }

    public void printline3() {
        String result = "";
        for (int i = 0; i <= 73; i++) {
            result += "-";
        }
        System.out.println(result);
    }

    public void printDailySatus() {
        StatusDto stadto = stac.clacDailyStatus();
        int day = stadto.getDay();
        printline();
        System.out.printf("\t\t [ DAY %1d - DAILY REPORT & STATS  ]\n", day);
        printline();
        System.out.println();
        System.out.println(" ■ [ 영업 총평 및 매출 요약 ]");
        printline2();
        System.out.printf(" - 총 매출액\t\t :  + %,7d 원\n", stadto.getSales());
        System.out.printf("- 재고 지출\t\t :  + %,7d 원\n", stadto.getExpense());
        System.out.printf("- 일일 순이익\t\t :  + %,7d 원\n", stadto.getNetProfit());
        printline2();
        System.out.printf(" - 총 방문손님\t\t : %10d 명\n", stadto.getTotalCustomer());
        System.out.printf(" - 식사 완료 손님\t : %10d 명\n", stadto.getServedCustomer());
        System.out.printf(" - 놓친 손님\t\t : %10d 명\n", stadto.getLeftCustomer());
        System.out.println();
        System.out.println(" ■ [ 인기 메뉴 및 재고 소모 현황 ]");
        printline2();
        printDailyMenuSales(day);
        printline2();
        printDailyProductStatus(day);
        System.out.println();
        System.out.println();
        printline();
    }

    public void printDailyMenuSales(int day) {
        ArrayList<MenuStatusDto> menuList = stac.getMenuSales(day);
        for (MenuStatusDto list : menuList) {
            System.out.printf(" - %-10s\t     판매:  %2d개\n", list.getMenuName(), list.getCount());
        }
    }

    public void printDailyProductStatus(int day) {
        ArrayList<ProductStatusDto> productList = stac.getProductStatus(day);
        // System.out.println(productc.getProductStatus(day));
        for (ProductStatusDto list : productList) {
            String judge = stac.judgeProduct(list.getRemain(), list.getUsed());
            System.out.printf(" - %-5s    |   소모 재고:  %2d개   |   잔여재고 :  %2d개 (%s)\n",
                    list.getProductName(),
                    list.getUsed(),
                    list.getRemain(),
                    judge);

        }
    }

}
