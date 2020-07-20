package com.ScitLiugTeam.StudentsPerfMgr;

import java.io.*;
import java.util.*;

public class PerfMgr {
	public enum CmdType{
		SLT_CMD_IMPORT, //����ѧ������
		SLT_CMD_PERF, //������ñ���
		SLT_CMD_OUTPUT, //����ɼ�
		SLT_CMD_QUIT, //�˳�
		
		SLT_CMD_NULL
	}
	
	private List<Student> m_vdStudents = new ArrayList<>();
	private Map<String, Integer> m_mpNameToId = new HashMap<>();
	private StudentsDbHelper m_dStudentsDB = new StudentsDbHelper();
	private Scanner m_dScanner = new Scanner(System.in);
	
	public void welcome() {
		System.out.println("***************************************************");
		System.out.println("*        SLT Students' Performance Manager        *");
		System.out.println("***************************************************");
		System.out.println("Author: LiuGang@SCIT(liug.gc@foxmail.com)");
	}

	public CmdType navigate() {
		System.out.print("����ѧ������[1]��������ñ���[2]������ɼ�[3]���˳�[Q]��");
		CmdType cmd = CmdType.SLT_CMD_NULL;
		String sInput = m_dScanner.next();
		if(sInput.length() > 1) {
			return CmdType.SLT_CMD_NULL;
		}
		int nChar = sInput.charAt(0);
		if(nChar == '1') {
			cmd = CmdType.SLT_CMD_IMPORT;
		}
		else if(nChar == '2') {
			cmd = CmdType.SLT_CMD_PERF;
		}
		else if(nChar == '3') {
			cmd = CmdType.SLT_CMD_OUTPUT;
		}
		else if(nChar == 'q' || nChar == 'Q') {
			cmd = CmdType.SLT_CMD_QUIT;
		}
		return cmd;
	}
	
	//���ļ��е��뵽���ݿ�
	public void importStudents() {
		if(m_vdStudents.size() > 0) {
			System.out.print("��⵽Ŀǰ�����ݣ�ȷ��Ҫɾ������������[Y/N]��");
			String sInput = m_dScanner.next();
			if(sInput.length() > 1) {
				return;
			}
			int nChar = sInput.charAt(0);
			if(nChar == 'y' || nChar == 'Y') {
				m_dStudentsDB.deleteAllData();
			}
			else {
				return;
			}
		}
		m_dStudentsDB.importStudents();
		m_vdStudents.clear();
		this.m_mpNameToId.clear();
		readStudents();
	}
	
	//�����ݿ����ȫ��ѧ���б�
	public void readStudents() {
		m_vdStudents = m_dStudentsDB.readStudents();
		for(Student student : m_vdStudents) {
			this.m_mpNameToId.put(student.m_sName, student.m_nId);
		}
	}
	
	//���й���Ŀ¼������csv�����ļ������Ե�students.csv)
	public List<String> listCsvFiles(){
		File filePath = new File(ConstDef.m_sWorkingPath);
		if(!filePath.exists() || !filePath.isDirectory()) {
			return null;
		}
		List<String> csvFiles = new ArrayList<>();
		String[] allFiles = filePath.list();
		for(String sFile : allFiles) {
			if(sFile.endsWith(".csv") && !sFile.startsWith("students")) {
				csvFiles.add(sFile);
			}
		}
		return csvFiles;
	}
	
	//���ļ��е�����ñ�������
	public void importPerf() {
		List<String> csvFiles = listCsvFiles();
		String sOutput = "��ѡ����Ҫ¼����ļ�(";
		int nNum = 1;
		Map<Integer, String> mpIntToFileName = new HashMap<>();
		for(String sFile : csvFiles) {
			sOutput += sFile.substring(0, sFile.length() - 4);
			sOutput += "[";
			sOutput += nNum;
			sOutput += "] ";
			mpIntToFileName.put(nNum, sFile);
			++nNum;
		}
		sOutput += ")��";
		System.out.print(sOutput);
		Integer nChoise = m_dScanner.nextInt();
		String sFileChoisen = ConstDef.m_sWorkingPath + mpIntToFileName.get(nChoise);
		System.out.println("���ڵ��룺" + sFileChoisen);
		importPerf(sFileChoisen);
	}
	
	public void importPerf(String sFile) {
		List<Performance> performances = new ArrayList<>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(sFile)));
			String sLine;
			Calendar lessonBegTime = Calendar.getInstance();
			WeMeetHandler wemeetHandler = new WeMeetHandler();
			boolean bWemeet = false;
			while((sLine = bufferedReader.readLine()) != null) {
				String[] vs = sLine.split(",");
				if(vs.length > 1) {
					if(vs[0].equals("R") || vs[0].equals("NR")) {
						bWemeet = (vs[0].equals("R"));
						String[] vsDatetime = vs[1].split("\\D");
						if(vsDatetime.length >= 5) {
							int nYear = Integer.valueOf(vsDatetime[0]);
							int nMonth = Integer.valueOf(vsDatetime[1]) - 1;
							int nDay = Integer.valueOf(vsDatetime[2]);
							int nHour = Integer.valueOf(vsDatetime[3]);
							int nMin = Integer.valueOf(vsDatetime[4]);
							int nSec = 0;
							lessonBegTime.set(nYear, nMonth, nDay, nHour, nMin, nSec);
						}
					}
					else if(vs[0].equals("ȱ��")) {
						List<Integer> vAbsent = new ArrayList<>();
						for(int i = 1; i < vs.length; ++i) {
							vAbsent.add(this.m_mpNameToId.get(vs[i]));
						}
						if(bWemeet) {
							String sAttendances = wemeetHandler.queryAttendance(lessonBegTime);
							for(Student student : m_vdStudents) {
								if(!sAttendances.contains(student.m_sName)) {
									vAbsent.add(student.m_nId);
								}
							}
						}
						for(Integer nID : vAbsent) {
							Performance performance = new Performance();
							performance.m_nStudentId = nID;
							performance.setLessonDatetime(lessonBegTime);
							performance.m_bAbsent = true;
							performances.add(performance);
						}
					}
					else if(vs[0].equals("����")) {
						if(vs.length >= 3) {
							Performance performance = new Performance();
							performance.m_nStudentId = this.m_mpNameToId.get(vs[1]);
							performance.setLessonDatetime(lessonBegTime);
							performance.m_nAddScore = Integer.valueOf(vs[2]);
							if(vs.length >= 4) {
								performance.m_sReason = vs[3];
							}
							performances.add(performance);
						}
					}
				}
			}
			bufferedReader.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		//Write into database
		m_dStudentsDB.writePerformances(performances);
	}
	
	public void output() {
		List<Performance> performances = m_dStudentsDB.readPerformances();
		//��һ���ж��ٽڿ�
		Set<Calendar> stDates = new HashSet<>();
		Map<Integer, Integer> mpScores = new HashMap<>();
		for(Performance perf : performances) {
			if(!stDates.contains(perf.m_dLessonDatetime)) {
				stDates.add(perf.m_dLessonDatetime);
			}
			int nScore = 0;
			if(mpScores.containsKey(perf.m_nStudentId)) {
				nScore = mpScores.get(perf.m_nStudentId);
			}
			if(perf.m_bAbsent) {
				nScore -= ConstDef.m_nLessonScore;
			}
			nScore += perf.m_nAddScore;
			mpScores.put(perf.m_nStudentId, nScore);
		}
		int nTotalScore = stDates.size() * ConstDef.m_nLessonScore;
		for(Student student : m_vdStudents) {
			if(mpScores.containsKey(student.m_nId)) {
				student.m_nScore = nTotalScore + mpScores.get(student.m_nId);
			}
			else {
				student.m_nScore = nTotalScore;
			}
		}
		//��ӡ
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(ConstDef.m_sWorkingPath + "out.csv"));
			for(Student student : m_vdStudents) {
				String sLine = student.m_sClass + "," + student.m_sSchoolId + "," + student.m_sName + "," + student.m_nScore;
				pw.println(sLine);
			}
			pw.flush();
			pw.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void finalize() {
		m_dStudentsDB.close();
		m_dScanner.close();
	}
	
	public static void main(String[] args) {
		PerfMgr mgr = new PerfMgr();
		mgr.readStudents();
		mgr.welcome();
		while(true) {
			CmdType cmd = mgr.navigate();
			switch(cmd) {
			case SLT_CMD_IMPORT: mgr.importStudents(); break;
			case SLT_CMD_PERF: mgr.importPerf(); break;
			case SLT_CMD_OUTPUT: mgr.output(); break;
			case SLT_CMD_QUIT: return;
			default: break;
			}
		}
	}

}
