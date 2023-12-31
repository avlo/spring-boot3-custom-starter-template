package edu.mayo.lpea.cad.cadence3.model.dto;

import edu.mayo.lpea.cad.cadence3.model.entity.ExampleJpaUser;
import edu.mayo.lpea.cad.cadence3.web.model.AppUserDto;
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
public class ExampleJpaUserDto extends AppUserDto {
	private String customUserField;
	public ExampleJpaUser convertToExampleUser() throws InvocationTargetException, IllegalAccessException {
		ExampleJpaUser exampleJpaUser = new ExampleJpaUser();
		BeanUtils.copyProperties(exampleJpaUser, this);
		return exampleJpaUser;
	}
}
