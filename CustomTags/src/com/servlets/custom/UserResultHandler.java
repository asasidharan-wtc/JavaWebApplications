package com.servlets.custom;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class UserResultHandler extends TagSupport {

	private static final long serialVersionUID = 1L;
	private Connection connection;
	private PreparedStatement prepStatement;

	public UserResultHandler() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String newDBUrlString = "jdbc:mysql://localhost/newDB";
			String newDBUserString = "root";
			String newDBPasswordString = "dragonballZ68!";
			connection = DriverManager.getConnection(newDBUrlString, newDBUserString, newDBPasswordString);
			prepStatement = connection.prepareStatement("SELECT * FROM user WHERE email = ?");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public int doStartTag() throws JspException {
		ServletRequest request = pageContext.getRequest();
		String emailString = request.getParameter("email");
		try {
			prepStatement.setString(1, emailString);
			ResultSet resultset = prepStatement.executeQuery();
			JspWriter outJspWriter = pageContext.getOut();
			if (resultset.next()) {
				outJspWriter.print("User Details are:</br>Email = ");
				outJspWriter.print(resultset.getString(1) + "</br>Password = ");
				outJspWriter.print(resultset.getString(2));
			} else {
				outJspWriter.print("Invalid email entered.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Tag.SKIP_BODY;
	}

	@Override
	public void release() {
		try {
			prepStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
