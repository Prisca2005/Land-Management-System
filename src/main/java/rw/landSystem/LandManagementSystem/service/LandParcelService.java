package rw.landSystem.LandManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.landSystem.LandManagementSystem.model.LandParcel;
import rw.landSystem.LandManagementSystem.model.User;
import rw.landSystem.LandManagementSystem.repository.LandParcelRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LandParcelService {
    
    @Autowired
    private LandParcelRepository landParcelRepository;
    
    public List<LandParcel> getAllLandParcels() {
        return landParcelRepository.findAll();
    }
    
    public Optional<LandParcel> getLandParcelById(Long id) {
        return landParcelRepository.findById(id);
    }
    
    public List<LandParcel> getLandParcelsByStatus(String status) {
        return landParcelRepository.findByStatus(status);
    }
    
    public List<LandParcel> getLandParcelsByRegisteredBy(User registeredBy) {
        return landParcelRepository.findByRegisteredBy(registeredBy);
    }
    
    public LandParcel saveLandParcel(LandParcel landParcel) {
        return landParcelRepository.save(landParcel);
    }
    
    public void deleteLandParcel(Long id) {
        landParcelRepository.deleteById(id);
    }
}
