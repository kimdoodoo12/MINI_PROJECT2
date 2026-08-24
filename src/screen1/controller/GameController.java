package screen1.controller;

public class GameController {

    public void gameStart(){
        Runnable runnable = new Runnable(){
            private int time = 40;
            @Override
            public void run(){
                if (time <= 0){
                    break;
                }
                Thread.sleep(1000);
                time--;
            };
            changeGameState();
            minusGold();
        };
    }

    public void changeGameState(){

    }

    public void minusGold(){
        
    }
}
