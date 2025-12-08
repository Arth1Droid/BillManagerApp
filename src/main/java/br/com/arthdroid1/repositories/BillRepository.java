package br.com.arthdroid1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.arthdroid1.models.Bill;
import br.com.arthdroid1.models.BillStatus;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long>{
	
	@Query("SELECT SUM(b.cost) FROM Bill b WHERE b.status != 'PAID'")
	Double sumOpenBills();
	
	List<Bill> findByStatus(BillStatus status);
	
    List<Bill> findByUserId(Long userId);
	
}
