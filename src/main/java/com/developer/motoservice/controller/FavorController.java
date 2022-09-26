package com.developer.motoservice.controller;

import com.developer.motoservice.dto.request.FavorRequestDto;
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

    @ApiOperation(value = "create new favor into DB")
    @PostMapping("/create")
    public FavorResponseDto create(@RequestBody FavorRequestDto requestDto) {
        return favorMapper.toDto(favorService.create(favorMapper.toModel(requestDto)));
    }

    @ApiOperation(value = "fetching favor into DB to update date")
    @PutMapping("/update")
    public void update(@RequestBody FavorRequestDto requestDto) {
        favorService.update(favorMapper.toModel(requestDto));
    }

    @ApiOperation(value = "Changing status of existent favor into DB")
    @PutMapping("/change-status")
    public void changeStatus(@RequestParam Long favorId, @RequestParam String status) {
        favorService.changeStatus(favorId, status);
    }
}
