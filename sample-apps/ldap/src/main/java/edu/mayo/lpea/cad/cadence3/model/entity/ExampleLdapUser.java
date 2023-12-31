package edu.mayo.lpea.cad.cadence3.model.entity;

import edu.mayo.lpea.cad.cadence3.model.dto.ExampleLdapUserDto;
import edu.mayo.lpea.cad.cadence3.security.entity.AppUser;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import java.lang.reflect.InvocationTargetException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.beanutils.BeanUtils;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Embeddable
@Entity
public class ExampleLdapUser extends AppUser {
  private String customUserField;

  @Override
  public ExampleLdapUser getInstantiatedCustomAppUserType() {
    return this;
  }

  @Override
  public ExampleLdapUser createNewCustomAppUserInstance() {
    return new ExampleLdapUser();
  }

  public ExampleLdapUserDto convertToDto()
      throws InvocationTargetException, IllegalAccessException {
    ExampleLdapUserDto exampleLdapUserDto = new ExampleLdapUserDto();
    BeanUtils.copyProperties(exampleLdapUserDto, this);
    return exampleLdapUserDto;
  }
}
