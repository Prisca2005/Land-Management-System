package rw.landSystem.LandManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.landSystem.LandManagementSystem.model.Request;
import rw.landSystem.LandManagementSystem.service.RequestService;
import rw.landSystem.LandManagementSystem.service.UserService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {
    
    @Autowired
    private RequestService requestService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public ResponseEntity<List<Request>> getAllRequests() {
        return ResponseEntity.ok(requestService.getAllRequests());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable Long id) {
        return requestService.getRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/requester/{requesterId}")
    public ResponseEntity<List<Request>> getRequestsByRequester(@PathVariable Long requesterId) {
        return userService.getUserById(requesterId)
                .map(requester -> ResponseEntity.ok(requestService.getRequestsByRequester(requester)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Request>> getRequestsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(requestService.getRequestsByStatus(status));
    }
    
    @GetMapping("/type/{requestType}")
    public ResponseEntity<List<Request>> getRequestsByType(@PathVariable String requestType) {
        return ResponseEntity.ok(requestService.getRequestsByType(requestType));
    }
    
    @PostMapping
    public ResponseEntity<Request> createRequest(@RequestBody Request request) {
        request.setRequestDate(LocalDate.now());
        request.setStatus("PENDING");
        return ResponseEntity.status(HttpStatus.CREATED).body(requestService.saveRequest(request));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Request> updateRequest(@PathVariable Long id, @RequestBody Request request) {
        return requestService.getRequestById(id)
                .map(existingRequest -> {
                    request.setId(id);
                    return ResponseEntity.ok(requestService.saveRequest(request));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}/approve")
    public ResponseEntity<Request> approveRequest(@PathVariable Long id) {
        return requestService.getRequestById(id)
                .map(request -> {
                    request.setStatus("APPROVED");
                    return ResponseEntity.ok(requestService.saveRequest(request));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}/reject")
    public ResponseEntity<Request> rejectRequest(@PathVariable Long id) {
        return requestService.getRequestById(id)
                .map(request -> {
                    request.setStatus("REJECTED");
                    return ResponseEntity.ok(requestService.saveRequest(request));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        return requestService.getRequestById(id)
                .map(request -> {
                    requestService.deleteRequest(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
