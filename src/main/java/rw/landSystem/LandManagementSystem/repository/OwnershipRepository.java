package rw.landSystem.LandManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rw.landSystem.LandManagementSystem.model.LandParcel;
import rw.landSystem.LandManagementSystem.model.Ownership;
import rw.landSystem.LandManagementSystem.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnershipRepository extends JpaRepository<Ownership, Long> {
    List<Ownership> findByOwner(User owner);
    List<Ownership> findByLand(LandParcel land);
    
    @Query("SELECT o FROM Ownership o WHERE o.land = ?1 AND o.ownershipEnd IS NULL")
    Optional<Ownership> findCurrentOwnership(LandParcel land);
    
    @Query("SELECT o FROM Ownership o WHERE o.owner = ?1 AND o.ownershipEnd IS NULL")
    List<Ownership> findCurrentOwnershipsByOwner(User owner);
}
