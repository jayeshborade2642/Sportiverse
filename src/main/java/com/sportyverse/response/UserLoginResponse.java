package com.sportyverse.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLoginResponse {
    String message;
    boolean status;
}
