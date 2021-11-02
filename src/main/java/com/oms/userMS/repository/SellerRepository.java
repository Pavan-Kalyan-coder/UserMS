package com.oms.userMS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oms.userMS.entity.Buyer;
import com.oms.userMS.entity.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, String>{

	Optional<Seller> findByEmailID(String emailID);

	Optional<Buyer> findByPhoneNo(String phoneNo);

	Seller findBySellerID(String sellerID);

}
