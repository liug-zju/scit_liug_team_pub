/*
 * 大类课程类
 * liug@ScitLiugTeam 2020.06.24
 */

package com.ScitLiugTeam.auto_scheduling;

import java.util.*;

public class CourseBase {
	//课程所需资源
	protected Set<Classroom.ClassroomRes> m_dResourcesNeeded = new HashSet<>();
	//课程名称
	private String m_sCourseBaseName;
	//课程ID
	private int m_nID;
}
