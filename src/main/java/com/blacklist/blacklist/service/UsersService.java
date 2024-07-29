package com.blacklist.blacklist.service;

import com.blacklist.blacklist.database.repository.PermissionsRepository;
import com.blacklist.blacklist.database.repository.UsersRepository;
import com.blacklist.blacklist.models.dto.UsersDTO;
import com.blacklist.blacklist.models.entity.PermissionsModel;
import com.blacklist.blacklist.models.entity.UsersModel;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PermissionsRepository permissionsRepository;


    public List<UsersDTO> getAllUsers() {
        List<UsersModel> usersList = usersRepository.findAll();
        return usersList.stream()
                .map(this::convertToUsersDTO)
                .collect(Collectors.toList());
    }
    public UsersDTO getUserByJobNumber(String jobNumber) {
        Optional<UsersModel> usersModel = usersRepository.findByJobNumber(jobNumber);
        return usersModel.map(this::convertToUsersDTO).orElse(null);
    }

    public UsersDTO addUser(UsersDTO usersDTO) {
    Set<PermissionsModel> permissions = usersDTO.getPermissions().stream()
            .map(permissionId -> permissionsRepository.findById(permissionId)
                    .orElseThrow(() -> new RuntimeException("Permission not found: " + permissionId)))
            .collect(Collectors.toSet());
    UsersModel usersModel = UsersModel.builder()
            .jobNumber(usersDTO.getJobNumber())
            .firstName(usersDTO.getFirstName())
            .lastName(usersDTO.getLastName())
            .permissions(permissions)
            .build();
    usersRepository.save(usersModel);
    return convertToUsersDTO(usersModel);
    }

    public boolean deleteUsersByJobNumber(String jobNumber) {
        UsersModel usersModel = usersRepository.findByJobNumber(jobNumber).orElse(null);
        if (usersModel != null) {
            usersRepository.delete(usersModel);
            return true;
        } else {
            return false;
        }
    }

    public UsersDTO updateUser(UsersDTO usersDTO, String jobNumber) {
        UsersModel existingUser = usersRepository.findByJobNumber(jobNumber).orElse(null);
        if (existingUser != null) {
            existingUser.setFirstName(usersDTO.getFirstName());
            existingUser.setLastName(usersDTO.getLastName());

            Set<PermissionsModel> permissions = usersDTO.getPermissions().stream()
                    .map(permissionId -> permissionsRepository.findById(permissionId)
                            .orElseThrow(() -> new RuntimeException("Permission not found: " + permissionId)))
                    .collect(Collectors.toSet());
            existingUser.setPermissions(permissions);

            usersRepository.save(existingUser);

            return convertToUsersDTO(existingUser);
        }

        throw new RuntimeException("User not found: " + jobNumber);
    }

    public UsersDTO partialUserUpdate(UsersDTO usersDTO, String jobNumber) {
        UsersModel existingUser = usersRepository.findByJobNumber(jobNumber).orElse(null);
        if (existingUser != null) {
            if (usersDTO.getFirstName() != null) {
                existingUser.setFirstName(usersDTO.getFirstName());
            }
            if (usersDTO.getLastName() != null) {
                existingUser.setLastName(usersDTO.getLastName());
            }
            if (usersDTO.getPermissions() != null && !usersDTO.getPermissions().isEmpty()) {
                Set<PermissionsModel> permissions = usersDTO.getPermissions().stream()
                        .map(permissionId -> permissionsRepository.findById(permissionId)
                                .orElseThrow(() -> new RuntimeException("Permission not found: " + permissionId)))
                        .collect(Collectors.toSet());
                existingUser.setPermissions(permissions);
            }

            usersRepository.save(existingUser);
            return convertToUsersDTO(existingUser);
        }
        throw new RuntimeException("User not found: " + jobNumber);
    }


    private UsersDTO convertToUsersDTO(UsersModel usersModel) {
        return UsersDTO.builder()
                .jobNumber(usersModel.getJobNumber())
                .firstName(usersModel.getFirstName())
                .lastName(usersModel.getLastName())
                .permissions(usersModel.getPermissions().stream()
                        .map(PermissionsModel::getId)
                        .collect(Collectors.toSet()))
                .build();
    }

}
