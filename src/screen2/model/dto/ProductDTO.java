package screen2.model.dto;

public class ProductDTO {

    int product_no;
    String product_name;
    int product_price;
    
    public ProductDTO(){}

    public ProductDTO(int product_no, int product_price) {
        this.product_no = product_no;
        this.product_price = product_price;
    }

    public int getProduct_no() {
        return product_no;
    }

    public void setProduct_no(int product_no) {
        this.product_no = product_no;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    @Override
    public String toString() {
        return "ProductDTO [product_no=" + product_no + ", product_name=" + product_name + ", product_price="
                + product_price + "]";
    }

    

    
    
}
