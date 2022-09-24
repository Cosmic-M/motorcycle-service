package com.developer.motoservice.controller;

import com.developer.motoservice.dto.request.FavorRequestDto;
import com.developer.motoservice.dto.response.FavorResponseDto;
import com.developer.motoservice.service.FavorService;
import com.developer.motoservice.service.mapper.FavorMapper;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favors")
public class FavorController {
    private final FavorService favorService;
    private final FavorMapper favorMapper;

    @ApiOperation(value = "create new favor into DB")
    @PostMapping("/create")
    public FavorResponseDto create(@RequestBody FavorRequestDto requestDto) {
        return favorMapper.toDto(favorService.create(favorMapper.toModel(requestDto)));
    }
}
