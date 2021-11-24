package main.dao;

import main.entity.Client;

public interface ClientDAO extends DAO<Client> {

    Client getByName(String name);
}
