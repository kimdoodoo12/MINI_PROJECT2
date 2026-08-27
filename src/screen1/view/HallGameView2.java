package screen1.view;

import java.util.ArrayList;

import screen1.controller.HallController;
import screen1.controller.TotalStatusController;
import screen1.model.dto.CustomerDto;

public class HallGameView2 {
    private HallGameView2() {
    } // 1.

    private static final HallGameView2 instance = new HallGameView2(); // 2.

    public static HallGameView2 getInstance() {
        return instance;
    } // 3.

    private HallController hc = HallController.getInstance();
    private TotalStatusController tc = TotalStatusController.getInstance();

    public void run() throws InterruptedException {
        while (true) { // ─ while ① 하루를 반복 (게임 전체)

            while (!hc.isOpen()) { // ─ while ② ON 될 때까지 대기
                Thread.sleep(1000);
            }

            hc.gameStart(); // 40초 타이머 기동
            hc.startService(); // 서빙 + 손님생성 기동
            HallController.isChange = true;
            HallController.isOpen = true;

            while (HallController.isOpen) { // ─ while ③ 영업중 화면
                if (HallController.isChange) {
                    showCustomer();
                    HallController.isChange = false;
                }
                Thread.sleep(1000); //
            }

            // while ③ 탈출 = 40초 끝나서 DB가 OFF 됨

            if (tc.getGold() < 0) {
                TotalStatusView.getinstance().printFinalSatus();
                break; // ← while ① 탈출 = 게임 종료
            }
            StatusView.getinstance().printDailySatus();

            // 다시 while ② 로 → 다른 프로세스가 ON 해줄 때까지 대기
        }

    }

    public void showCustomer() {
        ArrayList<CustomerDto> customers = hc.findAllCustomer();

        System.out.println("========================================");
        System.out.println("        [ 홀 현황 - 대기 손님 목록 ]");
        System.out.println("========================================");

        if (customers.isEmpty()) {
            System.out.println("현재 대기 중인 손님이 없습니다.");
        } else {
            System.out.printf("%-8s %-8s %-10s %-6s%n", "손님번호", "메뉴이름", "예상금액", "일차");
            System.out.println("----------------------------------------");
            for (CustomerDto customer : customers) {
                int price = hc.getPrice(customer.getMenu_id());
                String menu = hc.getMenu(customer.getMenu_id());
                System.out.printf("%-8d %-8s %-10d %-6d%n",
                        customer.getCustomer_no(),
                        menu,
                        price,
                        customer.getCustomer_log_day());
            }
        }

        System.out.println("----------------------------------------");
        System.out.println("총 대기 손님 수: " + customers.size() + "명");
        System.out.println("========================================");
    }
}
