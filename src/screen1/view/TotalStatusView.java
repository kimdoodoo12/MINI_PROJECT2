package screen1.view;

import java.util.ArrayList;

import screen1.controller.TotalStatusController;
import screen1.model.dto.TotalMenuStatusDto;
import screen1.model.dto.TotalStatusDto;

public class TotalStatusView {
    private TotalStatusView() {
    }

    private static final TotalStatusView instance = new TotalStatusView();

    public static TotalStatusView getinstance() {
        return instance;
    }

    private TotalStatusController totalstac = TotalStatusController.getInstance();

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
        int totalGold = totalstac.getGold();
        int totalSales = totalstac.getSales();
        int totalExpense = totalstac.getExpense();
        int totalNetProfit = totalstac.getTotalNetProfit(totalSales, totalExpense);
        int totalCustomer = totalstac.getTotalCustomer();
        int totalServed = totalstac.getServedCustomer();
        int totalLeft = totalstac.getTotalLeftCustomer(totalCustomer, totalServed);
        int totalOrder = totalstac.getTotalOrder();
        int totalUsed = totalstac.getTotalUsed();
        int totalRemain = totalstac.getTotalRemain();

        printline();
        System.out.printf("\t\t [ DAY %1d GAME OVER - FINAL SCORE ]  ]\n", day);
        printline();
        System.out.println();
        System.out.println(" ■ [ 경영 성적 ]");
        printline2();
        if (totalGold < 0) {
            System.out.printf(" - 총 자금\t\t :  - %,7d 원\n", -totalGold);
        } else {
            System.out.printf(" - 총 자금\t\t :  + %,7d 원\n", totalGold);
        }
        System.out.printf(" - 총 매출액\t\t :  + %,7d 원\n", totalSales);
        System.out.printf(" - 총 재고 지출\t\t :  - %,7d 원\n", totalExpense);
        if (totalNetProfit < 0) {
            System.out.printf(" - 총 순이익\t\t :  - %,7d 원\n", -totalNetProfit);
        } else {
            System.out.printf(" - 총 순이익\t\t :  + %,7d 원\n", totalNetProfit);
        }

        System.out.println();
        System.out.println(" ■ [ 서비스 성적 ]");
        printline2();
        System.out.printf(" - 총 방문손님\t\t : %10d 명\n", totalCustomer);
        System.out.printf(" - 식사 완료 손님\t : %10d 명 (성공률 %.1f %%)\n", totalServed,
                (totalServed / (double) totalCustomer) * 100);
        System.out.printf(" - 놓친 손님\t\t : %10d 명\n", totalLeft);
        System.out.println();
        System.out.println(" ■ [ 재고 총계 ]");
        printline2();
        System.out.printf(" - 총 발주 수량\t\t :  %,7d 개\n", totalOrder);
        System.out.printf(" - 총 소모 수량\t\t :  %,7d 개\n", totalUsed);
        System.out.printf(" - 총 잔여 수량\t\t :  %,7d 개\n", totalRemain);
        System.out.println();
        System.out.println(" ■ [ 누적 인기 메뉴 ]");
        printline2();
        printFinalMenuSales();
        System.out.println();
        System.out.println();
        printline();
    }

    public void printFinalMenuSales() {
        ArrayList<TotalMenuStatusDto> menuList = totalstac.getMenuSales();
        int i = 0;
        for (TotalMenuStatusDto list : menuList) {
            i++;
            System.out.printf(" %d위   %-10s\t 판매: %2d개\n", i, list.getMenuName(),
                    list.getCount());
        }
    }

}
