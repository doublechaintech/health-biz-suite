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



const menuData = {menuName: window.trans('district'), menuFor: "district",
  		subItems: [
  {name: 'locationList', displayName: window.mtrans('location','district.location_list',false), type:'location',icon:'at',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('district'), menuFor: "district",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('district.id'),
  name: window.trans('district.name'),
  city: window.trans('district.city'),
  platform: window.trans('district.platform'),
  createTime: window.trans('district.create_time'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'district') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '7',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.city, dataIndex: 'city', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.platform, dataIndex: 'platform', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.createTime, dataIndex: 'createTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(district, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={district.id}>
	
      <DescriptionList  key={district.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{district.id}</Description> 
        <Description term={fieldLabels.name} style={{wordBreak: 'break-all'}}>{district.name}</Description> 
        <Description term={fieldLabels.city}><div>{district.city==null?appLocaleName(userContext,"NotAssigned"):`${district.city.displayName}(${district.city.id})`}
        </div></Description>
        <Description term={fieldLabels.createTime}><div>{ moment(district.createTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, cityId, platformId} = formValuesToPack
	const city = {id: cityId, version: 2^31}
	const platform = {id: platformId, version: 2^31}
	const data = {name, city, platform}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {name, city, platform} = objectToUnpack
	const cityId = city ? city.id : null
	const platformId = platform ? platform.id : null
	const data = {name, cityId, platformId}
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
const DistrictBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default DistrictBase



