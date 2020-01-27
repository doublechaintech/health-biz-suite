

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import GlobalComponents from '../../custcomponents';
import {Form } from 'antd'
import { Link } from 'dva/router'

import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './Platform.profile.less'
import DescriptionList from '../../components/DescriptionList';

import DashboardTool from '../../common/Dashboard.tool'
import appLocaleName from '../../common/Locale.tool'

const {
  defaultRenderExtraHeader,
  defaultSubListsOf, defaultRenderSettingList,

}= DashboardTool

const { Description } = DescriptionList;

const internalRenderExtraHeader = defaultRenderExtraHeader

const internalSubListsOf = defaultSubListsOf

const internalRenderSettingList = defaultRenderSettingList

const internalRenderTitle = (cardsData,targetComponent) =>{
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <Icon type="double-left" style={{marginRight:"10px"}} /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName}</div>)

}

const internalSummaryOf = (item, targetComponents)=>{
	
	return GlobalComponents.PlatformBase.renderItemOfList(item, targetComponents)

}



class PlatformProfile extends Component {

  
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  platform = this.props.platform;
    const { id,displayName, provinceCount, cityCount, districtCount, schoolClassCount, teacherCount, guardianCount, questionCount, questionTypeCount, questionSourceCount, surveyStatusCount, wechatUserCount, userTypeCount, changeRequestCount, changeRequestTypeCount } = platform
    const  returnURL = `/platform/${id}/dashboard`
    const cardsData = {cardsName:"平台",cardsFor: "platform",cardsSource: platform,displayName,returnURL,
  		subItems: [
{name: 'provinceList', displayName:'省',type:'province',count:provinceCount,addFunction: true, role: 'province',  renderItem: GlobalComponents.ProvinceBase.renderItemOfList},
{name: 'cityList', displayName:'城市',type:'city',count:cityCount,addFunction: true, role: 'city',  renderItem: GlobalComponents.CityBase.renderItemOfList},
{name: 'districtList', displayName:'区/县',type:'district',count:districtCount,addFunction: true, role: 'district',  renderItem: GlobalComponents.DistrictBase.renderItemOfList},
{name: 'questionTypeList', displayName:'问题类型',type:'questionType',count:questionTypeCount,addFunction: false, role: 'questionType',  renderItem: GlobalComponents.QuestionTypeBase.renderItemOfList},
{name: 'questionSourceList', displayName:'问题的来源',type:'questionSource',count:questionSourceCount,addFunction: false, role: 'questionSource',  renderItem: GlobalComponents.QuestionSourceBase.renderItemOfList},
{name: 'surveyStatusList', displayName:'调查现状',type:'surveyStatus',count:surveyStatusCount,addFunction: false, role: 'surveyStatus',  renderItem: GlobalComponents.SurveyStatusBase.renderItemOfList},
{name: 'userTypeList', displayName:'用户类型',type:'userType',count:userTypeCount,addFunction: false, role: 'userType',  renderItem: GlobalComponents.UserTypeBase.renderItemOfList},
{name: 'changeRequestTypeList', displayName:'变更请求类型',type:'changeRequestType',count:changeRequestTypeCount,addFunction: false, role: 'changeRequestType',  renderItem: GlobalComponents.ChangeRequestTypeBase.renderItemOfList},
     
      	],
  	};
    
    const renderExtraHeader = this.props.renderExtraHeader || internalRenderExtraHeader
   
    const summaryOf = this.props.summaryOf || internalSummaryOf
    const renderSettingList = this.props.renderSettingList || internalRenderSettingList
    
    return (

      <PageHeaderLayout
        title={internalRenderTitle(cardsData, this)}
        content={summaryOf(cardsData.cardsSource, this)}
        wrapperClassName={styles.advancedForm}
      >
      {renderExtraHeader(cardsData.cardsSource)}
       {renderSettingList(cardsData)} 
      </PageHeaderLayout>
    )
  }
}

export default connect(state => ({
  platform: state._platform,
}))(Form.create()(PlatformProfile))

