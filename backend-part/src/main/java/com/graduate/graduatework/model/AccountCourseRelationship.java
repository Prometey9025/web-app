package com.graduate.graduatework.model;

import com.graduate.graduatework.model.key.AccountCourseRelationshipKey;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@IdClass(AccountCourseRelationshipKey.class)
@Entity
@Table(schema = "public", name = "account_course_relationship")
public class AccountCourseRelationship {
    @Id
    @ManyToOne
    @JoinColumn(name = "account_id")
    @NotNull
    private Account account;

    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    @NotNull
    private Course course;

    @Column(name = "created_at", columnDefinition = "timestamp default current_timestamp")
    @NotNull
    @CreatedDate
    @JsonIgnore
    private Timestamp createdAt;
}
