package com.graduate.graduatework.model.key;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class AccountCourseRelationshipKey implements Serializable {
    private Long account;
    private Long course;
}
