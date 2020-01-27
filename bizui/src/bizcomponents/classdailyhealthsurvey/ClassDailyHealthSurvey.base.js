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



const menuData = {menuName: window.trans('class_daily_health_survey'), menuFor: "classDailyHealthSurvey",
  		subItems: [
  {name: 'dailySurveyQuestionList', displayName: window.mtrans('daily_survey_question','class_daily_health_survey.daily_survey_question_list',false), type:'dailySurveyQuestion',icon:'question',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'studentHealthSurveyList', displayName: window.mtrans('student_health_survey','class_daily_health_survey.student_health_survey_list',false), type:'studentHealthSurvey',icon:'th',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('class_daily_health_survey'), menuFor: "classDailyHealthSurvey",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('class_daily_health_survey.id'),
  name: window.trans('class_daily_health_survey.name'),
  schoolClass: window.trans('class_daily_health_survey.school_class'),
  surveyTime: window.trans('class_daily_health_survey.survey_time'),
  creator: window.trans('class_daily_health_survey.creator'),
  cq: window.trans('class_daily_health_survey.cq'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'classDailyHealthSurvey') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '26',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.schoolClass, dataIndex: 'schoolClass', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.surveyTime, dataIndex: 'surveyTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},
  { title: fieldLabels.creator, dataIndex: 'creator', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.cq, dataIndex: 'cq', render: (text, record) => renderReferenceCell(text, record), sorter:true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(classDailyHealthSurvey, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={classDailyHealthSurvey.id}>
	
      <DescriptionList  key={classDailyHealthSurvey.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{classDailyHealthSurvey.id}</Description> 
        <Description term={fieldLabels.name} style={{wordBreak: 'break-all'}}>{classDailyHealthSurvey.name}</Description> 
        <Description term={fieldLabels.schoolClass}><div>{classDailyHealthSurvey.schoolClass==null?appLocaleName(userContext,"NotAssigned"):`${classDailyHealthSurvey.schoolClass.displayName}(${classDailyHealthSurvey.schoolClass.id})`}
        </div></Description>
        <Description term={fieldLabels.surveyTime}><div>{ moment(classDailyHealthSurvey.surveyTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
        <Description term={fieldLabels.creator}><div>{classDailyHealthSurvey.creator==null?appLocaleName(userContext,"NotAssigned"):`${classDailyHealthSurvey.creator.displayName}(${classDailyHealthSurvey.creator.id})`}
        </div></Description>
        <Description term={fieldLabels.cq}><div>{classDailyHealthSurvey.cq==null?appLocaleName(userContext,"NotAssigned"):`${classDailyHealthSurvey.cq.displayName}(${classDailyHealthSurvey.cq.id})`}
        </div></Description>
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, surveyTime, schoolClassId, creatorId, cqId} = formValuesToPack
	const schoolClass = {id: schoolClassId, version: 2^31}
	const creator = {id: creatorId, version: 2^31}
	const cq = {id: cqId, version: 2^31}
	const data = {name, surveyTime, schoolClass, creator, cq}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {name, surveyTime, schoolClass, creator, cq} = objectToUnpack
	const schoolClassId = schoolClass ? schoolClass.id : null
	const creatorId = creator ? creator.id : null
	const cqId = cq ? cq.id : null
	const data = {name, surveyTime, schoolClassId, creatorId, cqId}
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
const ClassDailyHealthSurveyBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default ClassDailyHealthSurveyBase



