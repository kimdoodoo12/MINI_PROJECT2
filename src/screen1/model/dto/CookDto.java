package screen1.model.dto;

public class CookDto {
    private int cook_id;
    private int menu_id;
    private String cook_state;


    
    public int getCook_id() {
        return cook_id;
    }
    public void setCook_id(int cook_id) {
        this.cook_id = cook_id;
    }
    public int getMenu_id() {
        return menu_id;
    }
    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }
    public String getCook_state() {
        return cook_state;
    }
    public void setCook_state(String cook_state) {
        this.cook_state = cook_state;
    }

    
}
