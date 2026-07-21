package fr.alb.backend.controller;

import fr.alb.backend.dto.request.CreatePartnerRequest;
import fr.alb.backend.dto.request.UpdatePartnerRequest;
import fr.alb.backend.dto.response.PartnerResponse;
import fr.alb.backend.service.PartnerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partners")
public class PartnerController {

    private final PartnerService partnerService;

    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @GetMapping
    public List<PartnerResponse> getAll() {
        return partnerService.getAll();
    }

    @GetMapping("/{id}")
    public PartnerResponse getById(@PathVariable Long id) {
        return partnerService.getById(id);
    }

    @PostMapping
    public PartnerResponse create(@RequestBody CreatePartnerRequest partner) {
        return partnerService.create(partner);
    }

    @PutMapping("/{id}")
    public PartnerResponse update(@PathVariable Long id, @RequestBody UpdatePartnerRequest request) {
        return partnerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        partnerService.delete(id);
    }
}