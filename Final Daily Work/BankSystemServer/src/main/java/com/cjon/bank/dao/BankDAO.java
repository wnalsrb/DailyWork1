package com.cjon.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cjon.bank.dto.AccountDTO;
import com.cjon.bank.dto.BankDTO;

public class BankDAO {
	
	private Connection con;
	
	public BankDAO (Connection con) {
		this.con = con;
	}
	
	//1. selectAll - end
	public ArrayList<BankDTO> selectAll() {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BankDTO> list = new ArrayList<BankDTO>();
		
		try {
			
			String sql = "select* from bank_member_tb";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				BankDTO dto = new BankDTO();
				dto.setMember_id(rs.getString("member_id"));
				dto.setMember_name(rs.getString("member_name"));
				dto.setMember_account(rs.getString("member_account"));
				dto.setMember_balance(rs.getInt("member_balance"));
				list.add(dto);
			}
		} catch (Exception e) {
			
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	
	//2. selectID
	public ArrayList<BankDTO> selectId(String member_id) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BankDTO> list = new ArrayList<BankDTO>();
		
		String sql = "select* from bank_member_tb where member_id = ?";
		System.out.println(member_id);
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				BankDTO dto = new BankDTO();
				dto.setMember_id(rs.getString("member_id"));
				dto.setMember_name(rs.getString("member_name"));
				dto.setMember_account(rs.getString("member_account"));
				dto.setMember_balance(rs.getInt("member_balance"));
				list.add(dto);
			}
		} catch (Exception e) {
			
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		System.out.println(list.size());
		return list;
	}
	
	
	//3. deposit - end
	public boolean updateDeposit(String member_id, String member_balance) {
		PreparedStatement pstmt = null;
		boolean result = false;
		
		String sql = "update bank_member_tb set member_balance = member_balance + ? where member_id = ?";
	
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(member_balance));
			pstmt.setString(2, member_id);
			
			int count = pstmt.executeUpdate();
			
			if(count == 1){
				
				String sql1 = "insert into bank_statement_tb values (HISTORY_SQ, ?,'deposit',?)";
				

				PreparedStatement pstmt2 = con.prepareStatement(sql1);
				pstmt2.setString(1, member_id);
				pstmt2.setInt(2, Integer.parseInt(member_balance));
				int count2  = pstmt2.executeUpdate();
				System.out.println(count2); 
				if (count2 == 0) {

					result=false;

				} else {
					result=true;
				}
				try {
					pstmt2.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}else {
				result = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally { 
			try {
				pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	//4. withdraw - end
	public boolean updateWithdraw(String member_id, String member_balance) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		String sql = "update bank_member_tb set member_balance = member_balance - ? where member_id = ? and member_balance > ?";
	
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(member_balance));
			pstmt.setString(2, member_id);
			pstmt.setInt(3, Integer.parseInt(member_balance));
			int count = pstmt.executeUpdate();
			
			if(count == 1){
				
				String sql1 = "insert into bank_statement_tb values (HISTORY_SQ, ?,'deposit',?)";
				

				PreparedStatement pstmt2 = con.prepareStatement(sql1);
				pstmt2.setString(1, member_id);
				pstmt2.setInt(2, Integer.parseInt(member_balance));
				int count2  = pstmt2.executeUpdate();
				System.out.println(count2); 
				if (count2 == 0) {

					result=false;

				} else {
					result=true;
				}
				try {
					pstmt2.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}else {
				result = false;
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally { 
			try {
				pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return result;
	}
	
	//5. transfer	
	public boolean updateTransfer(String send_id, String receive_id, String transfer_money) {
		PreparedStatement pstmt = null;
		boolean result = false;
		
		String sql = "update bank_member_tb (set member_balance = member_balance - ? where member_id = ? and member_balance > ?) and (set member_balance = member_balance + ? where member_id = ?)";
	
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(transfer_money));
			pstmt.setString(2, transfer_money);
			pstmt.setString(3, send_id);
			pstmt.setInt(4, Integer.parseInt(transfer_money));
			pstmt.setString(5, receive_id);
			
			int count = pstmt.executeUpdate();
			
			if(count == 1){
				result = true;
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally { 
			try {
				pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	public ArrayList<AccountDTO> selectId_2(String member_id) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<AccountDTO> list = new ArrayList<AccountDTO>();
		try {

			String sql = "select * from bank_statement_tb where member_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				AccountDTO dto = new AccountDTO();

				dto.setMemberId(rs.getString("member_id"));
				dto.setKind(rs.getString("kind"));
				dto.setMoney(rs.getInt("money"));
				list.add(dto);
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
