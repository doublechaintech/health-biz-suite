

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
import styles from './SchoolClass.dashboard.less'
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


const imageList =(schoolClass)=>{return [
	 ]}

const internalImageListOf = (schoolClass) =>defaultImageListOf(schoolClass,imageList)

const optionList =(schoolClass)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalRenderSubjectList = defaultRenderSubjectList
const internalSettingListOf = (schoolClass) =>defaultSettingListOf(schoolClass, optionList)
const internalLargeTextOf = (schoolClass) =>{

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
      <Link to={`/schoolClass/${targetComponent.props.schoolClass.id}/list/${item.name}/${item.displayName}/`}>
        <span>{item.displayName}</span>
        </Link>
        </Menu.Item>
  )

}
const renderSettingMenu = (cardsData,targetComponent) =>{

  const userContext = null
  return (<Menu>
    	<Menu.Item key="profile">
  			<Link to={`/schoolClass/${targetComponent.props.schoolClass.id}/permission`}><Icon type="safety-certificate" theme="twoTone" twoToneColor="#52c41a"/><span>{appLocaleName(userContext,"Permission")}</span></Link>
		</Menu.Item>
		<Menu.Divider />
		{cardsData.subSettingItems.map(item=>renderSettingMenuItem(item,cardsData,targetComponent))}
		</Menu>)

}

const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <Icon type="double-left" style={{marginRight:"10px"}} /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName} {renderSettingDropDown(cardsData,targetComponent)}</div>)

}


const internalSummaryOf = (schoolClass,targetComponent) =>{
	
	
	const {SchoolClassService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID" style={{wordBreak: 'break-all'}}>{schoolClass.id}</Description> 
<Description term="名称" style={{wordBreak: 'break-all'}}>{schoolClass.name}</Description> 
<Description term="班主任">{schoolClass.classTeacher==null?appLocaleName(userContext,"NotAssigned"):`${schoolClass.classTeacher.displayName}(${schoolClass.classTeacher.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"班主任","teacher",SchoolClassService.requestCandidateClassTeacher,
	      SchoolClassService.transferToAnotherClassTeacher,"anotherClassTeacherId",schoolClass.classTeacher?schoolClass.classTeacher.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="创建时间">{ moment(schoolClass.createTime).format('YYYY-MM-DD HH:mm')}</Description> 
<Description term="Schoole" style={{wordBreak: 'break-all'}}>{schoolClass.schoole}</Description> 
<Description term="Cq">{schoolClass.cq==null?appLocaleName(userContext,"NotAssigned"):`${schoolClass.cq.displayName}(${schoolClass.cq.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"Cq","changeRequest",SchoolClassService.requestCandidateCq,
	      SchoolClassService.transferToAnotherCq,"anotherCqId",schoolClass.cq?schoolClass.cq.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
	
        {buildTransferModal(schoolClass,targetComponent)}
      </DescriptionList>
	)

}

const internalQuickFunctions = defaultQuickFunctions

class SchoolClassDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'schoolClass'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName, classDailyHealthSurveyListMetaInfo, studentListMetaInfo, studentHealthSurveyListMetaInfo, classDailyHealthSurveyCount, studentCount, studentHealthSurveyCount } = this.props.schoolClass
    if(!this.props.schoolClass.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"学校类",cardsFor: "schoolClass",
    	cardsSource: this.props.schoolClass,returnURL,displayName,
  		subItems: [
{name: 'classDailyHealthSurveyList', displayName: window.mtrans('class_daily_health_survey','school_class.class_daily_health_survey_list',false) ,viewGroup:'__no_group', type:'classDailyHealthSurvey',count:classDailyHealthSurveyCount,addFunction: true, role: 'classDailyHealthSurvey', metaInfo: classDailyHealthSurveyListMetaInfo, renderItem: GlobalComponents.ClassDailyHealthSurveyBase.renderItemOfList},
{name: 'studentList', displayName: window.mtrans('student','school_class.student_list',false) ,viewGroup:'__no_group', type:'student',count:studentCount,addFunction: true, role: 'student', metaInfo: studentListMetaInfo, renderItem: GlobalComponents.StudentBase.renderItemOfList},
{name: 'studentHealthSurveyList', displayName: window.mtrans('student_health_survey','school_class.student_health_survey_list',false) ,viewGroup:'__no_group', type:'studentHealthSurvey',count:studentHealthSurveyCount,addFunction: true, role: 'studentHealthSurvey', metaInfo: studentHealthSurveyListMetaInfo, renderItem: GlobalComponents.StudentHealthSurveyBase.renderItemOfList},
    
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
  schoolClass: state._schoolClass,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(SchoolClassDashboard))

