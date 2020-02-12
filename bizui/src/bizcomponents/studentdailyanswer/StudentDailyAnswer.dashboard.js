

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
import styles from './StudentDailyAnswer.dashboard.less'
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


const imageList =(studentDailyAnswer)=>{return [
	 ]}

const internalImageListOf = (studentDailyAnswer) =>defaultImageListOf(studentDailyAnswer,imageList)

const optionList =(studentDailyAnswer)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalRenderSubjectList = defaultRenderSubjectList
const internalSettingListOf = (studentDailyAnswer) =>defaultSettingListOf(studentDailyAnswer, optionList)
const internalLargeTextOf = (studentDailyAnswer) =>{

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
      <Link to={`/studentDailyAnswer/${targetComponent.props.studentDailyAnswer.id}/list/${item.name}/${item.displayName}/`}>
        <span>{item.displayName}</span>
        </Link>
        </Menu.Item>
  )

}
const renderSettingMenu = (cardsData,targetComponent) =>{

  const userContext = null
  return (<Menu>
    	<Menu.Item key="profile">
  			<Link to={`/studentDailyAnswer/${targetComponent.props.studentDailyAnswer.id}/permission`}><Icon type="safety-certificate" theme="twoTone" twoToneColor="#52c41a"/><span>{appLocaleName(userContext,"Permission")}</span></Link>
		</Menu.Item>
		<Menu.Divider />
		{cardsData.subSettingItems.map(item=>renderSettingMenuItem(item,cardsData,targetComponent))}
		</Menu>)

}

const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <Icon type="double-left" style={{marginRight:"10px"}} /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName} {renderSettingDropDown(cardsData,targetComponent)}</div>)

}


const internalSummaryOf = (studentDailyAnswer,targetComponent) =>{
	
	
	const {StudentDailyAnswerService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID" style={{wordBreak: 'break-all'}}>{studentDailyAnswer.id}</Description> 
<Description term="学生健康调查">{studentDailyAnswer.studentHealthSurvey==null?appLocaleName(userContext,"NotAssigned"):`${studentDailyAnswer.studentHealthSurvey.displayName}(${studentDailyAnswer.studentHealthSurvey.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"学生健康调查","studentHealthSurvey",StudentDailyAnswerService.requestCandidateStudentHealthSurvey,
	      StudentDailyAnswerService.transferToAnotherStudentHealthSurvey,"anotherStudentHealthSurveyId",studentDailyAnswer.studentHealthSurvey?studentDailyAnswer.studentHealthSurvey.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="检查问题">{studentDailyAnswer.question==null?appLocaleName(userContext,"NotAssigned"):`${studentDailyAnswer.question.displayName}(${studentDailyAnswer.question.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"检查问题","dailySurveyQuestion",StudentDailyAnswerService.requestCandidateQuestion,
	      StudentDailyAnswerService.transferToAnotherQuestion,"anotherQuestionId",studentDailyAnswer.question?studentDailyAnswer.question.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="回答" style={{wordBreak: 'break-all'}}>{studentDailyAnswer.answer}</Description> 
<Description term="创建时间">{ moment(studentDailyAnswer.createTime).format('YYYY-MM-DD HH:mm')}</Description> 
<Description term="最后更新时间">{ moment(studentDailyAnswer.lastUpdateTime).format('YYYY-MM-DD HH:mm')}</Description> 
	
        {buildTransferModal(studentDailyAnswer,targetComponent)}
      </DescriptionList>
	)

}

const internalQuickFunctions = defaultQuickFunctions

class StudentDailyAnswerDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'studentDailyAnswer'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName, studentAnswerListMetaInfo, studentAnswerCount } = this.props.studentDailyAnswer
    if(!this.props.studentDailyAnswer.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"学生每天回答",cardsFor: "studentDailyAnswer",
    	cardsSource: this.props.studentDailyAnswer,returnURL,displayName,
  		subItems: [
{name: 'studentAnswerList', displayName: window.mtrans('student_answer','student_daily_answer.student_answer_list',false) ,viewGroup:'__no_group', type:'studentAnswer',count:studentAnswerCount,addFunction: true, role: 'studentAnswer', metaInfo: studentAnswerListMetaInfo, renderItem: GlobalComponents.StudentAnswerBase.renderItemOfList},
    
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
  studentDailyAnswer: state._studentDailyAnswer,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(StudentDailyAnswerDashboard))

