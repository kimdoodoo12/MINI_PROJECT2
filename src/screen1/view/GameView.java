// package screen1.view;

// import screen1.controller.GameController;
// import screen1.controller.HallController;

// public class GameView {
// private GameView() {
// }

// private final static GameView instance = new GameView();

// public GameView getInstance() {
// return instance;
// }

// GameController gc = GameController.getInstance();
// StatusView statusv = StatusView.getinstance();
// TotalStatusView totalv = TotalStatusView.getinstance();
// HallController hallc = HallController.getInstance();

// public void run() {
// boolean isOpen = true; // 매개변수
// gc.gameStart(); // 40초동안 시간 1초마다 차감되고 0이되면 db OFF + 골드차감

// while (true) {
// boolean db = hallc.isOpen(); // db 조회

// if (isOpen != db) { // db boolean이랑 main bolean 값이 다르면
// isOpen = db; // db 값으로 업데이트 하고

// if (isOpen == false) {
// // 현재 골드가 음수다 -> 전체통계 양수다 -> 일일통계
// statusv.printDailySatus();
// }
// if (isOpen == true) {

// // 손님 무한 출력 view 실행
// }
// }
// }
// }
// }
