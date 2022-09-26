package com.developer.motoservice.controller;

import com.developer.motoservice.dto.request.MasterRequestDto;
import com.developer.motoservice.dto.response.MasterResponseDto;
import com.developer.motoservice.dto.response.OrderResponseDto;
import com.developer.motoservice.dto.response.SalaryResponseDto;
import com.developer.motoservice.model.Order;
import com.developer.motoservice.service.MasterService;
import com.developer.motoservice.service.mapper.MasterMapper;
import com.developer.motoservice.service.mapper.OrderMapper;
import com.developer.motoservice.service.mapper.SalaryMapper;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/masters")
public class MasterController {
    private final MasterService masterService;
    private final MasterMapper masterMapper;
    private final OrderMapper orderMapper;
    private final SalaryMapper salaryMapper;

    @ApiOperation(value = "save new master into DB")
    @PostMapping("/create")
    public MasterResponseDto create(@RequestBody MasterRequestDto masterRequestDto) {
        return masterMapper.toDto(masterService.create(masterMapper.toModel(masterRequestDto)));
    }

    @ApiOperation(value = "fetching existent entity master into DB to update")
    @PutMapping("/update")
    public void update(@RequestBody MasterRequestDto requestDto) {
        masterService.update(masterMapper.toModel(requestDto));
    }

    @ApiOperation(value = "fetching existent entity master into DB to update")
    @GetMapping("/order-list/{masterId}")
    public List<OrderResponseDto> getOrders(@PathVariable Long masterId) {
        List<Order> orders = masterService.getOrders(masterId);
        return orders.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "get master's salary by master id")
    @GetMapping("/salary/{masterId}")
    public SalaryResponseDto getSalary(@PathVariable Long masterId) {
        BigDecimal salary = masterService.getSalary(masterId);
        return salaryMapper.toDto(salary);
    }
}
