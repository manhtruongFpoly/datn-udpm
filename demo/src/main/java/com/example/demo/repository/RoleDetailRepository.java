package com.example.demo.repository;

import com.example.demo.entity.ProductColor;
import com.example.demo.entity.RoleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDetailRepository extends JpaRepository<RoleDetail,Long> {
}
