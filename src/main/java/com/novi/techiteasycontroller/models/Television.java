package com.novi.techiteasycontroller.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="televisions")
public class Television {

    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private String brand;
    private String name;
    private double price;
    private AvailableSizes availableSizes;
    private RefreshRate refreshRate;
    private ScreenType screenType;
    private ScreenQuality screenQuality;
    private boolean smartTv;
    private boolean wifi;
    private boolean voiceControl;
    private boolean hdr;
    private boolean bluetooth;
    private boolean ambiLight;
    private int originalStock;
    private int sold;
    private LocalDateTime dateOfSale;
    private LocalDateTime dateOfPurchase;

    public Television() {
    }

    public Television(Long id, String type, String brand, String name, double price, AvailableSizes availableSizes, RefreshRate refreshRate, ScreenType screenType, ScreenQuality screenQuality, boolean smartTv, boolean wifi, boolean voiceControl, boolean hdr, boolean bluetooth, boolean ambiLight, int originalStock, int sold, LocalDateTime dateOfSale, LocalDateTime dateOfPurchase) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.availableSizes = availableSizes;
        this.refreshRate = refreshRate;
        this.screenType = screenType;
        this.screenQuality = screenQuality;
        this.smartTv = smartTv;
        this.wifi = wifi;
        this.voiceControl = voiceControl;
        this.hdr = hdr;
        this.bluetooth = bluetooth;
        this.ambiLight = ambiLight;
        this.originalStock = originalStock;
        this.sold = sold;
        this.dateOfSale = dateOfSale;
        this.dateOfPurchase = dateOfPurchase;
    }

    // Enums for all the options with multiple choices
    public enum AvailableSizes {
        TWENTYTWO_INCH(22),
        THIRTYTWO_INCH(32),
        FORTY_INCH(40),
        FIFTY_INCH(50),
        FIFTYFIVE_INCH(55),
        SIXTY_INCH(60);

        private final int inches;

        AvailableSizes(int inches) {
            this.inches = inches;
        }

        public int getInches() {
            return inches;
        }
    }

    public enum RefreshRate {
        FIFTY_HERTZ,
        ONE_HUNDRED_HERTZ,
        TWO_HUNDRED_HERTZ;
    }

    public enum ScreenType {
        LED,
        OLED,
        QLED,
        LCD
    }

    public enum ScreenQuality {
        HD,
        FULL_HD,
        UHD,
        FOUR_K,
        EIGHT_K
    }


    // Getters & Setters
    public AvailableSizes getAvailableSizes() {
        return availableSizes;
    }

    public void setAvailableSizes(AvailableSizes availableSizes) {
        this.availableSizes = availableSizes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public RefreshRate getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(RefreshRate refreshRate) {
        this.refreshRate = refreshRate;
    }

    public ScreenType getScreenType() {
        return screenType;
    }

    public void setScreenType(ScreenType screenType) {
        this.screenType = screenType;
    }

    public ScreenQuality getScreenQuality() {
        return screenQuality;
    }

    public void setScreenQuality(ScreenQuality screenQuality) {
        this.screenQuality = screenQuality;
    }

    public boolean isSmartTv() {
        return smartTv;
    }

    public void setSmartTv(boolean smartTv) {
        this.smartTv = smartTv;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isVoiceControl() {
        return voiceControl;
    }

    public void setVoiceControl(boolean voiceControl) {
        this.voiceControl = voiceControl;
    }

    public boolean isHdr() {
        return hdr;
    }

    public void setHdr(boolean hdr) {
        this.hdr = hdr;
    }

    public boolean isBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public boolean isAmbiLight() {
        return ambiLight;
    }

    public void setAmbiLight(boolean ambiLight) {
        this.ambiLight = ambiLight;
    }

    public int getOriginalStock() {
        return originalStock;
    }

    public void setOriginalStock(int originalStock) {
        this.originalStock = originalStock;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public LocalDateTime getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(LocalDateTime dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public LocalDateTime getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDateTime dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }
}
