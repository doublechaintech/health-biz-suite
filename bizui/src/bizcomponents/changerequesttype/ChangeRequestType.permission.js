

import React, { Component } from 'react'
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'

import DashboardTool from '../../common/Dashboard.tool'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './ChangeRequestType.profile.less'
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
const internalSummaryOf = (changeRequestType,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{changeRequestType.id}</Description> 
<Description term="名称">{changeRequestType.name}</Description> 
<Description term="编码">{changeRequestType.code}</Description> 
<Description term="图标">{changeRequestType.icon}</Description> 
<Description term="顺序">{changeRequestType.displayOrder}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = changeRequestType => {
  const {ChangeRequestTypeBase} = GlobalComponents
  return <PermissionSetting targetObject={changeRequestType}  targetObjectMeta={ChangeRequestTypeBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class ChangeRequestTypePermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  changeRequestType = this.props.changeRequestType
    const { id,displayName, changeRequestCount } = changeRequestType
    const  returnURL = `/changeRequestType/${id}/dashboard`
    const cardsData = {cardsName:"变更请求类型",cardsFor: "changeRequestType",cardsSource: changeRequestType,displayName,returnURL,
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
  changeRequestType: state._changeRequestType,
}))(Form.create()(ChangeRequestTypePermission))

