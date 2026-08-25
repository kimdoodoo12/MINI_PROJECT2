package screen1.controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import screen1.model.dao.CookDao;
import screen1.model.dao.CustomerDao;
import screen1.model.dao.MenuDao;
import screen1.model.dto.CookDto;
import screen1.model.dto.CustomerDto;
import screen1.task.CustomerTask;

public class HallService {

    private HallService() {
    };

    private static HallService instance = new HallService();

    public static HallService getInstance() {
        return instance;
    }

    private CustomerDao cd = CustomerDao.getInstance();
    private CookDao ckd = CookDao.getInstance();
    private MenuDao md = MenuDao.getInstance();

    // 쓰레드 풀 자리 5개 생성
    ThreadPoolExecutor customerPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

    public boolean cookServe() {
        ArrayList<CustomerDto> customerDtos = findAllCustomer();
        ArrayList<CookDto> cookDtos = findAllCook();

        // 손님 테이블과 완성된 요리테이블 전부 비교
        for (CustomerDto customer : customerDtos) {
            for (CookDto cook : cookDtos) {
                // 손님이 원하는 메뉴와 완성된 요리의 메뉴가 같다면
                if (customer.getMenu_id() == cook.getMenu_id()) {

                    // 완성 요리의 요리 상태를 바꿈
                    boolean result = setCook(cook.getCook_id(), true);
                    // 손님들의 상태와 수익을 바꿈
                    boolean result2 = checkBill(customer.getCustomer_no(),
                            getPrice(cook.getMenu_id()));

                    if (result && result2) {
                        return true;
                    }
                }

            }
        }

        // 요리테이블중에서 아무것도 매치되지 않는 경우 모두 폐기처리
        for (CookDto cook : cookDtos) {
            ckd.setCook(cook.getCook_id(), false);
        }

        return false;
    }

    public boolean checkBill(int customer_no, int price) {
        boolean result = cd.checkBill(customer_no, price);
        return result;
    }

    public boolean setCook(int menu_id, boolean isServed) {
        boolean result = ckd.setCook(menu_id, isServed);
        return result;
    }

    public ArrayList<CustomerDto> findAllCustomer() {
        ArrayList<CustomerDto> customerDtos = new ArrayList<>();
        customerDtos = cd.findAllCustomer();
        return customerDtos;
    }

    public ArrayList<CookDto> findAllCook() {
        ArrayList<CookDto> cookDtos = new ArrayList<>();
        cookDtos = ckd.findAllCook();
        return cookDtos;
    }

    public boolean setLeft(int customer_no) {
        boolean result = cd.setLeft(customer_no);
        return result;
    }

    public String getState(int customer_no) {
        String result = cd.getState(customer_no);
        return result;
    }

    public int getRandomMenu() {
        int result = md.getRandomMenu();
        return result;
    }

    public int getPrice(int menu_id) {
        int result = md.getPrice(menu_id);
        return result;
    }

    public boolean createCustomer(CustomerDto customerDto) {
        boolean result = cd.createCustomer(customerDto);
        return result;
    }

    public boolean isOpen() {
        boolean result = cd.isOpen();
        return result;
    }

    // 서비스가 시작됐을 때 손님이 들어오는 함수
    public void startService() {

        // 완성된 요리를 손님과 매칭해 서빙하는 쓰레드 (1초마다 반복)
        Runnable serveRunnable = new Runnable() {
            @Override
            public void run() {
                while (isOpen()) {

                    boolean result2 = cookServe();

                    if (result2) {
                        /* 서빙 완료 메시지 */ } else {
                        /* 폐기처리 메시지 */}

                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        };

        // 독립적으로 손님들을 생성하는 쓰레드 생성 (서빙 체크와는 별개로 랜덤 시간마다 생성)
        Runnable customerRunnable = new Runnable() {
            @Override
            public void run() {
                Random random = new Random();

                // 손님들을 무한으로 생성시키는 반복문 start
                while (isOpen()) {
                    System.out.println(isOpen());
                    System.out.println(customerPool.getActiveCount());
                    // 사용중인 쓰레드가 최대 쓰레드풀보다 작은 경우에만 실행
                    if (customerPool.getActiveCount() < 5) {
                        try {
                            // 랜덤으로 대기시간 (2초부터 10초 사이)
                            Thread.sleep((2 + random.nextInt(9)) * 1000);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        // 손님 쓰레드 생성
                        CustomerTask customer = new CustomerTask(new CustomerDto(getRandomMenu()));
                        // 손님 생성 DB에 적용이 됐는지 확인
                        boolean result3 = cd.createCustomer(customer.getCustomer());
                        if (result3) {
                            // 성공하면 쓰레드풀에 등록
                            customerPool.submit(customer);
                        }
                    } else {
                        // 풀이 꽉 찼을 때도 쉬지 않고 도는 대시시간없이 도는 것을 막기 위한 sleep
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
                // 식당이 영업중이 아니여서 반복문을 탈출했을 때 쓰레드풀 종료
                customerPool.shutdown();
            }
        };

        // 두 쓰레드 각각 시작
        new Thread(serveRunnable).start();
        new Thread(customerRunnable).start();
    }
}
