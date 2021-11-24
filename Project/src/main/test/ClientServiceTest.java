package main.test;

import main.connection.ConnectionFactory;
import main.dao.ClientDAO;
import main.dao.impl.ClientDAOImpl;
import main.entity.Client;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import main.service.ClientService;
import java.util.List;


public class ClientServiceTest {

    private final ConnectionFactory factory = new ConnectionFactory();
    private final ClientDAO dao = new ClientDAOImpl(factory);
    private final ClientService clientService = new ClientService(dao);

    private static void assertClient(Client client, int id, String fullName, String phone) {
        Assert.assertEquals(id, client.getId());
        Assert.assertEquals(fullName, client.getFullName());
        Assert.assertEquals(phone, client.getPhone());
    }

    @BeforeMethod
    public void setUp() {
        Client client = new Client();
        client.setId(999);
        client.setFullName("Kristen Stewart");
        client.setPhone("9999");
        dao.add(client);
    }

    @AfterMethod
    public void tearDown() {
        dao.deleteById(666);
    }

    @Test
    public void testGetByName() {

        Client cs = clientService.getByName("Kristen Stewart");

        Assert.assertEquals(999, cs.getId());
        Assert.assertEquals("Kristen Stewart", cs.getFullName());
        Assert.assertEquals("9999", cs.getPhone());
    }

    @Test
    public void testGetAll() {

        List<Client> clientsList = clientService.getAll();
        int size = clientsList.size();

        Client client1 = new Client();
        client1.setId(888);
        client1.setFullName("Chris Prat");
        client1.setPhone("8888");
        dao.add(client1);

        List<Client> clientsList1 = clientService.getAll();
        int delta = clientsList1.size() - size;

        Assert.assertEquals(1, delta);

        dao.deleteById(888);
    }

    @Test
    public void testGetById() {

        assertClient(clientService.getById(999), 999, "Kristen Stewart", "9999");
    }

    @Test
    public void testUpdate() {

        Client newClient = new Client();
        newClient.setId(999);
        newClient.setFullName("Chris Prat");
        newClient.setPhone("6666");
        assertClient(dao.getById(999), 999, "Kristen Stewart", "9999");
        clientService.update(newClient);
        assertClient(dao.getById(999), 999, "Chris Prat", "6666");
    }

    @Test
    public void testDeleteById() {

        Client client1 = new Client();
        client1.setId(666);
        client1.setFullName("Vladimir Pupin");
        client1.setPhone("6666");
        dao.add(client1);

        clientService.deleteById(666);
        Client cs = dao.getById(666);

        Assert.assertNull(cs);
    }

    @Test
    public void testAdd() {
        Client client1 = new Client();
        client1.setId(777);
        client1.setFullName("Emma Stone");
        client1.setPhone("7777");
        clientService.add(client1);

        assertClient(dao.getById(777), 777, "Emma Stone", "7777");

        dao.deleteById(777);
    }

}
