

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'

import DashboardTool from '../../common/Dashboard.tool'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './SchooleClass.profile.less'
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
const internalSummaryOf = (schooleClass,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{schooleClass.id}</Description> 
<Description term="名称">{schooleClass.name}</Description> 
<Description term="创建时间">{ moment(schooleClass.createTime).format('YYYY-MM-DD')}</Description> 
<Description term="Schoole">{schooleClass.schoole}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = schooleClass => {
  const {SchooleClassBase} = GlobalComponents
  return <PermissionSetting targetObject={schooleClass}  targetObjectMeta={SchooleClassBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class SchooleClassPermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  schooleClass = this.props.schooleClass
    const { id,displayName, classDailyHealthSurveyCount, studentCount, studentHealthSurveyCount } = schooleClass
    const  returnURL = `/schooleClass/${id}/dashboard`
    const cardsData = {cardsName:"Schoole类",cardsFor: "schooleClass",cardsSource: schooleClass,displayName,returnURL,
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
  schooleClass: state._schooleClass,
}))(Form.create()(SchooleClassPermission))

