package com.bankaccount.bankaccount.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SignupRequest {
    private String name;
    private String email;
    private String password;
}
