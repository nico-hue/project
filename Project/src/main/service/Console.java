package main.service;

import main.entity.Client;
import main.entity.Account;
import main.entity.Card;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Console {

    private final ClientService clientService;
    private final AccountService accountService;
    private final CardService cardService;
    private final BufferedReader reader;

    public Console(ClientService clientService, AccountService accountService, CardService cardService) {
        this.clientService = clientService;
        this.accountService = accountService;
        this.cardService = cardService;
        InputStreamReader isr = new InputStreamReader(System.in);
        this.reader = new BufferedReader(isr);

    }

    public void start() throws IOException {
        printMessage();
        try {
            int action = readUserAction();
            switch (action) {
                case 1 -> executeGetAllClientsAction();
                case 2 -> executeGetClientByIdAction();
                case 3 -> executeGetClientByNameAction();
                case 4 -> executeUpdateClientAction();
                case 5 -> executeDeleteClientByIdAction();
                case 6 -> executeAddClientAction();
                case 7 -> executeGetAllAccountsAction();
                case 8 -> executeGetAccountByIdAction();
                case 9 -> executeGetAccountsByAmountAction();
                case 10 -> executeUpdateAccountAction();
                case 11 -> executeDeleteAccountByIdAction();
                case 12 -> executeAddAccountAction();
                case 13 -> executeGetAllCardsAction();
                case 14 -> executeGetCardByIdAction();
                case 15 -> executeGetCardByNumberAction();
                case 16 -> executeUpdateCardAction();
                case 17 -> executeDeleteCardByIdAction();
                case 18 -> executeAddCardAction();
                default -> throw new UnsupportedOperationException(action + " action is not supported");
            }
        } finally {
            reader.close();
        }

    }


    private void printMessage() {

        System.out.println("Enter wanted action:");
        System.out.println("\t1: get all clients");
        System.out.println("\t2: get client by id");
        System.out.println("\t3: get client by name");
        System.out.println("\t4: update client");
        System.out.println("\t5: delete client by id");
        System.out.println("\t6: add client");
        System.out.println("\t7: get all accounts");
        System.out.println("\t8: get account by id");
        System.out.println("\t9: get accounts by amount");
        System.out.println("\t10: update account");
        System.out.println("\t11: delete account by id");
        System.out.println("\t12: add account");
        System.out.println("\t13: get all cards");
        System.out.println("\t14: get card by id");
        System.out.println("\t15: get card by number");
        System.out.println("\t16: update card");
        System.out.println("\t17: delete card by id");
        System.out.println("\t18: add card");

    }

    private int readUserAction() {
        try {
            int action = Integer.parseInt(readUserInput());
            if (action < 1 || action > 18) {
                throw new IllegalArgumentException("Action must be within [1; 18]");
            }
            return action;
        } catch (NumberFormatException e) { // user entered some bs
            System.err.println("Enter valid number from 1 to 18");
        }
        throw new IllegalArgumentException();
    }

    public String readUserInput() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }


    private void executeGetAllClientsAction() {
        List<Client> clientsList = clientService.getAll();
        for (Client s : clientsList) {
            System.out.println(s);
        }

    }

    private void executeGetClientByIdAction() {
        System.out.println("Enter id: ");
        int id = Integer.parseInt(readUserInput());
        System.out.println(clientService.getById(id));
    }

    private void executeGetClientByNameAction() {
        System.out.println("Enter full name:");
        String fullName = readUserInput();
        System.out.println(clientService.getByName(fullName));
    }

    private void executeUpdateClientAction() {
        System.out.println("Enter client id:");
        int id = Integer.parseInt(readUserInput());
        System.out.println("Enter new full name:");
        String fullName = readUserInput();
        System.out.println("Enter new phone");
        String phone = readUserInput();

        Client client = new Client();
        client.setId(id);
        client.setFullName(fullName);
        client.setPhone(phone);

        clientService.update(client);
        System.out.println("Client with id: " + id + " was successfully updated");
    }

    private void executeDeleteClientByIdAction() {
        System.out.println("Enter id");
        int id = Integer.parseInt(readUserInput());
        clientService.deleteById(id);
        System.out.println("Client with id: " + id + " was successfully deleted");
    }

    private void executeAddClientAction() {
        System.out.println("Enter id:");
        int id = Integer.parseInt(readUserInput());
        System.out.println("Enter full name:");
        String fullName = readUserInput();
        System.out.println("Enter phone");
        String phone = readUserInput();

        Client client = new Client();
        client.setId(id);
        client.setFullName(fullName);
        client.setPhone(phone);

        clientService.add(client);
        System.out.println("New client was successfully added");
    }


    private void executeGetAllAccountsAction() {

        List<Account> accountsList = accountService.getAll();
        for (Account o : accountsList) {
            System.out.println(o);
        }
    }

    private void executeGetAccountByIdAction() {
        System.out.println("Enter id:");
        int accountId = Integer.parseInt(readUserInput());
        System.out.println(accountService.getById(accountId));
    }

    private void executeGetAccountsByAmountAction() {

        List<Account> accountsList = accountService.getByAmount();
        for (Account s : accountsList) {
            System.out.println(s);
        }

    }

    private void executeUpdateAccountAction() {
        System.out.println("Enter account id:");
        int accountId = Integer.parseInt(readUserInput());
        System.out.println("Enter client id:");
        int id = Integer.parseInt(readUserInput());
        System.out.println("Enter card id:");
        int cardId = Integer.parseInt(readUserInput());
        System.out.println("Enter amount:");
        int amount = Integer.parseInt(readUserInput());

        Account account = new Account();
        account.setAccountId(accountId);
        account.setId(id);
        account.setCardId(cardId);
        account.setAmount(amount);

        accountService.update(account);
        System.out.println("Account with id: " + accountId + " was successfully updated");
    }

    private void executeDeleteAccountByIdAction() {
        System.out.println("Enter id");
        int accountId = Integer.parseInt(readUserInput());
        accountService.deleteById(accountId);
        System.out.println("Account with id: " + accountId + " was successfully deleted");
    }

    private void executeAddAccountAction() {
        System.out.println("Enter account id:");
        int accountId = Integer.parseInt(readUserInput());
        System.out.println("Enter client id:");
        int id = Integer.parseInt(readUserInput());
        System.out.println("Enter card id:");
        int cardId = Integer.parseInt(readUserInput());
        System.out.println("Enter amount:");
        int amount = Integer.parseInt(readUserInput());

        Account account = new Account();
        account.setAccountId(accountId);
        account.setId(id);
        account.setCardId(cardId);
        account.setAmount(amount);


        accountService.add(account);
        System.out.println("Account with id: " + accountId + " was successfully added");
    }


    private void executeGetAllCardsAction() {

        List<Card> cardsList = cardService.getAll();
        for (Card p : cardsList) {
            System.out.println(p);
        }
    }

    private void executeGetCardByIdAction() {
        System.out.println("Enter id:");
        int cardId = Integer.parseInt(readUserInput());
        System.out.println(cardService.getById((cardId)));
    }

    private void executeGetCardByNumberAction() {

        System.out.println("Enter number:");
        String number = readUserInput();
        System.out.println(cardService.getByNumber(number));
    }

    private void executeUpdateCardAction() {
        System.out.println("Enter card id:");
        int cardId = Integer.parseInt(readUserInput());
        System.out.println("Enter new number of card:");
        String number = readUserInput();
        System.out.println("Enter new balance:");
        double balance = Double.parseDouble(readUserInput());

        Card card = new Card();
        card.setCardId(cardId);
        card.setNumber(number);
        card.setBalance(balance);

        cardService.update(card);
        System.out.println("Card with id: " + cardId + " was successfully updated");
    }

    private void executeDeleteCardByIdAction() {
        System.out.println("Enter id");
        int cardId = Integer.parseInt(readUserInput());
        cardService.deleteById(cardId);
        System.out.println("Card with id: " + cardId + " was successfully deleted");
    }

    private void executeAddCardAction() {
        System.out.println("Enter card id:");
        int cardId = Integer.parseInt(readUserInput());
        System.out.println("Enter new number of card:");
        String number = readUserInput();
        System.out.println("Enter new balance:");
        double balance = Double.parseDouble(readUserInput());

        Card card = new Card();
        card.setCardId(cardId);
        card.setNumber(number);
        card.setBalance(balance);

        cardService.add(card);
        System.out.println("Card with id: " + cardId + " was successfully added");
    }


}
