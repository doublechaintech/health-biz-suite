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
  studentName: window.trans('student.student_name'),
  studentId: window.trans('student.student_id'),
  guardianName: window.trans('student.guardian_name'),
  guardianMobile: window.trans('student.guardian_mobile'),
  address: window.trans('student.address'),
  user: window.trans('student.user'),
  createTime: window.trans('student.create_time'),
  platform: window.trans('student.platform'),
  cq: window.trans('student.cq'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'student') , sorter: true },
  { title: fieldLabels.studentName, debugtype: 'string', dataIndex: 'studentName', width: '7',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.studentId, debugtype: 'string', dataIndex: 'studentId', width: '7',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.guardianName, debugtype: 'string', dataIndex: 'guardianName', width: '7',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.guardianMobile, debugtype: 'string_china_mobile_phone', dataIndex: 'guardianMobile', width: '15',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.address, dataIndex: 'address', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.user, dataIndex: 'user', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.createTime, dataIndex: 'createTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},
  { title: fieldLabels.platform, dataIndex: 'platform', render: (text, record) => renderReferenceCell(text, record), sorter:true},
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
        <Description term={fieldLabels.studentName} style={{wordBreak: 'break-all'}}>{student.studentName}</Description> 
        <Description term={fieldLabels.studentId} style={{wordBreak: 'break-all'}}>{student.studentId}</Description> 
        <Description term={fieldLabels.guardianName} style={{wordBreak: 'break-all'}}>{student.guardianName}</Description> 
        <Description term={fieldLabels.guardianMobile} style={{wordBreak: 'break-all'}}>{student.guardianMobile}</Description> 
        <Description term={fieldLabels.address}><div>{student.address==null?appLocaleName(userContext,"NotAssigned"):`${student.address.displayName}(${student.address.id})`}
        </div></Description>
        <Description term={fieldLabels.user}><div>{student.user==null?appLocaleName(userContext,"NotAssigned"):`${student.user.displayName}(${student.user.id})`}
        </div></Description>
        <Description term={fieldLabels.createTime}><div>{ moment(student.createTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
        <Description term={fieldLabels.cq}><div>{student.cq==null?appLocaleName(userContext,"NotAssigned"):`${student.cq.displayName}(${student.cq.id})`}
        </div></Description>
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {studentName, studentId, guardianName, guardianMobile, addressId, userId, platformId, cqId} = formValuesToPack
	const address = {id: addressId, version: 2^31}
	const user = {id: userId, version: 2^31}
	const platform = {id: platformId, version: 2^31}
	const cq = {id: cqId, version: 2^31}
	const data = {studentName, studentId, guardianName, guardianMobile, address, user, platform, cq}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {studentName, studentId, guardianName, guardianMobile, address, user, platform, cq} = objectToUnpack
	const addressId = address ? address.id : null
	const userId = user ? user.id : null
	const platformId = platform ? platform.id : null
	const cqId = cq ? cq.id : null
	const data = {studentName, studentId, guardianName, guardianMobile, addressId, userId, platformId, cqId}
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



