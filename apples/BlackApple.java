package apples;

import java.awt.Point;
import java.util.Random;
import logic.StateManager;
import utils.Config;

/**
 * A black apple. When eaten, the game ends. When expired, it turns into either
 * a red or yellow apple. See the implementation of onExpire() for additional
 * information and the logic.
 * 
 * @author Tymur Tykva
 * @ID 2275201
 * @author Borislav Grebanarov
 * @ID 2109832
 */
public class BlackApple extends Apple {
    /* --------------- Constructors --------------- */
    /**
     * Create an instance of BlackApple with a given position.
     * 
     * @param position Position of the apple.
     */
    public BlackApple(Point position) {
        super(position);
        this.imageUrl = getClass().getResource("/ui/blackAppleImage.png");
        this.expiresIn = 10;
    }

    /**
     * Create an instance of BlackApple at a random available space on the board,
     * with strict set to true.
     * 
     * @param stateManager The StateManager instance.
     */
    public BlackApple(StateManager stateManager) {
        super(stateManager, true);
        this.imageUrl = getClass().getResource("/ui/blackAppleImage.png");
        this.expiresIn = 10;
    }

    /* ------------------ Public ------------------ */
    @Override
    public void eat(StateManager stateManager) {
        // Kill the snake and remove the apple.
        stateManager.setGameOver(true);
        stateManager.setRandomDeathMessage(Config.DEATH_BLACK_APPLE_MESSAGES);
        stateManager.getApples().remove(this);
    }

    /**
     * When the apple expires, it turns into either a yellow or a red apple, at its
     * previous position. The probabilities of turning into each are specified in
     * the Config class.
     * 
     * @param stateManager The StateManager instance.
     */
    @Override
    public void onExpire(StateManager stateManager) {
        int score = stateManager.getScore();

        // Set the chance of the black apple expiring into a yellow apple.
        float yellowChance = Config.INITIAL_BLACK_TO_YELLOW_CHANCE;
        if (score >= Config.SCORE_BREAKPOINTS[1]) {
            yellowChance = Config.LATE_BLACK_TO_YELLOW_CHANCE;
        }

        // Get a random float, and create an apple instance based on that.
        Random random = new Random(System.currentTimeMillis() + 278575268L);
        float randomChoice = random.nextFloat();

        Apple newApple = null;
        if (randomChoice <= yellowChance) {
            newApple = new YellowApple(this.position);
        } else {
            newApple = new RedApple(this.position);
        }

        // Remove the current apple, and add the new one instead.
        stateManager.getApples().remove(this);
        stateManager.getApples().add(newApple);
    }
}
