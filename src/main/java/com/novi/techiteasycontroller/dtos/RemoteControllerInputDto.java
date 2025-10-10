package com.novi.techiteasycontroller.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class RemoteControllerInputDto {

    @NotBlank(message = "Compatibility field cannot be blank")
    private String compatibleWith;
    @NotBlank(message = "Battery type cannot be blank")
    private String batteryType;
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 40, message = "Name cannot exceed 40 characters")
    private String name;
    @NotBlank(message = "Brand cannot be blank")
    private String brand;
    @Positive(message = "Price can't be negative or zero")
    private Double price;
}
