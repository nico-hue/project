package main.dao.impl;

import main.connection.ConnectionFactory;
import main.dao.ClientDAO;
import main.entity.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClientDAOImpl implements ClientDAO {


    public ConnectionFactory factory;

    public ClientDAOImpl(ConnectionFactory factory) {
        this.factory = factory;
    }


    @Override
    public Client getByName(String fullName) {
        String sql = "SELECT `id`, `fullName`, `phone` FROM `client` WHERE `fullName`= ?";

        try (Connection con = factory.connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            preparedStatement.setString(1, fullName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setFullName(resultSet.getString("fullName"));
                client.setPhone(resultSet.getString("phone"));
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> getAll() {

        List<Client> clientsList = new ArrayList<>();
        String sql = "SELECT id, fullName, phone FROM client";

        try (Connection con = factory.connect();
             Statement statement = con.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setFullName(resultSet.getString("fullName"));
                client.setPhone(resultSet.getString("phone"));
                clientsList.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientsList;
    }

    @Override
    public Client getById(int id) {

        String sql = "SELECT id, fullName, phone FROM client WHERE id=?";


        try (Connection con = factory.connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setFullName(resultSet.getString("fullName"));
                client.setPhone(resultSet.getString("phone"));
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Client client) {

        String sql = "UPDATE client SET fullName = ?, phone = ? WHERE id = ?";

        try (Connection con = factory.connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            preparedStatement.setInt(1, client.getId());
            preparedStatement.setString(2, client.getFullName());
            preparedStatement.setString(3, client.getPhone());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {

        String sql = "DELETE FROM Client WHERE ID=?";

        try (Connection con = factory.connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void add(Client client) {

        String sql = "INSERT INTO Client (id, fullName, phone) VALUES(?, ?, ?)";

        try (Connection con = factory.connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {


            preparedStatement.setInt(1, client.getId());
            preparedStatement.setString(2, client.getFullName());
            preparedStatement.setString(3, client.getPhone());


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}