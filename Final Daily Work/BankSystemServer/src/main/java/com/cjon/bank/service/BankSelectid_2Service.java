package com.cjon.bank.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.ui.Model;

import com.cjon.bank.dao.BankDAO;
import com.cjon.bank.dto.AccountDTO;

public class BankSelectid_2Service implements BankService {

	@Override
	public void execute(Model model) {
		DataSource dataSource = (DataSource) model.asMap().get("dataSource");
		String member_id = (String) model.asMap().get("member_id");
		Connection con;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			BankDAO dao = new BankDAO(con);

			ArrayList<AccountDTO> result = dao.selectId_2(member_id);

			if (result != null) {
				con.commit();
			} else {
				con.rollback();
			}
			model.addAttribute("RESULT", result);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

}
