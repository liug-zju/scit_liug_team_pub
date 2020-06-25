/*
 * �Ŵ��㷨֮����ӿ�
 * ÿ���������һ���������У���Ӧ���ܹ�ֱ��ת��Ϊ����Ľ⡣�κ�ʵ�ָýӿڵľ����඼Ӧ��ʵ�ֶ�����Ļ�������Լ������������Ϊ�����
 * liug@ScitLiugTeam 2020.6.25
 */

package com.ScitLiugTeam.auto_scheduling;

public interface Individual {
	//�����ʼ��
	public void randInitialize();
	//��������һ������
	public void copyFrom(Individual ind);
	//�жϱ����������Ľ��Ƿ�Ϸ����Ϸ�����true�����Ϸ�false
	public boolean isLegal();
	//��������ĳ���
	public int length();
	//�������������Ϊ����Ľ�
	public Solution decode();
	//�����������֡����ǳ�ʼ�Ĵ��
	public void score();
	//�������������õ÷֡����Ǹ�����Ⱥ������������Է������й�һ��֮���������÷���
	public void setScore(double fScore);
	//��ȡ��������ĵ÷�
	public double getScore();
	//��ӡ��������Ϣ
	public String toString();
}
