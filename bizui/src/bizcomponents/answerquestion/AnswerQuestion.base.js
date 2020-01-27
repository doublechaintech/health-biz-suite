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



const menuData = {menuName: window.trans('answer_question'), menuFor: "answerQuestion",
  		subItems: [
  
  		],
}


const settingMenuData = {menuName: window.trans('answer_question'), menuFor: "answerQuestion",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('answer_question.id'),
  nickName: window.trans('answer_question.nick_name'),
  user: window.trans('answer_question.user'),
  userAnswer: window.trans('answer_question.user_answer'),
  answer: window.trans('answer_question.answer'),
  changeRequest: window.trans('answer_question.change_request'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'answerQuestion') , sorter: true },
  { title: fieldLabels.nickName, debugtype: 'string', dataIndex: 'nickName', width: '11',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.user, dataIndex: 'user', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.userAnswer, dataIndex: 'userAnswer', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.answer, debugtype: 'string', dataIndex: 'answer', width: '5',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.changeRequest, dataIndex: 'changeRequest', render: (text, record) => renderReferenceCell(text, record), sorter:true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(answerQuestion, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={answerQuestion.id}>
	
      <DescriptionList  key={answerQuestion.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{answerQuestion.id}</Description> 
        <Description term={fieldLabels.nickName} style={{wordBreak: 'break-all'}}>{answerQuestion.nickName}</Description> 
        <Description term={fieldLabels.user}><div>{answerQuestion.user==null?appLocaleName(userContext,"NotAssigned"):`${answerQuestion.user.displayName}(${answerQuestion.user.id})`}
        </div></Description>
        <Description term={fieldLabels.userAnswer}><div>{answerQuestion.userAnswer==null?appLocaleName(userContext,"NotAssigned"):`${answerQuestion.userAnswer.displayName}(${answerQuestion.userAnswer.id})`}
        </div></Description>
        <Description term={fieldLabels.answer} style={{wordBreak: 'break-all'}}>{answerQuestion.answer}</Description> 
        <Description term={fieldLabels.changeRequest}><div>{answerQuestion.changeRequest==null?appLocaleName(userContext,"NotAssigned"):`${answerQuestion.changeRequest.displayName}(${answerQuestion.changeRequest.id})`}
        </div></Description>
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {nickName, answer, userId, userAnswerId, changeRequestId} = formValuesToPack
	const user = {id: userId, version: 2^31}
	const userAnswer = {id: userAnswerId, version: 2^31}
	const changeRequest = {id: changeRequestId, version: 2^31}
	const data = {nickName, answer, user, userAnswer, changeRequest}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {nickName, answer, user, userAnswer, changeRequest} = objectToUnpack
	const userId = user ? user.id : null
	const userAnswerId = userAnswer ? userAnswer.id : null
	const changeRequestId = changeRequest ? changeRequest.id : null
	const data = {nickName, answer, userId, userAnswerId, changeRequestId}
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
const AnswerQuestionBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default AnswerQuestionBase



