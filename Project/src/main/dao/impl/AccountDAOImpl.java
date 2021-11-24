package main.dao.impl;

import main.connection.ConnectionFactory;
import main.entity.Account;
import main.dao.AccountDAO;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;


public class AccountDAOImpl implements AccountDAO {

    public ConnectionFactory factory;

    public AccountDAOImpl(ConnectionFactory factory) {
        this.factory = factory;
    }


    @Override
    public List<Account> getByAmount() {
        List<Account> accountsList = new ArrayList<>();
        String sql = "SELECT `accountId`, `id`, `cardId`, `amount` FROM `account` WHERE `amount` > 0";

        try (Connection con = factory.connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)){


            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Account account = new Account();

                account.setAccountId(resultSet.getInt("accountId"));
                account.setId(resultSet.getInt("id"));
                account.setCardId(resultSet.getInt("cardId"));
                account.setAmount(resultSet.getInt("amount"));
                accountsList.add(account);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountsList;
    }

    @Override
    public List<Account> getAll()  {

        List<Account> accountsList = new ArrayList<>();
        String sql = "SELECT `accountId`, `id`, `cardId`, `amount` FROM `account`";

        try (Connection con = factory.connect();
             Statement statement = con.createStatement()){

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Account account = new Account();
                account.setAccountId(resultSet.getInt("accountId"));
                account.setId(resultSet.getInt("id"));
                account.setCardId(resultSet.getInt("cardId"));
                account.setAmount(resultSet.getInt("amount"));
                accountsList.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountsList;
    }


    @Override
    public Account getById(int accountId)  {

        String sql = "SELECT `accountId`, `id`, `cardId`, `amount` FROM `account` WHERE `accountId` = ?";


        try (Connection con = factory.connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)){

            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Account account = new Account();
                account.setAccountId(resultSet.getInt("accountId"));
                account.setId(resultSet.getInt("id"));
                account.setCardId(resultSet.getInt("cardId"));
                account.setAmount(resultSet.getInt("amount"));
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void update(Account account)  {

        String sql = "UPDATE `account` SET `id`=?, `cardId`=?, `amount`=? WHERE `accountId`=?";

        try (Connection con = factory.connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)){

            preparedStatement.setInt(1,account.getAccountId());
            preparedStatement.setInt(2,account.getId());
            preparedStatement.setInt(3, account.getCardId());
            preparedStatement.setInt(4, account.getAmount());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteById(int accountId)  {

        String sql = "DELETE FROM `account` WHERE `accountId`=?";

        try (Connection con = factory.connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)){

            preparedStatement.setInt(1, accountId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void add(Account account)  {

        String sql = "INSERT INTO `account` (`accountId`, `id`, `cardId`, `amount`) VALUES(?, ?, ?, ?)";

        try (Connection con = factory.connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)){

            preparedStatement.setInt(1,account.getAccountId());
            preparedStatement.setInt(2,account.getId());
            preparedStatement.setInt(3, account.getCardId());
            preparedStatement.setInt(4, account.getAmount());


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}