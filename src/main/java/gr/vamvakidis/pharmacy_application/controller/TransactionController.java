package gr.vamvakidis.pharmacy_application.controller;

import gr.vamvakidis.pharmacy_application.dto.TransactionDto;
import gr.vamvakidis.pharmacy_application.entity.Medicine;
import gr.vamvakidis.pharmacy_application.entity.Transaction;
import gr.vamvakidis.pharmacy_application.mapper.TransactionMapper;
import gr.vamvakidis.pharmacy_application.service.MedicineService;
import gr.vamvakidis.pharmacy_application.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public ResponseEntity<List<TransactionDto>> getAll() {
        List<TransactionDto> transactionDtos = transactionService.getAllTransactions().stream().map(TransactionMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(transactionDtos);
    }


    /**
     * Retrieves a single transaction by its ID.
     *
     * @param id The ID of the transaction to retrieve
     * @return TransactionDto representing the requested transaction
     */
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getById(@PathVariable Long id) {
        TransactionDto transactionDto = TransactionMapper.toDto(transactionService.getTransactionById(id));
        return ResponseEntity.status(HttpStatus.OK).body(transactionDto);
    }


    /**
     * Creates a new transaction.
     *
     * @param dto The TransactionDto containing data for the new transaction
     * @return TransactionDto representing the created transaction
     */
    @PostMapping
    public ResponseEntity<TransactionDto> create(@RequestBody TransactionDto dto) {
        Medicine medicine = medicineService.getMedicineById(dto.medicineId());
        Transaction transaction = TransactionMapper.toEntity(dto, medicine);
        TransactionDto createdTransactionDto = TransactionMapper.toDto(transactionService.createTransaction(transaction));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTransactionDto);
    }


    /**
     * Deletes a transaction by its ID.
     *
     * @param id The ID of the transaction to delete
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    /**
     * Retrieves all OUT transactions for a specific medicine within a given date range.
     *
     * @param medicineId The ID of the medicine whose OUT transactions are requested
     * @param startDate  The start of the date range (ISO-8601 format, e.g. "2025-09-28T00:00:00")
     * @param endDate    The end of the date range (ISO-8601 format, e.g. "2025-09-28T23:59:59")
     * @return List of TransactionDto representing the OUT transactions
     */
    @GetMapping("/out")
    public ResponseEntity<List<TransactionDto>> getTransactionsOut(
            @RequestParam("medicineId") Long medicineId,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        LocalDateTime startingDate = LocalDateTime.parse(startDate);
        LocalDateTime endingDate = LocalDateTime.parse(endDate);
        List<TransactionDto> transactionDtos = transactionService.getTransactionsOut(medicineId, startingDate, endingDate).
                stream().map(TransactionMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(transactionDtos);
    }
}
