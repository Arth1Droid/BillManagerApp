package com.arthDroid1.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.arthDroid1.persistence.entity.Bill;

public class BillRepository {

    private Map<UUID,Bill> bills = new HashMap<UUID, Bill>();

    public void save(Bill bill){
        if (bill == null || bill.getId() == null) {
            throw new IllegalArgumentException("Bill and Bill ID must not be null");
        }
            bills.put(bill.getId(), bill); 
        }
    
    public Bill findById(UUID id){
        return bills.get(id);
    }

    public List<Bill> findAll(){
        return new ArrayList<>(bills.values());
     }

    public boolean delete(UUID id){
        if (id== null ) {
            throw new IllegalArgumentException("Bill ID must not be null");
        }
        else if (!bills.containsKey(id)){
            throw new IllegalArgumentException("The bill not exist");
        }
            bills.remove(id); 
            return true;
        }
    public boolean update(Bill updatedBill){
        if (updatedBill == null || updatedBill.getId() == null) {
            throw new IllegalArgumentException("Updated Bill and Bill ID must not be null");
        }
        if (!bills.containsKey(updatedBill.getId())) {
            return false;
        }
        bills.put(updatedBill.getId(), updatedBill); 
        return true;
    }
}
        

    
    
    

