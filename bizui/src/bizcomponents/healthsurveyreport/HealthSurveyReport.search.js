
import React, { PureComponent } from 'react'
import { connect } from 'dva'
import Result from '../../components/Result'
import { Row, Col, Card, Form, Input, Select, Icon, Button, Dropdown, Menu, InputNumber, DatePicker, Modal, message,Alert } from 'antd';
import GlobalComponents from '../../custcomponents'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './HealthSurveyReport.search.less'
import ListViewTool from '../../common/ListView.tool'
import HealthSurveyReportBase from './HealthSurveyReport.base'
import PermissionSettingService from '../../permission/PermissionSetting.service'
import appLocaleName from '../../common/Locale.tool'
const {fieldLabels} = HealthSurveyReportBase
import { Link, Route, Redirect} from 'dva/router'

const  {  hasCreatePermission,hasExecutionPermission,hasDeletePermission,hasUpdatePermission,hasReadPermission } = PermissionSettingService


const {handleSelectRows,handleStandardTableChange,
  showDeletionDialog,handleUpdate,handleDeletionModalVisible,
  handleElementCreate,toggleAssociateModalVisible,handleCloseAlert}=ListViewTool


const buttonMenuFor =(targetComponent, internalName, localeName)=> {
  const userContext = null
  return (
   <Menu >
     <Menu.Item key="1" onClick={()=>toggleAssociateModalVisible(targetComponent,internalName)}>{appLocaleName(userContext,"New")}{localeName}</Menu.Item>
     <Menu.Item key="2">{appLocaleName(userContext,"Merge")}{localeName}</Menu.Item>
    </Menu>
  )

}


 
const showListActionBar = (targetComponent)=>{

  const {selectedRows} = targetComponent.state
  const {metaInfo} = targetComponent.props
  const disable = (selectedRows.length === 0)
  const userContext = null
  return (<div className={styles.tableListOperator}>
  

    {hasCreatePermission(metaInfo)&&<Button icon="plus" type="primary" onClick={() => handleElementCreate(targetComponent)}>{appLocaleName(userContext,"New")}</Button>}


    {hasUpdatePermission(metaInfo)&&<Button onClick={()=>handleUpdate(targetComponent)} icon="edit" disabled={disable}>{appLocaleName(userContext,"BatchUpdate")}</Button>}
 
 
    {hasDeletePermission(metaInfo)&&<Button onClick={(event)=>handleDeletionModalVisible(event,targetComponent)} type="danger" icon="delete" disabled={disable}>{appLocaleName(userContext,"BatchDelete")}</Button>}

</div> )


}


const showAssociateDialog = (targetComponent) => {
  const {data, owner, visible,onCancel,onCreate} = targetComponent.props
  const {currentAssociateModal} = targetComponent.state
  
  const {selectedRows} = targetComponent.state
  
  const { StudentAssociateForm } = GlobalComponents
  const { TeacherAssociateForm } = GlobalComponents
  const { ClassDailyHealthSurveyAssociateForm } = GlobalComponents


  return (
  <div>
  
   
  
    <StudentAssociateForm 
	visible={currentAssociateModal==='student'} 
	data={{healthSurveyReportList:selectedRows}} owner={owner}  
	onCancel={()=>toggleAssociateModalVisible(targetComponent,'student')} 
	onCreate={()=>toggleAssociateModalVisible(targetComponent,'student')}/> <TeacherAssociateForm 
	visible={currentAssociateModal==='teacher'} 
	data={{healthSurveyReportList:selectedRows}} owner={owner}  
	onCancel={()=>toggleAssociateModalVisible(targetComponent,'teacher')} 
	onCreate={()=>toggleAssociateModalVisible(targetComponent,'teacher')}/> <ClassDailyHealthSurveyAssociateForm 
	visible={currentAssociateModal==='survey'} 
	data={{healthSurveyReportList:selectedRows}} owner={owner}  
	onCancel={()=>toggleAssociateModalVisible(targetComponent,'survey')} 
	onCreate={()=>toggleAssociateModalVisible(targetComponent,'survey')}/> 
 


    </div>
    
    
    
    )
}


class HealthSurveyReportSearch extends PureComponent {
  state = {
    deletionModalVisible: false,
    selectedRows: [],
    showDeleteResult: false,
    currentAssociateModal: null,
  }

  render(){
    const { data, loading, count, currentPage, owner,partialList } = this.props;
    const {displayName} = owner.ref
    const { showDeleteResult, selectedRows, deletionModalVisible, showAssociatePaymentForm } = this.state;
    const {HealthSurveyReportTable} = GlobalComponents;
    const {HealthSurveyReportSearchForm} = GlobalComponents;
    const {HealthSurveyReportModalTable} = GlobalComponents;
    
    const userContext = null
    
    const renderTitle=()=>{
      const {returnURL} = this.props
      
      const linkComp=returnURL?<Link to={returnURL}> <Icon type="double-left" style={{marginRight:"10px"}} /> </Link>:null
      return (<div>{linkComp}{`${displayName}:${this.props.name}${appLocaleName(userContext,"List")}`}</div>);
    }
  
    return (
      <PageHeaderLayout title={renderTitle()}>
        <Card bordered={false}>
          <div className={styles.tableList}>
            <div className={styles.tableListForm}>
              <HealthSurveyReportSearchForm {...this.props} />
            </div>
            <div className={styles.tableListOperator}>
           
   
              {showListActionBar(this)}
              {partialList&&(
              <div className={styles.searchAlert}>
                	<Alert message={appLocaleName(userContext,"CloseToShowAll")} type="success" closable  afterClose={()=>handleCloseAlert(displayName, this)}/>
              </div>  	
              )}
              
            </div>
            <HealthSurveyReportTable
              selectedRows={selectedRows}
              loading={loading}
              data={data}
              count={count}
              current={currentPage}
              onSelectRow={(selectedRows)=>handleSelectRows(selectedRows,this)}
              onChange={(pagination, filtersArg, sorter)=>handleStandardTableChange(pagination, filtersArg, sorter,this)}
              owner={owner}
              {...this.props}
            />
          </div>
        </Card>
        {showDeletionDialog(this,HealthSurveyReportModalTable,"healthSurveyReportIds")}
        {showAssociateDialog(this)}
      </PageHeaderLayout>
    )
  }
}

export default Form.create()(HealthSurveyReportSearch)


