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



const menuData = {menuName: window.trans('daily_health_survey'), menuFor: "dailyHealthSurvey",
  		subItems: [
  {name: 'surveyQuestionList', displayName: window.mtrans('survey_question','daily_health_survey.survey_question_list',false), type:'surveyQuestion',icon:'question',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'userDailyAnswerList', displayName: window.mtrans('user_daily_answer','daily_health_survey.user_daily_answer_list',false), type:'userDailyAnswer',icon:'user',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('daily_health_survey'), menuFor: "dailyHealthSurvey",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('daily_health_survey.id'),
  name: window.trans('daily_health_survey.name'),
  organization: window.trans('daily_health_survey.organization'),
  surveyTime: window.trans('daily_health_survey.survey_time'),
  createTime: window.trans('daily_health_survey.create_time'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'dailyHealthSurvey') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '26',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.organization, dataIndex: 'organization', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.surveyTime, dataIndex: 'surveyTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},
  { title: fieldLabels.createTime, dataIndex: 'createTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(dailyHealthSurvey, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={dailyHealthSurvey.id}>
	
      <DescriptionList  key={dailyHealthSurvey.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{dailyHealthSurvey.id}</Description> 
        <Description term={fieldLabels.name} style={{wordBreak: 'break-all'}}>{dailyHealthSurvey.name}</Description> 
        <Description term={fieldLabels.organization}><div>{dailyHealthSurvey.organization==null?appLocaleName(userContext,"NotAssigned"):`${dailyHealthSurvey.organization.displayName}(${dailyHealthSurvey.organization.id})`}
        </div></Description>
        <Description term={fieldLabels.surveyTime}><div>{ moment(dailyHealthSurvey.surveyTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
        <Description term={fieldLabels.createTime}><div>{ moment(dailyHealthSurvey.createTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, surveyTime, organizationId} = formValuesToPack
	const organization = {id: organizationId, version: 2^31}
	const data = {name, surveyTime, organization}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {name, surveyTime, organization} = objectToUnpack
	const organizationId = organization ? organization.id : null
	const data = {name, surveyTime, organizationId}
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
const DailyHealthSurveyBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default DailyHealthSurveyBase



