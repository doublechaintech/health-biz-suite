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



const menuData = {menuName: window.trans('student_daily_answer'), menuFor: "studentDailyAnswer",
  		subItems: [
  
  		],
}


const settingMenuData = {menuName: window.trans('student_daily_answer'), menuFor: "studentDailyAnswer",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('student_daily_answer.id'),
  studentHealthSurvey: window.trans('student_daily_answer.student_health_survey'),
  question: window.trans('student_daily_answer.question'),
  answer: window.trans('student_daily_answer.answer'),
  createTime: window.trans('student_daily_answer.create_time'),
  lastUpdateTime: window.trans('student_daily_answer.last_update_time'),
  cq: window.trans('student_daily_answer.cq'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'studentDailyAnswer') , sorter: true },
  { title: fieldLabels.studentHealthSurvey, dataIndex: 'studentHealthSurvey', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.question, dataIndex: 'question', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.answer, debugtype: 'string', dataIndex: 'answer', width: '5',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.createTime, dataIndex: 'createTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},
  { title: fieldLabels.lastUpdateTime, dataIndex: 'lastUpdateTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},
  { title: fieldLabels.cq, dataIndex: 'cq', render: (text, record) => renderReferenceCell(text, record), sorter:true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(studentDailyAnswer, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={studentDailyAnswer.id}>
	
      <DescriptionList  key={studentDailyAnswer.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{studentDailyAnswer.id}</Description> 
        <Description term={fieldLabels.studentHealthSurvey}><div>{studentDailyAnswer.studentHealthSurvey==null?appLocaleName(userContext,"NotAssigned"):`${studentDailyAnswer.studentHealthSurvey.displayName}(${studentDailyAnswer.studentHealthSurvey.id})`}
        </div></Description>
        <Description term={fieldLabels.question}><div>{studentDailyAnswer.question==null?appLocaleName(userContext,"NotAssigned"):`${studentDailyAnswer.question.displayName}(${studentDailyAnswer.question.id})`}
        </div></Description>
        <Description term={fieldLabels.answer} style={{wordBreak: 'break-all'}}>{studentDailyAnswer.answer}</Description> 
        <Description term={fieldLabels.createTime}><div>{ moment(studentDailyAnswer.createTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
        <Description term={fieldLabels.lastUpdateTime}><div>{ moment(studentDailyAnswer.lastUpdateTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
        <Description term={fieldLabels.cq}><div>{studentDailyAnswer.cq==null?appLocaleName(userContext,"NotAssigned"):`${studentDailyAnswer.cq.displayName}(${studentDailyAnswer.cq.id})`}
        </div></Description>
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {answer, studentHealthSurveyId, questionId, cqId} = formValuesToPack
	const studentHealthSurvey = {id: studentHealthSurveyId, version: 2^31}
	const question = {id: questionId, version: 2^31}
	const cq = {id: cqId, version: 2^31}
	const data = {answer, studentHealthSurvey, question, cq}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {answer, studentHealthSurvey, question, cq} = objectToUnpack
	const studentHealthSurveyId = studentHealthSurvey ? studentHealthSurvey.id : null
	const questionId = question ? question.id : null
	const cqId = cq ? cq.id : null
	const data = {answer, studentHealthSurveyId, questionId, cqId}
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
const StudentDailyAnswerBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default StudentDailyAnswerBase



