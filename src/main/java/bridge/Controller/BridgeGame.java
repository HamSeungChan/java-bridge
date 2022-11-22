package bridge.Controller;

import bridge.domain.BridgeMaker;
import bridge.domain.BridgeRandomNumberGenerator;
import bridge.domain.User;
import bridge.domain.Map;
import bridge.service.GameService;
import bridge.view.InputView;
import bridge.view.OutputView;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private GameService gameService;

    public void play() {
        set();
        move();
    }

    public void set() {
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        User user = new User();
        Map map = new Map();
        gameService = new GameService(bridgeMaker.makeBridge(InputView.readBridgeSize()), user, map);
    }

    public void move() {
        boolean proceed = true;
        while (proceed) {
            gameService.move(InputView.readMoving());
            OutputView.printMap(gameService.drawMap());
            proceed = gameService.CanKeepGoingGame();
        }
        if (gameService.isNotPass()) {
            retry();
        }
    }

    public void retry() {
        if (InputView.readGameCommand().equals("R")) {
            gameService.retry();
            move();
        }
    }
}
