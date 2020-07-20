package com.ScitLiugTeam.StudentsPerfMgr;

import java.util.Calendar;

public class Performance {
	public int m_nStudentId;
	public Calendar m_dLessonDatetime;
	public boolean m_bAbsent;
	public int m_nAddScore;
	public String m_sReason;
	
	public Performance() {
		m_bAbsent = false;
		m_nAddScore = 0;
		m_dLessonDatetime = Calendar.getInstance();
	}
	
	public boolean equals(Performance perf) {
		if(m_nStudentId != perf.m_nStudentId) {
			return false;
		}
		if(m_dLessonDatetime != perf.m_dLessonDatetime) {
			return false;
		}
		return true;
	}
	
	public void setLessonDatetime(Calendar datetime) {
		this.m_dLessonDatetime.setTime(datetime.getTime());
	}
}
