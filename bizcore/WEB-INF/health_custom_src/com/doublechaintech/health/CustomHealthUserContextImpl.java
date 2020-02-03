package com.doublechaintech.health;

import java.util.Date;

import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.user.User;

public class CustomHealthUserContextImpl extends HealthBizUserContextImpl {

	private User currentUserInfo;

	private String schoolName;
	private String className;
	private int classSize;
	private String teacherName;
	
	private String surveyDate;
	private String teacherId;
	private String surveyId;
	
	private String studentName;
	private String studentNumber;
	private String studentAvatar;
	
	private StudentHealthSurvey studentSurvey;
	
	private String answer;
	
	
	


	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public StudentHealthSurvey getStudentSurvey() {
		return studentSurvey;
	}

	public void setStudentSurvey(StudentHealthSurvey studentSurvey) {
		this.studentSurvey = studentSurvey;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getStudentAvatar() {
		return studentAvatar;
	}

	public void setStudentAvatar(String studentAvatar) {
		this.studentAvatar = studentAvatar;
	}

	public String getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}


	public String getSurveyDate() {
		return surveyDate;
	}

	public void setSurveyDate(String surveyDate) {
		this.surveyDate = surveyDate;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getClassSize() {
		return classSize;
	}

	public void setClassSize(int classSize) {
		this.classSize = classSize;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public User getCurrentUserInfo() {
		return currentUserInfo;
	}

	public void setCurrentUserInfo(User currentUserInfo) {
		this.currentUserInfo = currentUserInfo;
	}




}
