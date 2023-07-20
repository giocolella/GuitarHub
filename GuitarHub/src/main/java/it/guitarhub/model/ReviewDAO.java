package it.guitarhub.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import it.guitarhub.beans.Product;
import it.guitarhub.beans.Review;
import it.guitarhub.beans.User;



public class ReviewDAO implements ImplementDAO<Review>{
	
	private static DataSource ds;
	private static final String TABLE_NAME = "Recensione";
	private static final Logger logger = Logger.getLogger(ReviewDAO.class.getName());

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/guitarhub");
		} catch (NamingException e) {
			logger.log(Level.SEVERE, "An error occurred", e);
		}
	}
	
	@Override
	public Collection<Review> doRetrieveAll(String order) throws SQLException {
		Collection<Review> reviewes = new LinkedList<>();
		if(order == null || order.equals("")) {
			return reviewes;
		}
		var sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY ?";
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, order);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					var rw = new Review();
					rw.setId(rs.getInt("id"));
					rw.setScore(rs.getInt("punteggio"));
					rw.setTitle(rs.getString("titolo"));
					rw.setDescription(rs.getString("descrizione"));
					rw.setIdProd(rs.getInt("idProd"));
					rw.setIdUser(rs.getInt("idUser"));
					rw.setReviewDate((Date) rs.getTimestamp("dataRec"));
					reviewes.add(rw);
				}
			}
		}
		return reviewes;
	}
	
	public Collection<Review> doRetrieveByUser(String order, User user) throws SQLException {
		Collection<Review> reviewes = new LinkedList<>();
		var sql = "SELECT * FROM " + TABLE_NAME + " WHERE idUser = ? ORDER BY ?";
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, user.getId());
				stmt.setString(2, order);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					var rw = new Review();
					rw.setId(rs.getInt("id"));
					rw.setScore(rs.getInt("punteggio"));
					rw.setTitle(rs.getString("titolo"));
					rw.setDescription(rs.getString("descrizione"));
					rw.setIdProd(rs.getInt("idProd"));
					rw.setIdUser(rs.getInt("idUser"));
					rw.setReviewDate((Date) rs.getTimestamp("dataRec"));
					reviewes.add(rw);
				}
			}
		}
		return reviewes;
	}
	
	public Collection<Review> doRetrieveByProduct(Product product) throws SQLException {
		Collection<Review> reviewes = new LinkedList<>();
		var sql = "SELECT * FROM " + TABLE_NAME + " WHERE idProd = ?";
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, product.getId());
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					var rw = new Review();
					rw.setId(rs.getInt("id"));
					rw.setIdProd(rs.getInt("idProd"));
					rw.setScore(rs.getInt("punteggio"));
					rw.setTitle(rs.getString("titolo"));
					rw.setDescription(rs.getString("descrizione"));
					rw.setIdUser(rs.getInt("idUser"));
					rw.setReviewDate((Date) rs.getTimestamp("dataRec"));
					reviewes.add(rw);
				}
			}
		}
		return reviewes;
	}
	

	@Override
	public Review doRetriveByKey(int code) throws SQLException {
		var sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
		Review rw = null;
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, code);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
					rw = new Review();
					rw.setId(rs.getInt("id"));
					rw.setScore(rs.getInt("punteggio"));
					rw.setTitle(rs.getString("titolo"));
					rw.setDescription(rs.getString("descrizione"));
					rw.setIdProd(rs.getInt("idProd"));
					rw.setIdUser(rs.getInt("idUser"));
					rw.setReviewDate((Date) rs.getTimestamp("dataRec"));
				}
			}
		}
		return rw;
	}
	
	public boolean doVerifySecond(int idUser, int idProd) throws SQLException {
		var sql = "SELECT * FROM " + TABLE_NAME + " WHERE idUser = ? AND idProd = ?";
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, idUser);
				stmt.setInt(2, idProd);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public void doSave(Review rw) throws SQLException {
		var sql = "INSERT INTO " + TABLE_NAME + " (idProd,idUser,titolo,descrizione,punteggio) VALUES (?,?,?,?,?)";
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, rw.getIdProd());
				stmt.setInt(2, rw.getIdUser());
				stmt.setString(3, rw.getTitle());
				stmt.setString(4, rw.getDescription());
				stmt.setInt(5, rw.getScore());
				stmt.executeUpdate();
			}
		}
	}

	public int doUpdate(Review rw) throws SQLException {
		var sql = "UPDATE FROM " + TABLE_NAME + " SET titolo = ?, descrizione = ?, punteggio = ? WHERE id = ?";
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, rw.getTitle());
				stmt.setString(2, rw.getDescription());
				stmt.setInt(3, rw.getScore());
				stmt.setInt(4, rw.getId());
				return stmt.executeUpdate();
			}
		}
	}

	@Override
	public boolean doDelete(int code) throws SQLException {
		var sql = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, code);
				return stmt.executeUpdate() > 0;
			}
		}
	}

}
