package gr.vamvakidis.pharmacy_application.repository;

import gr.vamvakidis.pharmacy_application.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
