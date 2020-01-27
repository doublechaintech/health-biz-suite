

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Button, Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'
import {
  ChartCard, yuan, MiniArea, MiniBar, MiniProgress, Field, Bar, Pie, TimelineChart,
} from '../../components/Charts'
import Trend from '../../components/Trend'
import NumberInfo from '../../components/NumberInfo'
import { getTimeDistance } from '../../utils/utils'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './UserDailyAnswer.dashboard.less'
import DescriptionList from '../../components/DescriptionList';
import ImagePreview from '../../components/ImagePreview';
import GlobalComponents from '../../custcomponents';
import DashboardTool from '../../common/Dashboard.tool'
import appLocaleName from '../../common/Locale.tool'

const {aggregateDataset,calcKey, defaultHideCloseTrans,
  defaultImageListOf,defaultSettingListOf,defaultBuildTransferModal,
  defaultExecuteTrans,defaultHandleTransferSearch,defaultShowTransferModel,
  defaultRenderExtraHeader,
  defaultSubListsOf,defaultRenderAnalytics,
  defaultRenderExtraFooter,renderForTimeLine,renderForNumbers,
  defaultQuickFunctions, defaultRenderSubjectList,
}= DashboardTool



const { Description } = DescriptionList;
const { TabPane } = Tabs
const { RangePicker } = DatePicker
const { Option } = Select


const imageList =(userDailyAnswer)=>{return [
	 ]}

const internalImageListOf = (userDailyAnswer) =>defaultImageListOf(userDailyAnswer,imageList)

const optionList =(userDailyAnswer)=>{return [ 
	  {"title":'答案提交',"value":userDailyAnswer.isAnswerSubmitted,"parameterName":"isAnswerSubmitted"},
]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalRenderSubjectList = defaultRenderSubjectList
const internalSettingListOf = (userDailyAnswer) =>defaultSettingListOf(userDailyAnswer, optionList)
const internalLargeTextOf = (userDailyAnswer) =>{

	return null
	

}


const internalRenderExtraHeader = defaultRenderExtraHeader

const internalRenderExtraFooter = defaultRenderExtraFooter
const internalSubListsOf = defaultSubListsOf


const renderSettingDropDown = (cardsData,targetComponent)=>{

  return (<div style={{float: 'right'}} >
        <Dropdown overlay={renderSettingMenu(cardsData,targetComponent)} placement="bottomRight" >
       
        <Button>
        <Icon type="setting" theme="filled" twoToneColor="#00b" style={{color:'#3333b0'}}/> 设置  <Icon type="down"/>
      </Button>
      </Dropdown></div>)

}

const renderSettingMenuItem = (item,cardsData,targetComponent) =>{

  const userContext = null
  return (<Menu.Item key={item.name}>
      <Link to={`/userDailyAnswer/${targetComponent.props.userDailyAnswer.id}/list/${item.name}/${item.displayName}/`}>
        <span>{item.displayName}</span>
        </Link>
        </Menu.Item>
  )

}
const renderSettingMenu = (cardsData,targetComponent) =>{

  const userContext = null
  return (<Menu>
    	<Menu.Item key="profile">
  			<Link to={`/userDailyAnswer/${targetComponent.props.userDailyAnswer.id}/permission`}><Icon type="safety-certificate" theme="twoTone" twoToneColor="#52c41a"/><span>{appLocaleName(userContext,"Permission")}</span></Link>
		</Menu.Item>
		<Menu.Divider />
		{cardsData.subSettingItems.map(item=>renderSettingMenuItem(item,cardsData,targetComponent))}
		</Menu>)

}

const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <Icon type="double-left" style={{marginRight:"10px"}} /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName} {renderSettingDropDown(cardsData,targetComponent)}</div>)

}


const internalSummaryOf = (userDailyAnswer,targetComponent) =>{
	
	
	const {UserDailyAnswerService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID" style={{wordBreak: 'break-all'}}>{userDailyAnswer.id}</Description> 
<Description term="名称" style={{wordBreak: 'break-all'}}>{userDailyAnswer.name}</Description> 
<Description term="用户">{userDailyAnswer.user==null?appLocaleName(userContext,"NotAssigned"):`${userDailyAnswer.user.displayName}(${userDailyAnswer.user.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"用户","wechatUser",UserDailyAnswerService.requestCandidateUser,
	      UserDailyAnswerService.transferToAnotherUser,"anotherUserId",userDailyAnswer.user?userDailyAnswer.user.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="回答时间">{ moment(userDailyAnswer.answerTime).format('YYYY-MM-DD HH:mm')}</Description> 
<Description term="公证机构名称">{userDailyAnswer.organization==null?appLocaleName(userContext,"NotAssigned"):`${userDailyAnswer.organization.displayName}(${userDailyAnswer.organization.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"公证机构名称","organization",UserDailyAnswerService.requestCandidateOrganization,
	      UserDailyAnswerService.transferToAnotherOrganization,"anotherOrganizationId",userDailyAnswer.organization?userDailyAnswer.organization.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="每日健康调查">{userDailyAnswer.dailyHealthSurvey==null?appLocaleName(userContext,"NotAssigned"):`${userDailyAnswer.dailyHealthSurvey.displayName}(${userDailyAnswer.dailyHealthSurvey.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"每日健康调查","dailyHealthSurvey",UserDailyAnswerService.requestCandidateDailyHealthSurvey,
	      UserDailyAnswerService.transferToAnotherDailyHealthSurvey,"anotherDailyHealthSurveyId",userDailyAnswer.dailyHealthSurvey?userDailyAnswer.dailyHealthSurvey.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
	
        {buildTransferModal(userDailyAnswer,targetComponent)}
      </DescriptionList>
	)

}

const internalQuickFunctions = defaultQuickFunctions

class UserDailyAnswerDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'userDailyAnswer'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName, dailyAnswerListMetaInfo, dailyAnswerCount } = this.props.userDailyAnswer
    if(!this.props.userDailyAnswer.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"用户每日回答",cardsFor: "userDailyAnswer",
    	cardsSource: this.props.userDailyAnswer,returnURL,displayName,
  		subItems: [
{name: 'dailyAnswerList', displayName: window.mtrans('daily_answer','user_daily_answer.daily_answer_list',false) ,viewGroup:'__no_group', type:'dailyAnswer',count:dailyAnswerCount,addFunction: true, role: 'dailyAnswer', metaInfo: dailyAnswerListMetaInfo, renderItem: GlobalComponents.DailyAnswerBase.renderItemOfList},
    
      	],
   		subSettingItems: [
    
      	],     	
      	
  	};
    
    const renderExtraHeader = this.props.renderExtraHeader || internalRenderExtraHeader
    const settingListOf = this.props.settingListOf || internalSettingListOf
    const imageListOf = this.props.imageListOf || internalImageListOf
    const subListsOf = this.props.subListsOf || internalSubListsOf
    const largeTextOf = this.props.largeTextOf ||internalLargeTextOf
    const summaryOf = this.props.summaryOf || internalSummaryOf
    const renderTitle = this.props.renderTitle || internalRenderTitle
    const renderExtraFooter = this.props.renderExtraFooter || internalRenderExtraFooter
    const renderAnalytics = this.props.renderAnalytics || defaultRenderAnalytics
    const quickFunctions = this.props.quickFunctions || internalQuickFunctions
    const renderSubjectList = this.props.renderSubjectList || internalRenderSubjectList
    
    return (

      <PageHeaderLayout
        title={renderTitle(cardsData,this)}
        content={summaryOf(cardsData.cardsSource,this)}
        wrapperClassName={styles.advancedForm}
      >
       
        {renderExtraHeader(cardsData.cardsSource)}
        
        {quickFunctions(cardsData)} 
        {imageListOf(cardsData.cardsSource)}  
        {renderAnalytics(cardsData.cardsSource)}
        {settingListOf(cardsData.cardsSource)}
        {renderSubjectList(cardsData)}       
        {largeTextOf(cardsData.cardsSource)}
        {renderExtraFooter(cardsData.cardsSource)}
  		
      </PageHeaderLayout>
    
    )
  }
}

export default connect(state => ({
  userDailyAnswer: state._userDailyAnswer,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(UserDailyAnswerDashboard))

