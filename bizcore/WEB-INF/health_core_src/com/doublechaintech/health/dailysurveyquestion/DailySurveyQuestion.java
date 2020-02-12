
package com.doublechaintech.health.dailysurveyquestion;

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
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.question.Question;

@JsonSerialize(using = DailySurveyQuestionSerializer.class)
public class DailySurveyQuestion extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String TOPIC_PROPERTY                 = "topic"             ;
	public static final String QUESTION_TYPE_PROPERTY         = "questionType"      ;
	public static final String OPTION_A_PROPERTY              = "optionA"           ;
	public static final String OPTION_B_PROPERTY              = "optionB"           ;
	public static final String OPTION_C_PROPERTY              = "optionC"           ;
	public static final String OPTION_D_PROPERTY              = "optionD"           ;
	public static final String CLASS_DAILY_HEALTH_SURVEY_PROPERTY = "classDailyHealthSurvey";
	public static final String SURVEY_QUESTION_PROPERTY       = "surveyQuestion"    ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String STUDENT_DAILY_ANSWER_LIST                = "studentDailyAnswerList";

	public static final String INTERNAL_TYPE="DailySurveyQuestion";
	public String getInternalType(){
		return INTERNAL_TYPE;
	}
	
	public String getDisplayName(){
	
		String displayName = getTopic();
		if(displayName!=null){
			return displayName;
		}
		
		return super.getDisplayName();
		
	}

	private static final long serialVersionUID = 1L;
	

	protected		String              	mId                 ;
	protected		String              	mTopic              ;
	protected		QuestionType        	mQuestionType       ;
	protected		String              	mOptionA            ;
	protected		String              	mOptionB            ;
	protected		String              	mOptionC            ;
	protected		String              	mOptionD            ;
	protected		ClassDailyHealthSurvey	mClassDailyHealthSurvey;
	protected		Question            	mSurveyQuestion     ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<StudentDailyAnswer>	mStudentDailyAnswerList;
	
		
	public 	DailySurveyQuestion(){
		// lazy load for all the properties
	}
	public 	static DailySurveyQuestion withId(String id){
		DailySurveyQuestion dailySurveyQuestion = new DailySurveyQuestion();
		dailySurveyQuestion.setId(id);
		dailySurveyQuestion.setVersion(Integer.MAX_VALUE);
		return dailySurveyQuestion;
	}
	public 	static DailySurveyQuestion refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setQuestionType( null );
		setClassDailyHealthSurvey( null );
		setSurveyQuestion( null );

		this.changed = true;
	}
	
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(TOPIC_PROPERTY.equals(property)){
			changeTopicProperty(newValueExpr);
		}
		if(OPTION_A_PROPERTY.equals(property)){
			changeOptionAProperty(newValueExpr);
		}
		if(OPTION_B_PROPERTY.equals(property)){
			changeOptionBProperty(newValueExpr);
		}
		if(OPTION_C_PROPERTY.equals(property)){
			changeOptionCProperty(newValueExpr);
		}
		if(OPTION_D_PROPERTY.equals(property)){
			changeOptionDProperty(newValueExpr);
		}

      
	}
    
    
	protected void changeTopicProperty(String newValueExpr){
	
		String oldValue = getTopic();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateTopic(newValue);
		this.onChangeProperty(TOPIC_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			
	protected void changeOptionAProperty(String newValueExpr){
	
		String oldValue = getOptionA();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateOptionA(newValue);
		this.onChangeProperty(OPTION_A_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			
	protected void changeOptionBProperty(String newValueExpr){
	
		String oldValue = getOptionB();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateOptionB(newValue);
		this.onChangeProperty(OPTION_B_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			
	protected void changeOptionCProperty(String newValueExpr){
	
		String oldValue = getOptionC();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateOptionC(newValue);
		this.onChangeProperty(OPTION_C_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			
	protected void changeOptionDProperty(String newValueExpr){
	
		String oldValue = getOptionD();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateOptionD(newValue);
		this.onChangeProperty(OPTION_D_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(TOPIC_PROPERTY.equals(property)){
			return getTopic();
		}
		if(QUESTION_TYPE_PROPERTY.equals(property)){
			return getQuestionType();
		}
		if(OPTION_A_PROPERTY.equals(property)){
			return getOptionA();
		}
		if(OPTION_B_PROPERTY.equals(property)){
			return getOptionB();
		}
		if(OPTION_C_PROPERTY.equals(property)){
			return getOptionC();
		}
		if(OPTION_D_PROPERTY.equals(property)){
			return getOptionD();
		}
		if(CLASS_DAILY_HEALTH_SURVEY_PROPERTY.equals(property)){
			return getClassDailyHealthSurvey();
		}
		if(SURVEY_QUESTION_PROPERTY.equals(property)){
			return getSurveyQuestion();
		}
		if(STUDENT_DAILY_ANSWER_LIST.equals(property)){
			List<BaseEntity> list = getStudentDailyAnswerList().stream().map(item->item).collect(Collectors.toList());
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
	public DailySurveyQuestion updateId(String id){
		this.mId = trimString(id);;
		this.changed = true;
		return this;
	}
	public void mergeId(String id){
		if(id != null) { setId(id);}
	}
	
	
	public void setTopic(String topic){
		this.mTopic = trimString(topic);;
	}
	public String getTopic(){
		return this.mTopic;
	}
	public DailySurveyQuestion updateTopic(String topic){
		this.mTopic = trimString(topic);;
		this.changed = true;
		return this;
	}
	public void mergeTopic(String topic){
		if(topic != null) { setTopic(topic);}
	}
	
	
	public void setQuestionType(QuestionType questionType){
		this.mQuestionType = questionType;;
	}
	public QuestionType getQuestionType(){
		return this.mQuestionType;
	}
	public DailySurveyQuestion updateQuestionType(QuestionType questionType){
		this.mQuestionType = questionType;;
		this.changed = true;
		return this;
	}
	public void mergeQuestionType(QuestionType questionType){
		if(questionType != null) { setQuestionType(questionType);}
	}
	
	
	public void clearQuestionType(){
		setQuestionType ( null );
		this.changed = true;
	}
	
	public void setOptionA(String optionA){
		this.mOptionA = trimString(optionA);;
	}
	public String getOptionA(){
		return this.mOptionA;
	}
	public DailySurveyQuestion updateOptionA(String optionA){
		this.mOptionA = trimString(optionA);;
		this.changed = true;
		return this;
	}
	public void mergeOptionA(String optionA){
		if(optionA != null) { setOptionA(optionA);}
	}
	
	
	public void setOptionB(String optionB){
		this.mOptionB = trimString(optionB);;
	}
	public String getOptionB(){
		return this.mOptionB;
	}
	public DailySurveyQuestion updateOptionB(String optionB){
		this.mOptionB = trimString(optionB);;
		this.changed = true;
		return this;
	}
	public void mergeOptionB(String optionB){
		if(optionB != null) { setOptionB(optionB);}
	}
	
	
	public void setOptionC(String optionC){
		this.mOptionC = trimString(optionC);;
	}
	public String getOptionC(){
		return this.mOptionC;
	}
	public DailySurveyQuestion updateOptionC(String optionC){
		this.mOptionC = trimString(optionC);;
		this.changed = true;
		return this;
	}
	public void mergeOptionC(String optionC){
		if(optionC != null) { setOptionC(optionC);}
	}
	
	
	public void setOptionD(String optionD){
		this.mOptionD = trimString(optionD);;
	}
	public String getOptionD(){
		return this.mOptionD;
	}
	public DailySurveyQuestion updateOptionD(String optionD){
		this.mOptionD = trimString(optionD);;
		this.changed = true;
		return this;
	}
	public void mergeOptionD(String optionD){
		if(optionD != null) { setOptionD(optionD);}
	}
	
	
	public void setClassDailyHealthSurvey(ClassDailyHealthSurvey classDailyHealthSurvey){
		this.mClassDailyHealthSurvey = classDailyHealthSurvey;;
	}
	public ClassDailyHealthSurvey getClassDailyHealthSurvey(){
		return this.mClassDailyHealthSurvey;
	}
	public DailySurveyQuestion updateClassDailyHealthSurvey(ClassDailyHealthSurvey classDailyHealthSurvey){
		this.mClassDailyHealthSurvey = classDailyHealthSurvey;;
		this.changed = true;
		return this;
	}
	public void mergeClassDailyHealthSurvey(ClassDailyHealthSurvey classDailyHealthSurvey){
		if(classDailyHealthSurvey != null) { setClassDailyHealthSurvey(classDailyHealthSurvey);}
	}
	
	
	public void clearClassDailyHealthSurvey(){
		setClassDailyHealthSurvey ( null );
		this.changed = true;
	}
	
	public void setSurveyQuestion(Question surveyQuestion){
		this.mSurveyQuestion = surveyQuestion;;
	}
	public Question getSurveyQuestion(){
		return this.mSurveyQuestion;
	}
	public DailySurveyQuestion updateSurveyQuestion(Question surveyQuestion){
		this.mSurveyQuestion = surveyQuestion;;
		this.changed = true;
		return this;
	}
	public void mergeSurveyQuestion(Question surveyQuestion){
		if(surveyQuestion != null) { setSurveyQuestion(surveyQuestion);}
	}
	
	
	public void clearSurveyQuestion(){
		setSurveyQuestion ( null );
		this.changed = true;
	}
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public DailySurveyQuestion updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<StudentDailyAnswer> getStudentDailyAnswerList(){
		if(this.mStudentDailyAnswerList == null){
			this.mStudentDailyAnswerList = new SmartList<StudentDailyAnswer>();
			this.mStudentDailyAnswerList.setListInternalName (STUDENT_DAILY_ANSWER_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mStudentDailyAnswerList;	
	}
	public  void setStudentDailyAnswerList(SmartList<StudentDailyAnswer> studentDailyAnswerList){
		for( StudentDailyAnswer studentDailyAnswer:studentDailyAnswerList){
			studentDailyAnswer.setQuestion(this);
		}

		this.mStudentDailyAnswerList = studentDailyAnswerList;
		this.mStudentDailyAnswerList.setListInternalName (STUDENT_DAILY_ANSWER_LIST );
		
	}
	
	public  void addStudentDailyAnswer(StudentDailyAnswer studentDailyAnswer){
		studentDailyAnswer.setQuestion(this);
		getStudentDailyAnswerList().add(studentDailyAnswer);
	}
	public  void addStudentDailyAnswerList(SmartList<StudentDailyAnswer> studentDailyAnswerList){
		for( StudentDailyAnswer studentDailyAnswer:studentDailyAnswerList){
			studentDailyAnswer.setQuestion(this);
		}
		getStudentDailyAnswerList().addAll(studentDailyAnswerList);
	}
	public  void mergeStudentDailyAnswerList(SmartList<StudentDailyAnswer> studentDailyAnswerList){
		if(studentDailyAnswerList==null){
			return;
		}
		if(studentDailyAnswerList.isEmpty()){
			return;
		}
		addStudentDailyAnswerList( studentDailyAnswerList );
		
	}
	public  StudentDailyAnswer removeStudentDailyAnswer(StudentDailyAnswer studentDailyAnswerIndex){
		
		int index = getStudentDailyAnswerList().indexOf(studentDailyAnswerIndex);
        if(index < 0){
        	String message = "StudentDailyAnswer("+studentDailyAnswerIndex.getId()+") with version='"+studentDailyAnswerIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        StudentDailyAnswer studentDailyAnswer = getStudentDailyAnswerList().get(index);        
        // studentDailyAnswer.clearQuestion(); //disconnect with Question
        studentDailyAnswer.clearFromAll(); //disconnect with Question
		
		boolean result = getStudentDailyAnswerList().planToRemove(studentDailyAnswer);
        if(!result){
        	String message = "StudentDailyAnswer("+studentDailyAnswerIndex.getId()+") with version='"+studentDailyAnswerIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return studentDailyAnswer;
        
	
	}
	//断舍离
	public  void breakWithStudentDailyAnswer(StudentDailyAnswer studentDailyAnswer){
		
		if(studentDailyAnswer == null){
			return;
		}
		studentDailyAnswer.setQuestion(null);
		//getStudentDailyAnswerList().remove();
	
	}
	
	public  boolean hasStudentDailyAnswer(StudentDailyAnswer studentDailyAnswer){
	
		return getStudentDailyAnswerList().contains(studentDailyAnswer);
  
	}
	
	public void copyStudentDailyAnswerFrom(StudentDailyAnswer studentDailyAnswer) {

		StudentDailyAnswer studentDailyAnswerInList = findTheStudentDailyAnswer(studentDailyAnswer);
		StudentDailyAnswer newStudentDailyAnswer = new StudentDailyAnswer();
		studentDailyAnswerInList.copyTo(newStudentDailyAnswer);
		newStudentDailyAnswer.setVersion(0);//will trigger copy
		getStudentDailyAnswerList().add(newStudentDailyAnswer);
		addItemToFlexiableObject(COPIED_CHILD, newStudentDailyAnswer);
	}
	
	public  StudentDailyAnswer findTheStudentDailyAnswer(StudentDailyAnswer studentDailyAnswer){
		
		int index =  getStudentDailyAnswerList().indexOf(studentDailyAnswer);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "StudentDailyAnswer("+studentDailyAnswer.getId()+") with version='"+studentDailyAnswer.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getStudentDailyAnswerList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpStudentDailyAnswerList(){
		getStudentDailyAnswerList().clear();
	}
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getQuestionType(), internalType);
		addToEntityList(this, entityList, getClassDailyHealthSurvey(), internalType);
		addToEntityList(this, entityList, getSurveyQuestion(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getStudentDailyAnswerList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getStudentDailyAnswerList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, TOPIC_PROPERTY, getTopic());
		appendKeyValuePair(result, QUESTION_TYPE_PROPERTY, getQuestionType());
		appendKeyValuePair(result, OPTION_A_PROPERTY, getOptionA());
		appendKeyValuePair(result, OPTION_B_PROPERTY, getOptionB());
		appendKeyValuePair(result, OPTION_C_PROPERTY, getOptionC());
		appendKeyValuePair(result, OPTION_D_PROPERTY, getOptionD());
		appendKeyValuePair(result, CLASS_DAILY_HEALTH_SURVEY_PROPERTY, getClassDailyHealthSurvey());
		appendKeyValuePair(result, SURVEY_QUESTION_PROPERTY, getSurveyQuestion());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, STUDENT_DAILY_ANSWER_LIST, getStudentDailyAnswerList());
		if(!getStudentDailyAnswerList().isEmpty()){
			appendKeyValuePair(result, "studentDailyAnswerCount", getStudentDailyAnswerList().getTotalCount());
			appendKeyValuePair(result, "studentDailyAnswerCurrentPageNumber", getStudentDailyAnswerList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof DailySurveyQuestion){
		
		
			DailySurveyQuestion dest =(DailySurveyQuestion)baseDest;
		
			dest.setId(getId());
			dest.setTopic(getTopic());
			dest.setQuestionType(getQuestionType());
			dest.setOptionA(getOptionA());
			dest.setOptionB(getOptionB());
			dest.setOptionC(getOptionC());
			dest.setOptionD(getOptionD());
			dest.setClassDailyHealthSurvey(getClassDailyHealthSurvey());
			dest.setSurveyQuestion(getSurveyQuestion());
			dest.setVersion(getVersion());
			dest.setStudentDailyAnswerList(getStudentDailyAnswerList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof DailySurveyQuestion){
		
			
			DailySurveyQuestion dest =(DailySurveyQuestion)baseDest;
		
			dest.mergeId(getId());
			dest.mergeTopic(getTopic());
			dest.mergeQuestionType(getQuestionType());
			dest.mergeOptionA(getOptionA());
			dest.mergeOptionB(getOptionB());
			dest.mergeOptionC(getOptionC());
			dest.mergeOptionD(getOptionD());
			dest.mergeClassDailyHealthSurvey(getClassDailyHealthSurvey());
			dest.mergeSurveyQuestion(getSurveyQuestion());
			dest.mergeVersion(getVersion());
			dest.mergeStudentDailyAnswerList(getStudentDailyAnswerList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof DailySurveyQuestion){
		
			
			DailySurveyQuestion dest =(DailySurveyQuestion)baseDest;
		
			dest.mergeId(getId());
			dest.mergeTopic(getTopic());
			dest.mergeOptionA(getOptionA());
			dest.mergeOptionB(getOptionB());
			dest.mergeOptionC(getOptionC());
			dest.mergeOptionD(getOptionD());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getTopic(), getQuestionType(), getOptionA(), getOptionB(), getOptionC(), getOptionD(), getClassDailyHealthSurvey(), getSurveyQuestion(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("DailySurveyQuestion{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\ttopic='"+getTopic()+"';");
		if(getQuestionType() != null ){
 			stringBuilder.append("\tquestionType='QuestionType("+getQuestionType().getId()+")';");
 		}
		stringBuilder.append("\toptionA='"+getOptionA()+"';");
		stringBuilder.append("\toptionB='"+getOptionB()+"';");
		stringBuilder.append("\toptionC='"+getOptionC()+"';");
		stringBuilder.append("\toptionD='"+getOptionD()+"';");
		if(getClassDailyHealthSurvey() != null ){
 			stringBuilder.append("\tclassDailyHealthSurvey='ClassDailyHealthSurvey("+getClassDailyHealthSurvey().getId()+")';");
 		}
		if(getSurveyQuestion() != null ){
 			stringBuilder.append("\tsurveyQuestion='Question("+getSurveyQuestion().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

