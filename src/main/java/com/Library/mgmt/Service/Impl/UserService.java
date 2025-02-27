package com.Library.mgmt.Service.Impl;

import com.Library.mgmt.Enums.Operator;
import com.Library.mgmt.Enums.UserFilter;
import com.Library.mgmt.Model.User;
import com.Library.mgmt.Enums.UserType;
import com.Library.mgmt.Repository.UserRepository;
import com.Library.mgmt.dto.UserCreationRequest;
import com.Library.mgmt.dto.UserCreationResponse;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserCreationResponse addStudent(UserCreationRequest request) {
        User user = request.toUser();
        user.setUserType(UserType.STUDENT);
        User userFromDb = userRepository.save(user);
        return UserCreationResponse.builder().
                userName(userFromDb.getName()).
                userAddress(userFromDb.getAddress()).
                userEmail(userFromDb.getEmail()).
                userPhone(userFromDb.getPhoneNo()).
                build();
    }

    public List<User> filter(UserFilter filterBy, Operator operator, String value) {
        switch (filterBy){
            case NAME :
                switch (operator){
                    case EQUALS :
                        return userRepository.findByName(value);
                    case LIKE:
                        return userRepository.findByNameLike("%"+value+"%");

                }
            case EMAIL :
            	switch (operator) {
            		case EQUALS :
            			return userRepository.findByEmail(value);
            		case LIKE :
            			return userRepository.findByEmailLike("%"+value+"%");
            	}
            case PHONE_NO :
            	switch (operator) {
            		case EQUALS :
            			return userRepository.findByPhoneNo(value);
            		case LIKE :
            			return userRepository.findByPhoneNoLike("%"+value+"%");
            	}
        }
        return new ArrayList<>();
    }

    public User checkIfUserIsValid(@NotBlank(message = "userEmail must not be blank") String userEmail) {
//        return userRepository.findUserByEmail(userEmail);
        List<User> users = userRepository.findByEmail(userEmail);
        if (users == null){
            return null;
        }
        return users.get(0);
    }
}
