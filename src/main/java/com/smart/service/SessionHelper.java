package com.smart.service;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component
public class SessionHelper {// This class is for taking object of session for removing
	// message from signup because in new verison of template is not possible
	// removing message with session object directly.
	
	public void removeMessageFromSession() {

		try {

			System.out.println("removing message form session ");

			//finding session object......
			HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
					.getSession();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
