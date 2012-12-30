/**
 * SakuraUtils - Package: net.syamn.utils.exception
 * Created: 2012/12/26 21:46:17
 */
package net.syamn.utils.exception;

/**
 * CommandException (CommandException.java)
 * @author syam(syamn)
 */
public class CommandException extends Exception {
    private static final long serialVersionUID = 1L;

    public CommandException(String message) {
        super(message);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
