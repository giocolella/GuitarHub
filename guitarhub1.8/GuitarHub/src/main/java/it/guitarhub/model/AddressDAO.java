package it.guitarhub.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.guitarhub.beans.Address;


public class AddressDAO implements ImplementDAO<Address> {
	
	private static DataSource ds;
	public static final String TABLE_NAME = "Indirizzo";

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
	public Collection<Address> doRetrieveAll(String order) throws SQLException {
		return null;
	}

	@Override
	public Address doRetriveByKey(int code) throws SQLException {
		String sql = "SELECT i.codice AS ic, i.nomeInd, i.cognomeInd, i.indirizzo, i.codPostale, i.city, i.provincia, i.telefono, i.preferito "
				   + " FROM " + TABLE_NAME + " AS i WHERE i.codice=?";
		Address addr = new Address();
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, code);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
					addr.setId(rs.getInt("ic"));
					addr.setFirstName(rs.getString("nomeInd"));
					addr.setLastName(rs.getString("cognomeInd"));
					addr.setAddress(rs.getString("indirizzo"));
					addr.setPostalCode(rs.getString("codPostale"));
					addr.setCity(rs.getString("city"));
					addr.setProvince(rs.getString("provincia"));
					addr.setPhone(rs.getString("telefono"));
					addr.setPreferred(rs.getBoolean("preferito"));
				}
			}
		}
		return addr;
	}

	@Override
	public void doSave(Address dao) throws SQLException {
		String sql = "INSERT INTO Indirizzo "
				   + " (nomeInd,cognomeInd,indirizzo,codPostale,city,provincia,telefono,preferito) "
				   + " VALUES (?,?,?,?,?,?,?,?)";
		
		if(dao.isPreferred()==true) {
			String updateSQL2= "UPDATE Indirizzo SET preferito=null WHERE idUser=?";
			
			try (var conn = ds.getConnection()) {
				try (var stmt2 = conn.prepareStatement(updateSQL2)) {
				stmt2.setInt(1, dao.getUid());
				System.out.println(stmt2);
				stmt2.execute();
				}
			}
		}
		
		try(var conn = ds.getConnection()) {
			try(var stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, dao.getFirstName());
				stmt.setString(2, dao.getLastName());
				stmt.setString(3, dao.getAddress());
				stmt.setString(4, dao.getPostalCode());
				stmt.setString(5, dao.getCity());
				stmt.setString(6, dao.getProvince());
				stmt.setString(7, dao.getPhone());
				stmt.setBoolean(8, false);
				
				stmt.execute();
			}
		}
	}

	@Override
	public int doUpdate(Address dao) throws SQLException {
		String updateSQL = "UPDATE " + TABLE_NAME + " SET "
				+ "nomeInd=?,cognomeInd=?,indirizzo=?,codPostale=?,city=?,"
				+ "provincia=?,telefono=?,preferito=? "
				+ " WHERE codice=?";
	
		if(dao.isPreferred()==true) {
			String updateSQL2= "UPDATE Indirizzo SET preferito=null WHERE idUser=?";
			
			try (var conn = ds.getConnection()) {
				try (var stmt2 = conn.prepareStatement(updateSQL2)) {
				stmt2.setInt(1, dao.getUid());
				System.out.println(stmt2);
				stmt2.execute();
				}
			}
		}
		
		
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(updateSQL)) {
				stmt.setString(1, dao.getFirstName());
				stmt.setString(2, dao.getLastName());
				stmt.setString(3, dao.getAddress());
				stmt.setString(4, dao.getPostalCode());
				stmt.setString(5, dao.getCity());
				stmt.setString(6, dao.getProvince());
				stmt.setString(7, dao.getPhone());
				stmt.setBoolean(8,dao.isPreferred());
				stmt.setInt(9,dao.getId());
				System.out.println(stmt);
				return stmt.executeUpdate();
			}
		}
	}
	

	@Override
	public boolean doDelete(int code) throws SQLException {
		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE  `codice= ?";
			
			try (var conn = ds.getConnection()) {
				try (var stmt = conn.prepareStatement(deleteSQL)) {
					stmt.setInt(1, code);
					return stmt.executeUpdate()>0;
				}
		}
	}
	
}