package rw.landSystem.LandManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.landSystem.LandManagementSystem.model.LandParcel;
import rw.landSystem.LandManagementSystem.model.Transaction;
import rw.landSystem.LandManagementSystem.model.User;
import rw.landSystem.LandManagementSystem.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }
    
    public List<Transaction> getTransactionsByLand(LandParcel land) {
        return transactionRepository.findByLand(land);
    }
    
    public List<Transaction> getTransactionsByFromOwner(User fromOwner) {
        return transactionRepository.findByFromOwner(fromOwner);
    }
    
    public List<Transaction> getTransactionsByToOwner(User toOwner) {
        return transactionRepository.findByToOwner(toOwner);
    }
    
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
    
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
