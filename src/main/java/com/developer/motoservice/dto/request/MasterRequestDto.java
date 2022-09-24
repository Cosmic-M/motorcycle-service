package com.developer.motoservice.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MasterRequestDto {
    private String firstName;
    private String lastName;
    private List<Long> orderIdList;
}
