package com.developer.motoservice.controller;

import com.developer.motoservice.dto.request.MasterRequestDto;
import com.developer.motoservice.dto.response.MasterResponseDto;
import com.developer.motoservice.service.MasterService;
import com.developer.motoservice.service.mapper.MasterMapper;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/masters")
public class MasterController {
    private final MasterService masterService;
    private final MasterMapper masterMapper;

    @ApiOperation(value = "save new master into DB")
    @PostMapping("/create")
    public MasterResponseDto create(@RequestBody MasterRequestDto masterRequestDto) {
        return masterMapper.toDto(masterService.create(masterMapper.toModel(masterRequestDto)));
    }
}
