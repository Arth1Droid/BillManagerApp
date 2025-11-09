package com.arthDroid1.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.arthDroid1.persistence.entity.Bill;
import com.arthDroid1.persistence.entity.BillStatus;
import com.arthDroid1.repositories.BillRepository;

public class BillService {
  private BillRepository repository = new BillRepository();


  public void registerBill(Bill newBill){
    
    if ( newBill == null) {
      throw new IllegalArgumentException("The bill  cannot be null ");
    }
    if (newBill.getCost() <= 0 )   {
      throw new IllegalArgumentException("The bill cost must be be greater than 0 ");

    }
    UUID existBill = newBill.getId();
    if (repository.findById(existBill) != null) {
      throw new IllegalArgumentException("The bill already exist");
    }

    repository.save(newBill);

  }

  public boolean payBill(UUID id, LocalDate payDay ){
    Bill billTopay = repository.findById(id);
    if (payDay == null) {
      throw new IllegalArgumentException("The pay date cannot be null");
    }

    if (billTopay == null) {
      throw new IllegalArgumentException("The bill not exist");
    }
    if (billTopay.getStatus() == BillStatus.PAID) {
      return false;
    }
    billTopay.setPayDay(payDay);
    billTopay.setStatus(BillStatus.PAID);
    repository.update(billTopay);
    return true;
  }

  public List<Bill> listAll(){
     
    return repository.findAll();
  }

  public List<Bill> listOpenBills(){
    List <Bill> foundBills = repository.findAll();
    
    return foundBills.stream()
    .filter(bill -> bill.getStatus() == BillStatus.OPEN) 
    .collect(java.util.stream.Collectors.toList());
    
  }

  public List<Bill> listOverdueBills(){
    List <Bill> foundBills = repository.findAll();
    
    return foundBills.stream()
    .filter(bill -> bill.getStatus() == BillStatus.OVERDUE) 
    .collect(java.util.stream.Collectors.toList());
  }

  public List<Bill> listPaidBills(){
    List <Bill> foundBills = repository.findAll();
    
    return foundBills.stream()
    .filter(bill -> bill.getStatus() == BillStatus.PAID) 
    .collect(java.util.stream.Collectors.toList());
  }

  public double getTotalOpenAmount(){
    
    return repository.findAll().stream()
        .filter(bill -> bill.getStatus() != BillStatus.PAID) 
        .mapToDouble(Bill::getCost) 
        .sum();
  }

public boolean updatedBill(UUID id, Bill updatedBill){
    Bill existingBill = repository.findById(id);
    LocalDate today = LocalDate.now();

    if (existingBill == null) {
        throw new IllegalArgumentException("This bill not exist.");
    }

    if (existingBill.getStatus() == BillStatus.PAID) {
        throw new IllegalArgumentException("You cannot update an account that has already been paid for");
    }

    if (updatedBill.getDueDate().isBefore(today)) {
        throw new IllegalArgumentException("The new due date cannot be earlier than today.");
    }
  
    existingBill.setCost(updatedBill.getCost());
    existingBill.setDueDate(updatedBill.getDueDate());
    existingBill.setDescription(updatedBill.getDescription());

    repository.update(existingBill); 
    
    return true;
}

  public boolean deleteBill(UUID id){
    Bill existingBill = repository.findById(id);
    if (existingBill == null) {
      throw new IllegalArgumentException("This bill not exist");
    }

    repository.delete(id);
    return true;

  }

  public void checkBillsDueStatus(LocalDate today){
    List<Bill> allBills = repository.findAll();
    
    allBills.stream()
        .filter(bill -> bill.getStatus() == BillStatus.OPEN && 
                         (bill.getDueDate().isBefore(today) || bill.getDueDate().isEqual(today)))
        .forEach(bill -> {
            bill.setStatus(BillStatus.OVERDUE);
            repository.update(bill); 
        });
  
}

  public Bill findById(UUID id) {
    Bill foundId = repository.findById(id);
    return foundId;

  }




}
