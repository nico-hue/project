package main.entity;

import java.util.Objects;

public class Card {

    private int cardId; // Primary key
    private String number;
    private double balance;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) { this.balance = balance; }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) { this.cardId = cardId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardId == card.cardId &&
                Double.compare(card.balance, balance) == 0 &&
                number.equals(card.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, number, balance);
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", number='" + number + '\'' +
                ", balance=" + balance +
                '}';
    }
}
