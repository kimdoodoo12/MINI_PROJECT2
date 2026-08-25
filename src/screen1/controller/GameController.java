// package screen1.controller;

// import screen1.model.dao.GameStateDao;

// public class GameController {
// private GameController() {
// }

// private final static GameController instance = new GameController();

// public static GameController getInstance() {
// return instance;
// }

// private GameStateDao gd = GameStateDao.getInstance();

// public void gameStart() {

// Runnable runnable = new Runnable() {
// private int time = 40;

// @Override
// public void run() {
// try {
// while (time >= 0) {
// Thread.sleep(1000);
// time--;
// }
// } catch (InterruptedException e) {
// System.out.println(e);
// }
// boolean result = changeGameState();
// if (result == true) {
// minusGold();
// if (!minusGold()) {
// System.out.println("골드차감 실패");
// }
// }

// }
// };
// Thread thread = new Thread(runnable);
// thread.start();
// }

// public boolean changeGameState() {
// boolean result = false;
// result = gd.setGameOff();
// return result;
// }

// public boolean minusGold() {
// boolean result = gd.minusGold();
// return result;
// }

// }
