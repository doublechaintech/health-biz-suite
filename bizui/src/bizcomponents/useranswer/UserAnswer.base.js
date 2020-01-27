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



const menuData = {menuName: window.trans('user_answer'), menuFor: "userAnswer",
  		subItems: [
  {name: 'dailyAnswerList', displayName: window.mtrans('daily_answer','user_answer.daily_answer_list',false), type:'dailyAnswer',icon:'500px',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'answerQuestionList', displayName: window.mtrans('answer_question','user_answer.answer_question_list',false), type:'answerQuestion',icon:'question',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('user_answer'), menuFor: "userAnswer",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('user_answer.id'),
  topic: window.trans('user_answer.topic'),
  question: window.trans('user_answer.question'),
  questionAnswer: window.trans('user_answer.question_answer'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'userAnswer') , sorter: true },
  { title: fieldLabels.topic, debugtype: 'string', dataIndex: 'topic', width: '18',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.question, dataIndex: 'question', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.questionAnswer, debugtype: 'string', dataIndex: 'questionAnswer', width: '5',render: (text, record)=>renderTextCell(text,record)},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(userAnswer, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={userAnswer.id}>
	
      <DescriptionList  key={userAnswer.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{userAnswer.id}</Description> 
        <Description term={fieldLabels.topic} style={{wordBreak: 'break-all'}}>{userAnswer.topic}</Description> 
        <Description term={fieldLabels.question}><div>{userAnswer.question==null?appLocaleName(userContext,"NotAssigned"):`${userAnswer.question.displayName}(${userAnswer.question.id})`}
        </div></Description>
        <Description term={fieldLabels.questionAnswer} style={{wordBreak: 'break-all'}}>{userAnswer.questionAnswer}</Description> 
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {topic, questionAnswer, questionId} = formValuesToPack
	const question = {id: questionId, version: 2^31}
	const data = {topic, questionAnswer, question}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {topic, questionAnswer, question} = objectToUnpack
	const questionId = question ? question.id : null
	const data = {topic, questionAnswer, questionId}
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
const UserAnswerBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default UserAnswerBase



