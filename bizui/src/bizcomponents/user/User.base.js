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



const menuData = {menuName: window.trans('user'), menuFor: "user",
  		subItems: [
  {name: 'teacherList', displayName: window.mtrans('teacher','user.teacher_list',false), type:'teacher',icon:'chalkboard-teacher',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'studentList', displayName: window.mtrans('student','user.student_list',false), type:'student',icon:'500px',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'questionList', displayName: window.mtrans('question','user.question_list',false), type:'question',icon:'question',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'classDailyHealthSurveyList', displayName: window.mtrans('class_daily_health_survey','user.class_daily_health_survey_list',false), type:'classDailyHealthSurvey',icon:'th',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'wechatLoginInfoList', displayName: window.mtrans('wechat_login_info','user.wechat_login_info_list',false), type:'wechatLoginInfo',icon:'info',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('user'), menuFor: "user",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('user.id'),
  name: window.trans('user.name'),
  avatar: window.trans('user.avatar'),
  createTime: window.trans('user.create_time'),
  platform: window.trans('user.platform'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'user') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '6',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.avatar, dataIndex: 'avatar', render: (text, record) => renderAvatarCell(text,record,'user.avatar') },
  { title: fieldLabels.createTime, dataIndex: 'createTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},
  { title: fieldLabels.platform, dataIndex: 'platform', render: (text, record) => renderReferenceCell(text, record), sorter:true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(user, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={user.id}>
	
      <DescriptionList  key={user.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{user.id}</Description> 
        <Description term={fieldLabels.name} style={{wordBreak: 'break-all'}}>{user.name}</Description> 
        <Description term={fieldLabels.createTime}><div>{ moment(user.createTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, platformId} = formValuesToPack
	const platform = {id: platformId, version: 2^31}
	const data = {name, platform}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {name, platform} = objectToUnpack
	const platformId = platform ? platform.id : null
	const data = {name, platformId}
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
const UserBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default UserBase



