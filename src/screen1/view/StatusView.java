package screen1.view;

import java.security.PublicKey;
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

        int day = stac.getDay();
        int sales = stac.getSales(day);
        int expense = stac.getExpense(day);
        int netProfit = stac.getNetProfit(sales, expense) - 20000;
        int customer = stac.getTotalCustomer(day);
        int served = stac.getServedCustomer(day);
        int left = stac.getLeftCustomer(customer, served);
        ArrayList<MenuStatusDto> menuList = stac.getMenuSales(day);
        ArrayList<ProductStatusDto> productList = stac.getProductStatus(day);

        printline();
        System.out.printf("\t\t [ DAY %1d - DAILY REPORT & STATS  ]\n", day);
        printline();
        System.out.println();
        System.out.println(" ■ [ 영업 총평 및 매출 요약 ]");
        printline2();
        System.out.printf(" - 총 매출액\t\t :  + %,7d 원\n", sales);
        System.out.printf(" - 재고 지출\t\t :  - %,7d 원\n", expense);
        System.out.printf(" - 관리비\t\t :  - %,7d 원\n", 20000); // 추후 차감계산 메소드 대입
        if (netProfit < 0) {
            System.out.printf(" - 일일 순이익\t\t :  - %,7d 원\n", -netProfit);
        } else {
            System.out.printf(" - 일일 순이익\t\t :  + %,7d 원\n", netProfit);
        }

        printline2();
        System.out.printf(" - 총 방문손님\t\t : %10d 명\n", customer);
        System.out.printf(" - 식사 완료 손님\t : %10d 명\n", served);
        System.out.printf(" - 놓친 손님\t\t : %10d 명\n", left);
        System.out.println();

        if (menuList.size() > 0 || productList.size() > 0) {
            System.out.println(" ■ [ 판매 메뉴 및 재고 소모 현황 ]");
            printline2();
            for (MenuStatusDto list : menuList) {
                System.out.printf(" - %-10s\t     판매:  %2d개\n", list.getMenuName(), list.getCount());
            }
            printline2();
            for (ProductStatusDto list : productList) {
                String judge = stac.judgeProduct(list.getRemain(), list.getUsed());
                System.out.printf(" - %-5s    |   소모 재고:  %2d개   |   잔여재고 :  %2d개 (%s)\n",
                        list.getProductName(),
                        list.getUsed(),
                        list.getRemain(),
                        judge);

            }
        }
        System.out.println();
        System.out.println();
        printline();
    }

}
