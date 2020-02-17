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



const menuData = {menuName: window.trans('wechat_miniapp_identify'), menuFor: "wechatMiniappIdentify",
  		subItems: [
  
  		],
}


const settingMenuData = {menuName: window.trans('wechat_miniapp_identify'), menuFor: "wechatMiniappIdentify",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('wechat_miniapp_identify.id'),
  openId: window.trans('wechat_miniapp_identify.open_id'),
  appId: window.trans('wechat_miniapp_identify.app_id'),
  secUser: window.trans('wechat_miniapp_identify.sec_user'),
  createTime: window.trans('wechat_miniapp_identify.create_time'),
  lastLoginTime: window.trans('wechat_miniapp_identify.last_login_time'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'wechatMiniappIdentify') , sorter: true },
  { title: fieldLabels.openId, debugtype: 'string', dataIndex: 'openId', width: '29',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.appId, debugtype: 'string', dataIndex: 'appId', width: '32',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.secUser, dataIndex: 'secUser', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.createTime, dataIndex: 'createTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},
  { title: fieldLabels.lastLoginTime, dataIndex: 'lastLoginTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(wechatMiniappIdentify, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={wechatMiniappIdentify.id}>
	
      <DescriptionList  key={wechatMiniappIdentify.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{wechatMiniappIdentify.id}</Description> 
        <Description term={fieldLabels.openId} style={{wordBreak: 'break-all'}}>{wechatMiniappIdentify.openId}</Description> 
        <Description term={fieldLabels.appId} style={{wordBreak: 'break-all'}}>{wechatMiniappIdentify.appId}</Description> 
        <Description term={fieldLabels.secUser}><div>{wechatMiniappIdentify.secUser==null?appLocaleName(userContext,"NotAssigned"):`${wechatMiniappIdentify.secUser.displayName}(${wechatMiniappIdentify.secUser.id})`}
        </div></Description>
        <Description term={fieldLabels.createTime}><div>{ moment(wechatMiniappIdentify.createTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
        <Description term={fieldLabels.lastLoginTime}><div>{ moment(wechatMiniappIdentify.lastLoginTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {openId, appId, lastLoginTime, secUserId} = formValuesToPack
	const secUser = {id: secUserId, version: 2^31}
	const data = {openId, appId, lastLoginTime:moment(lastLoginTime).valueOf(), secUser}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {openId, appId, lastLoginTime, secUser} = objectToUnpack
	const secUserId = secUser ? secUser.id : null
	const data = {openId, appId, lastLoginTime:moment(lastLoginTime), secUserId}
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
const WechatMiniappIdentifyBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default WechatMiniappIdentifyBase










