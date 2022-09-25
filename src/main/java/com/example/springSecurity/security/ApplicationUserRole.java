package com.example.springSecurity.security;

import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static com.example.springSecurity.security.ApplicationUserPermission.*;

@RequiredArgsConstructor
public enum ApplicationUserRole {

    STUDENT(Sets.newHashSet()),

    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE,
            STUDENT_READ, STUDENT_WRITE));

    private final Set<ApplicationUserPermission> permissions;
}
