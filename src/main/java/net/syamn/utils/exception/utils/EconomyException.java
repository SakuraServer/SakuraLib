/**
 * SakuraLib - Package: net.syamn.utils.exception
 * Created: 2013/01/10 1:54:08
 */
package net.syamn.utils.exception.utils;

/**
 * EconomyException (EconomyException.java)
 * @author syam(syamn)
 */
public class EconomyException extends SakuraLibException{
    private static final long serialVersionUID = 9177202031066133342L;

    public EconomyException(String message) {
        super(message);
    }

    public EconomyException(Throwable cause) {
        super(cause);
    }

    public EconomyException(String message, Throwable cause) {
        super(message, cause);
    }
}
