package main.dao.impl;

import main.connection.ConnectionFactory;
import main.entity.Card;
import main.dao.CardDAO;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;


public class CardDAOImpl implements CardDAO {

    public ConnectionFactory factory;

    public CardDAOImpl(ConnectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Card getByNumber(String number) {

        String sql = "SELECT cardId, number, balance FROM Card WHERE number =?";

        try (Connection con = factory.connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)){

            preparedStatement.setString(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Card card = new Card();
                card.setCardId(resultSet.getInt("cardId"));
                card.setNumber(resultSet.getString("number"));
                card.setBalance(resultSet.getDouble("balance"));
                return card;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Card> getAll()  {

        List<Card> cardsList = new ArrayList<>();
        String sql = "SELECT cardId, number, balance FROM Card";

        try (Connection con = factory.connect();
             Statement statement = con.createStatement()){

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Card card = new Card();
                card.setCardId(resultSet.getInt("cardId"));
                card.setNumber(resultSet.getString("number"));
                card.setBalance(resultSet.getDouble("balance"));
                cardsList.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cardsList;
    }


    @Override
    public Card getById(int cardId)  {

        String sql = "SELECT cardId, number, balance FROM Card WHERE cardId=?";


        try (Connection con = factory.connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)){

            preparedStatement.setInt(1, cardId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Card card = new Card();
                card.setCardId(resultSet.getInt("cardId"));
                card.setNumber(resultSet.getString("number"));
                card.setBalance(resultSet.getDouble("balance"));
                return card;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public void update(Card card)  {

        String sql = "UPDATE `card` SET `number`=?, `balance`=? WHERE `cardId` =?";

        try (Connection con = factory.connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)){

            preparedStatement.setInt(1, card.getCardId());
            preparedStatement.setString(2,card.getNumber());
            preparedStatement.setDouble(3, card.getBalance());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteById(int cardId)  {

        String sql = "DELETE FROM Card WHERE cardId=?";

        try (Connection con = factory.connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)){

            preparedStatement.setInt(1, cardId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void add(Card card)  {

        String sql = "INSERT INTO Card (cardId, number, balance) VALUES(?, ?, ?)";

        try (Connection con = factory.connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)){

            preparedStatement.setInt(1, card.getCardId());
            preparedStatement.setString(2,card.getNumber());
            preparedStatement.setDouble(3, card.getBalance());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}