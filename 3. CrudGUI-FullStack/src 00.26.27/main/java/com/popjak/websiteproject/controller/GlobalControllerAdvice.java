package com.popjak.websiteproject.controller;

import org.springframework.beans.propertyeditors.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalControllerAdvice {
    /*
    The initBinder method, annotated with @InitBinder, configures data binding in a web
    application. It uses a StringTrimmerEditor to automatically remove leading and trailing
    white spaces from input strings of the String class, ensuring data integrity and improving user experience.
     */

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
}
