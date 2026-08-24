package screen1.controller;

import java.util.ArrayList;

import screen1.model.dao.ProductStatusDao;
import screen1.model.dto.ProductStatusDto;

public class ProductStatusController {
    private ProductStatusController() {
    }

    private static final ProductStatusController instance = new ProductStatusController();

    public static ProductStatusController getInstance() {
        return instance;
    }

    private ProductStatusDao productd = ProductStatusDao.getInstance();

    // 재고명 + 사용량 + 잔여량 가져오기 및 병합
    public ArrayList<ProductStatusDto> getProductStatus(int day) {
        ArrayList<ProductStatusDto> usedList = productd.getUsed(day);
        ArrayList<ProductStatusDto> remainList = productd.getRemain(day);
        ArrayList<ProductStatusDto> result = new ArrayList<>();

        for (ProductStatusDto used2 : usedList) {
            int remain = 0;
            String pName = used2.getProductName();
            int used = used2.getUsed();
            for (ProductStatusDto remain2 : remainList) {
                if (used2.getProductName().equals(remain2.getProductName())) {
                    System.out.println("조건 걸림");
                    remain = remain2.getRemain();
                }

            }
            ProductStatusDto productStatusDto = new ProductStatusDto(pName, used, remain);
            System.out.println("컨트롤러 출력");
            System.out.println(pName);
            System.out.println(used);
            System.out.println(remain);
            System.out.println(productStatusDto);
            result.add(productStatusDto);
        }
        System.out.println("컨트롤러 객체 " + result);
        return result;

    }
}
