import React from 'react'
import PropTypes from 'prop-types'
import {
  Layout,
  Menu,
  Icon,
  Avatar,
  Dropdown,
  Tag,
  message,
  Spin,
  Breadcrumb,
  AutoComplete,Row, Col,
  Input,Button
} from 'antd'
import TopMenu from '../../launcher/TopMenu'
import DocumentTitle from 'react-document-title'
import { connect } from 'dva'
import { Link, Route, Redirect, Switch } from 'dva/router'
import moment from 'moment'
import groupBy from 'lodash/groupBy'
import { ContainerQuery } from 'react-container-query'
import classNames from 'classnames'
import styles from './ChangeRequest.app.less'
import {sessionObject} from '../../utils/utils'

import HeaderSearch from '../../components/HeaderSearch';
import NoticeIcon from '../../components/NoticeIcon';
import GlobalFooter from '../../components/GlobalFooter';


import GlobalComponents from '../../custcomponents';

import PermissionSettingService from '../../permission/PermissionSetting.service'
import appLocaleName from '../../common/Locale.tool'
import BizAppTool from '../../common/BizApp.tool'

const { Header, Sider, Content } = Layout
const { SubMenu } = Menu
const {
  defaultFilteredNoGroupMenuItems,
  defaultFilteredMenuItemsGroup,
  defaultRenderMenuItem,

} = BizAppTool


const filteredNoGroupMenuItems = defaultFilteredNoGroupMenuItems
const filteredMenuItemsGroup = defaultFilteredMenuItemsGroup
const renderMenuItem=defaultRenderMenuItem

const userBarResponsiveStyle = {
  xs: 8,
  sm: 8,
  md: 8,
  lg: 6,
  xl: 6,
  
};


const searchBarResponsiveStyle = {
  xs: 8,
  sm: 8,
  md: 8,
  lg: 12,
  xl: 12,
  
};


const naviBarResponsiveStyle = {
  xs: 8,
  sm: 8,
  md: 8,
  lg: 6,
  xl: 6,
  
};


const query = {
  'screen-xs': {
    maxWidth: 575,
  },
  'screen-sm': {
    minWidth: 576,
    maxWidth: 767,
  },
  'screen-md': {
    minWidth: 768,
    maxWidth: 991,
  },
  'screen-lg': {
    minWidth: 992,
    maxWidth: 1199,
  },
  'screen-xl': {
    minWidth: 1200,
  },
}




class ChangeRequestBizApp extends React.PureComponent {
constructor(props) {
    super(props)
     this.state = {
      openKeys: this.getDefaultCollapsedSubMenus(props),
      showSearch: false,
      searchKeyword:''
    }
  }

  componentDidMount() {}
  componentWillUnmount() {
    clearTimeout(this.resizeTimeout)
  }
  onCollapse = (collapsed) => {
    this.props.dispatch({
      type: 'global/changeLayoutCollapsed',
      payload: collapsed,
    })
  }

  getDefaultCollapsedSubMenus = (props) => {
    const currentMenuSelectedKeys = [...this.getCurrentMenuSelectedKeys(props)]
    currentMenuSelectedKeys.splice(-1, 1)
    if (currentMenuSelectedKeys.length === 0) {
      return ['/changeRequest/']
    }
    return currentMenuSelectedKeys
  }
  getCurrentMenuSelectedKeys = (props) => {
    const { location: { pathname } } = props || this.props
    const keys = pathname.split('/').slice(1)
    if (keys.length === 1 && keys[0] === '') {
      return [this.menus[0].key]
    }
    return keys
  }
  
  getNavMenuItems = (targetObject) => {
  

    const menuData = sessionObject('menuData')
    const targetApp = sessionObject('targetApp')
	const {objectId}=targetApp;
  	const userContext = null
    return (
	  <Menu
        theme="dark"
        mode="inline"
        
        onOpenChange={this.handleOpenChange}
        defaultOpenKeys={['firstOne']}
        style={{ width: '456px' }}
       >
           

             <Menu.Item key="dashboard">
               <Link to={`/changeRequest/${this.props.changeRequest.id}/dashboard`}><Icon type="dashboard" style={{marginRight:"20px"}}/><span>{appLocaleName(userContext,"Dashboard")}</span></Link>
             </Menu.Item>
           
        {filteredNoGroupMenuItems(targetObject,this).map((item)=>(renderMenuItem(item)))}  
        {filteredMenuItemsGroup(targetObject,this).map((groupedMenuItem,index)=>{
          return(
    <SubMenu key={`vg${index}`} title={<span><Icon type="folder" style={{marginRight:"20px"}} /><span>{`${groupedMenuItem.viewGroup}`}</span></span>} >
      {groupedMenuItem.subItems.map((item)=>(renderMenuItem(item)))}  
    </SubMenu>

        )}
        )}

       		
        
           </Menu>
    )
  }
  



  getSchoolClassSearch = () => {
    const {SchoolClassSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('school_class','change_request.school_class_list',false),
      role: "schoolClass",
      data: state._changeRequest.schoolClassList,
      metaInfo: state._changeRequest.schoolClassListMetaInfo,
      count: state._changeRequest.schoolClassCount,
      returnURL: `/changeRequest/${state._changeRequest.id}/dashboard`,
      currentPage: state._changeRequest.schoolClassCurrentPageNumber,
      searchFormParameters: state._changeRequest.schoolClassSearchFormParameters,
      searchParameters: {...state._changeRequest.searchParameters},
      expandForm: state._changeRequest.expandForm,
      loading: state._changeRequest.loading,
      partialList: state._changeRequest.partialList,
      owner: { type: '_changeRequest', id: state._changeRequest.id, 
      referenceName: 'cq', 
      listName: 'schoolClassList', ref:state._changeRequest, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(SchoolClassSearch)
  }
  
  getSchoolClassCreateForm = () => {
   	const {SchoolClassCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "schoolClass",
      data: state._changeRequest.schoolClassList,
      metaInfo: state._changeRequest.schoolClassListMetaInfo,
      count: state._changeRequest.schoolClassCount,
      returnURL: `/changeRequest/${state._changeRequest.id}/list`,
      currentPage: state._changeRequest.schoolClassCurrentPageNumber,
      searchFormParameters: state._changeRequest.schoolClassSearchFormParameters,
      loading: state._changeRequest.loading,
      owner: { type: '_changeRequest', id: state._changeRequest.id, referenceName: 'cq', listName: 'schoolClassList', ref:state._changeRequest, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(SchoolClassCreateForm)
  }
  
  getSchoolClassUpdateForm = () => {
    const userContext = null
  	const {SchoolClassUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._changeRequest.selectedRows,
      role: "schoolClass",
      currentUpdateIndex: state._changeRequest.currentUpdateIndex,
      owner: { type: '_changeRequest', id: state._changeRequest.id, listName: 'schoolClassList', ref:state._changeRequest, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(SchoolClassUpdateForm)
  }

  getTeacherSearch = () => {
    const {TeacherSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('teacher','change_request.teacher_list',false),
      role: "teacher",
      data: state._changeRequest.teacherList,
      metaInfo: state._changeRequest.teacherListMetaInfo,
      count: state._changeRequest.teacherCount,
      returnURL: `/changeRequest/${state._changeRequest.id}/dashboard`,
      currentPage: state._changeRequest.teacherCurrentPageNumber,
      searchFormParameters: state._changeRequest.teacherSearchFormParameters,
      searchParameters: {...state._changeRequest.searchParameters},
      expandForm: state._changeRequest.expandForm,
      loading: state._changeRequest.loading,
      partialList: state._changeRequest.partialList,
      owner: { type: '_changeRequest', id: state._changeRequest.id, 
      referenceName: 'cq', 
      listName: 'teacherList', ref:state._changeRequest, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(TeacherSearch)
  }
  
  getTeacherCreateForm = () => {
   	const {TeacherCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "teacher",
      data: state._changeRequest.teacherList,
      metaInfo: state._changeRequest.teacherListMetaInfo,
      count: state._changeRequest.teacherCount,
      returnURL: `/changeRequest/${state._changeRequest.id}/list`,
      currentPage: state._changeRequest.teacherCurrentPageNumber,
      searchFormParameters: state._changeRequest.teacherSearchFormParameters,
      loading: state._changeRequest.loading,
      owner: { type: '_changeRequest', id: state._changeRequest.id, referenceName: 'cq', listName: 'teacherList', ref:state._changeRequest, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(TeacherCreateForm)
  }
  
  getTeacherUpdateForm = () => {
    const userContext = null
  	const {TeacherUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._changeRequest.selectedRows,
      role: "teacher",
      currentUpdateIndex: state._changeRequest.currentUpdateIndex,
      owner: { type: '_changeRequest', id: state._changeRequest.id, listName: 'teacherList', ref:state._changeRequest, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(TeacherUpdateForm)
  }

  getGuardianSearch = () => {
    const {GuardianSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('guardian','change_request.guardian_list',false),
      role: "guardian",
      data: state._changeRequest.guardianList,
      metaInfo: state._changeRequest.guardianListMetaInfo,
      count: state._changeRequest.guardianCount,
      returnURL: `/changeRequest/${state._changeRequest.id}/dashboard`,
      currentPage: state._changeRequest.guardianCurrentPageNumber,
      searchFormParameters: state._changeRequest.guardianSearchFormParameters,
      searchParameters: {...state._changeRequest.searchParameters},
      expandForm: state._changeRequest.expandForm,
      loading: state._changeRequest.loading,
      partialList: state._changeRequest.partialList,
      owner: { type: '_changeRequest', id: state._changeRequest.id, 
      referenceName: 'cq', 
      listName: 'guardianList', ref:state._changeRequest, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(GuardianSearch)
  }
  
  getGuardianCreateForm = () => {
   	const {GuardianCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "guardian",
      data: state._changeRequest.guardianList,
      metaInfo: state._changeRequest.guardianListMetaInfo,
      count: state._changeRequest.guardianCount,
      returnURL: `/changeRequest/${state._changeRequest.id}/list`,
      currentPage: state._changeRequest.guardianCurrentPageNumber,
      searchFormParameters: state._changeRequest.guardianSearchFormParameters,
      loading: state._changeRequest.loading,
      owner: { type: '_changeRequest', id: state._changeRequest.id, referenceName: 'cq', listName: 'guardianList', ref:state._changeRequest, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(GuardianCreateForm)
  }
  
  getGuardianUpdateForm = () => {
    const userContext = null
  	const {GuardianUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._changeRequest.selectedRows,
      role: "guardian",
      currentUpdateIndex: state._changeRequest.currentUpdateIndex,
      owner: { type: '_changeRequest', id: state._changeRequest.id, listName: 'guardianList', ref:state._changeRequest, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(GuardianUpdateForm)
  }

  getClassQuestionSearch = () => {
    const {ClassQuestionSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('class_question','change_request.class_question_list',false),
      role: "classQuestion",
      data: state._changeRequest.classQuestionList,
      metaInfo: state._changeRequest.classQuestionListMetaInfo,
      count: state._changeRequest.classQuestionCount,
      returnURL: `/changeRequest/${state._changeRequest.id}/dashboard`,
      currentPage: state._changeRequest.classQuestionCurrentPageNumber,
      searchFormParameters: state._changeRequest.classQuestionSearchFormParameters,
      searchParameters: {...state._changeRequest.searchParameters},
      expandForm: state._changeRequest.expandForm,
      loading: state._changeRequest.loading,
      partialList: state._changeRequest.partialList,
      owner: { type: '_changeRequest', id: state._changeRequest.id, 
      referenceName: 'cq', 
      listName: 'classQuestionList', ref:state._changeRequest, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ClassQuestionSearch)
  }
  
  getClassQuestionCreateForm = () => {
   	const {ClassQuestionCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "classQuestion",
      data: state._changeRequest.classQuestionList,
      metaInfo: state._changeRequest.classQuestionListMetaInfo,
      count: state._changeRequest.classQuestionCount,
      returnURL: `/changeRequest/${state._changeRequest.id}/list`,
      currentPage: state._changeRequest.classQuestionCurrentPageNumber,
      searchFormParameters: state._changeRequest.classQuestionSearchFormParameters,
      loading: state._changeRequest.loading,
      owner: { type: '_changeRequest', id: state._changeRequest.id, referenceName: 'cq', listName: 'classQuestionList', ref:state._changeRequest, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(ClassQuestionCreateForm)
  }
  
  getClassQuestionUpdateForm = () => {
    const userContext = null
  	const {ClassQuestionUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._changeRequest.selectedRows,
      role: "classQuestion",
      currentUpdateIndex: state._changeRequest.currentUpdateIndex,
      owner: { type: '_changeRequest', id: state._changeRequest.id, listName: 'classQuestionList', ref:state._changeRequest, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ClassQuestionUpdateForm)
  }

  getClassDailyHealthSurveySearch = () => {
    const {ClassDailyHealthSurveySearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('class_daily_health_survey','change_request.class_daily_health_survey_list',false),
      role: "classDailyHealthSurvey",
      data: state._changeRequest.classDailyHealthSurveyList,
      metaInfo: state._changeRequest.classDailyHealthSurveyListMetaInfo,
      count: state._changeRequest.classDailyHealthSurveyCount,
      returnURL: `/changeRequest/${state._changeRequest.id}/dashboard`,
      currentPage: state._changeRequest.classDailyHealthSurveyCurrentPageNumber,
      searchFormParameters: state._changeRequest.classDailyHealthSurveySearchFormParameters,
      searchParameters: {...state._changeRequest.searchParameters},
      expandForm: state._changeRequest.expandForm,
      loading: state._changeRequest.loading,
      partialList: state._changeRequest.partialList,
      owner: { type: '_changeRequest', id: state._changeRequest.id, 
      referenceName: 'cq', 
      listName: 'classDailyHealthSurveyList', ref:state._changeRequest, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ClassDailyHealthSurveySearch)
  }
  
  getClassDailyHealthSurveyCreateForm = () => {
   	const {ClassDailyHealthSurveyCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "classDailyHealthSurvey",
      data: state._changeRequest.classDailyHealthSurveyList,
      metaInfo: state._changeRequest.classDailyHealthSurveyListMetaInfo,
      count: state._changeRequest.classDailyHealthSurveyCount,
      returnURL: `/changeRequest/${state._changeRequest.id}/list`,
      currentPage: state._changeRequest.classDailyHealthSurveyCurrentPageNumber,
      searchFormParameters: state._changeRequest.classDailyHealthSurveySearchFormParameters,
      loading: state._changeRequest.loading,
      owner: { type: '_changeRequest', id: state._changeRequest.id, referenceName: 'cq', listName: 'classDailyHealthSurveyList', ref:state._changeRequest, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(ClassDailyHealthSurveyCreateForm)
  }
  
  getClassDailyHealthSurveyUpdateForm = () => {
    const userContext = null
  	const {ClassDailyHealthSurveyUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._changeRequest.selectedRows,
      role: "classDailyHealthSurvey",
      currentUpdateIndex: state._changeRequest.currentUpdateIndex,
      owner: { type: '_changeRequest', id: state._changeRequest.id, listName: 'classDailyHealthSurveyList', ref:state._changeRequest, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ClassDailyHealthSurveyUpdateForm)
  }

  getStudentSearch = () => {
    const {StudentSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('student','change_request.student_list',false),
      role: "student",
      data: state._changeRequest.studentList,
      metaInfo: state._changeRequest.studentListMetaInfo,
      count: state._changeRequest.studentCount,
      returnURL: `/changeRequest/${state._changeRequest.id}/dashboard`,
      currentPage: state._changeRequest.studentCurrentPageNumber,
      searchFormParameters: state._changeRequest.studentSearchFormParameters,
      searchParameters: {...state._changeRequest.searchParameters},
      expandForm: state._changeRequest.expandForm,
      loading: state._changeRequest.loading,
      partialList: state._changeRequest.partialList,
      owner: { type: '_changeRequest', id: state._changeRequest.id, 
      referenceName: 'cq', 
      listName: 'studentList', ref:state._changeRequest, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(StudentSearch)
  }
  
  getStudentCreateForm = () => {
   	const {StudentCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "student",
      data: state._changeRequest.studentList,
      metaInfo: state._changeRequest.studentListMetaInfo,
      count: state._changeRequest.studentCount,
      returnURL: `/changeRequest/${state._changeRequest.id}/list`,
      currentPage: state._changeRequest.studentCurrentPageNumber,
      searchFormParameters: state._changeRequest.studentSearchFormParameters,
      loading: state._changeRequest.loading,
      owner: { type: '_changeRequest', id: state._changeRequest.id, referenceName: 'cq', listName: 'studentList', ref:state._changeRequest, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(StudentCreateForm)
  }
  
  getStudentUpdateForm = () => {
    const userContext = null
  	const {StudentUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._changeRequest.selectedRows,
      role: "student",
      currentUpdateIndex: state._changeRequest.currentUpdateIndex,
      owner: { type: '_changeRequest', id: state._changeRequest.id, listName: 'studentList', ref:state._changeRequest, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(StudentUpdateForm)
  }

  getStudentHealthSurveySearch = () => {
    const {StudentHealthSurveySearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('student_health_survey','change_request.student_health_survey_list',false),
      role: "studentHealthSurvey",
      data: state._changeRequest.studentHealthSurveyList,
      metaInfo: state._changeRequest.studentHealthSurveyListMetaInfo,
      count: state._changeRequest.studentHealthSurveyCount,
      returnURL: `/changeRequest/${state._changeRequest.id}/dashboard`,
      currentPage: state._changeRequest.studentHealthSurveyCurrentPageNumber,
      searchFormParameters: state._changeRequest.studentHealthSurveySearchFormParameters,
      searchParameters: {...state._changeRequest.searchParameters},
      expandForm: state._changeRequest.expandForm,
      loading: state._changeRequest.loading,
      partialList: state._changeRequest.partialList,
      owner: { type: '_changeRequest', id: state._changeRequest.id, 
      referenceName: 'cq', 
      listName: 'studentHealthSurveyList', ref:state._changeRequest, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(StudentHealthSurveySearch)
  }
  
  getStudentHealthSurveyCreateForm = () => {
   	const {StudentHealthSurveyCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "studentHealthSurvey",
      data: state._changeRequest.studentHealthSurveyList,
      metaInfo: state._changeRequest.studentHealthSurveyListMetaInfo,
      count: state._changeRequest.studentHealthSurveyCount,
      returnURL: `/changeRequest/${state._changeRequest.id}/list`,
      currentPage: state._changeRequest.studentHealthSurveyCurrentPageNumber,
      searchFormParameters: state._changeRequest.studentHealthSurveySearchFormParameters,
      loading: state._changeRequest.loading,
      owner: { type: '_changeRequest', id: state._changeRequest.id, referenceName: 'cq', listName: 'studentHealthSurveyList', ref:state._changeRequest, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(StudentHealthSurveyCreateForm)
  }
  
  getStudentHealthSurveyUpdateForm = () => {
    const userContext = null
  	const {StudentHealthSurveyUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._changeRequest.selectedRows,
      role: "studentHealthSurvey",
      currentUpdateIndex: state._changeRequest.currentUpdateIndex,
      owner: { type: '_changeRequest', id: state._changeRequest.id, listName: 'studentHealthSurveyList', ref:state._changeRequest, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(StudentHealthSurveyUpdateForm)
  }

  getStudentDailyAnswerSearch = () => {
    const {StudentDailyAnswerSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('student_daily_answer','change_request.student_daily_answer_list',false),
      role: "studentDailyAnswer",
      data: state._changeRequest.studentDailyAnswerList,
      metaInfo: state._changeRequest.studentDailyAnswerListMetaInfo,
      count: state._changeRequest.studentDailyAnswerCount,
      returnURL: `/changeRequest/${state._changeRequest.id}/dashboard`,
      currentPage: state._changeRequest.studentDailyAnswerCurrentPageNumber,
      searchFormParameters: state._changeRequest.studentDailyAnswerSearchFormParameters,
      searchParameters: {...state._changeRequest.searchParameters},
      expandForm: state._changeRequest.expandForm,
      loading: state._changeRequest.loading,
      partialList: state._changeRequest.partialList,
      owner: { type: '_changeRequest', id: state._changeRequest.id, 
      referenceName: 'cq', 
      listName: 'studentDailyAnswerList', ref:state._changeRequest, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(StudentDailyAnswerSearch)
  }
  
  getStudentDailyAnswerCreateForm = () => {
   	const {StudentDailyAnswerCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "studentDailyAnswer",
      data: state._changeRequest.studentDailyAnswerList,
      metaInfo: state._changeRequest.studentDailyAnswerListMetaInfo,
      count: state._changeRequest.studentDailyAnswerCount,
      returnURL: `/changeRequest/${state._changeRequest.id}/list`,
      currentPage: state._changeRequest.studentDailyAnswerCurrentPageNumber,
      searchFormParameters: state._changeRequest.studentDailyAnswerSearchFormParameters,
      loading: state._changeRequest.loading,
      owner: { type: '_changeRequest', id: state._changeRequest.id, referenceName: 'cq', listName: 'studentDailyAnswerList', ref:state._changeRequest, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(StudentDailyAnswerCreateForm)
  }
  
  getStudentDailyAnswerUpdateForm = () => {
    const userContext = null
  	const {StudentDailyAnswerUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._changeRequest.selectedRows,
      role: "studentDailyAnswer",
      currentUpdateIndex: state._changeRequest.currentUpdateIndex,
      owner: { type: '_changeRequest', id: state._changeRequest.id, listName: 'studentDailyAnswerList', ref:state._changeRequest, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(StudentDailyAnswerUpdateForm)
  }


  

 

  getPageTitle = () => {
    // const { location } = this.props
    // const { pathname } = location
    const title = '健康状态调查平台'
    return title
  }
 
  buildRouters = () =>{
  	const {ChangeRequestDashboard} = GlobalComponents
  	const {ChangeRequestPermission} = GlobalComponents
  	const {ChangeRequestProfile} = GlobalComponents
  	
  	
  	const routers=[
  	{path:"/changeRequest/:id/dashboard", component: ChangeRequestDashboard},
  	{path:"/changeRequest/:id/profile", component: ChangeRequestProfile},
  	{path:"/changeRequest/:id/permission", component: ChangeRequestPermission},
  	
  	
  	
  	{path:"/changeRequest/:id/list/schoolClassList", component: this.getSchoolClassSearch()},
  	{path:"/changeRequest/:id/list/schoolClassCreateForm", component: this.getSchoolClassCreateForm()},
  	{path:"/changeRequest/:id/list/schoolClassUpdateForm", component: this.getSchoolClassUpdateForm()},
   	
  	{path:"/changeRequest/:id/list/teacherList", component: this.getTeacherSearch()},
  	{path:"/changeRequest/:id/list/teacherCreateForm", component: this.getTeacherCreateForm()},
  	{path:"/changeRequest/:id/list/teacherUpdateForm", component: this.getTeacherUpdateForm()},
   	
  	{path:"/changeRequest/:id/list/guardianList", component: this.getGuardianSearch()},
  	{path:"/changeRequest/:id/list/guardianCreateForm", component: this.getGuardianCreateForm()},
  	{path:"/changeRequest/:id/list/guardianUpdateForm", component: this.getGuardianUpdateForm()},
   	
  	{path:"/changeRequest/:id/list/classQuestionList", component: this.getClassQuestionSearch()},
  	{path:"/changeRequest/:id/list/classQuestionCreateForm", component: this.getClassQuestionCreateForm()},
  	{path:"/changeRequest/:id/list/classQuestionUpdateForm", component: this.getClassQuestionUpdateForm()},
   	
  	{path:"/changeRequest/:id/list/classDailyHealthSurveyList", component: this.getClassDailyHealthSurveySearch()},
  	{path:"/changeRequest/:id/list/classDailyHealthSurveyCreateForm", component: this.getClassDailyHealthSurveyCreateForm()},
  	{path:"/changeRequest/:id/list/classDailyHealthSurveyUpdateForm", component: this.getClassDailyHealthSurveyUpdateForm()},
   	
  	{path:"/changeRequest/:id/list/studentList", component: this.getStudentSearch()},
  	{path:"/changeRequest/:id/list/studentCreateForm", component: this.getStudentCreateForm()},
  	{path:"/changeRequest/:id/list/studentUpdateForm", component: this.getStudentUpdateForm()},
   	
  	{path:"/changeRequest/:id/list/studentHealthSurveyList", component: this.getStudentHealthSurveySearch()},
  	{path:"/changeRequest/:id/list/studentHealthSurveyCreateForm", component: this.getStudentHealthSurveyCreateForm()},
  	{path:"/changeRequest/:id/list/studentHealthSurveyUpdateForm", component: this.getStudentHealthSurveyUpdateForm()},
   	
  	{path:"/changeRequest/:id/list/studentDailyAnswerList", component: this.getStudentDailyAnswerSearch()},
  	{path:"/changeRequest/:id/list/studentDailyAnswerCreateForm", component: this.getStudentDailyAnswerCreateForm()},
  	{path:"/changeRequest/:id/list/studentDailyAnswerUpdateForm", component: this.getStudentDailyAnswerUpdateForm()},
   	{path:"/changeRequest/:id/ChangeRequestType/:code", component: GlobalComponents.ChangeRequestStepForm},
    	
 	 
  	]
  	
  	const {extraRoutesFunc} = this.props;
  	const extraRoutes = extraRoutesFunc?extraRoutesFunc():[]
  	const finalRoutes = routers.concat(extraRoutes)
    
  	return (<Switch>
             {finalRoutes.map((item)=>(<Route key={item.path} path={item.path} component={item.component} />))}    
  	  	</Switch>)
  	
  
  }
 
 
  handleOpenChange = (openKeys) => {
    const latestOpenKey = openKeys.find(key => this.state.openKeys.indexOf(key) === -1)
    this.setState({
      openKeys: latestOpenKey ? [latestOpenKey] : [],
    })
  }
   toggle = () => {
     const { collapsed } = this.props
     this.props.dispatch({
       type: 'global/changeLayoutCollapsed',
       payload: !collapsed,
     })
   }
    logout = () => {
   
    console.log("log out called")
    this.props.dispatch({ type: 'launcher/signOut' })
  }
   render() {
     // const { collapsed, fetchingNotices,loading } = this.props
     const { collapsed } = this.props
     
  
     const targetApp = sessionObject('targetApp')
     const currentBreadcrumb =targetApp?sessionObject(targetApp.id):[];
     const userContext = null
     const renderBreadcrumbText=(value)=>{
     	if(value==null){
     		return "..."
     	}
     	if(value.length < 10){
     		return value
     	}
     
     	return value.substring(0,10)+"..."
     	
     	
     }
     const menuProps = collapsed ? {} : {
       openKeys: this.state.openKeys,
     }
     const renderBreadcrumbMenuItem=(breadcrumbMenuItem)=>{

      return (
      <Menu.Item key={breadcrumbMenuItem.link}>
      <Link key={breadcrumbMenuItem.link} to={`${breadcrumbMenuItem.link}`} className={styles.breadcrumbLink}>
        <Icon type="heart" style={{marginRight:"10px",color:"red"}} />
        {renderBreadcrumbText(breadcrumbMenuItem.name)}
      </Link></Menu.Item>)

     }
     const breadcrumbMenu=()=>{
      const currentBreadcrumb =targetApp?sessionObject(targetApp.id):[];
      return ( <Menu mode="vertical"> 
      {currentBreadcrumb.map(item => renderBreadcrumbMenuItem(item))}
      </Menu>)
  

     }
     
     const { Search } = Input;
     const showSearchResult=()=>{

        this.setState({showSearch:true})

     }
     const searchChange=(evt)=>{

      this.setState({searchKeyword :evt.target.value})

    }
    const hideSearchResult=()=>{

      this.setState({showSearch:false})

    }

    const {searchLocalData}=GlobalComponents.ChangeRequestBase
	
    
     
     
     const layout = (
     <Layout>
 <Header style={{ position: 'fixed', zIndex: 1, width: '100%' }}>
          
        <Row type="flex" justify="start" align="bottom">
        
        <Col {...naviBarResponsiveStyle} >
            <Dropdown overlay= {this.getNavMenuItems(this.props.changeRequest)}>
              <a  className={styles.menuLink}>
                <Icon type="unordered-list" style={{fontSize:"20px", marginRight:"10px"}}/> 菜单
              </a>
            </Dropdown>            
            <Dropdown overlay={breadcrumbMenu()}>
              <a  className={styles.menuLink}>
                <Icon type="down" style={{fontSize:"20px", marginRight:"10px"}}/> 快速转到
              </a>
            </Dropdown>
        </Col>
        <Col  className={styles.searchBox} {...searchBarResponsiveStyle}  > 
          
          <Search size="default" placeholder="请输入搜索条件, 查找功能，数据和词汇解释，关闭请点击搜索结果空白处" 
            enterButton onFocus={()=>showSearchResult()} onChange={(evt)=>searchChange(evt)}
           	
            style={{ marginLeft:"10px",marginTop:"7px",width:"100%"}} />  
            
            
          </Col>
          <Col  {...userBarResponsiveStyle}  > 
            <Dropdown overlay= { <TopMenu {...this.props} />} className={styles.right}>
                <a  className={styles.menuLink}>
                  <Icon type="user" style={{fontSize:"20px",marginRight:"10px"}}/> 账户
                </a>
            </Dropdown>
            
           </Col>  
         
         </Row>
        </Header>
       <Layout style={{  marginTop: 44 }}>
       
      {this.state.showSearch&&(

        <div style={{backgroundColor:'black'}}  onClick={()=>hideSearchResult()}  >{searchLocalData(this.props.changeRequest,this.state.searchKeyword)}</div>

      )}
       
        
         
         <Layout>
         
            
           <Content style={{ margin: '24px 24px 0', height: '100%' }}>
           
           {this.buildRouters()}
 
             
             
           </Content>
          </Layout>
        </Layout>
      </Layout>
     )
     return (
       <DocumentTitle title={this.getPageTitle()}>
         <ContainerQuery query={query}>
           {params => <div className={classNames(params)}>{layout}</div>}
         </ContainerQuery>
       </DocumentTitle>
     )
   }
}

export default connect(state => ({
  collapsed: state.global.collapsed,
  fetchingNotices: state.global.fetchingNotices,
  notices: state.global.notices,
  changeRequest: state._changeRequest,
  ...state,
}))(ChangeRequestBizApp)



