package com.novi.techiteasycontroller.dtos;

import com.novi.techiteasycontroller.models.Television;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class TelevisionInputDto {

    @NotBlank(message = "Type cannot be blank")
    @Size(max = 40, message = "Type cannot exceed 40 characters")
    public String type;
    @NotBlank(message = "Brand cannot be blank")
    public String brand;
    @NotBlank(message = "Name cannot be blank")
    public String name;
    @Positive(message = "Price can't be negative or zero")
    public double price;
    @NotNull(message = "Please specify screen size")
    public Television.AvailableSizes availableSizes;
    @NotNull(message = "Please specify refresh rate")
    public Television.RefreshRate refreshRate;
    @NotNull(message = "Please specify screen type")
    public Television.ScreenType screenType;
    @NotNull(message = "Please specify screen quality")
    public Television.ScreenQuality screenQuality;
    public boolean smartTv;
    public boolean wifi;
    public boolean voiceControl;
    public boolean hdr;
    public boolean bluetooth;
    public boolean ambiLight;
}
