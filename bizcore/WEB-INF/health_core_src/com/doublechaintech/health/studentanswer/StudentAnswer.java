
package com.doublechaintech.health.studentanswer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.terapico.caf.Images;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.KeyValuePair;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.healthsurveyreport.HealthSurveyReport;

@JsonSerialize(using = StudentAnswerSerializer.class)
public class StudentAnswer extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String HEALTH_SURVEY_REPORT_PROPERTY  = "healthSurveyReport";
	public static final String DAILY_ANSWER_PROPERTY          = "dailyAnswer"       ;
	public static final String QUESTION_TOPIC_PROPERTY        = "questionTopic"     ;
	public static final String ANSWER_PROPERTY                = "answer"            ;
	public static final String VERSION_PROPERTY               = "version"           ;


	public static final String INTERNAL_TYPE="StudentAnswer";
	public String getInternalType(){
		return INTERNAL_TYPE;
	}
	
	public String getDisplayName(){
	
		String displayName = getQuestionTopic();
		if(displayName!=null){
			return displayName;
		}
		
		return super.getDisplayName();
		
	}

	private static final long serialVersionUID = 1L;
	

	protected		String              	mId                 ;
	protected		HealthSurveyReport  	mHealthSurveyReport ;
	protected		StudentDailyAnswer  	mDailyAnswer        ;
	protected		String              	mQuestionTopic      ;
	protected		String              	mAnswer             ;
	protected		int                 	mVersion            ;
	
	
	
		
	public 	StudentAnswer(){
		// lazy load for all the properties
	}
	public 	static StudentAnswer withId(String id){
		StudentAnswer studentAnswer = new StudentAnswer();
		studentAnswer.setId(id);
		studentAnswer.setVersion(Integer.MAX_VALUE);
		return studentAnswer;
	}
	public 	static StudentAnswer refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setHealthSurveyReport( null );
		setDailyAnswer( null );

		this.changed = true;
	}
	
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(QUESTION_TOPIC_PROPERTY.equals(property)){
			changeQuestionTopicProperty(newValueExpr);
		}
		if(ANSWER_PROPERTY.equals(property)){
			changeAnswerProperty(newValueExpr);
		}

      
	}
    
    
	protected void changeQuestionTopicProperty(String newValueExpr){
	
		String oldValue = getQuestionTopic();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateQuestionTopic(newValue);
		this.onChangeProperty(QUESTION_TOPIC_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			
	protected void changeAnswerProperty(String newValueExpr){
	
		String oldValue = getAnswer();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateAnswer(newValue);
		this.onChangeProperty(ANSWER_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(HEALTH_SURVEY_REPORT_PROPERTY.equals(property)){
			return getHealthSurveyReport();
		}
		if(DAILY_ANSWER_PROPERTY.equals(property)){
			return getDailyAnswer();
		}
		if(QUESTION_TOPIC_PROPERTY.equals(property)){
			return getQuestionTopic();
		}
		if(ANSWER_PROPERTY.equals(property)){
			return getAnswer();
		}

    		//other property not include here
		return super.propertyOf(property);
	}
    
    


	
	
	
	public void setId(String id){
		this.mId = trimString(id);;
	}
	public String getId(){
		return this.mId;
	}
	public StudentAnswer updateId(String id){
		this.mId = trimString(id);;
		this.changed = true;
		return this;
	}
	public void mergeId(String id){
		if(id != null) { setId(id);}
	}
	
	
	public void setHealthSurveyReport(HealthSurveyReport healthSurveyReport){
		this.mHealthSurveyReport = healthSurveyReport;;
	}
	public HealthSurveyReport getHealthSurveyReport(){
		return this.mHealthSurveyReport;
	}
	public StudentAnswer updateHealthSurveyReport(HealthSurveyReport healthSurveyReport){
		this.mHealthSurveyReport = healthSurveyReport;;
		this.changed = true;
		return this;
	}
	public void mergeHealthSurveyReport(HealthSurveyReport healthSurveyReport){
		if(healthSurveyReport != null) { setHealthSurveyReport(healthSurveyReport);}
	}
	
	
	public void clearHealthSurveyReport(){
		setHealthSurveyReport ( null );
		this.changed = true;
	}
	
	public void setDailyAnswer(StudentDailyAnswer dailyAnswer){
		this.mDailyAnswer = dailyAnswer;;
	}
	public StudentDailyAnswer getDailyAnswer(){
		return this.mDailyAnswer;
	}
	public StudentAnswer updateDailyAnswer(StudentDailyAnswer dailyAnswer){
		this.mDailyAnswer = dailyAnswer;;
		this.changed = true;
		return this;
	}
	public void mergeDailyAnswer(StudentDailyAnswer dailyAnswer){
		if(dailyAnswer != null) { setDailyAnswer(dailyAnswer);}
	}
	
	
	public void clearDailyAnswer(){
		setDailyAnswer ( null );
		this.changed = true;
	}
	
	public void setQuestionTopic(String questionTopic){
		this.mQuestionTopic = trimString(questionTopic);;
	}
	public String getQuestionTopic(){
		return this.mQuestionTopic;
	}
	public StudentAnswer updateQuestionTopic(String questionTopic){
		this.mQuestionTopic = trimString(questionTopic);;
		this.changed = true;
		return this;
	}
	public void mergeQuestionTopic(String questionTopic){
		if(questionTopic != null) { setQuestionTopic(questionTopic);}
	}
	
	
	public void setAnswer(String answer){
		this.mAnswer = trimString(answer);;
	}
	public String getAnswer(){
		return this.mAnswer;
	}
	public StudentAnswer updateAnswer(String answer){
		this.mAnswer = trimString(answer);;
		this.changed = true;
		return this;
	}
	public void mergeAnswer(String answer){
		if(answer != null) { setAnswer(answer);}
	}
	
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public StudentAnswer updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getHealthSurveyReport(), internalType);
		addToEntityList(this, entityList, getDailyAnswer(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, HEALTH_SURVEY_REPORT_PROPERTY, getHealthSurveyReport());
		appendKeyValuePair(result, DAILY_ANSWER_PROPERTY, getDailyAnswer());
		appendKeyValuePair(result, QUESTION_TOPIC_PROPERTY, getQuestionTopic());
		appendKeyValuePair(result, ANSWER_PROPERTY, getAnswer());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof StudentAnswer){
		
		
			StudentAnswer dest =(StudentAnswer)baseDest;
		
			dest.setId(getId());
			dest.setHealthSurveyReport(getHealthSurveyReport());
			dest.setDailyAnswer(getDailyAnswer());
			dest.setQuestionTopic(getQuestionTopic());
			dest.setAnswer(getAnswer());
			dest.setVersion(getVersion());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof StudentAnswer){
		
			
			StudentAnswer dest =(StudentAnswer)baseDest;
		
			dest.mergeId(getId());
			dest.mergeHealthSurveyReport(getHealthSurveyReport());
			dest.mergeDailyAnswer(getDailyAnswer());
			dest.mergeQuestionTopic(getQuestionTopic());
			dest.mergeAnswer(getAnswer());
			dest.mergeVersion(getVersion());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof StudentAnswer){
		
			
			StudentAnswer dest =(StudentAnswer)baseDest;
		
			dest.mergeId(getId());
			dest.mergeQuestionTopic(getQuestionTopic());
			dest.mergeAnswer(getAnswer());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getHealthSurveyReport(), getDailyAnswer(), getQuestionTopic(), getAnswer(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("StudentAnswer{");
		stringBuilder.append("\tid='"+getId()+"';");
		if(getHealthSurveyReport() != null ){
 			stringBuilder.append("\thealthSurveyReport='HealthSurveyReport("+getHealthSurveyReport().getId()+")';");
 		}
		if(getDailyAnswer() != null ){
 			stringBuilder.append("\tdailyAnswer='StudentDailyAnswer("+getDailyAnswer().getId()+")';");
 		}
		stringBuilder.append("\tquestionTopic='"+getQuestionTopic()+"';");
		stringBuilder.append("\tanswer='"+getAnswer()+"';");
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

