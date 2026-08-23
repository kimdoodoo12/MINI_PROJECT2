package screen1.model.dto;

public class MenuStatusDto {
    private String menuName;
    private int count;

    public MenuStatusDto() {
    }

    public MenuStatusDto(String menuName, int count) {
        this.menuName = menuName;
        this.count = count;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getCount() {
        return count;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
