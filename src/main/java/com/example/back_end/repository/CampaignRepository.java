package com.example.back_end.repository;


import com.example.back_end.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CampaignRepository extends JpaRepository<Campaign,Integer> {


}
