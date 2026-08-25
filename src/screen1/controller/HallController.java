package screen1.controller;

public class HallController {

    private HallController() {
    };

    private static HallController instance = new HallController();

    public static HallController getInstance() {
        return instance;
    }

    // 서비스가 시작됐을 때 손님이 들어오는 함수
    public void startService() {
        HallService.getInstance().startService();
    }
}
