package screen2.model.dto;

public class RankDto {
    String userName;
    int currentGold;
    int maxDate;

    public RankDto() {
    }

    public RankDto(String userName, int currentGold, int maxDate) {
        this.userName = userName;
        this.currentGold = currentGold;
        this.maxDate = maxDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCurrentGold() {
        return currentGold;
    }

    public void setCurrentGold(int currentGold) {
        this.currentGold = currentGold;
    }

    public int getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(int maxDate) {
        this.maxDate = maxDate;
    }

}
