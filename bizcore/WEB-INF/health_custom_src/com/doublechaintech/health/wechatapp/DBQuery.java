package com.doublechaintech.health.wechatapp;

import java.util.Optional;

import com.doublechaintech.health.CustomHealthUserContextImpl;
import com.doublechaintech.health.SmartList;

public class DBQuery extends WechatAppDBQueryHelper {

	
	public int countStudentHealthSurveyListOfStudentBySurveyId(CustomHealthUserContextImpl ctx, String surveyId) {
		try {
			return Optional.ofNullable(super.queryStudentHealthSurveyListOfStudentBySurveyId(ctx, surveyId)).map(SmartList::size).orElse(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
