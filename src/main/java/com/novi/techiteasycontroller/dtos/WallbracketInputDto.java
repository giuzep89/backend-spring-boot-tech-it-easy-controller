package com.novi.techiteasycontroller.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class WallbracketInputDto {

    @NotBlank(message = "Size cannot be blank")
    public String size;
    public boolean adjustable;
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 40, message = "Name cannot exceed 40 characters")
    public String name;
    @Positive(message = "Price can't be negative or zero")
    public Double price;
}
