package rw.landSystem.LandManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.landSystem.LandManagementSystem.model.Document;
import rw.landSystem.LandManagementSystem.service.DocumentService;
import rw.landSystem.LandManagementSystem.service.LandParcelService;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
    
    @Autowired
    private DocumentService documentService;
    
    @Autowired
    private LandParcelService landParcelService;
    
    @GetMapping
    public ResponseEntity<List<Document>> getAllDocuments() {
        return ResponseEntity.ok(documentService.getAllDocuments());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocumentById(@PathVariable Long id) {
        return documentService.getDocumentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/land/{landId}")
    public ResponseEntity<List<Document>> getDocumentsByLand(@PathVariable Long landId) {
        return landParcelService.getLandParcelById(landId)
                .map(land -> ResponseEntity.ok(documentService.getDocumentsByLand(land)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/type/{documentType}")
    public ResponseEntity<List<Document>> getDocumentsByType(@PathVariable String documentType) {
        return ResponseEntity.ok(documentService.getDocumentsByType(documentType));
    }
    
    @PostMapping
    public ResponseEntity<Document> createDocument(@RequestBody Document document) {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentService.saveDocument(document));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Document> updateDocument(@PathVariable Long id, @RequestBody Document document) {
        return documentService.getDocumentById(id)
                .map(existingDocument -> {
                    document.setId(id);
                    return ResponseEntity.ok(documentService.saveDocument(document));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        return documentService.getDocumentById(id)
                .map(document -> {
                    documentService.deleteDocument(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}


