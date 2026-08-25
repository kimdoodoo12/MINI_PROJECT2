package screen2.model.dto;

public class GameStateDTO {

    int gameState_id;
    int current_day;
    int current_gold;
    Boolean restaurant_state;

    public GameStateDTO(){}

    public GameStateDTO(int gameState_id, int current_day, int current_gold, Boolean restaurant_state) {
        this.gameState_id = gameState_id;
        this.current_day = current_day;
        this.current_gold = current_gold;
        this.restaurant_state = restaurant_state;
    }

    public int getGameState_id() {
        return gameState_id;
    }

    public void setGameState_id(int gameState_id) {
        this.gameState_id = gameState_id;
    }

    public int getCurrent_day() {
        return current_day;
    }

    public void setCurrent_day(int current_day) {
        this.current_day = current_day;
    }

    public int getCurrent_gold() {
        return current_gold;
    }

    public void setCurrent_gold(int current_gold) {
        this.current_gold = current_gold;
    }

    public Boolean getRestaurant_state() {
        return restaurant_state;
    }

    public void setRestaurant_state(Boolean restaurant_state) {
        this.restaurant_state = restaurant_state;
    }

    @Override
    public String toString() {
        return "GameStateDTO [gameState_id=" + gameState_id + ", current_day=" + current_day + ", current_gold="
                + current_gold + ", restaurant_state=" + restaurant_state + "]";
    }

    

}
