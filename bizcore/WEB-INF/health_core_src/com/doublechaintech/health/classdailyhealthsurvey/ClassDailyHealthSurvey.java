
package com.doublechaintech.health.classdailyhealthsurvey;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.KeyValuePair;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.user.User;

@JsonSerialize(using = ClassDailyHealthSurveySerializer.class)
public class ClassDailyHealthSurvey extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String TEACHER_PROPERTY               = "teacher"           ;
	public static final String SURVEY_TIME_PROPERTY           = "surveyTime"        ;
	public static final String CREATOR_PROPERTY               = "creator"           ;
	public static final String CHANGE_REQUEST_PROPERTY        = "changeRequest"     ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String DAILY_SURVEY_QUESTION_LIST               = "dailySurveyQuestionList";
	public static final String STUDENT_HEALTH_SURVEY_LIST               = "studentHealthSurveyList";

	public static final String INTERNAL_TYPE="ClassDailyHealthSurvey";
	public String getInternalType(){
		return INTERNAL_TYPE;
	}
	
	public String getDisplayName(){
	
		String displayName = getName();
		if(displayName!=null){
			return displayName;
		}
		
		return super.getDisplayName();
		
	}

	private static final long serialVersionUID = 1L;
	

	protected		String              	mId                 ;
	protected		String              	mName               ;
	protected		Teacher             	mTeacher            ;
	protected		DateTime            	mSurveyTime         ;
	protected		User                	mCreator            ;
	protected		ChangeRequest       	mChangeRequest      ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<DailySurveyQuestion>	mDailySurveyQuestionList;
	protected		SmartList<StudentHealthSurvey>	mStudentHealthSurveyList;
	
		
	public 	ClassDailyHealthSurvey(){
		// lazy load for all the properties
	}
	public 	static ClassDailyHealthSurvey withId(String id){
		ClassDailyHealthSurvey classDailyHealthSurvey = new ClassDailyHealthSurvey();
		classDailyHealthSurvey.setId(id);
		classDailyHealthSurvey.setVersion(Integer.MAX_VALUE);
		return classDailyHealthSurvey;
	}
	public 	static ClassDailyHealthSurvey refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setTeacher( null );
		setCreator( null );
		setChangeRequest( null );

		this.changed = true;
	}
	
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
		}
		if(SURVEY_TIME_PROPERTY.equals(property)){
			changeSurveyTimeProperty(newValueExpr);
		}

      
	}
    
    
	protected void changeNameProperty(String newValueExpr){
		String oldValue = getName();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateName(newValue);
		this.onChangeProperty(NAME_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeSurveyTimeProperty(String newValueExpr){
		DateTime oldValue = getSurveyTime();
		DateTime newValue = parseTimestamp(newValueExpr);
		if(equalsTimestamp(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateSurveyTime(newValue);
		this.onChangeProperty(SURVEY_TIME_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(NAME_PROPERTY.equals(property)){
			return getName();
		}
		if(TEACHER_PROPERTY.equals(property)){
			return getTeacher();
		}
		if(SURVEY_TIME_PROPERTY.equals(property)){
			return getSurveyTime();
		}
		if(CREATOR_PROPERTY.equals(property)){
			return getCreator();
		}
		if(CHANGE_REQUEST_PROPERTY.equals(property)){
			return getChangeRequest();
		}
		if(DAILY_SURVEY_QUESTION_LIST.equals(property)){
			List<BaseEntity> list = getDailySurveyQuestionList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}
		if(STUDENT_HEALTH_SURVEY_LIST.equals(property)){
			List<BaseEntity> list = getStudentHealthSurveyList().stream().map(item->item).collect(Collectors.toList());
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
	public ClassDailyHealthSurvey updateId(String id){
		this.mId = trimString(id);;
		this.changed = true;
		return this;
	}
	public void mergeId(String id){
		if(id != null) { setId(id);}
	}
	
	
	public void setName(String name){
		this.mName = trimString(name);;
	}
	public String getName(){
		return this.mName;
	}
	public ClassDailyHealthSurvey updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setTeacher(Teacher teacher){
		this.mTeacher = teacher;;
	}
	public Teacher getTeacher(){
		return this.mTeacher;
	}
	public ClassDailyHealthSurvey updateTeacher(Teacher teacher){
		this.mTeacher = teacher;;
		this.changed = true;
		return this;
	}
	public void mergeTeacher(Teacher teacher){
		if(teacher != null) { setTeacher(teacher);}
	}
	
	
	public void clearTeacher(){
		setTeacher ( null );
		this.changed = true;
	}
	
	public void setSurveyTime(DateTime surveyTime){
		this.mSurveyTime = surveyTime;;
	}
	public DateTime getSurveyTime(){
		return this.mSurveyTime;
	}
	public ClassDailyHealthSurvey updateSurveyTime(DateTime surveyTime){
		this.mSurveyTime = surveyTime;;
		this.changed = true;
		return this;
	}
	public void mergeSurveyTime(DateTime surveyTime){
		setSurveyTime(surveyTime);
	}
	
	
	public void setCreator(User creator){
		this.mCreator = creator;;
	}
	public User getCreator(){
		return this.mCreator;
	}
	public ClassDailyHealthSurvey updateCreator(User creator){
		this.mCreator = creator;;
		this.changed = true;
		return this;
	}
	public void mergeCreator(User creator){
		if(creator != null) { setCreator(creator);}
	}
	
	
	public void clearCreator(){
		setCreator ( null );
		this.changed = true;
	}
	
	public void setChangeRequest(ChangeRequest changeRequest){
		this.mChangeRequest = changeRequest;;
	}
	public ChangeRequest getChangeRequest(){
		return this.mChangeRequest;
	}
	public ClassDailyHealthSurvey updateChangeRequest(ChangeRequest changeRequest){
		this.mChangeRequest = changeRequest;;
		this.changed = true;
		return this;
	}
	public void mergeChangeRequest(ChangeRequest changeRequest){
		if(changeRequest != null) { setChangeRequest(changeRequest);}
	}
	
	
	public void clearChangeRequest(){
		setChangeRequest ( null );
		this.changed = true;
	}
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public ClassDailyHealthSurvey updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<DailySurveyQuestion> getDailySurveyQuestionList(){
		if(this.mDailySurveyQuestionList == null){
			this.mDailySurveyQuestionList = new SmartList<DailySurveyQuestion>();
			this.mDailySurveyQuestionList.setListInternalName (DAILY_SURVEY_QUESTION_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mDailySurveyQuestionList;	
	}
	public  void setDailySurveyQuestionList(SmartList<DailySurveyQuestion> dailySurveyQuestionList){
		for( DailySurveyQuestion dailySurveyQuestion:dailySurveyQuestionList){
			dailySurveyQuestion.setClassDailyHealthSurvey(this);
		}

		this.mDailySurveyQuestionList = dailySurveyQuestionList;
		this.mDailySurveyQuestionList.setListInternalName (DAILY_SURVEY_QUESTION_LIST );
		
	}
	
	public  void addDailySurveyQuestion(DailySurveyQuestion dailySurveyQuestion){
		dailySurveyQuestion.setClassDailyHealthSurvey(this);
		getDailySurveyQuestionList().add(dailySurveyQuestion);
	}
	public  void addDailySurveyQuestionList(SmartList<DailySurveyQuestion> dailySurveyQuestionList){
		for( DailySurveyQuestion dailySurveyQuestion:dailySurveyQuestionList){
			dailySurveyQuestion.setClassDailyHealthSurvey(this);
		}
		getDailySurveyQuestionList().addAll(dailySurveyQuestionList);
	}
	public  void mergeDailySurveyQuestionList(SmartList<DailySurveyQuestion> dailySurveyQuestionList){
		if(dailySurveyQuestionList==null){
			return;
		}
		if(dailySurveyQuestionList.isEmpty()){
			return;
		}
		addDailySurveyQuestionList( dailySurveyQuestionList );
		
	}
	public  DailySurveyQuestion removeDailySurveyQuestion(DailySurveyQuestion dailySurveyQuestionIndex){
		
		int index = getDailySurveyQuestionList().indexOf(dailySurveyQuestionIndex);
        if(index < 0){
        	String message = "DailySurveyQuestion("+dailySurveyQuestionIndex.getId()+") with version='"+dailySurveyQuestionIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        DailySurveyQuestion dailySurveyQuestion = getDailySurveyQuestionList().get(index);        
        // dailySurveyQuestion.clearClassDailyHealthSurvey(); //disconnect with ClassDailyHealthSurvey
        dailySurveyQuestion.clearFromAll(); //disconnect with ClassDailyHealthSurvey
		
		boolean result = getDailySurveyQuestionList().planToRemove(dailySurveyQuestion);
        if(!result){
        	String message = "DailySurveyQuestion("+dailySurveyQuestionIndex.getId()+") with version='"+dailySurveyQuestionIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return dailySurveyQuestion;
        
	
	}
	//断舍离
	public  void breakWithDailySurveyQuestion(DailySurveyQuestion dailySurveyQuestion){
		
		if(dailySurveyQuestion == null){
			return;
		}
		dailySurveyQuestion.setClassDailyHealthSurvey(null);
		//getDailySurveyQuestionList().remove();
	
	}
	
	public  boolean hasDailySurveyQuestion(DailySurveyQuestion dailySurveyQuestion){
	
		return getDailySurveyQuestionList().contains(dailySurveyQuestion);
  
	}
	
	public void copyDailySurveyQuestionFrom(DailySurveyQuestion dailySurveyQuestion) {

		DailySurveyQuestion dailySurveyQuestionInList = findTheDailySurveyQuestion(dailySurveyQuestion);
		DailySurveyQuestion newDailySurveyQuestion = new DailySurveyQuestion();
		dailySurveyQuestionInList.copyTo(newDailySurveyQuestion);
		newDailySurveyQuestion.setVersion(0);//will trigger copy
		getDailySurveyQuestionList().add(newDailySurveyQuestion);
		addItemToFlexiableObject(COPIED_CHILD, newDailySurveyQuestion);
	}
	
	public  DailySurveyQuestion findTheDailySurveyQuestion(DailySurveyQuestion dailySurveyQuestion){
		
		int index =  getDailySurveyQuestionList().indexOf(dailySurveyQuestion);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "DailySurveyQuestion("+dailySurveyQuestion.getId()+") with version='"+dailySurveyQuestion.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getDailySurveyQuestionList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpDailySurveyQuestionList(){
		getDailySurveyQuestionList().clear();
	}
	
	
	


	public  SmartList<StudentHealthSurvey> getStudentHealthSurveyList(){
		if(this.mStudentHealthSurveyList == null){
			this.mStudentHealthSurveyList = new SmartList<StudentHealthSurvey>();
			this.mStudentHealthSurveyList.setListInternalName (STUDENT_HEALTH_SURVEY_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mStudentHealthSurveyList;	
	}
	public  void setStudentHealthSurveyList(SmartList<StudentHealthSurvey> studentHealthSurveyList){
		for( StudentHealthSurvey studentHealthSurvey:studentHealthSurveyList){
			studentHealthSurvey.setClassDailyHealthSurvey(this);
		}

		this.mStudentHealthSurveyList = studentHealthSurveyList;
		this.mStudentHealthSurveyList.setListInternalName (STUDENT_HEALTH_SURVEY_LIST );
		
	}
	
	public  void addStudentHealthSurvey(StudentHealthSurvey studentHealthSurvey){
		studentHealthSurvey.setClassDailyHealthSurvey(this);
		getStudentHealthSurveyList().add(studentHealthSurvey);
	}
	public  void addStudentHealthSurveyList(SmartList<StudentHealthSurvey> studentHealthSurveyList){
		for( StudentHealthSurvey studentHealthSurvey:studentHealthSurveyList){
			studentHealthSurvey.setClassDailyHealthSurvey(this);
		}
		getStudentHealthSurveyList().addAll(studentHealthSurveyList);
	}
	public  void mergeStudentHealthSurveyList(SmartList<StudentHealthSurvey> studentHealthSurveyList){
		if(studentHealthSurveyList==null){
			return;
		}
		if(studentHealthSurveyList.isEmpty()){
			return;
		}
		addStudentHealthSurveyList( studentHealthSurveyList );
		
	}
	public  StudentHealthSurvey removeStudentHealthSurvey(StudentHealthSurvey studentHealthSurveyIndex){
		
		int index = getStudentHealthSurveyList().indexOf(studentHealthSurveyIndex);
        if(index < 0){
        	String message = "StudentHealthSurvey("+studentHealthSurveyIndex.getId()+") with version='"+studentHealthSurveyIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        StudentHealthSurvey studentHealthSurvey = getStudentHealthSurveyList().get(index);        
        // studentHealthSurvey.clearClassDailyHealthSurvey(); //disconnect with ClassDailyHealthSurvey
        studentHealthSurvey.clearFromAll(); //disconnect with ClassDailyHealthSurvey
		
		boolean result = getStudentHealthSurveyList().planToRemove(studentHealthSurvey);
        if(!result){
        	String message = "StudentHealthSurvey("+studentHealthSurveyIndex.getId()+") with version='"+studentHealthSurveyIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return studentHealthSurvey;
        
	
	}
	//断舍离
	public  void breakWithStudentHealthSurvey(StudentHealthSurvey studentHealthSurvey){
		
		if(studentHealthSurvey == null){
			return;
		}
		studentHealthSurvey.setClassDailyHealthSurvey(null);
		//getStudentHealthSurveyList().remove();
	
	}
	
	public  boolean hasStudentHealthSurvey(StudentHealthSurvey studentHealthSurvey){
	
		return getStudentHealthSurveyList().contains(studentHealthSurvey);
  
	}
	
	public void copyStudentHealthSurveyFrom(StudentHealthSurvey studentHealthSurvey) {

		StudentHealthSurvey studentHealthSurveyInList = findTheStudentHealthSurvey(studentHealthSurvey);
		StudentHealthSurvey newStudentHealthSurvey = new StudentHealthSurvey();
		studentHealthSurveyInList.copyTo(newStudentHealthSurvey);
		newStudentHealthSurvey.setVersion(0);//will trigger copy
		getStudentHealthSurveyList().add(newStudentHealthSurvey);
		addItemToFlexiableObject(COPIED_CHILD, newStudentHealthSurvey);
	}
	
	public  StudentHealthSurvey findTheStudentHealthSurvey(StudentHealthSurvey studentHealthSurvey){
		
		int index =  getStudentHealthSurveyList().indexOf(studentHealthSurvey);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "StudentHealthSurvey("+studentHealthSurvey.getId()+") with version='"+studentHealthSurvey.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getStudentHealthSurveyList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpStudentHealthSurveyList(){
		getStudentHealthSurveyList().clear();
	}
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getTeacher(), internalType);
		addToEntityList(this, entityList, getCreator(), internalType);
		addToEntityList(this, entityList, getChangeRequest(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getDailySurveyQuestionList(), internalType);
		collectFromList(this, entityList, getStudentHealthSurveyList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getDailySurveyQuestionList());
		listOfList.add( getStudentHealthSurveyList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, TEACHER_PROPERTY, getTeacher());
		appendKeyValuePair(result, SURVEY_TIME_PROPERTY, getSurveyTime());
		appendKeyValuePair(result, CREATOR_PROPERTY, getCreator());
		appendKeyValuePair(result, CHANGE_REQUEST_PROPERTY, getChangeRequest());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, DAILY_SURVEY_QUESTION_LIST, getDailySurveyQuestionList());
		if(!getDailySurveyQuestionList().isEmpty()){
			appendKeyValuePair(result, "dailySurveyQuestionCount", getDailySurveyQuestionList().getTotalCount());
			appendKeyValuePair(result, "dailySurveyQuestionCurrentPageNumber", getDailySurveyQuestionList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, STUDENT_HEALTH_SURVEY_LIST, getStudentHealthSurveyList());
		if(!getStudentHealthSurveyList().isEmpty()){
			appendKeyValuePair(result, "studentHealthSurveyCount", getStudentHealthSurveyList().getTotalCount());
			appendKeyValuePair(result, "studentHealthSurveyCurrentPageNumber", getStudentHealthSurveyList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof ClassDailyHealthSurvey){
		
		
			ClassDailyHealthSurvey dest =(ClassDailyHealthSurvey)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setTeacher(getTeacher());
			dest.setSurveyTime(getSurveyTime());
			dest.setCreator(getCreator());
			dest.setChangeRequest(getChangeRequest());
			dest.setVersion(getVersion());
			dest.setDailySurveyQuestionList(getDailySurveyQuestionList());
			dest.setStudentHealthSurveyList(getStudentHealthSurveyList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof ClassDailyHealthSurvey){
		
			
			ClassDailyHealthSurvey dest =(ClassDailyHealthSurvey)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeTeacher(getTeacher());
			dest.mergeSurveyTime(getSurveyTime());
			dest.mergeCreator(getCreator());
			dest.mergeChangeRequest(getChangeRequest());
			dest.mergeVersion(getVersion());
			dest.mergeDailySurveyQuestionList(getDailySurveyQuestionList());
			dest.mergeStudentHealthSurveyList(getStudentHealthSurveyList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof ClassDailyHealthSurvey){
		
			
			ClassDailyHealthSurvey dest =(ClassDailyHealthSurvey)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeSurveyTime(getSurveyTime());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getName(), getTeacher(), getSurveyTime(), getCreator(), getChangeRequest(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("ClassDailyHealthSurvey{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		if(getTeacher() != null ){
 			stringBuilder.append("\tteacher='Teacher("+getTeacher().getId()+")';");
 		}
		stringBuilder.append("\tsurveyTime='"+getSurveyTime()+"';");
		if(getCreator() != null ){
 			stringBuilder.append("\tcreator='User("+getCreator().getId()+")';");
 		}
		if(getChangeRequest() != null ){
 			stringBuilder.append("\tchangeRequest='ChangeRequest("+getChangeRequest().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

