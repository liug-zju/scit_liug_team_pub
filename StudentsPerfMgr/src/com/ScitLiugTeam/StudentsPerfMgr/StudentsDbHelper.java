package com.ScitLiugTeam.StudentsPerfMgr;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StudentsDbHelper {
	public Connection m_dConn = null;
	
	public StudentsDbHelper() {
		try {
			Class.forName("org.sqlite.JDBC");
			m_dConn = DriverManager.getConnection("jdbc:sqlite:" + ConstDef.m_sWorkingPath + ConstDef.m_sDbFileName);
			//m_dConn.setAutoCommit(false);
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	public void deleteAllData() {
		try {
			String sQueryCmd = "delete from students;";
			PreparedStatement sql = m_dConn.prepareStatement(sQueryCmd);
			sql.execute();
			sQueryCmd = "delete from scores;";
			sql = m_dConn.prepareStatement(sQueryCmd);
			sql.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void importStudents() {
		try {
			String sFilePath = ConstDef.m_sWorkingPath + "students.csv";
			File file = new File(sFilePath);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String sLine;
			while((sLine = bufferedReader.readLine()) != null) {
				String[] vs = sLine.split(",");
				if(vs.length < 3) {
					continue;
				}
				String sClass = vs[0];
				String sSchoolId = vs[1];
				String sName = vs[2];
				String queryCmd = "insert into students (school_id, name, class) values (?,?,?);";
				PreparedStatement sql = m_dConn.prepareStatement(queryCmd);
				sql.setString(1, sSchoolId);
				sql.setString(2, sName);
				sql.setString(3, sClass);
				sql.execute();
			}
			bufferedReader.close();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Student> readStudents(){
		List<Student> students = new ArrayList<>();
		try {
			Statement stmt = m_dConn.createStatement();
			String queryCmd = "SELECT * FROM students;";
			ResultSet rs = stmt.executeQuery(queryCmd);
			while(rs.next()) {
				Student student = new Student();
				student.m_nId = rs.getInt("id");
				student.m_sSchoolId = rs.getString("school_id");
				student.m_sName = rs.getString("name");
				student.m_sClass = rs.getString("class");
				students.add(student);
			}
			rs.close();
			stmt.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return students;
	}
	
	//向数据库中写入课堂表现数据
	public void writePerformances(List<Performance> performances) {
		try {
			String queryCmd = "insert into scores (student_id, lesson_datetime, absent, additional_score, reason) values (?,?,?,?,?);";
			PreparedStatement sql = m_dConn.prepareStatement(queryCmd);
			for(Performance perf : performances) {
				sql.setInt(1, perf.m_nStudentId);
				sql.setLong(2, datetimeToInt(perf.m_dLessonDatetime));
				sql.setBoolean(3, perf.m_bAbsent);
				sql.setInt(4, perf.m_nAddScore);
				sql.setString(5, perf.m_sReason);
				sql.execute();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//从数据库中读入课堂表现数据
	public List<Performance> readPerformances() {
		List<Performance> performances = new ArrayList<>();
		try {
			Statement stmt = m_dConn.createStatement();
			String queryCmd = "SELECT * FROM scores;";
			ResultSet rs = stmt.executeQuery(queryCmd);
			while(rs.next()) {
				Performance perf = new Performance();
				perf.m_nStudentId = rs.getInt("student_id");
				perf.m_dLessonDatetime = intToDatetime(rs.getLong("lesson_datetime"));
				perf.m_bAbsent = rs.getBoolean("absent");
				perf.m_nAddScore = rs.getInt("additional_score");
				performances.add(perf);
			}
			rs.close();
			stmt.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return performances;
	}
	
	//convert long to datetime
	private Calendar intToDatetime(long nDatetime) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(nDatetime * 1000);
		return calendar;
	}
	
	//convert date to int
	private long datetimeToInt(Calendar dateTime) {
		return dateTime.getTime().getTime() / 1000;
	}
	
 	public void close() {
		try {
			m_dConn.close();
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}
