package com.graduate.graduatework.repository;

import com.graduate.graduatework.model.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Long>, JpaSpecificationExecutor<Contest> {
}
