

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'

import DashboardTool from '../../common/Dashboard.tool'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './StudentHealthSurvey.profile.less'
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
const internalSummaryOf = (studentHealthSurvey,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{studentHealthSurvey.id}</Description> 
<Description term="回答时间">{ moment(studentHealthSurvey.answerTime).format('YYYY-MM-DD')}</Description> 
<Description term="创建时间">{ moment(studentHealthSurvey.createTime).format('YYYY-MM-DD')}</Description> 
<Description term="最后更新时间">{ moment(studentHealthSurvey.lastUpdateTime).format('YYYY-MM-DD')}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = studentHealthSurvey => {
  const {StudentHealthSurveyBase} = GlobalComponents
  return <PermissionSetting targetObject={studentHealthSurvey}  targetObjectMeta={StudentHealthSurveyBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class StudentHealthSurveyPermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  studentHealthSurvey = this.props.studentHealthSurvey
    const { id,displayName, studentDailyAnswerCount } = studentHealthSurvey
    const  returnURL = `/studentHealthSurvey/${id}/dashboard`
    const cardsData = {cardsName:"学生健康调查",cardsFor: "studentHealthSurvey",cardsSource: studentHealthSurvey,displayName,returnURL,
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
  studentHealthSurvey: state._studentHealthSurvey,
}))(Form.create()(StudentHealthSurveyPermission))

