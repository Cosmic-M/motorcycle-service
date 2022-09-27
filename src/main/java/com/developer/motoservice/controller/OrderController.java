package com.developer.motoservice.controller;

import com.developer.motoservice.dto.request.OrderCreateRequestDto;
import com.developer.motoservice.dto.request.OrderUpdateRequestDto;
import com.developer.motoservice.dto.response.OrderResponseDto;
import com.developer.motoservice.model.Order;
import com.developer.motoservice.model.OrderStatus;
import com.developer.motoservice.repository.OrderRepository;
import com.developer.motoservice.service.OrderService;
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
    private final OrderRepository orderRepository;

    @ApiOperation(value = "create new order")
    @PostMapping("/create")
    public OrderResponseDto create(@RequestBody OrderCreateRequestDto requestDto) {
        return orderMapper.toDto(orderService.create(orderMapper.toModel(requestDto)));
    }

    @ApiOperation(value = "add moto part to order")
    @PutMapping("/add-moto-part")
    public OrderResponseDto addMotoPart(@RequestParam Long orderId, @RequestParam Long motoPartId) {
        Order fromDb = orderService.add(orderId, motoPartId);
        return orderMapper.toDto(fromDb);
    }

    @ApiOperation(value = "update order")
    @PutMapping("/update")
    public void update(@RequestBody OrderUpdateRequestDto requestDto) {
        orderService.update(orderMapper.toModel(requestDto));
    }

    @ApiOperation(value = "change order status")
    @PutMapping("/change-status")
    public void changeStatus(@RequestParam Long orderId, @RequestParam String status) {
        orderService.changeStatus(orderId, status);
    }

    @ApiOperation(value = "calculate total amount of order")
    @GetMapping("/total-amount/{orderId}")
    public OrderResponseDto calculateTotalAmount(@PathVariable Long orderId) {
        Order fromDb = orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("Cannot find order by id=" + orderId));
        fromDb.setTotalAmount(orderService.getTotalAmount(fromDb));
        fromDb.setStatus(OrderStatus.IN_PROCESS);
        return orderMapper.toDto(fromDb);
    }
}
