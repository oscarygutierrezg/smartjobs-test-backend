package com.smartjobs.test.users.domain.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class PhoneDTO {

    private String number;
    private String cityCode;
    private String countryCode;
}
