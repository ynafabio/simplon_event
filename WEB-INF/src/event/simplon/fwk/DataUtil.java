package event.simplon.fwk;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class DataUtil {
	private static Logger logger = Logger.getLogger(DataUtil.class);
	
	public static void endConn(PreparedStatement pstmt, Connection conn, ResultSet set) {
		if (null != pstmt) {
			try {
				pstmt.close();
			} catch (Exception ignore) {
			}
		}
		if (null != set) {
			try {
				set.close();
			} catch (Exception ignore) {
			}
		}

		if (null != conn) {
			try {
				conn.close();
			} catch (Exception ignore) {
			}
		}
	}

	
	
//	public static int seqDefault(String seq) {
//
//        long t1 = System.currentTimeMillis();
//
//        int newId = 0;
//        String query = "select "+seq+".nextval from dual";
//
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try {
//        	conn = getConnection();
//            pstmt = conn.prepareStatement(query);
//            rs = pstmt.executeQuery();
//
//            if (rs != null) {
//                if (rs.next()) {
//                    newId = rs.getInt(1);
//                }
//                rs.close();
//                rs = null;
//            }
//            pstmt.close();
//            pstmt = null;
//        } catch (Exception e) {
//            logger.error(e);
//        } finally {
//            endConn(pstmt, null, rs);
//            long t2 = System.currentTimeMillis();
//            logger.debug("sequence : " + seq + " in " + (long) (t2 - t1)
//                    + " ms : " + newId);
//
//        }
//        return newId;
//    } 


	public static Connection getConnection2() {
		 try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // System.out.println("Driver O.K.");
     
         String url = "jdbc:postgresql://localhost:1234/gestmat_20";
         String user = "gestmat";
         String passwd = "passwdGestm@t2019";
//         String url = "jdbc:postgresql://localhost:5432/gestmat";
//         String user = "postgres";
//         String passwd = "admin";
         Connection con = null;
         //System.out.println("Connexion effective !");
        try {
            con = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException ex) {
            logger.error("SQLException: " + ex.getMessage());
        }
        return con;
    }
	
	
	public static Connection getConnection() {

		Connection connection = null;
		DataSource dataSource = null;
		try {

			InitialContext initialContext = new InitialContext();

			dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/postgres");

			connection = dataSource.getConnection();

		} catch (SQLException se) {
			logger.error("Error SE in getting connection " + se.toString() + dataSource + " - " + connection);
		} catch (NamingException ne) {
			logger.error("Error NE in getting connection " + ne.toString() + dataSource + " -  " + connection);
		} catch (Exception e) {
			logger.error("Error in connection : " + e.toString() + dataSource + " -  " + connection);
		}
		// logger.info("Connection : "+connection);
		return connection;
	}
	
	public static int seqIdentity(Connection conn) {

		long t1 = System.currentTimeMillis();

		int newId = 0;
		String query = "	SELECT @@IDENTITY;";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs != null) {
				if (rs.next()) {
					newId = rs.getInt(1);
				}
				rs.close();
				rs = null;
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			endConn(pstmt, null, rs);
			long t2 = System.currentTimeMillis();
			System.out.println("sequence :  in " + (long) (t2 - t1) + " ms : " + newId);

		}
		return newId;
	}

	public static int seqDefault(String seq, Connection conn) {

		long t1 = System.currentTimeMillis();

		int newId = 0;
		String query = "select nextval('" + seq + "')";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs != null) {
				if (rs.next()) {
					newId = rs.getInt(1);
				}
				rs.close();
				rs = null;
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			endConn(pstmt, null, rs);
			long t2 = System.currentTimeMillis();
			System.out.println("sequence : " + seq + " in " + (long) (t2 - t1) + " ms : " + newId);

		}
		return newId;
	}
	
	public static int seqDefault(String seq) {

		long t1 = System.currentTimeMillis();

		int newId = 0;
		String query = "select nextval('" + seq + "')";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DataUtil.getConnection();
		
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs != null) {
				if (rs.next()) {
					newId = rs.getInt(1);
				}
				rs.close();
				rs = null;
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			endConn(pstmt, conn, rs);
			long t2 = System.currentTimeMillis();
			System.out.println("sequence : " + seq + " in " + (long) (t2 - t1) + " ms : " + newId);

		}
		return newId;
	}
	
	
	public static int getCurrentSeq(String seq) {
		long t1 = System.currentTimeMillis();
		Connection conn = DataUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "SELECT last_value FROM " + seq ;
		
		int newId = 0;
		try {
			pstmt = conn.prepareStatement(query);
			set = pstmt.executeQuery();
			if (set.next()) {
				newId = set.getInt(1);
			} 
		} catch (Exception e) {
			logger.error(e);
			logger.error("query : " + query);
		} finally {
			DataUtil.endConn(pstmt, conn, set);
			long t2 = System.currentTimeMillis();
			logger.debug("query " + (long) (t2 - t1) + " ms");
		}
		return newId;
	}
	
//	public static int getCurrentSeq(String seq) {
//		long t1 = System.currentTimeMillis();
//		int newId = 0;
//		String query = "select currval('" + seq + "')";
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			conn = DataUtil.getConnection();
//			pstmt = conn.prepareStatement(query);
//			rs = pstmt.executeQuery();
//
//			if (rs != null) {
//				if (rs.next()) {
//					newId = rs.getInt(1);
//				}
//				rs.close();
//				rs = null;
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		} finally {
//			endConn(pstmt, conn, rs);
//			long t2 = System.currentTimeMillis();
//			System.out.println("sequence : " + seq + " in " + (long) (t2 - t1) + " ms : " + newId);
//		}
//		return newId;
//	}

	public static Object getBean(Object bean, ResultSet set)
			throws SQLException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Method method = null;
		String nomColonne = null;
		ResultSetMetaData rsmd = set.getMetaData();
		int numberOfColumns = rsmd.getColumnCount();
		// logger.debug("nb col : " + numberOfColumns);
		Method[] methodsBean = bean.getClass().getDeclaredMethods();
		for (int i = 0; i < methodsBean.length; i++) {
			method = methodsBean[i];
			for (int j = 1; j <= numberOfColumns; j++) {
				// logger.debug(" col name - > " + rsmd.getColumnName(j));
				nomColonne = rsmd.getColumnName(j);

				if (method.getName().startsWith("set")
						&& method.getName().substring(3).equalsIgnoreCase(nomColonne.replaceAll("_", ""))) {
					// logger.debug(" method set name - > "
					// + methodsBean[i].getName());
					Object[] params = { "" };
					if (rsmd.getColumnType(j) == Types.INTEGER) {
						params[0] = new Integer(set.getInt(j));
					} else if (rsmd.getColumnType(j) == Types.NUMERIC) {
						params[0] = new Integer(set.getInt(j));
					} else if (rsmd.getColumnType(j) == Types.BIGINT) {
						params[0] = new Long(set.getLong(j));
					} else if (rsmd.getColumnType(j) == Types.DOUBLE) {
						params[0] = new Double(set.getDouble(j));
					} else if (rsmd.getColumnType(j) == Types.FLOAT) {
						params[0] = new Float(set.getFloat(j));
					} else if (rsmd.getColumnType(j) == Types.DECIMAL) {
						params[0] = new Double(set.getDouble(j));
					} else if (rsmd.getColumnType(j) == Types.VARCHAR) {
						params[0] = set.getString(j);
					} else if (rsmd.getColumnType(j) == Types.CHAR) {
						params[0] = set.getString(j);
					} else if (rsmd.getColumnType(j) == Types.DATE) {
						params[0] = set.getDate(j);
					} else if (rsmd.getColumnType(j) == Types.TIMESTAMP) {
						params[0] = set.getTimestamp(j);
					} else {
						params[0] = set.getString(j);
					}
					// logger.debug(" col type - > "
					// + rsmd.getColumnTypeName(j));
					method.invoke(bean, params);
				}
			}
		}
		rsmd = null;
		return bean;
	}


	public static Logger getLogger() {
		return logger;
	}


	public static void setLogger(Logger logger) {
		DataUtil.logger = logger;
	}

	
	
}
