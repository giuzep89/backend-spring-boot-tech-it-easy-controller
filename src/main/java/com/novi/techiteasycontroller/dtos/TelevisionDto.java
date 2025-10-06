package com.novi.techiteasycontroller.dtos;

import com.novi.techiteasycontroller.models.Television;

public class TelevisionDto {

    public Long id;
    public String type;
    public String brand;
    public String name;
    public double price;
    public Television.AvailableSizes availableSizes;
    public Television.RefreshRate refreshRate;
    public Television.ScreenType screenType;
    public Television.ScreenQuality screenQuality;
    public boolean smartTv;
    public boolean wifi;
    public boolean voiceControl;
    public boolean hdr;
    public boolean bluetooth;
    public boolean ambiLight;
}
