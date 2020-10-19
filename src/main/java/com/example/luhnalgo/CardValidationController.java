package com.example.luhnalgo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CardValidationController {


    @RequestMapping(value = "/validate/{cardnumber}", method = RequestMethod.GET)
    public @ResponseBody
    ValidationResult isValidCardNumber(@PathVariable("cardnumber") String cardnumber) {
        CardValidationService service = new CardValidationService(cardnumber);
        boolean result = false;
        if(cardnumber==null || cardnumber=="null" || cardnumber.isEmpty()){
            return new ValidationResult(false, ValidationResult.CardType.UNKNOWN, cardnumber);
        }
        ValidationResult cardtypeResult;
        try {
            cardtypeResult = service.validateCardType();
            if (cardtypeResult.getCardValidity()=="valid" && service.isValidLuhn(cardnumber)) {
                result = true;
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e){
            return new ValidationResult(result, ValidationResult.CardType.UNKNOWN, cardnumber);
        }
        return new ValidationResult(result, cardtypeResult.getCardType(), cardnumber);

    }
}
