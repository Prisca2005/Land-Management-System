package rw.landSystem.LandManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.landSystem.LandManagementSystem.model.Document;
import rw.landSystem.LandManagementSystem.model.LandParcel;
import rw.landSystem.LandManagementSystem.repository.DocumentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {
    
    @Autowired
    private DocumentRepository documentRepository;
    
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }
    
    public Optional<Document> getDocumentById(Long id) {
        return documentRepository.findById(id);
    }
    
    public List<Document> getDocumentsByLand(LandParcel land) {
        return documentRepository.findByLand(land);
    }
    
    public List<Document> getDocumentsByType(String documentType) {
        return documentRepository.findByDocumentType(documentType);
    }
    
    public Document saveDocument(Document document) {
        return documentRepository.save(document);
    }
    
    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }
}