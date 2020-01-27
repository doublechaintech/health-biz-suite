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
  createTime: window.trans('teacher.create_time'),
  platform: window.trans('teacher.platform'),
  cq: window.trans('teacher.cq'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'teacher') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '7',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.mobile, debugtype: 'string_china_mobile_phone', dataIndex: 'mobile', width: '15',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.school, debugtype: 'string', dataIndex: 'school', width: '8',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.schoolClass, debugtype: 'string', dataIndex: 'schoolClass', width: '12',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.createTime, dataIndex: 'createTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},
  { title: fieldLabels.platform, dataIndex: 'platform', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.cq, dataIndex: 'cq', render: (text, record) => renderReferenceCell(text, record), sorter:true},

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
        <Description term={fieldLabels.createTime}><div>{ moment(teacher.createTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
        <Description term={fieldLabels.cq}><div>{teacher.cq==null?appLocaleName(userContext,"NotAssigned"):`${teacher.cq.displayName}(${teacher.cq.id})`}
        </div></Description>
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, mobile, school, schoolClass, platformId, cqId} = formValuesToPack
	const platform = {id: platformId, version: 2^31}
	const cq = {id: cqId, version: 2^31}
	const data = {name, mobile, school, schoolClass, platform, cq}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {name, mobile, school, schoolClass, platform, cq} = objectToUnpack
	const platformId = platform ? platform.id : null
	const cqId = cq ? cq.id : null
	const data = {name, mobile, school, schoolClass, platformId, cqId}
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



