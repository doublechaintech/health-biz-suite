
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
		//replaceGenericRelation("SchoolClass"                           , BaseRelation.TRUST_CHAIN_ALL, "classTeacher");
		//replaceGenericRelation("SchoolClass"                           , BaseRelation.TRUST_CHAIN_ALL, "platform");
		//replaceGenericRelation("SchoolClass"                           , BaseRelation.TRUST_CHAIN_ALL, "cq");
		//replaceGenericRelation("Teacher"                               , BaseRelation.TRUST_CHAIN_ALL, "platform");
		//replaceGenericRelation("Teacher"                               , BaseRelation.TRUST_CHAIN_ALL, "cq");
		//replaceGenericRelation("Guardian"                              , BaseRelation.TRUST_CHAIN_ALL, "address");
		//replaceGenericRelation("Guardian"                              , BaseRelation.TRUST_CHAIN_ALL, "wechatUser");
		//replaceGenericRelation("Guardian"                              , BaseRelation.TRUST_CHAIN_ALL, "platform");
		//replaceGenericRelation("Guardian"                              , BaseRelation.TRUST_CHAIN_ALL, "cq");
		//replaceGenericRelation("Question"                              , BaseRelation.TRUST_CHAIN_ALL, "questionType");
		//replaceGenericRelation("Question"                              , BaseRelation.TRUST_CHAIN_ALL, "platform");
		//replaceGenericRelation("QuestionType"                          , BaseRelation.TRUST_CHAIN_ALL, "platform");
		//replaceGenericRelation("QuestionSource"                        , BaseRelation.TRUST_CHAIN_ALL, "platform");
		//replaceGenericRelation("ClassQuestion"                         , BaseRelation.TRUST_CHAIN_ALL, "questionType");
		//replaceGenericRelation("ClassQuestion"                         , BaseRelation.TRUST_CHAIN_ALL, "questionSource");
		//replaceGenericRelation("ClassQuestion"                         , BaseRelation.TRUST_CHAIN_ALL, "creator");
		//replaceGenericRelation("ClassQuestion"                         , BaseRelation.TRUST_CHAIN_ALL, "cq");
		//replaceGenericRelation("ClassDailyHealthSurvey"                , BaseRelation.TRUST_CHAIN_ALL, "schoolClass");
		//replaceGenericRelation("ClassDailyHealthSurvey"                , BaseRelation.TRUST_CHAIN_ALL, "creator");
		//replaceGenericRelation("ClassDailyHealthSurvey"                , BaseRelation.TRUST_CHAIN_ALL, "cq");
		//replaceGenericRelation("DailySurveyQuestion"                   , BaseRelation.TRUST_CHAIN_ALL, "questionType");
		//replaceGenericRelation("DailySurveyQuestion"                   , BaseRelation.TRUST_CHAIN_ALL, "classDailyHealthSurvey");
		//replaceGenericRelation("DailySurveyQuestion"                   , BaseRelation.TRUST_CHAIN_ALL, "classQuestion");
		//replaceGenericRelation("Student"                               , BaseRelation.TRUST_CHAIN_ALL, "guardian");
		//replaceGenericRelation("Student"                               , BaseRelation.TRUST_CHAIN_ALL, "schoolClass");
		//replaceGenericRelation("Student"                               , BaseRelation.TRUST_CHAIN_ALL, "cq");
		//replaceGenericRelation("StudentHealthSurvey"                   , BaseRelation.TRUST_CHAIN_ALL, "student");
		//replaceGenericRelation("StudentHealthSurvey"                   , BaseRelation.TRUST_CHAIN_ALL, "surveyStatus");
		//replaceGenericRelation("StudentHealthSurvey"                   , BaseRelation.TRUST_CHAIN_ALL, "schoolClass");
		//replaceGenericRelation("StudentHealthSurvey"                   , BaseRelation.TRUST_CHAIN_ALL, "classDailyHealthSurvey");
		//replaceGenericRelation("StudentHealthSurvey"                   , BaseRelation.TRUST_CHAIN_ALL, "cq");
		//replaceGenericRelation("StudentDailyAnswer"                    , BaseRelation.TRUST_CHAIN_ALL, "studentHealthSurvey");
		//replaceGenericRelation("StudentDailyAnswer"                    , BaseRelation.TRUST_CHAIN_ALL, "question");
		//replaceGenericRelation("StudentDailyAnswer"                    , BaseRelation.TRUST_CHAIN_ALL, "cq");
		//replaceGenericRelation("SurveyStatus"                          , BaseRelation.TRUST_CHAIN_ALL, "platform");
		//replaceGenericRelation("WechatUser"                            , BaseRelation.TRUST_CHAIN_ALL, "address");
		//replaceGenericRelation("WechatUser"                            , BaseRelation.TRUST_CHAIN_ALL, "userType");
		//replaceGenericRelation("WechatUser"                            , BaseRelation.TRUST_CHAIN_ALL, "platform");
		//replaceGenericRelation("UserType"                              , BaseRelation.TRUST_CHAIN_ALL, "platform");
		//replaceGenericRelation("WechatLoginInfo"                       , BaseRelation.TRUST_CHAIN_ALL, "wechatUser");
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

		//String [] schoolClassRelatedObjectNames = {"class_teacher:Teacher","platform:Platform","cq:ChangeRequest"};
		//replaceRelationIndex("SchoolClass",schoolClassRelatedObjectNames);

		//String [] teacherRelatedObjectNames = {"platform:Platform","cq:ChangeRequest"};
		//replaceRelationIndex("Teacher",teacherRelatedObjectNames);

		//String [] guardianRelatedObjectNames = {"address:Location","wechat_user:WechatUser","platform:Platform","cq:ChangeRequest"};
		//replaceRelationIndex("Guardian",guardianRelatedObjectNames);

		//String [] questionRelatedObjectNames = {"question_type:QuestionType","platform:Platform"};
		//replaceRelationIndex("Question",questionRelatedObjectNames);

		//String [] questionTypeRelatedObjectNames = {"platform:Platform"};
		//replaceRelationIndex("QuestionType",questionTypeRelatedObjectNames);

		//String [] questionSourceRelatedObjectNames = {"platform:Platform"};
		//replaceRelationIndex("QuestionSource",questionSourceRelatedObjectNames);

		//String [] classQuestionRelatedObjectNames = {"question_type:QuestionType","question_source:QuestionSource","creator:WechatUser","cq:ChangeRequest"};
		//replaceRelationIndex("ClassQuestion",classQuestionRelatedObjectNames);

		//String [] classDailyHealthSurveyRelatedObjectNames = {"school_class:SchoolClass","creator:WechatUser","cq:ChangeRequest"};
		//replaceRelationIndex("ClassDailyHealthSurvey",classDailyHealthSurveyRelatedObjectNames);

		//String [] dailySurveyQuestionRelatedObjectNames = {"question_type:QuestionType","class_daily_health_survey:ClassDailyHealthSurvey","class_question:ClassQuestion"};
		//replaceRelationIndex("DailySurveyQuestion",dailySurveyQuestionRelatedObjectNames);

		//String [] studentRelatedObjectNames = {"guardian:Guardian","school_class:SchoolClass","cq:ChangeRequest"};
		//replaceRelationIndex("Student",studentRelatedObjectNames);

		//String [] studentHealthSurveyRelatedObjectNames = {"student:Student","survey_status:SurveyStatus","school_class:SchoolClass","class_daily_health_survey:ClassDailyHealthSurvey","cq:ChangeRequest"};
		//replaceRelationIndex("StudentHealthSurvey",studentHealthSurveyRelatedObjectNames);

		//String [] studentDailyAnswerRelatedObjectNames = {"student_health_survey:StudentHealthSurvey","question:DailySurveyQuestion","cq:ChangeRequest"};
		//replaceRelationIndex("StudentDailyAnswer",studentDailyAnswerRelatedObjectNames);

		//String [] surveyStatusRelatedObjectNames = {"platform:Platform"};
		//replaceRelationIndex("SurveyStatus",surveyStatusRelatedObjectNames);

		//String [] wechatUserRelatedObjectNames = {"address:Location","user_type:UserType","platform:Platform"};
		//replaceRelationIndex("WechatUser",wechatUserRelatedObjectNames);

		//String [] userTypeRelatedObjectNames = {"platform:Platform"};
		//replaceRelationIndex("UserType",userTypeRelatedObjectNames);

		//String [] wechatLoginInfoRelatedObjectNames = {"wechat_user:WechatUser"};
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










