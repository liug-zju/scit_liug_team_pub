package com.ScitLiugTeam.StudentsPerfMgr;

import java.sql.*;
import java.util.*;

public class WeMeetHandler {
	//返回包含全部出勤学生的长字符串
	public String queryAttendance(Calendar begTime) {
		long intBegTime = datetimeToInt(begTime);
		begTime.add(Calendar.HOUR, 2);
		long intEndTime = datetimeToInt(begTime);
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + ConstDef.m_sWeMeetDb);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String queryCmd = "SELECT content FROM message WHERE time>=";
			queryCmd += intBegTime;
			queryCmd += " AND time<=";
			queryCmd += intEndTime;
			queryCmd += ";";
			ResultSet rs = stmt.executeQuery(queryCmd);
			String allAttendance = "";
			while(rs.next()) {
				String content = rs.getString("content");
				String resString = processContent(content);
				//allAttendance += "\r\n";
				allAttendance += resString;
			}
			rs.close();
			stmt.close();
			conn.close();
			return allAttendance;
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return "";
	}
	//convert date to int
	private long datetimeToInt(Calendar dateTime) {
		return dateTime.getTime().getTime() / 1000;
	}
	//process the content to the desired string that contain the name of students
	private String processContent(String content) {
		if(content.contains("签到")) {
			int nPos = content.indexOf("签到");
			nPos = content.indexOf("\n", nPos);
			nPos += 1;
			nPos = content.indexOf("\n", nPos);
			nPos += 1;
			int nEndPos = content.indexOf("{\"chat_type\"", nPos);
			if(nEndPos > nPos) {
				return content.substring(nPos, nEndPos);
			}
			return content.substring(nPos);
		}
		return "";
	}
}
