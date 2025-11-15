package br.com.arthdroid1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arthdroid1.models.Bill;

public interface BillRepository extends JpaRepository<Bill, Long>{
	
}
