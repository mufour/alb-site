package fr.alb.backend.mapper;

import org.springframework.stereotype.Component;

import fr.alb.backend.dto.request.CreatePartnerRequest;
import fr.alb.backend.dto.request.UpdatePartnerRequest;
import fr.alb.backend.dto.response.PartnerResponse;
import fr.alb.backend.model.entity.Partner;

@Component
public class PartnerMapper {

    public Partner toEntity(CreatePartnerRequest request) {

        Partner partner = new Partner();

        partner.setName(request.getName());
        partner.setType(request.getType());
        partner.setWebsiteUrl(request.getWebsiteUrl());
        partner.setDisplayOrder(request.getDisplayOrder());

        return partner;
    }

    public PartnerResponse toResponse(Partner partner) {

        PartnerResponse response = new PartnerResponse();

        response.setId(partner.getId());
        response.setName(partner.getName());
        response.setType(partner.getType());
        response.setWebsiteUrl(partner.getWebsiteUrl());
        response.setDisplayOrder(partner.getDisplayOrder());

        if (partner.getImage() != null) {
            response.setImageId(partner.getImage().getId());
    }

        return response;
    }

    public void updateEntity(UpdatePartnerRequest request, Partner partner) {

        partner.setName(request.getName());
        partner.setType(request.getType());
        partner.setWebsiteUrl(request.getWebsiteUrl());
        partner.setDisplayOrder(request.getDisplayOrder());
    }
}