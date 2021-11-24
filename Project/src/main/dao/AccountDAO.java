package main.dao;

import main.entity.Account;
import java.util.List;

public interface AccountDAO extends DAO<Account> {

    List<Account> getByAmount();
}
