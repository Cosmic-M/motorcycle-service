package com.developer.motoservice.service.mapper;

import com.developer.motoservice.dto.request.FavorRequestDto;
import com.developer.motoservice.dto.response.FavorResponseDto;
import com.developer.motoservice.model.Favor;
import com.developer.motoservice.repository.MasterRepository;
import com.developer.motoservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FavorMapper {
    private final OrderRepository orderRepository;
    private final MasterRepository masterRepository;

    public FavorResponseDto toDto(Favor favor) {
        FavorResponseDto responseDto = new FavorResponseDto();
        responseDto.setId(favor.getId());
        responseDto.setOrderId(favor.getMaster().getId());
        responseDto.setMasterId(favor.getOrder().getId());
        responseDto.setStatus(favor.getStatus());
        responseDto.setTotalCost(favor.getCost());
        return responseDto;
    }

    public Favor toModel(FavorRequestDto requestDto) {
        Favor favor = new Favor();
        if (requestDto.getId() != null) {
            favor.setId(requestDto.getId());
        }
        favor.setOrder(orderRepository.findById(requestDto.getOrderId()).orElseThrow(
                () -> new RuntimeException("Cannot find order by id=" + requestDto.getOrderId())));
        favor.setMaster(masterRepository.findById(requestDto.getMasterId()).orElseThrow(
                () -> new RuntimeException("Cannot find master by id=" + requestDto.getMasterId())));
        favor.setStatus(requestDto.getStatus());
        favor.setCost(requestDto.getCost());
        return favor;
    }
}
