

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'

import DashboardTool from '../../common/Dashboard.tool'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './DailyHealthSurvey.profile.less'
import DescriptionList from '../../components/DescriptionList';

import GlobalComponents from '../../custcomponents';
import PermissionSetting from '../../permission/PermissionSetting'
import appLocaleName from '../../common/Locale.tool'
const { Description } = DescriptionList;
const {defaultRenderExtraHeader}= DashboardTool


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

const internalRenderTitle = (cardsData,targetComponent) =>{
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <Icon type="double-left" style={{marginRight:"10px"}} /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName}</div>)

}
const internalSummaryOf = (dailyHealthSurvey,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{dailyHealthSurvey.id}</Description> 
<Description term="名称">{dailyHealthSurvey.name}</Description> 
<Description term="调查的时间">{ moment(dailyHealthSurvey.surveyTime).format('YYYY-MM-DD')}</Description> 
<Description term="创建时间">{ moment(dailyHealthSurvey.createTime).format('YYYY-MM-DD')}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = dailyHealthSurvey => {
  const {DailyHealthSurveyBase} = GlobalComponents
  return <PermissionSetting targetObject={dailyHealthSurvey}  targetObjectMeta={DailyHealthSurveyBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class DailyHealthSurveyPermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  dailyHealthSurvey = this.props.dailyHealthSurvey
    const { id,displayName, surveyQuestionCount, userDailyAnswerCount } = dailyHealthSurvey
    const  returnURL = `/dailyHealthSurvey/${id}/dashboard`
    const cardsData = {cardsName:"每日健康调查",cardsFor: "dailyHealthSurvey",cardsSource: dailyHealthSurvey,displayName,returnURL,
  		subItems: [
    
      	],
  	};
    const renderExtraHeader = this.props.renderExtraHeader || internalRenderExtraHeader
    const summaryOf = this.props.summaryOf || internalSummaryOf
   
    return (

      <PageHeaderLayout
        title={internalRenderTitle(cardsData,this)}
        content={summaryOf(cardsData.cardsSource,this)}
        wrapperClassName={styles.advancedForm}
      >
      {renderExtraHeader(cardsData.cardsSource)}
      {renderPermissionSetting(cardsData.cardsSource)}
      
      </PageHeaderLayout>
    )
  }
}

export default connect(state => ({
  dailyHealthSurvey: state._dailyHealthSurvey,
}))(Form.create()(DailyHealthSurveyPermission))

