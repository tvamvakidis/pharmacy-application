package gr.vamvakidis.pharmacy_application.dto;


public record MedicineDto(
        Long id,
        String name,
        String code,
        Double price,
        Integer stock,
        Long categoryId
) {
}
