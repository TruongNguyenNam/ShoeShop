package com.example.back_end.service.admin;

import com.example.back_end.dto.admin.response.CampaignResponse;

import java.util.List;

public interface CampaignService {
    List<CampaignResponse> getAll();

}
