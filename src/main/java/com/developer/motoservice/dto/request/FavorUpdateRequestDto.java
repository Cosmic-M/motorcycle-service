package com.developer.motoservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavorUpdateRequestDto extends FavorCreateRequestDto {
    private Long id;
}
