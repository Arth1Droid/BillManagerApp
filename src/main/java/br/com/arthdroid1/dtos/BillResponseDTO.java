package br.com.arthdroid1.dtos;

import java.time.LocalDate;

import br.com.arthdroid1.models.BillStatus;
import br.com.arthdroid1.models.BillType;

public class BillResponseDTO {
	Long id;
    String description;
    LocalDate dueDate;
    LocalDate payDay;
    Double cost;
    BillStatus status;
    BillType billType;
	
    public BillResponseDTO(Long id, String description, LocalDate dueDate, LocalDate payDay, Double cost,
			BillStatus status, BillType billType) {
		this.id = id;
		this.description = description;
		this.dueDate = dueDate;
		this.payDay = payDay;
		this.cost = cost;
		this.status = status;
		this.billType = billType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getPayDay() {
		return payDay;
	}

	public void setPayDay(LocalDate payDay) {
		this.payDay = payDay;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public BillStatus getStatus() {
		return status;
	}

	public void setStatus(BillStatus status) {
		this.status = status;
	}

	public BillType getBillType() {
		return billType;
	}

	public void setBillType(BillType billType) {
		this.billType = billType;
	}
    
    
    
}
