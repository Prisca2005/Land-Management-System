package rw.landSystem.LandManagementSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private LandParcel land;
    
    @ManyToOne
    private User fromOwner;
    
    @ManyToOne
    private User toOwner;
    
    private Double amount;
    private LocalDate transactionDate;
    private String transactionType; // SALE, INHERITANCE, GIFT
    private String status; // COMPLETED, PENDING, CANCELLED
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public LandParcel getLand() { return land; }
    public void setLand(LandParcel land) { this.land = land; }
    
    public User getFromOwner() { return fromOwner; }
    public void setFromOwner(User fromOwner) { this.fromOwner = fromOwner; }
    
    public User getToOwner() { return toOwner; }
    public void setToOwner(User toOwner) { this.toOwner = toOwner; }
    
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    
    public LocalDate getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDate transactionDate) { this.transactionDate = transactionDate; }
    
    public String getTransactionType() { return transactionType; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

