package com.oms.userMS.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oms.userMS.entity.Buyer;


@Repository
public interface BuyerRepository extends CrudRepository<Buyer, String>{

	Optional<Buyer> findByEmailID(String emailID);
	Buyer findByBuyerID(String buyerID);
	Optional<Buyer> findByPhoneNo(String phoneNo);
}
