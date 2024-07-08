package com.augustczar.healthforall.dtos;

import com.augustczar.healthforall.domain.Users;

public record RegisterDto(String login, String password, Users role) {

}
