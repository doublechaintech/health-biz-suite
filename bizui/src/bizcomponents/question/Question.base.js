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



const menuData = {menuName: window.trans('question'), menuFor: "question",
  		subItems: [
  {name: 'dailySurveyQuestionList', displayName: window.mtrans('daily_survey_question','question.daily_survey_question_list',false), type:'dailySurveyQuestion',icon:'question',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('question'), menuFor: "question",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('question.id'),
  topic: window.trans('question.topic'),
  questionType: window.trans('question.question_type'),
  optionA: window.trans('question.option_a'),
  optionB: window.trans('question.option_b'),
  optionC: window.trans('question.option_c'),
  optionD: window.trans('question.option_d'),
  platform: window.trans('question.platform'),
  creator: window.trans('question.creator'),
  cq: window.trans('question.cq'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'question') , sorter: true },
  { title: fieldLabels.topic, debugtype: 'string', dataIndex: 'topic', width: '18',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.questionType, dataIndex: 'questionType', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.optionA, debugtype: 'string', dataIndex: 'optionA', width: '6',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.optionB, debugtype: 'string', dataIndex: 'optionB', width: '5',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.optionC, debugtype: 'string', dataIndex: 'optionC', width: '7',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.optionD, debugtype: 'string', dataIndex: 'optionD', width: '7',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.platform, dataIndex: 'platform', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.creator, dataIndex: 'creator', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.cq, dataIndex: 'cq', render: (text, record) => renderReferenceCell(text, record), sorter:true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(question, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={question.id}>
	
      <DescriptionList  key={question.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{question.id}</Description> 
        <Description term={fieldLabels.topic} style={{wordBreak: 'break-all'}}>{question.topic}</Description> 
        <Description term={fieldLabels.questionType}><div>{question.questionType==null?appLocaleName(userContext,"NotAssigned"):`${question.questionType.displayName}(${question.questionType.id})`}
        </div></Description>
        <Description term={fieldLabels.optionA} style={{wordBreak: 'break-all'}}>{question.optionA}</Description> 
        <Description term={fieldLabels.optionB} style={{wordBreak: 'break-all'}}>{question.optionB}</Description> 
        <Description term={fieldLabels.optionC} style={{wordBreak: 'break-all'}}>{question.optionC}</Description> 
        <Description term={fieldLabels.optionD} style={{wordBreak: 'break-all'}}>{question.optionD}</Description> 
        <Description term={fieldLabels.creator}><div>{question.creator==null?appLocaleName(userContext,"NotAssigned"):`${question.creator.displayName}(${question.creator.id})`}
        </div></Description>
        <Description term={fieldLabels.cq}><div>{question.cq==null?appLocaleName(userContext,"NotAssigned"):`${question.cq.displayName}(${question.cq.id})`}
        </div></Description>
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {topic, optionA, optionB, optionC, optionD, questionTypeId, platformId, creatorId, cqId} = formValuesToPack
	const questionType = {id: questionTypeId, version: 2^31}
	const platform = {id: platformId, version: 2^31}
	const creator = {id: creatorId, version: 2^31}
	const cq = {id: cqId, version: 2^31}
	const data = {topic, optionA, optionB, optionC, optionD, questionType, platform, creator, cq}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {topic, optionA, optionB, optionC, optionD, questionType, platform, creator, cq} = objectToUnpack
	const questionTypeId = questionType ? questionType.id : null
	const platformId = platform ? platform.id : null
	const creatorId = creator ? creator.id : null
	const cqId = cq ? cq.id : null
	const data = {topic, optionA, optionB, optionC, optionD, questionTypeId, platformId, creatorId, cqId}
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
const QuestionBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default QuestionBase



