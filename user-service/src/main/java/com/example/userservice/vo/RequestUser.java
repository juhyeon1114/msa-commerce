package com.example.userservice.vo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestUser {

    @NotNull(message = "email can't be null")
    @Size(min = 2, message = "email not be less than 2 chars")
    private String email;

    @NotNull(message = "name can't be null")
    @Size(min = 2, message = "name not be less than 2 chars")
    private String name;

    @NotNull(message = "name can't be null")
    @Size(min = 8, message = "name not be less than 8 chars")
    private String pwd;

}
