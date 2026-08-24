package screen1.model.dto;

public class ProductStatusDto {
    private String productName;
    private int used;
    private int remain;

    public ProductStatusDto() {
    }

    public ProductStatusDto(String productName, int used, int remain) {
        this.productName = productName;
        this.used = used;
        this.remain = remain;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }

    @Override
    public String toString() {
        return "ProductStatusDto [productName=" + productName + ", used=" + used + ", remain=" + remain + "]";
    }

}
