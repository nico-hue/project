package main.service;

import main.dao.ClientDAO;
import main.entity.Client;
import java.util.List;

public class ClientService {

    private final ClientDAO clientDAO;

    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public Client getByName(String fullName) {
        return clientDAO.getByName(fullName);
    }

    public List<Client> getAll() {
        return clientDAO.getAll();
    }

    public Client getById(int id) {
        return clientDAO.getById(id);
    }

    public void update(Client client) {
        clientDAO.update(client);
    }

    public void deleteById(int id) {
        clientDAO.deleteById(id);
    }

    public void add(Client client) {
        clientDAO.add(client);
    }
}
