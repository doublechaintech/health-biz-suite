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



const menuData = {menuName: window.trans('platform'), menuFor: "platform",
  		subItems: [
  {name: 'schoolClassList', displayName: window.mtrans('school_class','platform.school_class_list',false), type:'schoolClass',icon:'school',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'teacherList', displayName: window.mtrans('teacher','platform.teacher_list',false), type:'teacher',icon:'chalkboard-teacher',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'guardianList', displayName: window.mtrans('guardian','platform.guardian_list',false), type:'guardian',icon:'500px',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'questionList', displayName: window.mtrans('question','platform.question_list',false), type:'question',icon:'question',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'wechatUserList', displayName: window.mtrans('wechat_user','platform.wechat_user_list',false), type:'wechatUser',icon:'user',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'changeRequestList', displayName: window.mtrans('change_request','platform.change_request_list',false), type:'changeRequest',icon:'exchange-alt',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '变更'},
  
  		],
}


const settingMenuData = {menuName: window.trans('platform'), menuFor: "platform",
  		subItems: [
  {name: 'provinceList', displayName: window.mtrans('province','platform.province_list',false), type:'province', icon:'500px',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'cityList', displayName: window.mtrans('city','platform.city_list',false), type:'city', icon:'city',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'districtList', displayName: window.mtrans('district','platform.district_list',false), type:'district', icon:'500px',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'questionTypeList', displayName: window.mtrans('question_type','platform.question_type_list',false), type:'questionType', icon:'question',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'questionSourceList', displayName: window.mtrans('question_source','platform.question_source_list',false), type:'questionSource', icon:'question',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'surveyStatusList', displayName: window.mtrans('survey_status','platform.survey_status_list',false), type:'surveyStatus', icon:'at',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'userTypeList', displayName: window.mtrans('user_type','platform.user_type_list',false), type:'userType', icon:'user',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'changeRequestTypeList', displayName: window.mtrans('change_request_type','platform.change_request_type_list',false), type:'changeRequestType', icon:'exchange-alt',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}

const fieldLabels = {
  id: window.trans('platform.id'),
  name: window.trans('platform.name'),
  description: window.trans('platform.description'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'platform') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '12',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.description, debugtype: 'string_longtext', dataIndex: 'description', width: '10',render: (text, record)=>renderTextCell(text,record)},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(platform, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={platform.id}>
	
      <DescriptionList  key={platform.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{platform.id}</Description> 
        <Description term={fieldLabels.name} style={{wordBreak: 'break-all'}}>{platform.name}</Description> 
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, description} = formValuesToPack

	const data = {name, description}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {name, description} = objectToUnpack

	const data = {name, description}
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
const PlatformBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default PlatformBase



