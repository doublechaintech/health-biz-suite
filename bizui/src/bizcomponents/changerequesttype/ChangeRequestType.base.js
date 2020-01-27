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



const menuData = {menuName: window.trans('change_request_type'), menuFor: "changeRequestType",
  		subItems: [
  {name: 'changeRequestList', displayName: window.mtrans('change_request','change_request_type.change_request_list',false), type:'changeRequest',icon:'exchange-alt',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('change_request_type'), menuFor: "changeRequestType",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('change_request_type.id'),
  name: window.trans('change_request_type.name'),
  code: window.trans('change_request_type.code'),
  icon: window.trans('change_request_type.icon'),
  displayOrder: window.trans('change_request_type.display_order'),
  bindTypes: window.trans('change_request_type.bind_types'),
  stepConfiguration: window.trans('change_request_type.step_configuration'),
  platform: window.trans('change_request_type.platform'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'changeRequestType') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '6',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.code, debugtype: 'string', dataIndex: 'code', width: '18',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.icon, debugtype: 'string', dataIndex: 'icon', width: '8',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.displayOrder, debugtype: 'int', dataIndex: 'displayOrder', width: '5',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.bindTypes, debugtype: 'string_longtext', dataIndex: 'bindTypes', width: '10',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.stepConfiguration, debugtype: 'string_longtext', dataIndex: 'stepConfiguration', width: '10',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.platform, dataIndex: 'platform', render: (text, record) => renderReferenceCell(text, record), sorter:true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(changeRequestType, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={changeRequestType.id}>
	
      <DescriptionList  key={changeRequestType.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{changeRequestType.id}</Description> 
        <Description term={fieldLabels.name} style={{wordBreak: 'break-all'}}>{changeRequestType.name}</Description> 
        <Description term={fieldLabels.code} style={{wordBreak: 'break-all'}}>{changeRequestType.code}</Description> 
        <Description term={fieldLabels.icon} style={{wordBreak: 'break-all'}}>{changeRequestType.icon}</Description> 
        <Description term={fieldLabels.displayOrder}><div style={{"color":"red"}}>{changeRequestType.displayOrder}</div></Description> 
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, code, icon, displayOrder, platformId, bindTypes, stepConfiguration} = formValuesToPack
	const platform = {id: platformId, version: 2^31}
	const data = {name, code, icon, displayOrder, platform, bindTypes, stepConfiguration}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {name, code, icon, displayOrder, platform, bindTypes, stepConfiguration} = objectToUnpack
	const platformId = platform ? platform.id : null
	const data = {name, code, icon, displayOrder, platformId, bindTypes, stepConfiguration}
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
const ChangeRequestTypeBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default ChangeRequestTypeBase



