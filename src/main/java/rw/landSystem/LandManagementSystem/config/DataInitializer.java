package rw.landSystem.LandManagementSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rw.landSystem.LandManagementSystem.model.*;
import rw.landSystem.LandManagementSystem.service.*;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;
    
    @Autowired
    private LandParcelService landParcelService;
    
    @Autowired
    private OwnershipService ownershipService;
    
    @Autowired
    private DocumentService documentService;
    
    @Autowired
    private RequestService requestService;
    
    @Override
    public void run(String... args) throws Exception {
        // Create users
        User admin = new User();
        admin.setFullName("Admin User");
        admin.setEmail("admin@land.rw");
        admin.setPassword("password"); // In a real app, this would be encrypted
        admin.setRole("ADMIN");
        userService.saveUser(admin);
        
        User owner1 = new User();
        owner1.setFullName("John Doe");
        owner1.setEmail("john@example.com");
        owner1.setPassword("password");
        owner1.setRole("OWNER");
        userService.saveUser(owner1);
        
        User owner2 = new User();
        owner2.setFullName("Jane Smith");
        owner2.setEmail("jane@example.com");
        owner2.setPassword("password");
        owner2.setRole("OWNER");
        userService.saveUser(owner2);
        
        // Create land parcels
        LandParcel land1 = new LandParcel();
        land1.setParcelNumber("LP001");
        land1.setLocation("Kigali, Sector 1");
        land1.setSizeInHectares(2.5);
        land1.setStatus("REGISTERED");
        land1.setRegisteredBy(admin);
        landParcelService.saveLandParcel(land1);
        
        LandParcel land2 = new LandParcel();
        land2.setParcelNumber("LP002");
        land2.setLocation("Kigali, Sector 2");
        land2.setSizeInHectares(1.8);
        land2.setStatus("REGISTERED");
        land2.setRegisteredBy(admin);
        landParcelService.saveLandParcel(land2);
        
        // Create ownerships
        Ownership ownership1 = new Ownership();
        ownership1.setLand(land1);
        ownership1.setOwner(owner1);
        ownership1.setOwnershipStart(LocalDate.now().minusMonths(6));
        ownershipService.saveOwnership(ownership1);
        
        Ownership ownership2 = new Ownership();
        ownership2.setLand(land2);
        ownership2.setOwner(owner2);
        ownership2.setOwnershipStart(LocalDate.now().minusMonths(3));
        ownershipService.saveOwnership(ownership2);
        
        // Create documents
        Document doc1 = new Document();
        doc1.setLand(land1);
        doc1.setDocumentType("TITLE_DEED");
        doc1.setDocumentNumber("DOC001");
        doc1.setIssueDate(LocalDate.now().minusMonths(6));
        doc1.setDescription("Original title deed for parcel LP001");
        documentService.saveDocument(doc1);
        
        Document doc2 = new Document();
        doc2.setLand(land2);
        doc2.setDocumentType("TITLE_DEED");
        doc2.setDocumentNumber("DOC002");
        doc2.setIssueDate(LocalDate.now().minusMonths(3));
        doc2.setDescription("Original title deed for parcel LP002");
        documentService.saveDocument(doc2);
        
        // Create requests
        Request request1 = new Request();
        request1.setRequester(owner1);
        request1.setRequestType("TRANSFER");
        request1.setStatus("PENDING");
        request1.setRequestDate(LocalDate.now().minusDays(5));
        requestService.saveRequest(request1);
        
        Request request2 = new Request();
        request2.setRequester(owner2);
        request2.setRequestType("TITLE_UPDATE");
        request2.setStatus("APPROVED");
        request2.setRequestDate(LocalDate.now().minusDays(10));
        requestService.saveRequest(request2);
    }
}

