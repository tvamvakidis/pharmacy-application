package gr.vamvakidis.pharmacy_application.exception;

/**
 * Custom exception thrown when a requested resource (e.g., Category, Medicine, Transaction)
 * is not found in the database.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with a detailed message.
     *
     * @param resource The name of the resource that was not found
     * @param id The ID of the resource that was requested
     */
    public ResourceNotFoundException(String resource, Long id) {
        super(resource + " with id " + id + " not found.");
    }
}
