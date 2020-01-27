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



const menuData = {menuName: window.trans('organization'), menuFor: "organization",
  		subItems: [
  {name: 'questionList', displayName: window.mtrans('question','organization.question_list',false), type:'question',icon:'question',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'dailyHealthSurveyList', displayName: window.mtrans('daily_health_survey','organization.daily_health_survey_list',false), type:'dailyHealthSurvey',icon:'th',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'userDailyAnswerList', displayName: window.mtrans('user_daily_answer','organization.user_daily_answer_list',false), type:'userDailyAnswer',icon:'user',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'wechatUserList', displayName: window.mtrans('wechat_user','organization.wechat_user_list',false), type:'wechatUser',icon:'user',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('organization'), menuFor: "organization",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('organization.id'),
  name: window.trans('organization.name'),
  supervisor: window.trans('organization.supervisor'),
  district: window.trans('organization.district'),
  platform: window.trans('organization.platform'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'organization') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '12',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.supervisor, dataIndex: 'supervisor', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.district, dataIndex: 'district', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.platform, dataIndex: 'platform', render: (text, record) => renderReferenceCell(text, record), sorter:true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(organization, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={organization.id}>
	
      <DescriptionList  key={organization.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{organization.id}</Description> 
        <Description term={fieldLabels.name} style={{wordBreak: 'break-all'}}>{organization.name}</Description> 
        <Description term={fieldLabels.supervisor}><div>{organization.supervisor==null?appLocaleName(userContext,"NotAssigned"):`${organization.supervisor.displayName}(${organization.supervisor.id})`}
        </div></Description>
        <Description term={fieldLabels.district}><div>{organization.district==null?appLocaleName(userContext,"NotAssigned"):`${organization.district.displayName}(${organization.district.id})`}
        </div></Description>
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, supervisorId, districtId, platformId} = formValuesToPack
	const supervisor = {id: supervisorId, version: 2^31}
	const district = {id: districtId, version: 2^31}
	const platform = {id: platformId, version: 2^31}
	const data = {name, supervisor, district, platform}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {name, supervisor, district, platform} = objectToUnpack
	const supervisorId = supervisor ? supervisor.id : null
	const districtId = district ? district.id : null
	const platformId = platform ? platform.id : null
	const data = {name, supervisorId, districtId, platformId}
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
const OrganizationBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default OrganizationBase



