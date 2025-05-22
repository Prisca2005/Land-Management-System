package rw.landSystem.LandManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.landSystem.LandManagementSystem.model.LandParcel;
import rw.landSystem.LandManagementSystem.service.LandParcelService;
import rw.landSystem.LandManagementSystem.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/land-parcels")
public class LandParcelController {
    
    @Autowired
    private LandParcelService landParcelService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public ResponseEntity<List<LandParcel>> getAllLandParcels() {
        return ResponseEntity.ok(landParcelService.getAllLandParcels());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<LandParcel> getLandParcelById(@PathVariable Long id) {
        return landParcelService.getLandParcelById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<LandParcel>> getLandParcelsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(landParcelService.getLandParcelsByStatus(status));
    }
    
    @GetMapping("/registered-by/{userId}")
    public ResponseEntity<List<LandParcel>> getLandParcelsByRegisteredBy(@PathVariable Long userId) {
        return userService.getUserById(userId)
                .map(user -> ResponseEntity.ok(landParcelService.getLandParcelsByRegisteredBy(user)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<LandParcel> createLandParcel(@RequestBody LandParcel landParcel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(landParcelService.saveLandParcel(landParcel));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<LandParcel> updateLandParcel(@PathVariable Long id, @RequestBody LandParcel landParcel) {
        return landParcelService.getLandParcelById(id)
                .map(existingLandParcel -> {
                    landParcel.setId(id);
                    return ResponseEntity.ok(landParcelService.saveLandParcel(landParcel));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLandParcel(@PathVariable Long id) {
        return landParcelService.getLandParcelById(id)
                .map(landParcel -> {
                    landParcelService.deleteLandParcel(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
