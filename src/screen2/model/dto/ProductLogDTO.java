package screen2.model.dto;

public class ProductLogDTO {


    int product_id;
    int product_qty;
    String product_condition;
    int productLog_price;
    int customerLog_day;

    public ProductLogDTO(){}

    public ProductLogDTO(int product_id, int product_qty) {
        this.product_id = product_id;
        this.product_qty = product_qty;
    }

    public ProductLogDTO(int product_id , int product_qty , String product_condition , int productLog_price , int customerLog_day){
        this.product_id = product_id;
        this.product_qty = product_qty;
        this.product_condition = product_condition;
        this.productLog_price = productLog_price;
        this.customerLog_day = customerLog_day;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getProduct_qty() {
        return product_qty;
    }

    public void setProduct_qty(int product_qty) {
        this.product_qty = product_qty;
    }

    public String getProduct_condition() {
        return product_condition;
    }

    public void setProduct_condition(String product_condition) {
        this.product_condition = product_condition;
    }

    public int getProductLog_price() {
        return productLog_price;
    }

    public void setProductLog_price(int productLog_price) {
        this.productLog_price = productLog_price;
    }

    public int getCustomerLog_day() {
        return customerLog_day;
    }

    public void setCustomerLog_day(int customerLog_day) {
        this.customerLog_day = customerLog_day;
    }

    @Override
    public String toString() {
        return "ProductDTO [product_id=" + product_id + ", product_qty=" + product_qty + ", product_condition="
                + product_condition + ", productLog_price=" + productLog_price + ", customerLog_day=" + customerLog_day
                + "]";
    }


}
