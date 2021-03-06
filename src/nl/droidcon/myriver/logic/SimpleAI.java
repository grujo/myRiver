package nl.droidcon.myriver.logic;

import nl.droidcon.myriver.MainActivity;

import java.util.Random;

/**
 * Write comment here.
 * <p/>
 * Creator: <lukasz.izmajlowicz@mydriver.com>
 * Date: 24/11/13
 */
public class SimpleAI {

    public static MainActivity.Mode runAI() {
        Random random = new Random();
        int i = random.nextInt(3);

        if (i == 0) {
            return MainActivity.Mode.PAPER;
        } else if (i == 1) {
            return MainActivity.Mode.ROCK;
        } else if (i == 2) {
            return MainActivity.Mode.SCISSORS;
        } else {
            return MainActivity.Mode.RAND;
        }
    }
}
