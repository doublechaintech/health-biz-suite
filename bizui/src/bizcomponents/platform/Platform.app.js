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
import styles from './Platform.app.less'
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




class PlatformBizApp extends React.PureComponent {
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
      return ['/platform/']
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
               <Link to={`/platform/${this.props.platform.id}/dashboard`}><Icon type="dashboard" style={{marginRight:"20px"}}/><span>{appLocaleName(userContext,"Dashboard")}</span></Link>
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
  



  getProvinceSearch = () => {
    const {ProvinceSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('province','platform.province_list',false),
      role: "province",
      data: state._platform.provinceList,
      metaInfo: state._platform.provinceListMetaInfo,
      count: state._platform.provinceCount,
      returnURL: `/platform/${state._platform.id}/dashboard`,
      currentPage: state._platform.provinceCurrentPageNumber,
      searchFormParameters: state._platform.provinceSearchFormParameters,
      searchParameters: {...state._platform.searchParameters},
      expandForm: state._platform.expandForm,
      loading: state._platform.loading,
      partialList: state._platform.partialList,
      owner: { type: '_platform', id: state._platform.id, 
      referenceName: 'platform', 
      listName: 'provinceList', ref:state._platform, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ProvinceSearch)
  }
  
  getProvinceCreateForm = () => {
   	const {ProvinceCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "province",
      data: state._platform.provinceList,
      metaInfo: state._platform.provinceListMetaInfo,
      count: state._platform.provinceCount,
      returnURL: `/platform/${state._platform.id}/list`,
      currentPage: state._platform.provinceCurrentPageNumber,
      searchFormParameters: state._platform.provinceSearchFormParameters,
      loading: state._platform.loading,
      owner: { type: '_platform', id: state._platform.id, referenceName: 'platform', listName: 'provinceList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(ProvinceCreateForm)
  }
  
  getProvinceUpdateForm = () => {
    const userContext = null
  	const {ProvinceUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._platform.selectedRows,
      role: "province",
      currentUpdateIndex: state._platform.currentUpdateIndex,
      owner: { type: '_platform', id: state._platform.id, listName: 'provinceList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ProvinceUpdateForm)
  }

  getCitySearch = () => {
    const {CitySearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('city','platform.city_list',false),
      role: "city",
      data: state._platform.cityList,
      metaInfo: state._platform.cityListMetaInfo,
      count: state._platform.cityCount,
      returnURL: `/platform/${state._platform.id}/dashboard`,
      currentPage: state._platform.cityCurrentPageNumber,
      searchFormParameters: state._platform.citySearchFormParameters,
      searchParameters: {...state._platform.searchParameters},
      expandForm: state._platform.expandForm,
      loading: state._platform.loading,
      partialList: state._platform.partialList,
      owner: { type: '_platform', id: state._platform.id, 
      referenceName: 'platform', 
      listName: 'cityList', ref:state._platform, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(CitySearch)
  }
  
  getCityCreateForm = () => {
   	const {CityCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "city",
      data: state._platform.cityList,
      metaInfo: state._platform.cityListMetaInfo,
      count: state._platform.cityCount,
      returnURL: `/platform/${state._platform.id}/list`,
      currentPage: state._platform.cityCurrentPageNumber,
      searchFormParameters: state._platform.citySearchFormParameters,
      loading: state._platform.loading,
      owner: { type: '_platform', id: state._platform.id, referenceName: 'platform', listName: 'cityList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(CityCreateForm)
  }
  
  getCityUpdateForm = () => {
    const userContext = null
  	const {CityUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._platform.selectedRows,
      role: "city",
      currentUpdateIndex: state._platform.currentUpdateIndex,
      owner: { type: '_platform', id: state._platform.id, listName: 'cityList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(CityUpdateForm)
  }

  getDistrictSearch = () => {
    const {DistrictSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('district','platform.district_list',false),
      role: "district",
      data: state._platform.districtList,
      metaInfo: state._platform.districtListMetaInfo,
      count: state._platform.districtCount,
      returnURL: `/platform/${state._platform.id}/dashboard`,
      currentPage: state._platform.districtCurrentPageNumber,
      searchFormParameters: state._platform.districtSearchFormParameters,
      searchParameters: {...state._platform.searchParameters},
      expandForm: state._platform.expandForm,
      loading: state._platform.loading,
      partialList: state._platform.partialList,
      owner: { type: '_platform', id: state._platform.id, 
      referenceName: 'platform', 
      listName: 'districtList', ref:state._platform, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(DistrictSearch)
  }
  
  getDistrictCreateForm = () => {
   	const {DistrictCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "district",
      data: state._platform.districtList,
      metaInfo: state._platform.districtListMetaInfo,
      count: state._platform.districtCount,
      returnURL: `/platform/${state._platform.id}/list`,
      currentPage: state._platform.districtCurrentPageNumber,
      searchFormParameters: state._platform.districtSearchFormParameters,
      loading: state._platform.loading,
      owner: { type: '_platform', id: state._platform.id, referenceName: 'platform', listName: 'districtList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(DistrictCreateForm)
  }
  
  getDistrictUpdateForm = () => {
    const userContext = null
  	const {DistrictUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._platform.selectedRows,
      role: "district",
      currentUpdateIndex: state._platform.currentUpdateIndex,
      owner: { type: '_platform', id: state._platform.id, listName: 'districtList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(DistrictUpdateForm)
  }

  getTeacherSearch = () => {
    const {TeacherSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('teacher','platform.teacher_list',false),
      role: "teacher",
      data: state._platform.teacherList,
      metaInfo: state._platform.teacherListMetaInfo,
      count: state._platform.teacherCount,
      returnURL: `/platform/${state._platform.id}/dashboard`,
      currentPage: state._platform.teacherCurrentPageNumber,
      searchFormParameters: state._platform.teacherSearchFormParameters,
      searchParameters: {...state._platform.searchParameters},
      expandForm: state._platform.expandForm,
      loading: state._platform.loading,
      partialList: state._platform.partialList,
      owner: { type: '_platform', id: state._platform.id, 
      referenceName: 'platform', 
      listName: 'teacherList', ref:state._platform, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(TeacherSearch)
  }
  
  getTeacherCreateForm = () => {
   	const {TeacherCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "teacher",
      data: state._platform.teacherList,
      metaInfo: state._platform.teacherListMetaInfo,
      count: state._platform.teacherCount,
      returnURL: `/platform/${state._platform.id}/list`,
      currentPage: state._platform.teacherCurrentPageNumber,
      searchFormParameters: state._platform.teacherSearchFormParameters,
      loading: state._platform.loading,
      owner: { type: '_platform', id: state._platform.id, referenceName: 'platform', listName: 'teacherList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(TeacherCreateForm)
  }
  
  getTeacherUpdateForm = () => {
    const userContext = null
  	const {TeacherUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._platform.selectedRows,
      role: "teacher",
      currentUpdateIndex: state._platform.currentUpdateIndex,
      owner: { type: '_platform', id: state._platform.id, listName: 'teacherList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(TeacherUpdateForm)
  }

  getStudentSearch = () => {
    const {StudentSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('student','platform.student_list',false),
      role: "student",
      data: state._platform.studentList,
      metaInfo: state._platform.studentListMetaInfo,
      count: state._platform.studentCount,
      returnURL: `/platform/${state._platform.id}/dashboard`,
      currentPage: state._platform.studentCurrentPageNumber,
      searchFormParameters: state._platform.studentSearchFormParameters,
      searchParameters: {...state._platform.searchParameters},
      expandForm: state._platform.expandForm,
      loading: state._platform.loading,
      partialList: state._platform.partialList,
      owner: { type: '_platform', id: state._platform.id, 
      referenceName: 'platform', 
      listName: 'studentList', ref:state._platform, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(StudentSearch)
  }
  
  getStudentCreateForm = () => {
   	const {StudentCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "student",
      data: state._platform.studentList,
      metaInfo: state._platform.studentListMetaInfo,
      count: state._platform.studentCount,
      returnURL: `/platform/${state._platform.id}/list`,
      currentPage: state._platform.studentCurrentPageNumber,
      searchFormParameters: state._platform.studentSearchFormParameters,
      loading: state._platform.loading,
      owner: { type: '_platform', id: state._platform.id, referenceName: 'platform', listName: 'studentList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(StudentCreateForm)
  }
  
  getStudentUpdateForm = () => {
    const userContext = null
  	const {StudentUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._platform.selectedRows,
      role: "student",
      currentUpdateIndex: state._platform.currentUpdateIndex,
      owner: { type: '_platform', id: state._platform.id, listName: 'studentList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(StudentUpdateForm)
  }

  getQuestionSearch = () => {
    const {QuestionSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('question','platform.question_list',false),
      role: "question",
      data: state._platform.questionList,
      metaInfo: state._platform.questionListMetaInfo,
      count: state._platform.questionCount,
      returnURL: `/platform/${state._platform.id}/dashboard`,
      currentPage: state._platform.questionCurrentPageNumber,
      searchFormParameters: state._platform.questionSearchFormParameters,
      searchParameters: {...state._platform.searchParameters},
      expandForm: state._platform.expandForm,
      loading: state._platform.loading,
      partialList: state._platform.partialList,
      owner: { type: '_platform', id: state._platform.id, 
      referenceName: 'platform', 
      listName: 'questionList', ref:state._platform, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(QuestionSearch)
  }
  
  getQuestionCreateForm = () => {
   	const {QuestionCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "question",
      data: state._platform.questionList,
      metaInfo: state._platform.questionListMetaInfo,
      count: state._platform.questionCount,
      returnURL: `/platform/${state._platform.id}/list`,
      currentPage: state._platform.questionCurrentPageNumber,
      searchFormParameters: state._platform.questionSearchFormParameters,
      loading: state._platform.loading,
      owner: { type: '_platform', id: state._platform.id, referenceName: 'platform', listName: 'questionList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(QuestionCreateForm)
  }
  
  getQuestionUpdateForm = () => {
    const userContext = null
  	const {QuestionUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._platform.selectedRows,
      role: "question",
      currentUpdateIndex: state._platform.currentUpdateIndex,
      owner: { type: '_platform', id: state._platform.id, listName: 'questionList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(QuestionUpdateForm)
  }

  getQuestionTypeSearch = () => {
    const {QuestionTypeSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('question_type','platform.question_type_list',false),
      role: "questionType",
      data: state._platform.questionTypeList,
      metaInfo: state._platform.questionTypeListMetaInfo,
      count: state._platform.questionTypeCount,
      returnURL: `/platform/${state._platform.id}/dashboard`,
      currentPage: state._platform.questionTypeCurrentPageNumber,
      searchFormParameters: state._platform.questionTypeSearchFormParameters,
      searchParameters: {...state._platform.searchParameters},
      expandForm: state._platform.expandForm,
      loading: state._platform.loading,
      partialList: state._platform.partialList,
      owner: { type: '_platform', id: state._platform.id, 
      referenceName: 'platform', 
      listName: 'questionTypeList', ref:state._platform, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(QuestionTypeSearch)
  }
  
  getQuestionTypeCreateForm = () => {
   	const {QuestionTypeCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "questionType",
      data: state._platform.questionTypeList,
      metaInfo: state._platform.questionTypeListMetaInfo,
      count: state._platform.questionTypeCount,
      returnURL: `/platform/${state._platform.id}/list`,
      currentPage: state._platform.questionTypeCurrentPageNumber,
      searchFormParameters: state._platform.questionTypeSearchFormParameters,
      loading: state._platform.loading,
      owner: { type: '_platform', id: state._platform.id, referenceName: 'platform', listName: 'questionTypeList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(QuestionTypeCreateForm)
  }
  
  getQuestionTypeUpdateForm = () => {
    const userContext = null
  	const {QuestionTypeUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._platform.selectedRows,
      role: "questionType",
      currentUpdateIndex: state._platform.currentUpdateIndex,
      owner: { type: '_platform', id: state._platform.id, listName: 'questionTypeList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(QuestionTypeUpdateForm)
  }

  getSurveyStatusSearch = () => {
    const {SurveyStatusSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('survey_status','platform.survey_status_list',false),
      role: "surveyStatus",
      data: state._platform.surveyStatusList,
      metaInfo: state._platform.surveyStatusListMetaInfo,
      count: state._platform.surveyStatusCount,
      returnURL: `/platform/${state._platform.id}/dashboard`,
      currentPage: state._platform.surveyStatusCurrentPageNumber,
      searchFormParameters: state._platform.surveyStatusSearchFormParameters,
      searchParameters: {...state._platform.searchParameters},
      expandForm: state._platform.expandForm,
      loading: state._platform.loading,
      partialList: state._platform.partialList,
      owner: { type: '_platform', id: state._platform.id, 
      referenceName: 'platform', 
      listName: 'surveyStatusList', ref:state._platform, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(SurveyStatusSearch)
  }
  
  getSurveyStatusCreateForm = () => {
   	const {SurveyStatusCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "surveyStatus",
      data: state._platform.surveyStatusList,
      metaInfo: state._platform.surveyStatusListMetaInfo,
      count: state._platform.surveyStatusCount,
      returnURL: `/platform/${state._platform.id}/list`,
      currentPage: state._platform.surveyStatusCurrentPageNumber,
      searchFormParameters: state._platform.surveyStatusSearchFormParameters,
      loading: state._platform.loading,
      owner: { type: '_platform', id: state._platform.id, referenceName: 'platform', listName: 'surveyStatusList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(SurveyStatusCreateForm)
  }
  
  getSurveyStatusUpdateForm = () => {
    const userContext = null
  	const {SurveyStatusUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._platform.selectedRows,
      role: "surveyStatus",
      currentUpdateIndex: state._platform.currentUpdateIndex,
      owner: { type: '_platform', id: state._platform.id, listName: 'surveyStatusList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(SurveyStatusUpdateForm)
  }

  getUserSearch = () => {
    const {UserSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('user','platform.user_list',false),
      role: "user",
      data: state._platform.userList,
      metaInfo: state._platform.userListMetaInfo,
      count: state._platform.userCount,
      returnURL: `/platform/${state._platform.id}/dashboard`,
      currentPage: state._platform.userCurrentPageNumber,
      searchFormParameters: state._platform.userSearchFormParameters,
      searchParameters: {...state._platform.searchParameters},
      expandForm: state._platform.expandForm,
      loading: state._platform.loading,
      partialList: state._platform.partialList,
      owner: { type: '_platform', id: state._platform.id, 
      referenceName: 'platform', 
      listName: 'userList', ref:state._platform, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(UserSearch)
  }
  
  getUserCreateForm = () => {
   	const {UserCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "user",
      data: state._platform.userList,
      metaInfo: state._platform.userListMetaInfo,
      count: state._platform.userCount,
      returnURL: `/platform/${state._platform.id}/list`,
      currentPage: state._platform.userCurrentPageNumber,
      searchFormParameters: state._platform.userSearchFormParameters,
      loading: state._platform.loading,
      owner: { type: '_platform', id: state._platform.id, referenceName: 'platform', listName: 'userList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(UserCreateForm)
  }
  
  getUserUpdateForm = () => {
    const userContext = null
  	const {UserUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._platform.selectedRows,
      role: "user",
      currentUpdateIndex: state._platform.currentUpdateIndex,
      owner: { type: '_platform', id: state._platform.id, listName: 'userList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(UserUpdateForm)
  }

  getChangeRequestSearch = () => {
    const {ChangeRequestSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('change_request','platform.change_request_list',false),
      role: "changeRequest",
      data: state._platform.changeRequestList,
      metaInfo: state._platform.changeRequestListMetaInfo,
      count: state._platform.changeRequestCount,
      returnURL: `/platform/${state._platform.id}/dashboard`,
      currentPage: state._platform.changeRequestCurrentPageNumber,
      searchFormParameters: state._platform.changeRequestSearchFormParameters,
      searchParameters: {...state._platform.searchParameters},
      expandForm: state._platform.expandForm,
      loading: state._platform.loading,
      partialList: state._platform.partialList,
      owner: { type: '_platform', id: state._platform.id, 
      referenceName: 'platform', 
      listName: 'changeRequestList', ref:state._platform, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ChangeRequestSearch)
  }
  
  getChangeRequestCreateForm = () => {
   	const {ChangeRequestCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "changeRequest",
      data: state._platform.changeRequestList,
      metaInfo: state._platform.changeRequestListMetaInfo,
      count: state._platform.changeRequestCount,
      returnURL: `/platform/${state._platform.id}/list`,
      currentPage: state._platform.changeRequestCurrentPageNumber,
      searchFormParameters: state._platform.changeRequestSearchFormParameters,
      loading: state._platform.loading,
      owner: { type: '_platform', id: state._platform.id, referenceName: 'platform', listName: 'changeRequestList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(ChangeRequestCreateForm)
  }
  
  getChangeRequestUpdateForm = () => {
    const userContext = null
  	const {ChangeRequestUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._platform.selectedRows,
      role: "changeRequest",
      currentUpdateIndex: state._platform.currentUpdateIndex,
      owner: { type: '_platform', id: state._platform.id, listName: 'changeRequestList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ChangeRequestUpdateForm)
  }

  getChangeRequestTypeSearch = () => {
    const {ChangeRequestTypeSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('change_request_type','platform.change_request_type_list',false),
      role: "changeRequestType",
      data: state._platform.changeRequestTypeList,
      metaInfo: state._platform.changeRequestTypeListMetaInfo,
      count: state._platform.changeRequestTypeCount,
      returnURL: `/platform/${state._platform.id}/dashboard`,
      currentPage: state._platform.changeRequestTypeCurrentPageNumber,
      searchFormParameters: state._platform.changeRequestTypeSearchFormParameters,
      searchParameters: {...state._platform.searchParameters},
      expandForm: state._platform.expandForm,
      loading: state._platform.loading,
      partialList: state._platform.partialList,
      owner: { type: '_platform', id: state._platform.id, 
      referenceName: 'platform', 
      listName: 'changeRequestTypeList', ref:state._platform, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ChangeRequestTypeSearch)
  }
  
  getChangeRequestTypeCreateForm = () => {
   	const {ChangeRequestTypeCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "changeRequestType",
      data: state._platform.changeRequestTypeList,
      metaInfo: state._platform.changeRequestTypeListMetaInfo,
      count: state._platform.changeRequestTypeCount,
      returnURL: `/platform/${state._platform.id}/list`,
      currentPage: state._platform.changeRequestTypeCurrentPageNumber,
      searchFormParameters: state._platform.changeRequestTypeSearchFormParameters,
      loading: state._platform.loading,
      owner: { type: '_platform', id: state._platform.id, referenceName: 'platform', listName: 'changeRequestTypeList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(ChangeRequestTypeCreateForm)
  }
  
  getChangeRequestTypeUpdateForm = () => {
    const userContext = null
  	const {ChangeRequestTypeUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._platform.selectedRows,
      role: "changeRequestType",
      currentUpdateIndex: state._platform.currentUpdateIndex,
      owner: { type: '_platform', id: state._platform.id, listName: 'changeRequestTypeList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ChangeRequestTypeUpdateForm)
  }


  

 

  getPageTitle = () => {
    // const { location } = this.props
    // const { pathname } = location
    const title = '健康状态调查平台'
    return title
  }
 
  buildRouters = () =>{
  	const {PlatformDashboard} = GlobalComponents
  	const {PlatformPermission} = GlobalComponents
  	const {PlatformProfile} = GlobalComponents
  	
  	
  	const routers=[
  	{path:"/platform/:id/dashboard", component: PlatformDashboard},
  	{path:"/platform/:id/profile", component: PlatformProfile},
  	{path:"/platform/:id/permission", component: PlatformPermission},
  	
  	
  	
  	{path:"/platform/:id/list/provinceList", component: this.getProvinceSearch()},
  	{path:"/platform/:id/list/provinceCreateForm", component: this.getProvinceCreateForm()},
  	{path:"/platform/:id/list/provinceUpdateForm", component: this.getProvinceUpdateForm()},
   	
  	{path:"/platform/:id/list/cityList", component: this.getCitySearch()},
  	{path:"/platform/:id/list/cityCreateForm", component: this.getCityCreateForm()},
  	{path:"/platform/:id/list/cityUpdateForm", component: this.getCityUpdateForm()},
   	
  	{path:"/platform/:id/list/districtList", component: this.getDistrictSearch()},
  	{path:"/platform/:id/list/districtCreateForm", component: this.getDistrictCreateForm()},
  	{path:"/platform/:id/list/districtUpdateForm", component: this.getDistrictUpdateForm()},
   	
  	{path:"/platform/:id/list/teacherList", component: this.getTeacherSearch()},
  	{path:"/platform/:id/list/teacherCreateForm", component: this.getTeacherCreateForm()},
  	{path:"/platform/:id/list/teacherUpdateForm", component: this.getTeacherUpdateForm()},
   	
  	{path:"/platform/:id/list/studentList", component: this.getStudentSearch()},
  	{path:"/platform/:id/list/studentCreateForm", component: this.getStudentCreateForm()},
  	{path:"/platform/:id/list/studentUpdateForm", component: this.getStudentUpdateForm()},
   	
  	{path:"/platform/:id/list/questionList", component: this.getQuestionSearch()},
  	{path:"/platform/:id/list/questionCreateForm", component: this.getQuestionCreateForm()},
  	{path:"/platform/:id/list/questionUpdateForm", component: this.getQuestionUpdateForm()},
   	
  	{path:"/platform/:id/list/questionTypeList", component: this.getQuestionTypeSearch()},
  	{path:"/platform/:id/list/questionTypeCreateForm", component: this.getQuestionTypeCreateForm()},
  	{path:"/platform/:id/list/questionTypeUpdateForm", component: this.getQuestionTypeUpdateForm()},
   	
  	{path:"/platform/:id/list/surveyStatusList", component: this.getSurveyStatusSearch()},
  	{path:"/platform/:id/list/surveyStatusCreateForm", component: this.getSurveyStatusCreateForm()},
  	{path:"/platform/:id/list/surveyStatusUpdateForm", component: this.getSurveyStatusUpdateForm()},
   	
  	{path:"/platform/:id/list/userList", component: this.getUserSearch()},
  	{path:"/platform/:id/list/userCreateForm", component: this.getUserCreateForm()},
  	{path:"/platform/:id/list/userUpdateForm", component: this.getUserUpdateForm()},
   	
  	{path:"/platform/:id/list/changeRequestList", component: this.getChangeRequestSearch()},
  	{path:"/platform/:id/list/changeRequestCreateForm", component: this.getChangeRequestCreateForm()},
  	{path:"/platform/:id/list/changeRequestUpdateForm", component: this.getChangeRequestUpdateForm()},
   	
  	{path:"/platform/:id/list/changeRequestTypeList", component: this.getChangeRequestTypeSearch()},
  	{path:"/platform/:id/list/changeRequestTypeCreateForm", component: this.getChangeRequestTypeCreateForm()},
  	{path:"/platform/:id/list/changeRequestTypeUpdateForm", component: this.getChangeRequestTypeUpdateForm()},
   	{path:"/platform/:id/ChangeRequestType/:code", component: GlobalComponents.ChangeRequestStepForm},
    	
 	 
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

    const {searchLocalData}=GlobalComponents.PlatformBase
	
    
     
     
     const layout = (
     <Layout>
 <Header style={{ position: 'fixed', zIndex: 1, width: '100%' }}>
          
        <Row type="flex" justify="start" align="bottom">
        
        <Col {...naviBarResponsiveStyle} >
            <Dropdown overlay= {this.getNavMenuItems(this.props.platform)}>
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

        <div style={{backgroundColor:'black'}}  onClick={()=>hideSearchResult()}  >{searchLocalData(this.props.platform,this.state.searchKeyword)}</div>

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
  platform: state._platform,
  ...state,
}))(PlatformBizApp)



