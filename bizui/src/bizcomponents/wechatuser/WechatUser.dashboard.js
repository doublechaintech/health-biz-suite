

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
import styles from './WechatUser.dashboard.less'
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


const imageList =(wechatUser)=>{return [
	   {"title":'头像',"imageLocation":wechatUser.avatar},
]}

const internalImageListOf = (wechatUser) =>defaultImageListOf(wechatUser,imageList)

const optionList =(wechatUser)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalRenderSubjectList = defaultRenderSubjectList
const internalSettingListOf = (wechatUser) =>defaultSettingListOf(wechatUser, optionList)
const internalLargeTextOf = (wechatUser) =>{

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
      <Link to={`/wechatUser/${targetComponent.props.wechatUser.id}/list/${item.name}/${item.displayName}/`}>
        <span>{item.displayName}</span>
        </Link>
        </Menu.Item>
  )

}
const renderSettingMenu = (cardsData,targetComponent) =>{

  const userContext = null
  return (<Menu>
    	<Menu.Item key="profile">
  			<Link to={`/wechatUser/${targetComponent.props.wechatUser.id}/permission`}><Icon type="safety-certificate" theme="twoTone" twoToneColor="#52c41a"/><span>{appLocaleName(userContext,"Permission")}</span></Link>
		</Menu.Item>
		<Menu.Divider />
		{cardsData.subSettingItems.map(item=>renderSettingMenuItem(item,cardsData,targetComponent))}
		</Menu>)

}

const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <Icon type="double-left" style={{marginRight:"10px"}} /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName} {renderSettingDropDown(cardsData,targetComponent)}</div>)

}


const internalSummaryOf = (wechatUser,targetComponent) =>{
	
	
	const {WechatUserService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID" style={{wordBreak: 'break-all'}}>{wechatUser.id}</Description> 
<Description term="名称" style={{wordBreak: 'break-all'}}>{wechatUser.name}</Description> 
<Description term="地址">{wechatUser.address==null?appLocaleName(userContext,"NotAssigned"):`${wechatUser.address.displayName}(${wechatUser.address.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"地址","location",WechatUserService.requestCandidateAddress,
	      WechatUserService.transferToAnotherAddress,"anotherAddressId",wechatUser.address?wechatUser.address.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="用户类型">{wechatUser.userType==null?appLocaleName(userContext,"NotAssigned"):`${wechatUser.userType.displayName}(${wechatUser.userType.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"用户类型","userType",WechatUserService.requestCandidateUserType,
	      WechatUserService.transferToAnotherUserType,"anotherUserTypeId",wechatUser.userType?wechatUser.userType.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="创建时间">{ moment(wechatUser.createTime).format('YYYY-MM-DD HH:mm')}</Description> 
	
        {buildTransferModal(wechatUser,targetComponent)}
      </DescriptionList>
	)

}

const internalQuickFunctions = defaultQuickFunctions

class WechatUserDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'wechatUser'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName, parentListMetaInfo, classQuestionListMetaInfo, classDailyHealthSurveyListMetaInfo, wechatLoginInfoListMetaInfo, parentCount, classQuestionCount, classDailyHealthSurveyCount, wechatLoginInfoCount } = this.props.wechatUser
    if(!this.props.wechatUser.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"微信用户",cardsFor: "wechatUser",
    	cardsSource: this.props.wechatUser,returnURL,displayName,
  		subItems: [
{name: 'parentList', displayName: window.mtrans('parent','wechat_user.parent_list',false) ,viewGroup:'__no_group', type:'parent',count:parentCount,addFunction: true, role: 'parent', metaInfo: parentListMetaInfo, renderItem: GlobalComponents.ParentBase.renderItemOfList},
{name: 'classQuestionList', displayName: window.mtrans('class_question','wechat_user.class_question_list',false) ,viewGroup:'__no_group', type:'classQuestion',count:classQuestionCount,addFunction: true, role: 'classQuestion', metaInfo: classQuestionListMetaInfo, renderItem: GlobalComponents.ClassQuestionBase.renderItemOfList},
{name: 'classDailyHealthSurveyList', displayName: window.mtrans('class_daily_health_survey','wechat_user.class_daily_health_survey_list',false) ,viewGroup:'__no_group', type:'classDailyHealthSurvey',count:classDailyHealthSurveyCount,addFunction: true, role: 'classDailyHealthSurvey', metaInfo: classDailyHealthSurveyListMetaInfo, renderItem: GlobalComponents.ClassDailyHealthSurveyBase.renderItemOfList},
{name: 'wechatLoginInfoList', displayName: window.mtrans('wechat_login_info','wechat_user.wechat_login_info_list',false) ,viewGroup:'__no_group', type:'wechatLoginInfo',count:wechatLoginInfoCount,addFunction: true, role: 'wechatLoginInfo', metaInfo: wechatLoginInfoListMetaInfo, renderItem: GlobalComponents.WechatLoginInfoBase.renderItemOfList},
    
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
  wechatUser: state._wechatUser,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(WechatUserDashboard))

