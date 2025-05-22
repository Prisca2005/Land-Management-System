package rw.landSystem.LandManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.landSystem.LandManagementSystem.model.LandParcel;
import rw.landSystem.LandManagementSystem.model.Transaction;
import rw.landSystem.LandManagementSystem.model.User;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByLand(LandParcel land);
    List<Transaction> findByFromOwner(User fromOwner);
    List<Transaction> findByToOwner(User toOwner);
}
