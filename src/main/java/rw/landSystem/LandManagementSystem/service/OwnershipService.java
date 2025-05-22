package rw.landSystem.LandManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.landSystem.LandManagementSystem.model.LandParcel;
import rw.landSystem.LandManagementSystem.model.Ownership;
import rw.landSystem.LandManagementSystem.model.User;
import rw.landSystem.LandManagementSystem.repository.OwnershipRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OwnershipService {
    
    @Autowired
    private OwnershipRepository ownershipRepository;
    
    public List<Ownership> getAllOwnerships() {
        return ownershipRepository
                .findAll()
                .stream()
                .filter(Ownership::isActive)
                .collect(Collectors.toList());
    }
    
    public Optional<Ownership> getOwnershipById(Long id) {
        return ownershipRepository.findById(id);
    }
    
    public List<Ownership> getOwnershipsByOwner(User owner) {
        return ownershipRepository.findByOwner(owner);
    }
    
    public List<Ownership> getOwnershipsByLand(LandParcel land) {
        return ownershipRepository.findByLand(land);
    }
    
    public Optional<Ownership> getCurrentOwnership(LandParcel land) {
        return ownershipRepository.findCurrentOwnership(land);
    }
    
    public List<Ownership> getCurrentOwnershipsByOwner(User owner) {
        return ownershipRepository.findCurrentOwnershipsByOwner(owner);
    }
    
    public Ownership saveOwnership(Ownership ownership) {
        return ownershipRepository.save(ownership);
    }
    
    public Ownership endOwnership(Ownership ownership, LocalDate endDate) {
        ownership.setOwnershipEnd(endDate);
        return ownershipRepository.save(ownership);
    }
    
    public void deleteOwnership(Long id) {
        ownershipRepository.deleteById(id);
    }
}



