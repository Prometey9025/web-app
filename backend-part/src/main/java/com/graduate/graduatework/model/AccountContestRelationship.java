package com.graduate.graduatework.model;

import com.graduate.graduatework.model.key.AccountContestRelationshipKey;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@IdClass(AccountContestRelationshipKey.class)
@Entity
@Table(schema = "public", name = "account_contest_relationship")
public class AccountContestRelationship {
    @Id
    @ManyToOne
    @JoinColumn(name = "account_id")
    @NotNull
    private Account account;

    @Id
    @ManyToOne
    @JoinColumn(name = "contest_id")
    @NotNull
    private Contest contest;

    @Column(name = "created_at", columnDefinition = "timestamp default current_timestamp")
    @NotNull
    @CreatedDate
    @JsonIgnore
    private Timestamp createdAt;
}
