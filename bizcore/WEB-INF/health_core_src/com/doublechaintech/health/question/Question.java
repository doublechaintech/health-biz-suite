
package com.doublechaintech.health.question;

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
import com.doublechaintech.health.questiontype.QuestionType;

@JsonSerialize(using = QuestionSerializer.class)
public class Question extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String TOPIC_PROPERTY                 = "topic"             ;
	public static final String QUESTION_TYPE_PROPERTY         = "questionType"      ;
	public static final String OPTION_A_PROPERTY              = "optionA"           ;
	public static final String OPTION_B_PROPERTY              = "optionB"           ;
	public static final String OPTION_C_PROPERTY              = "optionC"           ;
	public static final String OPTION_D_PROPERTY              = "optionD"           ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String VERSION_PROPERTY               = "version"           ;


	public static final String INTERNAL_TYPE="Question";
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
	protected		Platform            	mPlatform           ;
	protected		int                 	mVersion            ;
	
	
	
		
	public 	Question(){
		// lazy load for all the properties
	}
	public 	static Question withId(String id){
		Question question = new Question();
		question.setId(id);
		question.setVersion(Integer.MAX_VALUE);
		return question;
	}
	public 	static Question refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setQuestionType( null );
		setPlatform( null );

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
		if(PLATFORM_PROPERTY.equals(property)){
			return getPlatform();
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
	public Question updateId(String id){
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
	public Question updateTopic(String topic){
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
	public Question updateQuestionType(QuestionType questionType){
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
	public Question updateOptionA(String optionA){
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
	public Question updateOptionB(String optionB){
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
	public Question updateOptionC(String optionC){
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
	public Question updateOptionD(String optionD){
		this.mOptionD = trimString(optionD);;
		this.changed = true;
		return this;
	}
	public void mergeOptionD(String optionD){
		if(optionD != null) { setOptionD(optionD);}
	}
	
	
	public void setPlatform(Platform platform){
		this.mPlatform = platform;;
	}
	public Platform getPlatform(){
		return this.mPlatform;
	}
	public Question updatePlatform(Platform platform){
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
	public Question updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getQuestionType(), internalType);
		addToEntityList(this, entityList, getPlatform(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
			

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
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Question){
		
		
			Question dest =(Question)baseDest;
		
			dest.setId(getId());
			dest.setTopic(getTopic());
			dest.setQuestionType(getQuestionType());
			dest.setOptionA(getOptionA());
			dest.setOptionB(getOptionB());
			dest.setOptionC(getOptionC());
			dest.setOptionD(getOptionD());
			dest.setPlatform(getPlatform());
			dest.setVersion(getVersion());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Question){
		
			
			Question dest =(Question)baseDest;
		
			dest.mergeId(getId());
			dest.mergeTopic(getTopic());
			dest.mergeQuestionType(getQuestionType());
			dest.mergeOptionA(getOptionA());
			dest.mergeOptionB(getOptionB());
			dest.mergeOptionC(getOptionC());
			dest.mergeOptionD(getOptionD());
			dest.mergePlatform(getPlatform());
			dest.mergeVersion(getVersion());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Question){
		
			
			Question dest =(Question)baseDest;
		
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
		return new Object[]{getId(), getTopic(), getQuestionType(), getOptionA(), getOptionB(), getOptionC(), getOptionD(), getPlatform(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("Question{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\ttopic='"+getTopic()+"';");
		if(getQuestionType() != null ){
 			stringBuilder.append("\tquestionType='QuestionType("+getQuestionType().getId()+")';");
 		}
		stringBuilder.append("\toptionA='"+getOptionA()+"';");
		stringBuilder.append("\toptionB='"+getOptionB()+"';");
		stringBuilder.append("\toptionC='"+getOptionC()+"';");
		stringBuilder.append("\toptionD='"+getOptionD()+"';");
		if(getPlatform() != null ){
 			stringBuilder.append("\tplatform='Platform("+getPlatform().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

