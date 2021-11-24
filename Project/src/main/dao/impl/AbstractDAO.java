package main.dao.impl;

import main.connection.ConnectionFactory;
import main.dao.DAO;

abstract class AbstractDAO<T> implements DAO<T> {

    protected final ConnectionFactory factory;

    AbstractDAO(ConnectionFactory factory) {
        this.factory = factory;
    }
}
