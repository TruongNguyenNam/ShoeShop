package com.example.back_end.controller;

import com.example.back_end.dto.admin.response.CampaignResponse;
import com.example.back_end.service.admin.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/campaign")
public class CampaignController {
    @Autowired
    private CampaignService campaignService;

    @GetMapping
    public ResponseEntity<List<CampaignResponse>> getAllCampaigns() {
        List<CampaignResponse> campaigns = campaignService.getAll();
        return ResponseEntity.ok(campaigns);
    }
}
