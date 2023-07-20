package com.example.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.Cart;


public interface CartRepo extends JpaRepository<Cart, Integer>{
	@Query("FROM Cart c WHERE c.userid = :userid")
	List<Cart> findCartByUserId(@Param("userid")int userid);
	@Query("FROM Cart c WHERE c.productid =:productid"
			+ " AND c.userid =:userid")
	Cart findCartItemByUserId(@Param("userid") int userid, @Param("productid") int productid);
	@Query("SELECT c FROM Cart c WHERE c.userid=:userid"
			+ " AND c.cartId=:cartId")
	Cart findCartItemBycartitemId(int userid, int cartId);
	@Query("SELECT c FROM Cart c WHERE c.cartId=:cartId")
	Cart findBycartItemId(int cartId);

	
}
