package screen1.model.dto;

public class TotalMenuStatusDto {
    private String menuName;
    private int count;

    public TotalMenuStatusDto() {
    }

    public TotalMenuStatusDto(String menuName, int count) {
        this.menuName = menuName;
        this.count = count;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getCount() {
        return count;
    }
}
