package com.cjon.bank.controller;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjon.bank.dto.AccountDTO;
import com.cjon.bank.dto.BankDTO;
import com.cjon.bank.service.BankDepositService;
import com.cjon.bank.service.BankSelectAllMemberService;
import com.cjon.bank.service.BankSelectidService;
import com.cjon.bank.service.BankSelectid_2Service;
import com.cjon.bank.service.BankService;
import com.cjon.bank.service.BankWithdrawService;

@Controller
public class BankController {
	
	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private BankService service;
	
	@RequestMapping(value="/selectAllMember")
	public void selectAllMember(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String callback = request.getParameter("callback");
		
		service = new BankSelectAllMemberService();
		model.addAttribute("dataSource", dataSource);
		service.execute(model);
		
		ArrayList<BankDTO> list = (ArrayList<BankDTO>) model.asMap().get("RESULT");	
		
		String json = null;
		ObjectMapper om = new ObjectMapper();
		try {
			json = om.defaultPrettyPrintingWriter().writeValueAsString(list);
			response.setContentType("text/plain; charset=utf8");
			response.getWriter().println(callback + "(" + json + ")");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/selectid")
	public void selectid(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String callback = request.getParameter("callback");
		String member_id = request.getParameter("member_id");
		service = new BankSelectidService();
		model.addAttribute("dataSource", dataSource);
		model.addAttribute("request", request);
		model.addAttribute("member_id", member_id);
		service.execute(model);
		
		ArrayList<BankDTO> list = (ArrayList<BankDTO>) model.asMap().get("RESULT");	
		
		String json = null;
		ObjectMapper om = new ObjectMapper();
		try {
			json = om.defaultPrettyPrintingWriter().writeValueAsString(list);
			response.setContentType("text/plain; charset=utf8");
			response.getWriter().println(callback + "(" + json + ")");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value="/deposit")
	public void deposit(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String callback = request.getParameter("callback");
		
		service = new BankDepositService();
		model.addAttribute("dataSource", dataSource);
		model.addAttribute("request", request);
		service.execute(model);
		
		// result
		
		boolean result = (Boolean) model.asMap().get("RESULT");
		response.setContentType("text/plain; charset=utf8");
		PrintWriter out;
		
		try {
			out = response.getWriter();

			out.println(callback + "(" + result + ")");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@RequestMapping(value="/withdraw")
	public void withdraw(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String callback = request.getParameter("callback");
		
		service = new BankWithdrawService();
		model.addAttribute("dataSource", dataSource);
		model.addAttribute("request", request);
		service.execute(model);
		
		// result
		
		boolean result = (Boolean) model.asMap().get("RESULT");
		response.setContentType("text/plain; charset=utf8");
		PrintWriter out;
		
		try {
			out = response.getWriter();

			out.println(callback + "(" + result + ")");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@RequestMapping(value="/transfer")
	public void transfer(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String callback = request.getParameter("callback");
		String send_id = request.getParameter("send_id");
		String receive_id = request.getParameter("receive_id");
		String transfer_money = request.getParameter("transfer_money");
		
		
		service = new BankWithdrawService();
		model.addAttribute("dataSource", dataSource);
		model.addAttribute("request", request);
		model.addAttribute("send_id", send_id);
		model.addAttribute("receive_id", receive_id);
		model.addAttribute("transfer_money", transfer_money);
		service.execute(model);
		
		// result
		
		boolean result = (Boolean) model.asMap().get("RESULT");
		response.setContentType("text/plain; charset=utf8");
		PrintWriter out;
		
		try {
			out = response.getWriter();

			out.println(callback + "(" + result + ")");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/selectid_2")
	public void selectId_2(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String callback = request.getParameter("callback");
		String member_id = request.getParameter("member_id");
		service = new BankSelectid_2Service();
		
		model.addAttribute("dataSource", dataSource);
		model.addAttribute("member_id", member_id);
		service.execute(model);

		ArrayList<AccountDTO> list = (ArrayList<AccountDTO>) model.asMap().get("RESULT");
		
		String json = null;
		ObjectMapper om = new ObjectMapper();
		
		try {
			json = om.defaultPrettyPrintingWriter().writeValueAsString(list);
			response.setContentType("text/plain; charset=utf8");
			response.getWriter().println(callback + "(" + json + ")");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
