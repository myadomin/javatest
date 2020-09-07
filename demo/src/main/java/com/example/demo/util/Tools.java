package com.example.demo.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class Tools {
    public static String getValidateError (BindingResult bindingResult) {
        if(bindingResult.hasErrors() == false) {
            return null;
        }

        StringBuffer s = new StringBuffer("");
        for(FieldError error : bindingResult.getFieldErrors()){
            s.append(error.getDefaultMessage() + ',');
        }

        return s.toString();
    }
}
