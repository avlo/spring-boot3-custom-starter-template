package edu.mayo.lpea.cad.cadence3.repository;

import edu.mayo.lpea.cad.cadence3.model.entity.ExampleLdapUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleLdapUserRepository extends JpaRepository<ExampleLdapUser, Long> {
}
