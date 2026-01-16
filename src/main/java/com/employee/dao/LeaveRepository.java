
package com.employee.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.employee.model.LeaveRequest;
public interface LeaveRepository extends JpaRepository<LeaveRequest, Long> {
 List<LeaveRequest> findByEmployeeId(Long empId);
}
