package com.gymbuddy.repository;

import com.gymbuddy.model.shared.WorkingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkingInfoRepository extends JpaRepository<WorkingInfo, Long>
{
}
