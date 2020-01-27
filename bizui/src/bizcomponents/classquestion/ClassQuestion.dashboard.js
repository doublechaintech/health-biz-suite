

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
import styles from './ClassQuestion.dashboard.less'
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


const imageList =(classQuestion)=>{return [
	 ]}

const internalImageListOf = (classQuestion) =>defaultImageListOf(classQuestion,imageList)

const optionList =(classQuestion)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalRenderSubjectList = defaultRenderSubjectList
const internalSettingListOf = (classQuestion) =>defaultSettingListOf(classQuestion, optionList)
const internalLargeTextOf = (classQuestion) =>{

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
      <Link to={`/classQuestion/${targetComponent.props.classQuestion.id}/list/${item.name}/${item.displayName}/`}>
        <span>{item.displayName}</span>
        </Link>
        </Menu.Item>
  )

}
const renderSettingMenu = (cardsData,targetComponent) =>{

  const userContext = null
  return (<Menu>
    	<Menu.Item key="profile">
  			<Link to={`/classQuestion/${targetComponent.props.classQuestion.id}/permission`}><Icon type="safety-certificate" theme="twoTone" twoToneColor="#52c41a"/><span>{appLocaleName(userContext,"Permission")}</span></Link>
		</Menu.Item>
		<Menu.Divider />
		{cardsData.subSettingItems.map(item=>renderSettingMenuItem(item,cardsData,targetComponent))}
		</Menu>)

}

const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <Icon type="double-left" style={{marginRight:"10px"}} /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName} {renderSettingDropDown(cardsData,targetComponent)}</div>)

}


const internalSummaryOf = (classQuestion,targetComponent) =>{
	
	
	const {ClassQuestionService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID" style={{wordBreak: 'break-all'}}>{classQuestion.id}</Description> 
<Description term="活动主题" style={{wordBreak: 'break-all'}}>{classQuestion.topic}</Description> 
<Description term="问题类型">{classQuestion.questionType==null?appLocaleName(userContext,"NotAssigned"):`${classQuestion.questionType.displayName}(${classQuestion.questionType.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"问题类型","questionType",ClassQuestionService.requestCandidateQuestionType,
	      ClassQuestionService.transferToAnotherQuestionType,"anotherQuestionTypeId",classQuestion.questionType?classQuestion.questionType.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="A选项" style={{wordBreak: 'break-all'}}>{classQuestion.optionA}</Description> 
<Description term="B选项" style={{wordBreak: 'break-all'}}>{classQuestion.optionB}</Description> 
<Description term="C选项" style={{wordBreak: 'break-all'}}>{classQuestion.optionC}</Description> 
<Description term="D选项" style={{wordBreak: 'break-all'}}>{classQuestion.optionD}</Description> 
<Description term="问题的来源">{classQuestion.questionSource==null?appLocaleName(userContext,"NotAssigned"):`${classQuestion.questionSource.displayName}(${classQuestion.questionSource.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"问题的来源","questionSource",ClassQuestionService.requestCandidateQuestionSource,
	      ClassQuestionService.transferToAnotherQuestionSource,"anotherQuestionSourceId",classQuestion.questionSource?classQuestion.questionSource.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="创建人名称">{classQuestion.creator==null?appLocaleName(userContext,"NotAssigned"):`${classQuestion.creator.displayName}(${classQuestion.creator.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"创建人名称","wechatUser",ClassQuestionService.requestCandidateCreator,
	      ClassQuestionService.transferToAnotherCreator,"anotherCreatorId",classQuestion.creator?classQuestion.creator.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="Cq">{classQuestion.cq==null?appLocaleName(userContext,"NotAssigned"):`${classQuestion.cq.displayName}(${classQuestion.cq.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"Cq","changeRequest",ClassQuestionService.requestCandidateCq,
	      ClassQuestionService.transferToAnotherCq,"anotherCqId",classQuestion.cq?classQuestion.cq.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
	
        {buildTransferModal(classQuestion,targetComponent)}
      </DescriptionList>
	)

}

const internalQuickFunctions = defaultQuickFunctions

class ClassQuestionDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'classQuestion'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName, dailySurveyQuestionListMetaInfo, dailySurveyQuestionCount } = this.props.classQuestion
    if(!this.props.classQuestion.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"类问题",cardsFor: "classQuestion",
    	cardsSource: this.props.classQuestion,returnURL,displayName,
  		subItems: [
{name: 'dailySurveyQuestionList', displayName: window.mtrans('daily_survey_question','class_question.daily_survey_question_list',false) ,viewGroup:'__no_group', type:'dailySurveyQuestion',count:dailySurveyQuestionCount,addFunction: true, role: 'dailySurveyQuestion', metaInfo: dailySurveyQuestionListMetaInfo, renderItem: GlobalComponents.DailySurveyQuestionBase.renderItemOfList},
    
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
  classQuestion: state._classQuestion,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(ClassQuestionDashboard))

