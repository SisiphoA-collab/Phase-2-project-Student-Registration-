package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dbconn.DbConnection;
import com.model.Student;

public class StudentOperations {

	private Connection conobj= null;
	
	

	

	public StudentOperations() {
		super();
		conobj= DbConnection.getConnection();
	}
	
	public String AddNewStudent(Student std)
	{
		String res="Error";
		
		try
		{
			String inscmd="Insert into Student(stdname,course,fees,email,pwd) value(?,?,?,?,?)";
			
			PreparedStatement ps= conobj.prepareStatement(inscmd);
			
			ps.setString(1, std.getStdname());
			ps.setString(2, std.getCourse());
			ps.setFloat(3, std.getFees());
			ps.setString(4, std.getEmail());
			ps.setString(5, std.getPwd());
			
			int r=ps.executeUpdate();
			
			if(r>=1)
			{
				res="success";
			}
		}
		catch(Exception e)
		{
			res=e.getMessage();
			e.printStackTrace();
		}
		
		return res;
	}
	
	public List<Student> showAll()
	{
		List<Student> sall = new ArrayList();
		
		Student std=null;
		
		try
		{
			PreparedStatement ps = conobj.prepareStatement("select * from Student");
			ResultSet rs= ps.executeQuery();
			
			while(rs.next())
			{
				std= new Student();
				
				std.setRollno(rs.getInt("rollno"));
				std.setStdname(rs.getString("stdname"));
				std.setCourse(rs.getString("course"));
				std.setFees(rs.getFloat("fees"));
				std.setEmail(rs.getString("email"));
				std.setPwd(rs.getString("pwd"));
				
				sall.add(std);
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return sall;
	}
	
	public Student SearchStudent(int rollno)
	{
		Student std= null;
		
		try
		{
			PreparedStatement ps= conobj.prepareStatement("select * from Student where rollno=?");
			ps.setInt(1, rollno);
			ResultSet rs= ps.executeQuery();
			
			if(rs.next())
			{
				std= new Student();
				std.setRollno(rs.getInt("rollno"));
				std.setStdname(rs.getString("stdname"));
				std.setCourse(rs.getString("course"));
				std.setFees(rs.getFloat("fees"));
				std.setEmail(rs.getString("email"));
				std.setPwd(rs.getString("pwd"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return std;
	}
	
	
	public String DeleteStudent(int rollno)
	{
		String res="Error";
		try
		{
			String delcmd= "delete from Student where rollno=?";
			
			PreparedStatement ps= conobj.prepareStatement(delcmd);
			ps.setInt(1, rollno);
			
			int r=ps.executeUpdate();
			
			if(r>=1)
			{
				res="Success";
			}
		}
		catch(Exception e)
		{
			res=e.getMessage();
			e.printStackTrace();
		}
		
		return res;
	}
	
	public String UpdateStudent(Student std)
	{
		String res="Error";
		
		try
		{
			String uptcmd= "update Student set stdname=? where rollno=?";
			
			PreparedStatement ps = conobj.prepareStatement(uptcmd);
			
			ps.setString(1, std.getStdname());
			ps.setInt(2, std.getRollno());
			
			int r=ps.executeUpdate();
			if(r>=1)
			{
				res="Success";
			}
		}
		catch(Exception e)
		{
			res=e.getMessage();
			e.printStackTrace();
		}
		
		return res;
	}
	
	public Student CheckUser(String uname, String pwd)
	{
		Student std= null;
		
		try
		{
			PreparedStatement ps = conobj.prepareStatement("select * from Student where email=? and pwd=?");
			
			ps.setString(1, uname);
			ps.setString(2, pwd);
			
			ResultSet rs= ps.executeQuery();
			
			if(rs.next())
			{
				std=new Student();
				std.setRollno(rs.getInt("rollno"));
				std.setStdname(rs.getString("stdname"));
				std.setCourse(rs.getString("course"));
				std.setFees(rs.getFloat("fees"));
				std.setEmail(rs.getString("email"));
				std.setPwd(rs.getString("pwd"));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return std;
	}
}
