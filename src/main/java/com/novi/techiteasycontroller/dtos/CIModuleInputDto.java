package com.novi.techiteasycontroller.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CIModuleInputDto {

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 40, message = "Name cannot exceed 40 characters")
    public String name;
    @NotBlank(message = "Type cannot be blank")
    public String type;
    @Positive(message = "Price can't be negative or zero")
    public Double price;
}
