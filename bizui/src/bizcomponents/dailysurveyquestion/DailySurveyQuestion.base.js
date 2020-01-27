import React from 'react'
import { Icon,Divider, Avata, Card, Col} from 'antd'

import { Link } from 'dva/router'
import moment from 'moment'
import ImagePreview from '../../components/ImagePreview'
import appLocaleName from '../../common/Locale.tool'
import BaseTool from '../../common/Base.tool'
import GlobalComponents from '../../custcomponents'
import DescriptionList from '../../components/DescriptionList'
const { Description } = DescriptionList

const {
	defaultRenderReferenceCell,
	defaultRenderBooleanCell,
	defaultRenderMoneyCell,
	defaultRenderDateTimeCell,
	defaultRenderImageCell,
	defaultRenderAvatarCell,
	defaultRenderDateCell,
	defaultRenderIdentifier,
	defaultRenderTextCell,
	defaultSearchLocalData,
} = BaseTool

const renderTextCell=defaultRenderTextCell
const renderIdentifier=defaultRenderIdentifier
const renderDateCell=defaultRenderDateCell
const renderDateTimeCell=defaultRenderDateTimeCell
const renderImageCell=defaultRenderImageCell
const renderAvatarCell=defaultRenderAvatarCell
const renderMoneyCell=defaultRenderMoneyCell
const renderBooleanCell=defaultRenderBooleanCell
const renderReferenceCell=defaultRenderReferenceCell



const menuData = {menuName: window.trans('daily_survey_question'), menuFor: "dailySurveyQuestion",
  		subItems: [
  {name: 'studentDailyAnswerList', displayName: window.mtrans('student_daily_answer','daily_survey_question.student_daily_answer_list',false), type:'studentDailyAnswer',icon:'500px',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('daily_survey_question'), menuFor: "dailySurveyQuestion",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('daily_survey_question.id'),
  topic: window.trans('daily_survey_question.topic'),
  questionType: window.trans('daily_survey_question.question_type'),
  optionA: window.trans('daily_survey_question.option_a'),
  optionB: window.trans('daily_survey_question.option_b'),
  optionC: window.trans('daily_survey_question.option_c'),
  optionD: window.trans('daily_survey_question.option_d'),
  classDailyHealthSurvey: window.trans('daily_survey_question.class_daily_health_survey'),
  classQuestion: window.trans('daily_survey_question.class_question'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'dailySurveyQuestion') , sorter: true },
  { title: fieldLabels.topic, debugtype: 'string', dataIndex: 'topic', width: '18',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.questionType, dataIndex: 'questionType', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.optionA, debugtype: 'string', dataIndex: 'optionA', width: '6',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.optionB, debugtype: 'string', dataIndex: 'optionB', width: '5',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.optionC, debugtype: 'string', dataIndex: 'optionC', width: '7',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.optionD, debugtype: 'string', dataIndex: 'optionD', width: '7',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.classDailyHealthSurvey, dataIndex: 'classDailyHealthSurvey', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.classQuestion, dataIndex: 'classQuestion', render: (text, record) => renderReferenceCell(text, record), sorter:true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(dailySurveyQuestion, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={dailySurveyQuestion.id}>
	
      <DescriptionList  key={dailySurveyQuestion.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{dailySurveyQuestion.id}</Description> 
        <Description term={fieldLabels.topic} style={{wordBreak: 'break-all'}}>{dailySurveyQuestion.topic}</Description> 
        <Description term={fieldLabels.questionType}><div>{dailySurveyQuestion.questionType==null?appLocaleName(userContext,"NotAssigned"):`${dailySurveyQuestion.questionType.displayName}(${dailySurveyQuestion.questionType.id})`}
        </div></Description>
        <Description term={fieldLabels.optionA} style={{wordBreak: 'break-all'}}>{dailySurveyQuestion.optionA}</Description> 
        <Description term={fieldLabels.optionB} style={{wordBreak: 'break-all'}}>{dailySurveyQuestion.optionB}</Description> 
        <Description term={fieldLabels.optionC} style={{wordBreak: 'break-all'}}>{dailySurveyQuestion.optionC}</Description> 
        <Description term={fieldLabels.optionD} style={{wordBreak: 'break-all'}}>{dailySurveyQuestion.optionD}</Description> 
        <Description term={fieldLabels.classDailyHealthSurvey}><div>{dailySurveyQuestion.classDailyHealthSurvey==null?appLocaleName(userContext,"NotAssigned"):`${dailySurveyQuestion.classDailyHealthSurvey.displayName}(${dailySurveyQuestion.classDailyHealthSurvey.id})`}
        </div></Description>
        <Description term={fieldLabels.classQuestion}><div>{dailySurveyQuestion.classQuestion==null?appLocaleName(userContext,"NotAssigned"):`${dailySurveyQuestion.classQuestion.displayName}(${dailySurveyQuestion.classQuestion.id})`}
        </div></Description>
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {topic, optionA, optionB, optionC, optionD, questionTypeId, classDailyHealthSurveyId, classQuestionId} = formValuesToPack
	const questionType = {id: questionTypeId, version: 2^31}
	const classDailyHealthSurvey = {id: classDailyHealthSurveyId, version: 2^31}
	const classQuestion = {id: classQuestionId, version: 2^31}
	const data = {topic, optionA, optionB, optionC, optionD, questionType, classDailyHealthSurvey, classQuestion}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {topic, optionA, optionB, optionC, optionD, questionType, classDailyHealthSurvey, classQuestion} = objectToUnpack
	const questionTypeId = questionType ? questionType.id : null
	const classDailyHealthSurveyId = classDailyHealthSurvey ? classDailyHealthSurvey.id : null
	const classQuestionId = classQuestion ? classQuestion.id : null
	const data = {topic, optionA, optionB, optionC, optionD, questionTypeId, classDailyHealthSurveyId, classQuestionId}
	return data
}
const stepOf=(targetComponent, title, content, position, index)=>{
	return {
		title,
		content,
		position,
		packFunction: packFormValuesToObject,
		unpackFunction: unpackObjectToFormValues,
		index,
      }
}
const DailySurveyQuestionBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default DailySurveyQuestionBase



