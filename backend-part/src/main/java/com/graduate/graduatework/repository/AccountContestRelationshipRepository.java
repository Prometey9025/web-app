package com.graduate.graduatework.repository;

import com.graduate.graduatework.model.AccountContestRelationship;
import com.graduate.graduatework.model.key.AccountContestRelationshipKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountContestRelationshipRepository extends JpaRepository<AccountContestRelationship, AccountContestRelationshipKey>,
        JpaSpecificationExecutor<AccountContestRelationship> {
}
