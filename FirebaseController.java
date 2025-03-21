package com.example.firebase_try.controller;

import java.util.List;

import org.checkerframework.checker.builder.qual.ReturnsReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.firebase_try.entity.User;
import com.example.firebase_try.service.FirestoreService;

@RestController
@RequestMapping("/firebase")
public class FirebaseController {
	
	@Autowired
	private FirestoreService firestoreService;

	@GetMapping("/test")
	public String testFirebase() {
		return "Firebase bağlantısı başarılı";
	}
	
	@PostMapping("/addUser")
	public String addUser(@RequestBody User user) {
		return firestoreService.addUser(user);
	}
	
	@GetMapping("/getUser")
	public User getUser(@RequestParam String userId) {
		return firestoreService.getUser(userId);
	}
	
	@PutMapping("/updateUser")
	public String updateUser(@RequestBody User user) {
		return firestoreService.updateUser(user);
	}
	
	@GetMapping("/getAllUsers")
	public List<User>getAllUsers(){
		return firestoreService.getAllUsers();
	}
}
