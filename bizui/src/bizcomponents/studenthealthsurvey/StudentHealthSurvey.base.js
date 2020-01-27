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



const menuData = {menuName: window.trans('student_health_survey'), menuFor: "studentHealthSurvey",
  		subItems: [
  {name: 'studentDailyAnswerList', displayName: window.mtrans('student_daily_answer','student_health_survey.student_daily_answer_list',false), type:'studentDailyAnswer',icon:'500px',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('student_health_survey'), menuFor: "studentHealthSurvey",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('student_health_survey.id'),
  student: window.trans('student_health_survey.student'),
  answerTime: window.trans('student_health_survey.answer_time'),
  surveyStatus: window.trans('student_health_survey.survey_status'),
  teacher: window.trans('student_health_survey.teacher'),
  classDailyHealthSurvey: window.trans('student_health_survey.class_daily_health_survey'),
  createTime: window.trans('student_health_survey.create_time'),
  lastUpdateTime: window.trans('student_health_survey.last_update_time'),
  cq: window.trans('student_health_survey.cq'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'studentHealthSurvey') , sorter: true },
  { title: fieldLabels.student, dataIndex: 'student', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.answerTime, dataIndex: 'answerTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},
  { title: fieldLabels.surveyStatus, dataIndex: 'surveyStatus', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.teacher, dataIndex: 'teacher', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.classDailyHealthSurvey, dataIndex: 'classDailyHealthSurvey', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.createTime, dataIndex: 'createTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},
  { title: fieldLabels.lastUpdateTime, dataIndex: 'lastUpdateTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},
  { title: fieldLabels.cq, dataIndex: 'cq', render: (text, record) => renderReferenceCell(text, record), sorter:true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(studentHealthSurvey, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={studentHealthSurvey.id}>
	
      <DescriptionList  key={studentHealthSurvey.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{studentHealthSurvey.id}</Description> 
        <Description term={fieldLabels.student}><div>{studentHealthSurvey.student==null?appLocaleName(userContext,"NotAssigned"):`${studentHealthSurvey.student.displayName}(${studentHealthSurvey.student.id})`}
        </div></Description>
        <Description term={fieldLabels.answerTime}><div>{ moment(studentHealthSurvey.answerTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
        <Description term={fieldLabels.surveyStatus}><div>{studentHealthSurvey.surveyStatus==null?appLocaleName(userContext,"NotAssigned"):`${studentHealthSurvey.surveyStatus.displayName}(${studentHealthSurvey.surveyStatus.id})`}
        </div></Description>
        <Description term={fieldLabels.teacher}><div>{studentHealthSurvey.teacher==null?appLocaleName(userContext,"NotAssigned"):`${studentHealthSurvey.teacher.displayName}(${studentHealthSurvey.teacher.id})`}
        </div></Description>
        <Description term={fieldLabels.classDailyHealthSurvey}><div>{studentHealthSurvey.classDailyHealthSurvey==null?appLocaleName(userContext,"NotAssigned"):`${studentHealthSurvey.classDailyHealthSurvey.displayName}(${studentHealthSurvey.classDailyHealthSurvey.id})`}
        </div></Description>
        <Description term={fieldLabels.createTime}><div>{ moment(studentHealthSurvey.createTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
        <Description term={fieldLabels.lastUpdateTime}><div>{ moment(studentHealthSurvey.lastUpdateTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
        <Description term={fieldLabels.cq}><div>{studentHealthSurvey.cq==null?appLocaleName(userContext,"NotAssigned"):`${studentHealthSurvey.cq.displayName}(${studentHealthSurvey.cq.id})`}
        </div></Description>
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {answerTime, studentId, surveyStatusId, teacherId, classDailyHealthSurveyId, cqId} = formValuesToPack
	const student = {id: studentId, version: 2^31}
	const surveyStatus = {id: surveyStatusId, version: 2^31}
	const teacher = {id: teacherId, version: 2^31}
	const classDailyHealthSurvey = {id: classDailyHealthSurveyId, version: 2^31}
	const cq = {id: cqId, version: 2^31}
	const data = {answerTime, student, surveyStatus, teacher, classDailyHealthSurvey, cq}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {answerTime, student, surveyStatus, teacher, classDailyHealthSurvey, cq} = objectToUnpack
	const studentId = student ? student.id : null
	const surveyStatusId = surveyStatus ? surveyStatus.id : null
	const teacherId = teacher ? teacher.id : null
	const classDailyHealthSurveyId = classDailyHealthSurvey ? classDailyHealthSurvey.id : null
	const cqId = cq ? cq.id : null
	const data = {answerTime, studentId, surveyStatusId, teacherId, classDailyHealthSurveyId, cqId}
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
const StudentHealthSurveyBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default StudentHealthSurveyBase



