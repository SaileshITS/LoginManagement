package code.with.sailesh.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

	private String name;
	private String department;
	private String email;
	private String mobile;
	private String password;
	
}
