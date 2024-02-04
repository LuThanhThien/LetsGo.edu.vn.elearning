package vn.letsgo.elearning.exception;

@HandleGlobalException
public class DataNotFoundException extends RuntimeException {

    private final Class<?> entityClass;

    public DataNotFoundException(Class<?> entityClass) {
        super("Cannot found data within entity " + entityClass.getName());
        this.entityClass = entityClass;
    }

    public DataNotFoundException(Class<?> entityClass, String msg) {
        super(msg);
        this.entityClass = entityClass;
    }

    public DataNotFoundException(Class<?> entityClass, String msg, Throwable cause) {
        super(msg, cause);
        this.entityClass = entityClass;
    }

    public Class<?> getEntityClass() {
        return entityClass;
    }
}
