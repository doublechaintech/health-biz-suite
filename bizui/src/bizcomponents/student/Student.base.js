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



const menuData = {menuName: window.trans('student'), menuFor: "student",
  		subItems: [
  {name: 'studentHealthSurveyList', displayName: window.mtrans('student_health_survey','student.student_health_survey_list',false), type:'studentHealthSurvey',icon:'th',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('student'), menuFor: "student",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('student.id'),
  name: window.trans('student.name'),
  gender: window.trans('student.gender'),
  guardian: window.trans('student.guardian'),
  schoolClass: window.trans('student.school_class'),
  studentId: window.trans('student.student_id'),
  cq: window.trans('student.cq'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'student') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '7',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.gender, debugtype: 'string_gender', dataIndex: 'gender', width: '10',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.guardian, dataIndex: 'guardian', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.schoolClass, dataIndex: 'schoolClass', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.studentId, debugtype: 'string', dataIndex: 'studentId', width: '7',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.cq, dataIndex: 'cq', render: (text, record) => renderReferenceCell(text, record), sorter:true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(student, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={student.id}>
	
      <DescriptionList  key={student.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{student.id}</Description> 
        <Description term={fieldLabels.name} style={{wordBreak: 'break-all'}}>{student.name}</Description> 
        <Description term={fieldLabels.gender} style={{wordBreak: 'break-all'}}>{student.gender}</Description> 
        <Description term={fieldLabels.guardian}><div>{student.guardian==null?appLocaleName(userContext,"NotAssigned"):`${student.guardian.displayName}(${student.guardian.id})`}
        </div></Description>
        <Description term={fieldLabels.schoolClass}><div>{student.schoolClass==null?appLocaleName(userContext,"NotAssigned"):`${student.schoolClass.displayName}(${student.schoolClass.id})`}
        </div></Description>
        <Description term={fieldLabels.studentId} style={{wordBreak: 'break-all'}}>{student.studentId}</Description> 
        <Description term={fieldLabels.cq}><div>{student.cq==null?appLocaleName(userContext,"NotAssigned"):`${student.cq.displayName}(${student.cq.id})`}
        </div></Description>
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, gender, studentId, guardianId, schoolClassId, cqId} = formValuesToPack
	const guardian = {id: guardianId, version: 2^31}
	const schoolClass = {id: schoolClassId, version: 2^31}
	const cq = {id: cqId, version: 2^31}
	const data = {name, gender, studentId, guardian, schoolClass, cq}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {name, gender, studentId, guardian, schoolClass, cq} = objectToUnpack
	const guardianId = guardian ? guardian.id : null
	const schoolClassId = schoolClass ? schoolClass.id : null
	const cqId = cq ? cq.id : null
	const data = {name, gender, studentId, guardianId, schoolClassId, cqId}
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
const StudentBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default StudentBase



