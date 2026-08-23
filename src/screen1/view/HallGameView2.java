package screen1.view;

import java.util.ArrayList;

import screen1.controller.HallController;
import screen1.model.dao.CustomerDao;
import screen1.model.dao.MenuDao;
import screen1.model.dto.CustomerDto;

public class HallGameView2 {
    private HallGameView2(){} // 1.
    private static final HallGameView2 instance = new HallGameView2(); // 2.
    public static HallGameView2 getInstance( ){ return instance; } // 3.

    private HallController hc = HallController.getInstance();
    private CustomerDao cd = CustomerDao.getInstance();
    private MenuDao md = MenuDao.getInstance();

    public void run(){

        hc.startService();
        while (cd.isOpen()){
            showCustomer();
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }
    }

    public void showCustomer(){
        ArrayList<CustomerDto> customers = cd.findAllCustomer();

        System.out.println("========================================");
        System.out.println("        [ 홀 현황 - 대기 손님 목록 ]");
        System.out.println("========================================");

        if (customers.isEmpty()){
            System.out.println("현재 대기 중인 손님이 없습니다.");
        } else {
            System.out.printf("%-8s %-8s %-10s %-6s%n", "손님번호", "메뉴ID", "예상금액", "일차");
            System.out.println("----------------------------------------");
            for (CustomerDto customer : customers){
                int price = md.getPrice(customer.getMenu_id());
                System.out.printf("%-8d %-8d %-10d %-6d%n",
                    customer.getCustomer_no(),
                    customer.getMenu_id(),
                    price,
                    customer.getCustomer_log_day());
            }
        }

        System.out.println("----------------------------------------");
        System.out.println("총 대기 손님 수: " + customers.size() + "명");
        System.out.println("========================================");
    }
}
