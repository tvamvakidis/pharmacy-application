package gr.vamvakidis.pharmacy_application.service;

import gr.vamvakidis.pharmacy_application.entity.Category;
import gr.vamvakidis.pharmacy_application.exception.DuplicateKeyException;
import gr.vamvakidis.pharmacy_application.exception.ResourceNotFoundException;
import gr.vamvakidis.pharmacy_application.repository.CategoryRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    /**
     * Retrieves all categories from the database.
     *
     * @return List of all Category entities
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    /**
     * Retrieves a single category by its ID.
     * Throws ResourceNotFoundException if the category does not exist.
     *
     * @param id The ID of the category
     * @return The Category entity with the given ID
     */
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Category", id));
    }


    /**
     * Creates a new category in the database.
     *
     * @param category The Category entity to create
     * @return The saved Category entity
     */
    public Category createCategory(Category category) {
        try {
            return categoryRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateKeyException("category's name", category.getName());
        }
    }


    /**
     * Updates an existing category by its ID.
     * Throws ResourceNotFoundException if the category does not exist.
     *
     * @param id The ID of the category to update
     * @param category The details to update
     * @return The updated Category entity
     */
    public Category updateCategory(Long id, Category category) {
        Category existingCategory = getCategoryById(id);
        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());
        try {
            return categoryRepository.save(existingCategory);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateKeyException("category's name", category.getName());
        }
    }


    /**
     * Deletes an existing category by its ID.
     * Throws ResourceNotFoundException if the category does not exist.
     *
     * @param id The ID of the category to delete
     */
    public void deleteCategory(Long id) {
        Category existingCategory = getCategoryById(id);
        categoryRepository.delete(existingCategory);
    }
}
