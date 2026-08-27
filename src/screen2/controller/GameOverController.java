package screen2.controller;

import java.util.ArrayList;

import screen2.model.dao.GameOverDao;
import screen2.model.dto.RankDto;

public class GameOverController {
    private GameOverController() {
    }

    private static final GameOverController instance = new GameOverController();

    public static GameOverController getInstance() {
        return instance;
    }

    private GameOverDao gd = GameOverDao.getInstance();

    // 게임 기록 추가
    public boolean gameRankInsert(String userName) {
        boolean result = gd.gameRankInsert(userName);
        return result;
    }

    // 게임 기록 조회
    public ArrayList<RankDto> gameRankList() {
        ArrayList<RankDto> list = gd.gameRankList();
        return list;
    }
}
