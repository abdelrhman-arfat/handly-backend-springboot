package com.project.handly.Controllers;


<<<<<<< HEAD
import com.project.handly.Entities.User;
import com.project.handly.Repositories.UserRepo;
import com.project.handly.Services.UserService;
import com.project.handly.Utils.ResponseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
=======
import com.project.handly.Repositories.UserRepo;
import com.project.handly.Services.UserService;
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
<<<<<<< HEAD
@RequestMapping("/api/authorized")
=======
@RequestMapping("/api/users")
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

<<<<<<< HEAD
    @GetMapping("/users/me")
    public ResponseEntity<?> me(HttpServletRequest request) {
        try {
            User user = (User) request.getAttribute("user");
            return ResponseHandler.success("Get user data successfully", user, HttpStatus.OK);
        }catch (Exception e){
            return ResponseHandler.error("Faild to get user profile data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
=======
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb

}
