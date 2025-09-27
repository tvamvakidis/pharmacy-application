package gr.vamvakidis.pharmacy_application.controller;

import gr.vamvakidis.pharmacy_application.dto.MedicineDto;
import gr.vamvakidis.pharmacy_application.entity.Category;
import gr.vamvakidis.pharmacy_application.entity.Medicine;
import gr.vamvakidis.pharmacy_application.mapper.MedicineMapper;
import gr.vamvakidis.pharmacy_application.service.CategoryService;
import gr.vamvakidis.pharmacy_application.service.MedicineService;
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
    public List<MedicineDto> getAll() {
        return medicineService.getAllMedicines().stream().map(MedicineMapper::toDto).collect(Collectors.toList());
    }


    /**
     * Retrieves a single medicine by its ID.
     *
     * @param id The ID of the medicine to retrieve
     * @return MedicineDto representing the requested medicine
     */
    @GetMapping("/{id}")
    public MedicineDto getById(@PathVariable Long id) {
        return MedicineMapper.toDto(medicineService.getMedicineById(id));
    }


    /**
     * Creates a new medicine.
     *
     * @param dto The MedicineDto containing data for the new medicine
     * @return MedicineDto representing the created medicine
     */
    @PostMapping
    public MedicineDto create(@RequestBody MedicineDto dto) {
        Category category = categoryService.getCategoryById(dto.categoryId());
        Medicine medicine = MedicineMapper.toEntity(dto, category);
        return MedicineMapper.toDto(medicineService.createMedicine(medicine));
    }


    /**
     * Updates an existing medicine by its ID.
     *
     * @param id The ID of the medicine to update
     * @param dto The MedicineDto containing updated data
     * @return MedicineDto representing the updated medicine
     */
    @PutMapping("/{id}")
    public MedicineDto update(@PathVariable Long id, @RequestBody MedicineDto dto) {
        Category category = categoryService.getCategoryById(dto.categoryId());
        Medicine medicine = MedicineMapper.toEntity(dto, category);
        return MedicineMapper.toDto(medicineService.updateMedicine(id, medicine));
    }


    /**
     * Deletes a medicine by its ID.
     *
     * @param id The ID of the medicine to delete
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        medicineService.deleteMedicine(id);
    }
}
