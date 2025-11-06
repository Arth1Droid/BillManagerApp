package com.arthDroid1.persistence.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class Bill {
    private UUID id;
    private String description;
    private LocalDate payDay;
    private LocalDate dueDate;
    private BillStatus status;
    private BillType billType;
    private double cost;

    public Bill(BillType billType,double cost, String description, LocalDate dueDate){
       
        if (description.isBlank()) {
            throw new IllegalArgumentException("The description cannt be blank");
        }
        if (cost < 0) {
            throw new IllegalArgumentException("The cost cannot be 0 or negative");       
        }  

        if (dueDate == null){
            throw new IllegalArgumentException("Due date cannot be null.");
        }

        this.cost = cost;
        this.id = UUID.randomUUID();
        this.dueDate = dueDate;
        this.billType = billType;
        this.description = description;
        this.payDay = null;
        this.status = BillStatus.OPEN;
    }

    public void payBill(LocalDate paymentDateTime){
        if (!(status == BillStatus.OPEN || status ==  BillStatus.OVERDUE)) {
            throw new IllegalArgumentException("The bill is already paid");
        }
        if (paymentDateTime == null) {
            throw new IllegalArgumentException("The payment date/time cannot be null.");
        }
        
        this.payDay = paymentDateTime;
        this.status = BillStatus.PAID;
    }

    public void verifyDueDate(LocalDate today){
        if (today.isAfter(dueDate) && status == BillStatus.OPEN) {
            status = BillStatus.OVERDUE;
        }
    }

    public Long remainingDays(LocalDate today){
        if (status == BillStatus.OVERDUE) {
            return 0L;
        }

        Long daysBetweenDates = ChronoUnit.DAYS.between(today, dueDate);

        return Math.max(0L, daysBetweenDates);

    }

    public boolean isOverdue(){
        return status == BillStatus.OVERDUE;
    }

    public double getCost(){
        return cost;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getPayDay() {
        return payDay;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public BillStatus getStatus() {
        return status;
    }

    public BillType getBillType() {
        return billType;
    }

    public void setDescription(String description) {
        
        if (description == null || description.isBlank()) {
             throw new IllegalArgumentException("The description cannot be blank.");
    }
        this.description = description;
    }

    public void setDueDate(LocalDate dueDate) {
        if (dueDate.isBefore(LocalDate.now())) {
             throw new IllegalArgumentException("The due date cannot be before today");
        }
        this.dueDate = dueDate;
    } 

}
