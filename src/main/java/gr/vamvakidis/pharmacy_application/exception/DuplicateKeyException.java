package gr.vamvakidis.pharmacy_application.exception;

/**
 * Exception thrown when attempting to insert or update a record
 * that violates a unique constraint (duplicate key).
 */
public class DuplicateKeyException extends RuntimeException {

    /**
     * Constructs a new DuplicateKeyException with a message indicating
     * which key and value caused the duplication conflict.
     *
     * @param key   The name of the key/field that must be unique
     * @param value The duplicate value that caused the exception
     */
    public DuplicateKeyException(String key, String value) {
        super("Duplicate value for " + key + ": " + value);
    }
}
