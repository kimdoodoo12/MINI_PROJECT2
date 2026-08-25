package screen1.controller;

import java.util.ArrayList;

import screen1.model.dao.TotalMenuStatusDao;
import screen1.model.dto.TotalMenuStatusDto;

public class TotalMenuStatusController {
    private TotalMenuStatusController() {
    }

    private static final TotalMenuStatusController instance = new TotalMenuStatusController();

    public static TotalMenuStatusController getInstance() {
        return instance;
    }

    private TotalMenuStatusDao totalMenud = TotalMenuStatusDao.getInstance();

    // 전체 팔린 메뉴 중 TOP3 메뉴 가져오기
    public ArrayList<TotalMenuStatusDto> getMenuSales() {
        ArrayList<TotalMenuStatusDto> result = totalMenud.getTotalMenuSales();
        return result;
    }
}
