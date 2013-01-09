/**
 * SakuraLib - Package: net.syamn.utils.exception.utils
 * Created: 2013/01/10 1:55:07
 */
package net.syamn.utils.exception.utils;

/**
 * SakuraLibException (SakuraLibException.java)
 * @author syam(syamn)
 */
public class SakuraLibException extends RuntimeException {
    private static final long serialVersionUID = -7876287352072103174L;

    public SakuraLibException(String message) {
        super(message);
    }

    public SakuraLibException(Throwable cause) {
        super(cause);
    }

    public SakuraLibException(String message, Throwable cause) {
        super(message, cause);
    }
}
