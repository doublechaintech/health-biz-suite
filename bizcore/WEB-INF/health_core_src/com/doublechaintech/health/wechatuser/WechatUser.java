
package com.doublechaintech.health.wechatuser;

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
import com.doublechaintech.health.guardian.Guardian;
import com.doublechaintech.health.classquestion.ClassQuestion;
import com.doublechaintech.health.usertype.UserType;

@JsonSerialize(using = WechatUserSerializer.class)
public class WechatUser extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String AVATAR_PROPERTY                = "avatar"            ;
	public static final String ADDRESS_PROPERTY               = "address"           ;
	public static final String USER_TYPE_PROPERTY             = "userType"          ;
	public static final String CREATE_TIME_PROPERTY           = "createTime"        ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String GUARDIAN_LIST                            = "guardianList"      ;
	public static final String CLASS_QUESTION_LIST                      = "classQuestionList" ;
	public static final String CLASS_DAILY_HEALTH_SURVEY_LIST           = "classDailyHealthSurveyList";
	public static final String WECHAT_LOGIN_INFO_LIST                   = "wechatLoginInfoList";

	public static final String INTERNAL_TYPE="WechatUser";
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
	protected		UserType            	mUserType           ;
	protected		DateTime            	mCreateTime         ;
	protected		Platform            	mPlatform           ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<Guardian> 	mGuardianList       ;
	protected		SmartList<ClassQuestion>	mClassQuestionList  ;
	protected		SmartList<ClassDailyHealthSurvey>	mClassDailyHealthSurveyList;
	protected		SmartList<WechatLoginInfo>	mWechatLoginInfoList;
	
		
	public 	WechatUser(){
		// lazy load for all the properties
	}
	public 	static WechatUser withId(String id){
		WechatUser wechatUser = new WechatUser();
		wechatUser.setId(id);
		wechatUser.setVersion(Integer.MAX_VALUE);
		return wechatUser;
	}
	public 	static WechatUser refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setAddress( null );
		setUserType( null );
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
		if(USER_TYPE_PROPERTY.equals(property)){
			return getUserType();
		}
		if(CREATE_TIME_PROPERTY.equals(property)){
			return getCreateTime();
		}
		if(PLATFORM_PROPERTY.equals(property)){
			return getPlatform();
		}
		if(GUARDIAN_LIST.equals(property)){
			List<BaseEntity> list = getGuardianList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}
		if(CLASS_QUESTION_LIST.equals(property)){
			List<BaseEntity> list = getClassQuestionList().stream().map(item->item).collect(Collectors.toList());
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
	public WechatUser updateId(String id){
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
	public WechatUser updateName(String name){
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
	public WechatUser updateAvatar(String avatar){
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
	public WechatUser updateAddress(Location address){
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
	
	public void setUserType(UserType userType){
		this.mUserType = userType;;
	}
	public UserType getUserType(){
		return this.mUserType;
	}
	public WechatUser updateUserType(UserType userType){
		this.mUserType = userType;;
		this.changed = true;
		return this;
	}
	public void mergeUserType(UserType userType){
		if(userType != null) { setUserType(userType);}
	}
	
	
	public void clearUserType(){
		setUserType ( null );
		this.changed = true;
	}
	
	public void setCreateTime(DateTime createTime){
		this.mCreateTime = createTime;;
	}
	public DateTime getCreateTime(){
		return this.mCreateTime;
	}
	public WechatUser updateCreateTime(DateTime createTime){
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
	public WechatUser updatePlatform(Platform platform){
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
	public WechatUser updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<Guardian> getGuardianList(){
		if(this.mGuardianList == null){
			this.mGuardianList = new SmartList<Guardian>();
			this.mGuardianList.setListInternalName (GUARDIAN_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mGuardianList;	
	}
	public  void setGuardianList(SmartList<Guardian> guardianList){
		for( Guardian guardian:guardianList){
			guardian.setWechatUser(this);
		}

		this.mGuardianList = guardianList;
		this.mGuardianList.setListInternalName (GUARDIAN_LIST );
		
	}
	
	public  void addGuardian(Guardian guardian){
		guardian.setWechatUser(this);
		getGuardianList().add(guardian);
	}
	public  void addGuardianList(SmartList<Guardian> guardianList){
		for( Guardian guardian:guardianList){
			guardian.setWechatUser(this);
		}
		getGuardianList().addAll(guardianList);
	}
	public  void mergeGuardianList(SmartList<Guardian> guardianList){
		if(guardianList==null){
			return;
		}
		if(guardianList.isEmpty()){
			return;
		}
		addGuardianList( guardianList );
		
	}
	public  Guardian removeGuardian(Guardian guardianIndex){
		
		int index = getGuardianList().indexOf(guardianIndex);
        if(index < 0){
        	String message = "Guardian("+guardianIndex.getId()+") with version='"+guardianIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        Guardian guardian = getGuardianList().get(index);        
        // guardian.clearWechatUser(); //disconnect with WechatUser
        guardian.clearFromAll(); //disconnect with WechatUser
		
		boolean result = getGuardianList().planToRemove(guardian);
        if(!result){
        	String message = "Guardian("+guardianIndex.getId()+") with version='"+guardianIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return guardian;
        
	
	}
	//断舍离
	public  void breakWithGuardian(Guardian guardian){
		
		if(guardian == null){
			return;
		}
		guardian.setWechatUser(null);
		//getGuardianList().remove();
	
	}
	
	public  boolean hasGuardian(Guardian guardian){
	
		return getGuardianList().contains(guardian);
  
	}
	
	public void copyGuardianFrom(Guardian guardian) {

		Guardian guardianInList = findTheGuardian(guardian);
		Guardian newGuardian = new Guardian();
		guardianInList.copyTo(newGuardian);
		newGuardian.setVersion(0);//will trigger copy
		getGuardianList().add(newGuardian);
		addItemToFlexiableObject(COPIED_CHILD, newGuardian);
	}
	
	public  Guardian findTheGuardian(Guardian guardian){
		
		int index =  getGuardianList().indexOf(guardian);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "Guardian("+guardian.getId()+") with version='"+guardian.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getGuardianList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpGuardianList(){
		getGuardianList().clear();
	}
	
	
	


	public  SmartList<ClassQuestion> getClassQuestionList(){
		if(this.mClassQuestionList == null){
			this.mClassQuestionList = new SmartList<ClassQuestion>();
			this.mClassQuestionList.setListInternalName (CLASS_QUESTION_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mClassQuestionList;	
	}
	public  void setClassQuestionList(SmartList<ClassQuestion> classQuestionList){
		for( ClassQuestion classQuestion:classQuestionList){
			classQuestion.setCreator(this);
		}

		this.mClassQuestionList = classQuestionList;
		this.mClassQuestionList.setListInternalName (CLASS_QUESTION_LIST );
		
	}
	
	public  void addClassQuestion(ClassQuestion classQuestion){
		classQuestion.setCreator(this);
		getClassQuestionList().add(classQuestion);
	}
	public  void addClassQuestionList(SmartList<ClassQuestion> classQuestionList){
		for( ClassQuestion classQuestion:classQuestionList){
			classQuestion.setCreator(this);
		}
		getClassQuestionList().addAll(classQuestionList);
	}
	public  void mergeClassQuestionList(SmartList<ClassQuestion> classQuestionList){
		if(classQuestionList==null){
			return;
		}
		if(classQuestionList.isEmpty()){
			return;
		}
		addClassQuestionList( classQuestionList );
		
	}
	public  ClassQuestion removeClassQuestion(ClassQuestion classQuestionIndex){
		
		int index = getClassQuestionList().indexOf(classQuestionIndex);
        if(index < 0){
        	String message = "ClassQuestion("+classQuestionIndex.getId()+") with version='"+classQuestionIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        ClassQuestion classQuestion = getClassQuestionList().get(index);        
        // classQuestion.clearCreator(); //disconnect with Creator
        classQuestion.clearFromAll(); //disconnect with Creator
		
		boolean result = getClassQuestionList().planToRemove(classQuestion);
        if(!result){
        	String message = "ClassQuestion("+classQuestionIndex.getId()+") with version='"+classQuestionIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return classQuestion;
        
	
	}
	//断舍离
	public  void breakWithClassQuestion(ClassQuestion classQuestion){
		
		if(classQuestion == null){
			return;
		}
		classQuestion.setCreator(null);
		//getClassQuestionList().remove();
	
	}
	
	public  boolean hasClassQuestion(ClassQuestion classQuestion){
	
		return getClassQuestionList().contains(classQuestion);
  
	}
	
	public void copyClassQuestionFrom(ClassQuestion classQuestion) {

		ClassQuestion classQuestionInList = findTheClassQuestion(classQuestion);
		ClassQuestion newClassQuestion = new ClassQuestion();
		classQuestionInList.copyTo(newClassQuestion);
		newClassQuestion.setVersion(0);//will trigger copy
		getClassQuestionList().add(newClassQuestion);
		addItemToFlexiableObject(COPIED_CHILD, newClassQuestion);
	}
	
	public  ClassQuestion findTheClassQuestion(ClassQuestion classQuestion){
		
		int index =  getClassQuestionList().indexOf(classQuestion);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "ClassQuestion("+classQuestion.getId()+") with version='"+classQuestion.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getClassQuestionList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpClassQuestionList(){
		getClassQuestionList().clear();
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
			wechatLoginInfo.setWechatUser(this);
		}

		this.mWechatLoginInfoList = wechatLoginInfoList;
		this.mWechatLoginInfoList.setListInternalName (WECHAT_LOGIN_INFO_LIST );
		
	}
	
	public  void addWechatLoginInfo(WechatLoginInfo wechatLoginInfo){
		wechatLoginInfo.setWechatUser(this);
		getWechatLoginInfoList().add(wechatLoginInfo);
	}
	public  void addWechatLoginInfoList(SmartList<WechatLoginInfo> wechatLoginInfoList){
		for( WechatLoginInfo wechatLoginInfo:wechatLoginInfoList){
			wechatLoginInfo.setWechatUser(this);
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
        // wechatLoginInfo.clearWechatUser(); //disconnect with WechatUser
        wechatLoginInfo.clearFromAll(); //disconnect with WechatUser
		
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
		wechatLoginInfo.setWechatUser(null);
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
		addToEntityList(this, entityList, getUserType(), internalType);
		addToEntityList(this, entityList, getPlatform(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getGuardianList(), internalType);
		collectFromList(this, entityList, getClassQuestionList(), internalType);
		collectFromList(this, entityList, getClassDailyHealthSurveyList(), internalType);
		collectFromList(this, entityList, getWechatLoginInfoList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getGuardianList());
		listOfList.add( getClassQuestionList());
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
		appendKeyValuePair(result, USER_TYPE_PROPERTY, getUserType());
		appendKeyValuePair(result, CREATE_TIME_PROPERTY, getCreateTime());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, GUARDIAN_LIST, getGuardianList());
		if(!getGuardianList().isEmpty()){
			appendKeyValuePair(result, "guardianCount", getGuardianList().getTotalCount());
			appendKeyValuePair(result, "guardianCurrentPageNumber", getGuardianList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, CLASS_QUESTION_LIST, getClassQuestionList());
		if(!getClassQuestionList().isEmpty()){
			appendKeyValuePair(result, "classQuestionCount", getClassQuestionList().getTotalCount());
			appendKeyValuePair(result, "classQuestionCurrentPageNumber", getClassQuestionList().getCurrentPageNumber());
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
		
		
		if(baseDest instanceof WechatUser){
		
		
			WechatUser dest =(WechatUser)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setAvatar(getAvatar());
			dest.setAddress(getAddress());
			dest.setUserType(getUserType());
			dest.setCreateTime(getCreateTime());
			dest.setPlatform(getPlatform());
			dest.setVersion(getVersion());
			dest.setGuardianList(getGuardianList());
			dest.setClassQuestionList(getClassQuestionList());
			dest.setClassDailyHealthSurveyList(getClassDailyHealthSurveyList());
			dest.setWechatLoginInfoList(getWechatLoginInfoList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof WechatUser){
		
			
			WechatUser dest =(WechatUser)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeAvatar(getAvatar());
			dest.mergeAddress(getAddress());
			dest.mergeUserType(getUserType());
			dest.mergeCreateTime(getCreateTime());
			dest.mergePlatform(getPlatform());
			dest.mergeVersion(getVersion());
			dest.mergeGuardianList(getGuardianList());
			dest.mergeClassQuestionList(getClassQuestionList());
			dest.mergeClassDailyHealthSurveyList(getClassDailyHealthSurveyList());
			dest.mergeWechatLoginInfoList(getWechatLoginInfoList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof WechatUser){
		
			
			WechatUser dest =(WechatUser)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeAvatar(getAvatar());
			dest.mergeCreateTime(getCreateTime());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getName(), getAvatar(), getAddress(), getUserType(), getCreateTime(), getPlatform(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("WechatUser{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		stringBuilder.append("\tavatar='"+getAvatar()+"';");
		if(getAddress() != null ){
 			stringBuilder.append("\taddress='Location("+getAddress().getId()+")';");
 		}
		if(getUserType() != null ){
 			stringBuilder.append("\tuserType='UserType("+getUserType().getId()+")';");
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

