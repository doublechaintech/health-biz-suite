

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
import styles from './Organization.dashboard.less'
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


const imageList =(organization)=>{return [
	 ]}

const internalImageListOf = (organization) =>defaultImageListOf(organization,imageList)

const optionList =(organization)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalRenderSubjectList = defaultRenderSubjectList
const internalSettingListOf = (organization) =>defaultSettingListOf(organization, optionList)
const internalLargeTextOf = (organization) =>{

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
      <Link to={`/organization/${targetComponent.props.organization.id}/list/${item.name}/${item.displayName}/`}>
        <span>{item.displayName}</span>
        </Link>
        </Menu.Item>
  )

}
const renderSettingMenu = (cardsData,targetComponent) =>{

  const userContext = null
  return (<Menu>
    	<Menu.Item key="profile">
  			<Link to={`/organization/${targetComponent.props.organization.id}/permission`}><Icon type="safety-certificate" theme="twoTone" twoToneColor="#52c41a"/><span>{appLocaleName(userContext,"Permission")}</span></Link>
		</Menu.Item>
		<Menu.Divider />
		{cardsData.subSettingItems.map(item=>renderSettingMenuItem(item,cardsData,targetComponent))}
		</Menu>)

}

const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <Icon type="double-left" style={{marginRight:"10px"}} /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName} {renderSettingDropDown(cardsData,targetComponent)}</div>)

}


const internalSummaryOf = (organization,targetComponent) =>{
	
	
	const {OrganizationService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID" style={{wordBreak: 'break-all'}}>{organization.id}</Description> 
<Description term="名称" style={{wordBreak: 'break-all'}}>{organization.name}</Description> 
<Description term="主管">{organization.supervisor==null?appLocaleName(userContext,"NotAssigned"):`${organization.supervisor.displayName}(${organization.supervisor.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"主管","supervisor",OrganizationService.requestCandidateSupervisor,
	      OrganizationService.transferToAnotherSupervisor,"anotherSupervisorId",organization.supervisor?organization.supervisor.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="区/县">{organization.district==null?appLocaleName(userContext,"NotAssigned"):`${organization.district.displayName}(${organization.district.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"区/县","district",OrganizationService.requestCandidateDistrict,
	      OrganizationService.transferToAnotherDistrict,"anotherDistrictId",organization.district?organization.district.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
	
        {buildTransferModal(organization,targetComponent)}
      </DescriptionList>
	)

}

const internalQuickFunctions = defaultQuickFunctions

class OrganizationDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'organization'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName, questionListMetaInfo, dailyHealthSurveyListMetaInfo, userDailyAnswerListMetaInfo, wechatUserListMetaInfo, questionCount, dailyHealthSurveyCount, userDailyAnswerCount, wechatUserCount } = this.props.organization
    if(!this.props.organization.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"公证机构名称",cardsFor: "organization",
    	cardsSource: this.props.organization,returnURL,displayName,
  		subItems: [
{name: 'questionList', displayName: window.mtrans('question','organization.question_list',false) ,viewGroup:'__no_group', type:'question',count:questionCount,addFunction: true, role: 'question', metaInfo: questionListMetaInfo, renderItem: GlobalComponents.QuestionBase.renderItemOfList},
{name: 'dailyHealthSurveyList', displayName: window.mtrans('daily_health_survey','organization.daily_health_survey_list',false) ,viewGroup:'__no_group', type:'dailyHealthSurvey',count:dailyHealthSurveyCount,addFunction: true, role: 'dailyHealthSurvey', metaInfo: dailyHealthSurveyListMetaInfo, renderItem: GlobalComponents.DailyHealthSurveyBase.renderItemOfList},
{name: 'userDailyAnswerList', displayName: window.mtrans('user_daily_answer','organization.user_daily_answer_list',false) ,viewGroup:'__no_group', type:'userDailyAnswer',count:userDailyAnswerCount,addFunction: true, role: 'userDailyAnswer', metaInfo: userDailyAnswerListMetaInfo, renderItem: GlobalComponents.UserDailyAnswerBase.renderItemOfList},
{name: 'wechatUserList', displayName: window.mtrans('wechat_user','organization.wechat_user_list',false) ,viewGroup:'__no_group', type:'wechatUser',count:wechatUserCount,addFunction: true, role: 'wechatUser', metaInfo: wechatUserListMetaInfo, renderItem: GlobalComponents.WechatUserBase.renderItemOfList},
    
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
  organization: state._organization,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(OrganizationDashboard))

