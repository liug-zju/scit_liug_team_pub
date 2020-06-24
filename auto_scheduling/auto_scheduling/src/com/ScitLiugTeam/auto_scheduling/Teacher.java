/*
 * 教师类
 * liug@ScitLiugTeam 2020.06.24
 */

package com.ScitLiugTeam.auto_scheduling;

import java.util.*;

public class Teacher {
	public enum TeacherTend{
		SLT_TT_CONCENT, //尽可能集中
		SLT_TT_SCATTER, //尽可能分散
		SLT_TT_UNDEF //无所谓
	}
	public enum SkipHours{
		SLT_SH_MONDAY,  //不喜欢周一上课
		SLT_SH_TUESDAY,  //不喜欢周二上课
		SLT_SH_WEDNESDAY,  //不喜欢周三上课
		SLT_SH_THURSDAY,  //不喜欢周四上课
		SLT_SH_FRIDAY,  //不喜欢周五上课
		SLT_SH_SATURDAY,  //不喜欢周六上课
		SLT_SH_NIGHT,  //不喜欢晚上上课
		SLT_SH_UNDEF  //无所谓
	}
	//老师的编号
	private int m_nID;
	//老师的工号
	private String m_sEmployeeNum;
	//老师的名字
	private String m_sName;
	//常带课程，参数是课程的ID。这里的课程是Course，不是CourseBase
	private Set<Integer> m_stRegCourses = new HashSet<>();
	//可带课程。Key是CourseBase的ID，Value是老师对该课程的熟悉程度打分，从0到1。如果文件中没有，我们就按照文件排列顺序，最前的1，最末的0，中间插值
	private Map<Integer, Double> m_mpAvaCourses = new HashMap<>(); 
	//老师的倾向
	private TeacherTend m_dTend = TeacherTend.SLT_TT_UNDEF;
	private Set<SkipHours> m_stSkipHours = new HashSet<>();
}
