package gr.vamvakidis.pharmacy_application.service;

import gr.vamvakidis.pharmacy_application.entity.Category;
import gr.vamvakidis.pharmacy_application.entity.Medicine;
import gr.vamvakidis.pharmacy_application.exception.ResourceNotFoundException;
import gr.vamvakidis.pharmacy_application.repository.MedicineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;
    private final CategoryService categoryService;

    public MedicineService(MedicineRepository medicineRepository, CategoryService categoryService) {
        this.medicineRepository = medicineRepository;
        this.categoryService = categoryService;
    }


    /**
     * Retrieves all medicines from the database.
     *
     * @return List of all Medicine entities
     */
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }


    /**
     * Retrieves a single medicine by its ID.
     * Throws ResourceNotFoundException if the medicine does not exist.
     *
     * @param id The ID of the medicine
     * @return The Medicine entity with the given ID
     */
    public Medicine getMedicineById(Long id) {
        return medicineRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Medicine", id));
    }


    /**
     * Creates a new medicine in the database.
     * Ensures that the associated category exists by fetching it from the CategoryService.
     *
     * @param medicine The Medicine entity to create
     * @return The saved Medicine entity
     */
    public Medicine createMedicine(Medicine medicine) {
        Category category = categoryService.getCategoryById(medicine.getCategory().getId());
        medicine.setCategory(category);

        return medicineRepository.save(medicine);
    }


    /**
     * Updates an existing medicine by its ID.
     * Ensures that the associated category exists if provided.
     * Throws ResourceNotFoundException if the medicine does not exist.
     *
     * @param id The ID of the medicine to update
     * @param medicineDetails The details to update
     * @return The updated Medicine entity
     */
    public Medicine updateMedicine(Long id, Medicine medicineDetails) {
        Medicine medicine = getMedicineById(id);

        medicine.setName(medicineDetails.getName());
        medicine.setCode(medicineDetails.getCode());
        medicine.setPrice(medicineDetails.getPrice());
        medicine.setStock(medicineDetails.getStock());

        if (medicineDetails.getCategory() != null) {
            Category category = categoryService.getCategoryById(medicineDetails.getCategory().getId());
            medicine.setCategory(category);
        }

        return medicineRepository.save(medicine);
    }


    /**
     * Deletes an existing medicine by its ID.
     * Throws ResourceNotFoundException if the medicine does not exist.
     *
     * @param id The ID of the medicine to delete
     */
    public void deleteMedicine(Long id) {
        Medicine existingMedicine = getMedicineById(id);
        medicineRepository.delete(existingMedicine);
    }
}
