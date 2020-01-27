

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'

import DashboardTool from '../../common/Dashboard.tool'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './ClassQuestion.profile.less'
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
const internalSummaryOf = (classQuestion,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{classQuestion.id}</Description> 
<Description term="活动主题">{classQuestion.topic}</Description> 
<Description term="A选项">{classQuestion.optionA}</Description> 
<Description term="B选项">{classQuestion.optionB}</Description> 
<Description term="C选项">{classQuestion.optionC}</Description> 
<Description term="D选项">{classQuestion.optionD}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = classQuestion => {
  const {ClassQuestionBase} = GlobalComponents
  return <PermissionSetting targetObject={classQuestion}  targetObjectMeta={ClassQuestionBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class ClassQuestionPermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  classQuestion = this.props.classQuestion
    const { id,displayName, dailySurveyQuestionCount } = classQuestion
    const  returnURL = `/classQuestion/${id}/dashboard`
    const cardsData = {cardsName:"类问题",cardsFor: "classQuestion",cardsSource: classQuestion,displayName,returnURL,
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
  classQuestion: state._classQuestion,
}))(Form.create()(ClassQuestionPermission))

