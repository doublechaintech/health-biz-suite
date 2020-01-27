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



const menuData = {menuName: window.trans('survey_question'), menuFor: "surveyQuestion",
  		subItems: [
  
  		],
}


const settingMenuData = {menuName: window.trans('survey_question'), menuFor: "surveyQuestion",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('survey_question.id'),
  name: window.trans('survey_question.name'),
  survey: window.trans('survey_question.survey'),
  question: window.trans('survey_question.question'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'surveyQuestion') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '6',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.survey, dataIndex: 'survey', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.question, dataIndex: 'question', render: (text, record) => renderReferenceCell(text, record), sorter:true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(surveyQuestion, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={surveyQuestion.id}>
	
      <DescriptionList  key={surveyQuestion.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{surveyQuestion.id}</Description> 
        <Description term={fieldLabels.name} style={{wordBreak: 'break-all'}}>{surveyQuestion.name}</Description> 
        <Description term={fieldLabels.survey}><div>{surveyQuestion.survey==null?appLocaleName(userContext,"NotAssigned"):`${surveyQuestion.survey.displayName}(${surveyQuestion.survey.id})`}
        </div></Description>
        <Description term={fieldLabels.question}><div>{surveyQuestion.question==null?appLocaleName(userContext,"NotAssigned"):`${surveyQuestion.question.displayName}(${surveyQuestion.question.id})`}
        </div></Description>
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, surveyId, questionId} = formValuesToPack
	const survey = {id: surveyId, version: 2^31}
	const question = {id: questionId, version: 2^31}
	const data = {name, survey, question}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {name, survey, question} = objectToUnpack
	const surveyId = survey ? survey.id : null
	const questionId = question ? question.id : null
	const data = {name, surveyId, questionId}
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
const SurveyQuestionBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default SurveyQuestionBase



