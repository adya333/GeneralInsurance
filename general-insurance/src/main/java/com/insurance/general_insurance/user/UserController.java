package com.insurance.general_insurance.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for handling user-related API requests.
 * This class exposes endpoints for user management.
 */
@RestController // Marks this class as a REST Controller, which combines @Controller and @ResponseBody.
@RequestMapping("/api/users") // Maps all requests starting with /api/users to this controller.
public class UserController {

    private final UserService userService;

    /**
     * Constructor-based dependency injection for the UserService.
     * Spring will inject the UserService bean that it manages.
     * @param userService The service responsible for user business logic.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint for registering a new user.
     * It listens for HTTP POST requests on /api/users/register.
     * The user's details are expected in the request body as a JSON object.
     *
     * @param request The UserRegistrationRequest DTO containing the new user's data.
     * @return A ResponseEntity indicating the result of the operation.
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest request) {
        try {
            // Call the service layer to perform the registration logic.
            User newUser = userService.registerUser(request);
            // If successful, return the new user object and an HTTP 201 Created status.
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            // If the service throws an exception (e.g., email already exists),
            // return an error message and an HTTP 400 Bad Request status.
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
