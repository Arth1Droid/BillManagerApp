package br.com.arthdroid1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arthdroid1.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	
	
}
