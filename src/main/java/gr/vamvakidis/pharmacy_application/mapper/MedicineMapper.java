package gr.vamvakidis.pharmacy_application.mapper;

import gr.vamvakidis.pharmacy_application.dto.MedicineDto;
import gr.vamvakidis.pharmacy_application.entity.Category;
import gr.vamvakidis.pharmacy_application.entity.Medicine;

public class MedicineMapper {

    /**
     * Converts a Medicine entity to a MedicineDto.
     * Used when sending data to the client (API response).
     *
     * @param medicine The Medicine entity to convert
     * @return A MedicineDto containing the same data as the entity, or null if input is null
     */
    public static MedicineDto toDto(Medicine medicine) {

        if (medicine == null) {
            return null;
        }

        return new MedicineDto(
                medicine.getId(),
                medicine.getName(),
                medicine.getCode(),
                medicine.getPrice(),
                medicine.getStock(),
                medicine.getCategory() != null ? medicine.getCategory().getId() : null
        );
    }

    /**
     * Converts a MedicineDto to a Medicine entity.
     * Used when receiving data from the client (API request)
     * to create or update a Medicine in the database.
     *
     * @param dto The MedicineDto to convert
     * @param category The associated Category entity
     * @return A Medicine entity ready to be persisted, or null if the DTO input is null
     */
    public static Medicine toEntity(MedicineDto dto, Category category) {

        if (dto == null) {
            return null;
        }

        Medicine medicine = new Medicine();
        medicine.setName(dto.name());
        medicine.setCode(dto.code());
        medicine.setPrice(dto.price());
        medicine.setStock(dto.stock());
        medicine.setCategory(category);

        return medicine;
    }
}