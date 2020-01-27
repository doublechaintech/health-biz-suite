

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'

import DashboardTool from '../../common/Dashboard.tool'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './DailySurveyQuestion.profile.less'
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
const internalSummaryOf = (dailySurveyQuestion,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{dailySurveyQuestion.id}</Description> 
<Description term="活动主题">{dailySurveyQuestion.topic}</Description> 
<Description term="A选项">{dailySurveyQuestion.optionA}</Description> 
<Description term="B选项">{dailySurveyQuestion.optionB}</Description> 
<Description term="C选项">{dailySurveyQuestion.optionC}</Description> 
<Description term="D选项">{dailySurveyQuestion.optionD}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = dailySurveyQuestion => {
  const {DailySurveyQuestionBase} = GlobalComponents
  return <PermissionSetting targetObject={dailySurveyQuestion}  targetObjectMeta={DailySurveyQuestionBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class DailySurveyQuestionPermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  dailySurveyQuestion = this.props.dailySurveyQuestion
    const { id,displayName, studentDailyAnswerCount } = dailySurveyQuestion
    const  returnURL = `/dailySurveyQuestion/${id}/dashboard`
    const cardsData = {cardsName:"每日调查问题",cardsFor: "dailySurveyQuestion",cardsSource: dailySurveyQuestion,displayName,returnURL,
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
  dailySurveyQuestion: state._dailySurveyQuestion,
}))(Form.create()(DailySurveyQuestionPermission))

