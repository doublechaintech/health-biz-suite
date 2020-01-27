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



const menuData = {menuName: window.trans('wechat_login_info'), menuFor: "wechatLoginInfo",
  		subItems: [
  
  		],
}


const settingMenuData = {menuName: window.trans('wechat_login_info'), menuFor: "wechatLoginInfo",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('wechat_login_info.id'),
  user: window.trans('wechat_login_info.user'),
  appId: window.trans('wechat_login_info.app_id'),
  openId: window.trans('wechat_login_info.open_id'),
  sessionKey: window.trans('wechat_login_info.session_key'),
  lastUpdateTime: window.trans('wechat_login_info.last_update_time'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'wechatLoginInfo') , sorter: true },
  { title: fieldLabels.user, dataIndex: 'user', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.appId, debugtype: 'string', dataIndex: 'appId', width: '11',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.openId, debugtype: 'string', dataIndex: 'openId', width: '11',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.sessionKey, debugtype: 'string', dataIndex: 'sessionKey', width: '14',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.lastUpdateTime, dataIndex: 'lastUpdateTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(wechatLoginInfo, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={wechatLoginInfo.id}>
	
      <DescriptionList  key={wechatLoginInfo.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{wechatLoginInfo.id}</Description> 
        <Description term={fieldLabels.user}><div>{wechatLoginInfo.user==null?appLocaleName(userContext,"NotAssigned"):`${wechatLoginInfo.user.displayName}(${wechatLoginInfo.user.id})`}
        </div></Description>
        <Description term={fieldLabels.appId} style={{wordBreak: 'break-all'}}>{wechatLoginInfo.appId}</Description> 
        <Description term={fieldLabels.openId} style={{wordBreak: 'break-all'}}>{wechatLoginInfo.openId}</Description> 
        <Description term={fieldLabels.sessionKey} style={{wordBreak: 'break-all'}}>{wechatLoginInfo.sessionKey}</Description> 
        <Description term={fieldLabels.lastUpdateTime}><div>{ moment(wechatLoginInfo.lastUpdateTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {appId, openId, sessionKey, userId} = formValuesToPack
	const user = {id: userId, version: 2^31}
	const data = {appId, openId, sessionKey, user}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {appId, openId, sessionKey, user} = objectToUnpack
	const userId = user ? user.id : null
	const data = {appId, openId, sessionKey, userId}
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
const WechatLoginInfoBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default WechatLoginInfoBase



