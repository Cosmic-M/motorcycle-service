package com.developer.motoservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerCreateRequestDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
