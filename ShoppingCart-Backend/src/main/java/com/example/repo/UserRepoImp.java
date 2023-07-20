package com.example.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.entities.User;
import com.example.request.LoginRequest;
import com.example.request.SignUpRequest;
import com.example.request.UpdateRequest;
import com.example.response.LoginResponse;
import com.example.response.SignUpResponse;
import com.example.response.profileResponse;

@Component
public  class UserRepoImp{
    @Autowired
    UserRepo userR;
    public boolean checkMail(String s){
        List<User> u =userR.findByemail(s);
        if(u.isEmpty())
            return true;
        return false;
    }
    public List<User> findByemail(String s){
        return userR.findByemail(s);
    }
    public void addUser(User u){
        userR.save(u);
    }
    public boolean checkUser(String s){
        List<User> u =findByemail(s);
        if(u==null)
            return false;
        return true;
    }
    public User findUserByEmail(String s){
        User u = findByemail(s).get(0);
        return u;
    }
    public void logOut(){

    }

    public profileResponse findEntityById(int id) {
    	profileResponse profile=new profileResponse();
    	User u = userR.findById(id);
    	profile.setEmail(u.getEmail());
    	profile.setId(u.getId());
    	profile.setName(u.getName());
    	profile.setPhone(u.getPhoneN());
    	profile.setStreet(u.getStreet());
    	profile.setCity(u.getCity());
    	profile.setCountry(u.getCountry());
    	profile.setPincode(u.getPincode());
    	return profile;
         
    }
	public String updateUser(UpdateRequest req,int id) {
		profileResponse profile=new profileResponse();
    	User u = userR.findById(id);
    	if(req.getStreet()!=null) {
    		u.setStreet(req.getStreet());
    	}
    	if(req.getCity()!=null) {
    		u.setCity(req.getCity());
    	}
    	if(req.getCountry()!=null) {
    		u.setCountry(req.getCountry());
    	}
    	if(req.getPincode()!=0)
    		u.setPincode(req.getPincode());
    	if(req.getPhone()!=0) {
    		u.setPhoneN(req.getPhone());
    	}
    	
    	userR.save(u);
    	
    	return "success";
    
	}
	public SignUpResponse SignUp(SignUpRequest req) {
		 User u = new User();
	        u.setName(req.getName());
	        u.setEmail(req.getEmail());
	        u.setPassword(req.getPassword());
	       
	        SignUpResponse signResp = new SignUpResponse();
	        try {
	            if (!checkMail(req.getEmail())) {
	            	u=(User) userR.findByemail(req.getEmail());
	                signResp.setId(u.getId());
	                signResp.setMessage("Email already exists");
	            }
	            else{
	                addUser(u);
	                signResp.setId(u.getId());
	                if (u.getId() == null)
	                    signResp.setMessage("Email and password are required field");
	                else
	                    signResp.setMessage("User created");
	            }
	        } catch (Exception e) {

	            addUser(u);
	            signResp.setId(u.getId());
	            if (u.getId() == null)
	                signResp.setMessage("Email and password are required field");
	            else
	           signResp.setMessage("User created");
	        }
			return signResp;
	}
	public LoginResponse Login(LoginRequest req) {
		 LoginResponse loginResponse = new LoginResponse();
	        try{
	            if(checkUser(req.getEmail())){
	                User u =findUserByEmail(req.getEmail());
	                if(u.getPassword().equals(req.getPassword()))
	                    loginResponse.setResult("Welcome  "+u.getName());
	            }
	            else {
	                loginResponse.setResult("User doesnt exists");
	            }

	        }
	        catch(Exception e){

	        }
			return loginResponse;
	}
   
   
}
	