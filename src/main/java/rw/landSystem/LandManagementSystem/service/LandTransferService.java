package rw.landSystem.LandManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.landSystem.LandManagementSystem.model.LandParcel;
import rw.landSystem.LandManagementSystem.model.Ownership;
import rw.landSystem.LandManagementSystem.model.Transaction;
import rw.landSystem.LandManagementSystem.model.User;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class LandTransferService {
    
    @Autowired
    private LandParcelService landParcelService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private OwnershipService ownershipService;
    
    @Autowired
    private TransactionService transactionService;
    
    @Transactional
    public Transaction transferLand(Long landId, Long fromOwnerId, Long toOwnerId, Double amount) {
        // Get the land parcel
        LandParcel land = landParcelService.getLandParcelById(landId)
                .orElseThrow(() -> new IllegalStateException("Land parcel not found"));
        
        // Get the users
        User fromOwner = userService.getUserById(fromOwnerId)
                .orElseThrow(() -> new IllegalStateException("From owner not found"));
        
        User toOwner = userService.getUserById(toOwnerId)
                .orElseThrow(() -> new IllegalStateException("To owner not found"));
        
        // Verify current ownership
        Optional<Ownership> currentOwnership = ownershipService.getCurrentOwnership(land);
        if (currentOwnership.isEmpty() || !currentOwnership.get().getOwner().getId().equals(fromOwnerId)) {
            throw new IllegalStateException("The from owner is not the current owner of this land");
        }
        
        // End current ownership
        Ownership oldOwnership = currentOwnership.get();
        ownershipService.endOwnership(oldOwnership, LocalDate.now());
        
        // Create new ownership
        Ownership newOwnership = new Ownership();
        newOwnership.setLand(land);
        newOwnership.setOwner(toOwner);
        newOwnership.setOwnershipStart(LocalDate.now());
        ownershipService.saveOwnership(newOwnership);
        
        // Record transaction
        Transaction transaction = new Transaction();
        transaction.setLand(land);
        transaction.setFromOwner(fromOwner);
        transaction.setToOwner(toOwner);
        transaction.setAmount(amount);
        transaction.setTransactionDate(LocalDate.now());
        transaction.setTransactionType("SALE");
        transaction.setStatus("COMPLETED");
        
        return transactionService.saveTransaction(transaction);
    }
}

