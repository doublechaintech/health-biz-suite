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



const menuData = {menuName: window.trans('school_class'), menuFor: "schoolClass",
  		subItems: [
  {name: 'classDailyHealthSurveyList', displayName: window.mtrans('class_daily_health_survey','school_class.class_daily_health_survey_list',false), type:'classDailyHealthSurvey',icon:'th',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'studentList', displayName: window.mtrans('student','school_class.student_list',false), type:'student',icon:'500px',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'studentHealthSurveyList', displayName: window.mtrans('student_health_survey','school_class.student_health_survey_list',false), type:'studentHealthSurvey',icon:'th',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('school_class'), menuFor: "schoolClass",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('school_class.id'),
  name: window.trans('school_class.name'),
  classTeacher: window.trans('school_class.class_teacher'),
  createTime: window.trans('school_class.create_time'),
  platform: window.trans('school_class.platform'),
  schoole: window.trans('school_class.schoole'),
  cq: window.trans('school_class.cq'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'schoolClass') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '12',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.classTeacher, dataIndex: 'classTeacher', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.createTime, dataIndex: 'createTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},
  { title: fieldLabels.platform, dataIndex: 'platform', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.schoole, debugtype: 'string', dataIndex: 'schoole', width: '8',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.cq, dataIndex: 'cq', render: (text, record) => renderReferenceCell(text, record), sorter:true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(schoolClass, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={schoolClass.id}>
	
      <DescriptionList  key={schoolClass.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{schoolClass.id}</Description> 
        <Description term={fieldLabels.name} style={{wordBreak: 'break-all'}}>{schoolClass.name}</Description> 
        <Description term={fieldLabels.classTeacher}><div>{schoolClass.classTeacher==null?appLocaleName(userContext,"NotAssigned"):`${schoolClass.classTeacher.displayName}(${schoolClass.classTeacher.id})`}
        </div></Description>
        <Description term={fieldLabels.createTime}><div>{ moment(schoolClass.createTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
        <Description term={fieldLabels.schoole} style={{wordBreak: 'break-all'}}>{schoolClass.schoole}</Description> 
        <Description term={fieldLabels.cq}><div>{schoolClass.cq==null?appLocaleName(userContext,"NotAssigned"):`${schoolClass.cq.displayName}(${schoolClass.cq.id})`}
        </div></Description>
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, schoole, classTeacherId, platformId, cqId} = formValuesToPack
	const classTeacher = {id: classTeacherId, version: 2^31}
	const platform = {id: platformId, version: 2^31}
	const cq = {id: cqId, version: 2^31}
	const data = {name, schoole, classTeacher, platform, cq}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {name, schoole, classTeacher, platform, cq} = objectToUnpack
	const classTeacherId = classTeacher ? classTeacher.id : null
	const platformId = platform ? platform.id : null
	const cqId = cq ? cq.id : null
	const data = {name, schoole, classTeacherId, platformId, cqId}
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
const SchoolClassBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default SchoolClassBase



