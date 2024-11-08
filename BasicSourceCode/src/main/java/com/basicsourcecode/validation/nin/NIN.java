package com.basicsourcecode.validation.nin;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NINVldImpl.class)
@Target({ElementType.PARAMETER , ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
/**
 * @Author mehdi_hosseini
 * <ul>
 * <li> Description: National Identification Number </li>
 * </ul>
 * */
public @interface NIN {
    String message() default  "interface national code validation";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    NIN_TypeVld NIN_type();

}
