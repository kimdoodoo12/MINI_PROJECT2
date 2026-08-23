package screen1;

import screen1.view.StatusView;

public class AppStart {
    public static void main(String[] args) {
        StatusView test = StatusView.getinstance();
        test.printDailySatus();

    }
}
