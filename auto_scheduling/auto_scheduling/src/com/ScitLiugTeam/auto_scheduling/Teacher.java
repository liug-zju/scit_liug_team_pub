/*
 * ��ʦ��
 * liug@ScitLiugTeam 2020.06.24
 */

package com.ScitLiugTeam.auto_scheduling;

import java.util.*;

public class Teacher {
	public enum TeacherTend{
		SLT_TT_CONCENT, //�����ܼ���
		SLT_TT_SCATTER, //�����ܷ�ɢ
		SLT_TT_UNDEF //����ν
	}
	public enum SkipHours{
		SLT_SH_MONDAY,  //��ϲ����һ�Ͽ�
		SLT_SH_TUESDAY,  //��ϲ���ܶ��Ͽ�
		SLT_SH_WEDNESDAY,  //��ϲ�������Ͽ�
		SLT_SH_THURSDAY,  //��ϲ�������Ͽ�
		SLT_SH_FRIDAY,  //��ϲ�������Ͽ�
		SLT_SH_SATURDAY,  //��ϲ�������Ͽ�
		SLT_SH_NIGHT,  //��ϲ�������Ͽ�
		SLT_SH_UNDEF  //����ν
	}
	//��ʦ�ı��
	private int m_nID;
	//��ʦ�Ĺ���
	private String m_sEmployeeNum;
	//��ʦ������
	private String m_sName;
	//�����γ̣������ǿγ̵�ID������Ŀγ���Course������CourseBase
	private Set<Integer> m_stRegCourses = new HashSet<>();
	//�ɴ��γ̡�Key��CourseBase��ID��Value����ʦ�Ըÿγ̵���Ϥ�̶ȴ�֣���0��1������ļ���û�У����ǾͰ����ļ�����˳����ǰ��1����ĩ��0���м��ֵ
	private Map<Integer, Double> m_mpAvaCourses = new HashMap<>(); 
	//��ʦ������
	private TeacherTend m_dTend = TeacherTend.SLT_TT_UNDEF;
	private Set<SkipHours> m_stSkipHours = new HashSet<>();
}
