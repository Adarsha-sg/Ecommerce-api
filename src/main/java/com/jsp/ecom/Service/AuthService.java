
package com.jsp.ecom.Service;

import java.util.Map;

import com.jsp.ecom.dto.MerchantDto;
import com.jsp.ecom.dto.OtpDto;

public interface AuthService {


	Map<String, Object> login(String email, String password);

	Map<String, Object> viewUser(String name);

	Map<String, Object> updatePassword(String email, String oldPassword, String newPassword);

	Map<String, Object> registerMerchant(MerchantDto merchantDto);

	Map<String, Object> verifyMerchantOtp(OtpDto dto);

	Map<String, Object> resendMerchantOtp(String email);

}
