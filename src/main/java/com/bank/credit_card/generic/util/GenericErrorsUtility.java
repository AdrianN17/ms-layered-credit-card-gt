package com.bank.credit_card.generic.util;

import com.bank.credit_card.exceptions.CustomBadRequest;
import com.bank.credit_card.exceptions.CustomNotFound;
import lombok.experimental.UtilityClass;
import org.springframework.validation.BindingResult;

@UtilityClass
public class GenericErrorsUtility {

    public static void thrownBadRequest (BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            throw new CustomBadRequest(bindingResult);
    }

    public static CustomBadRequest thrownBadRequest (String message)
    {
        return new CustomBadRequest(message);
    }

    public static CustomNotFound thrownNotFound (String message)
    {
        return new CustomNotFound(message);
    }
}
