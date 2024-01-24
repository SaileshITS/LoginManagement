package code.with.sailesh.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginCredential {

	private String deviceId;
	private String deviceType;
	private String email;
	private String password;
	private Integer mPin;
}
