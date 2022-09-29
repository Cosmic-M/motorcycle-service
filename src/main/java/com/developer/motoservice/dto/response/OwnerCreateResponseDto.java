package com.developer.motoservice.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerCreateResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
