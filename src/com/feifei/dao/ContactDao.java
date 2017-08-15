package com.feifei.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.feifei.po.Contact;

public class ContactDao extends BaseDAO<Contact> {

	private static final String INSERT="insert into index_contact values(?,?,?,?,?,?,?,?,?)";
	
	public int insertAll(Contact contact){
		return insert1(INSERT, contact);
	}

	public void toInsert(Contact t, PreparedStatement pstmt) {
		try {
			pstmt.setString(1, t.getcNo());
			pstmt.setString(2, t.getcName());
			pstmt.setString(3, t.getcEmail());
			pstmt.setString(4, t.getcSubject());
			pstmt.setString(5, t.getcMessage());
			pstmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			pstmt.setString(7, t.getcStatus());
			pstmt.setString(8, t.getcIP());
			pstmt.setString(9, t.getcAdress());
			
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}

	@Override
	public Contact toEntity(ResultSet rs) {
		return null;
	}

	@Override
	public void toUpdate(List list, PreparedStatement pstmt) {
		
	}
}
