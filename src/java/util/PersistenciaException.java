package util;

@SuppressWarnings("serial")
public class PersistenciaException extends java.lang.RuntimeException{
    public PersistenciaException() {
    }
    
    public PersistenciaException(String msg) {
        super(msg);
    }
    
    public PersistenciaException(Throwable cause) {
        super(cause);
    }
}
