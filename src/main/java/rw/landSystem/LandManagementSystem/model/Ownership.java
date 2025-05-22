package rw.landSystem.LandManagementSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Ownership {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private User owner;
    
    @ManyToOne
    private LandParcel land;
    
    private LocalDate ownershipStart;
    private LocalDate ownershipEnd;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }
    
    public LandParcel getLand() { return land; }
    public void setLand(LandParcel land) { this.land = land; }
    
    public LocalDate getOwnershipStart() { return ownershipStart; }
    public void setOwnershipStart(LocalDate ownershipStart) { this.ownershipStart = ownershipStart; }
    
    public LocalDate getOwnershipEnd() { return ownershipEnd; }
    public void setOwnershipEnd(LocalDate ownershipEnd) { this.ownershipEnd = ownershipEnd; }
    
    // Helper method to check if ownership is active (no end date)
    public boolean isActive() {
        return ownershipEnd == null;
    }
}

