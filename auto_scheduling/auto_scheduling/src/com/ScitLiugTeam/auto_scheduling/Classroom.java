/*
 * ������
 * liug@ScitLiugTeam 2020.06.24
 */

package com.ScitLiugTeam.auto_scheduling;

import java.util.HashSet;
import java.util.Set;

public class Classroom {
	//������Դ����
	public enum ClassroomRes{
		SLT_CRES_BLACKBOARD, //�ڰ�
		SLT_CRES_PROJECTOR, //ͶӰ��
		SLT_CRES_SPEECH, //������
		SLT_CRES_PHYLAB, //����ʵ��
		SLT_CRES_COMPUTER, //���Ի���
		
		SLT_CRES_NUM //������
	}
	
	//������Դ
	private Set<ClassroomRes> m_stResources = new HashSet<ClassroomRes>();
	//�����������磺6-404
	private String m_sName;
	//����ID
	private int m_nID;
	
	//TODO: ���Ʊ�������ݶ�д�����������ļ��ж�ȡȫ��������Դ����������ʽӿ�
}
