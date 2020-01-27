

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
import styles from './DailyHealthSurvey.dashboard.less'
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


const imageList =(dailyHealthSurvey)=>{return [
	 ]}

const internalImageListOf = (dailyHealthSurvey) =>defaultImageListOf(dailyHealthSurvey,imageList)

const optionList =(dailyHealthSurvey)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalRenderSubjectList = defaultRenderSubjectList
const internalSettingListOf = (dailyHealthSurvey) =>defaultSettingListOf(dailyHealthSurvey, optionList)
const internalLargeTextOf = (dailyHealthSurvey) =>{

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
      <Link to={`/dailyHealthSurvey/${targetComponent.props.dailyHealthSurvey.id}/list/${item.name}/${item.displayName}/`}>
        <span>{item.displayName}</span>
        </Link>
        </Menu.Item>
  )

}
const renderSettingMenu = (cardsData,targetComponent) =>{

  const userContext = null
  return (<Menu>
    	<Menu.Item key="profile">
  			<Link to={`/dailyHealthSurvey/${targetComponent.props.dailyHealthSurvey.id}/permission`}><Icon type="safety-certificate" theme="twoTone" twoToneColor="#52c41a"/><span>{appLocaleName(userContext,"Permission")}</span></Link>
		</Menu.Item>
		<Menu.Divider />
		{cardsData.subSettingItems.map(item=>renderSettingMenuItem(item,cardsData,targetComponent))}
		</Menu>)

}

const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <Icon type="double-left" style={{marginRight:"10px"}} /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName} {renderSettingDropDown(cardsData,targetComponent)}</div>)

}


const internalSummaryOf = (dailyHealthSurvey,targetComponent) =>{
	
	
	const {DailyHealthSurveyService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID" style={{wordBreak: 'break-all'}}>{dailyHealthSurvey.id}</Description> 
<Description term="名称" style={{wordBreak: 'break-all'}}>{dailyHealthSurvey.name}</Description> 
<Description term="公证机构名称">{dailyHealthSurvey.organization==null?appLocaleName(userContext,"NotAssigned"):`${dailyHealthSurvey.organization.displayName}(${dailyHealthSurvey.organization.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"公证机构名称","organization",DailyHealthSurveyService.requestCandidateOrganization,
	      DailyHealthSurveyService.transferToAnotherOrganization,"anotherOrganizationId",dailyHealthSurvey.organization?dailyHealthSurvey.organization.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="调查的时间">{ moment(dailyHealthSurvey.surveyTime).format('YYYY-MM-DD HH:mm')}</Description> 
<Description term="创建时间">{ moment(dailyHealthSurvey.createTime).format('YYYY-MM-DD HH:mm')}</Description> 
	
        {buildTransferModal(dailyHealthSurvey,targetComponent)}
      </DescriptionList>
	)

}

const internalQuickFunctions = defaultQuickFunctions

class DailyHealthSurveyDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'dailyHealthSurvey'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName, surveyQuestionListMetaInfo, userDailyAnswerListMetaInfo, surveyQuestionCount, userDailyAnswerCount } = this.props.dailyHealthSurvey
    if(!this.props.dailyHealthSurvey.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"每日健康调查",cardsFor: "dailyHealthSurvey",
    	cardsSource: this.props.dailyHealthSurvey,returnURL,displayName,
  		subItems: [
{name: 'surveyQuestionList', displayName: window.mtrans('survey_question','daily_health_survey.survey_question_list',false) ,viewGroup:'__no_group', type:'surveyQuestion',count:surveyQuestionCount,addFunction: true, role: 'surveyQuestion', metaInfo: surveyQuestionListMetaInfo, renderItem: GlobalComponents.SurveyQuestionBase.renderItemOfList},
{name: 'userDailyAnswerList', displayName: window.mtrans('user_daily_answer','daily_health_survey.user_daily_answer_list',false) ,viewGroup:'__no_group', type:'userDailyAnswer',count:userDailyAnswerCount,addFunction: true, role: 'userDailyAnswer', metaInfo: userDailyAnswerListMetaInfo, renderItem: GlobalComponents.UserDailyAnswerBase.renderItemOfList},
    
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
  dailyHealthSurvey: state._dailyHealthSurvey,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(DailyHealthSurveyDashboard))

