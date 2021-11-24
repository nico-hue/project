package main.dao;

import main.entity.Card;
import java.util.List;

public interface CardDAO extends DAO<Card> {

    Card getByNumber(String number);
}