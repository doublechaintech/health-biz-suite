
package com.doublechaintech.health.questionsource;

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
import com.doublechaintech.health.classquestion.ClassQuestion;

@JsonSerialize(using = QuestionSourceSerializer.class)
public class QuestionSource extends BaseEntity implements  java.io.Serializable{

	public static final String SYSTEM = "System";	// 题库
	public static final String UGC = "UGC";	// 用户自己录入
	public static List<KeyValuePair> CODE_NAME_LIST;
	static {
		CODE_NAME_LIST = new ArrayList<>();

		CODE_NAME_LIST.add(new KeyValuePair(SYSTEM, "题库"));
		CODE_NAME_LIST.add(new KeyValuePair(UGC, "用户自己录入"));
	}
	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String CODE_PROPERTY                  = "code"              ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String CLASS_QUESTION_LIST                      = "classQuestionList" ;

	public static final String INTERNAL_TYPE="QuestionSource";
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
	protected		String              	mCode               ;
	protected		Platform            	mPlatform           ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<ClassQuestion>	mClassQuestionList  ;
	
		
	public 	QuestionSource(){
		// lazy load for all the properties
	}
	public 	static QuestionSource withId(String id){
		QuestionSource questionSource = new QuestionSource();
		questionSource.setId(id);
		questionSource.setVersion(Integer.MAX_VALUE);
		return questionSource;
	}
	public 	static QuestionSource refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setPlatform( null );

		this.changed = true;
	}
	
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
		}
		if(CODE_PROPERTY.equals(property)){
			changeCodeProperty(newValueExpr);
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
			
			
			
	protected void changeCodeProperty(String newValueExpr){
		String oldValue = getCode();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateCode(newValue);
		this.onChangeProperty(CODE_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(NAME_PROPERTY.equals(property)){
			return getName();
		}
		if(CODE_PROPERTY.equals(property)){
			return getCode();
		}
		if(PLATFORM_PROPERTY.equals(property)){
			return getPlatform();
		}
		if(CLASS_QUESTION_LIST.equals(property)){
			List<BaseEntity> list = getClassQuestionList().stream().map(item->item).collect(Collectors.toList());
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
	public QuestionSource updateId(String id){
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
	public QuestionSource updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setCode(String code){
		this.mCode = trimString(code);;
	}
	public String getCode(){
		return this.mCode;
	}
	public QuestionSource updateCode(String code){
		this.mCode = trimString(code);;
		this.changed = true;
		return this;
	}
	public void mergeCode(String code){
		if(code != null) { setCode(code);}
	}
	
	
	public void setPlatform(Platform platform){
		this.mPlatform = platform;;
	}
	public Platform getPlatform(){
		return this.mPlatform;
	}
	public QuestionSource updatePlatform(Platform platform){
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
	public QuestionSource updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
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
			classQuestion.setQuestionSource(this);
		}

		this.mClassQuestionList = classQuestionList;
		this.mClassQuestionList.setListInternalName (CLASS_QUESTION_LIST );
		
	}
	
	public  void addClassQuestion(ClassQuestion classQuestion){
		classQuestion.setQuestionSource(this);
		getClassQuestionList().add(classQuestion);
	}
	public  void addClassQuestionList(SmartList<ClassQuestion> classQuestionList){
		for( ClassQuestion classQuestion:classQuestionList){
			classQuestion.setQuestionSource(this);
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
        // classQuestion.clearQuestionSource(); //disconnect with QuestionSource
        classQuestion.clearFromAll(); //disconnect with QuestionSource
		
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
		classQuestion.setQuestionSource(null);
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
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getPlatform(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getClassQuestionList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getClassQuestionList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, CODE_PROPERTY, getCode());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, CLASS_QUESTION_LIST, getClassQuestionList());
		if(!getClassQuestionList().isEmpty()){
			appendKeyValuePair(result, "classQuestionCount", getClassQuestionList().getTotalCount());
			appendKeyValuePair(result, "classQuestionCurrentPageNumber", getClassQuestionList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof QuestionSource){
		
		
			QuestionSource dest =(QuestionSource)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setCode(getCode());
			dest.setPlatform(getPlatform());
			dest.setVersion(getVersion());
			dest.setClassQuestionList(getClassQuestionList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof QuestionSource){
		
			
			QuestionSource dest =(QuestionSource)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeCode(getCode());
			dest.mergePlatform(getPlatform());
			dest.mergeVersion(getVersion());
			dest.mergeClassQuestionList(getClassQuestionList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof QuestionSource){
		
			
			QuestionSource dest =(QuestionSource)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeCode(getCode());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getName(), getCode(), getPlatform(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("QuestionSource{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		stringBuilder.append("\tcode='"+getCode()+"';");
		if(getPlatform() != null ){
 			stringBuilder.append("\tplatform='Platform("+getPlatform().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

