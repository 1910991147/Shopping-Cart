package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.entities.Order;
import com.example.entities.Product;
import com.example.repo.CartRepoImp;
import com.example.repo.OrderRepoImp;
import com.example.repo.ProductRepoImp;
import com.example.repo.UserRepoImp;
import com.example.request.AddRequest;
import com.example.request.ChangeQuantity;
import com.example.request.FilterRequest;
import com.example.request.LoginRequest;
import com.example.request.LogoutRequest;
import com.example.request.ModifyRequest;
import com.example.request.SignUpRequest;
import com.example.request.UpdateRequest;
import com.example.response.AddResponse;
import com.example.response.CartByUserIdRes;
import com.example.response.CartItemRes;
import com.example.response.LoginResponse;
import com.example.response.LogoutResponse;

import com.example.response.SignUpResponse;
import com.example.response.profileResponse;

@Controller
public class ShoppingController {
    @Autowired
    UserRepoImp userRepoImp;
    @Autowired
    ProductRepoImp productRepoImp;
    @Autowired
    CartRepoImp cartRepoImp;
    @Autowired
    OrderRepoImp orderRepoImp;

    @PostMapping("/signup")
    public ResponseEntity<?> Signup(@RequestBody SignUpRequest req) {
    	SignUpResponse signResp=userRepoImp.SignUp(req);
        return new ResponseEntity<SignUpResponse>(signResp, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req){
       LoginResponse loginResponse=userRepoImp.Login(req);
        return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.UNAUTHORIZED);
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest req) {
        LogoutResponse logoutResp = new LogoutResponse();
        logoutResp.setMessage("Success");
        return new ResponseEntity<LogoutResponse>(logoutResp, HttpStatus.OK);
    }

    @GetMapping("/getProfile/{id}")
    public ResponseEntity<?> getProfile(@PathVariable int id){
    	profileResponse profile=userRepoImp.findEntityById(id);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
    @PostMapping("/updateProfile/{id}")
    public ResponseEntity<?>updateProfile(@RequestBody UpdateRequest req,@PathVariable int id){
    	String status=userRepoImp.updateUser(req,id);
		return new ResponseEntity<>(status, HttpStatus.OK);
    }
    @PostMapping("/products/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody AddRequest req) {
    	AddResponse addResp =productRepoImp.addProduct(req);
        return new ResponseEntity<AddResponse>(addResp, HttpStatus.OK);
    }
    @GetMapping("/products/getById/{productid}")
    public ResponseEntity<?> getProduct(@PathVariable int productid){
        Product prof = productRepoImp.findEntityById(productid);
        return new ResponseEntity<Product>(prof, HttpStatus.OK);
    }
    @PostMapping("/products/update/{id}")
    public ResponseEntity<?>UpdateProduct(@RequestBody ModifyRequest req){
    	Product prof=productRepoImp.updateProduct(req);
		return new ResponseEntity<Product>(prof, HttpStatus.OK);
    }
    @GetMapping("/products/{category}")
    public ResponseEntity<?> getProduct(@PathVariable String category){
        List<Product> prof = productRepoImp.findEntityBycategory(category);
        
        return new ResponseEntity<List<Product>>(prof,HttpStatus.OK);
    }
    @GetMapping("/products/search/{searchString}")
    public ResponseEntity<?> search(@PathVariable String searchString){
        List<Product> prof = productRepoImp.findEntityBysearch(searchString);
        
        return new ResponseEntity<List<Product>>(prof,HttpStatus.OK);
    }
	@GetMapping("/products/{category}/getFilteredProducts")
	public ResponseEntity<?> getProduct(@PathVariable String category,@RequestBody FilterRequest req){
	    List<Product> prof = productRepoImp.findEntityBycategoryfilter(category,req);
	    
	    return new ResponseEntity<List<Product>>(prof,HttpStatus.OK);
	}
	@GetMapping("/cart/{userId}/getCart")
	 public ResponseEntity<?> getCart(@PathVariable int userId){
        List<CartByUserIdRes> prof = cartRepoImp.getCartByUserId(userId);
        return new ResponseEntity<List<?>>(prof,HttpStatus.OK);
	}
	@GetMapping("/cart/{userId}/getCartItem/{cartitemId}")
	public ResponseEntity<?> getCartByCartId(@PathVariable int userId,@PathVariable int cartitemId){
		CartItemRes item=cartRepoImp.getCartByCartId(userId,cartitemId);
		return new ResponseEntity<>(item,HttpStatus.OK);
		
	}
	@GetMapping("/cart/{userId}/add/{productId}")
	public ResponseEntity<?> addCart(@PathVariable int userId,@PathVariable int productId){
		CartItemRes prof =cartRepoImp.addCart(userId,productId);
		return new ResponseEntity<>(prof,HttpStatus.OK);
	}
	@GetMapping("/cart/{userId}/remove/{productId}")
	public ResponseEntity<?> removeCart(@PathVariable int userId,@PathVariable int productId){
		String s=cartRepoImp.removeCart(userId,productId);
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
	@PostMapping("/cart/{userId}/changeQuantity/{productId}")
	public ResponseEntity<?> changeQuantity(@PathVariable int userId,@PathVariable int productId,@RequestBody ChangeQuantity req){
	CartItemRes prof=cartRepoImp.changequantity(userId,productId,req.getQuantity());
	return new ResponseEntity<>(prof,HttpStatus.OK);
	}
	@PostMapping("/cart/changeQuantity/{cartItemId}")
	public ResponseEntity<?> changeQuantity(@PathVariable int cartItemId,@RequestBody ChangeQuantity req){
		CartItemRes prof=cartRepoImp.changequantity1(cartItemId,req.getQuantity());
		return new ResponseEntity<>(prof,HttpStatus.OK);
		
	}
	@GetMapping("/order/{userId}/createOrder")
	public ResponseEntity<?> createOrder(@PathVariable int userId){
		List<Order> order=orderRepoImp.createOrder(userId);
		return new ResponseEntity<>(order,HttpStatus.OK);
		
	}
	@GetMapping("/order/{userId}/getOrders")
	public ResponseEntity<?> getOrders(@PathVariable int userId){
		List<Order> order=orderRepoImp.getOrders(userId);
		
		return new ResponseEntity<>(order,HttpStatus.OK);
		
	}
	
}