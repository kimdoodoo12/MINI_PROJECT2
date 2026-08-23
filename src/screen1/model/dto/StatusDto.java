package screen1.model.dto;

public class StatusDto {
    private int day;
    private int sales;
    private int productExpense;

    public StatusDto(int day, int sales, int productExpense) {
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

}
