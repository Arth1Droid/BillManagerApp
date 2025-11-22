package br.com.arthdroid1.mapper;

import br.com.arthdroid1.dtos.BillRequestDTO;
import br.com.arthdroid1.dtos.BillResponseDTO;
import br.com.arthdroid1.models.Bill;
import br.com.arthdroid1.models.User;

public class BillMapper {
    
	public static Bill toEntity(BillRequestDTO dto, User user ) {
		Bill bill = new Bill(
	            dto.getBillType(),
	            dto.getCost(),
	            dto.getDescription(),
	            dto.getDueDate()
	        );
	        bill.setUser(user);
	        return bill;
	    }

    public static BillResponseDTO toResponse(Bill bill) {
        return new BillResponseDTO(
                bill.getId(),
                bill.getDescription(),
                bill.getDueDate(),
                bill.getPayDay(),
                bill.getCost(),
                bill.getStatus(),
                bill.getBillType()
            );
    }
 }

