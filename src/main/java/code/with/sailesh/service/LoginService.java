package code.with.sailesh.service;

import code.with.sailesh.requestdto.LoginCredential;
import code.with.sailesh.requestdto.MPinSetCrediential;
import code.with.sailesh.requestdto.PasswordChangeDto;
import code.with.sailesh.requestdto.UserRequestDto;
import code.with.sailesh.responsedto.CommonResponse;

public interface LoginService {
	
	CommonResponse 	createUser(UserRequestDto userRequestDto);
	CommonResponse login(LoginCredential credential);
	CommonResponse setMPin(MPinSetCrediential mPinCrediential);
	CommonResponse changePassword(PasswordChangeDto passwordChangeDto);

}
