package com.mcenk.percentileapi.response;

import lombok.Data;

@Data
public class Response {
    private String username;
    private String email;

    public Response(String username, String email) {
        this.username=username;
        this.email= email;
    }
}
