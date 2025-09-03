package com.insurance.general_insurance.user;

/**
 * Interface for the User Service.
 * Defines the business operations that can be performed on users.
 */

public interface UserService {
    /**
     * Registers a new user in the system.
     * @param request The DTO containing the new user's information.
     * @return The newly created User object.
     * @throws Exception if a user with the same email already exists.
     */
    User registerUser(UserRegistrationRequest request) throws Exception;
}
