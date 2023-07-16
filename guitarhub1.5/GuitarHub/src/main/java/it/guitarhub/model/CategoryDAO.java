package it.guitarhub.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.guitarhub.beans.Category;


public class CategoryDAO implements ImplementDAO<Category> {	
	private static DataSource ds;
	private static final String TABLE_NAME = "Categoria";
	private static final String RELATIONSHIP_TABLE_NAME = "Ha";
	//private static final Logger LOGGER = LogManager.getLogger();

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
	public Collection<Category> doRetrieveAll(String order) throws SQLException {
		
		Collection<Category> cat = new LinkedList<>();
		String selectSQL = "SELECT * FROM "+ TABLE_NAME +" c LEFT JOIN " + RELATIONSHIP_TABLE_NAME + " cp ON c.codice = cp.idCat ";
		
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(selectSQL)) {
				//LOGGER.debug(stmt);
				ResultSet rs = stmt.executeQuery();
				//int lastCategoryid;
				if(rs.next()) {
					do {
						Category bean = new Category();
						bean.setId(rs.getInt("codice"));
						bean.setName(rs.getString("nome"));
						bean.setDescription(rs.getString("descrizione"));
						if(rs.getInt("idProd") != 0) {
							do {
								bean.addProductId(rs.getInt("idProd"));
							} while(rs.next() && bean.getId() == rs.getInt("idCat"));
						} else {
							rs.next();
						}
						cat.add(bean);
					} while (!rs.isAfterLast());
				}								
			}
		}
		return cat;
	}
			
		
	@Override
	public Category doRetriveByKey(int code) throws SQLException {

		String selectSQL = "SELECT * FROM " + RELATIONSHIP_TABLE_NAME + "AS b JOIN " + TABLE_NAME + " AS c ON c.codice = b.idCat WHERE c.codice = ?";
		
		Category bean = new Category();
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(selectSQL)) {
				ResultSet rs = stmt.executeQuery();
					
				if(rs.next()) {
					bean.setId(rs.getInt("codice"));
					bean.setName(rs.getString("nome"));
					bean.setDescription(rs.getString("descrizione"));						
					bean.addProductId(rs.getInt("idProd"));
					do {
						bean.addProductId(rs.getInt("idProd"));
					}while(rs.next());

				}												
			}
		}
		return bean;
	}

	@Override
	public void doSave(Category dao) throws SQLException {
		//salvare la categoria
		String insertSQLCategory = "INSERT INTO " + TABLE_NAME + "  (nome,descrizione) VALUES (?,?)";
		
				
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(insertSQLCategory)) {
				
				stmt.setString(1, dao.getName());
				stmt.setString(2, dao.getDescription());
				
				stmt.execute();
				
				}
			}
		}

	/*/String insertSQLBelongs = "INSERT INTO "+PIVOTE_TABLE_NAME+" (`pid`, `cid`) VALUES (?,?);";
	 * 					ArrayList<Integer> arry = dao.getProductsId();
					for (Integer i : arry) {
						
						try (var stmt2 = conn.prepareStatement(insertSQLBelongs)) {
							stmt2.setInt(1, i);
							stmt.setInt(2, dao.getId());
							stmt2.execute();
						}	
					}	
	 * 
	 * */

	
	
	
	
	@Override
	public int doUpdate(Category dao) throws SQLException {
		String updateSQL = "UPDATE  SET nome = ?, descrizione = ?  WHERE codice = ?";
		
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(updateSQL)) {
		
				stmt.setString(1, dao.getName());
				stmt.setString(2, dao.getDescription());
				stmt.setInt(3, dao.getId());
				
				return stmt.executeUpdate();
			}
		}
		
	}

	@Override
	public boolean doDelete(int code) throws SQLException {
		String deleteSQL = "DELETE FROM "+ TABLE_NAME +" WHERE codice = ?";
		
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(deleteSQL)) {
				
				stmt.setInt(1, code);
				stmt.executeQuery();
				return true;
		
			}
		}
	}

	
	
	public ArrayList<Category> doRetriveByProduct(int codeProduct) throws SQLException {
		String selectSQL="SELECT DISTINCT  c.codice,c.nome,c.description FROM " + RELATIONSHIP_TABLE_NAME + " AS b JOIN " 
	                     + TABLE_NAME + " AS c ON b.idCat = c.codice WHERE idProd = ?";
		
		ArrayList<Category> list = new ArrayList<Category>();
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(selectSQL)) {
				
				stmt.setInt(1, codeProduct);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					Category bean = new Category();
					bean.setId(rs.getInt("codice"));
					bean.setName(rs.getString("nome"));
					bean.setDescription(rs.getString("descrizione"));								
					list.add(bean);			
				}
				return list;
			}			
		}		
	}
	
	public boolean doChangeCategory(int codeProduct ,int lastCategory , int newCategory) throws SQLException {
		String updateSQL = "UPDATE " + RELATIONSHIP_TABLE_NAME + " SET idCat = ? WHERE  idProd = ? AND idCat = ?";


		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(updateSQL)) {
				
				stmt.setInt(1, newCategory);
				stmt.setInt(2, codeProduct);
				stmt.setInt(3, lastCategory);
				stmt.executeQuery();
			
				return true;
			}
		}
		
	}
	
	
}
