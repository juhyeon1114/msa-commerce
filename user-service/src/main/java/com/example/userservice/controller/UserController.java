package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.model.UserEntity;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.Greeting;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final Environment env;

//    @GetMapping("/health_check")
//    public String status() {
//        return env.getProperty("greeting.message");
//    }

    private final Greeting greeting;

    @GetMapping("/health_check")
    public String status() {
        return env.getProperty("local.server.port");
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(user, UserDto.class);
        userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(user, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        Iterable<UserEntity> allUsers = userService.getAllUsers();
        List<ResponseUser> results = new ArrayList<>();
        allUsers.forEach(u -> {
            results.add(new ModelMapper().map(u, ResponseUser.class));
        });
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @GetMapping("/users/{userId}")
    public UserDto getUserByUserId(@PathVariable("userId") String userId) {
        return userService.getUserById(userId);
    }

}
