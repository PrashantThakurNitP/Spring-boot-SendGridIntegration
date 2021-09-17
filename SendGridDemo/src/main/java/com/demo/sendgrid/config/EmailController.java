package com.demo.sendgrid.config;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.sendgrid.service.EmailService;



@RestController
public class EmailController {

	@Autowired
	EmailService emailService;

	@GetMapping("/sendMail/{email}")
	public String sendEmail(@PathVariable(value = "email", required = true) String email) throws IOException
	{

	return	emailService.sendEmail(email);
	
	}
}
