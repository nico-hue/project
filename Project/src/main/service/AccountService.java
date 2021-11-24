package main.service;


import main.dao.AccountDAO;
import main.entity.Account;
import java.util.List;

public class AccountService  {

    private final AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public List<Account> getByAmount() {
        return accountDAO.getByAmount();
    }

    public List<Account> getAll()  {
        return accountDAO.getAll();
    }

    public Account getById(int accountId)  { return accountDAO.getById(accountId); }

    public void update(Account account)  {
        accountDAO.update(account);
    }

    public void deleteById(int accountId)  {
        accountDAO.deleteById(accountId);
    }

    public void add(Account account)  { accountDAO.add(account); }
}