package it.guitarhub.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.guitarhub.beans.Order;
import it.guitarhub.beans.OrderItem;
import it.guitarhub.beans.User;

public class OrderDAO implements ImplementDAO<Order> {
	
	private static DataSource ds;
	private static final String TABLE_NAME = "Ordine";
	private static final Logger logger = Logger.getLogger(OrderDAO.class.getName());

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
	public Collection<Order> doRetrieveAll(String order) throws SQLException {
		Collection<Order> orders = new LinkedList<>();
		String selectSQL = "SELECT * FROM " + TABLE_NAME;
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(selectSQL)) {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Order bean = new Order();
					bean.setId(rs.getInt("codice"));
					bean.setDestination(rs.getString("destinazione"));
					bean.setTotalProducts(rs.getInt("qntProdotti"));
					bean.setTotalPaid(rs.getDouble("totale"));
					orders.add(bean);
				}
			}
		}
		return orders;
	}

	public Collection<Order> doRetrieveOrdersBetween(String sort, int userId, int from, int howMany, Timestamp fromDate,
			Timestamp toDate) throws SQLException {
		String selectSQL = userId == 0 ? 
				"SELECT * FROM  " + TABLE_NAME
				+ " WHERE idUser LIKE ? AND dataOrdine BETWEEN ? AND ? ORDER BY ? LIMIT ? OFFSET ?"
				:
				"SELECT * FROM  " + TABLE_NAME
				+ " WHERE idUser = ?  AND dataOrdine BETWEEN ? AND ? ORDER BY ? LIMIT ? OFFSET ?";
		Collection<Order> orders = new LinkedList<>();

		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(selectSQL)) {
				if(userId == 0)
					stmt.setString(1, "%");
				else {
				stmt.setInt(1, userId);
				}
				stmt.setTimestamp(2, fromDate);
				stmt.setTimestamp(3, toDate);
				stmt.setString(4, sort);
				stmt.setInt(5, howMany);
				stmt.setInt(6, from);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Order bean = new Order();
					bean.setId(rs.getInt("codice"));
					bean.setDestination(rs.getString("destinazione"));
					bean.setTotalProducts(rs.getInt("qntProdotti"));
					bean.setTotalPaid(rs.getDouble("totale"));
					bean.setTrackNumber(rs.getString("numeroSpedizione"));
					//bean.setCreatedAt(rs.getTimestamp("dataOrdine").toLocalDateTime());
					orders.add(bean);
				}
			}
		}
		return orders;
	}

	public Collection<Order> doRetrieveSome(String order, int from, int howMany) throws SQLException {
		Collection<Order> orders = new LinkedList<>();
		String selectSQL = "SELECT * FROM  " + TABLE_NAME + " ORDER BY ? LIMIT ? OFFSET ? ";
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(selectSQL)) {

				stmt.setString(1, order);
				stmt.setInt(2, howMany);
				stmt.setInt(3, from);

				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Order bean = new Order();
					bean.setId(rs.getInt("codice"));
					bean.setDestination(rs.getString("destinazione"));
					bean.setTotalProducts(rs.getInt("qntProdotti"));
					bean.setTotalPaid(rs.getDouble("totale"));
					orders.add(bean);
				}
			}
		}
		return orders;
	}

	@Override
	public Order doRetriveByKey(int code) throws SQLException {
		String selectSQL = "SELECT o.codice AS orderId, o.idUser, o.destinazione, o.qntProdotti, o.totale, o.numeroSpedizione, o.dataOrdine, od.* FROM "
				+ TABLE_NAME + " AS o LEFT JOIN dettagliOrdine AS od ON od.idOrdine = o.codice WHERE o.codice = ?";
		Order order = new Order();
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(selectSQL)) {
				stmt.setInt(1, code);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					order.setId(rs.getInt("codice"));
					order.setDestination(rs.getString("destinazione"));
					order.setTotalProducts(rs.getInt("qntProdotti"));
					order.setTotalPaid(rs.getDouble("totale"));
					
				}
				do {
					OrderItem item = new OrderItem();
					item.setId(rs.getInt("codice"));
					item.setOid(rs.getInt("idOrdine"));
					item.setName(rs.getString("nomeProd"));
					item.setTax(rs.getInt("iva"));
					item.setPrice(rs.getDouble("prezzoProd"));
					item.setWeight(rs.getDouble("pesoProd"));
					item.setQuantity(rs.getInt("qntProd"));
					order.addItem(item);
				} while (rs.next());
			}
		}
		return order;
	}

	@Override
	public void doSave(Order dao) throws SQLException {
		String insertOrder = "INSERT INTO Ordine(idUser,destinazione,qntProdotti,totale,numeroSpedizione) "
				+ " VALUES (?, ?, ?, ?, ?)";
		String insertItem = "INSERT INTO dettagliOrdine "
				+ "(idOrdine,nomeProd,shortDescription,prezzoProd,pesoProd,qntProd,iva) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		var conn = ds.getConnection();
		conn.setAutoCommit(false); 
		try (var stmt = conn.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setInt(1, dao.getUser().getId());
			stmt.setString(2, dao.getDestination());
			stmt.setInt(3, dao.getTotalProducts());
			stmt.setDouble(4, dao.getTotalPaid());
			stmt.setString(5, dao.getTrackNumber());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int lastInsertedId = rs.getInt(1);
				for (OrderItem item : dao.getItems()) {
					try(var stmt2 = conn.prepareStatement(insertItem)){
					stmt2.setInt(1, lastInsertedId);
					stmt2.setString(2, item.getName());
					stmt2.setString(3, item.getShortDescription());
					stmt2.setDouble(4, item.getPrice());
					stmt2.setDouble(5, item.getWeight());
					stmt2.setInt(6, item.getQuantity());
					stmt2.setInt(7, item.getTax());
					stmt2.execute();
					}
				}
			}
			conn.commit(); 
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "An error occurred", e);
			conn.rollback();
		}finally {
				if (conn != null)
					conn.close();
			}
	}

	public int doUpdateOrder(Order dao) throws SQLException {
		String updateOrder = "UPDATE Ordine SET destinazione = ?, numeroSpedizione = ? WHERE codice = ?";

		try(var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(updateOrder);) {
				stmt.setString(1, dao.getDestination());
				stmt.setString(2, dao.getTrackNumber());
				stmt.setInt(3, dao.getId());
				return stmt.executeUpdate();
			} 
		}
	}

	@Override
	public boolean doDelete(int code) throws SQLException {
		String deleteSQL = "DELETE * FROM " + TABLE_NAME + "WHERE codice = ?";
		int rs;
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(deleteSQL)) {

				stmt.setInt(1, code);

				rs = stmt.executeUpdate();
			}
		}
		if(rs > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public Collection<Order> doRetrieveByUser(User userBean) throws SQLException {
	    Collection<Order> orders = new ArrayList<>();
	    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE idUser = ?";
	    try (var conn = ds.getConnection()) {
	        try (var stmt = conn.prepareStatement(selectSQL)) {
	            stmt.setInt(1, userBean.getId());
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                Order order = new Order();
	                order.setId(rs.getInt("codice"));
	                order.setDestination(rs.getString("destinazione"));
	                order.setTotalProducts(rs.getInt("qntProdotti"));
	                order.setTotalPaid(rs.getDouble("totale"));
	                order.setTrackNumber(rs.getString("numeroSpedizione"));
	                order.setCreatedAt((Date) rs.getTimestamp("dataOrdine"));

	                orders.add(order);
	            }
	        }
	    }
	    return orders;
	}

	public Collection<OrderItem> doRetrieveOrderItemsByOrder(Order order) throws SQLException {
	    Collection<OrderItem> orderItems = new ArrayList<>();
	    String selectSQL = "SELECT * FROM dettagliOrdine WHERE idOrdine = ?";
	    try (var conn = ds.getConnection()) {
	        try (var stmt = conn.prepareStatement(selectSQL)) {
	            stmt.setInt(1, order.getId());
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                OrderItem orderItem = new OrderItem();
	                orderItem.setId(rs.getInt("codice"));
	                orderItem.setOid(rs.getInt("idOrdine"));
	                orderItem.setName(rs.getString("nomeProd"));
	                orderItem.setPrice(rs.getDouble("prezzoProd"));
	                orderItem.setQuantity(rs.getInt("qntProd"));
	                orderItem.setWeight(rs.getDouble("pesoProd"));
	                orderItem.setTax(rs.getInt("iva"));
	                orderItem.setShortDescription(rs.getString("shortDescription"));

	                orderItems.add(orderItem);
	            }
	        }
	    }
	    return orderItems;
	}


	public int doRetrieveCount() throws SQLException {
		String sql = "SELECT COUNT(*) AS c FROM " + TABLE_NAME;
		try(var conn = ds.getConnection()){
			try(var stmt = conn.prepareStatement(sql)){
				ResultSet rs = stmt.executeQuery();
				return rs.next() ? rs.getInt("c") : 0;
			}
		}
	}
	
	public Order doRetriveByKeyBill(int code) throws SQLException {
		String selectSQL = "SELECT o.* FROM " + TABLE_NAME + " AS o WHERE o.codice = ?";
		Order order = new Order();
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(selectSQL)) {
				stmt.setInt(1, code);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					 order.setId(rs.getInt("codice"));
		             order.setDestination(rs.getString("destinazione"));
		             order.setTotalProducts(rs.getInt("qntProdotti"));
		             order.setTotalPaid(rs.getDouble("totale"));
		             order.setTrackNumber(rs.getString("numeroSpedizione"));
		             order.setCreatedAt((Date) rs.getTimestamp("dataOrdine"));
				}
			}
		}
		return order;
	}
	
	public boolean checkDettagliOrdineExistence(int idU, String nomeP) throws SQLException {
	    String sql = "SELECT COUNT(*) FROM dettagliOrdine d " +
	            "JOIN Ordine o ON d.idOrdine = o.codice " +
	            "WHERE o.idUser = ? AND d.nomeProd = ?";

	    try (var conn = ds.getConnection();
	         var stmt = conn.prepareStatement(sql)) {
	        stmt.setLong(1, idU);
	        stmt.setString(2, nomeP);
	        try (var rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                int count = rs.getInt(1);
	                return count > 0;
	            }
	        }
	    }

	    return false;
	}
	
	public Collection<Order> doRetrieveOrdersBetweenDates(Timestamp fromDate,Timestamp toDate) throws SQLException {
		String selectSQL = "SELECT * FROM  " + TABLE_NAME + " WHERE dataOrdine BETWEEN ? AND ? ORDER BY idUser";
		Collection<Order> orders = new ArrayList<>();
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(selectSQL)) {
				stmt.setTimestamp(1, fromDate);
				stmt.setTimestamp(2, toDate);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					User user = new User();
					Order bean = new Order();
					bean.setId(rs.getInt("codice"));
					bean.setDestination(rs.getString("destinazione"));
					bean.setTotalProducts(rs.getInt("qntProdotti"));
					bean.setTotalPaid(rs.getDouble("totale"));
					bean.setTrackNumber(rs.getString("numeroSpedizione"));
					bean.setCreatedAt((Date)rs.getTimestamp("dataOrdine"));
					user.setId(rs.getInt("idUser"));
					bean.setUser(user);
					orders.add(bean);
				}
			}
		}
		return orders;
	}
	
	public Collection<Order> doRetrieveOrdersBetweenDatesAndByUsername(int idUser,Timestamp fromDate,Timestamp toDate) throws SQLException {
		String selectSQL = "SELECT * FROM  " + TABLE_NAME + " WHERE dataOrdine BETWEEN ? AND ? AND idUser = ? ORDER BY idUser";
		Collection<Order> orders = new ArrayList<>();
		User user = new User();
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(selectSQL)) {
				stmt.setTimestamp(1, fromDate);
				stmt.setTimestamp(2, toDate);
				stmt.setInt(3, idUser);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Order bean = new Order();
					bean.setId(rs.getInt("codice"));
					bean.setDestination(rs.getString("destinazione"));
					bean.setTotalProducts(rs.getInt("qntProdotti"));
					bean.setTotalPaid(rs.getDouble("totale"));
					bean.setTrackNumber(rs.getString("numeroSpedizione"));
					bean.setCreatedAt((Date)rs.getTimestamp("dataOrdine"));
					user.setId(rs.getInt("idUser"));
					bean.setUser(user);
					orders.add(bean);
				}
			}
		}
		return orders;
	}

	
}
