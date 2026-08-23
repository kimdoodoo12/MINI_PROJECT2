package screen1.controller;

import java.util.ArrayList;

import screen1.model.dao.MenuStatusDao;
import screen1.model.dto.MenuStatusDto;

public class MenuController {
    private MenuController() {
    }

    private static final MenuController instance = new MenuController();

    public static MenuController getInstance() {
        return instance;
    }

    private MenuStatusDao menud = MenuStatusDao.getInstance();

    // 팔린 메뉴 이름, 수량 가져오기

    public ArrayList<MenuStatusDto> getMenuSales(int day) {
        ArrayList<MenuStatusDto> result = menud.getMenuSales(day);
        return result;
    }
}
