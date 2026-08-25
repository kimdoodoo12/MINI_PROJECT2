package screen1;

import screen1.view.StatusView;
import screen1.view.TotalStatusView;

public class AppStart {
    public static void main(String[] args) {
        // HallGameView2.getInstance().run();
        StatusView test = StatusView.getinstance();
        test.printDailySatus();
        TotalStatusView test2 = TotalStatusView.getinstance();
        test2.printFinalSatus();
    }
}