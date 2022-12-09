package com.mcenk.percentileapi.response;

import com.mcenk.percentileapi.model.User;
import lombok.Data;

@Data
public class UserResponse {

    Long id;
    String userName;

    public UserResponse(User entity) {
        this.id = entity.getId();

        this.userName = entity.getUserName();
    }
}
