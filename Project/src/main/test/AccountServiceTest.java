package main.test;

import main.connection.ConnectionFactory;
import main.dao.AccountDAO;
import main.dao.impl.AccountDAOImpl;
import main.entity.Account;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import main.service.AccountService;
import java.util.List;


public class AccountServiceTest {

    private final ConnectionFactory factory = new ConnectionFactory();
    private final AccountDAO dao = new AccountDAOImpl(factory);
    private final AccountService accountService = new AccountService(dao);

    private static void assertAccount(Account account, int accountId, int id, int cardId, int amount) {
        Assert.assertEquals(accountId, account.getAccountId());
        Assert.assertEquals(id, account.getId());
        Assert.assertEquals(cardId, account.getAccountId());
        Assert.assertEquals(amount, account.getAmount());
    }

    @BeforeMethod
    public void setUp() {
        Account account = new Account();
        account.setAccountId(999);
        account.setId(99);
        account.setCardId(9999);
        account.setAmount(9);
        dao.add(account);
    }

    @AfterMethod
    public void tearDown() {
        accountService.deleteById(999);
    }

    @Test
    public void testGetByAmount() {

        Account account1 = new Account();
        account1.setAccountId(9991);
        account1.setId(991);
        account1.setCardId(99991);
        account1.setAmount(1);
        accountService.add(account1);

        List<Account> accountsList = accountService.getByAmount();

        Assert.assertFalse(accountsList.contains(account1));

        accountService.deleteById(9991);
    }

    @Test
    public void testGetAll() {

        List<Account> accountsList = accountService.getAll();
        int size = accountsList.size();

        Account account1 = new Account();
        account1.setAccountId(9991);
        account1.setId(991);
        account1.setCardId(99991);
        account1.setAmount(1);
        dao.add(account1);

        List<Account> accountsList1 = accountService.getAll();
        int delta = accountsList1.size() - size;

        Assert.assertEquals(1, delta);

        dao.deleteById(9991);
    }

    @Test
    public void testGetById() {
        assertAccount(accountService.getById(999), 999, 99, 9999, 9);
    }

    @Test
    public void testUpdate() {

        Account newAccount = new Account();
        newAccount.setAccountId(999);
        newAccount.setId(991);
        newAccount.setCardId(99991);
        newAccount.setAmount(1);
        assertAccount(accountService.getById(999), 999, 99, 9999, 9);
        accountService.update(newAccount);
        assertAccount(accountService.getById(999), 999, 991, 99991, 1);
    }

    @Test
    public void testDeleteById() {

        Account account1 = new Account();
        account1.setAccountId(9991);
        account1.setId(991);
        account1.setCardId(99991);
        account1.setAmount(1);
        accountService.add(account1);

        accountService.deleteById(9991);
        Account as = dao.getById(9991);

        Assert.assertNull(as);
    }

    @Test
    public void testAdd() {

        Account account1 = new Account();
        account1.setAccountId(9991);
        account1.setId(991);
        account1.setCardId(99991);
        account1.setAmount(1);
        accountService.add(account1);

        assertAccount(accountService.getById(9991), 9991, 991, 99991, 1);

        dao.deleteById(9991);
    }

}