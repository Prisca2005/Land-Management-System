package rw.landSystem.LandManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.landSystem.LandManagementSystem.model.Request;
import rw.landSystem.LandManagementSystem.model.User;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByRequester(User requester);
    List<Request> findByStatus(String status);
    List<Request> findByRequestType(String requestType);
}
