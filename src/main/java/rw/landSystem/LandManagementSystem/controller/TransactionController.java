package rw.landSystem.LandManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.landSystem.LandManagementSystem.model.Transaction;
import rw.landSystem.LandManagementSystem.service.LandParcelService;
import rw.landSystem.LandManagementSystem.service.TransactionService;
import rw.landSystem.LandManagementSystem.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private LandParcelService landParcelService;
    
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/land/{landId}")
    public ResponseEntity<List<Transaction>> getTransactionsByLand(@PathVariable Long landId) {
        return landParcelService.getLandParcelById(landId)
                .map(land -> ResponseEntity.ok(transactionService.getTransactionsByLand(land)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/from-owner/{userId}")
    public ResponseEntity<List<Transaction>> getTransactionsByFromOwner(@PathVariable Long userId) {
        return userService.getUserById(userId)
                .map(user -> ResponseEntity.ok(transactionService.getTransactionsByFromOwner(user)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/to-owner/{userId}")
    public ResponseEntity<List<Transaction>> getTransactionsByToOwner(@PathVariable Long userId) {
        return userService.getUserById(userId)
                .map(user -> ResponseEntity.ok(transactionService.getTransactionsByToOwner(user)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.saveTransaction(transaction));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        return transactionService.getTransactionById(id)
                .map(existingTransaction -> {
                    transaction.setId(id);
                    return ResponseEntity.ok(transactionService.saveTransaction(transaction));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        return transactionService.getTransactionById(id)
                .map(transaction -> {
                    transactionService.deleteTransaction(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
