

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
import styles from './HealthSurveyReport.dashboard.less'
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


const imageList =(healthSurveyReport)=>{return [
	 ]}

const internalImageListOf = (healthSurveyReport) =>defaultImageListOf(healthSurveyReport,imageList)

const optionList =(healthSurveyReport)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalRenderSubjectList = defaultRenderSubjectList
const internalSettingListOf = (healthSurveyReport) =>defaultSettingListOf(healthSurveyReport, optionList)
const internalLargeTextOf = (healthSurveyReport) =>{

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
      <Link to={`/healthSurveyReport/${targetComponent.props.healthSurveyReport.id}/list/${item.name}/${item.displayName}/`}>
        <span>{item.displayName}</span>
        </Link>
        </Menu.Item>
  )

}
const renderSettingMenu = (cardsData,targetComponent) =>{

  const userContext = null
  return (<Menu>
    	<Menu.Item key="profile">
  			<Link to={`/healthSurveyReport/${targetComponent.props.healthSurveyReport.id}/permission`}><Icon type="safety-certificate" theme="twoTone" twoToneColor="#52c41a"/><span>{appLocaleName(userContext,"Permission")}</span></Link>
		</Menu.Item>
		<Menu.Divider />
		{cardsData.subSettingItems.map(item=>renderSettingMenuItem(item,cardsData,targetComponent))}
		</Menu>)

}

const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <Icon type="double-left" style={{marginRight:"10px"}} /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName} {renderSettingDropDown(cardsData,targetComponent)}</div>)

}


const internalSummaryOf = (healthSurveyReport,targetComponent) =>{
	
	
	const {HealthSurveyReportService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID" style={{wordBreak: 'break-all'}}>{healthSurveyReport.id}</Description> 
<Description term="调查的名字" style={{wordBreak: 'break-all'}}>{healthSurveyReport.surveyName}</Description> 
<Description term="调查的时间">{ moment(healthSurveyReport.surveyTime).format('YYYY-MM-DD HH:mm')}</Description> 
<Description term="老师的名字" style={{wordBreak: 'break-all'}}>{healthSurveyReport.teacherName}</Description> 
<Description term="学校" style={{wordBreak: 'break-all'}}>{healthSurveyReport.school}</Description> 
<Description term="学校类" style={{wordBreak: 'break-all'}}>{healthSurveyReport.schoolClass}</Description> 
<Description term="学生的名字" style={{wordBreak: 'break-all'}}>{healthSurveyReport.studentName}</Description> 
<Description term="学生数量" style={{wordBreak: 'break-all'}}>{healthSurveyReport.studentNumber}</Description> 
<Description term="监护人姓名" style={{wordBreak: 'break-all'}}>{healthSurveyReport.guardianName}</Description> 
<Description term="监护人手机" style={{wordBreak: 'break-all'}}>{healthSurveyReport.guardianMobile}</Description> 
<Description term="学生">{healthSurveyReport.student==null?appLocaleName(userContext,"NotAssigned"):`${healthSurveyReport.student.displayName}(${healthSurveyReport.student.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"学生","student",HealthSurveyReportService.requestCandidateStudent,
	      HealthSurveyReportService.transferToAnotherStudent,"anotherStudentId",healthSurveyReport.student?healthSurveyReport.student.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="老师">{healthSurveyReport.teacher==null?appLocaleName(userContext,"NotAssigned"):`${healthSurveyReport.teacher.displayName}(${healthSurveyReport.teacher.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"老师","teacher",HealthSurveyReportService.requestCandidateTeacher,
	      HealthSurveyReportService.transferToAnotherTeacher,"anotherTeacherId",healthSurveyReport.teacher?healthSurveyReport.teacher.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="调查">{healthSurveyReport.survey==null?appLocaleName(userContext,"NotAssigned"):`${healthSurveyReport.survey.displayName}(${healthSurveyReport.survey.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"调查","classDailyHealthSurvey",HealthSurveyReportService.requestCandidateSurvey,
	      HealthSurveyReportService.transferToAnotherSurvey,"anotherSurveyId",healthSurveyReport.survey?healthSurveyReport.survey.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
	
        {buildTransferModal(healthSurveyReport,targetComponent)}
      </DescriptionList>
	)

}

const internalQuickFunctions = defaultQuickFunctions

class HealthSurveyReportDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'healthSurveyReport'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName, studentAnswerListMetaInfo, studentAnswerCount } = this.props.healthSurveyReport
    if(!this.props.healthSurveyReport.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"健康调查报告",cardsFor: "healthSurveyReport",
    	cardsSource: this.props.healthSurveyReport,returnURL,displayName,
  		subItems: [
{name: 'studentAnswerList', displayName: window.mtrans('student_answer','health_survey_report.student_answer_list',false) ,viewGroup:'__no_group', type:'studentAnswer',count:studentAnswerCount,addFunction: true, role: 'studentAnswer', metaInfo: studentAnswerListMetaInfo, renderItem: GlobalComponents.StudentAnswerBase.renderItemOfList},
    
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
  healthSurveyReport: state._healthSurveyReport,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(HealthSurveyReportDashboard))

