package gr.vamvakidis.pharmacy_application.controller;

import gr.vamvakidis.pharmacy_application.entity.Category;
import gr.vamvakidis.pharmacy_application.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    /**
     * Retrieves all categories.
     *
     * @return ResponseEntity containing the list of all categories with HTTP status 200 OK
     */
    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }


    /**
     * Retrieves a category by its ID.
     * Returns 404 Not Found if the category does not exist.
     *
     * @param id The ID of the category to retrieve
     * @return ResponseEntity containing the category and HTTP status 200 OK,
     * or HTTP status 404 Not Found if it does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(category);
        }
    }


    /**
     * Creates a new category.
     *
     * @param category The Category entity to create
     * @return ResponseEntity containing the created category with HTTP status 201 Created
     */
    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Category newCategory = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }


    /**
     * Updates an existing category by its ID.
     *
     * @param id       The ID of the category to update
     * @param category The Category entity containing updated details
     * @return ResponseEntity containing the updated category with HTTP status 200 OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(id, category);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCategory);
    }


    /**
     * Deletes a category by its ID.
     *
     * @param id The ID of the category to delete
     * @return ResponseEntity with HTTP status 200 OK and no body
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
