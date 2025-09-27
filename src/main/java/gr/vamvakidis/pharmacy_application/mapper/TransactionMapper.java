package gr.vamvakidis.pharmacy_application.mapper;

import gr.vamvakidis.pharmacy_application.dto.TransactionDto;
import gr.vamvakidis.pharmacy_application.entity.Medicine;
import gr.vamvakidis.pharmacy_application.entity.Transaction;

public class TransactionMapper {

    /**
     * Converts a Transaction entity to a TransactionDto.
     * Used when sending data to the client (API response).
     *
     * @param transaction The Transaction entity to convert
     * @return A TransactionDto containing the same data as the entity, or null if input is null
     */
    public static TransactionDto toDto(Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        return new TransactionDto(
                transaction.getId(),
                transaction.getType(),
                transaction.getQuantity(),
                transaction.getDate(),
                transaction.getDescription(),
                transaction.getMedicine() != null ? transaction.getMedicine().getId() : null
        );
    }

    /**
     * Converts a TransactionDto to a Transaction entity.
     * Used when receiving data from the client (API request)
     * to create or update a Transaction in the database.
     *
     * @param dto The TransactionDto to convert
     * @param medicine The associated Medicine entity
     * @return A Transaction entity ready to be persisted, or null if the DTO input is null
     */
    public static Transaction toEntity(TransactionDto dto, Medicine medicine) {
        if (dto == null) {
            return null;
        }

        Transaction transaction = new Transaction();
        transaction.setId(dto.id());
        transaction.setType(dto.type());
        transaction.setQuantity(dto.quantity());
        transaction.setDate(dto.date());
        transaction.setDescription(dto.description());
        transaction.setMedicine(medicine);
        return transaction;
    }
}