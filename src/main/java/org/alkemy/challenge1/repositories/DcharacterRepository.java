package org.alkemy.challenge1.repositories;

import org.alkemy.challenge1.domain.Dcharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.core.userdetails.User;

@RepositoryRestResource(path = "characters")
public interface DcharacterRepository extends JpaRepository<Dcharacter, Long> {
}
