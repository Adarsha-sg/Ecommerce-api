package com.jsp.ecom.Service;

import java.security.SecureRandom;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsp.ecom.dao.UserDao;
import com.jsp.ecom.dto.MerchantDto;
import com.jsp.ecom.dto.OtpDto;
import com.jsp.ecom.entity.Merchant;
import com.jsp.ecom.entity.User;
import com.jsp.ecom.enums.UserRole;
import com.jsp.ecom.security.JwtService;
import com.jsp.ecom.util.EmailService;
import com.jsp.ecom.util.RedisService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;
	private final UserDao userDao;
	private final PasswordEncoder passwordEncoder;
	private final EmailService emailService;
	private final RedisService redisService;

	@Override
	public Map<String, Object> login(String email, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		UserDetails userDetails = userDetailsService.loadUserByUsername(email);
		String token = jwtService.generateToken(userDetails);
		return Map.of("message", "Login Success", "token", token);
	}

	@Override
	public Map<String, Object> viewUser(String email) {
		User user = userDao.findByEmail(email);
		return Map.of("message", "Data Found", "user", user);
	}

	@Override
	public Map<String, Object> updatePassword(String email, String oldPassword, String newPassword) {
		User user = userDao.findByEmail(email);
		if (passwordEncoder.matches(oldPassword, user.getPassword())) {
			user.setPassword(passwordEncoder.encode(newPassword));
			userDao.save(user);
			return Map.of("message", "Password Updated Success", "user", user);
		}
		throw new IllegalArgumentException("Old Password Not Matching");
	}

	private Integer generateOtp() {
		return new SecureRandom().nextInt(100000, 1000000);
	}

	@Override
	public Map<String, Object> registerMerchant(MerchantDto merchantDto) {
		if (userDao.checkEmailAndMobieDuplicate(merchantDto.getEmail(), merchantDto.getMobile()))
			throw new IllegalArgumentException("Already Account Exists with Email or Mobile");
		MerchantDto tempData = redisService.getTempMerchantData(merchantDto.getEmail());
		if (tempData != null)
			throw new IllegalArgumentException("Already Otp Sent First Verify It or After 30 minutes Try Again");
		Integer otp = generateOtp();
		emailService.sendOtpEmail(otp, merchantDto.getName(), merchantDto.getEmail());
		redisService.saveOtp(otp, merchantDto.getEmail());
		redisService.saveTempMerchantData(merchantDto, merchantDto.getEmail());
		return Map.of("message", "Otp Sent Succes Verify within 5 minutes");
	}

	@Override
	public Map<String, Object> verifyMerchantOtp(OtpDto dto) {
		Integer storedOtp = redisService.getOtp(dto.getEmail());
		MerchantDto merchantDto = redisService.getTempMerchantData(dto.getEmail());
		if (merchantDto == null)
			throw new IllegalArgumentException("No Account Exists recreate account");
		if (storedOtp == null)
			throw new IllegalArgumentException("Otp Expired, Try Resending");
		if (storedOtp.equals(dto.getOtp())) {
			if (userDao.checkEmailAndMobieDuplicate(merchantDto.getEmail(), merchantDto.getMobile()))
				throw new IllegalArgumentException("Already Account Exists with Email or Mobile");
			
			User user = new User(null, merchantDto.getName(), merchantDto.getEmail(), merchantDto.getMobile(),
					passwordEncoder.encode(merchantDto.getPassword()), UserRole.MERCHANT, true);
			userDao.save(user);
			Merchant merchant = new Merchant(null, merchantDto.getName(), merchantDto.getAddress(),
					merchantDto.getGstNo(), user);
			userDao.save(merchant);
			return Map.of("message", "Account Created Success", "user", merchant);
		} else {
			throw new IllegalArgumentException("Otp Missmatch Try Again");
		}

	}

	@Override
	public Map<String, Object> resendMerchantOtp(String email) {
		MerchantDto merchantDto = redisService.getTempMerchantData(email);
		if (merchantDto == null)
			throw new IllegalArgumentException("No Account Exists recreate account");
		if (userDao.checkEmailAndMobieDuplicate(merchantDto.getEmail(), merchantDto.getMobile()))
			throw new IllegalArgumentException("Already Account Exists with Email or Mobile");
		int otp = generateOtp();
		emailService.sendOtpEmail(otp, merchantDto.getName(), merchantDto.getEmail());
		redisService.saveOtp(otp, merchantDto.getEmail());
		return Map.of("message", "OTP Resent Success");
	}
}
