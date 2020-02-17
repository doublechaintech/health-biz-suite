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



const menuData = {menuName: window.trans('question_type'), menuFor: "questionType",
  		subItems: [
  {name: 'questionList', displayName: window.mtrans('question','question_type.question_list',false), type:'question',icon:'question',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'dailySurveyQuestionList', displayName: window.mtrans('daily_survey_question','question_type.daily_survey_question_list',false), type:'dailySurveyQuestion',icon:'question',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('question_type'), menuFor: "questionType",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('question_type.id'),
  name: window.trans('question_type.name'),
  code: window.trans('question_type.code'),
  platform: window.trans('question_type.platform'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'questionType') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '7',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.code, debugtype: 'string', dataIndex: 'code', width: '17',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.platform, dataIndex: 'platform', render: (text, record) => renderReferenceCell(text, record), sorter:true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(questionType, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={questionType.id}>
	
      <DescriptionList  key={questionType.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{questionType.id}</Description> 
        <Description term={fieldLabels.name} style={{wordBreak: 'break-all'}}>{questionType.name}</Description> 
        <Description term={fieldLabels.code} style={{wordBreak: 'break-all'}}>{questionType.code}</Description> 
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, code, platformId} = formValuesToPack
	const platform = {id: platformId, version: 2^31}
	const data = {name, code, platform}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {name, code, platform} = objectToUnpack
	const platformId = platform ? platform.id : null
	const data = {name, code, platformId}
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
const QuestionTypeBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default QuestionTypeBase



