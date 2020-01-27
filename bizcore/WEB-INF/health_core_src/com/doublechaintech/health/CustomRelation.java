
package com.doublechaintech.health;
import java.util.HashMap;
import java.util.Map;

public class CustomRelation extends BaseRelation{

	protected void prepareRelation()
	{
		super.prepareRelation();
		//Uncomment to make any change to the relation type
		//replaceGenericRelation("Province"                              , BaseRelation.TRUST_CHAIN_ALL, "platform");
		//replaceGenericRelation("City"                                  , BaseRelation.TRUST_CHAIN_ALL, "province");
		//replaceGenericRelation("City"                                  , BaseRelation.TRUST_CHAIN_ALL, "platform");
		//replaceGenericRelation("District"                              , BaseRelation.TRUST_CHAIN_ALL, "city");
		//replaceGenericRelation("District"                              , BaseRelation.TRUST_CHAIN_ALL, "platform");
		//replaceGenericRelation("Location"                              , BaseRelation.TRUST_CHAIN_ALL, "district");
		//replaceGenericRelation("Location"                              , BaseRelation.TRUST_CHAIN_ALL, "province");
		//replaceGenericRelation("Teacher"                               , BaseRelation.TRUST_CHAIN_ALL, "platform");
		//replaceGenericRelation("Teacher"                               , BaseRelation.TRUST_CHAIN_ALL, "cq");
		//replaceGenericRelation("Student"                               , BaseRelation.TRUST_CHAIN_ALL, "address");
		//replaceGenericRelation("Student"                               , BaseRelation.TRUST_CHAIN_ALL, "user");
		//replaceGenericRelation("Student"                               , BaseRelation.TRUST_CHAIN_ALL, "platform");
		//replaceGenericRelation("Student"                               , BaseRelation.TRUST_CHAIN_ALL, "cq");
		//replaceGenericRelation("Question"                              , BaseRelation.TRUST_CHAIN_ALL, "questionType");
		//replaceGenericRelation("Question"                              , BaseRelation.TRUST_CHAIN_ALL, "platform");
		//replaceGenericRelation("Question"                              , BaseRelation.TRUST_CHAIN_ALL, "creator");
		//replaceGenericRelation("Question"                              , BaseRelation.TRUST_CHAIN_ALL, "cq");
		//replaceGenericRelation("QuestionType"                          , BaseRelation.TRUST_CHAIN_ALL, "platform");
		//replaceGenericRelation("ClassDailyHealthSurvey"                , BaseRelation.TRUST_CHAIN_ALL, "teacher");
		//replaceGenericRelation("ClassDailyHealthSurvey"                , BaseRelation.TRUST_CHAIN_ALL, "creator");
		//replaceGenericRelation("ClassDailyHealthSurvey"                , BaseRelation.TRUST_CHAIN_ALL, "cq");
		//replaceGenericRelation("DailySurveyQuestion"                   , BaseRelation.TRUST_CHAIN_ALL, "questionType");
		//replaceGenericRelation("DailySurveyQuestion"                   , BaseRelation.TRUST_CHAIN_ALL, "classDailyHealthSurvey");
		//replaceGenericRelation("DailySurveyQuestion"                   , BaseRelation.TRUST_CHAIN_ALL, "surveyQuestion");
		//replaceGenericRelation("StudentHealthSurvey"                   , BaseRelation.TRUST_CHAIN_ALL, "student");
		//replaceGenericRelation("StudentHealthSurvey"                   , BaseRelation.TRUST_CHAIN_ALL, "surveyStatus");
		//replaceGenericRelation("StudentHealthSurvey"                   , BaseRelation.TRUST_CHAIN_ALL, "teacher");
		//replaceGenericRelation("StudentHealthSurvey"                   , BaseRelation.TRUST_CHAIN_ALL, "classDailyHealthSurvey");
		//replaceGenericRelation("StudentHealthSurvey"                   , BaseRelation.TRUST_CHAIN_ALL, "cq");
		//replaceGenericRelation("StudentDailyAnswer"                    , BaseRelation.TRUST_CHAIN_ALL, "studentHealthSurvey");
		//replaceGenericRelation("StudentDailyAnswer"                    , BaseRelation.TRUST_CHAIN_ALL, "question");
		//replaceGenericRelation("StudentDailyAnswer"                    , BaseRelation.TRUST_CHAIN_ALL, "cq");
		//replaceGenericRelation("SurveyStatus"                          , BaseRelation.TRUST_CHAIN_ALL, "platform");
		//replaceGenericRelation("User"                                  , BaseRelation.TRUST_CHAIN_ALL, "address");
		//replaceGenericRelation("User"                                  , BaseRelation.TRUST_CHAIN_ALL, "platform");
		//replaceGenericRelation("WechatLoginInfo"                       , BaseRelation.TRUST_CHAIN_ALL, "user");
		//replaceGenericRelation("ChangeRequest"                         , BaseRelation.TRUST_CHAIN_ALL, "requestType");
		//replaceGenericRelation("ChangeRequest"                         , BaseRelation.TRUST_CHAIN_ALL, "platform");
		//replaceGenericRelation("ChangeRequestType"                     , BaseRelation.TRUST_CHAIN_ALL, "platform");
		//replaceGenericRelation("UserWhiteList"                         , BaseRelation.TRUST_CHAIN_ALL, "domain");
		//replaceGenericRelation("SecUser"                               , BaseRelation.TRUST_CHAIN_ALL, "domain");
		//replaceGenericRelation("UserApp"                               , BaseRelation.TRUST_CHAIN_ALL, "secUser");
		//replaceGenericRelation("QuickLink"                             , BaseRelation.TRUST_CHAIN_ALL, "app");
		//replaceGenericRelation("ListAccess"                            , BaseRelation.TRUST_CHAIN_ALL, "app");
		//replaceGenericRelation("ObjectAccess"                          , BaseRelation.TRUST_CHAIN_ALL, "app");
		//replaceGenericRelation("LoginHistory"                          , BaseRelation.TRUST_CHAIN_ALL, "secUser");
		//replaceGenericRelation("FormMessage"                           , BaseRelation.TRUST_CHAIN_ALL, "form");
		//replaceGenericRelation("FormFieldMessage"                      , BaseRelation.TRUST_CHAIN_ALL, "form");
		//replaceGenericRelation("FormField"                             , BaseRelation.TRUST_CHAIN_ALL, "form");
		//replaceGenericRelation("FormAction"                            , BaseRelation.TRUST_CHAIN_ALL, "form");
		//replaceGenericRelation("CandidateElement"                      , BaseRelation.TRUST_CHAIN_ALL, "container");
		//replaceGenericRelation("WechatWorkappIdentify"                 , BaseRelation.TRUST_CHAIN_ALL, "secUser");
		//replaceGenericRelation("WechatMiniappIdentify"                 , BaseRelation.TRUST_CHAIN_ALL, "secUser");

	}
	
	protected void prepareRelationIndex()
	{
		super.prepareRelationIndex();
		/*
		
		Note: you could delete some of the possible relations if you do not want it.
		Just uncomment the definition line and replaceRelationIndex line to replace existing one.
		
		*/
		//String [] provinceRelatedObjectNames = {"platform:Platform"};
		//replaceRelationIndex("Province",provinceRelatedObjectNames);

		//String [] cityRelatedObjectNames = {"province:Province","platform:Platform"};
		//replaceRelationIndex("City",cityRelatedObjectNames);

		//String [] districtRelatedObjectNames = {"city:City","platform:Platform"};
		//replaceRelationIndex("District",districtRelatedObjectNames);

		//String [] locationRelatedObjectNames = {"district:District","province:Province"};
		//replaceRelationIndex("Location",locationRelatedObjectNames);

		//String [] teacherRelatedObjectNames = {"platform:Platform","cq:ChangeRequest"};
		//replaceRelationIndex("Teacher",teacherRelatedObjectNames);

		//String [] studentRelatedObjectNames = {"address:Location","user:User","platform:Platform","cq:ChangeRequest"};
		//replaceRelationIndex("Student",studentRelatedObjectNames);

		//String [] questionRelatedObjectNames = {"question_type:QuestionType","platform:Platform","creator:User","cq:ChangeRequest"};
		//replaceRelationIndex("Question",questionRelatedObjectNames);

		//String [] questionTypeRelatedObjectNames = {"platform:Platform"};
		//replaceRelationIndex("QuestionType",questionTypeRelatedObjectNames);

		//String [] classDailyHealthSurveyRelatedObjectNames = {"teacher:Teacher","creator:User","cq:ChangeRequest"};
		//replaceRelationIndex("ClassDailyHealthSurvey",classDailyHealthSurveyRelatedObjectNames);

		//String [] dailySurveyQuestionRelatedObjectNames = {"question_type:QuestionType","class_daily_health_survey:ClassDailyHealthSurvey","survey_question:Question"};
		//replaceRelationIndex("DailySurveyQuestion",dailySurveyQuestionRelatedObjectNames);

		//String [] studentHealthSurveyRelatedObjectNames = {"student:Student","survey_status:SurveyStatus","teacher:Teacher","class_daily_health_survey:ClassDailyHealthSurvey","cq:ChangeRequest"};
		//replaceRelationIndex("StudentHealthSurvey",studentHealthSurveyRelatedObjectNames);

		//String [] studentDailyAnswerRelatedObjectNames = {"student_health_survey:StudentHealthSurvey","question:DailySurveyQuestion","cq:ChangeRequest"};
		//replaceRelationIndex("StudentDailyAnswer",studentDailyAnswerRelatedObjectNames);

		//String [] surveyStatusRelatedObjectNames = {"platform:Platform"};
		//replaceRelationIndex("SurveyStatus",surveyStatusRelatedObjectNames);

		//String [] userRelatedObjectNames = {"address:Location","platform:Platform"};
		//replaceRelationIndex("User",userRelatedObjectNames);

		//String [] wechatLoginInfoRelatedObjectNames = {"user:User"};
		//replaceRelationIndex("WechatLoginInfo",wechatLoginInfoRelatedObjectNames);

		//String [] changeRequestRelatedObjectNames = {"request_type:ChangeRequestType","platform:Platform"};
		//replaceRelationIndex("ChangeRequest",changeRequestRelatedObjectNames);

		//String [] changeRequestTypeRelatedObjectNames = {"platform:Platform"};
		//replaceRelationIndex("ChangeRequestType",changeRequestTypeRelatedObjectNames);

		//String [] userWhiteListRelatedObjectNames = {"domain:UserDomain"};
		//replaceRelationIndex("UserWhiteList",userWhiteListRelatedObjectNames);

		//String [] secUserRelatedObjectNames = {"domain:UserDomain"};
		//replaceRelationIndex("SecUser",secUserRelatedObjectNames);

		//String [] userAppRelatedObjectNames = {"sec_user:SecUser"};
		//replaceRelationIndex("UserApp",userAppRelatedObjectNames);

		//String [] quickLinkRelatedObjectNames = {"app:UserApp"};
		//replaceRelationIndex("QuickLink",quickLinkRelatedObjectNames);

		//String [] listAccessRelatedObjectNames = {"app:UserApp"};
		//replaceRelationIndex("ListAccess",listAccessRelatedObjectNames);

		//String [] objectAccessRelatedObjectNames = {"app:UserApp"};
		//replaceRelationIndex("ObjectAccess",objectAccessRelatedObjectNames);

		//String [] loginHistoryRelatedObjectNames = {"sec_user:SecUser"};
		//replaceRelationIndex("LoginHistory",loginHistoryRelatedObjectNames);

		//String [] formMessageRelatedObjectNames = {"form:GenericForm"};
		//replaceRelationIndex("FormMessage",formMessageRelatedObjectNames);

		//String [] formFieldMessageRelatedObjectNames = {"form:GenericForm"};
		//replaceRelationIndex("FormFieldMessage",formFieldMessageRelatedObjectNames);

		//String [] formFieldRelatedObjectNames = {"form:GenericForm"};
		//replaceRelationIndex("FormField",formFieldRelatedObjectNames);

		//String [] formActionRelatedObjectNames = {"form:GenericForm"};
		//replaceRelationIndex("FormAction",formActionRelatedObjectNames);

		//String [] candidateElementRelatedObjectNames = {"container:CandidateContainer"};
		//replaceRelationIndex("CandidateElement",candidateElementRelatedObjectNames);

		//String [] wechatWorkappIdentifyRelatedObjectNames = {"sec_user:SecUser"};
		//replaceRelationIndex("WechatWorkappIdentify",wechatWorkappIdentifyRelatedObjectNames);

		//String [] wechatMiniappIdentifyRelatedObjectNames = {"sec_user:SecUser"};
		//replaceRelationIndex("WechatMiniappIdentify",wechatMiniappIdentifyRelatedObjectNames);

		
		
	
	}
	
	
	@Override
	public String getRelation(String fromType, String fromId, String targetField, String targetId)
	{

		String relation = super.getRelation(fromType, fromId, targetField, targetId);
		if(relation == null){
			throw new IllegalArgumentException("Not able to find any relation to the target type: "+ targetField);
		}
		return relation;
		
	}

}










