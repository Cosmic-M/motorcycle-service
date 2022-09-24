package com.developer.motoservice.controller;

import com.developer.motoservice.dto.request.MotoPartRequestDto;
import com.developer.motoservice.dto.response.MotoPartResponseDto;
import com.developer.motoservice.service.MotoPartService;
import com.developer.motoservice.service.mapper.MotoPartMapper;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/moto-parts")
public class MotoPartController {
    private final MotoPartService motoPartService;
    private final MotoPartMapper motoPartMapper;

    @ApiOperation(value = "save new moto part into DB")
    @PostMapping("/create")
    public MotoPartResponseDto create(@RequestBody MotoPartRequestDto requestDto) {
        return motoPartMapper.toDto(motoPartService.create(motoPartMapper.toModel(requestDto)));
    }

    @ApiOperation(value = "fetch moto part to update data into DB")
    @PostMapping("/update")
    public void update(@RequestBody MotoPartRequestDto requestDto) {
        motoPartService.update(motoPartMapper.toModel(requestDto));
    }
}
