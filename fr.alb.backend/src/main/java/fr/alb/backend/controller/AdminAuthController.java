package fr.alb.backend.controller;

import java.security.Principal;

import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Minimal browser authentication contract. The login and logout POST endpoints
 * are implemented by Spring Security's filters, not by this controller.
 */
@RestController
@RequestMapping("/api/auth")
public class AdminAuthController {

    @GetMapping("/csrf")
    public ResponseEntity<CsrfResponse> csrf(CsrfToken token) {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.noStore())
                .body(new CsrfResponse(token.getHeaderName(), token.getToken()));
    }

    @GetMapping("/me")
    public ResponseEntity<AdminProfile> me(Principal principal) {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.noStore())
                .body(new AdminProfile(principal.getName()));
    }

    public record CsrfResponse(String headerName, String token) {
    }

    public record AdminProfile(String username) {
    }
}
