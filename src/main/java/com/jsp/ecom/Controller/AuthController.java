
package com.jsp.ecom.Controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ecom.dto.CustomerDto;
import com.jsp.ecom.dto.LoginDto;
import com.jsp.ecom.dto.MerchantDto;
import com.jsp.ecom.dto.OtpDto;
import com.jsp.ecom.dto.PasswordDto;

import jakarta.validation.Valid;

import com.jsp.ecom.Service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public Map<String, Object> login(@Valid @RequestBody LoginDto loginDto) {
		return authService.login(loginDto.getEmail(),loginDto.getPassword());
	}
	
	
	@GetMapping("/me")
	@PreAuthorize("hasAnyRole('ADMIN','USER','MERCHANT')")
	public Map<String, Object> viewLoggedInUser(Principal principal) {
		return authService.viewUser(principal.getName());
	}

	
	@PatchMapping("/password")
	@PreAuthorize("hasAnyRole('ADMIN','USER','MERCHANT')")
	public Map<String, Object> updatePassword(Principal principal,@Valid @RequestBody PasswordDto passwordDto){
		return authService.updatePassword(principal.getName(),passwordDto.getOldPassword(),passwordDto.getNewPassword());
	}
	
	
	@PostMapping("/merchant/register")
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, Object> registerMerchantAccount(@Valid @RequestBody MerchantDto merchantDto) {
		return authService.registerMerchant(merchantDto);
	}
	
	
	@PatchMapping("/merchant/otp")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> verifyOtp(@Valid
			@RequestBody OtpDto dto){
		return authService.verifyMerchantOtp(dto);
	}
	
	@PatchMapping("/merchant/resend/{email}")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> resendOtp(@PathVariable String email){
		return authService.resendMerchantOtp(email);
	}
	
	
	@PostMapping("/customer/register")
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, Object> registerCustomerAccount(@Valid @RequestBody CustomerDto customerDto) {
		return authService.registerCustomer(customerDto);
	}
	
	
	@PatchMapping("/customer/otp")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> verifyCustomerOtp(@Valid
			@RequestBody OtpDto dto){
		return authService.verifyCustomerOtp(dto);
	}
	
	@PatchMapping("/customer/resend/{email}")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> resendCustomerOtp(@PathVariable String email){
		return authService.resendCustomerOtp(email);
	}
}
