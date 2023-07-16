package it.guitarhub.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.guitarhub.beans.Address;
import it.guitarhub.beans.User;

public class UserDAO implements ImplementDAO<User> {
	public static final String TABLE_NAME = "Utente";
	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/guitarhub");
		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	@Override
	public Collection<User> doRetrieveAll(String order) throws SQLException {
		String sql = "SELECT u.*, i.codice AS ic, i.nomeInd, i.cognomeInd, i.indirizzo, i.codPostale, i.city, i.provincia, i.telefono, i.preferito "
				   + " FROM " +  TABLE_NAME + " AS u LEFT JOIN Indirizzo AS i ON u.id = i.idUser ORDER BY ?";
		order = order.isEmpty() ? "id" : order;
		var users = new ArrayList<User>();
		User bean = null;
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, order);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
					do {
						bean = new User();
						bean.setId(rs.getInt("id"));
						bean.setFirstName(rs.getString("nome"));
						bean.setLastName(rs.getString("cognome"));
						bean.setGender(rs.getString("sesso"));
						bean.setBirthday(rs.getDate("dataNascita").toLocalDate());
						bean.setEmail(rs.getString("email"));
						bean.setPassword(rs.getString("passwd"));
						bean.setUsername(rs.getString("username"));
						bean.setRole(rs.getString("ruolo"));
						if(rs.getInt("ic") != 0) {
							do {
								Address addr = new Address();
								addr.setId(rs.getInt("ic"));
								addr.setFirstName(rs.getString("nomeInd"));
								addr.setLastName(rs.getString("cognomeInd"));
								addr.setAddress(rs.getString("indirizzo"));
								addr.setPostalCode(rs.getString("codPostale"));
								addr.setCity(rs.getString("city"));
								addr.setProvince(rs.getString("provincia"));
								addr.setPhone(rs.getString("telefono"));
								//addr.setPreferred(rs.getBoolean("preferito"));
								//bean.addAddress(addr);
							} while(rs.next() && bean.getId() == rs.getInt("id"));
						} else {
							rs.next();
						}
						users.add(bean);
					} while (!rs.isAfterLast());
				}
			}
		}
		return users;
	}

	@Override
	public User doRetriveByKey(int code) throws SQLException {
		String sql = "SELECT u.*, i.codice AS ic, i.nomeInd, i.cognomeInd, i.indirizzo, i.codPostale, i.city, i.provincia, i.telefono, i.preferito "
				+ " FROM " +  TABLE_NAME + " AS u LEFT JOIN Indirizzo AS i ON u.id = i.idUser WHERE u.id = ?";
		User bean = new User();
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, code);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					bean.setId(rs.getInt("id"));
					bean.setFirstName(rs.getString("nome"));
					bean.setLastName(rs.getString("cognome"));
					bean.setGender(rs.getString("sesso"));
					bean.setBirthday(rs.getDate("dataNascita").toLocalDate());
					bean.setEmail(rs.getString("email"));
					bean.setPassword(rs.getString("passwd"));
					bean.setUsername(rs.getString("username"));
					bean.setRole(rs.getString("ruolo"));
				}
				if(rs.getInt("ic") != 0) {
					do {
						Address addr = new Address();
						addr.setId(rs.getInt("ic"));
						addr.setFirstName(rs.getString("nomeInd"));
						addr.setLastName(rs.getString("cognomeInd"));
						addr.setAddress(rs.getString("indirizzo"));
						addr.setPostalCode(rs.getString("codPostale"));
						addr.setCity(rs.getString("city"));
						addr.setProvince(rs.getString("provincia"));
						addr.setPhone(rs.getString("telefono"));
						//addr.setPreferred(rs.getBoolean("preferito"));
						//bean.addAddress(addr);
					} while (rs.next());
				}
			}
		}
		return bean;
	}

	@Override
	public void doSave(User dao) throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME + " (username,passwd,nome,cognome,dataNascita,sesso,email,ruolo)" 
	              +  " VALUES (?,?,?,?,?,?,?,?)";
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, dao.getUsername());
				stmt.setString(2, dao.getPassword());
				stmt.setString(3, dao.getFirstName());
				stmt.setString(4, dao.getLastName());
				stmt.setDate(5, java.sql.Date.valueOf(dao.getBirthday()));
				stmt.setString(6, dao.getGender());
				stmt.setString(7, dao.getEmail());
				stmt.setString(8, dao.getRole());
				stmt.execute();
			}
		}
	}

	@Override
	public int doUpdate(User dao) throws SQLException {
		return 0;
	}

	@Override
	public boolean doDelete(int code) throws SQLException {
		return false;
	}

	public User doRetriveByEmail(String email) throws SQLException {
		String sql = "SELECT u.*, i.codice AS ic, i.nomeInd, i.cognomeInd, i.indirizzo, i.codPostale, i.city, i.provincia, i.telefono, i.preferito "
				   + " FROM " +  TABLE_NAME + " AS u LEFT JOIN Indirizzo AS i ON u.id = i.idUser WHERE u.email = ?";
		User bean = null;
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, email);
				System.out.println(stmt);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					bean = new User();
					bean.setId(rs.getInt("id"));
					bean.setFirstName(rs.getString("nome"));
					bean.setLastName(rs.getString("cognome"));
					bean.setGender(rs.getString("sesso"));
					bean.setBirthday(rs.getDate("dataNascita").toLocalDate());
					bean.setEmail(rs.getString("email"));
					bean.setPassword(rs.getString("passwd"));
					bean.setUsername(rs.getString("username"));
					bean.setRole(rs.getString("ruolo"));
					if(rs.getInt("ic") != 0) {
						do {
							Address addr = new Address();
							addr.setId(rs.getInt("ic"));
							addr.setFirstName(rs.getString("nomeInd"));
							addr.setLastName(rs.getString("cognomeInd"));
							addr.setAddress(rs.getString("indirizzo"));
							addr.setPostalCode(rs.getString("codPostale"));
							addr.setCity(rs.getString("city"));
							addr.setProvince(rs.getString("provincia"));
							addr.setPhone(rs.getString("telefono"));
							//addr.setPreferred(rs.getBoolean("preferito"));
							//bean.addAddress(addr);
						} while (rs.next());
					}
				}
				
			}
		}
		return bean;
	}
	
	public User doRetrieveByEmailLogin(String email) throws SQLException {
		String sql = "SELECT u.*" + " FROM " +  TABLE_NAME + " AS u WHERE u.email = ?";
		User bean = null;
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, email);
				System.out.println(stmt);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					bean = new User();
					bean.setId(rs.getInt("id"));
					bean.setEmail(rs.getString("email"));
					bean.setPassword(rs.getString("passwd"));
					bean.setUsername(rs.getString("username"));
					bean.setRole(rs.getString("ruolo"));
				}
				
			}
		}
		return bean;
	}

	public User doRetrieveByUsername(String username) throws SQLException {
		String sql = "SELECT u.*" + " FROM " +  TABLE_NAME + " AS u WHERE u.username = ?";
		User bean = null;
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, username);
				System.out.println(stmt);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					bean = new User();
					bean.setId(rs.getInt("id"));
					bean.setEmail(rs.getString("email"));
					bean.setPassword(rs.getString("passwd"));
					bean.setUsername(rs.getString("username"));
					bean.setRole(rs.getString("ruolo"));
				}
				
			}
		}
		return bean;
	}
	
	public void doSaveReg(User dao) throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME + " (username,passwd,email,ruolo)" 
	              +  " VALUES (?,?,?,?)";
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, dao.getUsername());
				stmt.setString(2, dao.getPassword());
				stmt.setString(3, dao.getEmail());
				stmt.setString(4, dao.getRole());
				stmt.execute();
			}
		}
	}
	
	public void doSaveCompleted(User dao) throws SQLException {
		String sql = "UPDATE " + TABLE_NAME + " As u SET u.username = ?,u.email = ?,u.ruolo= ?, u.nome = ?, u.cognome = ?,u.dataNascita = ?,u.sesso = ?,u.cardNumber = ?,u.cardExpDate = ?,u.cvv = ?,u.billingAddress = ? WHERE u.id = ?;";
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, dao.getUsername());
				stmt.setString(2, dao.getEmail());
				stmt.setString(3, dao.getRole());
				stmt.setString(4, dao.getFirstName());
				stmt.setString(5, dao.getLastName());
				stmt.setDate(6, java.sql.Date.valueOf(dao.getBirthday()));
				stmt.setString(7, dao.getGender());
				stmt.setString(8, dao.getCardNumber());
				stmt.setDate(9, java.sql.Date.valueOf(dao.getCardExpDate()));
				stmt.setString(10, dao.getCvv());
				stmt.setString(11, dao.getBillingAddress());
				stmt.setInt(12, dao.getId());
				stmt.execute();
			}
		}
	}
	
	public void doSaveAddress(User user, Address address) throws SQLException {
		String sql = "INSERT INTO Indirizzo "
				   + " (idUser,nomeInd,cognomeInd,indirizzo,numeroCivico,codPostale,city,provincia,telefono) "
				   + " VALUES (?,?,?,?,?,?,?,?,?)";
		
		
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, user.getId());
				stmt.setString(2, address.getFirstName());
				stmt.setString(3, address.getLastName());
				stmt.setString(4, address.getAddress());
				stmt.setString(5, address.getCivicNumber());
				stmt.setString(6, address.getPostalCode());
				stmt.setString(7, address.getCity());
				stmt.setString(8, address.getProvince());
				stmt.setString(9, address.getPhone());
				System.out.println(stmt);
				stmt.execute();
			}
		}
	}

}

