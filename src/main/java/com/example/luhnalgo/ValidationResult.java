package com.example.luhnalgo;

public class ValidationResult {

    public  enum CardType {VISA, MASTERCARD, AMEX, DISCOVER, UNKNOWN}
    //private boolean isValid;
    private CardType cardType;
    private String cardNumber;
    private String cardValidity;

    public ValidationResult(boolean isValid, CardType cardType, String cardNumber) {
        //this.isValid = isValid;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        if(isValid){
            this.cardValidity="valid";
        }else{
            this.cardValidity="invalid";
        }
    }

    public String getCardValidity() {
        return cardValidity;
    }

    public CardType getCardType() {
        return cardType;
    }

    public String getCardNumber() { return cardNumber; }

    @Override
    public String toString() {
        return "ValidationResult{" +
                ", cardType=" + cardType +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardValidity='" + cardValidity + '\'' +
                '}';
    }
}
