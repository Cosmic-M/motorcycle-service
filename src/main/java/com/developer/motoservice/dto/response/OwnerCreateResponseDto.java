package com.developer.motoservice.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OwnerCreateResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
