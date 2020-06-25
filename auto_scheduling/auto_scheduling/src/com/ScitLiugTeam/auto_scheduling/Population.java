/*
 * ��Ⱥ
 * liug@ScitLiugTeam 2020.6.25
 */

package com.ScitLiugTeam.auto_scheduling;

import java.util.*;

public abstract class Population {
	//��Ⱥ��ȫ������
	protected List<Individual> m_vdIndividuals = new ArrayList<>();
	//���Ÿ���
	protected Individual m_dBestIndividual;
	
	//�Ը�����д��
	public abstract void score();
	//����ÿ������ĵ÷֣�ͨ�����̶ķ�ʽ����������һ��
	public abstract void generateNextGeneration();
	//��ȡ���Ÿ���
	public abstract Individual getBestIndividual();
}
