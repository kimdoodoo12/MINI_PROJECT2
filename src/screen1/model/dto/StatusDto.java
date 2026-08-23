package screen1.model.dto;

public class StatusDto {
    private int day;
    private int sales;
    private int productExpense;
    private int totalCustomer;
    private int servedCustomer;

    public StatusDto(int day, int sales, int productExpense, int totalCustomer, int ServedCustomer) {
        this.day = day;
        this.sales = sales;
        this.productExpense = productExpense;
    }

    public int getDay() {
        return day;
    }

    public int getSalse() {
        return sales;
    }

    public int getExpense() {
        return productExpense;
    }

    public int getNetProfit() {
        return sales - productExpense;
    }

    public int getTotalCustomer() {
        return totalCustomer;
    }

    public int getServedCustomer() {
        return servedCustomer;
    }

    public int getLeftCustomer() {
        return totalCustomer - servedCustomer;
    }

}
