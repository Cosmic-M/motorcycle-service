package com.developer.motoservice.controller;

import com.developer.motoservice.dto.request.MasterCreateRequestDto;
import com.developer.motoservice.dto.request.MasterUpdateRequestDto;
import com.developer.motoservice.dto.response.MasterCreateResponseDto;
import com.developer.motoservice.dto.response.OrderResponseDto;
import com.developer.motoservice.dto.response.SalaryResponseDto;
import com.developer.motoservice.model.Order;
import com.developer.motoservice.service.MasterService;
import com.developer.motoservice.service.mapper.MasterMapper;
import com.developer.motoservice.service.mapper.OrderMapper;
import com.developer.motoservice.service.mapper.SalaryMapper;
import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public MasterCreateResponseDto create(@RequestBody MasterCreateRequestDto masterRequestDto) {
        return masterMapper.toDto(masterService.create(masterMapper.toModel(masterRequestDto)));
    }

    @ApiOperation(value = "update master`s data")
    @PutMapping("/update")
    public void update(@RequestBody MasterUpdateRequestDto requestDto) {
        masterService.update(masterMapper.toModel(requestDto));
    }

    @ApiOperation(value = "get list of orders by masterId")
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
