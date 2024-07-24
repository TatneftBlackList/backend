package com.blacklist.blacklist.service;

import com.blacklist.blacklist.database.repository.CompanyRepository;
import com.blacklist.blacklist.models.dto.CompanyCreateUpdateDTO;
import com.blacklist.blacklist.models.dto.CompanyDTO;
import com.blacklist.blacklist.models.entity.CompanyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<CompanyDTO> getAllCompany() {
        List<CompanyModel> companyList = companyRepository.findAll();
        List<CompanyDTO> companyDTOList = new ArrayList<>();
        for (CompanyModel companyModel : companyList) {
            CompanyDTO companyDTO = CompanyDTO.builder()
                    .id(companyModel.getId())
                    .name(companyModel.getName()).build();
            companyDTOList.add(companyDTO);
        }
        return companyDTOList;
    }

    public CompanyDTO getCompanyById(long id) {
        CompanyModel companyModel = companyRepository.findById(id).orElse(null);
        if (companyModel == null) {
            return null;
        } else {
            return CompanyDTO.builder()
                    .id(companyModel.getId())
                    .name(companyModel.getName()).build();
        }
    }

    public CompanyDTO createCompany(CompanyCreateUpdateDTO companyCreateUpdateDTO) {
        CompanyModel companyModel = CompanyModel.builder()
                .name(companyCreateUpdateDTO.getName()).build();
        companyRepository.save(companyModel);
        return CompanyDTO.builder()
                .id(companyModel.getId())
                .name(companyModel.getName()).build();
    }

    public boolean deleteCompanyById(long id) {
        CompanyModel companyModel = companyRepository.findById(id).orElse(null);

        if (companyModel == null) {
            return false;
        } else {
            companyRepository.delete(companyModel);
            return true;
        }
    }

    public CompanyDTO updateCompany(long company_id, CompanyCreateUpdateDTO companyCreateUpdateDTO) {
        CompanyModel companyModel = companyRepository.findById(company_id).orElse(null);
        if (companyModel == null) {
            return null;
        } else {
            companyModel.setName(companyCreateUpdateDTO.getName());
            companyRepository.save(companyModel);
            return CompanyDTO.builder()
                    .id(companyModel.getId())
                    .name(companyModel.getName()).build();
        }
    }
}
