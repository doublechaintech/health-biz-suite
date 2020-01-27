

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
import styles from './DailySurveyQuestion.dashboard.less'
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


const imageList =(dailySurveyQuestion)=>{return [
	 ]}

const internalImageListOf = (dailySurveyQuestion) =>defaultImageListOf(dailySurveyQuestion,imageList)

const optionList =(dailySurveyQuestion)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalRenderSubjectList = defaultRenderSubjectList
const internalSettingListOf = (dailySurveyQuestion) =>defaultSettingListOf(dailySurveyQuestion, optionList)
const internalLargeTextOf = (dailySurveyQuestion) =>{

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
      <Link to={`/dailySurveyQuestion/${targetComponent.props.dailySurveyQuestion.id}/list/${item.name}/${item.displayName}/`}>
        <span>{item.displayName}</span>
        </Link>
        </Menu.Item>
  )

}
const renderSettingMenu = (cardsData,targetComponent) =>{

  const userContext = null
  return (<Menu>
    	<Menu.Item key="profile">
  			<Link to={`/dailySurveyQuestion/${targetComponent.props.dailySurveyQuestion.id}/permission`}><Icon type="safety-certificate" theme="twoTone" twoToneColor="#52c41a"/><span>{appLocaleName(userContext,"Permission")}</span></Link>
		</Menu.Item>
		<Menu.Divider />
		{cardsData.subSettingItems.map(item=>renderSettingMenuItem(item,cardsData,targetComponent))}
		</Menu>)

}

const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <Icon type="double-left" style={{marginRight:"10px"}} /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName} {renderSettingDropDown(cardsData,targetComponent)}</div>)

}


const internalSummaryOf = (dailySurveyQuestion,targetComponent) =>{
	
	
	const {DailySurveyQuestionService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID" style={{wordBreak: 'break-all'}}>{dailySurveyQuestion.id}</Description> 
<Description term="活动主题" style={{wordBreak: 'break-all'}}>{dailySurveyQuestion.topic}</Description> 
<Description term="问题类型">{dailySurveyQuestion.questionType==null?appLocaleName(userContext,"NotAssigned"):`${dailySurveyQuestion.questionType.displayName}(${dailySurveyQuestion.questionType.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"问题类型","questionType",DailySurveyQuestionService.requestCandidateQuestionType,
	      DailySurveyQuestionService.transferToAnotherQuestionType,"anotherQuestionTypeId",dailySurveyQuestion.questionType?dailySurveyQuestion.questionType.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="A选项" style={{wordBreak: 'break-all'}}>{dailySurveyQuestion.optionA}</Description> 
<Description term="B选项" style={{wordBreak: 'break-all'}}>{dailySurveyQuestion.optionB}</Description> 
<Description term="C选项" style={{wordBreak: 'break-all'}}>{dailySurveyQuestion.optionC}</Description> 
<Description term="D选项" style={{wordBreak: 'break-all'}}>{dailySurveyQuestion.optionD}</Description> 
<Description term="每日健康调查">{dailySurveyQuestion.classDailyHealthSurvey==null?appLocaleName(userContext,"NotAssigned"):`${dailySurveyQuestion.classDailyHealthSurvey.displayName}(${dailySurveyQuestion.classDailyHealthSurvey.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"每日健康调查","classDailyHealthSurvey",DailySurveyQuestionService.requestCandidateClassDailyHealthSurvey,
	      DailySurveyQuestionService.transferToAnotherClassDailyHealthSurvey,"anotherClassDailyHealthSurveyId",dailySurveyQuestion.classDailyHealthSurvey?dailySurveyQuestion.classDailyHealthSurvey.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="类问题">{dailySurveyQuestion.classQuestion==null?appLocaleName(userContext,"NotAssigned"):`${dailySurveyQuestion.classQuestion.displayName}(${dailySurveyQuestion.classQuestion.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"类问题","classQuestion",DailySurveyQuestionService.requestCandidateClassQuestion,
	      DailySurveyQuestionService.transferToAnotherClassQuestion,"anotherClassQuestionId",dailySurveyQuestion.classQuestion?dailySurveyQuestion.classQuestion.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
	
        {buildTransferModal(dailySurveyQuestion,targetComponent)}
      </DescriptionList>
	)

}

const internalQuickFunctions = defaultQuickFunctions

class DailySurveyQuestionDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'dailySurveyQuestion'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName, studentDailyAnswerListMetaInfo, studentDailyAnswerCount } = this.props.dailySurveyQuestion
    if(!this.props.dailySurveyQuestion.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"每日调查问题",cardsFor: "dailySurveyQuestion",
    	cardsSource: this.props.dailySurveyQuestion,returnURL,displayName,
  		subItems: [
{name: 'studentDailyAnswerList', displayName: window.mtrans('student_daily_answer','daily_survey_question.student_daily_answer_list',false) ,viewGroup:'__no_group', type:'studentDailyAnswer',count:studentDailyAnswerCount,addFunction: true, role: 'studentDailyAnswer', metaInfo: studentDailyAnswerListMetaInfo, renderItem: GlobalComponents.StudentDailyAnswerBase.renderItemOfList},
    
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
  dailySurveyQuestion: state._dailySurveyQuestion,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(DailySurveyQuestionDashboard))

