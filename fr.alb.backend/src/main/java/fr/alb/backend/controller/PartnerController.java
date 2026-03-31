package fr.alb.backend.controller;

import fr.alb.backend.model.entity.Partner;
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
    public List<Partner> getAll() {
        return partnerService.getAll();
    }

    @GetMapping("/{id}")
    public Partner getById(@PathVariable Long id) {
        return partnerService.getById(id)
                .orElseThrow(() -> new RuntimeException("Le partenaire n'a pas été trouvé"));
    }

    @PostMapping
    public Partner create(@RequestBody Partner partner) {
        return partnerService.create(partner);
    }

    @PutMapping("/{id}")
    public Partner update(@PathVariable Long id, @RequestBody Partner partner) {
        return partnerService.update(id, partner);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        partnerService.delete(id);
    }
}