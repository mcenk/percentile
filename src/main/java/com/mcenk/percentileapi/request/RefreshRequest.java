package com.mcenk.percentileapi.request;

import lombok.Data;

@Data
public class RefreshRequest {

    Long userId;
    String refreshToken;
}