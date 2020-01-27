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



const menuData = {menuName: window.trans('user_daily_answer'), menuFor: "userDailyAnswer",
  		subItems: [
  {name: 'dailyAnswerList', displayName: window.mtrans('daily_answer','user_daily_answer.daily_answer_list',false), type:'dailyAnswer',icon:'500px',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}


const settingMenuData = {menuName: window.trans('user_daily_answer'), menuFor: "userDailyAnswer",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('user_daily_answer.id'),
  name: window.trans('user_daily_answer.name'),
  user: window.trans('user_daily_answer.user'),
  answerTime: window.trans('user_daily_answer.answer_time'),
  isAnswerSubmitted: window.trans('user_daily_answer.is_answer_submitted'),
  organization: window.trans('user_daily_answer.organization'),
  dailyHealthSurvey: window.trans('user_daily_answer.daily_health_survey'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'userDailyAnswer') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '9',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.user, dataIndex: 'user', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.answerTime, dataIndex: 'answerTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},
  { title: fieldLabels.isAnswerSubmitted, dataIndex: 'isAnswerSubmitted', render: (text, record) =>renderBooleanCell(text, record), sorter:true },
  { title: fieldLabels.organization, dataIndex: 'organization', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.dailyHealthSurvey, dataIndex: 'dailyHealthSurvey', render: (text, record) => renderReferenceCell(text, record), sorter:true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(userDailyAnswer, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={userDailyAnswer.id}>
	
      <DescriptionList  key={userDailyAnswer.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{userDailyAnswer.id}</Description> 
        <Description term={fieldLabels.name} style={{wordBreak: 'break-all'}}>{userDailyAnswer.name}</Description> 
        <Description term={fieldLabels.user}><div>{userDailyAnswer.user==null?appLocaleName(userContext,"NotAssigned"):`${userDailyAnswer.user.displayName}(${userDailyAnswer.user.id})`}
        </div></Description>
        <Description term={fieldLabels.answerTime}><div>{ moment(userDailyAnswer.answerTime).format('YYYY-MM-DD HH:mm')}</div></Description> 
        <Description term={fieldLabels.organization}><div>{userDailyAnswer.organization==null?appLocaleName(userContext,"NotAssigned"):`${userDailyAnswer.organization.displayName}(${userDailyAnswer.organization.id})`}
        </div></Description>
        <Description term={fieldLabels.dailyHealthSurvey}><div>{userDailyAnswer.dailyHealthSurvey==null?appLocaleName(userContext,"NotAssigned"):`${userDailyAnswer.dailyHealthSurvey.displayName}(${userDailyAnswer.dailyHealthSurvey.id})`}
        </div></Description>
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, answerTime, userId, organizationId, dailyHealthSurveyId} = formValuesToPack
	const user = {id: userId, version: 2^31}
	const organization = {id: organizationId, version: 2^31}
	const dailyHealthSurvey = {id: dailyHealthSurveyId, version: 2^31}
	const data = {name, answerTime, user, organization, dailyHealthSurvey}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {name, answerTime, user, organization, dailyHealthSurvey} = objectToUnpack
	const userId = user ? user.id : null
	const organizationId = organization ? organization.id : null
	const dailyHealthSurveyId = dailyHealthSurvey ? dailyHealthSurvey.id : null
	const data = {name, answerTime, userId, organizationId, dailyHealthSurveyId}
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
const UserDailyAnswerBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default UserDailyAnswerBase



