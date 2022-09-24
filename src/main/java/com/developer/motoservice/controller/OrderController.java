package com.developer.motoservice.controller;

import com.developer.motoservice.dto.request.MotoPartRequestDto;
import com.developer.motoservice.dto.request.OrderRequestDto;
import com.developer.motoservice.dto.response.OrderResponseDto;
import com.developer.motoservice.model.MotoPart;
import com.developer.motoservice.model.Order;
import com.developer.motoservice.service.OrderService;
import com.developer.motoservice.service.mapper.MotoPartMapper;
import com.developer.motoservice.service.mapper.OrderMapper;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final MotoPartMapper motoPartMapper;

    @ApiOperation(value = "create new order")
    @PostMapping("/create")
    public OrderResponseDto create(@RequestBody OrderRequestDto requestDto) {
        return orderMapper.toDto(orderService.create(orderMapper.toModel(requestDto)));
    }

    @ApiOperation(value = "add moto part to order")
    @PostMapping("/add-moto-part")
    public OrderResponseDto addMotoPart(@RequestParam Long orderId, @RequestBody MotoPartRequestDto requestDto) {
        MotoPart motoPart = motoPartMapper.toModel(requestDto);
        Order fromDb = orderService.add(orderId, motoPart);
        return orderMapper.toDto(fromDb);
    }

    @ApiOperation(value = "update order")
    @PostMapping("/update")
    public void update(@RequestBody OrderRequestDto requestDto) {
        orderService.create(orderMapper.toModel(requestDto));
    }

    @ApiOperation(value = "change order status")
    @PostMapping("/change-status")
    public void changeStatus(@RequestParam Long orderId, @RequestParam String status) {
        orderService.updateStatus(orderId, status);
    }

    @ApiOperation(value = "calculate total amount of order")
    @GetMapping("/total-amount")
    public OrderResponseDto calculateTotalAmount(@RequestBody Order order) {
        return orderMapper.toDto(orderService.calculateTotalAmount(order));
    }
}
