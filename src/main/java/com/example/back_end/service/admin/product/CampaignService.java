package com.example.back_end.service.admin.product;

import com.example.back_end.dto.admin.response.CampaignResponse;

import java.util.List;

public interface CampaignService {
    List<CampaignResponse> getAll();

}
