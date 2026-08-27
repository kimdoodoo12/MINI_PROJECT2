package screen1.model.dto;

public class TotalStatusDto {
    private int totalDays;
    private int finalGold;
    private int totalSales;
    private int totalExpense;
    private int totalCustomer;
    private int totalServed;

    private TotalStatusDto() {
    }

    public TotalStatusDto(int totalDays, int finalGold, int totalSales, int totalExpense, int totalCustomer,
            int totalServed) {
        this.totalDays = totalDays;
        this.finalGold = finalGold;
        this.totalSales = totalSales;
        this.totalExpense = totalExpense;
        this.totalCustomer = totalCustomer;
        this.totalServed = totalServed;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public int getFinalGold() {
        return finalGold;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public int getTotalExpense() {
        return totalExpense;
    }

    public int getTotalCustomer() {
        return totalCustomer;
    }

    public int getTotalServed() {
        return totalServed;
    }

}
