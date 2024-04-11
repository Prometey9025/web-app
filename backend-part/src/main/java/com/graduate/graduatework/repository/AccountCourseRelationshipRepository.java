package com.graduate.graduatework.repository;

import com.graduate.graduatework.model.AccountCourseRelationship;
import com.graduate.graduatework.model.key.AccountCourseRelationshipKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountCourseRelationshipRepository extends JpaRepository<AccountCourseRelationship, AccountCourseRelationshipKey>,
        JpaSpecificationExecutor<AccountCourseRelationship> {
}
