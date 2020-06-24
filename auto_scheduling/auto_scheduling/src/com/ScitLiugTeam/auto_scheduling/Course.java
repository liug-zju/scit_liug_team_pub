/*
 * 具体课程类
 * liug@ScitLiugTeam 2020.06.24
 */

package com.ScitLiugTeam.auto_scheduling;

public class Course extends CourseBase {
	public enum CourseType{
		SLT_CT_PUBCOM, //公共必修课
		SLT_CT_PUBELE, //公共选修课
		SLT_CT_PROCOM, //专业必修课
		SLT_CT_PROELE  //专业选修课
	}
	
	//本课程编号
	private int m_nID;
	//课程名，比如：大学物理B1
	private String m_sName;
	//周学时
	private int m_nWeeklyClassHours;
	//本学期总学时
	private int m_nTotalClassHours;
	//课程类型
	private CourseType m_dCourseType;
}
