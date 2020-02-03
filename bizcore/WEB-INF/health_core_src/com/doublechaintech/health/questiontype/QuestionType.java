
package com.doublechaintech.health.questiontype;

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
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.question.Question;

@JsonSerialize(using = QuestionTypeSerializer.class)
public class QuestionType extends BaseEntity implements  java.io.Serializable{

	public static final String SINGLE_SELECT = "SINGLE_SELECT";	// 单选题
	public static final String TEXT_INPUT = "TEXT_INPUT";	// 简答题
	public static List<KeyValuePair> CODE_NAME_LIST;
	static {
		CODE_NAME_LIST = new ArrayList<>();

		CODE_NAME_LIST.add(new KeyValuePair(SINGLE_SELECT, "单选题"));
		CODE_NAME_LIST.add(new KeyValuePair(TEXT_INPUT, "简答题"));
	}
	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String CODE_PROPERTY                  = "code"              ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String QUESTION_LIST                            = "questionList"      ;
	public static final String DAILY_SURVEY_QUESTION_LIST               = "dailySurveyQuestionList";

	public static final String INTERNAL_TYPE="QuestionType";
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
	
	
	protected		SmartList<Question> 	mQuestionList       ;
	protected		SmartList<DailySurveyQuestion>	mDailySurveyQuestionList;
	
		
	public 	QuestionType(){
		// lazy load for all the properties
	}
	public 	static QuestionType withId(String id){
		QuestionType questionType = new QuestionType();
		questionType.setId(id);
		questionType.setVersion(Integer.MAX_VALUE);
		return questionType;
	}
	public 	static QuestionType refById(String id){
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
		if(QUESTION_LIST.equals(property)){
			List<BaseEntity> list = getQuestionList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}
		if(DAILY_SURVEY_QUESTION_LIST.equals(property)){
			List<BaseEntity> list = getDailySurveyQuestionList().stream().map(item->item).collect(Collectors.toList());
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
	public QuestionType updateId(String id){
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
	public QuestionType updateName(String name){
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
	public QuestionType updateCode(String code){
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
	public QuestionType updatePlatform(Platform platform){
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
	public QuestionType updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
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
			question.setQuestionType(this);
		}

		this.mQuestionList = questionList;
		this.mQuestionList.setListInternalName (QUESTION_LIST );
		
	}
	
	public  void addQuestion(Question question){
		question.setQuestionType(this);
		getQuestionList().add(question);
	}
	public  void addQuestionList(SmartList<Question> questionList){
		for( Question question:questionList){
			question.setQuestionType(this);
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
        // question.clearQuestionType(); //disconnect with QuestionType
        question.clearFromAll(); //disconnect with QuestionType
		
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
		question.setQuestionType(null);
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
			dailySurveyQuestion.setQuestionType(this);
		}

		this.mDailySurveyQuestionList = dailySurveyQuestionList;
		this.mDailySurveyQuestionList.setListInternalName (DAILY_SURVEY_QUESTION_LIST );
		
	}
	
	public  void addDailySurveyQuestion(DailySurveyQuestion dailySurveyQuestion){
		dailySurveyQuestion.setQuestionType(this);
		getDailySurveyQuestionList().add(dailySurveyQuestion);
	}
	public  void addDailySurveyQuestionList(SmartList<DailySurveyQuestion> dailySurveyQuestionList){
		for( DailySurveyQuestion dailySurveyQuestion:dailySurveyQuestionList){
			dailySurveyQuestion.setQuestionType(this);
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
        // dailySurveyQuestion.clearQuestionType(); //disconnect with QuestionType
        dailySurveyQuestion.clearFromAll(); //disconnect with QuestionType
		
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
		dailySurveyQuestion.setQuestionType(null);
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
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getPlatform(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getQuestionList(), internalType);
		collectFromList(this, entityList, getDailySurveyQuestionList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getQuestionList());
		listOfList.add( getDailySurveyQuestionList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, CODE_PROPERTY, getCode());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, QUESTION_LIST, getQuestionList());
		if(!getQuestionList().isEmpty()){
			appendKeyValuePair(result, "questionCount", getQuestionList().getTotalCount());
			appendKeyValuePair(result, "questionCurrentPageNumber", getQuestionList().getCurrentPageNumber());
		}
		appendKeyValuePair(result, DAILY_SURVEY_QUESTION_LIST, getDailySurveyQuestionList());
		if(!getDailySurveyQuestionList().isEmpty()){
			appendKeyValuePair(result, "dailySurveyQuestionCount", getDailySurveyQuestionList().getTotalCount());
			appendKeyValuePair(result, "dailySurveyQuestionCurrentPageNumber", getDailySurveyQuestionList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof QuestionType){
		
		
			QuestionType dest =(QuestionType)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setCode(getCode());
			dest.setPlatform(getPlatform());
			dest.setVersion(getVersion());
			dest.setQuestionList(getQuestionList());
			dest.setDailySurveyQuestionList(getDailySurveyQuestionList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof QuestionType){
		
			
			QuestionType dest =(QuestionType)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeCode(getCode());
			dest.mergePlatform(getPlatform());
			dest.mergeVersion(getVersion());
			dest.mergeQuestionList(getQuestionList());
			dest.mergeDailySurveyQuestionList(getDailySurveyQuestionList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof QuestionType){
		
			
			QuestionType dest =(QuestionType)baseDest;
		
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

		stringBuilder.append("QuestionType{");
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

