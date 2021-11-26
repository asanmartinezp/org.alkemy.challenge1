package org.alkemy.challenge1.repositories;

import org.alkemy.challenge1.domain.Dcharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "characters")
public interface DcharacterRepository extends JpaRepository<Dcharacter, Long> {
}
