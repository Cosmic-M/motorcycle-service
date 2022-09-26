package com.developer.motoservice.controller;

import com.developer.motoservice.dto.request.FavorCreateRequestDto;
import com.developer.motoservice.dto.request.FavorUpdateRequestDto;
import com.developer.motoservice.dto.response.FavorResponseDto;
import com.developer.motoservice.service.FavorService;
import com.developer.motoservice.service.mapper.FavorMapper;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favors")
public class FavorController {
    private final FavorService favorService;
    private final FavorMapper favorMapper;

    @ApiOperation(value = "create new favor")
    @PostMapping("/create")
    public FavorResponseDto create(@RequestBody FavorCreateRequestDto requestDto) {
        return favorMapper.toDto(favorService.create(favorMapper.toModel(requestDto)));
    }

    @ApiOperation(value = "update existing favor")
    @PutMapping("/update")
    public void update(@RequestBody FavorUpdateRequestDto requestDto) {
        favorService.update(favorMapper.toModel(requestDto));
    }

    @ApiOperation(value = "Changing status of existent favor into DB")
    @PutMapping("/change-status")
    public void changeStatus(@RequestParam Long favorId, @RequestParam String status) {
        favorService.changeStatus(favorId, status);
    }
}
