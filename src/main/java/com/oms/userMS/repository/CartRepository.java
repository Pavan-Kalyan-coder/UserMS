package com.oms.userMS.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oms.userMS.entity.Cart;
import com.oms.userMS.entity.CartPK;

@Repository
public interface CartRepository extends CrudRepository<Cart, CartPK>{

	List<Cart> findByBuyerID(String buyerID);
}
