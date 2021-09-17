package com.demo.sendgrid.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

@Service
public class EmailService {

	@Value("${app.sendgrid.templateId}")
	private String templateId;
	@Value("${app.sendgrid.key}")
	private String secretKey;
	@Value("${app.sendgrid.fromEmail}")
	private String fromEmail;
	@Autowired
	SendGrid sendGrid;
	public String sendEmail(String email) throws IOException  {
		Email from = new Email(fromEmail);
	    String subject = "Testing mail sending with sandgrid by Prashant Thakur";
	    Email to = new Email(email);
	    Content content = new Content("text/plain", "If you could see this mail then sendgrid is working with Spring boot");
	    Mail mail = new Mail(from, subject, to, content);
	    
	    SendGrid sg = new SendGrid(secretKey);
	    Request request = new Request();
	  
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	      return "Mail sent succesfully";
	  
		
	}
	
	public Mail prepareMail(String email) {
		
		Mail mail = new Mail();
		
		Email fromEmail = new Email();
		
		fromEmail.setEmail("fricletech@gmail.com");
		
		mail.setFrom(fromEmail);
		Email to = new Email();
		to.setEmail(email);
		
		
		Personalization personalization = new Personalization();
		
		personalization.addTo(to);
		mail.addPersonalization(personalization);
		
	//	mail.setTemplateId(templateId);
		
		return mail;
	}
}
