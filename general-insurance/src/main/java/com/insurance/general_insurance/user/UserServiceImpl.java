package com.insurance.general_insurance.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implementation of the UserService interface.
 * Contains the business logic for user management.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor-based dependency injection.
     * Spring will automatically provide the instances of UserRepository and PasswordEncoder.
     * This is the recommended way to inject dependencies.
     * @param userRepository The repository for user data access.
     * @param passwordEncoder The encoder for hashing passwords.
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(UserRegistrationRequest request) throws Exception {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new Exception("User with email " + request.getEmail() + " already exists.");
        }

        // 2. Create a new User entity from the request DTO.
        User newUser = new User();
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setEmail(request.getEmail());

        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        newUser.setPhoneNumber(request.getPhoneNumber());
        newUser.setAddress(request.getAddress());

        return userRepository.save(newUser);
    }
}

