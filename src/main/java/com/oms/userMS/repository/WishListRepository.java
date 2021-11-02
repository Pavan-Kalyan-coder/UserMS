package com.oms.userMS.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oms.userMS.entity.WishList;
import com.oms.userMS.entity.WishListPK;

@Repository
public interface WishListRepository extends CrudRepository<WishList, WishListPK>{	

}
