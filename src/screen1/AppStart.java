package screen1;

import screen1.view.HallGameView2;
import screen1.view.StatusView;

public class AppStart {
    public static void main(String[] args) {
        HallGameView2.getInstance().run();
        // StatusView test = StatusView.getinstance();
        // test.printDailySatus();
    }
}