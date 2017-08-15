package com.feifei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.feifei.po.Contact;

/**
 * 问题6:多数类包含相同的功能 解决:抽象公共父类---包含公共的方法实现 问题7:同一个方法在不同环境下返回不同的对象类型
 * 解决:泛型类--T--占位--用在父类和方法上
 * 
 * @author 高家强
 * 
 */
public abstract class BaseDAO<T> {
	/*
	 * 问题8:定义方法时,参数个数不确定,参数类型也不确定 解决:Object[]作为参数
	 */
	
	protected List<T> find(String sql, Object[] params) {
		List<T> l = new ArrayList<T>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			if (params != null) { // 参数数组中包含及格参数,就要set几次
				// 遍历数组
				for (int i = 0; i < params.length; i++) { // 设置每一条参数  setObject自动判断参数类型
				//  Java和Oracle下标习惯不同   java从0开始,而Oracle从1开始
					pstmt.setObject(i + 1, params[i]);
				}
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {

				// 问题9:父类无法替子类实现部分功能 解决:定义抽象方法--子类必须实现 注意:抽象方法的定义由子类完成
				// 抽象方法多数被父类调用

				l.add(toEntity(rs));
			}
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBUtils.closeConnection(conn);
		}
	}
	 
	public int insert1(String sql, Contact c) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			if (c != null) {
				toInsert(c, pstmt);
				pstmt.execute();
				// pstmt.addBatch();
				// pstmt.executeBatch();
				// pstmt.clearBatch();

				conn.commit();
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return 0;
	}

	public int insert(String sql, List<T> list, int insertOrUpdate) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
//					toInsert(list.get(i), pstmt);
					pstmt.addBatch();
					if (i % 1000 == 0) {
						pstmt.executeBatch();
						pstmt.clearBatch();
						return 1;
					}
				}
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return 0;
	}

	
	public void update(String sql, List list) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					toUpdate(list, pstmt);
					pstmt.addBatch();
					if (i % 1000 == 0) {
						pstmt.executeBatch();
						pstmt.clearBatch();
					}
				}
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBUtils.closeConnection(conn);
		}
	}
	 
	/**
	 * 父类交给子类一个rs,请求子类帮助转化为实体对象
	 * 
	 * @param rs
	 *            :父类交给子类的结果集
	 * @return:子类转化后的实体对象 注意:类中包含抽象方法,类必须也是抽象的
	 */
	public abstract void toInsert(Contact c, PreparedStatement pstmt);
	
	public abstract T toEntity(ResultSet rs);
	 
	 
	public abstract void toUpdate(List list,PreparedStatement pstmt);
	 
}
