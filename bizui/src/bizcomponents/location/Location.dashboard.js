

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
import styles from './Location.dashboard.less'
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


const imageList =(location)=>{return [
	 ]}

const internalImageListOf = (location) =>defaultImageListOf(location,imageList)

const optionList =(location)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalRenderSubjectList = defaultRenderSubjectList
const internalSettingListOf = (location) =>defaultSettingListOf(location, optionList)
const internalLargeTextOf = (location) =>{

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
      <Link to={`/location/${targetComponent.props.location.id}/list/${item.name}/${item.displayName}/`}>
        <span>{item.displayName}</span>
        </Link>
        </Menu.Item>
  )

}
const renderSettingMenu = (cardsData,targetComponent) =>{

  const userContext = null
  return (<Menu>
    	<Menu.Item key="profile">
  			<Link to={`/location/${targetComponent.props.location.id}/permission`}><Icon type="safety-certificate" theme="twoTone" twoToneColor="#52c41a"/><span>{appLocaleName(userContext,"Permission")}</span></Link>
		</Menu.Item>
		<Menu.Divider />
		{cardsData.subSettingItems.map(item=>renderSettingMenuItem(item,cardsData,targetComponent))}
		</Menu>)

}

const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <Icon type="double-left" style={{marginRight:"10px"}} /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName} {renderSettingDropDown(cardsData,targetComponent)}</div>)

}


const internalSummaryOf = (location,targetComponent) =>{
	
	
	const {LocationService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID" style={{wordBreak: 'break-all'}}>{location.id}</Description> 
<Description term="名称" style={{wordBreak: 'break-all'}}>{location.name}</Description> 
<Description term="地址" style={{wordBreak: 'break-all'}}>{location.address}</Description> 
<Description term="区/县">{location.district==null?appLocaleName(userContext,"NotAssigned"):`${location.district.displayName}(${location.district.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"区/县","district",LocationService.requestCandidateDistrict,
	      LocationService.transferToAnotherDistrict,"anotherDistrictId",location.district?location.district.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="省">{location.province==null?appLocaleName(userContext,"NotAssigned"):`${location.province.displayName}(${location.province.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"省","province",LocationService.requestCandidateProvince,
	      LocationService.transferToAnotherProvince,"anotherProvinceId",location.province?location.province.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="纬度" style={{wordBreak: 'break-all'}}>{location.latitude}</Description> 
<Description term="经度" style={{wordBreak: 'break-all'}}>{location.longitude}</Description> 
	
        {buildTransferModal(location,targetComponent)}
      </DescriptionList>
	)

}

const internalQuickFunctions = defaultQuickFunctions

class LocationDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'location'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName, studentListMetaInfo, studentCount } = this.props.location
    if(!this.props.location.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"位置",cardsFor: "location",
    	cardsSource: this.props.location,returnURL,displayName,
  		subItems: [
{name: 'studentList', displayName: window.mtrans('student','location.student_list',false) ,viewGroup:'__no_group', type:'student',count:studentCount,addFunction: true, role: 'student', metaInfo: studentListMetaInfo, renderItem: GlobalComponents.StudentBase.renderItemOfList},
    
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
  location: state._location,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(LocationDashboard))

