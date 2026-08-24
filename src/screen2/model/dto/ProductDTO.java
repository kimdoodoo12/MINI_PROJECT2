package screen2.model.dto;

public class ProductDTO {

    int 재료번호;
    int 수량;

    public ProductDTO(int 재료번호, int 수량) {
        this.재료번호 = 재료번호;
        this.수량 = 수량;
    }

    public int get재료번호() {
        return 재료번호;
    }
    public void set재료번호(int 재료번호) {
        this.재료번호 = 재료번호;
    }
    public int get수량() {
        return 수량;
    }
    public void set수량(int 수량) {
        this.수량 = 수량;
    }

    @Override
    public String toString() {
        return "ProductDTO [재료번호=" + 재료번호 + ", 수량=" + 수량 + "]";
    }

    
}
