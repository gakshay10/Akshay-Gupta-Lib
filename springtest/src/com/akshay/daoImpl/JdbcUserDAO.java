package com.akshay.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.akshay.beans.UserBean;
import com.akshay.mycontroller.MyController;

/**
 * This class manages all the Data Access Object related operations.
 * 
 * @author AkshayKrGupta
 * @version 1.0
 */
public class JdbcUserDAO {
	@Autowired
	DataSource dataSource;

	/**
	 * @return dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * This method inserts the records of a new user and returns a integer value, where negative value represent failure of the operation.
	 * @param bean
	 *            The object of bean {@link UserBean}, contains all the records
	 *            to be inserted.
	 * @return i integer value to determine that the DAO operation is operation
	 *         was successful or not.
	 */
	public int insert(UserBean bean) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "insert into user (Name, Age, City, Country, Language, Contact, Description) values (?,?,?,?,?,?,?)";
		int i = jdbcTemplate.update(
				sql,
				new Object[] { bean.getName(), bean.getAge(), bean.getCity(),
						bean.getCountry(), bean.getLanguage(),
						bean.getContact(), bean.getDescription() });
		return i;
	}

	/**
	 * This method fetch the list of all the user records from the database and returns to {@link MyController}.
	 * @return List of {@link UserBean} which contains all the records of User
	 *         table
	 * @throws SQLException
	 */
	public List<UserBean> fetchAll() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select * from user";
		RowMapper<UserBean> rowMapper = new RowMapper<UserBean>() {
			/* 
			 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
			 */
			@Override
			public UserBean mapRow(ResultSet rs, int line) throws SQLException {
				UserBean userBean = new UserBean();
				userBean.setId(rs.getInt("id"));
				userBean.setName(rs.getString("NAME"));
				userBean.setAge(rs.getInt("AGE"));
				userBean.setCity(rs.getString("CITY"));
				userBean.setContact(rs.getString("CONTACT"));
				userBean.setCountry(rs.getString("COUNTRY"));
				userBean.setDescription(rs.getString("DESCRIPTION"));
				userBean.setLanguage(rs.getString("LANGUAGE"));
				return userBean;
			}
		};
		List<UserBean> list = jdbcTemplate.query(sql, rowMapper);
		return list;

	}

	/**
	 * This method returns the record of a particular user for edit.
	 * @param id
	 *            Id of a user whose record is needed to be fetched.
	 * @return bean An object of {@link UserBean} class which contains the
	 *         record of a User whose id is provided in parameter.
	 */
	public UserBean fetchUserById(String id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		UserBean bean = new UserBean();
		String sql = "select * from user where id = ?";
		bean = (UserBean) jdbcTemplate.queryForObject(sql, new Object[] { id },
				new BeanPropertyRowMapper<>(UserBean.class));
		return bean;
	}

	/**
	 * This method update the records of a user and returns a integer value, where negative value represent failure of the operation.
	 * @param bean
	 *            The object of bean {@link UserBean}, contains all the records
	 *            to be updated.
	 * @return integer value to determine that the DAO operation is operation
	 *         was successful or not.
	 */
	public int updateUser(UserBean bean) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "UPDATE `user` SET `NAME`=?, `CITY`=?, `AGE`=?, `COUNTRY`=?, `LANGUAGE`=?, `CONTACT`=?, `DESCRIPTION`=? WHERE `id`=?;";
		int i = jdbcTemplate
				.update(sql,
						new Object[] { bean.getName(), bean.getCity(),
								bean.getAge(), bean.getCountry(),
								bean.getLanguage(), bean.getContact(),
								bean.getDescription(), bean.getId() });
		return i;
	}

	/**
	 * This method deletes the record of selected user.
	 * @param id
	 *            Id of the user whose record is needed to delete.S
	 * @return i integer value to determine that the DAO operation is operation
	 *         was successful or not.
	 */
	public int deleteUser(String id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "DELETE FROM `user` WHERE `id`=?;";
		int i = jdbcTemplate.update(sql, new Object[] { id });
		return i;

	}

}
