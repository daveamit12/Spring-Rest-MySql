package com.dave.springBootRest.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.dave.springBootRest.model.UserModel;

public class RestClient {
//Rest End Points:
public static final String GET_ALL_USERS_API=	"http://localhost:8080/api/users";
public static final String GET_ALL_USERS_BY_ID=	"http://localhost:8080/api/users/{id}";
public static final String CREATE_USERS_API =	"http://localhost:8080/api/users";
public static final String UPDATE_USERS_API=	"http://localhost:8080/api/users/{id}";
public static final String DELETE_USERS_API=	"http://localhost:8080/api/users/{id}";

static RestTemplate restTemplate=new RestTemplate();
	public static void main(String[] args) {
		callGetAllUsersAPI();
		callGetAllUsersByIdAPI();
		callCreateUserAPI();

	}

	private static void callGetAllUsersAPI() {
		
		HttpHeaders headers=new HttpHeaders();
		headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
		
		HttpEntity<String> entity=new HttpEntity<String>("para", headers);
		ResponseEntity<String> result=		restTemplate.exchange(GET_ALL_USERS_API, HttpMethod.GET, entity, String.class);
		
		System.out.println("Final result:="+result);
		
		
	}
	private static void callGetAllUsersByIdAPI() {
		
		Map<String,Integer> param=new HashMap<String, Integer>();
		param.put("id", 2);//in real ex. pass param coming in request: 
		UserModel res=restTemplate.getForObject(GET_ALL_USERS_BY_ID, UserModel.class, param);
		System.out.println("Get By Id result:="+"id:="+res.getId()+"Name:="+res.getFirstName()+" last:="+res.getLastName()+" mailid:="+res.getEmail());
		
	}
	
	private static void callCreateUserAPI() {
		UserModel u2=new UserModel("TTT","ZZZ","TT@gail.com");
		//postForEntity(url, request, responseType):
	ResponseEntity<UserModel> u3=  	restTemplate.postForEntity(CREATE_USERS_API, u2, UserModel.class);
		 System.out.println("body:="+u3.hasBody());
		 System.out.println("headers"+u3.getHeaders());
		 System.out.println("status:="+u3.getStatusCode());
	}
}
