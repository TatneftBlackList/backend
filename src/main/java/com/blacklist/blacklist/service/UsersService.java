package com.blacklist.blacklist.service;

import com.blacklist.blacklist.database.repository.PermissionsRepository;
import com.blacklist.blacklist.database.repository.UsersRepository;
import com.blacklist.blacklist.models.dto.PermissionsDTO;
import com.blacklist.blacklist.models.dto.UsersCreateUpdateDTO;
import com.blacklist.blacklist.models.dto.UsersDTO;
import com.blacklist.blacklist.models.entity.PermissionsModel;
import com.blacklist.blacklist.models.entity.UsersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        UsersModel usersModel = usersRepository.getJobNumber(jobNumber);

        if (usersModel == null) {
            return null;
        } else {
            return convertToUsersDTO(usersModel);
        }
    }

    public UsersDTO addUser(UsersCreateUpdateDTO usersCreateUpdateDTO) {
    PermissionsModel permissions = permissionsRepository.findByName(String.valueOf(usersCreateUpdateDTO.getPermissions()))
    UsersModel usersModel = UsersModel.builder()
            .jobNumber(usersCreateUpdateDTO.getJobNumber())
            .firstName(usersCreateUpdateDTO.getFirstName())
            .lastName(usersCreateUpdateDTO.getLastName())
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
