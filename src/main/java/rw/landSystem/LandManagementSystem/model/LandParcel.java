package rw.landSystem.LandManagementSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class LandParcel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String parcelNumber;
    private String location;
    private Double sizeInHectares;
    private String status; // REGISTERED, PENDING, DISPUTED
    
    @ManyToOne
    private User registeredBy;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getParcelNumber() { return parcelNumber; }
    public void setParcelNumber(String parcelNumber) { this.parcelNumber = parcelNumber; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public Double getSizeInHectares() { return sizeInHectares; }
    public void setSizeInHectares(Double sizeInHectares) { this.sizeInHectares = sizeInHectares; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public User getRegisteredBy() { return registeredBy; }
    public void setRegisteredBy(User registeredBy) { this.registeredBy = registeredBy; }
}

