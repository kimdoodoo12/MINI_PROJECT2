// package screen1.view;

// import java.util.ArrayList;

// import screen1.controller.GameController;
// import screen1.controller.HallController;
// import screen1.controller.TotalProductStatusController;
// import screen1.model.dto.CustomerDto;

// public class HallGameView2 {
// private HallGameView2() {
// } // 1.

// private static final HallGameView2 instance = new HallGameView2(); // 2.

// public static HallGameView2 getInstance() {
// return instance;
// } // 3.

// private HallController hc = HallController.getInstance();

// public void run() {

// // hc.startService();
// // while (hc.isOpen()){
// // showCustomer();
// // try{
// // Thread.sleep(1000);
// // }catch(InterruptedException e){
// // System.out.println(e);
// // }
// // }

// boolean isOpen = true; // 매개변수
// gc.gameStart(); // 40초동안 시간 1초마다 차감되고 0이되면 db OFF + 골드차감

// while (true) {
// boolean db = hallc.isOpen(); // db 조회

// if (isOpen != db) { // db boolean이랑 main bolean 값이 다르면
// isOpen = db; // db 값으로 업데이트 하고

// if (isOpen == false) { // 영업 종료일떄
// // 현재 골드가 음수다 -> 전체통계 양수다 -> 일일통계
// if()
// statusv.printDailySatus();
// }
// if (isOpen == true) { // 영업 열었을떄
// showCustomer();
// // 손님 무한 출력 view 실행
// }
// }
// }
// }

// public void showCustomer() {
// ArrayList<CustomerDto> customers = hc.findAllCustomer();

// System.out.println("========================================");
// System.out.println(" [ 홀 현황 - 대기 손님 목록 ]");
// System.out.println("========================================");

// if (customers.isEmpty()) {
// System.out.println("현재 대기 중인 손님이 없습니다.");
// } else {
// System.out.printf("%-8s %-8s %-10s %-6s%n", "손님번호", "메뉴ID", "예상금액", "일차");
// System.out.println("----------------------------------------");
// for (CustomerDto customer : customers) {
// int price = hc.getPrice(customer.getMenu_id());
// System.out.printf("%-8d %-8d %-10d %-6d%n",
// customer.getCustomer_no(),
// customer.getMenu_id(),
// price,
// customer.getCustomer_log_day());
// }
// }

// System.out.println("----------------------------------------");
// System.out.println("총 대기 손님 수: " + customers.size() + "명");
// System.out.println("========================================");
// }

// }
