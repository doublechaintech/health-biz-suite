

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'

import DashboardTool from '../../common/Dashboard.tool'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './SchoolClass.profile.less'
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
const internalSummaryOf = (schoolClass,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{schoolClass.id}</Description> 
<Description term="名称">{schoolClass.name}</Description> 
<Description term="创建时间">{ moment(schoolClass.createTime).format('YYYY-MM-DD')}</Description> 
<Description term="Schoole">{schoolClass.schoole}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = schoolClass => {
  const {SchoolClassBase} = GlobalComponents
  return <PermissionSetting targetObject={schoolClass}  targetObjectMeta={SchoolClassBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class SchoolClassPermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  schoolClass = this.props.schoolClass
    const { id,displayName, classDailyHealthSurveyCount, studentCount, studentHealthSurveyCount } = schoolClass
    const  returnURL = `/schoolClass/${id}/dashboard`
    const cardsData = {cardsName:"学校类",cardsFor: "schoolClass",cardsSource: schoolClass,displayName,returnURL,
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
  schoolClass: state._schoolClass,
}))(Form.create()(SchoolClassPermission))

