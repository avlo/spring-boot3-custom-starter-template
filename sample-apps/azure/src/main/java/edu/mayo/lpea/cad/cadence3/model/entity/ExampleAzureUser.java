package edu.mayo.lpea.cad.cadence3.model.entity;

import edu.mayo.lpea.cad.cadence3.model.dto.ExampleAzureUserDto;
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
public class ExampleAzureUser extends AppUser {
  private String customUserField;

  @Override
  public ExampleAzureUser getInstantiatedCustomAppUserType() {
    return this;
  }

  @Override
  public ExampleAzureUser createNewCustomAppUserInstance() {
    return new ExampleAzureUser();
  }

  public ExampleAzureUserDto convertToDto() throws InvocationTargetException, IllegalAccessException {
    ExampleAzureUserDto exampleAzureUserDto = new ExampleAzureUserDto();
    BeanUtils.copyProperties(exampleAzureUserDto, this);
    return exampleAzureUserDto;
  }
}
