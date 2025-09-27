package gr.vamvakidis.pharmacy_application.dto;

import gr.vamvakidis.pharmacy_application.entity.TransactionType;

import java.time.LocalDateTime;

public record TransactionDto(
        Long id,
        TransactionType type,
        Integer quantity,
        LocalDateTime date,
        String description,
        Long medicineId
) {
}