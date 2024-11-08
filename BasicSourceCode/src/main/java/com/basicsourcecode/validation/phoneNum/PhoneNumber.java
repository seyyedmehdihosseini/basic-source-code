package com.basicsourcecode.validation.phoneNum;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumberVldImpl.class)
@Target({ElementType.PARAMETER , ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/**
 * @Author mehdi_hosseini
 * <ul>
 * <li> Description: phone number {mobile number and home number or telephone number} </li>
 * </ul>
 * */
public @interface PhoneNumber {
    String message() default  "the entered phoneNumber is not valid.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    PhoneTypeVld phoneType() default PhoneTypeVld.MOBILE;
}
