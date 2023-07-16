package it.guitarhub.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedList;
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
					bean.setStatus(rs.getString("stato"));
					bean.setCreatedAt(rs.getTimestamp("dataOrdine").toLocalDateTime());
					orders.add(bean);
				}
			}
		}
		return orders;
	}

	public Collection<Order> doRetrieveOrdersBetween(String sort, int userId, int from, int howMany, Timestamp fromDate,
			Timestamp ToDate) throws SQLException {
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
				else
				stmt.setInt(1, userId);
				stmt.setTimestamp(2, fromDate);
				stmt.setTimestamp(3, ToDate);
				stmt.setString(4, sort);
				stmt.setInt(5, howMany);
				stmt.setInt(6, from);
				System.out.println(stmt);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Order bean = new Order();
					bean.setId(rs.getInt("codice"));
					bean.setDestination(rs.getString("destinazione"));
					bean.setTotalProducts(rs.getInt("qntProdotti"));
					bean.setTotalPaid(rs.getDouble("totale"));
					bean.setTrackNumber(rs.getString("numeroSpedizione"));
					bean.setCreatedAt(rs.getTimestamp("dataOrdine").toLocalDateTime());
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
					bean.setStatus(rs.getString("stato"));
					bean.setCreatedAt(rs.getTimestamp("dataOrdine").toLocalDateTime());
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
				System.out.println(stmt);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					order.setId(rs.getInt("codice"));
					order.setDestination(rs.getString("destinazione"));
					order.setTotalProducts(rs.getInt("qntProdotti"));
					order.setTotalPaid(rs.getDouble("totale"));
					order.setStatus(rs.getString("numeroSpedizione"));
					order.setCreatedAt(rs.getTimestamp("dataOrdine").toLocalDateTime());
					
				}
				do {
					OrderItem item = new OrderItem();
					item.setId(rs.getInt("codice"));
					item.setOid(rs.getInt("idOrdine"));
					item.setName(rs.getString("nomeProd"));
					//item.setDescription(rs.getString("description"));
					item.setTax(rs.getInt("iva"));
					item.setPrice(rs.getDouble("prezzoProd"));
					item.setWeight(rs.getDouble("pesoProd"));
					item.setDiscount(rs.getDouble("sconto"));
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
				+ "(idOrdine,nomeProd,prezzoProd,pesoProd,sconto,qntProd,iva) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		var conn = ds.getConnection();
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
					var stmt2 = conn.prepareStatement(insertItem);
					stmt2.setInt(1, lastInsertedId);
					stmt2.setString(2, item.getName());
					//stmt2.setString(3, item.getDescription());
					stmt2.setDouble(3, item.getPrice());
					stmt2.setDouble(4, item.getWeight());
					stmt2.setDouble(5, item.getDiscount());
					stmt2.setInt(6, item.getQuantity());
					stmt2.setInt(7, item.getTax());
					stmt2.execute();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}

	}

	public int doUpdateOrder(Order dao) throws SQLException {
		String updateOrder = "UPDATE Ordine SET destinazione = ?, numeroSpedizione = ? WHERE codice = ?";

		try(var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(updateOrder);) {
				stmt.setString(1, dao.getDestination());
				stmt.setString(2, dao.getTrackNumber());
				stmt.setInt(3, dao.getId());
				System.out.println(stmt);
				return stmt.executeUpdate();
			} 
		}
	}

	@Override
	public int doUpdate(Order dao) throws SQLException {

		String updateOrder = "UPDATE Ordine SET (destinazione = ?,qntProdotti = ?,totale = ?,numeroSpedizione = ?) WHERE idUser = ? AND codice = ? ";
		String updateItem = "UPDATE  dettagliOrdine SET (nomeProd = ?,qntProd = ?,prezzoProd = ?,sconto = ?,pesoProd = ?,iva = ? ) WHERE codice = ? AND idOrdine = ?";
		var conn = ds.getConnection();

		try {
			var stmt = conn.prepareStatement(updateOrder);
			stmt.setString(1, dao.getDestination());
			stmt.setInt(2, dao.getTotalProducts());
			stmt.setDouble(3, dao.getTotalPaid());
			stmt.setString(4, dao.getTrackNumber());
			stmt.setInt(5, dao.getUser().getId());
			stmt.setInt(6, dao.getId());

			stmt.executeUpdate();

			for (OrderItem item : dao.getItems()) {
				var stmt2 = conn.prepareStatement(updateItem);

				stmt2.setString(1, item.getName());
				//stmt2.setString(2, item.getDescription());
				stmt2.setInt(2, item.getQuantity());
				stmt2.setDouble(3, item.getPrice());
				stmt2.setDouble(4, item.getDiscount());
				stmt2.setDouble(5, item.getWeight());
				stmt2.setInt(6, item.getTax());
				stmt2.setInt(7, item.getId());
				stmt2.setInt(8, dao.getId());

				stmt2.execute();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}

		return 0;
	}

	@Override
	public boolean doDelete(int code) throws SQLException {
		String deleteSQL = "DELETE * FROM " + TABLE_NAME + "WHERE codice = ?";
		int rs;
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(deleteSQL)) {

				stmt.setInt(1, code);

				rs = stmt.executeUpdate();

				return rs > 0 ? true : false;
			}
		}
	}

	public Collection<Order> doRetriveByUser(User userBean) throws SQLException {
		Collection<Order> orders = new LinkedList<Order>();
		String selectSQL = "SELECT * FROM " + TABLE_NAME +" WHERE idUser = ?";
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(selectSQL)) {
				stmt.setInt(1, userBean.getId());
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Order bean = new Order();
					bean.setId(rs.getInt("codice"));
					bean.setDestination(rs.getString("destinazione"));
					bean.setTotalProducts(rs.getInt("qntProdotti"));
					bean.setTotalPaid(rs.getDouble("totale"));
					bean.setStatus(rs.getString("stato"));
					bean.setCreatedAt(rs.getTimestamp("dataOrdine").toLocalDateTime());
					orders.add(bean);
				}
			}
		}
		return orders;
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

}
