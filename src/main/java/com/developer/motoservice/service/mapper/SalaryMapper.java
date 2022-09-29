package com.developer.motoservice.service.mapper;

import com.developer.motoservice.dto.response.SalaryResponseDto;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class SalaryMapper {
    public SalaryResponseDto toDto(BigDecimal salary) {
        SalaryResponseDto responseDto = new SalaryResponseDto();
        responseDto.setSalary(salary);
        return responseDto;
    }
}
