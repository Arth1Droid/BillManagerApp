package br.com.arthdroid1.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.arthdroid1.exceptions.BillCostMinimumRequiredException;
import br.com.arthdroid1.exceptions.ExistingBillException;
import br.com.arthdroid1.exceptions.NotFindBillException;
import br.com.arthdroid1.exceptions.NullBillException;
import br.com.arthdroid1.exceptions.NullPayDateException;
import br.com.arthdroid1.models.Bill;
import br.com.arthdroid1.models.BillStatus;
import br.com.arthdroid1.repositories.BillRepository;

@Service
public class BillService {
  private final BillRepository repository;
  
  public BillService(BillRepository repository) {
	  this.repository = repository;
  }


  public void registerBill(Bill newBill){
    
    if ( newBill == null) {
      throw new NullBillException();
    }
    if (newBill.getCost() <= 0 )   {
      throw new BillCostMinimumRequiredException();

    }
    
    Long existBill = newBill.getId();
    if (repository.findById(existBill) != null) {
        throw new ExistingBillException();
    }

    repository.saveAndFlush(newBill);

  }

  public boolean payBill(Long id, LocalDate payDay ){
    Bill billTopay = findById(id);
    if (payDay == null) {
      throw new NullPayDateException();
    }

    if (billTopay.getStatus() == BillStatus.PAID) {
      return false;
    }
    billTopay.setPayDay(payDay);
    billTopay.setStatus(BillStatus.PAID);
    repository.save(billTopay);
    return true;
  }

  public List<Bill> listAll(){
     
    return repository.findAll();
  }

  public List<Bill> listOpenBills(){
	 List<Bill> openBills = repository.findByStatus(BillStatus.OPEN);    
	 return openBills;
    
  }

  public List<Bill> listOverdueBills(){    
	 List<Bill> overdueBills = repository.findByStatus(BillStatus.OVERDUE);    
	 return overdueBills;
  }

  public List<Bill> listPaidBills(){
	  List<Bill> paidBills = repository.findByStatus(BillStatus.PAID);    
	  return paidBills;
  }

  public Double getTotalOpenAmount(){
    Double total = repository.sumOpenBills();
    return total;
  }

public boolean updatingBill(Long id, Bill updatedBill){
    Bill existingBill = findById(id);
    LocalDate today = LocalDate.now();

    if (updatedBill.getDescription() != null) {
        existingBill.setDescription(updatedBill.getDescription());
    }

    if (updatedBill.getDueDate() != null) {
        existingBill.setDueDate(updatedBill.getDueDate());
    }

    if (updatedBill.getCost() != null) { // 
        existingBill.setCost(updatedBill.getCost());
    }

    repository.save(existingBill); 
    
    return true;
}

  public boolean deleteBill(Long id){
	Bill existingBill = findById(id);
    repository.delete(existingBill);
    return true;

  }

  public void checkBillsDueStatus(LocalDate today){
    List<Bill> allBills = repository.findAll();
    
    List<Bill> overdueBills = allBills.stream()
    	    .filter(b -> b.getStatus() == BillStatus.OPEN && !b.getDueDate().isAfter(today))
    	    .toList();
    	overdueBills.forEach(b -> b.setStatus(BillStatus.OVERDUE));
    	repository.saveAll(overdueBills);
}

  public Bill findById(Long id) {
	    return repository.findById(id)
	            .orElseThrow(() -> new NotFindBillException());
  }




}
