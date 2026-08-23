package screen1.model.dto;

/**
 * CustomerDto
 */
public class CustomerDto{
    
    private int time = 20;
    private int customer_no;
    private int menu_id;
    private String customer_state;
    private int customer_log_day;
    private int current_gold;



    public CustomerDto(){}

    
    // 메뉴는 랜덤으로 자바로직에서 생성, 일차는 다른 테이블 참고 필요
    public CustomerDto(int menu_id) {
        this.menu_id = menu_id;
        this.customer_log_day = customer_log_day;
    }



    public int getCustomer_no() {
        return customer_no;
    }
    public void setCustomer_no(int customer_no) {
        this.customer_no = customer_no;
    }
    public int getMenu_id() {
        return menu_id;
    }
    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }
    public String getCustomer_state() {
        return customer_state;
    }
    public void setCustomer_state(String customer_state) {
        this.customer_state = customer_state;
    }
    public int getCustomer_log_day() {
        return customer_log_day;
    }
    public void setCustomer_log_day(int customer_log_day) {
        this.customer_log_day = customer_log_day;
    }
    public int getCurrent_gold() {
        return current_gold;
    }
    public void setCurrent_gold(int current_gold) {
        this.current_gold = current_gold;
    }
    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }

    
}