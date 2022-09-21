package com.eazybytes.easyschool.controller;

import com.eazybytes.easyschool.model.Contact;
import com.eazybytes.easyschool.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ContactController {

    private static Logger log = LoggerFactory.getLogger(ContactController.class);

    private final ContactService contactService;
    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String displayContactPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

    //save message method using request param
//    @PostMapping("saveMsg")
//    public ModelAndView saveMessage(
//            @RequestParam String name,
//            @RequestParam(name="mobileNum") String mobileNumber,
//            @RequestParam String email,
//            @RequestParam String subject,
//            @RequestParam String message) {
//        log.info("Name :" + name);
//        log.info("Mobile Number :" + mobileNumber);
//        return new ModelAndView("redirect:/contact");
//    }

    @PostMapping("saveMsg")
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {
        if(errors.hasErrors()) {
            log.error("Contact form validation failed due to: " + errors.toString());
            return "contact.html";
        }
        contactService.saveMessageDetails(contact);
        contactService.setCounter(contactService.getCounter()+1);
        log.info("total number of times: " + contactService.getCounter());
        return "redirect:/contact";
    }
}
