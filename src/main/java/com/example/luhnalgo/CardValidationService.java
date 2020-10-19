package com.example.luhnalgo;

public class CardValidationService {

    private String cardNumber;

    public CardValidationService(String cardNumber) {
        this.cardNumber = cardNumber;
    }


   public boolean isValidLuhn(String cardNumber) {

        return luhnCheck(cardNumber);

    }

    public static boolean luhnCheck(String cardNumber) throws NumberFormatException, StringIndexOutOfBoundsException
    {
        boolean isAlternate = false;
        boolean isValidCardNumber=false;
        int totalSum = 0;

        for (int i = cardNumber.length() - 1; i >= 0; i--)
        {
            int num = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (isAlternate)
            {
                num = num * 2;
                if (num > 9)
                {
                    num = (num % 10) + (num/10);
                }
            }
            totalSum += num;
            isAlternate = !isAlternate;
        }
        if(totalSum % 10 == 0){
            isValidCardNumber=true;
        }

        return isValidCardNumber;
    }

    public ValidationResult validateCardType() throws NumberFormatException, StringIndexOutOfBoundsException{

        if(cardNumber.startsWith("34") || cardNumber.startsWith("37")){
           if(cardNumber.length()==15){
               return  new ValidationResult(true, ValidationResult.CardType.AMEX, cardNumber);
           }else  return new ValidationResult(false, ValidationResult.CardType.AMEX, cardNumber);
       }
       else if(cardNumber.startsWith("6011")){
           if(cardNumber.length()==16){
               return  new ValidationResult(true, ValidationResult.CardType.DISCOVER, cardNumber);
           }else return  new ValidationResult(false, ValidationResult.CardType.DISCOVER, cardNumber);
       }
       else if(Integer.parseInt(cardNumber.substring(0,2)) >=51 && Integer.parseInt(cardNumber.substring(0,2)) <56){
           if(cardNumber.length()==16){
               return  new ValidationResult(true, ValidationResult.CardType.MASTERCARD, cardNumber);
           }else return  new ValidationResult(false, ValidationResult.CardType.MASTERCARD, cardNumber);
       }
       else if(cardNumber.startsWith("4")){
           if(cardNumber.length()==13 || cardNumber.length()==16){
               return  new ValidationResult(true, ValidationResult.CardType.VISA, cardNumber);
           }else return  new ValidationResult(false, ValidationResult.CardType.VISA, cardNumber);
       }
       else {
           return  new ValidationResult(false, ValidationResult.CardType.UNKNOWN, cardNumber);
       }

    }
}
