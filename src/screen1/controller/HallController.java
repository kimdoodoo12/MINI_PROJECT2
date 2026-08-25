// package screen1.controller;

// import java.util.ArrayList;
// import java.util.Random;
// import java.util.concurrent.Executors;
// import java.util.concurrent.ThreadPoolExecutor;

// import screen1.model.dao.CookDao;
// import screen1.model.dao.CustomerDao;
// import screen1.model.dao.MenuDao;
// import screen1.model.dto.CookDto;
// import screen1.model.dto.CustomerDto;
// import screen1.task.CustomerTask;

// public class HallController {

// private HallController() {
// };

// private static HallController instance = new HallController();

// public static HallController getInstance() {
// return instance;
// }

// // 서비스가 시작됐을 때 손님이 들어오는 함수
// public void startService() {
// HallService.getInstance().startService();
// }
// }

// // public class CallCenterMain {

// // public static void main(String[] args) {

// // System.out.println("=== 고객센터 시스템 시작 ===");

// // // [1] 상담원 3명 스레드 풀 생성 및 대기 상태 초기화

// // ThreadPoolExecutor agentPool = (ThreadPoolExecutor)
// // Executors.newFixedThreadPool(3);

// // // [2] 전화 인입 스레드 생성 (익명 구현체)

// // Runnable customerProducer = new Runnable() {

// // @Override

// // public void run() {

// // for (int customerId = 1; customerId <= 20; customerId++) {

// // try {

// // Thread.sleep(3000); // 3초 간격 전화 인입

// // } catch (InterruptedException e) {}

// // // [3] CallTask 구현체 객체 생성 및 스레드풀에 배정

// // CallTask task = new CallTask(customerId);

// // agentPool.submit(task);

// // // [4] 현재 풀 상태 계산

// // int activeAgents = agentPool.getActiveCount();

// // int idleAgents = agentPool.getCorePoolSize() - activeAgents;

// // int waitingCustomers = agentPool.getQueue().size();

// // System.out.println("\n==[현황] 고객 " + customerId + "번 인입 , [대기 상담원]: " +
// // idleAgents + "명 , [통화
// // 중]: " + activeAgents + "명 , [대기 고객]: " + waitingCustomers + "명\n");

// // } // for end

// // // 100건 인입 완료 후 스레드 풀 종료 예약

// // agentPool.shutdown();

// // } // run end

// // }; // 익명구현체 end

// // // [5] 전화 인입 스레드 시작

// // Thread producerThread = new Thread(customerProducer);

// // producerThread.start();

// // }

// // }

// // class CallTask implements Runnable {

// // private Random random = new Random();

// // private int customerId;

// // public CallTask( int customerId ){ this.customerId = customerId; }

// // @Override

// // public void run() {

// // String agentName = Thread.currentThread().getName() ;

// // System.out.println(agentName+"-상담원 고객통화 시작-" + customerId);

// // try {

// // Thread.sleep(6000 + random.nextInt(6001)); // 6000ms ~ 12000ms (6초 ~ 12초)

// // } catch (InterruptedException e) {}

// // System.out.println(agentName+"-상담원 고객통화 종료-" + customerId);

// // }

// // }