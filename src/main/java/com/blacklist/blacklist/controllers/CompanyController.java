package com.blacklist.blacklist.controllers;

import com.blacklist.blacklist.models.dto.CompanyCreateUpdateDTO;
import com.blacklist.blacklist.models.dto.CompanyDTO;
import com.blacklist.blacklist.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "CRUD company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/company")
    @Operation(summary = "Возвращает список компаний")
    public ResponseEntity<List<CompanyDTO>> getCompany() {
        return ResponseEntity.ok(companyService.getAllCompany());
    }

    @GetMapping("/company/{company_id}")
    @Operation(summary = "Возвращает компанию по ID")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable long company_id) {

        return ResponseEntity.ok(companyService.getCompanyById(company_id));

    }

    @PostMapping("/company")
    @Operation(summary = "Создание компании")
    public ResponseEntity<CompanyDTO> addCompany(@RequestBody CompanyCreateUpdateDTO companyCreateUpdateDTO) {
        CompanyDTO companyDTO = companyService.createCompany(companyCreateUpdateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(companyDTO);
    }

    @DeleteMapping("/company/{company_id}")
    @Operation(summary = "Удаление компании по ID")
    public ResponseEntity<String> deleteCompany(@PathVariable long company_id) {
        boolean deleteCompany = companyService.deleteCompanyById(company_id);
        if (deleteCompany) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Company not found");
        }
    }

    @PatchMapping("/company/{company_id}")
    @Operation(summary = "Обновление данных компании")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable long company_id,
                                                    @RequestBody CompanyCreateUpdateDTO companyCreateUpdateDTO) {
        CompanyDTO companyDTO = companyService.updateCompany(company_id, companyCreateUpdateDTO);
        return ResponseEntity.ok(companyDTO);
    }
}
