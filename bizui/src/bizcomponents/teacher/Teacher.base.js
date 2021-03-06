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



const menuData = {menuName: window.trans('teacher'), menuFor: "teacher",
  		subItems: [
  {name: 'classDailyHealthSurveyList', displayName: window.mtrans('class_daily_health_survey','teacher.class_daily_health_survey_list',false), type:'classDailyHealthSurvey',icon:'th',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'studentHealthSurveyList', displayName: window.mtrans('student_health_survey','teacher.student_health_survey_list',false), type:'studentHealthSurvey',icon:'th',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'healthSurveyReportList', displayName: window.mtrans('health_survey_report','teacher.health_survey_report_list',false), type:'healthSurveyReport',icon:'th',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('teacher'), menuFor: "teacher",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('teacher.id'),
  name: window.trans('teacher.name'),
  mobile: window.trans('teacher.mobile'),
  school: window.trans('teacher.school'),
  schoolClass: window.trans('teacher.school_class'),
  classSize: window.trans('teacher.class_size'),
  createTime: window.trans('teacher.create_time'),
  platform: window.trans('teacher.platform'),
  user: window.trans('teacher.user'),
  changeRequest: window.trans('teacher.change_request'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'teacher') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '7',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.mobile, debugtype: 'string_china_mobile_phone', dataIndex: 'mobile', width: '15',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.school, debugtype: 'string', dataIndex: 'school', width: '8',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.schoolClass, debugtype: 'string', dataIndex: 'schoolClass', width: '12',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.classSize, debugtype: 'int', dataIndex: 'classSize', width: '5',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.createTime, dataIndex: 'createTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},
  { title: fieldLabels.platform, dataIndex: 'platform', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.user, dataIndex: 'user', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.changeRequest, dataIndex: 'changeRequest', render: (text, record) => renderReferenceCell(text, record), sorter:true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(teacher, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={teacher.id}>
	
      <DescriptionList  key={teacher.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{teacher.id}</Description> 
        <Description term={fieldLabels.name} style={{wordBreak: 'break-all'}}>{teacher.name}</Description> 
        <Description term={fieldLabels.mobile} style={{wordBreak: 'break-all'}}>{teacher.mobile}</Description> 
        <Description term={fieldLabels.school} style={{wordBreak: 'break-all'}}>{teacher.school}</Description> 
        <Description term={fieldLabels.schoolClass} style={{wordBreak: 'break-all'}}>{teacher.schoolClass}</Description> 
        <Description term={fieldLabels.classSize}><div style={{"color":"red"}}>{teacher.classSize}</div></Description> 
        <Description term={fieldLabels.createTime}><div>{ moment(teacher.createTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
        <Description term={fieldLabels.user}><div>{teacher.user==null?appLocaleName(userContext,"NotAssigned"):`${teacher.user.displayName}(${teacher.user.id})`}
        </div></Description>
        <Description term={fieldLabels.changeRequest}><div>{teacher.changeRequest==null?appLocaleName(userContext,"NotAssigned"):`${teacher.changeRequest.displayName}(${teacher.changeRequest.id})`}
        </div></Description>
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, mobile, school, schoolClass, classSize, platformId, userId, changeRequestId} = formValuesToPack
	const platform = {id: platformId, version: 2^31}
	const user = {id: userId, version: 2^31}
	const changeRequest = {id: changeRequestId, version: 2^31}
	const data = {name, mobile, school, schoolClass, classSize, platform, user, changeRequest}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {name, mobile, school, schoolClass, classSize, platform, user, changeRequest} = objectToUnpack
	const platformId = platform ? platform.id : null
	const userId = user ? user.id : null
	const changeRequestId = changeRequest ? changeRequest.id : null
	const data = {name, mobile, school, schoolClass, classSize, platformId, userId, changeRequestId}
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
const TeacherBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default TeacherBase



