
package com.employee.service;
import java.util.List;
import com.employee.dto.*;
import com.employee.model.LeaveRequest;
public interface LeaveService {
 LeaveRequest applyLeave(LeaveApplyDTO dto);
 LeaveRequest updateStatus(Long id, LeaveStatusUpdateDTO dto);
 List<LeaveRequest> getByEmployee(Long empId);
}
