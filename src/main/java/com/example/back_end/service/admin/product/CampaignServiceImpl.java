package com.example.back_end.service.admin.product;

import com.example.back_end.dto.admin.response.CampaignResponse;
import com.example.back_end.entity.Campaign;
import com.example.back_end.exception.AppException;
import com.example.back_end.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampaignServiceImpl implements CampaignService{
    private final CampaignRepository campaignRepository;
    @Autowired
    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public List<CampaignResponse> getAll() {
        List<Campaign> campaigns = campaignRepository.findAll();
        if(campaigns.isEmpty()){
            throw  new AppException("campaigns is not found");
        }
        return campaigns.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private CampaignResponse mapToResponse(Campaign campaign){
        CampaignResponse response = new CampaignResponse();
        response.setId(campaign.getId());
        response.setName(campaign.getName());
        response.setStartDate(campaign.getStartDate());
        response.setEndDate(campaign.getEndDate());
        return response;
    }

}
