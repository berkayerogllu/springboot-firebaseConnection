package com.example.firebase_try.service;

import org.springframework.stereotype.Service;
import com.example.firebase_try.entity.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;


@Service
public class FirestoreService {
	
	
	private final Firestore firestore;
	
	@Autowired
	public FirestoreService(Firestore firestore) {
		this.firestore = firestore;
	}
	
	public String addUser(User user) {
		try {
			CollectionReference users = firestore.collection("users");
			DocumentReference docRef = users.document(user.getId());
			
			docRef.set(user).get();
			return "User added succesfully";
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return "error adding user :" + e.getMessage();
		}
	}
	
	
	public User getUser(String userId) {
		try {
			CollectionReference users = firestore.collection("users");
			DocumentReference docRef = users.document(userId);
			DocumentSnapshot snapshot = docRef.get().get();
			
			if(snapshot.exists()) {
				return snapshot.toObject(User.class);
			} else{
				return null;
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public String updateUser(User user) {
		try {
			CollectionReference users = firestore.collection("users");
			DocumentReference docRef = users.document(user.getId());
			
			if(docRef.get().get().exists()) {
				docRef.set(user).get();
				return"User updated succesfully";
			} else {
				return "user not found";
			}	
		}catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return "Error updating user: " + e.getMessage();
		}
	}
	
	
	public List<User>getAllUsers(){
		List<User>userList = new ArrayList<>();
		
		try {
			CollectionReference users = firestore.collection("users");
			
			ApiFuture<QuerySnapshot> future = users.get();
			
			QuerySnapshot querySnapshot = future.get();
			
			for(DocumentSnapshot document:querySnapshot.getDocuments()) {
				User user = document.toObject(User.class);
				userList.add(user);
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
}
