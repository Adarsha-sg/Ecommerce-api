package com.jsp.ecom.mapper;

import com.jsp.ecom.dto.CustomerDto;
import com.jsp.ecom.dto.MerchantDto;
import com.jsp.ecom.dto.UserDto;
import com.jsp.ecom.entity.Customer;
import com.jsp.ecom.entity.Merchant;
import com.jsp.ecom.entity.User;

import java.util.List;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-12T20:11:22+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250526-2018, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setName( user.getUsername() );
        userDto.setEmail( user.getEmail() );
        userDto.setMobile( user.getMobile() );
        if ( user.getRole() != null ) {
            userDto.setRole( user.getRole().name() );
        }

        return userDto;
    }

    @Override
    public User toUserEntity(MerchantDto merchantDto) {
        if ( merchantDto == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( merchantDto.getName() );
        user.setEmail( merchantDto.getEmail() );
        user.setMobile( merchantDto.getMobile() );

        user.setRole( com.jsp.ecom.enums.UserRole.MERCHANT );
        user.setActive( true );
        user.setPassword( passwordEncoder.encode(merchantDto.getPassword()) );

        return user;
    }

    @Override
    public Merchant toMerchantEntity(MerchantDto merchantDto, User user) {
        if ( merchantDto == null && user == null ) {
            return null;
        }

        Merchant merchant = new Merchant();

        if ( merchantDto != null ) {
            merchant.setAddress( merchantDto.getAddress() );
            merchant.setGstNo( merchantDto.getGstNo() );
            merchant.setName( merchantDto.getName() );
        }
        merchant.setUser( user );

        return merchant;
    }

    @Override
    public MerchantDto toMerchantDto(Merchant merchant) {
        if ( merchant == null ) {
            return null;
        }

        MerchantDto merchantDto = new MerchantDto();

        merchantDto.setAddress( merchant.getAddress() );
        merchantDto.setGstNo( merchant.getGstNo() );
        merchantDto.setName( merchant.getName() );

        merchantDto.setPassword( "**********" );
        merchantDto.setEmail( merchant.getUser().getEmail() );
        merchantDto.setMobile( merchant.getUser().getMobile() );

        return merchantDto;
    }

	@Override
	public User toUserEntity(CustomerDto customerDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer toCustomerEntity(CustomerDto customerDto, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerDto toCustomerDto(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MerchantDto> toMerchantDtoList(List<Merchant> merchants) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerDto> toCustomerDtoList(List<Customer> customers) {
		// TODO Auto-generated method stub
		return null;
	}
}
