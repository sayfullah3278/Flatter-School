package com.mdfaysalhossain.SMS.With.Maven.service;

import com.mdfaysalhossain.SMS.With.Maven.model.UserModel;
import com.mdfaysalhossain.SMS.With.Maven.repository.IUserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {


    @Autowired
    private IUserRepo userRepository;

    // Other dependencies or autowired services if needed

    public List<UserModel> getAllUsers() {
        // Implementation to retrieve all users
        return userRepository.findAll();
    }

    public Optional<UserModel> findUserById(int id) {
        // Implementation to find a user by ID
        return userRepository.findById(id);
    }

    public void saveUser(UserModel userModel) {
        // Implementation to save a user
        userRepository.save(userModel);
    }

    public void deleteUserById(int id) {
        // Implementation to delete a user by ID
        userRepository.deleteById(id);
    }


}
