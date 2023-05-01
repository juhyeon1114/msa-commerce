package com.example.userservice.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseUser {

    private String email;
    private String name;
    private String userId;
    private List<ResponseOrder> orders = new ArrayList<>();

}
