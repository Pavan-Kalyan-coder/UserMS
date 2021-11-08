package com.oms.userMS.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oms.userMS.entity.WishList;
import com.oms.userMS.entity.WishListPK;

@Repository
public interface WishListRepository extends CrudRepository<WishList, WishListPK>{

	List<WishList> findByBuyerID(String buyerID);

	List<WishList> findByProductID(String productID);

	

}
