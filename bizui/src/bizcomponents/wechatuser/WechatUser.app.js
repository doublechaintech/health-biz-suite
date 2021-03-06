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
import styles from './WechatUser.app.less'
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




class WechatUserBizApp extends React.PureComponent {
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
      return ['/wechatUser/']
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
               <Link to={`/wechatUser/${this.props.wechatUser.id}/dashboard`}><Icon type="dashboard" style={{marginRight:"20px"}}/><span>{appLocaleName(userContext,"Dashboard")}</span></Link>
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
  



  getGuardianSearch = () => {
    const {GuardianSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('guardian','wechat_user.guardian_list',false),
      role: "guardian",
      data: state._wechatUser.guardianList,
      metaInfo: state._wechatUser.guardianListMetaInfo,
      count: state._wechatUser.guardianCount,
      returnURL: `/wechatUser/${state._wechatUser.id}/dashboard`,
      currentPage: state._wechatUser.guardianCurrentPageNumber,
      searchFormParameters: state._wechatUser.guardianSearchFormParameters,
      searchParameters: {...state._wechatUser.searchParameters},
      expandForm: state._wechatUser.expandForm,
      loading: state._wechatUser.loading,
      partialList: state._wechatUser.partialList,
      owner: { type: '_wechatUser', id: state._wechatUser.id, 
      referenceName: 'wechatUser', 
      listName: 'guardianList', ref:state._wechatUser, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(GuardianSearch)
  }
  
  getGuardianCreateForm = () => {
   	const {GuardianCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "guardian",
      data: state._wechatUser.guardianList,
      metaInfo: state._wechatUser.guardianListMetaInfo,
      count: state._wechatUser.guardianCount,
      returnURL: `/wechatUser/${state._wechatUser.id}/list`,
      currentPage: state._wechatUser.guardianCurrentPageNumber,
      searchFormParameters: state._wechatUser.guardianSearchFormParameters,
      loading: state._wechatUser.loading,
      owner: { type: '_wechatUser', id: state._wechatUser.id, referenceName: 'wechatUser', listName: 'guardianList', ref:state._wechatUser, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(GuardianCreateForm)
  }
  
  getGuardianUpdateForm = () => {
    const userContext = null
  	const {GuardianUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._wechatUser.selectedRows,
      role: "guardian",
      currentUpdateIndex: state._wechatUser.currentUpdateIndex,
      owner: { type: '_wechatUser', id: state._wechatUser.id, listName: 'guardianList', ref:state._wechatUser, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(GuardianUpdateForm)
  }

  getClassQuestionSearch = () => {
    const {ClassQuestionSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('class_question','wechat_user.class_question_list',false),
      role: "classQuestion",
      data: state._wechatUser.classQuestionList,
      metaInfo: state._wechatUser.classQuestionListMetaInfo,
      count: state._wechatUser.classQuestionCount,
      returnURL: `/wechatUser/${state._wechatUser.id}/dashboard`,
      currentPage: state._wechatUser.classQuestionCurrentPageNumber,
      searchFormParameters: state._wechatUser.classQuestionSearchFormParameters,
      searchParameters: {...state._wechatUser.searchParameters},
      expandForm: state._wechatUser.expandForm,
      loading: state._wechatUser.loading,
      partialList: state._wechatUser.partialList,
      owner: { type: '_wechatUser', id: state._wechatUser.id, 
      referenceName: 'creator', 
      listName: 'classQuestionList', ref:state._wechatUser, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ClassQuestionSearch)
  }
  
  getClassQuestionCreateForm = () => {
   	const {ClassQuestionCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "classQuestion",
      data: state._wechatUser.classQuestionList,
      metaInfo: state._wechatUser.classQuestionListMetaInfo,
      count: state._wechatUser.classQuestionCount,
      returnURL: `/wechatUser/${state._wechatUser.id}/list`,
      currentPage: state._wechatUser.classQuestionCurrentPageNumber,
      searchFormParameters: state._wechatUser.classQuestionSearchFormParameters,
      loading: state._wechatUser.loading,
      owner: { type: '_wechatUser', id: state._wechatUser.id, referenceName: 'creator', listName: 'classQuestionList', ref:state._wechatUser, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(ClassQuestionCreateForm)
  }
  
  getClassQuestionUpdateForm = () => {
    const userContext = null
  	const {ClassQuestionUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._wechatUser.selectedRows,
      role: "classQuestion",
      currentUpdateIndex: state._wechatUser.currentUpdateIndex,
      owner: { type: '_wechatUser', id: state._wechatUser.id, listName: 'classQuestionList', ref:state._wechatUser, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ClassQuestionUpdateForm)
  }

  getClassDailyHealthSurveySearch = () => {
    const {ClassDailyHealthSurveySearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('class_daily_health_survey','wechat_user.class_daily_health_survey_list',false),
      role: "classDailyHealthSurvey",
      data: state._wechatUser.classDailyHealthSurveyList,
      metaInfo: state._wechatUser.classDailyHealthSurveyListMetaInfo,
      count: state._wechatUser.classDailyHealthSurveyCount,
      returnURL: `/wechatUser/${state._wechatUser.id}/dashboard`,
      currentPage: state._wechatUser.classDailyHealthSurveyCurrentPageNumber,
      searchFormParameters: state._wechatUser.classDailyHealthSurveySearchFormParameters,
      searchParameters: {...state._wechatUser.searchParameters},
      expandForm: state._wechatUser.expandForm,
      loading: state._wechatUser.loading,
      partialList: state._wechatUser.partialList,
      owner: { type: '_wechatUser', id: state._wechatUser.id, 
      referenceName: 'creator', 
      listName: 'classDailyHealthSurveyList', ref:state._wechatUser, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ClassDailyHealthSurveySearch)
  }
  
  getClassDailyHealthSurveyCreateForm = () => {
   	const {ClassDailyHealthSurveyCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "classDailyHealthSurvey",
      data: state._wechatUser.classDailyHealthSurveyList,
      metaInfo: state._wechatUser.classDailyHealthSurveyListMetaInfo,
      count: state._wechatUser.classDailyHealthSurveyCount,
      returnURL: `/wechatUser/${state._wechatUser.id}/list`,
      currentPage: state._wechatUser.classDailyHealthSurveyCurrentPageNumber,
      searchFormParameters: state._wechatUser.classDailyHealthSurveySearchFormParameters,
      loading: state._wechatUser.loading,
      owner: { type: '_wechatUser', id: state._wechatUser.id, referenceName: 'creator', listName: 'classDailyHealthSurveyList', ref:state._wechatUser, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(ClassDailyHealthSurveyCreateForm)
  }
  
  getClassDailyHealthSurveyUpdateForm = () => {
    const userContext = null
  	const {ClassDailyHealthSurveyUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._wechatUser.selectedRows,
      role: "classDailyHealthSurvey",
      currentUpdateIndex: state._wechatUser.currentUpdateIndex,
      owner: { type: '_wechatUser', id: state._wechatUser.id, listName: 'classDailyHealthSurveyList', ref:state._wechatUser, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ClassDailyHealthSurveyUpdateForm)
  }

  getWechatLoginInfoSearch = () => {
    const {WechatLoginInfoSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('wechat_login_info','wechat_user.wechat_login_info_list',false),
      role: "wechatLoginInfo",
      data: state._wechatUser.wechatLoginInfoList,
      metaInfo: state._wechatUser.wechatLoginInfoListMetaInfo,
      count: state._wechatUser.wechatLoginInfoCount,
      returnURL: `/wechatUser/${state._wechatUser.id}/dashboard`,
      currentPage: state._wechatUser.wechatLoginInfoCurrentPageNumber,
      searchFormParameters: state._wechatUser.wechatLoginInfoSearchFormParameters,
      searchParameters: {...state._wechatUser.searchParameters},
      expandForm: state._wechatUser.expandForm,
      loading: state._wechatUser.loading,
      partialList: state._wechatUser.partialList,
      owner: { type: '_wechatUser', id: state._wechatUser.id, 
      referenceName: 'wechatUser', 
      listName: 'wechatLoginInfoList', ref:state._wechatUser, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(WechatLoginInfoSearch)
  }
  
  getWechatLoginInfoCreateForm = () => {
   	const {WechatLoginInfoCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "wechatLoginInfo",
      data: state._wechatUser.wechatLoginInfoList,
      metaInfo: state._wechatUser.wechatLoginInfoListMetaInfo,
      count: state._wechatUser.wechatLoginInfoCount,
      returnURL: `/wechatUser/${state._wechatUser.id}/list`,
      currentPage: state._wechatUser.wechatLoginInfoCurrentPageNumber,
      searchFormParameters: state._wechatUser.wechatLoginInfoSearchFormParameters,
      loading: state._wechatUser.loading,
      owner: { type: '_wechatUser', id: state._wechatUser.id, referenceName: 'wechatUser', listName: 'wechatLoginInfoList', ref:state._wechatUser, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(WechatLoginInfoCreateForm)
  }
  
  getWechatLoginInfoUpdateForm = () => {
    const userContext = null
  	const {WechatLoginInfoUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._wechatUser.selectedRows,
      role: "wechatLoginInfo",
      currentUpdateIndex: state._wechatUser.currentUpdateIndex,
      owner: { type: '_wechatUser', id: state._wechatUser.id, listName: 'wechatLoginInfoList', ref:state._wechatUser, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(WechatLoginInfoUpdateForm)
  }


  

 

  getPageTitle = () => {
    // const { location } = this.props
    // const { pathname } = location
    const title = '健康状态调查平台'
    return title
  }
 
  buildRouters = () =>{
  	const {WechatUserDashboard} = GlobalComponents
  	const {WechatUserPermission} = GlobalComponents
  	const {WechatUserProfile} = GlobalComponents
  	
  	
  	const routers=[
  	{path:"/wechatUser/:id/dashboard", component: WechatUserDashboard},
  	{path:"/wechatUser/:id/profile", component: WechatUserProfile},
  	{path:"/wechatUser/:id/permission", component: WechatUserPermission},
  	
  	
  	
  	{path:"/wechatUser/:id/list/guardianList", component: this.getGuardianSearch()},
  	{path:"/wechatUser/:id/list/guardianCreateForm", component: this.getGuardianCreateForm()},
  	{path:"/wechatUser/:id/list/guardianUpdateForm", component: this.getGuardianUpdateForm()},
   	
  	{path:"/wechatUser/:id/list/classQuestionList", component: this.getClassQuestionSearch()},
  	{path:"/wechatUser/:id/list/classQuestionCreateForm", component: this.getClassQuestionCreateForm()},
  	{path:"/wechatUser/:id/list/classQuestionUpdateForm", component: this.getClassQuestionUpdateForm()},
   	
  	{path:"/wechatUser/:id/list/classDailyHealthSurveyList", component: this.getClassDailyHealthSurveySearch()},
  	{path:"/wechatUser/:id/list/classDailyHealthSurveyCreateForm", component: this.getClassDailyHealthSurveyCreateForm()},
  	{path:"/wechatUser/:id/list/classDailyHealthSurveyUpdateForm", component: this.getClassDailyHealthSurveyUpdateForm()},
   	
  	{path:"/wechatUser/:id/list/wechatLoginInfoList", component: this.getWechatLoginInfoSearch()},
  	{path:"/wechatUser/:id/list/wechatLoginInfoCreateForm", component: this.getWechatLoginInfoCreateForm()},
  	{path:"/wechatUser/:id/list/wechatLoginInfoUpdateForm", component: this.getWechatLoginInfoUpdateForm()},
   	{path:"/wechatUser/:id/ChangeRequestType/:code", component: GlobalComponents.ChangeRequestStepForm},
    	
 	 
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

    const {searchLocalData}=GlobalComponents.WechatUserBase
	
    
     
     
     const layout = (
     <Layout>
 <Header style={{ position: 'fixed', zIndex: 1, width: '100%' }}>
          
        <Row type="flex" justify="start" align="bottom">
        
        <Col {...naviBarResponsiveStyle} >
            <Dropdown overlay= {this.getNavMenuItems(this.props.wechatUser)}>
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

        <div style={{backgroundColor:'black'}}  onClick={()=>hideSearchResult()}  >{searchLocalData(this.props.wechatUser,this.state.searchKeyword)}</div>

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
  wechatUser: state._wechatUser,
  ...state,
}))(WechatUserBizApp)



