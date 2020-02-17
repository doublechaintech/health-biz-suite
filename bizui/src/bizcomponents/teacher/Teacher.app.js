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
import styles from './Teacher.app.less'
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




class TeacherBizApp extends React.PureComponent {
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
      return ['/teacher/']
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
               <Link to={`/teacher/${this.props.teacher.id}/dashboard`}><Icon type="dashboard" style={{marginRight:"20px"}}/><span>{appLocaleName(userContext,"Dashboard")}</span></Link>
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
  



  getClassDailyHealthSurveySearch = () => {
    const {ClassDailyHealthSurveySearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('class_daily_health_survey','teacher.class_daily_health_survey_list',false),
      role: "classDailyHealthSurvey",
      data: state._teacher.classDailyHealthSurveyList,
      metaInfo: state._teacher.classDailyHealthSurveyListMetaInfo,
      count: state._teacher.classDailyHealthSurveyCount,
      returnURL: `/teacher/${state._teacher.id}/dashboard`,
      currentPage: state._teacher.classDailyHealthSurveyCurrentPageNumber,
      searchFormParameters: state._teacher.classDailyHealthSurveySearchFormParameters,
      searchParameters: {...state._teacher.searchParameters},
      expandForm: state._teacher.expandForm,
      loading: state._teacher.loading,
      partialList: state._teacher.partialList,
      owner: { type: '_teacher', id: state._teacher.id, 
      referenceName: 'teacher', 
      listName: 'classDailyHealthSurveyList', ref:state._teacher, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ClassDailyHealthSurveySearch)
  }
  
  getClassDailyHealthSurveyCreateForm = () => {
   	const {ClassDailyHealthSurveyCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "classDailyHealthSurvey",
      data: state._teacher.classDailyHealthSurveyList,
      metaInfo: state._teacher.classDailyHealthSurveyListMetaInfo,
      count: state._teacher.classDailyHealthSurveyCount,
      returnURL: `/teacher/${state._teacher.id}/list`,
      currentPage: state._teacher.classDailyHealthSurveyCurrentPageNumber,
      searchFormParameters: state._teacher.classDailyHealthSurveySearchFormParameters,
      loading: state._teacher.loading,
      owner: { type: '_teacher', id: state._teacher.id, referenceName: 'teacher', listName: 'classDailyHealthSurveyList', ref:state._teacher, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(ClassDailyHealthSurveyCreateForm)
  }
  
  getClassDailyHealthSurveyUpdateForm = () => {
    const userContext = null
  	const {ClassDailyHealthSurveyUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._teacher.selectedRows,
      role: "classDailyHealthSurvey",
      currentUpdateIndex: state._teacher.currentUpdateIndex,
      owner: { type: '_teacher', id: state._teacher.id, listName: 'classDailyHealthSurveyList', ref:state._teacher, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ClassDailyHealthSurveyUpdateForm)
  }

  getStudentHealthSurveySearch = () => {
    const {StudentHealthSurveySearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('student_health_survey','teacher.student_health_survey_list',false),
      role: "studentHealthSurvey",
      data: state._teacher.studentHealthSurveyList,
      metaInfo: state._teacher.studentHealthSurveyListMetaInfo,
      count: state._teacher.studentHealthSurveyCount,
      returnURL: `/teacher/${state._teacher.id}/dashboard`,
      currentPage: state._teacher.studentHealthSurveyCurrentPageNumber,
      searchFormParameters: state._teacher.studentHealthSurveySearchFormParameters,
      searchParameters: {...state._teacher.searchParameters},
      expandForm: state._teacher.expandForm,
      loading: state._teacher.loading,
      partialList: state._teacher.partialList,
      owner: { type: '_teacher', id: state._teacher.id, 
      referenceName: 'teacher', 
      listName: 'studentHealthSurveyList', ref:state._teacher, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(StudentHealthSurveySearch)
  }
  
  getStudentHealthSurveyCreateForm = () => {
   	const {StudentHealthSurveyCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "studentHealthSurvey",
      data: state._teacher.studentHealthSurveyList,
      metaInfo: state._teacher.studentHealthSurveyListMetaInfo,
      count: state._teacher.studentHealthSurveyCount,
      returnURL: `/teacher/${state._teacher.id}/list`,
      currentPage: state._teacher.studentHealthSurveyCurrentPageNumber,
      searchFormParameters: state._teacher.studentHealthSurveySearchFormParameters,
      loading: state._teacher.loading,
      owner: { type: '_teacher', id: state._teacher.id, referenceName: 'teacher', listName: 'studentHealthSurveyList', ref:state._teacher, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(StudentHealthSurveyCreateForm)
  }
  
  getStudentHealthSurveyUpdateForm = () => {
    const userContext = null
  	const {StudentHealthSurveyUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._teacher.selectedRows,
      role: "studentHealthSurvey",
      currentUpdateIndex: state._teacher.currentUpdateIndex,
      owner: { type: '_teacher', id: state._teacher.id, listName: 'studentHealthSurveyList', ref:state._teacher, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(StudentHealthSurveyUpdateForm)
  }

  getHealthSurveyReportSearch = () => {
    const {HealthSurveyReportSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('health_survey_report','teacher.health_survey_report_list',false),
      role: "healthSurveyReport",
      data: state._teacher.healthSurveyReportList,
      metaInfo: state._teacher.healthSurveyReportListMetaInfo,
      count: state._teacher.healthSurveyReportCount,
      returnURL: `/teacher/${state._teacher.id}/dashboard`,
      currentPage: state._teacher.healthSurveyReportCurrentPageNumber,
      searchFormParameters: state._teacher.healthSurveyReportSearchFormParameters,
      searchParameters: {...state._teacher.searchParameters},
      expandForm: state._teacher.expandForm,
      loading: state._teacher.loading,
      partialList: state._teacher.partialList,
      owner: { type: '_teacher', id: state._teacher.id, 
      referenceName: 'teacher', 
      listName: 'healthSurveyReportList', ref:state._teacher, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(HealthSurveyReportSearch)
  }
  
  getHealthSurveyReportCreateForm = () => {
   	const {HealthSurveyReportCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "healthSurveyReport",
      data: state._teacher.healthSurveyReportList,
      metaInfo: state._teacher.healthSurveyReportListMetaInfo,
      count: state._teacher.healthSurveyReportCount,
      returnURL: `/teacher/${state._teacher.id}/list`,
      currentPage: state._teacher.healthSurveyReportCurrentPageNumber,
      searchFormParameters: state._teacher.healthSurveyReportSearchFormParameters,
      loading: state._teacher.loading,
      owner: { type: '_teacher', id: state._teacher.id, referenceName: 'teacher', listName: 'healthSurveyReportList', ref:state._teacher, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(HealthSurveyReportCreateForm)
  }
  
  getHealthSurveyReportUpdateForm = () => {
    const userContext = null
  	const {HealthSurveyReportUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._teacher.selectedRows,
      role: "healthSurveyReport",
      currentUpdateIndex: state._teacher.currentUpdateIndex,
      owner: { type: '_teacher', id: state._teacher.id, listName: 'healthSurveyReportList', ref:state._teacher, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(HealthSurveyReportUpdateForm)
  }


  

 

  getPageTitle = () => {
    // const { location } = this.props
    // const { pathname } = location
    const title = '健康状态调查平台'
    return title
  }
 
  buildRouters = () =>{
  	const {TeacherDashboard} = GlobalComponents
  	const {TeacherPermission} = GlobalComponents
  	const {TeacherProfile} = GlobalComponents
  	
  	
  	const routers=[
  	{path:"/teacher/:id/dashboard", component: TeacherDashboard},
  	{path:"/teacher/:id/profile", component: TeacherProfile},
  	{path:"/teacher/:id/permission", component: TeacherPermission},
  	
  	
  	
  	{path:"/teacher/:id/list/classDailyHealthSurveyList", component: this.getClassDailyHealthSurveySearch()},
  	{path:"/teacher/:id/list/classDailyHealthSurveyCreateForm", component: this.getClassDailyHealthSurveyCreateForm()},
  	{path:"/teacher/:id/list/classDailyHealthSurveyUpdateForm", component: this.getClassDailyHealthSurveyUpdateForm()},
   	
  	{path:"/teacher/:id/list/studentHealthSurveyList", component: this.getStudentHealthSurveySearch()},
  	{path:"/teacher/:id/list/studentHealthSurveyCreateForm", component: this.getStudentHealthSurveyCreateForm()},
  	{path:"/teacher/:id/list/studentHealthSurveyUpdateForm", component: this.getStudentHealthSurveyUpdateForm()},
   	
  	{path:"/teacher/:id/list/healthSurveyReportList", component: this.getHealthSurveyReportSearch()},
  	{path:"/teacher/:id/list/healthSurveyReportCreateForm", component: this.getHealthSurveyReportCreateForm()},
  	{path:"/teacher/:id/list/healthSurveyReportUpdateForm", component: this.getHealthSurveyReportUpdateForm()},
   	{path:"/teacher/:id/ChangeRequestType/:code", component: GlobalComponents.ChangeRequestStepForm},
    	
 	 
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

    const {searchLocalData}=GlobalComponents.TeacherBase
	
    
     
     
     const layout = (
     <Layout>
 <Header style={{ position: 'fixed', zIndex: 1, width: '100%' }}>
          
        <Row type="flex" justify="start" align="bottom">
        
        <Col {...naviBarResponsiveStyle} >
            <Dropdown overlay= {this.getNavMenuItems(this.props.teacher)}>
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

        <div style={{backgroundColor:'black'}}  onClick={()=>hideSearchResult()}  >{searchLocalData(this.props.teacher,this.state.searchKeyword)}</div>

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
  teacher: state._teacher,
  ...state,
}))(TeacherBizApp)



