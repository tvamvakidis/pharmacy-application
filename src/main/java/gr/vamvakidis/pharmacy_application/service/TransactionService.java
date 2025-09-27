package gr.vamvakidis.pharmacy_application.service;

import gr.vamvakidis.pharmacy_application.entity.Medicine;
import gr.vamvakidis.pharmacy_application.entity.Transaction;
import gr.vamvakidis.pharmacy_application.exception.ResourceNotFoundException;
import gr.vamvakidis.pharmacy_application.repository.TransactionRepository;
import org.springframework.stereotype.Service;

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
}
