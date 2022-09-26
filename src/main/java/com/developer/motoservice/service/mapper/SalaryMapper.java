package com.developer.motoservice.service.mapper;

import com.developer.motoservice.dto.response.SalaryResponseDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SalaryMapper {
    public SalaryResponseDto toDto(BigDecimal salary) {
        SalaryResponseDto responseDto = new SalaryResponseDto();
        responseDto.setSalary(salary);
        return responseDto;
    }
}
