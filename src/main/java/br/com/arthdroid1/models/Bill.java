package br.com.arthdroid1.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="description")
    private String description;
    @Column(name ="pay_date")
    private LocalDate payDay;
    @Column(name ="due_date")
    private LocalDate dueDate;
  
    @Enumerated(EnumType.STRING)
    @Column(name ="bill_status")
    private BillStatus status;
    
    @Enumerated(EnumType.STRING)
    @Column(name ="bill_type")
    private BillType billType;
    
    @Column(name ="cost")
    private Double cost;
    
    public Bill() {
    	
    }
    public Bill(BillType billType,double cost, String description, LocalDate dueDate){
       
        if (description.isBlank()) {
            throw new IllegalArgumentException("The description cannot be blank");
        }
        if (cost < 0) {
            throw new IllegalArgumentException("The cost cannot be 0 or negative");       
        }  

        if (dueDate == null){
            throw new IllegalArgumentException("Due date cannot be null.");
        }

        this.cost = cost;

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

    public Double getCost(){
        return cost;
    }

    public Long getId() {
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

    public void setStatus(BillStatus status){
        this.status = status;
    }
    public void setBillType(BillType billType){
        this.billType = billType;
    }

    public void setPayDay(LocalDate payDay) {
        this.payDay = payDay;
    }

    public void setCost(Double cost) {
    	if (cost <= 0) {
            throw new IllegalArgumentException("The cost cannot be zero or negative");
        }
        this.cost = cost;
    }

}