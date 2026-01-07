
package com.jsp.ecom.Service;

import java.util.Map;

public interface AuthService {


	Map<String, Object> login(String email, String password);

	Map<String, Object> viewUser(String name);

	Map<String, Object> updatePassword(String email, String oldPassword, String newPassword);

}
