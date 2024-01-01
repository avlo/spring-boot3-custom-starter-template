package edu.mayo.lpea.cad.cadence3.repository;

import edu.mayo.lpea.cad.cadence3.model.entity.ExampleJpaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleJpaUserRepository extends JpaRepository<ExampleJpaUser, Long> {
}
