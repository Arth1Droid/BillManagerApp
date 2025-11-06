package com.arthDroid1.persistence.entity;

public enum BillStatus {
    
    OPEN ("Open"),
    PAID("Paid"),
    OVERDUE("Overdue");

    private String description;
    BillStatus(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

}
