package com.cjon.bank.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.ui.Model;

import com.cjon.bank.dao.BankDAO;

public class BankDepositService implements BankService {

	@Override
	public void execute(Model model) {
			
		HttpServletRequest request = (HttpServletRequest) model.asMap().get("request");
		
		String member_id = request.getParameter("member_id");
		String member_balance = request.getParameter("member_balance");
		
		DataSource dataSource = (DataSource) model.asMap().get("dataSource");
		Connection con = null;
		
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			
			BankDAO dao = new BankDAO(con);
			boolean result = dao.updateDeposit(member_id, member_balance);
			
			if(result) { 
				con.commit();
			} else {
				con.rollback();
			}
			model.addAttribute("RESULT", result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
	}
}
