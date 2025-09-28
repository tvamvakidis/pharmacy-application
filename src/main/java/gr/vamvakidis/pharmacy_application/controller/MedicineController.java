package gr.vamvakidis.pharmacy_application.controller;

import gr.vamvakidis.pharmacy_application.dto.MedicineDto;
import gr.vamvakidis.pharmacy_application.entity.Category;
import gr.vamvakidis.pharmacy_application.entity.Medicine;
import gr.vamvakidis.pharmacy_application.mapper.MedicineMapper;
import gr.vamvakidis.pharmacy_application.service.CategoryService;
import gr.vamvakidis.pharmacy_application.service.MedicineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/medicine")
public class MedicineController {

    private final MedicineService medicineService;
    private final CategoryService categoryService;

    public MedicineController(MedicineService medicineService, CategoryService categoryService) {
        this.medicineService = medicineService;
        this.categoryService = categoryService;
    }


    /**
     * Retrieves all medicines.
     *
     * @return List of MedicineDto representing all medicines
     */
    @GetMapping
    public ResponseEntity<List<MedicineDto>> getAll() {
        List<MedicineDto> medicines = medicineService.getAllMedicines().stream().map(MedicineMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(medicines);
    }


    /**
     * Retrieves a single medicine by its ID.
     *
     * @param id The ID of the medicine to retrieve
     * @return MedicineDto representing the requested medicine
     */
    @GetMapping("/{id}")
    public ResponseEntity<MedicineDto> getById(@PathVariable Long id) {
        MedicineDto medicineDto = MedicineMapper.toDto(medicineService.getMedicineById(id));
        return ResponseEntity.status(HttpStatus.OK).body(medicineDto);
    }


    /**
     * Creates a new medicine.
     *
     * @param dto The MedicineDto containing data for the new medicine
     * @return MedicineDto representing the created medicine
     */
    @PostMapping
    public ResponseEntity<MedicineDto> create(@RequestBody MedicineDto dto) {
        Category category = categoryService.getCategoryById(dto.categoryId());
        Medicine medicine = MedicineMapper.toEntity(dto, category);
        Medicine createdMedicine = medicineService.createMedicine(medicine);
        return ResponseEntity.status(HttpStatus.CREATED).body(MedicineMapper.toDto(createdMedicine));
    }


    /**
     * Updates an existing medicine by its ID.
     *
     * @param id  The ID of the medicine to update
     * @param dto The MedicineDto containing updated data
     * @return MedicineDto representing the updated medicine
     */
    @PutMapping("/{id}")
    public ResponseEntity<MedicineDto> update(@PathVariable Long id, @RequestBody MedicineDto dto) {
        Category category = categoryService.getCategoryById(dto.categoryId());
        Medicine medicine = MedicineMapper.toEntity(dto, category);
        MedicineDto updatedMedicineDto = MedicineMapper.toDto(medicineService.updateMedicine(id, medicine));
        return ResponseEntity.status(HttpStatus.OK).body(updatedMedicineDto);
    }


    /**
     * Deletes a medicine by its ID.
     *
     * @param id The ID of the medicine to delete
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.noContent().build();
    }
}
