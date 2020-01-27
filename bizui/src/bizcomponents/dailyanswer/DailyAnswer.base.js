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



const menuData = {menuName: window.trans('daily_answer'), menuFor: "dailyAnswer",
  		subItems: [
  
  		],
}


const settingMenuData = {menuName: window.trans('daily_answer'), menuFor: "dailyAnswer",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: window.trans('daily_answer.id'),
  name: window.trans('daily_answer.name'),
  userDailyAnswer: window.trans('daily_answer.user_daily_answer'),
  userAnswer: window.trans('daily_answer.user_answer'),

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'dailyAnswer') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '6',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.userDailyAnswer, dataIndex: 'userDailyAnswer', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.userAnswer, dataIndex: 'userAnswer', render: (text, record) => renderReferenceCell(text, record), sorter:true},

]


const searchLocalData =(targetObject,searchTerm)=> defaultSearchLocalData(menuData,targetObject,searchTerm)

const renderItemOfList=(dailyAnswer, targetComponent, columCount)=>{
  const displayColumnsCount = columCount || 2
  const userContext = null
  return (
    <div key={dailyAnswer.id}>
	
      <DescriptionList  key={dailyAnswer.id} size="small" col="2" >
        <Description term={fieldLabels.id} style={{wordBreak: 'break-all'}}>{dailyAnswer.id}</Description> 
        <Description term={fieldLabels.name} style={{wordBreak: 'break-all'}}>{dailyAnswer.name}</Description> 
        <Description term={fieldLabels.userDailyAnswer}><div>{dailyAnswer.userDailyAnswer==null?appLocaleName(userContext,"NotAssigned"):`${dailyAnswer.userDailyAnswer.displayName}(${dailyAnswer.userDailyAnswer.id})`}
        </div></Description>
        <Description term={fieldLabels.userAnswer}><div>{dailyAnswer.userAnswer==null?appLocaleName(userContext,"NotAssigned"):`${dailyAnswer.userAnswer.displayName}(${dailyAnswer.userAnswer.id})`}
        </div></Description>
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	
const packFormValuesToObject = ( formValuesToPack )=>{
	const {name, userDailyAnswerId, userAnswerId} = formValuesToPack
	const userDailyAnswer = {id: userDailyAnswerId, version: 2^31}
	const userAnswer = {id: userAnswerId, version: 2^31}
	const data = {name, userDailyAnswer, userAnswer}
	return data
}
const unpackObjectToFormValues = ( objectToUnpack )=>{
	const {name, userDailyAnswer, userAnswer} = objectToUnpack
	const userDailyAnswerId = userDailyAnswer ? userDailyAnswer.id : null
	const userAnswerId = userAnswer ? userAnswer.id : null
	const data = {name, userDailyAnswerId, userAnswerId}
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
const DailyAnswerBase={menuData,displayColumns,fieldLabels,renderItemOfList, stepOf, searchLocalData}
export default DailyAnswerBase



