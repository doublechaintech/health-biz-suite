
package com.doublechaintech.health.classquestion;

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
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.wechatuser.WechatUser;
import com.doublechaintech.health.questionsource.QuestionSource;

@JsonSerialize(using = ClassQuestionSerializer.class)
public class ClassQuestion extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String TOPIC_PROPERTY                 = "topic"             ;
	public static final String QUESTION_TYPE_PROPERTY         = "questionType"      ;
	public static final String OPTION_A_PROPERTY              = "optionA"           ;
	public static final String OPTION_B_PROPERTY              = "optionB"           ;
	public static final String OPTION_C_PROPERTY              = "optionC"           ;
	public static final String OPTION_D_PROPERTY              = "optionD"           ;
	public static final String QUESTION_SOURCE_PROPERTY       = "questionSource"    ;
	public static final String CREATOR_PROPERTY               = "creator"           ;
	public static final String CQ_PROPERTY                    = "cq"                ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String DAILY_SURVEY_QUESTION_LIST               = "dailySurveyQuestionList";

	public static final String INTERNAL_TYPE="ClassQuestion";
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
	protected		QuestionSource      	mQuestionSource     ;
	protected		WechatUser          	mCreator            ;
	protected		ChangeRequest       	mCq                 ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<DailySurveyQuestion>	mDailySurveyQuestionList;
	
		
	public 	ClassQuestion(){
		// lazy load for all the properties
	}
	public 	static ClassQuestion withId(String id){
		ClassQuestion classQuestion = new ClassQuestion();
		classQuestion.setId(id);
		classQuestion.setVersion(Integer.MAX_VALUE);
		return classQuestion;
	}
	public 	static ClassQuestion refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setQuestionType( null );
		setQuestionSource( null );
		setCreator( null );
		setCq( null );

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
		if(QUESTION_SOURCE_PROPERTY.equals(property)){
			return getQuestionSource();
		}
		if(CREATOR_PROPERTY.equals(property)){
			return getCreator();
		}
		if(CQ_PROPERTY.equals(property)){
			return getCq();
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
	public ClassQuestion updateId(String id){
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
	public ClassQuestion updateTopic(String topic){
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
	public ClassQuestion updateQuestionType(QuestionType questionType){
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
	public ClassQuestion updateOptionA(String optionA){
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
	public ClassQuestion updateOptionB(String optionB){
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
	public ClassQuestion updateOptionC(String optionC){
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
	public ClassQuestion updateOptionD(String optionD){
		this.mOptionD = trimString(optionD);;
		this.changed = true;
		return this;
	}
	public void mergeOptionD(String optionD){
		if(optionD != null) { setOptionD(optionD);}
	}
	
	
	public void setQuestionSource(QuestionSource questionSource){
		this.mQuestionSource = questionSource;;
	}
	public QuestionSource getQuestionSource(){
		return this.mQuestionSource;
	}
	public ClassQuestion updateQuestionSource(QuestionSource questionSource){
		this.mQuestionSource = questionSource;;
		this.changed = true;
		return this;
	}
	public void mergeQuestionSource(QuestionSource questionSource){
		if(questionSource != null) { setQuestionSource(questionSource);}
	}
	
	
	public void clearQuestionSource(){
		setQuestionSource ( null );
		this.changed = true;
	}
	
	public void setCreator(WechatUser creator){
		this.mCreator = creator;;
	}
	public WechatUser getCreator(){
		return this.mCreator;
	}
	public ClassQuestion updateCreator(WechatUser creator){
		this.mCreator = creator;;
		this.changed = true;
		return this;
	}
	public void mergeCreator(WechatUser creator){
		if(creator != null) { setCreator(creator);}
	}
	
	
	public void clearCreator(){
		setCreator ( null );
		this.changed = true;
	}
	
	public void setCq(ChangeRequest cq){
		this.mCq = cq;;
	}
	public ChangeRequest getCq(){
		return this.mCq;
	}
	public ClassQuestion updateCq(ChangeRequest cq){
		this.mCq = cq;;
		this.changed = true;
		return this;
	}
	public void mergeCq(ChangeRequest cq){
		if(cq != null) { setCq(cq);}
	}
	
	
	public void clearCq(){
		setCq ( null );
		this.changed = true;
	}
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public ClassQuestion updateVersion(int version){
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
			dailySurveyQuestion.setClassQuestion(this);
		}

		this.mDailySurveyQuestionList = dailySurveyQuestionList;
		this.mDailySurveyQuestionList.setListInternalName (DAILY_SURVEY_QUESTION_LIST );
		
	}
	
	public  void addDailySurveyQuestion(DailySurveyQuestion dailySurveyQuestion){
		dailySurveyQuestion.setClassQuestion(this);
		getDailySurveyQuestionList().add(dailySurveyQuestion);
	}
	public  void addDailySurveyQuestionList(SmartList<DailySurveyQuestion> dailySurveyQuestionList){
		for( DailySurveyQuestion dailySurveyQuestion:dailySurveyQuestionList){
			dailySurveyQuestion.setClassQuestion(this);
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
        // dailySurveyQuestion.clearClassQuestion(); //disconnect with ClassQuestion
        dailySurveyQuestion.clearFromAll(); //disconnect with ClassQuestion
		
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
		dailySurveyQuestion.setClassQuestion(null);
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

		addToEntityList(this, entityList, getQuestionType(), internalType);
		addToEntityList(this, entityList, getQuestionSource(), internalType);
		addToEntityList(this, entityList, getCreator(), internalType);
		addToEntityList(this, entityList, getCq(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getDailySurveyQuestionList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getDailySurveyQuestionList());
			

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
		appendKeyValuePair(result, QUESTION_SOURCE_PROPERTY, getQuestionSource());
		appendKeyValuePair(result, CREATOR_PROPERTY, getCreator());
		appendKeyValuePair(result, CQ_PROPERTY, getCq());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, DAILY_SURVEY_QUESTION_LIST, getDailySurveyQuestionList());
		if(!getDailySurveyQuestionList().isEmpty()){
			appendKeyValuePair(result, "dailySurveyQuestionCount", getDailySurveyQuestionList().getTotalCount());
			appendKeyValuePair(result, "dailySurveyQuestionCurrentPageNumber", getDailySurveyQuestionList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof ClassQuestion){
		
		
			ClassQuestion dest =(ClassQuestion)baseDest;
		
			dest.setId(getId());
			dest.setTopic(getTopic());
			dest.setQuestionType(getQuestionType());
			dest.setOptionA(getOptionA());
			dest.setOptionB(getOptionB());
			dest.setOptionC(getOptionC());
			dest.setOptionD(getOptionD());
			dest.setQuestionSource(getQuestionSource());
			dest.setCreator(getCreator());
			dest.setCq(getCq());
			dest.setVersion(getVersion());
			dest.setDailySurveyQuestionList(getDailySurveyQuestionList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof ClassQuestion){
		
			
			ClassQuestion dest =(ClassQuestion)baseDest;
		
			dest.mergeId(getId());
			dest.mergeTopic(getTopic());
			dest.mergeQuestionType(getQuestionType());
			dest.mergeOptionA(getOptionA());
			dest.mergeOptionB(getOptionB());
			dest.mergeOptionC(getOptionC());
			dest.mergeOptionD(getOptionD());
			dest.mergeQuestionSource(getQuestionSource());
			dest.mergeCreator(getCreator());
			dest.mergeCq(getCq());
			dest.mergeVersion(getVersion());
			dest.mergeDailySurveyQuestionList(getDailySurveyQuestionList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof ClassQuestion){
		
			
			ClassQuestion dest =(ClassQuestion)baseDest;
		
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
		return new Object[]{getId(), getTopic(), getQuestionType(), getOptionA(), getOptionB(), getOptionC(), getOptionD(), getQuestionSource(), getCreator(), getCq(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("ClassQuestion{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\ttopic='"+getTopic()+"';");
		if(getQuestionType() != null ){
 			stringBuilder.append("\tquestionType='QuestionType("+getQuestionType().getId()+")';");
 		}
		stringBuilder.append("\toptionA='"+getOptionA()+"';");
		stringBuilder.append("\toptionB='"+getOptionB()+"';");
		stringBuilder.append("\toptionC='"+getOptionC()+"';");
		stringBuilder.append("\toptionD='"+getOptionD()+"';");
		if(getQuestionSource() != null ){
 			stringBuilder.append("\tquestionSource='QuestionSource("+getQuestionSource().getId()+")';");
 		}
		if(getCreator() != null ){
 			stringBuilder.append("\tcreator='WechatUser("+getCreator().getId()+")';");
 		}
		if(getCq() != null ){
 			stringBuilder.append("\tcq='ChangeRequest("+getCq().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

