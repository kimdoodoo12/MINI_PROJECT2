package screen1.view;

import java.util.ArrayList;

import screen1.controller.TotalStatusController;
import screen1.model.dto.StatusDto;
import screen1.model.dto.TotalStatusDto;

public class TotalStatusView {
    private TotalStatusView() {
    }

    private static final TotalStatusView instance = new TotalStatusView();

    public static TotalStatusView getinstance() {
        return instance;
    }

    private TotalStatusController totalstac = TotalStatusController.getInstance();
    // private TotalMenuController menuc = TotalMenuController.getInstance();
    // private TotalProductStatusController productc =
    // TotalProductStatusController.getInstance();

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

    public void printFinalSatus() {
        TotalStatusDto stadto = totalstac.clacFianlStatus();
        int day = stadto.getTotalDays();
        printline();
        System.out.printf("\t\t [ DAY %1d - DAILY REPORT & STATS  ]\n", day);
        printline();
        System.out.println();
        System.out.println(" ■ [ 영업 총평 및 매출 요약 ]");
        printline2();
        System.out.printf(" - 총 자금\t\t :  + %,7d 원\n", stadto.getFinalGold());
        System.out.printf(" - 총 매출액\t\t :  + %,7d 원\n", stadto.getTotalSales());
        System.out.printf("- 재고 지출\t\t :  + %,7d 원\n", stadto.getTotalExpense());
        System.out.printf("- 일일 순이익\t\t :  + %,7d 원\n", stadto.getTotalNetProfit());
        printline2();
        System.out.printf(" - 총 방문손님\t\t : %10d 명\n", stadto.getTotalCustomer());
        System.out.printf(" - 식사 완료 손님\t : %10d 명\n", stadto.getTotalServed());
        System.out.printf(" - 놓친 손님\t\t : %10d 명\n", stadto.getTotalLeftCustomer());
        System.out.println();
        System.out.println(" ■ [ 인기 메뉴 및 재고 소모 현황 ]");
        // printline2();
        // printDailyMenuSales(day);
        // printline2();
        // printDailyProductStatus(day);
        System.out.println();
        System.out.println();
        printline();
    }

    // public void printFinalMenuSales(int day) {
    // ArrayList<MenuStatusDto> menuList = totalstac.getMenuSales(day);
    // for (MenuStatusDto list : menuList) {
    // System.out.printf(" - %-10s\t 판매: %2d개\n", list.getMenuName(),
    // list.getCount());
    // }
    // }

    // public void printFinalProductStatus(int day) {
    // ArrayList<ProductStatusDto> productList = productc.getProductStatus(day);
    // // System.out.println(productc.getProductStatus(day));
    // for (ProductStatusDto list : productList) {
    // String judge = productc.judgeProduct(list.getRemain(), list.getUsed());
    // System.out.printf(" - %-5s | 소모 재고: %2d개 | 잔여재고 : %2d개 (%s)\n",
    // list.getProductName(),
    // list.getUsed(),
    // list.getRemain(),
    // judge);

    // }
    // }
}
