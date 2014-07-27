package com.akshay.mycontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.akshay.beans.UserBean;
import com.akshay.daoImpl.JdbcUserDAO;

/**
 * This is the controller class, handles all the User Operation related
 * requests.
 * 
 * @author AkshayKrGupta
 * @version 1.0
 */
@Controller
public class MyController {
	@Autowired
	JdbcUserDAO userDAO;

	/**
	 * This method is called on the load of the application and returns the Home
	 * page.
	 * 
	 * @return {@link ModelAndView} home.jsp
	 */
	@RequestMapping("/showHome")
	public ModelAndView showHome() {
		return new ModelAndView("home");
	}

	/**
	 * This method returns a User form whose properties are linked with
	 * {@link UserBean} properties.
	 * 
	 * @return {@link ModelAndView} userPage.jsp
	 */
	@RequestMapping("/showUser")
	public ModelAndView showForm() {
		UserBean obj = new UserBean();
		return new ModelAndView("userPage", "user", obj);
	}

	/**
	 * This method is called to save the records of new user and it returns a
	 * page with either success or failure message.
	 * 
	 * @param userBean
	 *            The bean which contains all the required values to be
	 *            persisted.
	 * @return {@link ModelAndView} displayMessage.jsp
	 */
	@RequestMapping("/saveUser")
	public ModelAndView saveUser(@ModelAttribute("obj") UserBean userBean) {
		String name = userBean.getName();

		int i = userDAO.insert(userBean);
		String message;
		if (i == -1) {
			message = "Could not save the record!!";
			return new ModelAndView("displayMessage", "msg", message);
		} else {
			message = "Record of User " + name + " saved successfully!!";
			return new ModelAndView("displayMessage", "msg", message);
		}
	}

	/**
	 * This method returns a page which contains the records of all the users.
	 * 
	 * @return {@link ModelAndView} viewAll.jsp
	 */
	@RequestMapping("/viewAll")
	public ModelAndView viewAll() {
		List<UserBean> list = new ArrayList<UserBean>();
		list = userDAO.fetchAll();
		return new ModelAndView("viewAll", "records", list);
	}

	/**
	 * This methods returns a edit page for a selected user, which contains the
	 * previous values of that user to be edited.
	 * 
	 * @param id
	 *            The id of the User.
	 * @return {@link ModelAndView} editUser.jsp
	 */
	@RequestMapping("/editUser")
	public ModelAndView editUser(@RequestParam String id) {
		UserBean bean = new UserBean();
		bean = userDAO.fetchUserById(id);
		return new ModelAndView("editUser", "user", bean);
	}

	/**
	 * This method update the records for a selected users and return a page
	 * with either success or failure message.
	 * 
	 * @param userBean
	 *            The bean object which contains all the required values to
	 *            update the record.
	 * @return {@link ModelAndView} displayMessage.jsp
	 */
	@RequestMapping("/updateUser")
	public ModelAndView updateUser(@ModelAttribute("bean") UserBean userBean) {

		int i = userDAO.updateUser(userBean);
		String message;
		if (i == -1) {
			message = "Could not update the record!!";
			return new ModelAndView("displayMessage", "msg", message);
		} else {
			message = "Record of User updated successfully!!";
			return new ModelAndView("displayMessage", "msg", message);
		}
	}

	/**
	 * This method deletes the records of selected user and return a page with
	 * either success or failure message.
	 * 
	 * @param id
	 *            The id of the User.
	 * @return {@link ModelAndView} displayMessage.jsp
	 */
	@RequestMapping("/deleteUser")
	public ModelAndView deleteUser(@RequestParam String id) {
		int i = userDAO.deleteUser(id);
		String message;
		if (i == -1) {
			message = "Could not delete the record!!";
			return new ModelAndView("displayMessage", "msg", message);
		} else {
			message = "Record of User deleted successfully!!";
			return new ModelAndView("displayMessage", "msg", message);
		}
	}

}
