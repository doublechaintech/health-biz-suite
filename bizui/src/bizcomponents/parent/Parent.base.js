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



const menuData = {menuName: window.trans('parent'), menuFor: "parent",
  		subItems: [
  {name: 'studentList', displayName: window.mtrans('student','parent.student_list',false), type:'student',icon:'500px',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('parent'), menuFor: "parent",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('parent.id'),
  name: window.trans('parent.name'),
  mobile: window.trans('parent.mobile'),
  address: window.trans('parent.address'),
  wechatUser: window.trans('parent.wechat_user'),
  createTime: window.trans('parent.create_time'),
  platform: window.trans('parent.platform'),
  cq: window.trans('parent.cq'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'parent') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '7',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.mobile, debugtype: 'string_china_mobile_phone', dataIndex: 'mobile', width: '15',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.address, dataIndex: 'address', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.wechatUser, dataIndex: 'wechatUser', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.createTime, dataIndex: 'createTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},
  { title: fieldLabels.platform, dataIndex: 'platform', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.cq, dataIndex: 'cq', render: (text, record) => renderReferenceCell(text, record), sorter:true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(parent, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={parent.id}>
	
      <DescriptionList  key={parent.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{parent.id}</Description> 
        <Description term={fieldLabels.name} style={{wordBreak: 'break-all'}}>{parent.name}</Description> 
        <Description term={fieldLabels.mobile} style={{wordBreak: 'break-all'}}>{parent.mobile}</Description> 
        <Description term={fieldLabels.address}><div>{parent.address==null?appLocaleName(userContext,"NotAssigned"):`${parent.address.displayName}(${parent.address.id})`}
        </div></Description>
        <Description term={fieldLabels.wechatUser}><div>{parent.wechatUser==null?appLocaleName(userContext,"NotAssigned"):`${parent.wechatUser.displayName}(${parent.wechatUser.id})`}
        </div></Description>
        <Description term={fieldLabels.createTime}><div>{ moment(parent.createTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
        <Description term={fieldLabels.cq}><div>{parent.cq==null?appLocaleName(userContext,"NotAssigned"):`${parent.cq.displayName}(${parent.cq.id})`}
        </div></Description>
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, mobile, addressId, wechatUserId, platformId, cqId} = formValuesToPack
	const address = {id: addressId, version: 2^31}
	const wechatUser = {id: wechatUserId, version: 2^31}
	const platform = {id: platformId, version: 2^31}
	const cq = {id: cqId, version: 2^31}
	const data = {name, mobile, address, wechatUser, platform, cq}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {name, mobile, address, wechatUser, platform, cq} = objectToUnpack
	const addressId = address ? address.id : null
	const wechatUserId = wechatUser ? wechatUser.id : null
	const platformId = platform ? platform.id : null
	const cqId = cq ? cq.id : null
	const data = {name, mobile, addressId, wechatUserId, platformId, cqId}
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
const ParentBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default ParentBase



