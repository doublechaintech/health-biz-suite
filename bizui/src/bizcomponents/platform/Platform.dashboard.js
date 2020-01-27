

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
import styles from './Platform.dashboard.less'
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


const imageList =(platform)=>{return [
	 ]}

const internalImageListOf = (platform) =>defaultImageListOf(platform,imageList)

const optionList =(platform)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalRenderSubjectList = defaultRenderSubjectList
const internalSettingListOf = (platform) =>defaultSettingListOf(platform, optionList)
const internalLargeTextOf = (platform) =>{

	return(<div> 
   <Card title={`描述`} ><pre>{platform.description}</pre></Card>
</div>)

	

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
      <Link to={`/platform/${targetComponent.props.platform.id}/list/${item.name}/${item.displayName}/`}>
        <span>{item.displayName}</span>
        </Link>
        </Menu.Item>
  )

}
const renderSettingMenu = (cardsData,targetComponent) =>{

  const userContext = null
  return (<Menu>
    	<Menu.Item key="profile">
  			<Link to={`/platform/${targetComponent.props.platform.id}/permission`}><Icon type="safety-certificate" theme="twoTone" twoToneColor="#52c41a"/><span>{appLocaleName(userContext,"Permission")}</span></Link>
		</Menu.Item>
		<Menu.Divider />
		{cardsData.subSettingItems.map(item=>renderSettingMenuItem(item,cardsData,targetComponent))}
		</Menu>)

}

const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <Icon type="double-left" style={{marginRight:"10px"}} /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName} {renderSettingDropDown(cardsData,targetComponent)}</div>)

}


const internalSummaryOf = (platform,targetComponent) =>{
	
	
	const {PlatformService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID" style={{wordBreak: 'break-all'}}>{platform.id}</Description> 
<Description term="名称" style={{wordBreak: 'break-all'}}>{platform.name}</Description> 
	
        {buildTransferModal(platform,targetComponent)}
      </DescriptionList>
	)

}

const internalQuickFunctions = defaultQuickFunctions

class PlatformDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'platform'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName, provinceListMetaInfo, cityListMetaInfo, districtListMetaInfo, teacherListMetaInfo, studentListMetaInfo, questionListMetaInfo, questionTypeListMetaInfo, surveyStatusListMetaInfo, userListMetaInfo, changeRequestListMetaInfo, changeRequestTypeListMetaInfo, provinceCount, cityCount, districtCount, teacherCount, studentCount, questionCount, questionTypeCount, surveyStatusCount, userCount, changeRequestCount, changeRequestTypeCount } = this.props.platform
    if(!this.props.platform.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"平台",cardsFor: "platform",
    	cardsSource: this.props.platform,returnURL,displayName,
  		subItems: [
{name: 'teacherList', displayName: window.mtrans('teacher','platform.teacher_list',false) ,viewGroup:'__no_group', type:'teacher',count:teacherCount,addFunction: true, role: 'teacher', metaInfo: teacherListMetaInfo, renderItem: GlobalComponents.TeacherBase.renderItemOfList},
{name: 'studentList', displayName: window.mtrans('student','platform.student_list',false) ,viewGroup:'__no_group', type:'student',count:studentCount,addFunction: true, role: 'student', metaInfo: studentListMetaInfo, renderItem: GlobalComponents.StudentBase.renderItemOfList},
{name: 'questionList', displayName: window.mtrans('question','platform.question_list',false) ,viewGroup:'__no_group', type:'question',count:questionCount,addFunction: true, role: 'question', metaInfo: questionListMetaInfo, renderItem: GlobalComponents.QuestionBase.renderItemOfList},
{name: 'userList', displayName: window.mtrans('user','platform.user_list',false) ,viewGroup:'__no_group', type:'user',count:userCount,addFunction: true, role: 'user', metaInfo: userListMetaInfo, renderItem: GlobalComponents.UserBase.renderItemOfList},
{name: 'changeRequestList', displayName: window.mtrans('change_request','platform.change_request_list',false) ,viewGroup:'变更', type:'changeRequest',count:changeRequestCount,addFunction: true, role: 'changeRequest', metaInfo: changeRequestListMetaInfo, renderItem: GlobalComponents.ChangeRequestBase.renderItemOfList},
    
      	],
   		subSettingItems: [
{name: 'provinceList', displayName:'省',type:'province',count:provinceCount,addFunction: true, role: 'province', metaInfo: provinceListMetaInfo, renderItem: GlobalComponents.ProvinceBase.renderItemOfList},
{name: 'cityList', displayName:'城市',type:'city',count:cityCount,addFunction: true, role: 'city', metaInfo: cityListMetaInfo, renderItem: GlobalComponents.CityBase.renderItemOfList},
{name: 'districtList', displayName:'区/县',type:'district',count:districtCount,addFunction: true, role: 'district', metaInfo: districtListMetaInfo, renderItem: GlobalComponents.DistrictBase.renderItemOfList},
{name: 'questionTypeList', displayName:'问题类型',type:'questionType',count:questionTypeCount,addFunction: false, role: 'questionType', metaInfo: questionTypeListMetaInfo, renderItem: GlobalComponents.QuestionTypeBase.renderItemOfList},
{name: 'surveyStatusList', displayName:'调查现状',type:'surveyStatus',count:surveyStatusCount,addFunction: false, role: 'surveyStatus', metaInfo: surveyStatusListMetaInfo, renderItem: GlobalComponents.SurveyStatusBase.renderItemOfList},
{name: 'changeRequestTypeList', displayName:'变更请求类型',type:'changeRequestType',count:changeRequestTypeCount,addFunction: false, role: 'changeRequestType', metaInfo: changeRequestTypeListMetaInfo, renderItem: GlobalComponents.ChangeRequestTypeBase.renderItemOfList},
    
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
  platform: state._platform,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(PlatformDashboard))

