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



const menuData = {menuName: window.trans('class_question'), menuFor: "classQuestion",
  		subItems: [
  {name: 'dailySurveyQuestionList', displayName: window.mtrans('daily_survey_question','class_question.daily_survey_question_list',false), type:'dailySurveyQuestion',icon:'question',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('class_question'), menuFor: "classQuestion",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('class_question.id'),
  topic: window.trans('class_question.topic'),
  questionType: window.trans('class_question.question_type'),
  optionA: window.trans('class_question.option_a'),
  optionB: window.trans('class_question.option_b'),
  optionC: window.trans('class_question.option_c'),
  optionD: window.trans('class_question.option_d'),
  questionSource: window.trans('class_question.question_source'),
  creator: window.trans('class_question.creator'),
  cq: window.trans('class_question.cq'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'classQuestion') , sorter: true },
  { title: fieldLabels.topic, debugtype: 'string', dataIndex: 'topic', width: '18',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.questionType, dataIndex: 'questionType', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.optionA, debugtype: 'string', dataIndex: 'optionA', width: '6',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.optionB, debugtype: 'string', dataIndex: 'optionB', width: '5',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.optionC, debugtype: 'string', dataIndex: 'optionC', width: '7',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.optionD, debugtype: 'string', dataIndex: 'optionD', width: '7',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.questionSource, dataIndex: 'questionSource', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.creator, dataIndex: 'creator', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.cq, dataIndex: 'cq', render: (text, record) => renderReferenceCell(text, record), sorter:true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(classQuestion, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={classQuestion.id}>
	
      <DescriptionList  key={classQuestion.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{classQuestion.id}</Description> 
        <Description term={fieldLabels.topic} style={{wordBreak: 'break-all'}}>{classQuestion.topic}</Description> 
        <Description term={fieldLabels.questionType}><div>{classQuestion.questionType==null?appLocaleName(userContext,"NotAssigned"):`${classQuestion.questionType.displayName}(${classQuestion.questionType.id})`}
        </div></Description>
        <Description term={fieldLabels.optionA} style={{wordBreak: 'break-all'}}>{classQuestion.optionA}</Description> 
        <Description term={fieldLabels.optionB} style={{wordBreak: 'break-all'}}>{classQuestion.optionB}</Description> 
        <Description term={fieldLabels.optionC} style={{wordBreak: 'break-all'}}>{classQuestion.optionC}</Description> 
        <Description term={fieldLabels.optionD} style={{wordBreak: 'break-all'}}>{classQuestion.optionD}</Description> 
        <Description term={fieldLabels.questionSource}><div>{classQuestion.questionSource==null?appLocaleName(userContext,"NotAssigned"):`${classQuestion.questionSource.displayName}(${classQuestion.questionSource.id})`}
        </div></Description>
        <Description term={fieldLabels.creator}><div>{classQuestion.creator==null?appLocaleName(userContext,"NotAssigned"):`${classQuestion.creator.displayName}(${classQuestion.creator.id})`}
        </div></Description>
        <Description term={fieldLabels.cq}><div>{classQuestion.cq==null?appLocaleName(userContext,"NotAssigned"):`${classQuestion.cq.displayName}(${classQuestion.cq.id})`}
        </div></Description>
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {topic, optionA, optionB, optionC, optionD, questionTypeId, questionSourceId, creatorId, cqId} = formValuesToPack
	const questionType = {id: questionTypeId, version: 2^31}
	const questionSource = {id: questionSourceId, version: 2^31}
	const creator = {id: creatorId, version: 2^31}
	const cq = {id: cqId, version: 2^31}
	const data = {topic, optionA, optionB, optionC, optionD, questionType, questionSource, creator, cq}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {topic, optionA, optionB, optionC, optionD, questionType, questionSource, creator, cq} = objectToUnpack
	const questionTypeId = questionType ? questionType.id : null
	const questionSourceId = questionSource ? questionSource.id : null
	const creatorId = creator ? creator.id : null
	const cqId = cq ? cq.id : null
	const data = {topic, optionA, optionB, optionC, optionD, questionTypeId, questionSourceId, creatorId, cqId}
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
const ClassQuestionBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default ClassQuestionBase



