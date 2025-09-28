package gr.vamvakidis.pharmacy_application.exception;

/**
 * Exception thrown when a transaction attempts to reduce the stock of a medicine
 * below zero (i.e., when there is not enough stock available to fulfill the request).
 */
public class NotEnoughStockException extends RuntimeException {

    /**
     * Constructs a new NotEnoughStockException with a message indicating
     * which medicine ID could not fulfill the transaction due to insufficient stock.
     *
     * @param id The ID of the medicine with insufficient stock
     */
    public NotEnoughStockException(Long id) {
        super("Not enough stock for " + id + " to fulfill this transaction.");
    }
}
