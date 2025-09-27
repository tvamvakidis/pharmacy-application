package gr.vamvakidis.pharmacy_application.repository;

import gr.vamvakidis.pharmacy_application.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Retrieves all "OUT" type transactions for a specific medicine
     * within a given date range.
     *
     * @param medicineId   The ID of the medicine to filter by
     * @param startingDate The start of the date range (inclusive)
     * @param endingDate   The end of the date range (inclusive)
     * @return A list of Transaction entities that match the criteria
     */
    @Query("SELECT t FROM Transaction t " +
            "WHERE t.medicine.id = :medicineId " +
            "AND t.type = 'OUT' " +
            "AND t.date BETWEEN :startingDate AND :endingDate")
    List<Transaction> getTransactionsOut(@Param("medicineId") Long medicineId,
                                         @Param("startingDate") LocalDateTime startingDate,
                                         @Param("endingDate") LocalDateTime endingDate);
}
