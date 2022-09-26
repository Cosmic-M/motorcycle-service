package com.developer.motoservice.controller;

import com.developer.motoservice.dto.request.OwnerCreateRequestDto;
import com.developer.motoservice.dto.request.OwnerUpdateRequestDto;
import com.developer.motoservice.dto.response.OrderResponseDto;
import com.developer.motoservice.dto.response.OwnerResponseDto;
import com.developer.motoservice.model.Order;
import com.developer.motoservice.service.OwnerService;
import com.developer.motoservice.service.mapper.OrderMapper;
import com.developer.motoservice.service.mapper.OwnerMapper;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;
    private final OwnerMapper ownerMapper;
    private final OrderMapper orderMapper;

    @ApiOperation(value = "create new owner into DB")
    @PostMapping("/create")
    public OwnerResponseDto create(@RequestBody OwnerCreateRequestDto requestDto) {
        return ownerMapper.toDto(ownerService.save(ownerMapper.toModel(requestDto)));
    }

    @ApiOperation(value = "update owner into DB")
    @PutMapping("/update")
    public void update(@RequestBody OwnerUpdateRequestDto requestDto) {
        ownerService.update(ownerMapper.toModel(requestDto));
    }

    @GetMapping("/{id}")
    public List<OrderResponseDto> getOrders(@PathVariable Long id) {
        List<Order> orders = ownerService.getOrders(id);
        return orders.stream().map(orderMapper::toDto).collect(Collectors.toList());
    }
}
