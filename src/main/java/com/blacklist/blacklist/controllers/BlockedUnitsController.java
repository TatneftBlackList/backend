package com.blacklist.blacklist.controllers;

import com.blacklist.blacklist.models.dto.BlockedUnitsCreateUpdateDTO;
import com.blacklist.blacklist.models.dto.BlockedUnitsDTO;
import com.blacklist.blacklist.service.BlockedUnitsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "CRUD по блокированным пользователям")
public class BlockedUnitsController {

    @Autowired
    private BlockedUnitsService blockedUnitsService;

    @GetMapping("/blockedUnits")
    @Operation(summary = "Возвращает массив заблокированных пользователей")
    public ResponseEntity<List<BlockedUnitsDTO>> getBlockedUnits() {
        List<BlockedUnitsDTO> blockedUnitsDTOS = blockedUnitsService.getBlockedUnits();
        return ResponseEntity.status(HttpStatus.OK).body(blockedUnitsDTOS);
    }

    @PostMapping("/blockedUnits")
    @Operation(summary = "Добавление пользователя в черный список")
    public ResponseEntity<BlockedUnitsDTO> addBlockedUnits (@RequestBody BlockedUnitsCreateUpdateDTO blockedUnitsCreateUpdateDTO) {
        BlockedUnitsDTO createBlockedUnits = blockedUnitsService.addBlockedUnits(blockedUnitsCreateUpdateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createBlockedUnits);
    }

    @GetMapping("/blockedUnits/{user_id}")
    @Operation(summary = "Возвращает пользователя по ID")
    public ResponseEntity<BlockedUnitsDTO> getBlockedUnitsByID (@PathVariable("user_id") long user_id) {
        BlockedUnitsDTO blockedUnitsDTO = blockedUnitsService.getBlockedUnitsByID(user_id);
        return ResponseEntity.status(HttpStatus.OK).body(blockedUnitsDTO);
    }

    @PatchMapping("/blockedUnits/{user_id}")
    @Operation(summary = "Частичное обновление ресурса по ID")
    public ResponseEntity<BlockedUnitsDTO> partialUpdateBlockedUnits(@PathVariable("user_id") long user_id, @RequestBody BlockedUnitsCreateUpdateDTO blockedUnitsCreateUpdateDTO) {
        BlockedUnitsDTO blockedUnitsDTO = blockedUnitsService.partialUpdateBlockedUnits(user_id, blockedUnitsCreateUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(blockedUnitsDTO);
    }

    @PutMapping("/blockedUnits/{user_id}")
    @Operation(summary = "Полное обновление ресурса по ID")
    public ResponseEntity<BlockedUnitsDTO> updateBlockedUnits(@PathVariable("user_id") long user_id, @RequestBody BlockedUnitsCreateUpdateDTO blockedUnitsCreateUpdateDTO) {
        BlockedUnitsDTO updateBlockedUnits = blockedUnitsService.updateBlockedUnit(user_id, blockedUnitsCreateUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updateBlockedUnits);
    }

}
