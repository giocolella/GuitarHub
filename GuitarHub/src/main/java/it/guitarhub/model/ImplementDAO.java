package it.guitarhub.model;

import java.sql.SQLException;
import java.util.Collection;

public interface ImplementDAO<T> {

	Collection<T> doRetrieveAll(String order) throws SQLException;

	T doRetriveByKey(int code) throws SQLException;

	void doSave(T dao) throws SQLException;

	boolean doDelete(int code) throws SQLException;

}
