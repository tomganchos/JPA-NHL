import Parsing.GameClass;
import Parsing.ParsingGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomga on 10/8/2016.
 */
public class Launcher {
    public static void main(String[] args) {
        ParsingGame parsingGame = new ParsingGame();
        List<GameClass> gameClassList = parsingGame.toParse();
        for (int i = 0; i < gameClassList.size(); i++) {
            System.out.println(gameClassList.get(i).toString());
        }
    }
}
