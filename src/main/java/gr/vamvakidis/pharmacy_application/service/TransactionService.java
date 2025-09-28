package gr.vamvakidis.pharmacy_application.service;

import gr.vamvakidis.pharmacy_application.entity.Medicine;
import gr.vamvakidis.pharmacy_application.entity.Transaction;
import gr.vamvakidis.pharmacy_application.entity.TransactionType;
import gr.vamvakidis.pharmacy_application.exception.NotEnoughStockException;
import gr.vamvakidis.pharmacy_application.exception.ResourceNotFoundException;
import gr.vamvakidis.pharmacy_application.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final MedicineService medicineService;

    public TransactionService(TransactionRepository transactionRepository, MedicineService medicineService) {
        this.transactionRepository = transactionRepository;
        this.medicineService = medicineService;
    }


    /**
     * Retrieves all transactions from the database.
     *
     * @return List of all Transaction entities
     */
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }


    /**
     * Retrieves a single transaction by its ID.
     * Throws ResourceNotFoundException if the transaction does not exist.
     *
     * @param id The ID of the transaction
     * @return The Transaction entity with the given ID
     */
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction", id));
    }


    /**
     * Creates a new transaction in the database.
     * Ensures that the associated Medicine exists by fetching it from the MedicineService.
     *
     * @param transaction The Transaction entity to create
     * @return The saved Transaction entity
     */
    public Transaction createTransaction(Transaction transaction) {
        Medicine medicine = medicineService.getMedicineById(transaction.getMedicine().getId());
        transaction.setMedicine(medicine);
        processTransaction(transaction);
        return transactionRepository.save(transaction);
    }


    /**
     * Deletes an existing transaction by its ID.
     * Throws ResourceNotFoundException if the transaction does not exist.
     *
     * @param id The ID of the transaction to delete
     */
    public void deleteTransaction(Long id) {
        Transaction existingTransaction = getTransactionById(id);
        transactionRepository.delete(existingTransaction);
    }


    /**
     * Processes the given transaction and updates the stock of the associated medicine accordingly.
     * <p>
     * - If the transaction type is IN, the stock of the medicine is increased by the given quantity.
     * - If the transaction type is OUT, the stock of the medicine is decreased by the given quantity,
     * unless there is not enough stock available, in which case a NotEnoughStockException is thrown.
     *
     * @param transaction The Transaction entity containing the medicine, type, and quantity
     * @throws NotEnoughStockException if the transaction is OUT and the requested quantity
     *                                 exceeds the available stock of the medicine
     */
    public void processTransaction(Transaction transaction) {
        Medicine medicine = transaction.getMedicine();
        TransactionType type = transaction.getType();
        int quantity = transaction.getQuantity();

        if (type == TransactionType.IN) {
            medicine.setStock(medicine.getStock() + quantity);
        } else {
            if (quantity > medicine.getStock()) {
                throw new NotEnoughStockException(medicine.getId());
            } else {
                medicine.setStock(medicine.getStock() - quantity);
            }
        }
    }


    /**
     * Retrieves all OUT transactions for a specific medicine within a given date range.
     *
     * @param medicineId   The ID of the medicine whose transactions are being queried
     * @param startingDate The start of the date range (inclusive)
     * @param endingDate   The end of the date range (inclusive)
     * @return List of Transaction entities of type OUT for the given medicine and date range
     */
    public List<Transaction> getTransactionsOut(Long medicineId, LocalDateTime startingDate, LocalDateTime endingDate) {
        return transactionRepository.getTransactionsOut(medicineId, startingDate, endingDate);
    }
}
