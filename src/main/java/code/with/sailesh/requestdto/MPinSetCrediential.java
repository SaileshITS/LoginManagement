package code.with.sailesh.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MPinSetCrediential {

	private String email;
	private String password;
	private Integer mPin;
	private Integer cnfMPin;
	
	public Integer getmPin() {
		return mPin;
	}
	public void setmPin(Integer mPin) {
		this.mPin = mPin;
	}
	
	
}
