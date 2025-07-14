package br.com.train.springkeycloack.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/roles")
public class MsController {

    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN_READ', 'ADMIN_WRITE')")
//    @PreAuthorize("hasAnyRole('ADMIN_READ', 'ADMIN_WRITE')")
    public ResponseEntity<?> doAdmin() {
        return ResponseEntity.ok("Access granted to admin role");
    }

    @GetMapping("/operations")
    @PreAuthorize("hasAnyAuthority('OPERATION_READ', 'OPERATION_WRITE')")
//    @PreAuthorize("hasAnyRole('OPERATION_READ', 'OPERATION_WRITE')")
    public ResponseEntity<?> doOperations() {
        return ResponseEntity.ok("Access granted to operations role");
    }
}
