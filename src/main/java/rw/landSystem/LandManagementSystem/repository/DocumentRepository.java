package rw.landSystem.LandManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.landSystem.LandManagementSystem.model.Document;
import rw.landSystem.LandManagementSystem.model.LandParcel;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByLand(LandParcel land);
    List<Document> findByDocumentType(String documentType);
}

