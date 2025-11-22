package br.com.arthdroid1.dtos;

import java.time.LocalDate;

import br.com.arthdroid1.models.BillType;
import jakarta.validation.constraints.NotBlank;

public class BillRequestDTO {
	 @NotBlank
	 String description;
	 @NotBlank
	 LocalDate dueDate;
	 @NotBlank
	 Double cost;
	 @NotBlank
	 BillType billType;
	 
	 public BillRequestDTO( String description,  LocalDate dueDate,  Double cost,  BillType billType) {
		this.description = description;
		this.dueDate = dueDate;
		this.cost = cost;
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

	 public Double getCost() {
		 return cost;
	 }

	 public void setCost(Double cost) {
		 this.cost = cost;
	 }

	 public BillType getBillType() {
		 return billType;
	 }

	 public void setBillType(BillType billType) {
		 this.billType = billType;
	 }
	 
	 
	 
	 
	 
}
