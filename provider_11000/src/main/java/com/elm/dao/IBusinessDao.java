package com.elm.dao;

import com.elm.pojo.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBusinessDao extends JpaRepository<Business,Integer> {
    List<Business> findBusinessesByBusinessNameContaining(String name);
    Business findBusinessByBusinessId(int businessId);
    List<Business> findBusinessesByOrderTypeId(int orderTypeId);
}
