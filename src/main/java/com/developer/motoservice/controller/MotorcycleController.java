package com.developer.motoservice.controller;

import com.developer.motoservice.dto.request.MotorcycleCreateRequestDto;
import com.developer.motoservice.dto.request.MotorcycleUpdateRequestDto;
import com.developer.motoservice.dto.response.MotorcycleResponseDto;
import com.developer.motoservice.service.MotorcycleService;
import com.developer.motoservice.service.mapper.MotorcycleMapper;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/motorcycles")
@RequiredArgsConstructor
public class MotorcycleController {
    private final MotorcycleService motorcycleService;
    private final MotorcycleMapper motorcycleMapper;

    @ApiOperation(value = "save new motorcycle into DB")
    @PostMapping("/create")
    public MotorcycleResponseDto create(@RequestBody MotorcycleCreateRequestDto requestDto) {
        return motorcycleMapper
                .toDto(motorcycleService.create(motorcycleMapper.toModel(requestDto)));
    }

    @ApiOperation("update motorcycle in DB")
    @PutMapping("/update")
    public void update(@RequestBody MotorcycleUpdateRequestDto requestDto) {
        motorcycleService.update(motorcycleMapper.toModel(requestDto));
    }
}
