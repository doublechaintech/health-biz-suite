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



const menuData = {menuName: window.trans('supervisor'), menuFor: "supervisor",
  		subItems: [
  {name: 'organizationList', displayName: window.mtrans('organization','supervisor.organization_list',false), type:'organization',icon:'at',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('supervisor'), menuFor: "supervisor",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('supervisor.id'),
  name: window.trans('supervisor.name'),
  gender: window.trans('supervisor.gender'),
  mobile: window.trans('supervisor.mobile'),
  platform: window.trans('supervisor.platform'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'supervisor') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '6',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.gender, debugtype: 'string_gender', dataIndex: 'gender', width: '10',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.mobile, debugtype: 'string_china_mobile_phone', dataIndex: 'mobile', width: '15',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.platform, dataIndex: 'platform', render: (text, record) => renderReferenceCell(text, record), sorter:true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(supervisor, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={supervisor.id}>
	
      <DescriptionList  key={supervisor.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{supervisor.id}</Description> 
        <Description term={fieldLabels.name} style={{wordBreak: 'break-all'}}>{supervisor.name}</Description> 
        <Description term={fieldLabels.gender} style={{wordBreak: 'break-all'}}>{supervisor.gender}</Description> 
        <Description term={fieldLabels.mobile} style={{wordBreak: 'break-all'}}>{supervisor.mobile}</Description> 
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, gender, mobile, platformId} = formValuesToPack
	const platform = {id: platformId, version: 2^31}
	const data = {name, gender, mobile, platform}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {name, gender, mobile, platform} = objectToUnpack
	const platformId = platform ? platform.id : null
	const data = {name, gender, mobile, platformId}
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
const SupervisorBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default SupervisorBase



