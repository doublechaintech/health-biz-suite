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



const menuData = {menuName: window.trans('wechat_user'), menuFor: "wechatUser",
  		subItems: [
  {name: 'guardianList', displayName: window.mtrans('guardian','wechat_user.guardian_list',false), type:'guardian',icon:'500px',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'classQuestionList', displayName: window.mtrans('class_question','wechat_user.class_question_list',false), type:'classQuestion',icon:'question',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'classDailyHealthSurveyList', displayName: window.mtrans('class_daily_health_survey','wechat_user.class_daily_health_survey_list',false), type:'classDailyHealthSurvey',icon:'th',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'wechatLoginInfoList', displayName: window.mtrans('wechat_login_info','wechat_user.wechat_login_info_list',false), type:'wechatLoginInfo',icon:'info',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('wechat_user'), menuFor: "wechatUser",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('wechat_user.id'),
  name: window.trans('wechat_user.name'),
  avatar: window.trans('wechat_user.avatar'),
  address: window.trans('wechat_user.address'),
  userType: window.trans('wechat_user.user_type'),
  createTime: window.trans('wechat_user.create_time'),
  platform: window.trans('wechat_user.platform'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'wechatUser') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '6',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.avatar, dataIndex: 'avatar', render: (text, record) => renderAvatarCell(text,record,'wechat_user.avatar') },
  { title: fieldLabels.address, dataIndex: 'address', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.userType, dataIndex: 'userType', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.createTime, dataIndex: 'createTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},
  { title: fieldLabels.platform, dataIndex: 'platform', render: (text, record) => renderReferenceCell(text, record), sorter:true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(wechatUser, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={wechatUser.id}>
	
      <DescriptionList  key={wechatUser.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{wechatUser.id}</Description> 
        <Description term={fieldLabels.name} style={{wordBreak: 'break-all'}}>{wechatUser.name}</Description> 
        <Description term={fieldLabels.address}><div>{wechatUser.address==null?appLocaleName(userContext,"NotAssigned"):`${wechatUser.address.displayName}(${wechatUser.address.id})`}
        </div></Description>
        <Description term={fieldLabels.userType}><div>{wechatUser.userType==null?appLocaleName(userContext,"NotAssigned"):`${wechatUser.userType.displayName}(${wechatUser.userType.id})`}
        </div></Description>
        <Description term={fieldLabels.createTime}><div>{ moment(wechatUser.createTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, addressId, userTypeId, platformId} = formValuesToPack
	const address = {id: addressId, version: 2^31}
	const userType = {id: userTypeId, version: 2^31}
	const platform = {id: platformId, version: 2^31}
	const data = {name, address, userType, platform}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {name, address, userType, platform} = objectToUnpack
	const addressId = address ? address.id : null
	const userTypeId = userType ? userType.id : null
	const platformId = platform ? platform.id : null
	const data = {name, addressId, userTypeId, platformId}
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
const WechatUserBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default WechatUserBase



