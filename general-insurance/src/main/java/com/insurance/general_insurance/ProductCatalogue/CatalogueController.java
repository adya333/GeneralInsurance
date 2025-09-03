package com.insurance.general_insurance.ProductCatalogue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing the Catalogue of available insurance policies.
 * This includes viewing, filtering, and adding/removing policies.
 */
@RestController
@RequestMapping("/catalogue") // URL path for the catalogue module
public class CatalogueController {

    private final CatalogueService catalogueService;

    @Autowired
    public CatalogueController(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    // Admin: View all policies in the catalogue (admin sees all policies)
    @GetMapping("/list") // This is working
    public ResponseEntity<List<Policy>> getAllPolicies() {
        List<Policy> policies = catalogueService.getAllPolicies();
        return new ResponseEntity<>(policies, HttpStatus.OK);
    }

    // Admin: Filter and search policies based on type, coverage, price, etc.
    @GetMapping("/filter") // Working
    public ResponseEntity<List<Policy>> filterPolicies(
        @RequestParam(required = false) String policyType,
        @RequestParam(required = false) Double maxPrice
    ) {
        List<Policy> policies = catalogueService.filterPolicies(policyType, maxPrice);
        return new ResponseEntity<>(policies, HttpStatus.OK);
    }

    // User: View details of a specific policy (Read-only for users)
    @GetMapping("/{policyId}") // working
    public ResponseEntity<Policy> getPolicyDetails(@PathVariable Long policyId) {
        Policy policy = catalogueService.getPolicyDetails(policyId);
        return new ResponseEntity<>(policy, HttpStatus.OK);
    }

    // Admin: Add a new policy to the catalogue
    @PostMapping("/add") // working
    public ResponseEntity<Policy> addPolicy(@RequestBody Policy policy) {
        Policy newPolicy = catalogueService.addPolicy(policy);
        return new ResponseEntity<>(newPolicy, HttpStatus.CREATED);
    }

    // Admin: Remove an outdated or irrelevant policy
    @DeleteMapping("/remove/{policyId}") // working
    public ResponseEntity<Void> removePolicy(@PathVariable Long policyId) {
        catalogueService.removePolicy(policyId);
        return ResponseEntity.noContent().build();
    }
}
