package main.entity;

import java.util.Objects;

public class Account {

    private int accountId; // Primary key
    private int id; // Foreign key to Client
    private int cardId; // Foreign key to Card
    private int amount;


    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) { this.cardId = cardId; }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", id=" + id +
                ", cardId=" + cardId +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId == account.accountId &&
                id == account.id &&
                cardId == account.cardId &&
                amount == account.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, id, cardId, amount);
    }
}