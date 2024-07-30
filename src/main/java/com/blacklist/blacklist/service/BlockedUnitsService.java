package com.blacklist.blacklist.service;

import com.blacklist.blacklist.database.repository.BlockedUnitsRepository;
import com.blacklist.blacklist.database.repository.CompanyRepository;
import com.blacklist.blacklist.database.repository.PassportsRepository;
import com.blacklist.blacklist.models.dto.BlockedUnitsCreateUpdateDTO;
import com.blacklist.blacklist.models.dto.BlockedUnitsDTO;
import com.blacklist.blacklist.models.entity.BlockedUnitsModel;
import com.blacklist.blacklist.models.entity.CompanyModel;
import com.blacklist.blacklist.models.entity.PassportsModel;
import org.aspectj.lang.annotation.AdviceName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlockedUnitsService {

    @Autowired
    private BlockedUnitsRepository blockedUnitsRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PassportsRepository passportsRepository;

    public List<BlockedUnitsDTO> getBlockedUnits() {
        List<BlockedUnitsModel> blockedUnitsModels = blockedUnitsRepository.findAll();
        List<BlockedUnitsDTO> blockedUnitsDTOs = new ArrayList<>();

        for (BlockedUnitsModel blockedUnitsModel : blockedUnitsModels) {
            BlockedUnitsDTO blockedUnitsDTO = convertBlockedUnitsModelToDTO(blockedUnitsModel);
            blockedUnitsDTOs.add(blockedUnitsDTO);
        }
        return blockedUnitsDTOs;
    }

    public BlockedUnitsDTO addBlockedUnits (BlockedUnitsCreateUpdateDTO blockedUnitsCreateUpdateDTO) {
        CompanyModel companyModel = companyRepository.findById(blockedUnitsCreateUpdateDTO.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found " + blockedUnitsCreateUpdateDTO.getCompanyId()));

        PassportsModel passportsModel = PassportsModel.builder()
                .passportSeria(blockedUnitsCreateUpdateDTO.getPassportSeria())
                .passportNumber(blockedUnitsCreateUpdateDTO.getPassportNumber())
                .oldPassportNumber(blockedUnitsCreateUpdateDTO.getOldPassportNumber())
                .oldPassportSeria(blockedUnitsCreateUpdateDTO.getOldPassportSeria())
                .build();
        passportsRepository.save(passportsModel);

        BlockedUnitsModel blockedUnitsModel = BlockedUnitsModel.builder()
                .fio(blockedUnitsCreateUpdateDTO.getFio())
                .passportsModel(passportsModel)
                .companyModel(companyModel)
                .reason(blockedUnitsCreateUpdateDTO.getReason())
                .dateAddToList(blockedUnitsCreateUpdateDTO.getDateAddToList())
                .build();
        blockedUnitsRepository.save(blockedUnitsModel);

        return convertBlockedUnitsModelToDTO(blockedUnitsModel);
    }

    public BlockedUnitsDTO getBlockedUnitsByID(long user_id) {
        BlockedUnitsModel blockedUnitsModel = blockedUnitsRepository.findById(user_id).orElse(null);
        if (blockedUnitsModel != null) {
            return convertBlockedUnitsModelToDTO(blockedUnitsModel);
        }
        return null;
    }

    public BlockedUnitsDTO partialUpdateBlockedUnits(long user_id, BlockedUnitsCreateUpdateDTO blockedUnitsCreateUpdateDTO) {
        BlockedUnitsModel existingBlockedUnits = blockedUnitsRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("Blocked unit not found: " + user_id));

        if (blockedUnitsCreateUpdateDTO.getFio() != null) {
            existingBlockedUnits.setFio(blockedUnitsCreateUpdateDTO.getFio());
        }
        if (blockedUnitsCreateUpdateDTO.getPassportSeria() != null) {
            existingBlockedUnits.getPassportsModel().setPassportSeria(blockedUnitsCreateUpdateDTO.getPassportSeria());
        }
        if (blockedUnitsCreateUpdateDTO.getPassportNumber() != null) {
            existingBlockedUnits.getPassportsModel().setPassportNumber(blockedUnitsCreateUpdateDTO.getPassportNumber());
        }
        if (blockedUnitsCreateUpdateDTO.getOldPassportSeria() != null) {
            existingBlockedUnits.getPassportsModel().setOldPassportSeria(blockedUnitsCreateUpdateDTO.getOldPassportSeria());
        }
        if (blockedUnitsCreateUpdateDTO.getOldPassportNumber() != null) {
            existingBlockedUnits.getPassportsModel().setOldPassportNumber(blockedUnitsCreateUpdateDTO.getOldPassportNumber());
        }
        if (blockedUnitsCreateUpdateDTO.getCompanyId() != null) {
            CompanyModel companyModel = companyRepository.findById(blockedUnitsCreateUpdateDTO.getCompanyId())
                    .orElseThrow(() -> new RuntimeException("Company not found: " + blockedUnitsCreateUpdateDTO.getCompanyId()));
            existingBlockedUnits.setCompanyModel(companyModel);
        }
        if (blockedUnitsCreateUpdateDTO.getReason() != null) {
            existingBlockedUnits.setReason(blockedUnitsCreateUpdateDTO.getReason());
        }
        if (blockedUnitsCreateUpdateDTO.getDateAddToList() != null) {
            existingBlockedUnits.setDateAddToList(blockedUnitsCreateUpdateDTO.getDateAddToList());
        }

        blockedUnitsRepository.save(existingBlockedUnits);
        return convertBlockedUnitsModelToDTO(existingBlockedUnits);
    }

    public BlockedUnitsDTO updateBlockedUnit(Long id, BlockedUnitsCreateUpdateDTO blockedUnitsCreateUpdateDTO) {
        BlockedUnitsModel existingBlockedUnit = blockedUnitsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blocked unit not found: " + id));

        CompanyModel companyModel = companyRepository.findById(blockedUnitsCreateUpdateDTO.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found: " + blockedUnitsCreateUpdateDTO.getCompanyId()));

        PassportsModel passportsModel = existingBlockedUnit.getPassportsModel();
        passportsModel.setPassportSeria(blockedUnitsCreateUpdateDTO.getPassportSeria());
        passportsModel.setPassportNumber(blockedUnitsCreateUpdateDTO.getPassportNumber());
        passportsModel.setOldPassportSeria(blockedUnitsCreateUpdateDTO.getOldPassportSeria());
        passportsModel.setOldPassportNumber(blockedUnitsCreateUpdateDTO.getOldPassportNumber());
        passportsRepository.save(passportsModel);

        existingBlockedUnit.setFio(blockedUnitsCreateUpdateDTO.getFio());
        existingBlockedUnit.setPassportsModel(passportsModel);
        existingBlockedUnit.setCompanyModel(companyModel);
        existingBlockedUnit.setReason(blockedUnitsCreateUpdateDTO.getReason());
        existingBlockedUnit.setDateAddToList(blockedUnitsCreateUpdateDTO.getDateAddToList());

        blockedUnitsRepository.save(existingBlockedUnit);
        return convertBlockedUnitsModelToDTO(existingBlockedUnit);
    }

    private BlockedUnitsDTO convertBlockedUnitsModelToDTO(BlockedUnitsModel blockedUnitsModel) {
        return BlockedUnitsDTO.builder()
                .id(blockedUnitsModel.getId())
                .fio(blockedUnitsModel.getFio())
                .passportId(blockedUnitsModel.getPassportsModel().getId())
                .companyId(blockedUnitsModel.getCompanyModel().getId())
                .reason(blockedUnitsModel.getReason())
                .dateAddToList(blockedUnitsModel.getDateAddToList())
                .build();
    }
}
