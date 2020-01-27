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



const menuData = {menuName: window.trans('city'), menuFor: "city",
  		subItems: [
  
  		],
}


const settingMenuData = {menuName: window.trans('city'), menuFor: "city",
  		subItems: [
  {name: 'districtList', displayName: window.mtrans('district','city.district_list',false), type:'district', icon:'500px',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}

const fieldLabels = {
  id: window.trans('city.id'),
  name: window.trans('city.name'),
  province: window.trans('city.province'),
  platform: window.trans('city.platform'),
  createTime: window.trans('city.create_time'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'city') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '7',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.province, dataIndex: 'province', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.platform, dataIndex: 'platform', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.createTime, dataIndex: 'createTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(city, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={city.id}>
	
      <DescriptionList  key={city.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{city.id}</Description> 
        <Description term={fieldLabels.name} style={{wordBreak: 'break-all'}}>{city.name}</Description> 
        <Description term={fieldLabels.province}><div>{city.province==null?appLocaleName(userContext,"NotAssigned"):`${city.province.displayName}(${city.province.id})`}
        </div></Description>
        <Description term={fieldLabels.createTime}><div>{ moment(city.createTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, provinceId, platformId} = formValuesToPack
	const province = {id: provinceId, version: 2^31}
	const platform = {id: platformId, version: 2^31}
	const data = {name, province, platform}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {name, province, platform} = objectToUnpack
	const provinceId = province ? province.id : null
	const platformId = platform ? platform.id : null
	const data = {name, provinceId, platformId}
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
const CityBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default CityBase



