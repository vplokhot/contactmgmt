package com.tutorialspoint;

import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;


@Controller
public class ContactController {
	

	ApplicationContext  context = new ClassPathXmlApplicationContext("Beans.xml");
	ContactJDBCTemplate  contactJDBCTemplate = (ContactJDBCTemplate)context.getBean("contactJDBCTemplate");
	  
	  
	
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	   public ModelAndView contact() {
	      return new ModelAndView("contact", "command", new Contact());
	   }
	   
	   @RequestMapping(value = "/addContact", method = RequestMethod.POST)
	   public String addContact(@ModelAttribute("SpringWeb")Contact contact, 
	   ModelMap model) {

		  contactJDBCTemplate.getGeo(contact);
		  contactJDBCTemplate.create(contact.getFirstName(), contact.getLastName(), contact.getAddress(), contact.getEmail(), contact.getLon(), contact.getLat()); 
		  	   
	      model.addAttribute("firstName", contact.getFirstName());
	      model.addAttribute("lastName", contact.getLastName());
	      model.addAttribute("address", contact.getAddress());
	      model.addAttribute("email", contact.getEmail());
	      model.addAttribute("lon", contact.getLon());
	      model.addAttribute("lat", contact.getLat());

	      return "result";
	   }
	   
	   
	   @RequestMapping(value="/")
	   public ModelAndView listContact(ModelAndView model) throws IOException{
		   List<Contact> contacts = contactJDBCTemplate.listContacts();
	       model.addObject("contacts", contacts);
	       model.setViewName("show_contacts");
	       return model;
	   }
	   	   
	   @RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
	   public ModelAndView deleteContact(HttpServletRequest request) {
	       Integer contactId = Integer.parseInt(request.getParameter("id"));
	       contactJDBCTemplate.delete(contactId);
	       return new ModelAndView("redirect:/");
	   }
	   
	   @RequestMapping(value = "/editContact", method = RequestMethod.GET)
	   public ModelAndView editContact(HttpServletRequest request) {
	       int contactId = Integer.parseInt(request.getParameter("id"));
	       Contact contact = contactJDBCTemplate.getContact(contactId);
	       ModelAndView model = new ModelAndView("edit_contact");
	       model.addObject("contact", contact);
	    
	       return model;
	   }
	   @RequestMapping(value = "/updateContact", method = RequestMethod.POST)
	   public ModelAndView saveContact(@ModelAttribute Contact contact) {
		   
		   
		   contactJDBCTemplate.getGeo(contact);

		   contactJDBCTemplate.update(contact.getId(), contact.getFirstName(), contact.getLastName(), contact.getAddress(), contact.getEmail(), contact.getLon(), contact.getLat());
	       return new ModelAndView("redirect:/");
	   }
	   

	   @RequestMapping(value = "/searchContact", method = RequestMethod.GET)
	   public String search(Model model) {
		
		Search search = new Search();
	    model.addAttribute(search);
		initModelList(model);
		
		return "search_contact";
   }
	   
	
	   @RequestMapping(value = "/processSearch", method = RequestMethod.POST)
	   public ModelAndView processSearch(@ModelAttribute Search search, ModelAndView model) {
		   List<Contact> contacts = new ArrayList<Contact>();
			switch (search.getField()) {
			case "nearest":
			  
			  List<Contact> allContacts = contactJDBCTemplate.listContacts();
			  for(Contact c : allContacts){
				  contactJDBCTemplate.getDistance(search, c);
			  }
		
			  Collections.sort(allContacts, new Contact());		  
			  
			  contacts.add(allContacts.get(0));
			   
		       model.addObject("contacts", contacts);
		       model.setViewName("show_contacts");
				break;
			default:
				contacts = contactJDBCTemplate.matchingContacts(search.getQuery(), search.getField());
				 model.addObject("contacts", contacts);
			       model.setViewName("show_contacts");
				break;
			}	
		   System.out.println(search.getField() + " is the field");
		   System.out.println("QUERY: " + search.getQuery());
	       return model;
	   }  
		   
		   
		private void initModelList(Model model) {
			List<String> fieldsList = new ArrayList<String>();
			fieldsList.add("firstName");
			fieldsList.add("lastName");
			fieldsList.add("email");
			fieldsList.add("nearest");
			model.addAttribute("fields", fieldsList);
		}

}
