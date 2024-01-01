package edu.mayo.lpea.cad.cadence3.model.entity;

import edu.mayo.lpea.cad.cadence3.model.dto.ExampleJpaUserDto;
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
public class ExampleJpaUser extends AppUser {
  private String customUserField;

  @Override
  public ExampleJpaUser getInstantiatedCustomAppUserType() {
    return this;
  }

  @Override
  public ExampleJpaUser createNewCustomAppUserInstance() {
    return new ExampleJpaUser();
  }

  public ExampleJpaUserDto convertToDto()
      throws InvocationTargetException, IllegalAccessException {
    ExampleJpaUserDto exampleJpaUserDto = new ExampleJpaUserDto();
    BeanUtils.copyProperties(exampleJpaUserDto, this);
    return exampleJpaUserDto;
  }
}
