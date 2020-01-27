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



const menuData = {menuName: window.trans('location'), menuFor: "location",
  		subItems: [
  {name: 'studentList', displayName: window.mtrans('student','location.student_list',false), type:'student',icon:'500px',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'userList', displayName: window.mtrans('user','location.user_list',false), type:'user',icon:'user',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('location'), menuFor: "location",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('location.id'),
  name: window.trans('location.name'),
  address: window.trans('location.address'),
  district: window.trans('location.district'),
  province: window.trans('location.province'),
  latitude: window.trans('location.latitude'),
  longitude: window.trans('location.longitude'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'location') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '8',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.address, debugtype: 'string', dataIndex: 'address', width: '20',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.district, dataIndex: 'district', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.province, dataIndex: 'province', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.latitude, debugtype: 'double', dataIndex: 'latitude', width: '12',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.longitude, debugtype: 'double', dataIndex: 'longitude', width: '13',render: (text, record)=>renderTextCell(text,record)},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(location, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={location.id}>
	
      <DescriptionList  key={location.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{location.id}</Description> 
        <Description term={fieldLabels.name} style={{wordBreak: 'break-all'}}>{location.name}</Description> 
        <Description term={fieldLabels.address} style={{wordBreak: 'break-all'}}>{location.address}</Description> 
        <Description term={fieldLabels.district}><div>{location.district==null?appLocaleName(userContext,"NotAssigned"):`${location.district.displayName}(${location.district.id})`}
        </div></Description>
        <Description term={fieldLabels.province}><div>{location.province==null?appLocaleName(userContext,"NotAssigned"):`${location.province.displayName}(${location.province.id})`}
        </div></Description>
        <Description term={fieldLabels.latitude}><div style={{"color":"red"}}>{location.latitude}</div></Description> 
        <Description term={fieldLabels.longitude}><div style={{"color":"red"}}>{location.longitude}</div></Description> 
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, address, latitude, longitude, districtId, provinceId} = formValuesToPack
	const district = {id: districtId, version: 2^31}
	const province = {id: provinceId, version: 2^31}
	const data = {name, address, latitude, longitude, district, province}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {name, address, latitude, longitude, district, province} = objectToUnpack
	const districtId = district ? district.id : null
	const provinceId = province ? province.id : null
	const data = {name, address, latitude, longitude, districtId, provinceId}
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
const LocationBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default LocationBase



