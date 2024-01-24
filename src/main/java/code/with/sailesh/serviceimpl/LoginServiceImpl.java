package code.with.sailesh.serviceimpl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code.with.sailesh.dao.LoginHistoryRepo;
import code.with.sailesh.dao.MPinDetailsRepo;
import code.with.sailesh.dao.PasswordHistoryRepo;
import code.with.sailesh.dao.UserRepo;
import code.with.sailesh.entity.LoginHistory;
import code.with.sailesh.entity.MPinDetails;
import code.with.sailesh.entity.PasswordHistory;
import code.with.sailesh.entity.User;
import code.with.sailesh.requestdto.LoginCredential;
import code.with.sailesh.requestdto.MPinSetCrediential;
import code.with.sailesh.requestdto.PasswordChangeDto;
import code.with.sailesh.requestdto.UserRequestDto;
import code.with.sailesh.responsedto.CommonResponse;
import code.with.sailesh.service.LoginService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private MPinDetailsRepo mPinDetailsRepo;

	@Autowired
	private PasswordHistoryRepo passwordHistoryRepo;

	@Autowired
	private LoginHistoryRepo loginHistoryRepo;

	@Override
	public CommonResponse createUser(UserRequestDto userRequestDto) {
		log.info("Inside LoginServiceImpl class :  Inside create User Method : Stared");
		User user = new User();
		BeanUtils.copyProperties(userRequestDto, user);
		user.setIsActive(true);
		userRepo.save(user);
		log.info("Inside LoginServiceImpl class :  Inside create User Method : Completed");
		return CommonResponse.builder().data("Sucessfully Register User : " + userRequestDto.getEmail()).build();
	}

	@Override
	public CommonResponse setMPin(MPinSetCrediential mPinCrediential) {
		try {
			User user = userRepo.findByEmail(mPinCrediential.getEmail());
			if (user != null && mPinCrediential.getmPin().equals(mPinCrediential.getCnfMPin())) {
				MPinDetails mPinDetails = new MPinDetails();
				mPinDetails.setmPin(mPinCrediential.getmPin());
				mPinDetails.setUserId(user);
				mPinDetailsRepo.save(mPinDetails);
				return CommonResponse.builder().data(mPinCrediential.getEmail()).build();

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return CommonResponse.builder().statusCode(404).msg("User not Found").data(null).build();
	}

	@Override
	public CommonResponse changePassword(PasswordChangeDto passwordChangeDto) {
		try {
			User user = userRepo.findByEmail(passwordChangeDto.getEmail());
			if (user != null && user.getPassword().equals(passwordChangeDto.getPassword())) {
				user.setPassword(passwordChangeDto.getChangePassword());
				PasswordHistory passwordHistory = new PasswordHistory();
				passwordHistory.setPassword(passwordChangeDto.getPassword());
				passwordHistory.setUser(user);
				passwordHistory.setChangeDate(new Date());
				passwordHistoryRepo.save(passwordHistory);
				userRepo.save(user);
				return CommonResponse.builder().data("Successfully Change the Password" + user.getEmail()).build();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return CommonResponse.builder().statusCode(404).msg("User Not Found ").data(null).build();
	}

	@Override
	public CommonResponse login(LoginCredential credential) {
		try {
			User user = userRepo.findByEmail(credential.getEmail());
			LoginHistory byDeviceId = loginHistoryRepo.findByDeviceId(credential.getDeviceId());
			if (user != null && byDeviceId != null && credential.getMPin() != null) {
				loginWithMPin(credential, user);
				userRepo.save(user);
				return CommonResponse.builder().data(user.getEmail() + " This User Existing Login Now").build();
			} else if (user.getPassword().equals(credential.getPassword())) {
				newLogin(credential);
				userRepo.save(user);
				return CommonResponse.builder().data(user.getEmail() + " This User New  Login Now").build();
			} else {
				return CommonResponse.builder().statusCode(404).msg("Password and Mpin is Null").data(null).build();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return CommonResponse.builder().statusCode(404).msg("User Not Found").data(null).build();
	}

	private void loginWithMPin(LoginCredential loginCredential,User user)
		{
			try {
				MPinDetails byUserId = mPinDetailsRepo.findByUserId(user);
				if(byUserId.getmPin().equals(loginCredential.getMPin()))
						{
					LoginHistory lg = new LoginHistory();
					lg.setActiveLogin(true);
					lg.setDeviceId(loginCredential.getDeviceId());
					lg.setDeviceType(loginCredential.getDeviceType());
					lg.setLoginDate(new Date());
					loginHistoryRepo.save(lg);
						}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}

	private void newLogin(LoginCredential loginCredential) {
		LoginHistory lg = new LoginHistory();
		lg.setActiveLogin(true);
		lg.setDeviceId(loginCredential.getDeviceId());
		lg.setDeviceType(loginCredential.getDeviceType());
		lg.setLoginDate(new Date());
		loginHistoryRepo.save(lg);
	}
}
