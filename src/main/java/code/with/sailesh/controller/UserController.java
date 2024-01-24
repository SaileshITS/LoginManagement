package code.with.sailesh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import code.with.sailesh.requestdto.LoginCredential;
import code.with.sailesh.requestdto.MPinSetCrediential;
import code.with.sailesh.requestdto.PasswordChangeDto;
import code.with.sailesh.requestdto.UserRequestDto;
import code.with.sailesh.responsedto.CommonResponse;
import code.with.sailesh.service.LoginService;

@RestController
public class UserController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/createuser")
	public CommonResponse createUser(@RequestBody UserRequestDto userRequestDto)
	{
		return loginService.createUser(userRequestDto);
	}
	
	@PostMapping("/setmpin")
	public CommonResponse setMPin(@RequestBody MPinSetCrediential mPinSetCrediential)
	{
		return loginService.setMPin(mPinSetCrediential);
	}
	
	@PostMapping("/login")
	public CommonResponse login(@RequestBody LoginCredential loginCredential)
	{
		return loginService.login(loginCredential);
	}
	
	@PostMapping("/changePassword")
	public CommonResponse changePassword(@RequestBody PasswordChangeDto passwordChangeDto)
	{
		return loginService.changePassword(passwordChangeDto);
	}
}
