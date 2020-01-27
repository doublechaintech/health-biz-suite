

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
import styles from './StudentHealthSurvey.dashboard.less'
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


const imageList =(studentHealthSurvey)=>{return [
	 ]}

const internalImageListOf = (studentHealthSurvey) =>defaultImageListOf(studentHealthSurvey,imageList)

const optionList =(studentHealthSurvey)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalRenderSubjectList = defaultRenderSubjectList
const internalSettingListOf = (studentHealthSurvey) =>defaultSettingListOf(studentHealthSurvey, optionList)
const internalLargeTextOf = (studentHealthSurvey) =>{

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
      <Link to={`/studentHealthSurvey/${targetComponent.props.studentHealthSurvey.id}/list/${item.name}/${item.displayName}/`}>
        <span>{item.displayName}</span>
        </Link>
        </Menu.Item>
  )

}
const renderSettingMenu = (cardsData,targetComponent) =>{

  const userContext = null
  return (<Menu>
    	<Menu.Item key="profile">
  			<Link to={`/studentHealthSurvey/${targetComponent.props.studentHealthSurvey.id}/permission`}><Icon type="safety-certificate" theme="twoTone" twoToneColor="#52c41a"/><span>{appLocaleName(userContext,"Permission")}</span></Link>
		</Menu.Item>
		<Menu.Divider />
		{cardsData.subSettingItems.map(item=>renderSettingMenuItem(item,cardsData,targetComponent))}
		</Menu>)

}

const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <Icon type="double-left" style={{marginRight:"10px"}} /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName} {renderSettingDropDown(cardsData,targetComponent)}</div>)

}


const internalSummaryOf = (studentHealthSurvey,targetComponent) =>{
	
	
	const {StudentHealthSurveyService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID" style={{wordBreak: 'break-all'}}>{studentHealthSurvey.id}</Description> 
<Description term="学生">{studentHealthSurvey.student==null?appLocaleName(userContext,"NotAssigned"):`${studentHealthSurvey.student.displayName}(${studentHealthSurvey.student.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"学生","student",StudentHealthSurveyService.requestCandidateStudent,
	      StudentHealthSurveyService.transferToAnotherStudent,"anotherStudentId",studentHealthSurvey.student?studentHealthSurvey.student.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="回答时间">{ moment(studentHealthSurvey.answerTime).format('YYYY-MM-DD HH:mm')}</Description> 
<Description term="调查现状">{studentHealthSurvey.surveyStatus==null?appLocaleName(userContext,"NotAssigned"):`${studentHealthSurvey.surveyStatus.displayName}(${studentHealthSurvey.surveyStatus.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"调查现状","surveyStatus",StudentHealthSurveyService.requestCandidateSurveyStatus,
	      StudentHealthSurveyService.transferToAnotherSurveyStatus,"anotherSurveyStatusId",studentHealthSurvey.surveyStatus?studentHealthSurvey.surveyStatus.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="老师">{studentHealthSurvey.teacher==null?appLocaleName(userContext,"NotAssigned"):`${studentHealthSurvey.teacher.displayName}(${studentHealthSurvey.teacher.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"老师","teacher",StudentHealthSurveyService.requestCandidateTeacher,
	      StudentHealthSurveyService.transferToAnotherTeacher,"anotherTeacherId",studentHealthSurvey.teacher?studentHealthSurvey.teacher.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="每日健康调查">{studentHealthSurvey.classDailyHealthSurvey==null?appLocaleName(userContext,"NotAssigned"):`${studentHealthSurvey.classDailyHealthSurvey.displayName}(${studentHealthSurvey.classDailyHealthSurvey.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"每日健康调查","classDailyHealthSurvey",StudentHealthSurveyService.requestCandidateClassDailyHealthSurvey,
	      StudentHealthSurveyService.transferToAnotherClassDailyHealthSurvey,"anotherClassDailyHealthSurveyId",studentHealthSurvey.classDailyHealthSurvey?studentHealthSurvey.classDailyHealthSurvey.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="创建时间">{ moment(studentHealthSurvey.createTime).format('YYYY-MM-DD HH:mm')}</Description> 
<Description term="最后更新时间">{ moment(studentHealthSurvey.lastUpdateTime).format('YYYY-MM-DD HH:mm')}</Description> 
<Description term="Cq">{studentHealthSurvey.cq==null?appLocaleName(userContext,"NotAssigned"):`${studentHealthSurvey.cq.displayName}(${studentHealthSurvey.cq.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"Cq","changeRequest",StudentHealthSurveyService.requestCandidateCq,
	      StudentHealthSurveyService.transferToAnotherCq,"anotherCqId",studentHealthSurvey.cq?studentHealthSurvey.cq.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
	
        {buildTransferModal(studentHealthSurvey,targetComponent)}
      </DescriptionList>
	)

}

const internalQuickFunctions = defaultQuickFunctions

class StudentHealthSurveyDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'studentHealthSurvey'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName, studentDailyAnswerListMetaInfo, studentDailyAnswerCount } = this.props.studentHealthSurvey
    if(!this.props.studentHealthSurvey.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"学生健康调查",cardsFor: "studentHealthSurvey",
    	cardsSource: this.props.studentHealthSurvey,returnURL,displayName,
  		subItems: [
{name: 'studentDailyAnswerList', displayName: window.mtrans('student_daily_answer','student_health_survey.student_daily_answer_list',false) ,viewGroup:'__no_group', type:'studentDailyAnswer',count:studentDailyAnswerCount,addFunction: true, role: 'studentDailyAnswer', metaInfo: studentDailyAnswerListMetaInfo, renderItem: GlobalComponents.StudentDailyAnswerBase.renderItemOfList},
    
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
  studentHealthSurvey: state._studentHealthSurvey,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(StudentHealthSurveyDashboard))

