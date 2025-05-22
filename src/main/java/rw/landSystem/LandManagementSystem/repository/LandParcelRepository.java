package rw.landSystem.LandManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.landSystem.LandManagementSystem.model.LandParcel;
import rw.landSystem.LandManagementSystem.model.User;

import java.util.List;

@Repository
public interface LandParcelRepository extends JpaRepository<LandParcel, Long> {
    List<LandParcel> findByStatus(String status);
    List<LandParcel> findByRegisteredBy(User registeredBy);
}
