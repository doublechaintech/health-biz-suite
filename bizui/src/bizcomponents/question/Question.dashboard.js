

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
import styles from './Question.dashboard.less'
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


const imageList =(question)=>{return [
	 ]}

const internalImageListOf = (question) =>defaultImageListOf(question,imageList)

const optionList =(question)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalRenderSubjectList = defaultRenderSubjectList
const internalSettingListOf = (question) =>defaultSettingListOf(question, optionList)
const internalLargeTextOf = (question) =>{

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
      <Link to={`/question/${targetComponent.props.question.id}/list/${item.name}/${item.displayName}/`}>
        <span>{item.displayName}</span>
        </Link>
        </Menu.Item>
  )

}
const renderSettingMenu = (cardsData,targetComponent) =>{

  const userContext = null
  return (<Menu>
    	<Menu.Item key="profile">
  			<Link to={`/question/${targetComponent.props.question.id}/permission`}><Icon type="safety-certificate" theme="twoTone" twoToneColor="#52c41a"/><span>{appLocaleName(userContext,"Permission")}</span></Link>
		</Menu.Item>
		<Menu.Divider />
		{cardsData.subSettingItems.map(item=>renderSettingMenuItem(item,cardsData,targetComponent))}
		</Menu>)

}

const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <Icon type="double-left" style={{marginRight:"10px"}} /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName} {renderSettingDropDown(cardsData,targetComponent)}</div>)

}


const internalSummaryOf = (question,targetComponent) =>{
	
	
	const {QuestionService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID" style={{wordBreak: 'break-all'}}>{question.id}</Description> 
<Description term="活动主题" style={{wordBreak: 'break-all'}}>{question.topic}</Description> 
<Description term="问题类型">{question.questionType==null?appLocaleName(userContext,"NotAssigned"):`${question.questionType.displayName}(${question.questionType.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"问题类型","questionType",QuestionService.requestCandidateQuestionType,
	      QuestionService.transferToAnotherQuestionType,"anotherQuestionTypeId",question.questionType?question.questionType.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="A选项" style={{wordBreak: 'break-all'}}>{question.optionA}</Description> 
<Description term="B选项" style={{wordBreak: 'break-all'}}>{question.optionB}</Description> 
<Description term="C选项" style={{wordBreak: 'break-all'}}>{question.optionC}</Description> 
<Description term="D选项" style={{wordBreak: 'break-all'}}>{question.optionD}</Description> 
<Description term="创建人名称">{question.creator==null?appLocaleName(userContext,"NotAssigned"):`${question.creator.displayName}(${question.creator.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"创建人名称","user",QuestionService.requestCandidateCreator,
	      QuestionService.transferToAnotherCreator,"anotherCreatorId",question.creator?question.creator.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="Cq">{question.cq==null?appLocaleName(userContext,"NotAssigned"):`${question.cq.displayName}(${question.cq.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"Cq","changeRequest",QuestionService.requestCandidateCq,
	      QuestionService.transferToAnotherCq,"anotherCqId",question.cq?question.cq.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
	
        {buildTransferModal(question,targetComponent)}
      </DescriptionList>
	)

}

const internalQuickFunctions = defaultQuickFunctions

class QuestionDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'question'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName, dailySurveyQuestionListMetaInfo, dailySurveyQuestionCount } = this.props.question
    if(!this.props.question.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"检查问题",cardsFor: "question",
    	cardsSource: this.props.question,returnURL,displayName,
  		subItems: [
{name: 'dailySurveyQuestionList', displayName: window.mtrans('daily_survey_question','question.daily_survey_question_list',false) ,viewGroup:'__no_group', type:'dailySurveyQuestion',count:dailySurveyQuestionCount,addFunction: true, role: 'dailySurveyQuestion', metaInfo: dailySurveyQuestionListMetaInfo, renderItem: GlobalComponents.DailySurveyQuestionBase.renderItemOfList},
    
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
  question: state._question,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(QuestionDashboard))

