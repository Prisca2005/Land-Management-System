package rw.landSystem.LandManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rw.landSystem.LandManagementSystem.model.Transaction;
import rw.landSystem.LandManagementSystem.service.LandTransferService;

import java.util.Map;

@RestController
@RequestMapping("/api/land-transfers")
public class LandTransferController {
    
    @Autowired
    private LandTransferService landTransferService;
    
    @PostMapping
    public ResponseEntity<?> transferLand(@RequestBody Map<String, Object> transferRequest) {
        try {
            Long landId = Long.valueOf(transferRequest.get("landId").toString());
            Long fromOwnerId = Long.valueOf(transferRequest.get("fromOwnerId").toString());
            Long toOwnerId = Long.valueOf(transferRequest.get("toOwnerId").toString());
            Double amount = Double.valueOf(transferRequest.get("amount").toString());
            
            Transaction transaction = landTransferService.transferLand(landId, fromOwnerId, toOwnerId, amount);
            return ResponseEntity.ok(transaction);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred during land transfer: " + e.getMessage());
        }
    }
}
