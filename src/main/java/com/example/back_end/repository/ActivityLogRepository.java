package com.example.back_end.repository;


import com.example.back_end.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ActivityLogRepository extends JpaRepository<ActivityLog,Integer> {


}
