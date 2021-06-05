package com.cos.photogramstart.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SubscribeDto {

    private BigInteger userId;
    private String username;
    private String profileImageUrl;
    private Integer subscribeState;     //
    private Integer equalUserState;     // 동일한 사용자인지


}
