package event.simplon.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.apache.log4j.Logger;

import event.simplon.bean.Evenement;
import event.simplon.fwk.DataUtil;
import event.simplon.fwk.DateUtils;

public class EvenementManager {

private static Logger logger = Logger.getLogger(EvenementManager.class);
	
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public static List<Evenement> list(String id, String idParticipant, String date1, String date2) {
		long t1 = System.currentTimeMillis();
		Connection conn = DataUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet set = null;
		List<Evenement> list = new LinkedList<Evenement>();
		String query = "select *\r\n"
					 + "from evenement e\r\n"
					 + "left join participant p on p.id_evenement=e.id_evenement\r\n"	
					 + "where e.id_evenement<>0\r\n";
		if(!GenericValidator.isBlankOrNull(id))
			query += " and e.id_evenement=?";
		if(!GenericValidator.isBlankOrNull(idParticipant))
			query += " and p.id_participant<>?";
		if(!GenericValidator.isBlankOrNull(date1) && !GenericValidator.isBlankOrNull(date2)) 
			query += " and to_char(e.date_evenement,'yyyy-mm-dd +00') between ? and ?";
		
		
		try {
			int i=0;
			
		pstmt = conn.prepareStatement(query);
			if(!GenericValidator.isBlankOrNull(id))
				pstmt.setInt(++i, Integer.parseInt(id));
			if(!GenericValidator.isBlankOrNull(idParticipant))
				pstmt.setInt(++i, Integer.parseInt(idParticipant));
			if(!GenericValidator.isBlankOrNull(date1) && !GenericValidator.isBlankOrNull(date2)) {
				pstmt.setDate(++i, DateUtils.utilDateToSqlDate(sdf.parse(date1)));
				pstmt.setDate(++i, DateUtils.utilDateToSqlDate(sdf.parse(date2)));
			 }
			set = pstmt.executeQuery();
			while (set.next()) {
				Evenement bean = new Evenement();
				bean = bean.getClass().cast(bean.getClass().newInstance());
				bean = (bean.getClass().cast(DataUtil.getBean(bean, set)));
	
				list.add(bean);
			}
		} catch (Exception e) {
			logger.error(e);
			logger.error("query : " + query);
		} finally {
			DataUtil.endConn(pstmt, conn, set);
			long t2 = System.currentTimeMillis();
			logger.debug("query " + (long) (t2 - t1) + " ms");
		}
		return list;
	}
	
	public static List<Evenement> listEvent() {
		long t1 = System.currentTimeMillis();
		Connection conn = DataUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet set = null;
		List<Evenement> list = new LinkedList<Evenement>();
		String query = "select *\r\n"
					 + "from evenement e\r\n"
					 + "where e.id_evenement<>0\r\n";

		try {
			
			pstmt = conn.prepareStatement(query);
			set = pstmt.executeQuery();
			while (set.next()) {
				Evenement bean = new Evenement();
				bean = bean.getClass().cast(bean.getClass().newInstance());
				bean = (bean.getClass().cast(DataUtil.getBean(bean, set)));
				list.add(bean);
			}
		} catch (Exception e) {
			logger.error(e);
			logger.error("query : " + query);
		} finally {
			DataUtil.endConn(pstmt, conn, set);
			long t2 = System.currentTimeMillis();
			logger.debug("query " + (long) (t2 - t1) + " ms");
		}
		return list;
	}
	
	
	public static Evenement get(Integer id) {
		long t1 = System.currentTimeMillis();
		Connection conn = DataUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet set = null;
		Evenement bean = new Evenement();
		String query = "select * from evenement where id_evenement=?";
		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			set = pstmt.executeQuery();
			if (set.next()) {
				bean = bean.getClass().cast(bean.getClass().newInstance());
				bean = (bean.getClass().cast(DataUtil.getBean(bean, set)));
			}else
				bean = null;
		} catch (Exception e) {
			logger.error(e);
			logger.error("query : " + query);
		} finally {
			DataUtil.endConn(pstmt, conn, set);
			long t2 = System.currentTimeMillis();
			logger.debug("query " + (long) (t2 - t1) + " ms");
		}
		return bean;
	}
	
	public static Evenement getPartEvent(Integer idParticipant) {
		long t1 = System.currentTimeMillis();
		Connection conn = DataUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet set = null;
		Evenement bean = new Evenement();
		String query = "select * from evenement e, participant p where e.id_evenement=p.id_evenement and id_participant=?";
		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idParticipant);
			set = pstmt.executeQuery();
			if (set.next()) {
				bean = bean.getClass().cast(bean.getClass().newInstance());
				bean = (bean.getClass().cast(DataUtil.getBean(bean, set)));
			}else
				bean = null;
		} catch (Exception e) {
			logger.error(e);
			logger.error("query : " + query);
		} finally {
			DataUtil.endConn(pstmt, conn, set);
			long t2 = System.currentTimeMillis();
			logger.debug("query " + (long) (t2 - t1) + " ms");
		}
		return bean;
	}
	public static int insert(Evenement bean) {
		long t1 = System.currentTimeMillis();
		Connection conn = DataUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "INSERT INTO evenement(id_evenement, date_evenement, lib_evenement) VALUES (?, ?, ?)";
		int id = 0;
		int i=0;
		try {
			pstmt = conn.prepareStatement(query);
			
			id = DataUtil.seqDefault("evenement_id_evenement_seq", conn);
			pstmt.setInt(++i, id);
			pstmt.setDate(++i, DateUtils.utilDateToSqlDate(bean.getDateEvenement()));
			pstmt.setString(++i, bean.getLibEvenement()!=null?bean.getLibEvenement().trim():"");
			pstmt.execute();

		} catch (Exception e) {
			logger.error(e);
			logger.error("query : " + query);
		} finally {
			DataUtil.endConn(pstmt, conn, set);
			long t2 = System.currentTimeMillis();
			logger.debug("query " + (long) (t2 - t1) + " ms");
		}
		return id;
	}

	public static int update(Evenement bean) {
		long t1 = System.currentTimeMillis();
		Connection conn = DataUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "UPDATE evenement SET id_evenement=?, date_evenement=?, lib_evenement=? where id_evenement=?";

		try {
			int i=0;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(++i, bean.getIdEvenement());
			pstmt.setDate(++i, DateUtils.utilDateToSqlDate(bean.getDateEvenement()));
			pstmt.setString(++i, bean.getLibEvenement()!=null?bean.getLibEvenement().trim():"");
			pstmt.setInt(++i, bean.getIdEvenement());
			pstmt.execute();

		} catch (Exception e) {
			logger.error(e);
			logger.error("query : " + query);
		} finally {
			DataUtil.endConn(pstmt, conn, set);
			long t2 = System.currentTimeMillis();
			logger.debug("query " + (long) (t2 - t1) + " ms");
		}
		return bean.getIdEvenement();
	}
	
	public static int insertParticipant(Evenement bean) {
		long t1 = System.currentTimeMillis();
		Connection conn = DataUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query =  "INSERT INTO participant(id_participant, id_evenement, nom_participant, prenom_participant, tel_participant, email_participant)\r\n" + 
						"VALUES (?, ?, ?, ?, ?, ?)";
		int id = 0;
		int i=0;
		try {
			pstmt = conn.prepareStatement(query);
			
			id = DataUtil.seqDefault("participant_id_participant_seq", conn);
			pstmt.setInt(++i, id);
			pstmt.setInt(++i, bean.getIdEvenement());
			pstmt.setString(++i, bean.getNomParticipant()!=null?bean.getNomParticipant().trim():"");
			pstmt.setString(++i, bean.getPrenomParticipant()!=null?bean.getPrenomParticipant().trim():"");
			pstmt.setString(++i, bean.getTelParticipant()!=null?bean.getTelParticipant().trim():"");
			pstmt.setString(++i, bean.getEmailParticipant()!=null?bean.getEmailParticipant().trim():"");
			pstmt.execute();

		} catch (Exception e) {
			logger.error(e);
			logger.error("query : " + query);
		} finally {
			DataUtil.endConn(pstmt, conn, set);
			long t2 = System.currentTimeMillis();
			logger.debug("query " + (long) (t2 - t1) + " ms");
		}
		return id;
	}

	public static int updateParticipant(Evenement bean) {
		long t1 = System.currentTimeMillis();
		Connection conn = DataUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "UPDATE participant\r\n" + 
						"SET id_participant=?, id_evenement=?, nom_participant=?, prenom_participant=?, tel_participant=?, email_participant=?\r\n" + 
						"WHERE id_participant=?";

		try {
			int i=0;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(++i, bean.getIdParticipant());
			pstmt.setInt(++i, bean.getIdEvenement());
			pstmt.setString(++i, bean.getNomParticipant()!=null?bean.getNomParticipant().trim():"");
			pstmt.setString(++i, bean.getPrenomParticipant()!=null?bean.getPrenomParticipant().trim():"");
			pstmt.setString(++i, bean.getTelParticipant()!=null?bean.getTelParticipant().trim():"");
			pstmt.setString(++i, bean.getEmailParticipant()!=null?bean.getEmailParticipant().trim():"");
			pstmt.setInt(++i, bean.getIdParticipant());
			pstmt.execute();

		} catch (Exception e) {
			logger.error(e);
			logger.error("query : " + query);
		} finally {
			DataUtil.endConn(pstmt, conn, set);
			long t2 = System.currentTimeMillis();
			logger.debug("query " + (long) (t2 - t1) + " ms");
		}
		return bean.getIdEvenement();
	}
	
	public static void delete(int idEvenement) {
		long t1 = System.currentTimeMillis();
		Connection conn = DataUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "DELETE FROM evenement WHERE id_evenement=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idEvenement);
			pstmt.execute();

		} catch (Exception e) {
			logger.error(e);
			logger.error("query : " + query);
		} finally {
			DataUtil.endConn(pstmt, conn, set);
			long t2 = System.currentTimeMillis();
			logger.debug("query " + (long) (t2 - t1) + " ms");
		}
	}
}
