package it.guitarhub.model;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.guitarhub.beans.*;

public class ProductDAO implements ImplementDAO<Product> {
	private static DataSource ds;
	private static final String TABLE_NAME = "Prodotto";

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
	public synchronized Collection<Product> doRetrieveAll(String order) throws SQLException {
		Collection<Product> products = new LinkedList<>();
		String selectSQL = "SELECT p.*, c.nome AS cnome, c.codice AS cid, c.descrizione AS cdescrizione " 
				+ " FROM ( " + TABLE_NAME + " AS p LEFT JOIN Ha AS h ON h.idProd = p.codice ) "
				+ "LEFT JOIN Categoria AS c ON h.idCat = c.codice";
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try(var connection = ds.getConnection()) {
			try (var preparedStatement = connection.prepareStatement(selectSQL)) {
				ResultSet rs = preparedStatement.executeQuery();
				if (!rs.next())
					return products;	
				do {
					Product bean = new Product();
					bean.setId(rs.getInt("codice"));
					bean.setName(rs.getString("nome"));
					bean.setDescription(rs.getString("descrizione"));
					bean.setTax(rs.getInt("iva"));
					bean.setPrice(rs.getDouble("prezzo"));
					bean.setBrand(rs.getString("marca"));
					bean.setWeight(rs.getDouble("peso"));
					bean.setAvailable(rs.getBoolean("disponibile"));
					bean.setDiscount(rs.getDouble("sconto"));
					bean.setQuantity(rs.getInt("quantity"));
					
					products.add(bean);				
				} while (!rs.isAfterLast());
			}
		}
		return products;
	}

	@Override
	public synchronized Product doRetriveByKey(int code) throws SQLException {
		Product bean = new Product();
		ReviewDAO rdao = new ReviewDAO();
		String selectSQL = "SELECT p.*" + " FROM " + TABLE_NAME + " AS p" + " WHERE p.codice=?";
		
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(selectSQL)) {
				stmt.setInt(1, code);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					bean.setId(rs.getInt("codice"));
					bean.setName(rs.getString("nome"));
					bean.setDescription(rs.getString("descrizione"));
					bean.setImagePath(rs.getString("immagine"));
					bean.setTax(rs.getInt("iva"));
					bean.setPrice(rs.getDouble("prezzo"));
					bean.setBrand(rs.getString("marca"));
					bean.setWeight(rs.getDouble("peso"));
					bean.setAvailable(rs.getBoolean("disponibile"));
					bean.setShortdescription(rs.getString("shortDescription"));
					bean.setDiscount(rs.getDouble("sconto"));
					bean.setQuantity(rs.getInt("quantity"));						
				}
			}
		}
		//bean.setReviewes(rdao.doRetrieveByProduct("codice", bean));
		return bean;
	}

	@Override
	public synchronized void doSave(Product dao) throws SQLException {
	    String inserSQL = "INSERT INTO " + TABLE_NAME + " (nome, descrizione,shortDescription,immagine, " + "prezzo, marca, peso, disponibile, " + "sconto, quantity, iva) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection conn = ds.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(inserSQL)) {

	        stmt.setString(1, dao.getName());
	        stmt.setString(2, dao.getDescription());
	        stmt.setString(3, dao.getShortdescription());
	        stmt.setString(4, dao.getImagePath());
	        stmt.setDouble(5, dao.getPrice());
	        stmt.setString(6, dao.getBrand());
	        stmt.setDouble(7, dao.getWeight());
	        stmt.setBoolean(8, dao.isAvailable());
	        stmt.setDouble(9, dao.getDiscount());
	        stmt.setInt(10, dao.getQuantity());
	        stmt.setInt(11, dao.getTax());
	        stmt.execute();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	@Override
	public synchronized int doUpdate(Product dao) throws SQLException {
		String updateSQL = "UPDATE Prodotto AS p  SET p.nome=?,p.descrizione=?,p.prezzo=?,p.marca=?,p.peso=?,"
				+ "p.disponibile = ?,p.sconto = ?,p.quantity = ?,p.iva = ? " 
				+ "WHERE p.codice = ? ";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		var result = 0;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);	
			stmt = conn.prepareStatement(updateSQL);
			stmt.setString(1, dao.getName());
			stmt.setString(2, dao.getDescription());
			stmt.setDouble(3, dao.getPrice());
			stmt.setString(4, dao.getBrand());
			stmt.setDouble(5, dao.getWeight());
			stmt.setBoolean(6, dao.isAvailable());
			stmt.setDouble(7, dao.getDiscount());
			stmt.setInt(8, dao.getQuantity());
			stmt.setInt(9, dao.getTax());
			stmt.setInt(10, dao.getId());		
			result = stmt.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
			result = 0;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} finally {
				if (conn != null)
					conn.close();
			}
		}
		return result;
	}

	@Override
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE codice = ?";
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(deleteSQL);
			stmt.setInt(1, code);

			result = stmt.executeUpdate();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} finally {
				if (conn != null)
					conn.close();
			}
		}
		return (result != 0);
	}

	public synchronized boolean doProductSearchByName(String value) throws SQLException{
		String selectSQL = "SELECT p.*" + " FROM " + TABLE_NAME + " AS p " + "WHERE p.nome = ?";
		boolean exists = false;
		try(var connection = ds.getConnection()) {
			try (var preparedStatement = connection.prepareStatement(selectSQL)) {
				preparedStatement.setString(1,value);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()) {
					exists = true;		
				}
			}
		}
		return exists;
	}
	
	public synchronized Collection<Product> doProductSearch(String value) throws SQLException{
		Collection<Product> products = new LinkedList<>();
		String selectSQL = "SELECT p.*" + " FROM " + TABLE_NAME + " AS p " + "WHERE p.nome LIKE ?";
		
		try(var connection = ds.getConnection()) {
			try (var preparedStatement = connection.prepareStatement(selectSQL)) {
				preparedStatement.setString(1, "%"+value+"%");
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()) {
					Product bean = new Product();
					bean.setId(rs.getInt("codice"));
					bean.setName(rs.getString("nome"));
					bean.setDescription(rs.getString("descrizione"));
					bean.setImagePath(rs.getString("immagine"));
					bean.setTax(rs.getInt("iva"));
					bean.setPrice(rs.getDouble("prezzo"));
					bean.setBrand(rs.getString("marca"));
					bean.setWeight(rs.getDouble("peso"));
					bean.setAvailable(rs.getBoolean("disponibile"));
					bean.setDiscount(rs.getDouble("sconto"));
					bean.setQuantity(rs.getInt("quantity"));
					products.add(bean);				
				}
			}
		}
		return products;
	}
	
	public synchronized void doUpdateQuantity(Product dao,int quantity) throws SQLException {
		String updateSQL = "UPDATE Prodotto AS p  SET p.quantity = ? " + " WHERE p.codice = ? ";
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ds.getConnection();	
			stmt = conn.prepareStatement(updateSQL);
			stmt.setInt(1,quantity - dao.getQuantity());
			stmt.setInt(2, dao.getId());		
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} finally {
				if (conn != null)
					conn.close();
			}
		}
		return;
	}
	
	public synchronized int doRetriveQuantity(int code) throws SQLException {
		String selectSQL = "SELECT p.quantity" + " FROM " + TABLE_NAME + " AS p" + " WHERE p.codice=?";
		int quantity = 0;
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(selectSQL)) {
				stmt.setInt(1, code);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					quantity = rs.getInt("quantity");						
				}
			}
		}
		return quantity;
	}
	
	public synchronized Collection<Product> doProductSearchByFilteredQuantity(String value) throws SQLException{
		Collection<Product> products = new LinkedList<>();
		String selectSQL = "SELECT p.* FROM " + TABLE_NAME + " AS p WHERE p.nome LIKE ? AND p.quantity > 0";
		
		try(var connection = ds.getConnection()) {
			try (var preparedStatement = connection.prepareStatement(selectSQL)) {
				preparedStatement.setString(1, "%"+value+"%");
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()) {
					Product bean = new Product();
					bean.setId(rs.getInt("codice"));
					bean.setName(rs.getString("nome"));
					bean.setDescription(rs.getString("descrizione"));
					bean.setImagePath(rs.getString("immagine"));
					bean.setTax(rs.getInt("iva"));
					bean.setPrice(rs.getDouble("prezzo"));
					bean.setBrand(rs.getString("marca"));
					bean.setWeight(rs.getDouble("peso"));
					bean.setAvailable(rs.getBoolean("disponibile"));
					bean.setDiscount(rs.getDouble("sconto"));
					bean.setQuantity(rs.getInt("quantity"));
					products.add(bean);				
				}
			}
		}
		return products;
	}


}

