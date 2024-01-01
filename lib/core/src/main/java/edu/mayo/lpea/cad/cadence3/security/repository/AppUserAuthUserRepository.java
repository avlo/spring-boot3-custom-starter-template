package edu.mayo.lpea.cad.cadence3.security.repository;

import edu.mayo.lpea.cad.cadence3.security.entity.AppUserAuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserAuthUserRepository extends JpaRepository<AppUserAuthUser, Long> {
}
