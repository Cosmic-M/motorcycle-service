package com.developer.motoservice.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OwnerResponseDto {
    private Long id;
    private List<Long> motorcycleIdList;
    private List<Long> orderIdList;
}
