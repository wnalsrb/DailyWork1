package com.cjon.bank.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.ui.Model;

import com.cjon.bank.dao.BankDAO;

public class BankTransferService implements BankService {

	@Override
	public void execute(Model model) {
		HttpServletRequest request = (HttpServletRequest) model.asMap().get("request");
		
		String send_id = (String) model.asMap().get("send_id");
		String receive_id = (String) model.asMap().get("receive_id");
		String transfer_money = (String) model.asMap().get("transfer_money");
		
		DataSource dataSource = (DataSource) model.asMap().get("dataSource");
		Connection con = null;
		
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			
			BankDAO dao = new BankDAO(con);
			boolean result_withdraw = dao.updateWithdraw(send_id, transfer_money);
			boolean result_deposit = dao.updateDeposit(receive_id, transfer_money);
			boolean result_sum = false;
			
			if(result_withdraw == true && result_deposit == true){
				con.commit();
				result_sum = true;
			} else {
				con.rollback();
			}
			
			model.addAttribute("RESULT", result_sum);
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
