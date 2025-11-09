package com.arthDroid1.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.arthDroid1.persistence.entity.Bill;
import com.arthDroid1.persistence.entity.BillStatus;
import com.arthDroid1.persistence.entity.BillType;
import com.arthDroid1.services.BillService;

public class BillController {
    private BillService billService = new BillService();
    private Scanner sc = new Scanner(System.in);

    private void displayMenu() {
        System.out.println("\n==================================");
        System.out.println("        Bill Manager App     ");
        System.out.println("==================================");
        System.out.println("1 - Register a new bill");        
        System.out.println("2 - List all bills ");
        System.out.println("3 - List bills by status ");   
        System.out.println("4 - Pay bill"); 
        System.out.println("5 - Update a bill");
        System.out.println("6 - Delete a bill");
        System.out.println("7 - Show total amount");
        System.out.println("0 - Exit");
        System.out.print("Type a option: ");
    }

    public void startMenu(){
        int option = -1;
        while (option != 0) {
            displayMenu();
        if (sc.hasNextInt()) {
            option = sc.nextInt();
        } else {
            System.out.println(" Opção inválida. Por favor, digite um número.");
            sc.nextLine(); 
            continue; 
        }
            sc.nextLine(); 

            switch (option) {
                case  1:
                    createBill();
                    break;
                case 2:
                    listAllBills();
                    break;
                case 3:
                    listByStatus();
                    break; 
                case 4:
                    payBill();
                    break;
                case 5:
                    updateBill();
                    break;   
                case 6:
                    deleteBill();
                    break;
                case 7:
                    showTotalAmount();
                    break;
                case 0:
                    System.out.println("exiting..");
                    break;
                default:
                    break;
            }
        }
    }

    private void createBill() {
    
    System.out.println("\n--- New Bill Register ---");
    System.out.println("Type the bill typw (Water Bill, Electricity Bill, Internet Bill, Rent Bill, Others): ");
    String inputType = sc.nextLine().trim(); 

    BillType selectedType = BillType.fromDescription(inputType);

    if (selectedType == null) {
        System.out.println(" Invalid bill type . try again.");
        return; 
    }
    
    double cost = 0.0;
    System.out.println("Type the bill cost: ");
    
    if (sc.hasNextDouble()) {
        cost = sc.nextDouble();
    } else {
        System.out.println(" Invalid cost. Please type a number");
        sc.nextLine(); 
        return;
    }
    sc.nextLine(); 

    LocalDate dueDate = null;
    System.out.println("Type the due date [yyyy-MM-dd]: ");
    String dateInput = sc.nextLine();
    
    try {
        dueDate = LocalDate.parse(dateInput);
    } catch (Exception e) {
        System.out.println(" \"Invalid date format! Use the yyyy-MM-dd format (Ex: 2025-11-20).");
        return;
    }

    System.out.println("Type a description: ");
    String description = sc.nextLine();
    
    
    Bill newBill = new Bill(selectedType, cost,description, dueDate);
    
    billService.registerBill(newBill); 
    
    System.out.println("\n Sucess! Bill registred.");
    System.out.println("Type: " + selectedType.getDescription() + " | Due date: " + dueDate);
    }

    private void listAllBills(){
        System.out.println("\n--- Bill List ---");
        billService.listAll();
    }

    private void listByStatus(){
    System.out.println("Type bill status (Open, Paid, Overdue): ");
    String billStatus = sc.nextLine().trim();
    List<Bill> result = null; 

    if (!billStatus.equalsIgnoreCase("Open") && 
        !billStatus.equalsIgnoreCase("Overdue") && 
        !billStatus.equalsIgnoreCase("Paid")) {
        
        System.out.println("Invalid bill status. Trry again.");
    }
    
    if (billStatus.equalsIgnoreCase("Open")) {
        result = billService.listOpenBills();
    }
    else if (billStatus.equalsIgnoreCase("Overdue")){
        result = billService.listOverdueBills();
    }
    else if (billStatus.equalsIgnoreCase("Paid")) {
        result = billService.listPaidBills();
    }

    System.out.println("\n--- Bills with Status: " + billStatus + " ---");

    if (result == null || result.isEmpty()) {
        System.out.println("Any bill found with status " + billStatus);
    } else {
        result.forEach(System.out::println);
    }
  }

    private void payBill(){
        System.out.println("Type the bill id: ");
        String searchedId = sc.nextLine();
        UUID id = null;
        LocalDate payDate = null;

        try {
             id = UUID.fromString(searchedId);
        } catch (IllegalArgumentException e) {
            System.out.println(" Invalid id format!");
            return;
        }
        System.out.println("Type the pay day (yyyy-MM-dd):  ");
        String payDay = sc.nextLine();

        try {
             payDate = LocalDate.parse(payDay);
        } catch (Exception e) {
            System.out.println("Invalid date format! Use the yyyy-MM-dd format.");
            return;
        }

        boolean success = billService.payBill(id, payDate);

        if (success) {
            System.out.println("\n Sucessful! Bill ID: " + id + " payed: " + payDate + ".");
        } else {
            System.out.println("\nFailed to pay the bill. Please check if the ID exists or if the bill has already been paid.");
        }


    }

    private void updateBill(){
        System.out.println("Type the bill id; ");
        String searchedId = sc.nextLine();
        UUID id = null;

        try {
             id = UUID.fromString(searchedId);
            System.out.println("UUID válido: " + id);
        } catch (IllegalArgumentException e) {
            System.out.println("Formato de UUID inválido!");
        }

        Bill existingBill = billService.findById(id);
        
        if(existingBill != null){
            System.out.println("\n--- Updating Bill  ---");
            System.out.println("Type the new bill type (Water Bill, Electricity Bill, Internet Bill, Rent Bill, Others): ");
            String inputType = sc.nextLine().trim(); 

            BillType selectedType = BillType.fromDescription(inputType);

            if (selectedType == null) {
                System.out.println(" Invalid bill type . try again.");
                return; 
            }
                
            double cost = 0.0;
            System.out.println("Type the new bill cost: ");
                
            if (sc.hasNextDouble()) {
                cost = sc.nextDouble();

            } else {
                System.out.println(" Invalid cost. Please type a number");
                sc.nextLine(); 
                return;
            }
            sc.nextLine(); 

            LocalDate dueDate = null;
            System.out.println("Type the new due date [yyyy-MM-dd]: ");
            String dateInput = sc.nextLine();
                
            try {
                dueDate = LocalDate.parse(dateInput);
            } catch (Exception e) {
                System.out.println(" \"Invalid date format! Use the yyyy-MM-dd format (Ex: 2025-11-20).");
                return;
            }

            System.out.println("Type a new description: ");
            String description = sc.nextLine();

            existingBill.setBillType(selectedType);
            existingBill.setCost(cost);
            existingBill.setDescription(description);
            existingBill.setDueDate(dueDate);
                
            billService.updatedBill(id,existingBill); 
                
            System.out.println("\n Sucess! Bill updated.");
            System.out.println("Type: " + selectedType.getDescription() + " | Due date: " + dueDate);            }
                
        }

    private void deleteBill(){
        System.out.println("Type the bill id: ");
        String searchedId = sc.nextLine();
        UUID id = null;

        try {
             id = UUID.fromString(searchedId);
        } catch (IllegalArgumentException e) {
            System.out.println("  Invalid id format!");
            return;
        }
        boolean success = billService.deleteBill(id); 

        if(success){
            System.out.println("\n Bill removed successfully!");
        }
        else{
            System.out.println("The bill: " + id + " not found.");
        }
    }

    private void showTotalAmount(){
        double totalAmount = billService.getTotalOpenAmount();
        System.out.println("Total amount: " + String.format("%.2f", totalAmount) + " $");    }
 }

