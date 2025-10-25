package logic;

/**
 * Interface for the RetryHandler class. Defined in the Frame class, and
 * consumed anywhere where the retry method needs to be called (buttons, state
 * manager, etc.). This creates a new thread to then restart the game loop and
 * state manager. See the Frame class for the implementation.
 * 
 * @author Tymur Tykva
 * @ID 2275201
 * @author Borislav Grebanarov
 * @ID 2109832
 */
public interface RetryHandler {
    public void retry();
}
