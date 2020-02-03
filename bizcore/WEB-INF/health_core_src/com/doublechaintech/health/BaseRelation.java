/*
******************************           DO NOT EDIT THIS FILE!!!           				*********************************
******************************         Please edit CustomRelation.java instead!        		*********************************	
******************************         不要编辑这个文件，这个文件每次都会被机器人覆盖!!!              *********************************
******************************    CustomRelation.java专门用于定制，该文件存在的时候不会被覆盖      	*********************************


*/
package com.doublechaintech.health;
import java.util.HashMap;
import java.util.Map;

public class BaseRelation{

	
	Map<String, String> relationMapping ;
	
	public String getRelation(String fromType, String fromId, String targetField, String targetId)
	{
		//the entry for external calls, ugly code with many path just works, using a map is fine but lose the way to override the methods
		
		if(relationMapping == null){
			prepareRelation();
		}

		String key = fromType+"->"+targetField;
		
		String relation = relationMapping.get(key);
		if(relation == null){
			throw new IllegalArgumentException("Not able to find any relation to the target type: "+ targetField);
		}
		return relation;
		
	}
	
	protected void addGenericRelation(String fromType, String relation,String targetField)
	{
		if(relationMapping == null){
			relationMapping = new HashMap<String,String>();
		}
		String key = fromType.trim()+"->"+targetField.trim();
		relationMapping.put(key, relation);
	}
	protected void replaceGenericRelation(String fromType, String relation,String targetField)
	{
		addGenericRelation( fromType, relation, targetField );
	}
	
	
	
	Map<String, String[]> relationIndex ;
	protected void addRelationIndex(String fromType,String related[])
	{
		if(relationIndex == null){
			relationIndex = new HashMap<String,String[]>();
		}
		
		relationIndex.put(fromType, related);
	}
	protected void replaceRelationIndex(String fromType,String related[])
	{
		addRelationIndex( fromType, related);
	}
	
	public String getTableFieldName(String expr){
		//the expr looks like owner:DecorationAccelerator
		String[] splitedValues = expr.split(":");
		if(splitedValues.length < 1){
			throw new IllegalArgumentException("Not able to split expr: "+expr);
		}
		
		return splitedValues[0];
	}
	public String getBeanFieldName(String expr){
		//the expr looks like owner:DecorationAccelerator
		String[] splitedValues = getTableFieldName(expr).split("_");
		String ret = splitedValues[0];
		for(int i=1;i<splitedValues.length;i++){
			
			ret = ret+splitedValues[i].substring(0,1).toUpperCase()+splitedValues[i].substring(1);
			
		}
		
		return ret;
	}
	public String getFieldType(String expr){
		//the expr looks like owner:DecorationAccelerator
		String[] splitedValues = expr.split(":");
		if(splitedValues.length < 2){
			throw new IllegalArgumentException("Not able to split expr: "+expr);
		}
		
		return splitedValues[1];
	}
	public String [] getRelationIndex(String type){
		
		if(relationIndex == null){
			prepareRelationIndex();
		}
		
		String relations [] =relationIndex.get(type);
		if(relations == null){
			//throw new IllegalArgumentException("Not able to find related objects for type: "+ type);
		}
		return relations;
	}
	
	protected void prepareRelationIndex()
	{
		
		
		String [] provinceRelatedObjectNames = {"platform:Platform"};
		addRelationIndex("Province",provinceRelatedObjectNames);

		String [] cityRelatedObjectNames = {"province:Province","platform:Platform"};
		addRelationIndex("City",cityRelatedObjectNames);

		String [] districtRelatedObjectNames = {"city:City","platform:Platform"};
		addRelationIndex("District",districtRelatedObjectNames);

		String [] locationRelatedObjectNames = {"district:District","province:Province"};
		addRelationIndex("Location",locationRelatedObjectNames);

		String [] teacherRelatedObjectNames = {"platform:Platform","user:User","change_request:ChangeRequest"};
		addRelationIndex("Teacher",teacherRelatedObjectNames);

		String [] studentRelatedObjectNames = {"address:Location","user:User","platform:Platform","change_request:ChangeRequest"};
		addRelationIndex("Student",studentRelatedObjectNames);

		String [] questionRelatedObjectNames = {"question_type:QuestionType","platform:Platform","creator:User","cq:ChangeRequest"};
		addRelationIndex("Question",questionRelatedObjectNames);

		String [] questionTypeRelatedObjectNames = {"platform:Platform"};
		addRelationIndex("QuestionType",questionTypeRelatedObjectNames);

		String [] classDailyHealthSurveyRelatedObjectNames = {"teacher:Teacher","creator:User","change_request:ChangeRequest"};
		addRelationIndex("ClassDailyHealthSurvey",classDailyHealthSurveyRelatedObjectNames);

		String [] dailySurveyQuestionRelatedObjectNames = {"question_type:QuestionType","class_daily_health_survey:ClassDailyHealthSurvey","survey_question:Question"};
		addRelationIndex("DailySurveyQuestion",dailySurveyQuestionRelatedObjectNames);

		String [] studentHealthSurveyRelatedObjectNames = {"student:Student","survey_status:SurveyStatus","teacher:Teacher","class_daily_health_survey:ClassDailyHealthSurvey","change_request:ChangeRequest"};
		addRelationIndex("StudentHealthSurvey",studentHealthSurveyRelatedObjectNames);

		String [] studentDailyAnswerRelatedObjectNames = {"student_health_survey:StudentHealthSurvey","question:DailySurveyQuestion","change_request:ChangeRequest"};
		addRelationIndex("StudentDailyAnswer",studentDailyAnswerRelatedObjectNames);

		String [] surveyStatusRelatedObjectNames = {"platform:Platform"};
		addRelationIndex("SurveyStatus",surveyStatusRelatedObjectNames);

		String [] healthSurveyReportRelatedObjectNames = {"student:Student","teacher:Teacher","survey:ClassDailyHealthSurvey"};
		addRelationIndex("HealthSurveyReport",healthSurveyReportRelatedObjectNames);

		String [] studentAnswerRelatedObjectNames = {"health_survey_report:HealthSurveyReport","daily_answer:StudentDailyAnswer"};
		addRelationIndex("StudentAnswer",studentAnswerRelatedObjectNames);

		String [] userRelatedObjectNames = {"address:Location","platform:Platform"};
		addRelationIndex("User",userRelatedObjectNames);

		String [] wechatLoginInfoRelatedObjectNames = {"user:User"};
		addRelationIndex("WechatLoginInfo",wechatLoginInfoRelatedObjectNames);

		String [] changeRequestRelatedObjectNames = {"request_type:ChangeRequestType","platform:Platform"};
		addRelationIndex("ChangeRequest",changeRequestRelatedObjectNames);

		String [] changeRequestTypeRelatedObjectNames = {"platform:Platform"};
		addRelationIndex("ChangeRequestType",changeRequestTypeRelatedObjectNames);

		String [] userWhiteListRelatedObjectNames = {"domain:UserDomain"};
		addRelationIndex("UserWhiteList",userWhiteListRelatedObjectNames);

		String [] secUserRelatedObjectNames = {"domain:UserDomain"};
		addRelationIndex("SecUser",secUserRelatedObjectNames);

		String [] userAppRelatedObjectNames = {"sec_user:SecUser"};
		addRelationIndex("UserApp",userAppRelatedObjectNames);

		String [] quickLinkRelatedObjectNames = {"app:UserApp"};
		addRelationIndex("QuickLink",quickLinkRelatedObjectNames);

		String [] listAccessRelatedObjectNames = {"app:UserApp"};
		addRelationIndex("ListAccess",listAccessRelatedObjectNames);

		String [] objectAccessRelatedObjectNames = {"app:UserApp"};
		addRelationIndex("ObjectAccess",objectAccessRelatedObjectNames);

		String [] loginHistoryRelatedObjectNames = {"sec_user:SecUser"};
		addRelationIndex("LoginHistory",loginHistoryRelatedObjectNames);

		String [] formMessageRelatedObjectNames = {"form:GenericForm"};
		addRelationIndex("FormMessage",formMessageRelatedObjectNames);

		String [] formFieldMessageRelatedObjectNames = {"form:GenericForm"};
		addRelationIndex("FormFieldMessage",formFieldMessageRelatedObjectNames);

		String [] formFieldRelatedObjectNames = {"form:GenericForm"};
		addRelationIndex("FormField",formFieldRelatedObjectNames);

		String [] formActionRelatedObjectNames = {"form:GenericForm"};
		addRelationIndex("FormAction",formActionRelatedObjectNames);

		String [] candidateElementRelatedObjectNames = {"container:CandidateContainer"};
		addRelationIndex("CandidateElement",candidateElementRelatedObjectNames);

		String [] wechatWorkappIdentifyRelatedObjectNames = {"sec_user:SecUser"};
		addRelationIndex("WechatWorkappIdentify",wechatWorkappIdentifyRelatedObjectNames);

		String [] wechatMiniappIdentifyRelatedObjectNames = {"sec_user:SecUser"};
		addRelationIndex("WechatMiniappIdentify",wechatMiniappIdentifyRelatedObjectNames);

	
	
	}
	protected static final String TRUST_CHAIN_READ = "R";
	protected static final String TRUST_CHAIN_WRITE = "W";
	protected static final String TRUST_CHAIN_MANAGEMENT = "M";
	protected static final String TRUST_CHAIN_EXECUTION = "X";
	
	protected static final String TRUST_READ = "r";
	protected static final String TRUST_WRITE = "w";
	protected static final String TRUST_MANAGEMENT = "m";
	protected static final String TRUST_EXECUTION = "x";
	
	protected static final String TRUST_CHAIN_ALL = "MXWR";
	
	
	//small 'r','w','m','x' mean no chain trust, just trust the same level
	//default for reading trust chain, the default sequence are MXWR, the order is not affect the result
	protected void prepareRelation()
	{
		addGenericRelation("Province"                              ,TRUST_CHAIN_READ,"platform");
		addGenericRelation("City"                                  ,TRUST_CHAIN_READ,"province");
		addGenericRelation("City"                                  ,TRUST_CHAIN_READ,"platform");
		addGenericRelation("District"                              ,TRUST_CHAIN_READ,"city");
		addGenericRelation("District"                              ,TRUST_CHAIN_READ,"platform");
		addGenericRelation("Location"                              ,TRUST_CHAIN_READ,"district");
		addGenericRelation("Location"                              ,TRUST_CHAIN_READ,"province");
		addGenericRelation("Teacher"                               ,TRUST_CHAIN_READ,"platform");
		addGenericRelation("Teacher"                               ,TRUST_CHAIN_READ,"user");
		addGenericRelation("Teacher"                               ,TRUST_CHAIN_READ,"changeRequest");
		addGenericRelation("Student"                               ,TRUST_CHAIN_READ,"address");
		addGenericRelation("Student"                               ,TRUST_CHAIN_READ,"user");
		addGenericRelation("Student"                               ,TRUST_CHAIN_READ,"platform");
		addGenericRelation("Student"                               ,TRUST_CHAIN_READ,"changeRequest");
		addGenericRelation("Question"                              ,TRUST_CHAIN_READ,"questionType");
		addGenericRelation("Question"                              ,TRUST_CHAIN_READ,"platform");
		addGenericRelation("Question"                              ,TRUST_CHAIN_READ,"creator");
		addGenericRelation("Question"                              ,TRUST_CHAIN_READ,"cq");
		addGenericRelation("QuestionType"                          ,TRUST_CHAIN_READ,"platform");
		addGenericRelation("ClassDailyHealthSurvey"                ,TRUST_CHAIN_READ,"teacher");
		addGenericRelation("ClassDailyHealthSurvey"                ,TRUST_CHAIN_READ,"creator");
		addGenericRelation("ClassDailyHealthSurvey"                ,TRUST_CHAIN_READ,"changeRequest");
		addGenericRelation("DailySurveyQuestion"                   ,TRUST_CHAIN_READ,"questionType");
		addGenericRelation("DailySurveyQuestion"                   ,TRUST_CHAIN_READ,"classDailyHealthSurvey");
		addGenericRelation("DailySurveyQuestion"                   ,TRUST_CHAIN_READ,"surveyQuestion");
		addGenericRelation("StudentHealthSurvey"                   ,TRUST_CHAIN_READ,"student");
		addGenericRelation("StudentHealthSurvey"                   ,TRUST_CHAIN_READ,"surveyStatus");
		addGenericRelation("StudentHealthSurvey"                   ,TRUST_CHAIN_READ,"teacher");
		addGenericRelation("StudentHealthSurvey"                   ,TRUST_CHAIN_READ,"classDailyHealthSurvey");
		addGenericRelation("StudentHealthSurvey"                   ,TRUST_CHAIN_READ,"changeRequest");
		addGenericRelation("StudentDailyAnswer"                    ,TRUST_CHAIN_READ,"studentHealthSurvey");
		addGenericRelation("StudentDailyAnswer"                    ,TRUST_CHAIN_READ,"question");
		addGenericRelation("StudentDailyAnswer"                    ,TRUST_CHAIN_READ,"changeRequest");
		addGenericRelation("SurveyStatus"                          ,TRUST_CHAIN_READ,"platform");
		addGenericRelation("HealthSurveyReport"                    ,TRUST_CHAIN_READ,"student");
		addGenericRelation("HealthSurveyReport"                    ,TRUST_CHAIN_READ,"teacher");
		addGenericRelation("HealthSurveyReport"                    ,TRUST_CHAIN_READ,"survey");
		addGenericRelation("StudentAnswer"                         ,TRUST_CHAIN_READ,"healthSurveyReport");
		addGenericRelation("StudentAnswer"                         ,TRUST_CHAIN_READ,"dailyAnswer");
		addGenericRelation("User"                                  ,TRUST_CHAIN_READ,"address");
		addGenericRelation("User"                                  ,TRUST_CHAIN_READ,"platform");
		addGenericRelation("WechatLoginInfo"                       ,TRUST_CHAIN_READ,"user");
		addGenericRelation("ChangeRequest"                         ,TRUST_CHAIN_READ,"requestType");
		addGenericRelation("ChangeRequest"                         ,TRUST_CHAIN_READ,"platform");
		addGenericRelation("ChangeRequestType"                     ,TRUST_CHAIN_READ,"platform");
		addGenericRelation("UserWhiteList"                         ,TRUST_CHAIN_READ,"domain");
		addGenericRelation("SecUser"                               ,TRUST_CHAIN_READ,"domain");
		addGenericRelation("UserApp"                               ,TRUST_CHAIN_READ,"secUser");
		addGenericRelation("QuickLink"                             ,TRUST_CHAIN_READ,"app");
		addGenericRelation("ListAccess"                            ,TRUST_CHAIN_READ,"app");
		addGenericRelation("ObjectAccess"                          ,TRUST_CHAIN_READ,"app");
		addGenericRelation("LoginHistory"                          ,TRUST_CHAIN_READ,"secUser");
		addGenericRelation("FormMessage"                           ,TRUST_CHAIN_READ,"form");
		addGenericRelation("FormFieldMessage"                      ,TRUST_CHAIN_READ,"form");
		addGenericRelation("FormField"                             ,TRUST_CHAIN_READ,"form");
		addGenericRelation("FormAction"                            ,TRUST_CHAIN_READ,"form");
		addGenericRelation("CandidateElement"                      ,TRUST_CHAIN_READ,"container");
		addGenericRelation("WechatWorkappIdentify"                 ,TRUST_CHAIN_READ,"secUser");
		addGenericRelation("WechatMiniappIdentify"                 ,TRUST_CHAIN_READ,"secUser");
	
	}

	


}


