

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
import styles from './Student.dashboard.less'
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


const imageList =(student)=>{return [
	   {"title":'学生阿凡达',"imageLocation":student.studentAvatar},
]}

const internalImageListOf = (student) =>defaultImageListOf(student,imageList)

const optionList =(student)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalRenderSubjectList = defaultRenderSubjectList
const internalSettingListOf = (student) =>defaultSettingListOf(student, optionList)
const internalLargeTextOf = (student) =>{

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
      <Link to={`/student/${targetComponent.props.student.id}/list/${item.name}/${item.displayName}/`}>
        <span>{item.displayName}</span>
        </Link>
        </Menu.Item>
  )

}
const renderSettingMenu = (cardsData,targetComponent) =>{

  const userContext = null
  return (<Menu>
    	<Menu.Item key="profile">
  			<Link to={`/student/${targetComponent.props.student.id}/permission`}><Icon type="safety-certificate" theme="twoTone" twoToneColor="#52c41a"/><span>{appLocaleName(userContext,"Permission")}</span></Link>
		</Menu.Item>
		<Menu.Divider />
		{cardsData.subSettingItems.map(item=>renderSettingMenuItem(item,cardsData,targetComponent))}
		</Menu>)

}

const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <Icon type="double-left" style={{marginRight:"10px"}} /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName} {renderSettingDropDown(cardsData,targetComponent)}</div>)

}


const internalSummaryOf = (student,targetComponent) =>{
	
	
	const {StudentService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID" style={{wordBreak: 'break-all'}}>{student.id}</Description> 
<Description term="学生的名字" style={{wordBreak: 'break-all'}}>{student.studentName}</Description> 
<Description term="学生数量" style={{wordBreak: 'break-all'}}>{student.studentNumber}</Description> 
<Description term="监护人姓名" style={{wordBreak: 'break-all'}}>{student.guardianName}</Description> 
<Description term="监护人手机" style={{wordBreak: 'break-all'}}>{student.guardianMobile}</Description> 
<Description term="地址">{student.address==null?appLocaleName(userContext,"NotAssigned"):`${student.address.displayName}(${student.address.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"地址","location",StudentService.requestCandidateAddress,
	      StudentService.transferToAnotherAddress,"anotherAddressId",student.address?student.address.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="用户">{student.user==null?appLocaleName(userContext,"NotAssigned"):`${student.user.displayName}(${student.user.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"用户","user",StudentService.requestCandidateUser,
	      StudentService.transferToAnotherUser,"anotherUserId",student.user?student.user.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="创建时间">{ moment(student.createTime).format('YYYY-MM-DD HH:mm')}</Description> 
	
        {buildTransferModal(student,targetComponent)}
      </DescriptionList>
	)

}

const internalQuickFunctions = defaultQuickFunctions

class StudentDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'student'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName, studentHealthSurveyListMetaInfo, healthSurveyReportListMetaInfo, studentHealthSurveyCount, healthSurveyReportCount } = this.props.student
    if(!this.props.student.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"学生",cardsFor: "student",
    	cardsSource: this.props.student,returnURL,displayName,
  		subItems: [
{name: 'studentHealthSurveyList', displayName: window.mtrans('student_health_survey','student.student_health_survey_list',false) ,viewGroup:'__no_group', type:'studentHealthSurvey',count:studentHealthSurveyCount,addFunction: true, role: 'studentHealthSurvey', metaInfo: studentHealthSurveyListMetaInfo, renderItem: GlobalComponents.StudentHealthSurveyBase.renderItemOfList},
{name: 'healthSurveyReportList', displayName: window.mtrans('health_survey_report','student.health_survey_report_list',false) ,viewGroup:'__no_group', type:'healthSurveyReport',count:healthSurveyReportCount,addFunction: true, role: 'healthSurveyReport', metaInfo: healthSurveyReportListMetaInfo, renderItem: GlobalComponents.HealthSurveyReportBase.renderItemOfList},
    
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
  student: state._student,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(StudentDashboard))

