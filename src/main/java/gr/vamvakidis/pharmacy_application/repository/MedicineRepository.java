package gr.vamvakidis.pharmacy_application.repository;

import gr.vamvakidis.pharmacy_application.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}
