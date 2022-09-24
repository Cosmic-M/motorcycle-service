package com.developer.motoservice.dto.request;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class OwnerRequestDto {
    private Long id;
    private List<Long> motorcycleIdList;
    private List<Long> orderIdList;
}
