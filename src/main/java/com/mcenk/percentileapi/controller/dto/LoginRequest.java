package com.mcenk.percentileapi.controller.dto;

import lombok.Data;

//import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {


    private String username;


    private String password;
}
