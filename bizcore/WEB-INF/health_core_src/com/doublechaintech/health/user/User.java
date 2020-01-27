
package com.doublechaintech.health.user;

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
import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.wechatlogininfo.WechatLoginInfo;
import com.doublechaintech.health.location.Location;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.question.Question;

@JsonSerialize(using = UserSerializer.class)
public class User extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String AVATAR_PROPERTY                = "avatar"            ;
	public static final String ADDRESS_PROPERTY               = "address"           ;
	public static final String CREATE_TIME_PROPERTY           = "createTime"        ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String STUDENT_LIST                             = "studentList"       ;
	public static final String QUESTION_LIST                            = "questionList"      ;
	public static final String CLASS_DAILY_HEALTH_SURVEY_LIST           = "classDailyHealthSurveyList";
	public static final String WECHAT_LOGIN_INFO_LIST                   = "wechatLoginInfoList";

	public static final String INTERNAL_TYPE="User";
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
	protected		String              	mAvatar             ;
	protected		Location            	mAddress            ;
	protected		DateTime            	mCreateTime         ;
	protected		Platform            	mPlatform           ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<Student>  	mStudentList        ;
	protected		SmartList<Question> 	mQuestionList       ;
	protected		SmartList<ClassDailyHealthSurvey>	mClassDailyHealthSurveyList;
	protected		SmartList<WechatLoginInfo>	mWechatLoginInfoList;
	
		
	public 	User(){
		// lazy load for all the properties
	}
	public 	static User withId(String id){
		User user = new User();
		user.setId(id);
		user.setVersion(Integer.MAX_VALUE);
		return user;
	}
	public 	static User refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setAddress( null );
		setPlatform( null );

		this.changed = true;
	}
	
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
		}
		if(AVATAR_PROPERTY.equals(property)){
			changeAvatarProperty(newValueExpr);
		}
		if(CREATE_TIME_PROPERTY.equals(property)){
			changeCreateTimeProperty(newValueExpr);
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
			
			
			
	protected void changeAvatarProperty(String newValueExpr){
		String oldValue = getAvatar();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateAvatar(newValue);
		this.onChangeProperty(AVATAR_PROPERTY, oldValue, newValue);
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
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(NAME_PROPERTY.equals(property)){
			return getName();
		}
		if(AVATAR_PROPERTY.equals(property)){
			return getAvatar();
		}
		if(ADDRESS_PROPERTY.equals(property)){
			return getAddress();
		}
		if(CREATE_TIME_PROPERTY.equals(property)){
			return getCreateTime();
		}
		if(PLATFORM_PROPERTY.equals(property)){
			return getPlatform();
		}
		if(STUDENT_LIST.equals(property)){
			List<BaseEntity> list = getStudentList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}
		if(QUESTION_LIST.equals(property)){
			List<BaseEntity> list = getQuestionList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}
		if(CLASS_DAILY_HEALTH_SURVEY_LIST.equals(property)){
			List<BaseEntity> list = getClassDailyHealthSurveyList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}
		if(WECHAT_LOGIN_INFO_LIST.equals(property)){
			List<BaseEntity> list = getWechatLoginInfoList().stream().map(item->item).collect(Collectors.toList());
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
	public User updateId(String id){
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
	public User updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setAvatar(String avatar){
		this.mAvatar = trimString(encodeUrl(avatar));;
	}
	public String getAvatar(){
		return this.mAvatar;
	}
	public User updateAvatar(String avatar){
		this.mAvatar = trimString(encodeUrl(avatar));;
		this.changed = true;
		return this;
	}
	public void mergeAvatar(String avatar){
		if(avatar != null) { setAvatar(avatar);}
	}
	
	
	public void setAddress(Location address){
		this.mAddress = address;;
	}
	public Location getAddress(){
		return this.mAddress;
	}
	public User updateAddress(Location address){
		this.mAddress = address;;
		this.changed = true;
		return this;
	}
	public void mergeAddress(Location address){
		if(address != null) { setAddress(address);}
	}
	
	
	public void clearAddress(){
		setAddress ( null );
		this.changed = true;
	}
	
	public void setCreateTime(DateTime createTime){
		this.mCreateTime = createTime;;
	}
	public DateTime getCreateTime(){
		return this.mCreateTime;
	}
	public User updateCreateTime(DateTime createTime){
		this.mCreateTime = createTime;;
		this.changed = true;
		return this;
	}
	public void mergeCreateTime(DateTime createTime){
		setCreateTime(createTime);
	}
	
	
	public void setPlatform(Platform platform){
		this.mPlatform = platform;;
	}
	public Platform getPlatform(){
		return this.mPlatform;
	}
	public User updatePlatform(Platform platform){
		this.mPlatform = platform;;
		this.changed = true;
		return this;
	}
	public void mergePlatform(Platform platform){
		if(platform != null) { setPlatform(platform);}
	}
	
	
	public void clearPlatform(){
		setPlatform ( null );
		this.changed = true;
	}
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public User updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<Student> getStudentList(){
		if(this.mStudentList == null){
			this.mStudentList = new SmartList<Student>();
			this.mStudentList.setListInternalName (STUDENT_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mStudentList;	
	}
	public  void setStudentList(SmartList<Student> studentList){
		for( Student student:studentList){
			student.setUser(this);
		}

		this.mStudentList = studentList;
		this.mStudentList.setListInternalName (STUDENT_LIST );
		
	}
	
	public  void addStudent(Student student){
		student.setUser(this);
		getStudentList().add(student);
	}
	public  void addStudentList(SmartList<Student> studentList){
		for( Student student:studentList){
			student.setUser(this);
		}
		getStudentList().addAll(studentList);
	}
	public  void mergeStudentList(SmartList<Student> studentList){
		if(studentList==null){
			return;
		}
		if(studentList.isEmpty()){
			return;
		}
		addStudentList( studentList );
		
	}
	public  Student removeStudent(Student studentIndex){
		
		int index = getStudentList().indexOf(studentIndex);
        if(index < 0){
        	String message = "Student("+studentIndex.getId()+") with version='"+studentIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        Student student = getStudentList().get(index);        
        // student.clearUser(); //disconnect with User
        student.clearFromAll(); //disconnect with User
		
		boolean result = getStudentList().planToRemove(student);
        if(!result){
        	String message = "Student("+studentIndex.getId()+") with version='"+studentIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return student;
        
	
	}
	//断舍离
	public  void breakWithStudent(Student student){
		
		if(student == null){
			return;
		}
		student.setUser(null);
		//getStudentList().remove();
	
	}
	
	public  boolean hasStudent(Student student){
	
		return getStudentList().contains(student);
  
	}
	
	public void copyStudentFrom(Student student) {

		Student studentInList = findTheStudent(student);
		Student newStudent = new Student();
		studentInList.copyTo(newStudent);
		newStudent.setVersion(0);//will trigger copy
		getStudentList().add(newStudent);
		addItemToFlexiableObject(COPIED_CHILD, newStudent);
	}
	
	public  Student findTheStudent(Student student){
		
		int index =  getStudentList().indexOf(student);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "Student("+student.getId()+") with version='"+student.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getStudentList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpStudentList(){
		getStudentList().clear();
	}
	
	
	


	public  SmartList<Question> getQuestionList(){
		if(this.mQuestionList == null){
			this.mQuestionList = new SmartList<Question>();
			this.mQuestionList.setListInternalName (QUESTION_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mQuestionList;	
	}
	public  void setQuestionList(SmartList<Question> questionList){
		for( Question question:questionList){
			question.setCreator(this);
		}

		this.mQuestionList = questionList;
		this.mQuestionList.setListInternalName (QUESTION_LIST );
		
	}
	
	public  void addQuestion(Question question){
		question.setCreator(this);
		getQuestionList().add(question);
	}
	public  void addQuestionList(SmartList<Question> questionList){
		for( Question question:questionList){
			question.setCreator(this);
		}
		getQuestionList().addAll(questionList);
	}
	public  void mergeQuestionList(SmartList<Question> questionList){
		if(questionList==null){
			return;
		}
		if(questionList.isEmpty()){
			return;
		}
		addQuestionList( questionList );
		
	}
	public  Question removeQuestion(Question questionIndex){
		
		int index = getQuestionList().indexOf(questionIndex);
        if(index < 0){
        	String message = "Question("+questionIndex.getId()+") with version='"+questionIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        Question question = getQuestionList().get(index);        
        // question.clearCreator(); //disconnect with Creator
        question.clearFromAll(); //disconnect with Creator
		
		boolean result = getQuestionList().planToRemove(question);
        if(!result){
        	String message = "Question("+questionIndex.getId()+") with version='"+questionIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return question;
        
	
	}
	//断舍离
	public  void breakWithQuestion(Question question){
		
		if(question == null){
			return;
		}
		question.setCreator(null);
		//getQuestionList().remove();
	
	}
	
	public  boolean hasQuestion(Question question){
	
		return getQuestionList().contains(question);
  
	}
	
	public void copyQuestionFrom(Question question) {

		Question questionInList = findTheQuestion(question);
		Question newQuestion = new Question();
		questionInList.copyTo(newQuestion);
		newQuestion.setVersion(0);//will trigger copy
		getQuestionList().add(newQuestion);
		addItemToFlexiableObject(COPIED_CHILD, newQuestion);
	}
	
	public  Question findTheQuestion(Question question){
		
		int index =  getQuestionList().indexOf(question);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "Question("+question.getId()+") with version='"+question.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getQuestionList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpQuestionList(){
		getQuestionList().clear();
	}
	
	
	


	public  SmartList<ClassDailyHealthSurvey> getClassDailyHealthSurveyList(){
		if(this.mClassDailyHealthSurveyList == null){
			this.mClassDailyHealthSurveyList = new SmartList<ClassDailyHealthSurvey>();
			this.mClassDailyHealthSurveyList.setListInternalName (CLASS_DAILY_HEALTH_SURVEY_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mClassDailyHealthSurveyList;	
	}
	public  void setClassDailyHealthSurveyList(SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList){
		for( ClassDailyHealthSurvey classDailyHealthSurvey:classDailyHealthSurveyList){
			classDailyHealthSurvey.setCreator(this);
		}

		this.mClassDailyHealthSurveyList = classDailyHealthSurveyList;
		this.mClassDailyHealthSurveyList.setListInternalName (CLASS_DAILY_HEALTH_SURVEY_LIST );
		
	}
	
	public  void addClassDailyHealthSurvey(ClassDailyHealthSurvey classDailyHealthSurvey){
		classDailyHealthSurvey.setCreator(this);
		getClassDailyHealthSurveyList().add(classDailyHealthSurvey);
	}
	public  void addClassDailyHealthSurveyList(SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList){
		for( ClassDailyHealthSurvey classDailyHealthSurvey:classDailyHealthSurveyList){
			classDailyHealthSurvey.setCreator(this);
		}
		getClassDailyHealthSurveyList().addAll(classDailyHealthSurveyList);
	}
	public  void mergeClassDailyHealthSurveyList(SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList){
		if(classDailyHealthSurveyList==null){
			return;
		}
		if(classDailyHealthSurveyList.isEmpty()){
			return;
		}
		addClassDailyHealthSurveyList( classDailyHealthSurveyList );
		
	}
	public  ClassDailyHealthSurvey removeClassDailyHealthSurvey(ClassDailyHealthSurvey classDailyHealthSurveyIndex){
		
		int index = getClassDailyHealthSurveyList().indexOf(classDailyHealthSurveyIndex);
        if(index < 0){
        	String message = "ClassDailyHealthSurvey("+classDailyHealthSurveyIndex.getId()+") with version='"+classDailyHealthSurveyIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        ClassDailyHealthSurvey classDailyHealthSurvey = getClassDailyHealthSurveyList().get(index);        
        // classDailyHealthSurvey.clearCreator(); //disconnect with Creator
        classDailyHealthSurvey.clearFromAll(); //disconnect with Creator
		
		boolean result = getClassDailyHealthSurveyList().planToRemove(classDailyHealthSurvey);
        if(!result){
        	String message = "ClassDailyHealthSurvey("+classDailyHealthSurveyIndex.getId()+") with version='"+classDailyHealthSurveyIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return classDailyHealthSurvey;
        
	
	}
	//断舍离
	public  void breakWithClassDailyHealthSurvey(ClassDailyHealthSurvey classDailyHealthSurvey){
		
		if(classDailyHealthSurvey == null){
			return;
		}
		classDailyHealthSurvey.setCreator(null);
		//getClassDailyHealthSurveyList().remove();
	
	}
	
	public  boolean hasClassDailyHealthSurvey(ClassDailyHealthSurvey classDailyHealthSurvey){
	
		return getClassDailyHealthSurveyList().contains(classDailyHealthSurvey);
  
	}
	
	public void copyClassDailyHealthSurveyFrom(ClassDailyHealthSurvey classDailyHealthSurvey) {

		ClassDailyHealthSurvey classDailyHealthSurveyInList = findTheClassDailyHealthSurvey(classDailyHealthSurvey);
		ClassDailyHealthSurvey newClassDailyHealthSurvey = new ClassDailyHealthSurvey();
		classDailyHealthSurveyInList.copyTo(newClassDailyHealthSurvey);
		newClassDailyHealthSurvey.setVersion(0);//will trigger copy
		getClassDailyHealthSurveyList().add(newClassDailyHealthSurvey);
		addItemToFlexiableObject(COPIED_CHILD, newClassDailyHealthSurvey);
	}
	
	public  ClassDailyHealthSurvey findTheClassDailyHealthSurvey(ClassDailyHealthSurvey classDailyHealthSurvey){
		
		int index =  getClassDailyHealthSurveyList().indexOf(classDailyHealthSurvey);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "ClassDailyHealthSurvey("+classDailyHealthSurvey.getId()+") with version='"+classDailyHealthSurvey.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getClassDailyHealthSurveyList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpClassDailyHealthSurveyList(){
		getClassDailyHealthSurveyList().clear();
	}
	
	
	


	public  SmartList<WechatLoginInfo> getWechatLoginInfoList(){
		if(this.mWechatLoginInfoList == null){
			this.mWechatLoginInfoList = new SmartList<WechatLoginInfo>();
			this.mWechatLoginInfoList.setListInternalName (WECHAT_LOGIN_INFO_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mWechatLoginInfoList;	
	}
	public  void setWechatLoginInfoList(SmartList<WechatLoginInfo> wechatLoginInfoList){
		for( WechatLoginInfo wechatLoginInfo:wechatLoginInfoList){
			wechatLoginInfo.setUser(this);
		}

		this.mWechatLoginInfoList = wechatLoginInfoList;
		this.mWechatLoginInfoList.setListInternalName (WECHAT_LOGIN_INFO_LIST );
		
	}
	
	public  void addWechatLoginInfo(WechatLoginInfo wechatLoginInfo){
		wechatLoginInfo.setUser(this);
		getWechatLoginInfoList().add(wechatLoginInfo);
	}
	public  void addWechatLoginInfoList(SmartList<WechatLoginInfo> wechatLoginInfoList){
		for( WechatLoginInfo wechatLoginInfo:wechatLoginInfoList){
			wechatLoginInfo.setUser(this);
		}
		getWechatLoginInfoList().addAll(wechatLoginInfoList);
	}
	public  void mergeWechatLoginInfoList(SmartList<WechatLoginInfo> wechatLoginInfoList){
		if(wechatLoginInfoList==null){
			return;
		}
		if(wechatLoginInfoList.isEmpty()){
			return;
		}
		addWechatLoginInfoList( wechatLoginInfoList );
		
	}
	public  WechatLoginInfo removeWechatLoginInfo(WechatLoginInfo wechatLoginInfoIndex){
		
		int index = getWechatLoginInfoList().indexOf(wechatLoginInfoIndex);
        if(index < 0){
        	String message = "WechatLoginInfo("+wechatLoginInfoIndex.getId()+") with version='"+wechatLoginInfoIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        WechatLoginInfo wechatLoginInfo = getWechatLoginInfoList().get(index);        
        // wechatLoginInfo.clearUser(); //disconnect with User
        wechatLoginInfo.clearFromAll(); //disconnect with User
		
		boolean result = getWechatLoginInfoList().planToRemove(wechatLoginInfo);
        if(!result){
        	String message = "WechatLoginInfo("+wechatLoginInfoIndex.getId()+") with version='"+wechatLoginInfoIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return wechatLoginInfo;
        
	
	}
	//断舍离
	public  void breakWithWechatLoginInfo(WechatLoginInfo wechatLoginInfo){
		
		if(wechatLoginInfo == null){
			return;
		}
		wechatLoginInfo.setUser(null);
		//getWechatLoginInfoList().remove();
	
	}
	
	public  boolean hasWechatLoginInfo(WechatLoginInfo wechatLoginInfo){
	
		return getWechatLoginInfoList().contains(wechatLoginInfo);
  
	}
	
	public void copyWechatLoginInfoFrom(WechatLoginInfo wechatLoginInfo) {

		WechatLoginInfo wechatLoginInfoInList = findTheWechatLoginInfo(wechatLoginInfo);
		WechatLoginInfo newWechatLoginInfo = new WechatLoginInfo();
		wechatLoginInfoInList.copyTo(newWechatLoginInfo);
		newWechatLoginInfo.setVersion(0);//will trigger copy
		getWechatLoginInfoList().add(newWechatLoginInfo);
		addItemToFlexiableObject(COPIED_CHILD, newWechatLoginInfo);
	}
	
	public  WechatLoginInfo findTheWechatLoginInfo(WechatLoginInfo wechatLoginInfo){
		
		int index =  getWechatLoginInfoList().indexOf(wechatLoginInfo);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "WechatLoginInfo("+wechatLoginInfo.getId()+") with version='"+wechatLoginInfo.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getWechatLoginInfoList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpWechatLoginInfoList(){
		getWechatLoginInfoList().clear();
	}
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getAddress(), internalType);
		addToEntityList(this, entityList, getPlatform(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getStudentList(), internalType);
		collectFromList(this, entityList, getQuestionList(), internalType);
		collectFromList(this, entityList, getClassDailyHealthSurveyList(), internalType);
		collectFromList(this, entityList, getWechatLoginInfoList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getStudentList());
		listOfList.add( getQuestionList());
		listOfList.add( getClassDailyHealthSurveyList());
		listOfList.add( getWechatLoginInfoList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, AVATAR_PROPERTY, getAvatar());
		appendKeyValuePair(result, ADDRESS_PROPERTY, getAddress());
		appendKeyValuePair(result, CREATE_TIME_PROPERTY, getCreateTime());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, STUDENT_LIST, getStudentList());
		if(!getStudentList().isEmpty()){
			appendKeyValuePair(result, "studentCount", getStudentList().getTotalCount());
			appendKeyValuePair(result, "studentCurrentPageNumber", getStudentList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, QUESTION_LIST, getQuestionList());
		if(!getQuestionList().isEmpty()){
			appendKeyValuePair(result, "questionCount", getQuestionList().getTotalCount());
			appendKeyValuePair(result, "questionCurrentPageNumber", getQuestionList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, CLASS_DAILY_HEALTH_SURVEY_LIST, getClassDailyHealthSurveyList());
		if(!getClassDailyHealthSurveyList().isEmpty()){
			appendKeyValuePair(result, "classDailyHealthSurveyCount", getClassDailyHealthSurveyList().getTotalCount());
			appendKeyValuePair(result, "classDailyHealthSurveyCurrentPageNumber", getClassDailyHealthSurveyList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, WECHAT_LOGIN_INFO_LIST, getWechatLoginInfoList());
		if(!getWechatLoginInfoList().isEmpty()){
			appendKeyValuePair(result, "wechatLoginInfoCount", getWechatLoginInfoList().getTotalCount());
			appendKeyValuePair(result, "wechatLoginInfoCurrentPageNumber", getWechatLoginInfoList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof User){
		
		
			User dest =(User)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setAvatar(getAvatar());
			dest.setAddress(getAddress());
			dest.setCreateTime(getCreateTime());
			dest.setPlatform(getPlatform());
			dest.setVersion(getVersion());
			dest.setStudentList(getStudentList());
			dest.setQuestionList(getQuestionList());
			dest.setClassDailyHealthSurveyList(getClassDailyHealthSurveyList());
			dest.setWechatLoginInfoList(getWechatLoginInfoList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof User){
		
			
			User dest =(User)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeAvatar(getAvatar());
			dest.mergeAddress(getAddress());
			dest.mergeCreateTime(getCreateTime());
			dest.mergePlatform(getPlatform());
			dest.mergeVersion(getVersion());
			dest.mergeStudentList(getStudentList());
			dest.mergeQuestionList(getQuestionList());
			dest.mergeClassDailyHealthSurveyList(getClassDailyHealthSurveyList());
			dest.mergeWechatLoginInfoList(getWechatLoginInfoList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof User){
		
			
			User dest =(User)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeAvatar(getAvatar());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getName(), getAvatar(), getAddress(), getCreateTime(), getPlatform(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("User{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		stringBuilder.append("\tavatar='"+getAvatar()+"';");
		if(getAddress() != null ){
 			stringBuilder.append("\taddress='Location("+getAddress().getId()+")';");
 		}
		stringBuilder.append("\tcreateTime='"+getCreateTime()+"';");
		if(getPlatform() != null ){
 			stringBuilder.append("\tplatform='Platform("+getPlatform().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

