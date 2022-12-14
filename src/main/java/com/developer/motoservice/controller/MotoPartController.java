package com.developer.motoservice.controller;

import com.developer.motoservice.dto.request.MotoPartCreateRequestDto;
import com.developer.motoservice.dto.request.MotoPartUpdateRequestDto;
import com.developer.motoservice.dto.response.MotoPartResponseDto;
import com.developer.motoservice.service.MotoPartService;
import com.developer.motoservice.service.mapper.MotoPartMapper;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public MotoPartResponseDto create(@RequestBody MotoPartCreateRequestDto requestDto) {
        return motoPartMapper.toDto(motoPartService.create(motoPartMapper.toModel(requestDto)));
    }

    @ApiOperation(value = "fetching existent moto part into DB for updating")
    @PutMapping("/update")
    public void update(@RequestBody MotoPartUpdateRequestDto requestDto) {
        motoPartService.update(motoPartMapper.toModel(requestDto));
    }
}
