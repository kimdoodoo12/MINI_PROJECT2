package screen1.controller;

import screen1.model.dao.TotalMenuStatusDao;
import screen1.model.dao.TotalProductStatusDao;

public class TotalProductStatusController {
    private TotalProductStatusController() {
    }

    private final static TotalProductStatusController instance = new TotalProductStatusController();

    public static TotalProductStatusController getInstance() {
        return instance;
    }

    private TotalProductStatusDao totalprod = TotalProductStatusDao.getInstance();

    // 총 발주량 가져오기
    public int getTotalOrder() {
        return totalprod.getTotalOrder();
    }

    // 총 사용량 가져오기
    public int getTotalUsed() {
        return totalprod.getTotalUsed();
    }

    // 총 잔여량 가져오기
    public int getTotalRemain() {
        return totalprod.getTotalRemain();
    }
}
