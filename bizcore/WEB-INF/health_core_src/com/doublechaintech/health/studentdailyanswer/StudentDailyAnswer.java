
package com.doublechaintech.health.studentdailyanswer;

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
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.studentanswer.StudentAnswer;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;

@JsonSerialize(using = StudentDailyAnswerSerializer.class)
public class StudentDailyAnswer extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String STUDENT_HEALTH_SURVEY_PROPERTY = "studentHealthSurvey";
	public static final String QUESTION_PROPERTY              = "question"          ;
	public static final String ANSWER_PROPERTY                = "answer"            ;
	public static final String CREATE_TIME_PROPERTY           = "createTime"        ;
	public static final String LAST_UPDATE_TIME_PROPERTY      = "lastUpdateTime"    ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String STUDENT_ANSWER_LIST                      = "studentAnswerList" ;

	public static final String INTERNAL_TYPE="StudentDailyAnswer";
	public String getInternalType(){
		return INTERNAL_TYPE;
	}
	
	public String getDisplayName(){
	
		String displayName = getAnswer();
		if(displayName!=null){
			return displayName;
		}
		
		return super.getDisplayName();
		
	}

	private static final long serialVersionUID = 1L;
	

	protected		String              	mId                 ;
	protected		StudentHealthSurvey 	mStudentHealthSurvey;
	protected		DailySurveyQuestion 	mQuestion           ;
	protected		String              	mAnswer             ;
	protected		DateTime            	mCreateTime         ;
	protected		DateTime            	mLastUpdateTime     ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<StudentAnswer>	mStudentAnswerList  ;
	
		
	public 	StudentDailyAnswer(){
		// lazy load for all the properties
	}
	public 	static StudentDailyAnswer withId(String id){
		StudentDailyAnswer studentDailyAnswer = new StudentDailyAnswer();
		studentDailyAnswer.setId(id);
		studentDailyAnswer.setVersion(Integer.MAX_VALUE);
		return studentDailyAnswer;
	}
	public 	static StudentDailyAnswer refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setStudentHealthSurvey( null );
		setQuestion( null );

		this.changed = true;
	}
	
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(ANSWER_PROPERTY.equals(property)){
			changeAnswerProperty(newValueExpr);
		}
		if(CREATE_TIME_PROPERTY.equals(property)){
			changeCreateTimeProperty(newValueExpr);
		}
		if(LAST_UPDATE_TIME_PROPERTY.equals(property)){
			changeLastUpdateTimeProperty(newValueExpr);
		}

      
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
			
			
			
	protected void changeCreateTimeProperty(String newValueExpr){
	
		DateTime oldValue = getCreateTime();
		DateTime newValue = parseTimestamp(newValueExpr);
		if(equalsTimestamp(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateCreateTime(newValue);
		this.onChangeProperty(CREATE_TIME_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			
	protected void changeLastUpdateTimeProperty(String newValueExpr){
	
		DateTime oldValue = getLastUpdateTime();
		DateTime newValue = parseTimestamp(newValueExpr);
		if(equalsTimestamp(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateLastUpdateTime(newValue);
		this.onChangeProperty(LAST_UPDATE_TIME_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(STUDENT_HEALTH_SURVEY_PROPERTY.equals(property)){
			return getStudentHealthSurvey();
		}
		if(QUESTION_PROPERTY.equals(property)){
			return getQuestion();
		}
		if(ANSWER_PROPERTY.equals(property)){
			return getAnswer();
		}
		if(CREATE_TIME_PROPERTY.equals(property)){
			return getCreateTime();
		}
		if(LAST_UPDATE_TIME_PROPERTY.equals(property)){
			return getLastUpdateTime();
		}
		if(STUDENT_ANSWER_LIST.equals(property)){
			List<BaseEntity> list = getStudentAnswerList().stream().map(item->item).collect(Collectors.toList());
			return list;
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
	public StudentDailyAnswer updateId(String id){
		this.mId = trimString(id);;
		this.changed = true;
		return this;
	}
	public void mergeId(String id){
		if(id != null) { setId(id);}
	}
	
	
	public void setStudentHealthSurvey(StudentHealthSurvey studentHealthSurvey){
		this.mStudentHealthSurvey = studentHealthSurvey;;
	}
	public StudentHealthSurvey getStudentHealthSurvey(){
		return this.mStudentHealthSurvey;
	}
	public StudentDailyAnswer updateStudentHealthSurvey(StudentHealthSurvey studentHealthSurvey){
		this.mStudentHealthSurvey = studentHealthSurvey;;
		this.changed = true;
		return this;
	}
	public void mergeStudentHealthSurvey(StudentHealthSurvey studentHealthSurvey){
		if(studentHealthSurvey != null) { setStudentHealthSurvey(studentHealthSurvey);}
	}
	
	
	public void clearStudentHealthSurvey(){
		setStudentHealthSurvey ( null );
		this.changed = true;
	}
	
	public void setQuestion(DailySurveyQuestion question){
		this.mQuestion = question;;
	}
	public DailySurveyQuestion getQuestion(){
		return this.mQuestion;
	}
	public StudentDailyAnswer updateQuestion(DailySurveyQuestion question){
		this.mQuestion = question;;
		this.changed = true;
		return this;
	}
	public void mergeQuestion(DailySurveyQuestion question){
		if(question != null) { setQuestion(question);}
	}
	
	
	public void clearQuestion(){
		setQuestion ( null );
		this.changed = true;
	}
	
	public void setAnswer(String answer){
		this.mAnswer = trimString(answer);;
	}
	public String getAnswer(){
		return this.mAnswer;
	}
	public StudentDailyAnswer updateAnswer(String answer){
		this.mAnswer = trimString(answer);;
		this.changed = true;
		return this;
	}
	public void mergeAnswer(String answer){
		if(answer != null) { setAnswer(answer);}
	}
	
	
	public void setCreateTime(DateTime createTime){
		this.mCreateTime = createTime;;
	}
	public DateTime getCreateTime(){
		return this.mCreateTime;
	}
	public StudentDailyAnswer updateCreateTime(DateTime createTime){
		this.mCreateTime = createTime;;
		this.changed = true;
		return this;
	}
	public void mergeCreateTime(DateTime createTime){
		setCreateTime(createTime);
	}
	
	
	public void setLastUpdateTime(DateTime lastUpdateTime){
		this.mLastUpdateTime = lastUpdateTime;;
	}
	public DateTime getLastUpdateTime(){
		return this.mLastUpdateTime;
	}
	public StudentDailyAnswer updateLastUpdateTime(DateTime lastUpdateTime){
		this.mLastUpdateTime = lastUpdateTime;;
		this.changed = true;
		return this;
	}
	public void mergeLastUpdateTime(DateTime lastUpdateTime){
		setLastUpdateTime(lastUpdateTime);
	}
	
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public StudentDailyAnswer updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<StudentAnswer> getStudentAnswerList(){
		if(this.mStudentAnswerList == null){
			this.mStudentAnswerList = new SmartList<StudentAnswer>();
			this.mStudentAnswerList.setListInternalName (STUDENT_ANSWER_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mStudentAnswerList;	
	}
	public  void setStudentAnswerList(SmartList<StudentAnswer> studentAnswerList){
		for( StudentAnswer studentAnswer:studentAnswerList){
			studentAnswer.setDailyAnswer(this);
		}

		this.mStudentAnswerList = studentAnswerList;
		this.mStudentAnswerList.setListInternalName (STUDENT_ANSWER_LIST );
		
	}
	
	public  void addStudentAnswer(StudentAnswer studentAnswer){
		studentAnswer.setDailyAnswer(this);
		getStudentAnswerList().add(studentAnswer);
	}
	public  void addStudentAnswerList(SmartList<StudentAnswer> studentAnswerList){
		for( StudentAnswer studentAnswer:studentAnswerList){
			studentAnswer.setDailyAnswer(this);
		}
		getStudentAnswerList().addAll(studentAnswerList);
	}
	public  void mergeStudentAnswerList(SmartList<StudentAnswer> studentAnswerList){
		if(studentAnswerList==null){
			return;
		}
		if(studentAnswerList.isEmpty()){
			return;
		}
		addStudentAnswerList( studentAnswerList );
		
	}
	public  StudentAnswer removeStudentAnswer(StudentAnswer studentAnswerIndex){
		
		int index = getStudentAnswerList().indexOf(studentAnswerIndex);
        if(index < 0){
        	String message = "StudentAnswer("+studentAnswerIndex.getId()+") with version='"+studentAnswerIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        StudentAnswer studentAnswer = getStudentAnswerList().get(index);        
        // studentAnswer.clearDailyAnswer(); //disconnect with DailyAnswer
        studentAnswer.clearFromAll(); //disconnect with DailyAnswer
		
		boolean result = getStudentAnswerList().planToRemove(studentAnswer);
        if(!result){
        	String message = "StudentAnswer("+studentAnswerIndex.getId()+") with version='"+studentAnswerIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return studentAnswer;
        
	
	}
	//断舍离
	public  void breakWithStudentAnswer(StudentAnswer studentAnswer){
		
		if(studentAnswer == null){
			return;
		}
		studentAnswer.setDailyAnswer(null);
		//getStudentAnswerList().remove();
	
	}
	
	public  boolean hasStudentAnswer(StudentAnswer studentAnswer){
	
		return getStudentAnswerList().contains(studentAnswer);
  
	}
	
	public void copyStudentAnswerFrom(StudentAnswer studentAnswer) {

		StudentAnswer studentAnswerInList = findTheStudentAnswer(studentAnswer);
		StudentAnswer newStudentAnswer = new StudentAnswer();
		studentAnswerInList.copyTo(newStudentAnswer);
		newStudentAnswer.setVersion(0);//will trigger copy
		getStudentAnswerList().add(newStudentAnswer);
		addItemToFlexiableObject(COPIED_CHILD, newStudentAnswer);
	}
	
	public  StudentAnswer findTheStudentAnswer(StudentAnswer studentAnswer){
		
		int index =  getStudentAnswerList().indexOf(studentAnswer);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "StudentAnswer("+studentAnswer.getId()+") with version='"+studentAnswer.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getStudentAnswerList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpStudentAnswerList(){
		getStudentAnswerList().clear();
	}
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getStudentHealthSurvey(), internalType);
		addToEntityList(this, entityList, getQuestion(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getStudentAnswerList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getStudentAnswerList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, STUDENT_HEALTH_SURVEY_PROPERTY, getStudentHealthSurvey());
		appendKeyValuePair(result, QUESTION_PROPERTY, getQuestion());
		appendKeyValuePair(result, ANSWER_PROPERTY, getAnswer());
		appendKeyValuePair(result, CREATE_TIME_PROPERTY, getCreateTime());
		appendKeyValuePair(result, LAST_UPDATE_TIME_PROPERTY, getLastUpdateTime());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, STUDENT_ANSWER_LIST, getStudentAnswerList());
		if(!getStudentAnswerList().isEmpty()){
			appendKeyValuePair(result, "studentAnswerCount", getStudentAnswerList().getTotalCount());
			appendKeyValuePair(result, "studentAnswerCurrentPageNumber", getStudentAnswerList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof StudentDailyAnswer){
		
		
			StudentDailyAnswer dest =(StudentDailyAnswer)baseDest;
		
			dest.setId(getId());
			dest.setStudentHealthSurvey(getStudentHealthSurvey());
			dest.setQuestion(getQuestion());
			dest.setAnswer(getAnswer());
			dest.setCreateTime(getCreateTime());
			dest.setLastUpdateTime(getLastUpdateTime());
			dest.setVersion(getVersion());
			dest.setStudentAnswerList(getStudentAnswerList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof StudentDailyAnswer){
		
			
			StudentDailyAnswer dest =(StudentDailyAnswer)baseDest;
		
			dest.mergeId(getId());
			dest.mergeStudentHealthSurvey(getStudentHealthSurvey());
			dest.mergeQuestion(getQuestion());
			dest.mergeAnswer(getAnswer());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeLastUpdateTime(getLastUpdateTime());
			dest.mergeVersion(getVersion());
			dest.mergeStudentAnswerList(getStudentAnswerList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof StudentDailyAnswer){
		
			
			StudentDailyAnswer dest =(StudentDailyAnswer)baseDest;
		
			dest.mergeId(getId());
			dest.mergeAnswer(getAnswer());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeLastUpdateTime(getLastUpdateTime());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getStudentHealthSurvey(), getQuestion(), getAnswer(), getCreateTime(), getLastUpdateTime(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("StudentDailyAnswer{");
		stringBuilder.append("\tid='"+getId()+"';");
		if(getStudentHealthSurvey() != null ){
 			stringBuilder.append("\tstudentHealthSurvey='StudentHealthSurvey("+getStudentHealthSurvey().getId()+")';");
 		}
		if(getQuestion() != null ){
 			stringBuilder.append("\tquestion='DailySurveyQuestion("+getQuestion().getId()+")';");
 		}
		stringBuilder.append("\tanswer='"+getAnswer()+"';");
		stringBuilder.append("\tcreateTime='"+getCreateTime()+"';");
		stringBuilder.append("\tlastUpdateTime='"+getLastUpdateTime()+"';");
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

