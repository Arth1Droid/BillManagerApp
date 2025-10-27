package com.arthDroid1.persistence.entity;

public enum BillType {
    WATER_BILL("Water Bill"),
    ELECTRICITY_BILL("Electricity Bill"),
    INTERNET_BILL("Internet Bill"),
    RENT_BILL("Rent Bill"),
    OTHERS("Others");

    private String description;

    BillType(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
