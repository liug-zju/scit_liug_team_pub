/*
 * 教室类
 * liug@ScitLiugTeam 2020.06.24
 */

package com.ScitLiugTeam.auto_scheduling;

import java.util.HashSet;
import java.util.Set;

public class Classroom {
	//教室资源类型
	public enum ClassroomRes{
		SLT_CRES_BLACKBOARD, //黑板
		SLT_CRES_PROJECTOR, //投影仪
		SLT_CRES_SPEECH, //语音室
		SLT_CRES_PHYLAB, //物理实验
		SLT_CRES_COMPUTER, //电脑机房
		
		SLT_CRES_NUM //无意义
	}
	
	//教室资源
	private Set<ClassroomRes> m_stResources = new HashSet<ClassroomRes>();
	//教室名，比如：6-404
	private String m_sName;
	//教室ID
	private int m_nID;
	
	//TODO: 完善本类的数据读写，包括：从文件中读取全部教室资源，其他类访问接口
}
