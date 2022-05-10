package com.treehouseapp.service.serviceInterfaces;

import com.treehouseapp.payload.AdminInfoResponse;
import com.treehouseapp.payload.AppStatisticsResponse;
import org.springframework.http.ResponseEntity;

public interface AdminService {
    ResponseEntity<AdminInfoResponse> getAdminInfo(String email);
    AppStatisticsResponse getStatistics(Integer pageNo, Integer pageSize);

    Long allUsersByGender(String gender);

    Long ordersByStatus(String status);
}
