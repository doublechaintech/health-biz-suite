
package com.doublechaintech.health.platform;

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
import com.doublechaintech.health.city.City;
import com.doublechaintech.health.changerequest.ChangeRequest;
import com.doublechaintech.health.teacher.Teacher;
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.changerequesttype.ChangeRequestType;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.surveystatus.SurveyStatus;
import com.doublechaintech.health.province.Province;
import com.doublechaintech.health.district.District;
import com.doublechaintech.health.question.Question;
import com.doublechaintech.health.user.User;

@JsonSerialize(using = PlatformSerializer.class)
public class Platform extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String DESCRIPTION_PROPERTY           = "description"       ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String PROVINCE_LIST                            = "provinceList"      ;
	public static final String CITY_LIST                                = "cityList"          ;
	public static final String DISTRICT_LIST                            = "districtList"      ;
	public static final String TEACHER_LIST                             = "teacherList"       ;
	public static final String STUDENT_LIST                             = "studentList"       ;
	public static final String QUESTION_LIST                            = "questionList"      ;
	public static final String QUESTION_TYPE_LIST                       = "questionTypeList"  ;
	public static final String SURVEY_STATUS_LIST                       = "surveyStatusList"  ;
	public static final String USER_LIST                                = "userList"          ;
	public static final String CHANGE_REQUEST_LIST                      = "changeRequestList" ;
	public static final String CHANGE_REQUEST_TYPE_LIST                 = "changeRequestTypeList";

	public static final String INTERNAL_TYPE="Platform";
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
	protected		String              	mDescription        ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<Province> 	mProvinceList       ;
	protected		SmartList<City>     	mCityList           ;
	protected		SmartList<District> 	mDistrictList       ;
	protected		SmartList<Teacher>  	mTeacherList        ;
	protected		SmartList<Student>  	mStudentList        ;
	protected		SmartList<Question> 	mQuestionList       ;
	protected		SmartList<QuestionType>	mQuestionTypeList   ;
	protected		SmartList<SurveyStatus>	mSurveyStatusList   ;
	protected		SmartList<User>     	mUserList           ;
	protected		SmartList<ChangeRequest>	mChangeRequestList  ;
	protected		SmartList<ChangeRequestType>	mChangeRequestTypeList;
	
		
	public 	Platform(){
		// lazy load for all the properties
	}
	public 	static Platform withId(String id){
		Platform platform = new Platform();
		platform.setId(id);
		platform.setVersion(Integer.MAX_VALUE);
		return platform;
	}
	public 	static Platform refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){

		this.changed = true;
	}
	
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
		}
		if(DESCRIPTION_PROPERTY.equals(property)){
			changeDescriptionProperty(newValueExpr);
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
			
			
			
	protected void changeDescriptionProperty(String newValueExpr){
	
		String oldValue = getDescription();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateDescription(newValue);
		this.onChangeProperty(DESCRIPTION_PROPERTY, oldValue, newValue);
		return;
   
	}
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(NAME_PROPERTY.equals(property)){
			return getName();
		}
		if(DESCRIPTION_PROPERTY.equals(property)){
			return getDescription();
		}
		if(PROVINCE_LIST.equals(property)){
			List<BaseEntity> list = getProvinceList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}
		if(CITY_LIST.equals(property)){
			List<BaseEntity> list = getCityList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}
		if(DISTRICT_LIST.equals(property)){
			List<BaseEntity> list = getDistrictList().stream().map(item->item).collect(Collectors.toList());
			return list;
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
		if(QUESTION_TYPE_LIST.equals(property)){
			List<BaseEntity> list = getQuestionTypeList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}
		if(SURVEY_STATUS_LIST.equals(property)){
			List<BaseEntity> list = getSurveyStatusList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}
		if(USER_LIST.equals(property)){
			List<BaseEntity> list = getUserList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}
		if(CHANGE_REQUEST_LIST.equals(property)){
			List<BaseEntity> list = getChangeRequestList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}
		if(CHANGE_REQUEST_TYPE_LIST.equals(property)){
			List<BaseEntity> list = getChangeRequestTypeList().stream().map(item->item).collect(Collectors.toList());
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
	public Platform updateId(String id){
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
	public Platform updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setDescription(String description){
		this.mDescription = description;;
	}
	public String getDescription(){
		return this.mDescription;
	}
	public Platform updateDescription(String description){
		this.mDescription = description;;
		this.changed = true;
		return this;
	}
	public void mergeDescription(String description){
		if(description != null) { setDescription(description);}
	}
	
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public Platform updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<Province> getProvinceList(){
		if(this.mProvinceList == null){
			this.mProvinceList = new SmartList<Province>();
			this.mProvinceList.setListInternalName (PROVINCE_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mProvinceList;	
	}
	public  void setProvinceList(SmartList<Province> provinceList){
		for( Province province:provinceList){
			province.setPlatform(this);
		}

		this.mProvinceList = provinceList;
		this.mProvinceList.setListInternalName (PROVINCE_LIST );
		
	}
	
	public  void addProvince(Province province){
		province.setPlatform(this);
		getProvinceList().add(province);
	}
	public  void addProvinceList(SmartList<Province> provinceList){
		for( Province province:provinceList){
			province.setPlatform(this);
		}
		getProvinceList().addAll(provinceList);
	}
	public  void mergeProvinceList(SmartList<Province> provinceList){
		if(provinceList==null){
			return;
		}
		if(provinceList.isEmpty()){
			return;
		}
		addProvinceList( provinceList );
		
	}
	public  Province removeProvince(Province provinceIndex){
		
		int index = getProvinceList().indexOf(provinceIndex);
        if(index < 0){
        	String message = "Province("+provinceIndex.getId()+") with version='"+provinceIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        Province province = getProvinceList().get(index);        
        // province.clearPlatform(); //disconnect with Platform
        province.clearFromAll(); //disconnect with Platform
		
		boolean result = getProvinceList().planToRemove(province);
        if(!result){
        	String message = "Province("+provinceIndex.getId()+") with version='"+provinceIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return province;
        
	
	}
	//断舍离
	public  void breakWithProvince(Province province){
		
		if(province == null){
			return;
		}
		province.setPlatform(null);
		//getProvinceList().remove();
	
	}
	
	public  boolean hasProvince(Province province){
	
		return getProvinceList().contains(province);
  
	}
	
	public void copyProvinceFrom(Province province) {

		Province provinceInList = findTheProvince(province);
		Province newProvince = new Province();
		provinceInList.copyTo(newProvince);
		newProvince.setVersion(0);//will trigger copy
		getProvinceList().add(newProvince);
		addItemToFlexiableObject(COPIED_CHILD, newProvince);
	}
	
	public  Province findTheProvince(Province province){
		
		int index =  getProvinceList().indexOf(province);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "Province("+province.getId()+") with version='"+province.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getProvinceList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpProvinceList(){
		getProvinceList().clear();
	}
	
	
	


	public  SmartList<City> getCityList(){
		if(this.mCityList == null){
			this.mCityList = new SmartList<City>();
			this.mCityList.setListInternalName (CITY_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mCityList;	
	}
	public  void setCityList(SmartList<City> cityList){
		for( City city:cityList){
			city.setPlatform(this);
		}

		this.mCityList = cityList;
		this.mCityList.setListInternalName (CITY_LIST );
		
	}
	
	public  void addCity(City city){
		city.setPlatform(this);
		getCityList().add(city);
	}
	public  void addCityList(SmartList<City> cityList){
		for( City city:cityList){
			city.setPlatform(this);
		}
		getCityList().addAll(cityList);
	}
	public  void mergeCityList(SmartList<City> cityList){
		if(cityList==null){
			return;
		}
		if(cityList.isEmpty()){
			return;
		}
		addCityList( cityList );
		
	}
	public  City removeCity(City cityIndex){
		
		int index = getCityList().indexOf(cityIndex);
        if(index < 0){
        	String message = "City("+cityIndex.getId()+") with version='"+cityIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        City city = getCityList().get(index);        
        // city.clearPlatform(); //disconnect with Platform
        city.clearFromAll(); //disconnect with Platform
		
		boolean result = getCityList().planToRemove(city);
        if(!result){
        	String message = "City("+cityIndex.getId()+") with version='"+cityIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return city;
        
	
	}
	//断舍离
	public  void breakWithCity(City city){
		
		if(city == null){
			return;
		}
		city.setPlatform(null);
		//getCityList().remove();
	
	}
	
	public  boolean hasCity(City city){
	
		return getCityList().contains(city);
  
	}
	
	public void copyCityFrom(City city) {

		City cityInList = findTheCity(city);
		City newCity = new City();
		cityInList.copyTo(newCity);
		newCity.setVersion(0);//will trigger copy
		getCityList().add(newCity);
		addItemToFlexiableObject(COPIED_CHILD, newCity);
	}
	
	public  City findTheCity(City city){
		
		int index =  getCityList().indexOf(city);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "City("+city.getId()+") with version='"+city.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getCityList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpCityList(){
		getCityList().clear();
	}
	
	
	


	public  SmartList<District> getDistrictList(){
		if(this.mDistrictList == null){
			this.mDistrictList = new SmartList<District>();
			this.mDistrictList.setListInternalName (DISTRICT_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mDistrictList;	
	}
	public  void setDistrictList(SmartList<District> districtList){
		for( District district:districtList){
			district.setPlatform(this);
		}

		this.mDistrictList = districtList;
		this.mDistrictList.setListInternalName (DISTRICT_LIST );
		
	}
	
	public  void addDistrict(District district){
		district.setPlatform(this);
		getDistrictList().add(district);
	}
	public  void addDistrictList(SmartList<District> districtList){
		for( District district:districtList){
			district.setPlatform(this);
		}
		getDistrictList().addAll(districtList);
	}
	public  void mergeDistrictList(SmartList<District> districtList){
		if(districtList==null){
			return;
		}
		if(districtList.isEmpty()){
			return;
		}
		addDistrictList( districtList );
		
	}
	public  District removeDistrict(District districtIndex){
		
		int index = getDistrictList().indexOf(districtIndex);
        if(index < 0){
        	String message = "District("+districtIndex.getId()+") with version='"+districtIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        District district = getDistrictList().get(index);        
        // district.clearPlatform(); //disconnect with Platform
        district.clearFromAll(); //disconnect with Platform
		
		boolean result = getDistrictList().planToRemove(district);
        if(!result){
        	String message = "District("+districtIndex.getId()+") with version='"+districtIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return district;
        
	
	}
	//断舍离
	public  void breakWithDistrict(District district){
		
		if(district == null){
			return;
		}
		district.setPlatform(null);
		//getDistrictList().remove();
	
	}
	
	public  boolean hasDistrict(District district){
	
		return getDistrictList().contains(district);
  
	}
	
	public void copyDistrictFrom(District district) {

		District districtInList = findTheDistrict(district);
		District newDistrict = new District();
		districtInList.copyTo(newDistrict);
		newDistrict.setVersion(0);//will trigger copy
		getDistrictList().add(newDistrict);
		addItemToFlexiableObject(COPIED_CHILD, newDistrict);
	}
	
	public  District findTheDistrict(District district){
		
		int index =  getDistrictList().indexOf(district);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "District("+district.getId()+") with version='"+district.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getDistrictList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpDistrictList(){
		getDistrictList().clear();
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
			teacher.setPlatform(this);
		}

		this.mTeacherList = teacherList;
		this.mTeacherList.setListInternalName (TEACHER_LIST );
		
	}
	
	public  void addTeacher(Teacher teacher){
		teacher.setPlatform(this);
		getTeacherList().add(teacher);
	}
	public  void addTeacherList(SmartList<Teacher> teacherList){
		for( Teacher teacher:teacherList){
			teacher.setPlatform(this);
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
        // teacher.clearPlatform(); //disconnect with Platform
        teacher.clearFromAll(); //disconnect with Platform
		
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
		teacher.setPlatform(null);
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
			student.setPlatform(this);
		}

		this.mStudentList = studentList;
		this.mStudentList.setListInternalName (STUDENT_LIST );
		
	}
	
	public  void addStudent(Student student){
		student.setPlatform(this);
		getStudentList().add(student);
	}
	public  void addStudentList(SmartList<Student> studentList){
		for( Student student:studentList){
			student.setPlatform(this);
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
        // student.clearPlatform(); //disconnect with Platform
        student.clearFromAll(); //disconnect with Platform
		
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
		student.setPlatform(null);
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
			question.setPlatform(this);
		}

		this.mQuestionList = questionList;
		this.mQuestionList.setListInternalName (QUESTION_LIST );
		
	}
	
	public  void addQuestion(Question question){
		question.setPlatform(this);
		getQuestionList().add(question);
	}
	public  void addQuestionList(SmartList<Question> questionList){
		for( Question question:questionList){
			question.setPlatform(this);
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
        // question.clearPlatform(); //disconnect with Platform
        question.clearFromAll(); //disconnect with Platform
		
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
		question.setPlatform(null);
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
	
	
	


	public  SmartList<QuestionType> getQuestionTypeList(){
		if(this.mQuestionTypeList == null){
			this.mQuestionTypeList = new SmartList<QuestionType>();
			this.mQuestionTypeList.setListInternalName (QUESTION_TYPE_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mQuestionTypeList;	
	}
	public  void setQuestionTypeList(SmartList<QuestionType> questionTypeList){
		for( QuestionType questionType:questionTypeList){
			questionType.setPlatform(this);
		}

		this.mQuestionTypeList = questionTypeList;
		this.mQuestionTypeList.setListInternalName (QUESTION_TYPE_LIST );
		
	}
	
	public  void addQuestionType(QuestionType questionType){
		questionType.setPlatform(this);
		getQuestionTypeList().add(questionType);
	}
	public  void addQuestionTypeList(SmartList<QuestionType> questionTypeList){
		for( QuestionType questionType:questionTypeList){
			questionType.setPlatform(this);
		}
		getQuestionTypeList().addAll(questionTypeList);
	}
	public  void mergeQuestionTypeList(SmartList<QuestionType> questionTypeList){
		if(questionTypeList==null){
			return;
		}
		if(questionTypeList.isEmpty()){
			return;
		}
		addQuestionTypeList( questionTypeList );
		
	}
	public  QuestionType removeQuestionType(QuestionType questionTypeIndex){
		
		int index = getQuestionTypeList().indexOf(questionTypeIndex);
        if(index < 0){
        	String message = "QuestionType("+questionTypeIndex.getId()+") with version='"+questionTypeIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        QuestionType questionType = getQuestionTypeList().get(index);        
        // questionType.clearPlatform(); //disconnect with Platform
        questionType.clearFromAll(); //disconnect with Platform
		
		boolean result = getQuestionTypeList().planToRemove(questionType);
        if(!result){
        	String message = "QuestionType("+questionTypeIndex.getId()+") with version='"+questionTypeIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return questionType;
        
	
	}
	//断舍离
	public  void breakWithQuestionType(QuestionType questionType){
		
		if(questionType == null){
			return;
		}
		questionType.setPlatform(null);
		//getQuestionTypeList().remove();
	
	}
	
	public  boolean hasQuestionType(QuestionType questionType){
	
		return getQuestionTypeList().contains(questionType);
  
	}
	
	public void copyQuestionTypeFrom(QuestionType questionType) {

		QuestionType questionTypeInList = findTheQuestionType(questionType);
		QuestionType newQuestionType = new QuestionType();
		questionTypeInList.copyTo(newQuestionType);
		newQuestionType.setVersion(0);//will trigger copy
		getQuestionTypeList().add(newQuestionType);
		addItemToFlexiableObject(COPIED_CHILD, newQuestionType);
	}
	
	public  QuestionType findTheQuestionType(QuestionType questionType){
		
		int index =  getQuestionTypeList().indexOf(questionType);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "QuestionType("+questionType.getId()+") with version='"+questionType.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getQuestionTypeList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpQuestionTypeList(){
		getQuestionTypeList().clear();
	}
	
	
	


	public  SmartList<SurveyStatus> getSurveyStatusList(){
		if(this.mSurveyStatusList == null){
			this.mSurveyStatusList = new SmartList<SurveyStatus>();
			this.mSurveyStatusList.setListInternalName (SURVEY_STATUS_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mSurveyStatusList;	
	}
	public  void setSurveyStatusList(SmartList<SurveyStatus> surveyStatusList){
		for( SurveyStatus surveyStatus:surveyStatusList){
			surveyStatus.setPlatform(this);
		}

		this.mSurveyStatusList = surveyStatusList;
		this.mSurveyStatusList.setListInternalName (SURVEY_STATUS_LIST );
		
	}
	
	public  void addSurveyStatus(SurveyStatus surveyStatus){
		surveyStatus.setPlatform(this);
		getSurveyStatusList().add(surveyStatus);
	}
	public  void addSurveyStatusList(SmartList<SurveyStatus> surveyStatusList){
		for( SurveyStatus surveyStatus:surveyStatusList){
			surveyStatus.setPlatform(this);
		}
		getSurveyStatusList().addAll(surveyStatusList);
	}
	public  void mergeSurveyStatusList(SmartList<SurveyStatus> surveyStatusList){
		if(surveyStatusList==null){
			return;
		}
		if(surveyStatusList.isEmpty()){
			return;
		}
		addSurveyStatusList( surveyStatusList );
		
	}
	public  SurveyStatus removeSurveyStatus(SurveyStatus surveyStatusIndex){
		
		int index = getSurveyStatusList().indexOf(surveyStatusIndex);
        if(index < 0){
        	String message = "SurveyStatus("+surveyStatusIndex.getId()+") with version='"+surveyStatusIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        SurveyStatus surveyStatus = getSurveyStatusList().get(index);        
        // surveyStatus.clearPlatform(); //disconnect with Platform
        surveyStatus.clearFromAll(); //disconnect with Platform
		
		boolean result = getSurveyStatusList().planToRemove(surveyStatus);
        if(!result){
        	String message = "SurveyStatus("+surveyStatusIndex.getId()+") with version='"+surveyStatusIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return surveyStatus;
        
	
	}
	//断舍离
	public  void breakWithSurveyStatus(SurveyStatus surveyStatus){
		
		if(surveyStatus == null){
			return;
		}
		surveyStatus.setPlatform(null);
		//getSurveyStatusList().remove();
	
	}
	
	public  boolean hasSurveyStatus(SurveyStatus surveyStatus){
	
		return getSurveyStatusList().contains(surveyStatus);
  
	}
	
	public void copySurveyStatusFrom(SurveyStatus surveyStatus) {

		SurveyStatus surveyStatusInList = findTheSurveyStatus(surveyStatus);
		SurveyStatus newSurveyStatus = new SurveyStatus();
		surveyStatusInList.copyTo(newSurveyStatus);
		newSurveyStatus.setVersion(0);//will trigger copy
		getSurveyStatusList().add(newSurveyStatus);
		addItemToFlexiableObject(COPIED_CHILD, newSurveyStatus);
	}
	
	public  SurveyStatus findTheSurveyStatus(SurveyStatus surveyStatus){
		
		int index =  getSurveyStatusList().indexOf(surveyStatus);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "SurveyStatus("+surveyStatus.getId()+") with version='"+surveyStatus.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getSurveyStatusList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpSurveyStatusList(){
		getSurveyStatusList().clear();
	}
	
	
	


	public  SmartList<User> getUserList(){
		if(this.mUserList == null){
			this.mUserList = new SmartList<User>();
			this.mUserList.setListInternalName (USER_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mUserList;	
	}
	public  void setUserList(SmartList<User> userList){
		for( User user:userList){
			user.setPlatform(this);
		}

		this.mUserList = userList;
		this.mUserList.setListInternalName (USER_LIST );
		
	}
	
	public  void addUser(User user){
		user.setPlatform(this);
		getUserList().add(user);
	}
	public  void addUserList(SmartList<User> userList){
		for( User user:userList){
			user.setPlatform(this);
		}
		getUserList().addAll(userList);
	}
	public  void mergeUserList(SmartList<User> userList){
		if(userList==null){
			return;
		}
		if(userList.isEmpty()){
			return;
		}
		addUserList( userList );
		
	}
	public  User removeUser(User userIndex){
		
		int index = getUserList().indexOf(userIndex);
        if(index < 0){
        	String message = "User("+userIndex.getId()+") with version='"+userIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        User user = getUserList().get(index);        
        // user.clearPlatform(); //disconnect with Platform
        user.clearFromAll(); //disconnect with Platform
		
		boolean result = getUserList().planToRemove(user);
        if(!result){
        	String message = "User("+userIndex.getId()+") with version='"+userIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return user;
        
	
	}
	//断舍离
	public  void breakWithUser(User user){
		
		if(user == null){
			return;
		}
		user.setPlatform(null);
		//getUserList().remove();
	
	}
	
	public  boolean hasUser(User user){
	
		return getUserList().contains(user);
  
	}
	
	public void copyUserFrom(User user) {

		User userInList = findTheUser(user);
		User newUser = new User();
		userInList.copyTo(newUser);
		newUser.setVersion(0);//will trigger copy
		getUserList().add(newUser);
		addItemToFlexiableObject(COPIED_CHILD, newUser);
	}
	
	public  User findTheUser(User user){
		
		int index =  getUserList().indexOf(user);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "User("+user.getId()+") with version='"+user.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getUserList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpUserList(){
		getUserList().clear();
	}
	
	
	


	public  SmartList<ChangeRequest> getChangeRequestList(){
		if(this.mChangeRequestList == null){
			this.mChangeRequestList = new SmartList<ChangeRequest>();
			this.mChangeRequestList.setListInternalName (CHANGE_REQUEST_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mChangeRequestList;	
	}
	public  void setChangeRequestList(SmartList<ChangeRequest> changeRequestList){
		for( ChangeRequest changeRequest:changeRequestList){
			changeRequest.setPlatform(this);
		}

		this.mChangeRequestList = changeRequestList;
		this.mChangeRequestList.setListInternalName (CHANGE_REQUEST_LIST );
		
	}
	
	public  void addChangeRequest(ChangeRequest changeRequest){
		changeRequest.setPlatform(this);
		getChangeRequestList().add(changeRequest);
	}
	public  void addChangeRequestList(SmartList<ChangeRequest> changeRequestList){
		for( ChangeRequest changeRequest:changeRequestList){
			changeRequest.setPlatform(this);
		}
		getChangeRequestList().addAll(changeRequestList);
	}
	public  void mergeChangeRequestList(SmartList<ChangeRequest> changeRequestList){
		if(changeRequestList==null){
			return;
		}
		if(changeRequestList.isEmpty()){
			return;
		}
		addChangeRequestList( changeRequestList );
		
	}
	public  ChangeRequest removeChangeRequest(ChangeRequest changeRequestIndex){
		
		int index = getChangeRequestList().indexOf(changeRequestIndex);
        if(index < 0){
        	String message = "ChangeRequest("+changeRequestIndex.getId()+") with version='"+changeRequestIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        ChangeRequest changeRequest = getChangeRequestList().get(index);        
        // changeRequest.clearPlatform(); //disconnect with Platform
        changeRequest.clearFromAll(); //disconnect with Platform
		
		boolean result = getChangeRequestList().planToRemove(changeRequest);
        if(!result){
        	String message = "ChangeRequest("+changeRequestIndex.getId()+") with version='"+changeRequestIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return changeRequest;
        
	
	}
	//断舍离
	public  void breakWithChangeRequest(ChangeRequest changeRequest){
		
		if(changeRequest == null){
			return;
		}
		changeRequest.setPlatform(null);
		//getChangeRequestList().remove();
	
	}
	
	public  boolean hasChangeRequest(ChangeRequest changeRequest){
	
		return getChangeRequestList().contains(changeRequest);
  
	}
	
	public void copyChangeRequestFrom(ChangeRequest changeRequest) {

		ChangeRequest changeRequestInList = findTheChangeRequest(changeRequest);
		ChangeRequest newChangeRequest = new ChangeRequest();
		changeRequestInList.copyTo(newChangeRequest);
		newChangeRequest.setVersion(0);//will trigger copy
		getChangeRequestList().add(newChangeRequest);
		addItemToFlexiableObject(COPIED_CHILD, newChangeRequest);
	}
	
	public  ChangeRequest findTheChangeRequest(ChangeRequest changeRequest){
		
		int index =  getChangeRequestList().indexOf(changeRequest);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "ChangeRequest("+changeRequest.getId()+") with version='"+changeRequest.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getChangeRequestList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpChangeRequestList(){
		getChangeRequestList().clear();
	}
	
	
	


	public  SmartList<ChangeRequestType> getChangeRequestTypeList(){
		if(this.mChangeRequestTypeList == null){
			this.mChangeRequestTypeList = new SmartList<ChangeRequestType>();
			this.mChangeRequestTypeList.setListInternalName (CHANGE_REQUEST_TYPE_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mChangeRequestTypeList;	
	}
	public  void setChangeRequestTypeList(SmartList<ChangeRequestType> changeRequestTypeList){
		for( ChangeRequestType changeRequestType:changeRequestTypeList){
			changeRequestType.setPlatform(this);
		}

		this.mChangeRequestTypeList = changeRequestTypeList;
		this.mChangeRequestTypeList.setListInternalName (CHANGE_REQUEST_TYPE_LIST );
		
	}
	
	public  void addChangeRequestType(ChangeRequestType changeRequestType){
		changeRequestType.setPlatform(this);
		getChangeRequestTypeList().add(changeRequestType);
	}
	public  void addChangeRequestTypeList(SmartList<ChangeRequestType> changeRequestTypeList){
		for( ChangeRequestType changeRequestType:changeRequestTypeList){
			changeRequestType.setPlatform(this);
		}
		getChangeRequestTypeList().addAll(changeRequestTypeList);
	}
	public  void mergeChangeRequestTypeList(SmartList<ChangeRequestType> changeRequestTypeList){
		if(changeRequestTypeList==null){
			return;
		}
		if(changeRequestTypeList.isEmpty()){
			return;
		}
		addChangeRequestTypeList( changeRequestTypeList );
		
	}
	public  ChangeRequestType removeChangeRequestType(ChangeRequestType changeRequestTypeIndex){
		
		int index = getChangeRequestTypeList().indexOf(changeRequestTypeIndex);
        if(index < 0){
        	String message = "ChangeRequestType("+changeRequestTypeIndex.getId()+") with version='"+changeRequestTypeIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        ChangeRequestType changeRequestType = getChangeRequestTypeList().get(index);        
        // changeRequestType.clearPlatform(); //disconnect with Platform
        changeRequestType.clearFromAll(); //disconnect with Platform
		
		boolean result = getChangeRequestTypeList().planToRemove(changeRequestType);
        if(!result){
        	String message = "ChangeRequestType("+changeRequestTypeIndex.getId()+") with version='"+changeRequestTypeIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return changeRequestType;
        
	
	}
	//断舍离
	public  void breakWithChangeRequestType(ChangeRequestType changeRequestType){
		
		if(changeRequestType == null){
			return;
		}
		changeRequestType.setPlatform(null);
		//getChangeRequestTypeList().remove();
	
	}
	
	public  boolean hasChangeRequestType(ChangeRequestType changeRequestType){
	
		return getChangeRequestTypeList().contains(changeRequestType);
  
	}
	
	public void copyChangeRequestTypeFrom(ChangeRequestType changeRequestType) {

		ChangeRequestType changeRequestTypeInList = findTheChangeRequestType(changeRequestType);
		ChangeRequestType newChangeRequestType = new ChangeRequestType();
		changeRequestTypeInList.copyTo(newChangeRequestType);
		newChangeRequestType.setVersion(0);//will trigger copy
		getChangeRequestTypeList().add(newChangeRequestType);
		addItemToFlexiableObject(COPIED_CHILD, newChangeRequestType);
	}
	
	public  ChangeRequestType findTheChangeRequestType(ChangeRequestType changeRequestType){
		
		int index =  getChangeRequestTypeList().indexOf(changeRequestType);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "ChangeRequestType("+changeRequestType.getId()+") with version='"+changeRequestType.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getChangeRequestTypeList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpChangeRequestTypeList(){
		getChangeRequestTypeList().clear();
	}
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){


		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getProvinceList(), internalType);
		collectFromList(this, entityList, getCityList(), internalType);
		collectFromList(this, entityList, getDistrictList(), internalType);
		collectFromList(this, entityList, getTeacherList(), internalType);
		collectFromList(this, entityList, getStudentList(), internalType);
		collectFromList(this, entityList, getQuestionList(), internalType);
		collectFromList(this, entityList, getQuestionTypeList(), internalType);
		collectFromList(this, entityList, getSurveyStatusList(), internalType);
		collectFromList(this, entityList, getUserList(), internalType);
		collectFromList(this, entityList, getChangeRequestList(), internalType);
		collectFromList(this, entityList, getChangeRequestTypeList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getProvinceList());
		listOfList.add( getCityList());
		listOfList.add( getDistrictList());
		listOfList.add( getTeacherList());
		listOfList.add( getStudentList());
		listOfList.add( getQuestionList());
		listOfList.add( getQuestionTypeList());
		listOfList.add( getSurveyStatusList());
		listOfList.add( getUserList());
		listOfList.add( getChangeRequestList());
		listOfList.add( getChangeRequestTypeList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, DESCRIPTION_PROPERTY, getDescription());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, PROVINCE_LIST, getProvinceList());
		if(!getProvinceList().isEmpty()){
			appendKeyValuePair(result, "provinceCount", getProvinceList().getTotalCount());
			appendKeyValuePair(result, "provinceCurrentPageNumber", getProvinceList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, CITY_LIST, getCityList());
		if(!getCityList().isEmpty()){
			appendKeyValuePair(result, "cityCount", getCityList().getTotalCount());
			appendKeyValuePair(result, "cityCurrentPageNumber", getCityList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, DISTRICT_LIST, getDistrictList());
		if(!getDistrictList().isEmpty()){
			appendKeyValuePair(result, "districtCount", getDistrictList().getTotalCount());
			appendKeyValuePair(result, "districtCurrentPageNumber", getDistrictList().getCurrentPageNumber());
		}
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
		appendKeyValuePair(result, QUESTION_TYPE_LIST, getQuestionTypeList());
		if(!getQuestionTypeList().isEmpty()){
			appendKeyValuePair(result, "questionTypeCount", getQuestionTypeList().getTotalCount());
			appendKeyValuePair(result, "questionTypeCurrentPageNumber", getQuestionTypeList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, SURVEY_STATUS_LIST, getSurveyStatusList());
		if(!getSurveyStatusList().isEmpty()){
			appendKeyValuePair(result, "surveyStatusCount", getSurveyStatusList().getTotalCount());
			appendKeyValuePair(result, "surveyStatusCurrentPageNumber", getSurveyStatusList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, USER_LIST, getUserList());
		if(!getUserList().isEmpty()){
			appendKeyValuePair(result, "userCount", getUserList().getTotalCount());
			appendKeyValuePair(result, "userCurrentPageNumber", getUserList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, CHANGE_REQUEST_LIST, getChangeRequestList());
		if(!getChangeRequestList().isEmpty()){
			appendKeyValuePair(result, "changeRequestCount", getChangeRequestList().getTotalCount());
			appendKeyValuePair(result, "changeRequestCurrentPageNumber", getChangeRequestList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, CHANGE_REQUEST_TYPE_LIST, getChangeRequestTypeList());
		if(!getChangeRequestTypeList().isEmpty()){
			appendKeyValuePair(result, "changeRequestTypeCount", getChangeRequestTypeList().getTotalCount());
			appendKeyValuePair(result, "changeRequestTypeCurrentPageNumber", getChangeRequestTypeList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Platform){
		
		
			Platform dest =(Platform)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setDescription(getDescription());
			dest.setVersion(getVersion());
			dest.setProvinceList(getProvinceList());
			dest.setCityList(getCityList());
			dest.setDistrictList(getDistrictList());
			dest.setTeacherList(getTeacherList());
			dest.setStudentList(getStudentList());
			dest.setQuestionList(getQuestionList());
			dest.setQuestionTypeList(getQuestionTypeList());
			dest.setSurveyStatusList(getSurveyStatusList());
			dest.setUserList(getUserList());
			dest.setChangeRequestList(getChangeRequestList());
			dest.setChangeRequestTypeList(getChangeRequestTypeList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Platform){
		
			
			Platform dest =(Platform)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeDescription(getDescription());
			dest.mergeVersion(getVersion());
			dest.mergeProvinceList(getProvinceList());
			dest.mergeCityList(getCityList());
			dest.mergeDistrictList(getDistrictList());
			dest.mergeTeacherList(getTeacherList());
			dest.mergeStudentList(getStudentList());
			dest.mergeQuestionList(getQuestionList());
			dest.mergeQuestionTypeList(getQuestionTypeList());
			dest.mergeSurveyStatusList(getSurveyStatusList());
			dest.mergeUserList(getUserList());
			dest.mergeChangeRequestList(getChangeRequestList());
			dest.mergeChangeRequestTypeList(getChangeRequestTypeList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Platform){
		
			
			Platform dest =(Platform)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeDescription(getDescription());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getName(), getDescription(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("Platform{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		stringBuilder.append("\tdescription='"+getDescription()+"';");
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

