
package com.doublechaintech.health.changerequest;

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
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.changerequesttype.ChangeRequestType;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.question.Question;

@JsonSerialize(using = ChangeRequestSerializer.class)
public class ChangeRequest extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String CREATE_TIME_PROPERTY           = "createTime"        ;
	public static final String REMOTE_IP_PROPERTY             = "remoteIp"          ;
	public static final String REQUEST_TYPE_PROPERTY          = "requestType"       ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String TEACHER_LIST                             = "teacherList"       ;
	public static final String STUDENT_LIST                             = "studentList"       ;
	public static final String QUESTION_LIST                            = "questionList"      ;
	public static final String CLASS_DAILY_HEALTH_SURVEY_LIST           = "classDailyHealthSurveyList";
	public static final String STUDENT_HEALTH_SURVEY_LIST               = "studentHealthSurveyList";
	public static final String STUDENT_DAILY_ANSWER_LIST                = "studentDailyAnswerList";

	public static final String INTERNAL_TYPE="ChangeRequest";
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
	protected		DateTime            	mCreateTime         ;
	protected		String              	mRemoteIp           ;
	protected		ChangeRequestType   	mRequestType        ;
	protected		Platform            	mPlatform           ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<Teacher>  	mTeacherList        ;
	protected		SmartList<Student>  	mStudentList        ;
	protected		SmartList<Question> 	mQuestionList       ;
	protected		SmartList<ClassDailyHealthSurvey>	mClassDailyHealthSurveyList;
	protected		SmartList<StudentHealthSurvey>	mStudentHealthSurveyList;
	protected		SmartList<StudentDailyAnswer>	mStudentDailyAnswerList;
	
		
	public 	ChangeRequest(){
		// lazy load for all the properties
	}
	public 	static ChangeRequest withId(String id){
		ChangeRequest changeRequest = new ChangeRequest();
		changeRequest.setId(id);
		changeRequest.setVersion(Integer.MAX_VALUE);
		return changeRequest;
	}
	public 	static ChangeRequest refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setRequestType( null );
		setPlatform( null );

		this.changed = true;
	}
	
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
		}
		if(CREATE_TIME_PROPERTY.equals(property)){
			changeCreateTimeProperty(newValueExpr);
		}
		if(REMOTE_IP_PROPERTY.equals(property)){
			changeRemoteIpProperty(newValueExpr);
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
			
			
			
	protected void changeRemoteIpProperty(String newValueExpr){
		String oldValue = getRemoteIp();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateRemoteIp(newValue);
		this.onChangeProperty(REMOTE_IP_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(NAME_PROPERTY.equals(property)){
			return getName();
		}
		if(CREATE_TIME_PROPERTY.equals(property)){
			return getCreateTime();
		}
		if(REMOTE_IP_PROPERTY.equals(property)){
			return getRemoteIp();
		}
		if(REQUEST_TYPE_PROPERTY.equals(property)){
			return getRequestType();
		}
		if(PLATFORM_PROPERTY.equals(property)){
			return getPlatform();
		}
		if(TEACHER_LIST.equals(property)){
			List<BaseEntity> list = getTeacherList().stream().map(item->item).collect(Collectors.toList());
			return list;
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
		if(STUDENT_HEALTH_SURVEY_LIST.equals(property)){
			List<BaseEntity> list = getStudentHealthSurveyList().stream().map(item->item).collect(Collectors.toList());
			return list;
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
	public ChangeRequest updateId(String id){
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
	public ChangeRequest updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setCreateTime(DateTime createTime){
		this.mCreateTime = createTime;;
	}
	public DateTime getCreateTime(){
		return this.mCreateTime;
	}
	public ChangeRequest updateCreateTime(DateTime createTime){
		this.mCreateTime = createTime;;
		this.changed = true;
		return this;
	}
	public void mergeCreateTime(DateTime createTime){
		setCreateTime(createTime);
	}
	
	
	public void setRemoteIp(String remoteIp){
		this.mRemoteIp = trimString(remoteIp);;
	}
	public String getRemoteIp(){
		return this.mRemoteIp;
	}
	public ChangeRequest updateRemoteIp(String remoteIp){
		this.mRemoteIp = trimString(remoteIp);;
		this.changed = true;
		return this;
	}
	public void mergeRemoteIp(String remoteIp){
		if(remoteIp != null) { setRemoteIp(remoteIp);}
	}
	
	
	public void setRequestType(ChangeRequestType requestType){
		this.mRequestType = requestType;;
	}
	public ChangeRequestType getRequestType(){
		return this.mRequestType;
	}
	public ChangeRequest updateRequestType(ChangeRequestType requestType){
		this.mRequestType = requestType;;
		this.changed = true;
		return this;
	}
	public void mergeRequestType(ChangeRequestType requestType){
		if(requestType != null) { setRequestType(requestType);}
	}
	
	
	public void clearRequestType(){
		setRequestType ( null );
		this.changed = true;
	}
	
	public void setPlatform(Platform platform){
		this.mPlatform = platform;;
	}
	public Platform getPlatform(){
		return this.mPlatform;
	}
	public ChangeRequest updatePlatform(Platform platform){
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
	public ChangeRequest updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<Teacher> getTeacherList(){
		if(this.mTeacherList == null){
			this.mTeacherList = new SmartList<Teacher>();
			this.mTeacherList.setListInternalName (TEACHER_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mTeacherList;	
	}
	public  void setTeacherList(SmartList<Teacher> teacherList){
		for( Teacher teacher:teacherList){
			teacher.setCq(this);
		}

		this.mTeacherList = teacherList;
		this.mTeacherList.setListInternalName (TEACHER_LIST );
		
	}
	
	public  void addTeacher(Teacher teacher){
		teacher.setCq(this);
		getTeacherList().add(teacher);
	}
	public  void addTeacherList(SmartList<Teacher> teacherList){
		for( Teacher teacher:teacherList){
			teacher.setCq(this);
		}
		getTeacherList().addAll(teacherList);
	}
	public  void mergeTeacherList(SmartList<Teacher> teacherList){
		if(teacherList==null){
			return;
		}
		if(teacherList.isEmpty()){
			return;
		}
		addTeacherList( teacherList );
		
	}
	public  Teacher removeTeacher(Teacher teacherIndex){
		
		int index = getTeacherList().indexOf(teacherIndex);
        if(index < 0){
        	String message = "Teacher("+teacherIndex.getId()+") with version='"+teacherIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        Teacher teacher = getTeacherList().get(index);        
        // teacher.clearCq(); //disconnect with Cq
        teacher.clearFromAll(); //disconnect with Cq
		
		boolean result = getTeacherList().planToRemove(teacher);
        if(!result){
        	String message = "Teacher("+teacherIndex.getId()+") with version='"+teacherIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return teacher;
        
	
	}
	//断舍离
	public  void breakWithTeacher(Teacher teacher){
		
		if(teacher == null){
			return;
		}
		teacher.setCq(null);
		//getTeacherList().remove();
	
	}
	
	public  boolean hasTeacher(Teacher teacher){
	
		return getTeacherList().contains(teacher);
  
	}
	
	public void copyTeacherFrom(Teacher teacher) {

		Teacher teacherInList = findTheTeacher(teacher);
		Teacher newTeacher = new Teacher();
		teacherInList.copyTo(newTeacher);
		newTeacher.setVersion(0);//will trigger copy
		getTeacherList().add(newTeacher);
		addItemToFlexiableObject(COPIED_CHILD, newTeacher);
	}
	
	public  Teacher findTheTeacher(Teacher teacher){
		
		int index =  getTeacherList().indexOf(teacher);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "Teacher("+teacher.getId()+") with version='"+teacher.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getTeacherList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpTeacherList(){
		getTeacherList().clear();
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
			student.setCq(this);
		}

		this.mStudentList = studentList;
		this.mStudentList.setListInternalName (STUDENT_LIST );
		
	}
	
	public  void addStudent(Student student){
		student.setCq(this);
		getStudentList().add(student);
	}
	public  void addStudentList(SmartList<Student> studentList){
		for( Student student:studentList){
			student.setCq(this);
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
        // student.clearCq(); //disconnect with Cq
        student.clearFromAll(); //disconnect with Cq
		
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
		student.setCq(null);
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
			question.setCq(this);
		}

		this.mQuestionList = questionList;
		this.mQuestionList.setListInternalName (QUESTION_LIST );
		
	}
	
	public  void addQuestion(Question question){
		question.setCq(this);
		getQuestionList().add(question);
	}
	public  void addQuestionList(SmartList<Question> questionList){
		for( Question question:questionList){
			question.setCq(this);
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
        // question.clearCq(); //disconnect with Cq
        question.clearFromAll(); //disconnect with Cq
		
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
		question.setCq(null);
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
			classDailyHealthSurvey.setCq(this);
		}

		this.mClassDailyHealthSurveyList = classDailyHealthSurveyList;
		this.mClassDailyHealthSurveyList.setListInternalName (CLASS_DAILY_HEALTH_SURVEY_LIST );
		
	}
	
	public  void addClassDailyHealthSurvey(ClassDailyHealthSurvey classDailyHealthSurvey){
		classDailyHealthSurvey.setCq(this);
		getClassDailyHealthSurveyList().add(classDailyHealthSurvey);
	}
	public  void addClassDailyHealthSurveyList(SmartList<ClassDailyHealthSurvey> classDailyHealthSurveyList){
		for( ClassDailyHealthSurvey classDailyHealthSurvey:classDailyHealthSurveyList){
			classDailyHealthSurvey.setCq(this);
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
        // classDailyHealthSurvey.clearCq(); //disconnect with Cq
        classDailyHealthSurvey.clearFromAll(); //disconnect with Cq
		
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
		classDailyHealthSurvey.setCq(null);
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
			studentHealthSurvey.setCq(this);
		}

		this.mStudentHealthSurveyList = studentHealthSurveyList;
		this.mStudentHealthSurveyList.setListInternalName (STUDENT_HEALTH_SURVEY_LIST );
		
	}
	
	public  void addStudentHealthSurvey(StudentHealthSurvey studentHealthSurvey){
		studentHealthSurvey.setCq(this);
		getStudentHealthSurveyList().add(studentHealthSurvey);
	}
	public  void addStudentHealthSurveyList(SmartList<StudentHealthSurvey> studentHealthSurveyList){
		for( StudentHealthSurvey studentHealthSurvey:studentHealthSurveyList){
			studentHealthSurvey.setCq(this);
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
        // studentHealthSurvey.clearCq(); //disconnect with Cq
        studentHealthSurvey.clearFromAll(); //disconnect with Cq
		
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
		studentHealthSurvey.setCq(null);
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
			studentDailyAnswer.setCq(this);
		}

		this.mStudentDailyAnswerList = studentDailyAnswerList;
		this.mStudentDailyAnswerList.setListInternalName (STUDENT_DAILY_ANSWER_LIST );
		
	}
	
	public  void addStudentDailyAnswer(StudentDailyAnswer studentDailyAnswer){
		studentDailyAnswer.setCq(this);
		getStudentDailyAnswerList().add(studentDailyAnswer);
	}
	public  void addStudentDailyAnswerList(SmartList<StudentDailyAnswer> studentDailyAnswerList){
		for( StudentDailyAnswer studentDailyAnswer:studentDailyAnswerList){
			studentDailyAnswer.setCq(this);
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
        // studentDailyAnswer.clearCq(); //disconnect with Cq
        studentDailyAnswer.clearFromAll(); //disconnect with Cq
		
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
		studentDailyAnswer.setCq(null);
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

		addToEntityList(this, entityList, getRequestType(), internalType);
		addToEntityList(this, entityList, getPlatform(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getTeacherList(), internalType);
		collectFromList(this, entityList, getStudentList(), internalType);
		collectFromList(this, entityList, getQuestionList(), internalType);
		collectFromList(this, entityList, getClassDailyHealthSurveyList(), internalType);
		collectFromList(this, entityList, getStudentHealthSurveyList(), internalType);
		collectFromList(this, entityList, getStudentDailyAnswerList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getTeacherList());
		listOfList.add( getStudentList());
		listOfList.add( getQuestionList());
		listOfList.add( getClassDailyHealthSurveyList());
		listOfList.add( getStudentHealthSurveyList());
		listOfList.add( getStudentDailyAnswerList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, CREATE_TIME_PROPERTY, getCreateTime());
		appendKeyValuePair(result, REMOTE_IP_PROPERTY, getRemoteIp());
		appendKeyValuePair(result, REQUEST_TYPE_PROPERTY, getRequestType());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, TEACHER_LIST, getTeacherList());
		if(!getTeacherList().isEmpty()){
			appendKeyValuePair(result, "teacherCount", getTeacherList().getTotalCount());
			appendKeyValuePair(result, "teacherCurrentPageNumber", getTeacherList().getCurrentPageNumber());
		}
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
		appendKeyValuePair(result, STUDENT_HEALTH_SURVEY_LIST, getStudentHealthSurveyList());
		if(!getStudentHealthSurveyList().isEmpty()){
			appendKeyValuePair(result, "studentHealthSurveyCount", getStudentHealthSurveyList().getTotalCount());
			appendKeyValuePair(result, "studentHealthSurveyCurrentPageNumber", getStudentHealthSurveyList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, STUDENT_DAILY_ANSWER_LIST, getStudentDailyAnswerList());
		if(!getStudentDailyAnswerList().isEmpty()){
			appendKeyValuePair(result, "studentDailyAnswerCount", getStudentDailyAnswerList().getTotalCount());
			appendKeyValuePair(result, "studentDailyAnswerCurrentPageNumber", getStudentDailyAnswerList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof ChangeRequest){
		
		
			ChangeRequest dest =(ChangeRequest)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setCreateTime(getCreateTime());
			dest.setRemoteIp(getRemoteIp());
			dest.setRequestType(getRequestType());
			dest.setPlatform(getPlatform());
			dest.setVersion(getVersion());
			dest.setTeacherList(getTeacherList());
			dest.setStudentList(getStudentList());
			dest.setQuestionList(getQuestionList());
			dest.setClassDailyHealthSurveyList(getClassDailyHealthSurveyList());
			dest.setStudentHealthSurveyList(getStudentHealthSurveyList());
			dest.setStudentDailyAnswerList(getStudentDailyAnswerList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof ChangeRequest){
		
			
			ChangeRequest dest =(ChangeRequest)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeRemoteIp(getRemoteIp());
			dest.mergeRequestType(getRequestType());
			dest.mergePlatform(getPlatform());
			dest.mergeVersion(getVersion());
			dest.mergeTeacherList(getTeacherList());
			dest.mergeStudentList(getStudentList());
			dest.mergeQuestionList(getQuestionList());
			dest.mergeClassDailyHealthSurveyList(getClassDailyHealthSurveyList());
			dest.mergeStudentHealthSurveyList(getStudentHealthSurveyList());
			dest.mergeStudentDailyAnswerList(getStudentDailyAnswerList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof ChangeRequest){
		
			
			ChangeRequest dest =(ChangeRequest)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeRemoteIp(getRemoteIp());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getName(), getCreateTime(), getRemoteIp(), getRequestType(), getPlatform(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("ChangeRequest{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		stringBuilder.append("\tcreateTime='"+getCreateTime()+"';");
		stringBuilder.append("\tremoteIp='"+getRemoteIp()+"';");
		if(getRequestType() != null ){
 			stringBuilder.append("\trequestType='ChangeRequestType("+getRequestType().getId()+")';");
 		}
		if(getPlatform() != null ){
 			stringBuilder.append("\tplatform='Platform("+getPlatform().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

