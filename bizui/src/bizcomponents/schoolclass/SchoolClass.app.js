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
import styles from './SchoolClass.app.less'
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




class SchoolClassBizApp extends React.PureComponent {
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
      return ['/schoolClass/']
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
               <Link to={`/schoolClass/${this.props.schoolClass.id}/dashboard`}><Icon type="dashboard" style={{marginRight:"20px"}}/><span>{appLocaleName(userContext,"Dashboard")}</span></Link>
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
      name: window.mtrans('class_daily_health_survey','school_class.class_daily_health_survey_list',false),
      role: "classDailyHealthSurvey",
      data: state._schoolClass.classDailyHealthSurveyList,
      metaInfo: state._schoolClass.classDailyHealthSurveyListMetaInfo,
      count: state._schoolClass.classDailyHealthSurveyCount,
      returnURL: `/schoolClass/${state._schoolClass.id}/dashboard`,
      currentPage: state._schoolClass.classDailyHealthSurveyCurrentPageNumber,
      searchFormParameters: state._schoolClass.classDailyHealthSurveySearchFormParameters,
      searchParameters: {...state._schoolClass.searchParameters},
      expandForm: state._schoolClass.expandForm,
      loading: state._schoolClass.loading,
      partialList: state._schoolClass.partialList,
      owner: { type: '_schoolClass', id: state._schoolClass.id, 
      referenceName: 'schoolClass', 
      listName: 'classDailyHealthSurveyList', ref:state._schoolClass, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ClassDailyHealthSurveySearch)
  }
  
  getClassDailyHealthSurveyCreateForm = () => {
   	const {ClassDailyHealthSurveyCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "classDailyHealthSurvey",
      data: state._schoolClass.classDailyHealthSurveyList,
      metaInfo: state._schoolClass.classDailyHealthSurveyListMetaInfo,
      count: state._schoolClass.classDailyHealthSurveyCount,
      returnURL: `/schoolClass/${state._schoolClass.id}/list`,
      currentPage: state._schoolClass.classDailyHealthSurveyCurrentPageNumber,
      searchFormParameters: state._schoolClass.classDailyHealthSurveySearchFormParameters,
      loading: state._schoolClass.loading,
      owner: { type: '_schoolClass', id: state._schoolClass.id, referenceName: 'schoolClass', listName: 'classDailyHealthSurveyList', ref:state._schoolClass, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(ClassDailyHealthSurveyCreateForm)
  }
  
  getClassDailyHealthSurveyUpdateForm = () => {
    const userContext = null
  	const {ClassDailyHealthSurveyUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._schoolClass.selectedRows,
      role: "classDailyHealthSurvey",
      currentUpdateIndex: state._schoolClass.currentUpdateIndex,
      owner: { type: '_schoolClass', id: state._schoolClass.id, listName: 'classDailyHealthSurveyList', ref:state._schoolClass, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ClassDailyHealthSurveyUpdateForm)
  }

  getStudentSearch = () => {
    const {StudentSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('student','school_class.student_list',false),
      role: "student",
      data: state._schoolClass.studentList,
      metaInfo: state._schoolClass.studentListMetaInfo,
      count: state._schoolClass.studentCount,
      returnURL: `/schoolClass/${state._schoolClass.id}/dashboard`,
      currentPage: state._schoolClass.studentCurrentPageNumber,
      searchFormParameters: state._schoolClass.studentSearchFormParameters,
      searchParameters: {...state._schoolClass.searchParameters},
      expandForm: state._schoolClass.expandForm,
      loading: state._schoolClass.loading,
      partialList: state._schoolClass.partialList,
      owner: { type: '_schoolClass', id: state._schoolClass.id, 
      referenceName: 'schoolClass', 
      listName: 'studentList', ref:state._schoolClass, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(StudentSearch)
  }
  
  getStudentCreateForm = () => {
   	const {StudentCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "student",
      data: state._schoolClass.studentList,
      metaInfo: state._schoolClass.studentListMetaInfo,
      count: state._schoolClass.studentCount,
      returnURL: `/schoolClass/${state._schoolClass.id}/list`,
      currentPage: state._schoolClass.studentCurrentPageNumber,
      searchFormParameters: state._schoolClass.studentSearchFormParameters,
      loading: state._schoolClass.loading,
      owner: { type: '_schoolClass', id: state._schoolClass.id, referenceName: 'schoolClass', listName: 'studentList', ref:state._schoolClass, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(StudentCreateForm)
  }
  
  getStudentUpdateForm = () => {
    const userContext = null
  	const {StudentUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._schoolClass.selectedRows,
      role: "student",
      currentUpdateIndex: state._schoolClass.currentUpdateIndex,
      owner: { type: '_schoolClass', id: state._schoolClass.id, listName: 'studentList', ref:state._schoolClass, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(StudentUpdateForm)
  }

  getStudentHealthSurveySearch = () => {
    const {StudentHealthSurveySearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('student_health_survey','school_class.student_health_survey_list',false),
      role: "studentHealthSurvey",
      data: state._schoolClass.studentHealthSurveyList,
      metaInfo: state._schoolClass.studentHealthSurveyListMetaInfo,
      count: state._schoolClass.studentHealthSurveyCount,
      returnURL: `/schoolClass/${state._schoolClass.id}/dashboard`,
      currentPage: state._schoolClass.studentHealthSurveyCurrentPageNumber,
      searchFormParameters: state._schoolClass.studentHealthSurveySearchFormParameters,
      searchParameters: {...state._schoolClass.searchParameters},
      expandForm: state._schoolClass.expandForm,
      loading: state._schoolClass.loading,
      partialList: state._schoolClass.partialList,
      owner: { type: '_schoolClass', id: state._schoolClass.id, 
      referenceName: 'schoolClass', 
      listName: 'studentHealthSurveyList', ref:state._schoolClass, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(StudentHealthSurveySearch)
  }
  
  getStudentHealthSurveyCreateForm = () => {
   	const {StudentHealthSurveyCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "studentHealthSurvey",
      data: state._schoolClass.studentHealthSurveyList,
      metaInfo: state._schoolClass.studentHealthSurveyListMetaInfo,
      count: state._schoolClass.studentHealthSurveyCount,
      returnURL: `/schoolClass/${state._schoolClass.id}/list`,
      currentPage: state._schoolClass.studentHealthSurveyCurrentPageNumber,
      searchFormParameters: state._schoolClass.studentHealthSurveySearchFormParameters,
      loading: state._schoolClass.loading,
      owner: { type: '_schoolClass', id: state._schoolClass.id, referenceName: 'schoolClass', listName: 'studentHealthSurveyList', ref:state._schoolClass, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(StudentHealthSurveyCreateForm)
  }
  
  getStudentHealthSurveyUpdateForm = () => {
    const userContext = null
  	const {StudentHealthSurveyUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._schoolClass.selectedRows,
      role: "studentHealthSurvey",
      currentUpdateIndex: state._schoolClass.currentUpdateIndex,
      owner: { type: '_schoolClass', id: state._schoolClass.id, listName: 'studentHealthSurveyList', ref:state._schoolClass, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(StudentHealthSurveyUpdateForm)
  }


  

 

  getPageTitle = () => {
    // const { location } = this.props
    // const { pathname } = location
    const title = '健康状态调查平台'
    return title
  }
 
  buildRouters = () =>{
  	const {SchoolClassDashboard} = GlobalComponents
  	const {SchoolClassPermission} = GlobalComponents
  	const {SchoolClassProfile} = GlobalComponents
  	
  	
  	const routers=[
  	{path:"/schoolClass/:id/dashboard", component: SchoolClassDashboard},
  	{path:"/schoolClass/:id/profile", component: SchoolClassProfile},
  	{path:"/schoolClass/:id/permission", component: SchoolClassPermission},
  	
  	
  	
  	{path:"/schoolClass/:id/list/classDailyHealthSurveyList", component: this.getClassDailyHealthSurveySearch()},
  	{path:"/schoolClass/:id/list/classDailyHealthSurveyCreateForm", component: this.getClassDailyHealthSurveyCreateForm()},
  	{path:"/schoolClass/:id/list/classDailyHealthSurveyUpdateForm", component: this.getClassDailyHealthSurveyUpdateForm()},
   	
  	{path:"/schoolClass/:id/list/studentList", component: this.getStudentSearch()},
  	{path:"/schoolClass/:id/list/studentCreateForm", component: this.getStudentCreateForm()},
  	{path:"/schoolClass/:id/list/studentUpdateForm", component: this.getStudentUpdateForm()},
   	
  	{path:"/schoolClass/:id/list/studentHealthSurveyList", component: this.getStudentHealthSurveySearch()},
  	{path:"/schoolClass/:id/list/studentHealthSurveyCreateForm", component: this.getStudentHealthSurveyCreateForm()},
  	{path:"/schoolClass/:id/list/studentHealthSurveyUpdateForm", component: this.getStudentHealthSurveyUpdateForm()},
   	{path:"/schoolClass/:id/ChangeRequestType/:code", component: GlobalComponents.ChangeRequestStepForm},
    	
 	 
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

    const {searchLocalData}=GlobalComponents.SchoolClassBase
	
    
     
     
     const layout = (
     <Layout>
 <Header style={{ position: 'fixed', zIndex: 1, width: '100%' }}>
          
        <Row type="flex" justify="start" align="bottom">
        
        <Col {...naviBarResponsiveStyle} >
            <Dropdown overlay= {this.getNavMenuItems(this.props.schoolClass)}>
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

        <div style={{backgroundColor:'black'}}  onClick={()=>hideSearchResult()}  >{searchLocalData(this.props.schoolClass,this.state.searchKeyword)}</div>

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
  schoolClass: state._schoolClass,
  ...state,
}))(SchoolClassBizApp)



