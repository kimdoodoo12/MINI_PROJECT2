package screen1.model.dto;

public class StatusDto {
    private int day;
    private int sales;
    private int productExpense;
    private int totalCustomer;
    private int servedCustomer;

    public StatusDto() {
    }

    public StatusDto(int day, int sales, int productExpense, int totalCustomer, int servedCustomer) {
        this.day = day;
        this.sales = sales;
        this.productExpense = productExpense;
        this.totalCustomer = totalCustomer;
        this.servedCustomer = servedCustomer;
    }

    public int getDay() {
        return day;
    }

    public int getSales() {
        return sales;
    }

    public int getExpense() {
        return productExpense;
    }

    public int getTotalCustomer() {
        return totalCustomer;
    }

    public int getServedCustomer() {
        return servedCustomer;
    }

}
