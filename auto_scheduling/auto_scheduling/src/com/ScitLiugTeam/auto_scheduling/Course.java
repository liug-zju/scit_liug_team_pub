/*
 * ����γ���
 * liug@ScitLiugTeam 2020.06.24
 */

package com.ScitLiugTeam.auto_scheduling;

public class Course extends CourseBase {
	public enum CourseType{
		SLT_CT_PUBCOM, //�������޿�
		SLT_CT_PUBELE, //����ѡ�޿�
		SLT_CT_PROCOM, //רҵ���޿�
		SLT_CT_PROELE  //רҵѡ�޿�
	}
	
	//���γ̱��
	private int m_nID;
	//�γ��������磺��ѧ����B1
	private String m_sName;
	//��ѧʱ
	private int m_nWeeklyClassHours;
	//��ѧ����ѧʱ
	private int m_nTotalClassHours;
	//�γ�����
	private CourseType m_dCourseType;
}
