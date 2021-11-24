package main.service;

import main.connection.ConnectionFactory;
import main.dao.ClientDAO;
import main.dao.AccountDAO;
import main.dao.CardDAO;
import main.dao.impl.ClientDAOImpl;
import main.dao.impl.AccountDAOImpl;
import main.dao.impl.CardDAOImpl;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

        // DAO layer
        ConnectionFactory factory = new ConnectionFactory();
        ClientDAO clientDAO = new ClientDAOImpl(factory);
        AccountDAO accountDAO = new AccountDAOImpl(factory);
        CardDAO cardDAO = new CardDAOImpl(factory);

        // Service layer
        ClientService clientService = new ClientService(clientDAO);
        AccountService accountService = new AccountService(accountDAO);
        CardService cardService = new CardService(cardDAO);

        Console con = new Console(clientService, accountService, cardService);
        con.start();
    }
}