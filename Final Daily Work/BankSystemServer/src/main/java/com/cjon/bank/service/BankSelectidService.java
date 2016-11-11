package com.cjon.bank.service;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.ui.Model;

import com.cjon.bank.dao.BankDAO;
import com.cjon.bank.dto.BankDTO;

public class BankSelectidService implements BankService {

	@Override
	public void execute(Model model) {
		HttpServletRequest request = (HttpServletRequest) model.asMap().get("request");
		String member_id = (String) model.asMap().get("member_id");
		System.out.println(member_id);
		DataSource dataSource = (DataSource) model.asMap().get("dataSource");
		Connection con;
		
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			BankDAO dao = new BankDAO(con);
			ArrayList<BankDTO> list = dao.selectId(member_id);
			
			if(list != null) {
				con.commit();
			} else {
				con.rollback();
			}
			
			model.addAttribute("RESULT", list);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
