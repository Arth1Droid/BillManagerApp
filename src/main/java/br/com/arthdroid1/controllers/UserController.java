package br.com.arthdroid1.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.arthdroid1.dtos.UserRequestDTO;
import br.com.arthdroid1.dtos.UserResponseDTO;
import br.com.arthdroid1.mapper.UserMapper;
import br.com.arthdroid1.models.Bill;
import br.com.arthdroid1.models.User;
import br.com.arthdroid1.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO request) {
		User newUser = UserMapper.toEntity(request);
		User createdUser = userService.createUser(newUser);
		return ResponseEntity.ok(UserMapper.toResponse(createdUser));
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<User> listUserById(@PathVariable Long id){
		User user = userService.findById(id);
        return ResponseEntity.ok(user);	
	}
	@GetMapping("/email/{email}")
	public ResponseEntity<User> listUserByEmail(@PathVariable String email){
		User user = userService.findByEmail(email);
        return ResponseEntity.ok(user);	
	}
	
	@GetMapping
	public ResponseEntity<List<User>> listAllUsers(){
		return ResponseEntity.ok(userService.findAllUsers());
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
		 User updatedUser = userService.updateUser(id, user);
		return ResponseEntity.ok(updatedUser);
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id){
		boolean deleted = userService.deleteUserById(id);
		if (!deleted) {
		    return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}/bills")
	public ResponseEntity<List<Bill>> findAllUserBill(@PathVariable Long id){
		List<Bill> userBills = userService.listAllUserBills(id);
		return ResponseEntity.ok(userBills);
	}
	
	
	

}
