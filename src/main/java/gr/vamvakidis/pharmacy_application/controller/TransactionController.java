package gr.vamvakidis.pharmacy_application.controller;

import gr.vamvakidis.pharmacy_application.dto.TransactionDto;
import gr.vamvakidis.pharmacy_application.entity.Medicine;
import gr.vamvakidis.pharmacy_application.entity.Transaction;
import gr.vamvakidis.pharmacy_application.mapper.TransactionMapper;
import gr.vamvakidis.pharmacy_application.service.MedicineService;
import gr.vamvakidis.pharmacy_application.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionService transactionService;
    private final MedicineService medicineService;

    public TransactionController(TransactionService transactionService, MedicineService medicineService) {
        this.transactionService = transactionService;
        this.medicineService = medicineService;
    }


    /**
     * Retrieves all transactions.
     *
     * @return List of TransactionDto representing all transactions
     */
    @GetMapping
    public List<TransactionDto> getAll() {
        return transactionService.getAllTransactions().stream().map(TransactionMapper::toDto).collect(Collectors.toList());
    }


    /**
     * Retrieves a single transaction by its ID.
     *
     * @param id The ID of the transaction to retrieve
     * @return TransactionDto representing the requested transaction
     */
    @GetMapping("/{id}")
    public TransactionDto getById(@PathVariable Long id) {
        return TransactionMapper.toDto(transactionService.getTransactionById(id));
    }


    /**
     * Creates a new transaction.
     *
     * @param dto The TransactionDto containing data for the new transaction
     * @return TransactionDto representing the created transaction
     */
    @PostMapping
    public TransactionDto create(@RequestBody TransactionDto dto) {
        Medicine medicine = medicineService.getMedicineById(dto.medicineId());
        Transaction transaction = TransactionMapper.toEntity(dto, medicine);
        return TransactionMapper.toDto(transactionService.createTransaction(transaction));
    }


    /**
     * Deletes a transaction by its ID.
     *
     * @param id The ID of the transaction to delete
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }
}
