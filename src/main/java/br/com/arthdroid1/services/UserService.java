package br.com.arthdroid1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arthdroid1.models.Bill;
import br.com.arthdroid1.models.User;
import br.com.arthdroid1.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public User createUSer(User newUser) {		
	    if(newUser.getName() == null) {
			throw new IllegalArgumentException("The name cannot be null");	
		}
		
		if(newUser.getEmail() == null) {
			throw new IllegalArgumentException("The email cannot be null");	
		}
		
		Optional<User> existingUserEmail = repository.findByEmail(newUser.getEmail());

	    if(existingUserEmail.isPresent()) {
			throw new IllegalArgumentException("This email is already in use");
		}
		  User userResponse = repository.save(newUser);
		  
		  return userResponse;
			
	}
	
	public List<User> findAllUsers(){
		return repository.findAll();
	}
	
	public User findById(Long id){
		User existentUser = repository.findById(id)
				  .orElseThrow(() -> new IllegalArgumentException("User not fund"));
		return existentUser;
	}
	
	public User findByEmail(String email) {
		User existentUser = repository.findByEmail(email)
				  .orElseThrow(() -> new IllegalArgumentException("User not found"));
		return existentUser;
	}
	
	public User updateUser(Long id, User user) {
		User existentUser = findById(id);
		
		if(user.getName() != null) {
			existentUser.setName(user.getName());
		}
		if (user.getEmail() != null) {
			existentUser.setEmail(user.getEmail());
		}
		
		return repository.save(existentUser);
	}
	
	public boolean deleteUserById(Long id) {
		User existentUser = findById(id);
		if(existentUser.getBills() != null && !existentUser.getBills().isEmpty()) {
			return false;
		}	
		repository.delete(existentUser);
		return true;
	}
	
	public List<Bill> listAllUserBills(Long id){
		User user = findById(id);	
		return user.getBills();
	}
}

