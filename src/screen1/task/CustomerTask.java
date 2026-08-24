package screen1.task;

import screen1.controller.HallController;
import screen1.model.dto.CustomerDto;

public class CustomerTask implements Runnable{
    private CustomerDto customer;

   private HallController hc = HallController.getInstance();

    public CustomerTask(CustomerDto customer){
        this.customer = customer;
    }

    @Override
    public void run() {
        try{
            int time = customer.getTime();
            while(time > 0){
                // DB에 들어간 customer 상태를 직접 조회 (로컬 customer 객체는 갱신되지 않음)
                String state = hc.getState(customer.getCustomer_no());
                if ("served".equals(state))
                    break;
                Thread.sleep(1000);
                time--;

            }
            

            // 시간이 다 됐을 때
            if (time <= 0){
                hc.setLeft(customer.getCustomer_no());
            }

        }catch(Exception e){
            System.out.println(e);
        }

    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }
    
}