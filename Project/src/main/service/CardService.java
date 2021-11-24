package main.service;

import main.dao.CardDAO;
import main.entity.Card;
import java.util.List;

public class CardService {

    private final CardDAO cardDAO;

    public CardService(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    public Card getByNumber(String number) { return cardDAO.getByNumber(number);}

    public List<Card> getAll()  {
        return cardDAO.getAll();
    }

    public Card getById(int cardId)  {
        return cardDAO.getById(cardId);
    }

    public void update(Card card)  {cardDAO.update(card); }

    public void deleteById(int cardId)  { cardDAO.deleteById(cardId); }

    public void add(Card card) { cardDAO.add(card); }
}
