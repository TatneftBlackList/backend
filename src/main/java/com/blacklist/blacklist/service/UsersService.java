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
        List<UsersDTO> usersDTOList = new ArrayList<>();

        for (UsersModel usersModel : usersList) {
            UsersDTO usersDTO = UsersDTO.builder()
                    .jobNumber(usersModel.getJobNumber())
                    .firstName(usersModel.getFirstName())
                    .lastName(usersModel.getLastName())
                    .permissions(usersModel.getPermissions().stream()
                            .map(PermissionsModel::getEPermissions)
                            .collect(Collectors.toSet()))
                    .build();
            usersDTOList.add(usersDTO);
        }
        return usersDTOList;
    }
    public UsersDTO getUserByJobNumber(String jobNumber) {
        Optional<UsersModel> usersModel = usersRepository.findByJobNumber(jobNumber);
        return usersModel.map(this::convertToUsersDTO).orElse(null);
    }

    public UsersDTO addUser(UsersDTO usersDTO) {
    Set<PermissionsModel> permissions = usersDTO.getPermissions().stream()
            .map(permission -> permissionsRepository.findByePermissions(permission)
                    .orElseThrow(() -> new RuntimeException("Permission not found: " + permission)))
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


    private UsersDTO convertToUsersDTO(UsersModel usersModel) {
    return UsersDTO.builder()
            .jobNumber(usersModel.getJobNumber())
            .firstName(usersModel.getFirstName())
            .lastName(usersModel.getLastName())
            .permissions(usersModel.getPermissions().stream()
                    .map(PermissionsModel::getEPermissions)
                    .collect(Collectors.toSet()))
            .build();
    }

}
