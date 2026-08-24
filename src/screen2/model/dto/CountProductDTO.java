package screen2.model.dto;

public class CountProductDTO {

    int product_id;
    String product_name;
    int product_totalQty;

    public CountProductDTO(){}

    public CountProductDTO(int product_id, String product_name, int product_totalQty) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_totalQty = product_totalQty;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_totalQty() {
        return product_totalQty;
    }

    public void setProduct_totalQty(int product_totalQty) {
        this.product_totalQty = product_totalQty;
    }

    @Override
    public String toString() {
        return "CountProductDTO [product_id=" + product_id + ", product_name=" + product_name + ", product_totalQty="
                + product_totalQty + "]";
    }

    

}
