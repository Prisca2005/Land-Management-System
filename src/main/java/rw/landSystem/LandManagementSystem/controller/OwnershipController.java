package rw.landSystem.LandManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.landSystem.LandManagementSystem.model.Ownership;
import rw.landSystem.LandManagementSystem.service.LandParcelService;
import rw.landSystem.LandManagementSystem.service.OwnershipService;
import rw.landSystem.LandManagementSystem.service.UserService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/ownerships")
public class OwnershipController {
    
    @Autowired
    private OwnershipService ownershipService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private LandParcelService landParcelService;
    
    @GetMapping
    public ResponseEntity<List<Ownership>> getAllOwnerships() {
        return ResponseEntity.ok(ownershipService.getAllOwnerships());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Ownership> getOwnershipById(@PathVariable Long id) {
        return ownershipService.getOwnershipById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Ownership>> getOwnershipsByOwner(@PathVariable Long ownerId) {
        return userService.getUserById(ownerId)
                .map(owner -> ResponseEntity.ok(ownershipService.getOwnershipsByOwner(owner)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/land/{landId}")
    public ResponseEntity<List<Ownership>> getOwnershipsByLand(@PathVariable Long landId) {
        return landParcelService.getLandParcelById(landId)
                .map(land -> ResponseEntity.ok(ownershipService.getOwnershipsByLand(land)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/current/land/{landId}")
    public ResponseEntity<Ownership> getCurrentOwnershipByLand(@PathVariable Long landId) {
        return landParcelService.getLandParcelById(landId)
                .flatMap(land -> ownershipService.getCurrentOwnership(land))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/current/owner/{ownerId}")
    public ResponseEntity<List<Ownership>> getCurrentOwnershipsByOwner(@PathVariable Long ownerId) {
        return userService.getUserById(ownerId)
                .map(owner -> ResponseEntity.ok(ownershipService.getCurrentOwnershipsByOwner(owner)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Ownership> createOwnership(@RequestBody Ownership ownership) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ownershipService.saveOwnership(ownership));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Ownership> updateOwnership(@PathVariable Long id, @RequestBody Ownership ownership) {
        return ownershipService.getOwnershipById(id)
                .map(existingOwnership -> {
                    ownership.setId(id);
                    return ResponseEntity.ok(ownershipService.saveOwnership(ownership));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}/end")
    public ResponseEntity<Ownership> endOwnership(@PathVariable Long id) {
        return ownershipService.getOwnershipById(id)
                .map(ownership -> ResponseEntity.ok(ownershipService.endOwnership(ownership, LocalDate.now())))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwnership(@PathVariable Long id) {
        return ownershipService.getOwnershipById(id)
                .map(ownership -> {
                    ownershipService.deleteOwnership(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}


