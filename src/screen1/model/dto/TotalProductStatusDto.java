package screen1.model.dto;

public class TotalProductStatusDto {
    private int totalUesd;
    private int totalRemain;
    private int totalOrder;

    public TotalProductStatusDto() {
    }

    public TotalProductStatusDto(int totalUesd, int totalRemain, int totalOrder) {
        this.totalUesd = totalUesd;
        this.totalRemain = totalRemain;
        this.totalOrder = totalOrder;
    }

    public int getTotalUesd() {
        return totalUesd;
    }

    public int getTotalRemain() {
        return totalRemain;
    }

    public int getTotalOrder() {
        return totalOrder;
    }

}
