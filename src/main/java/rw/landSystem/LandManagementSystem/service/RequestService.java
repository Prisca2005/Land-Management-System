package rw.landSystem.LandManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.landSystem.LandManagementSystem.model.Request;
import rw.landSystem.LandManagementSystem.model.User;
import rw.landSystem.LandManagementSystem.repository.RequestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    
    @Autowired
    private RequestRepository requestRepository;
    
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }
    
    public Optional<Request> getRequestById(Long id) {
        return requestRepository.findById(id);
    }
    
    public List<Request> getRequestsByRequester(User requester) {
        return requestRepository.findByRequester(requester);
    }
    
    public List<Request> getRequestsByStatus(String status) {
        return requestRepository.findByStatus(status);
    }
    
    public List<Request> getRequestsByType(String requestType) {
        return requestRepository.findByRequestType(requestType);
    }
    
    public Request saveRequest(Request request) {
        return requestRepository.save(request);
    }
    
    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }
}
