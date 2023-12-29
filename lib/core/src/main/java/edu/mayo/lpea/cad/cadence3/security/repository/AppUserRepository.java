package edu.mayo.lpea.cad.cadence3.security.repository;

import edu.mayo.lpea.cad.cadence3.security.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
