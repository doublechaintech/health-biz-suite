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
import styles from './Organization.app.less'
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




class OrganizationBizApp extends React.PureComponent {
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
      return ['/organization/']
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
               <Link to={`/organization/${this.props.organization.id}/dashboard`}><Icon type="dashboard" style={{marginRight:"20px"}}/><span>{appLocaleName(userContext,"Dashboard")}</span></Link>
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
  



  getQuestionSearch = () => {
    const {QuestionSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('question','organization.question_list',false),
      role: "question",
      data: state._organization.questionList,
      metaInfo: state._organization.questionListMetaInfo,
      count: state._organization.questionCount,
      returnURL: `/organization/${state._organization.id}/dashboard`,
      currentPage: state._organization.questionCurrentPageNumber,
      searchFormParameters: state._organization.questionSearchFormParameters,
      searchParameters: {...state._organization.searchParameters},
      expandForm: state._organization.expandForm,
      loading: state._organization.loading,
      partialList: state._organization.partialList,
      owner: { type: '_organization', id: state._organization.id, 
      referenceName: 'organization', 
      listName: 'questionList', ref:state._organization, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(QuestionSearch)
  }
  
  getQuestionCreateForm = () => {
   	const {QuestionCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "question",
      data: state._organization.questionList,
      metaInfo: state._organization.questionListMetaInfo,
      count: state._organization.questionCount,
      returnURL: `/organization/${state._organization.id}/list`,
      currentPage: state._organization.questionCurrentPageNumber,
      searchFormParameters: state._organization.questionSearchFormParameters,
      loading: state._organization.loading,
      owner: { type: '_organization', id: state._organization.id, referenceName: 'organization', listName: 'questionList', ref:state._organization, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(QuestionCreateForm)
  }
  
  getQuestionUpdateForm = () => {
    const userContext = null
  	const {QuestionUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._organization.selectedRows,
      role: "question",
      currentUpdateIndex: state._organization.currentUpdateIndex,
      owner: { type: '_organization', id: state._organization.id, listName: 'questionList', ref:state._organization, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(QuestionUpdateForm)
  }

  getDailyHealthSurveySearch = () => {
    const {DailyHealthSurveySearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('daily_health_survey','organization.daily_health_survey_list',false),
      role: "dailyHealthSurvey",
      data: state._organization.dailyHealthSurveyList,
      metaInfo: state._organization.dailyHealthSurveyListMetaInfo,
      count: state._organization.dailyHealthSurveyCount,
      returnURL: `/organization/${state._organization.id}/dashboard`,
      currentPage: state._organization.dailyHealthSurveyCurrentPageNumber,
      searchFormParameters: state._organization.dailyHealthSurveySearchFormParameters,
      searchParameters: {...state._organization.searchParameters},
      expandForm: state._organization.expandForm,
      loading: state._organization.loading,
      partialList: state._organization.partialList,
      owner: { type: '_organization', id: state._organization.id, 
      referenceName: 'organization', 
      listName: 'dailyHealthSurveyList', ref:state._organization, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(DailyHealthSurveySearch)
  }
  
  getDailyHealthSurveyCreateForm = () => {
   	const {DailyHealthSurveyCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "dailyHealthSurvey",
      data: state._organization.dailyHealthSurveyList,
      metaInfo: state._organization.dailyHealthSurveyListMetaInfo,
      count: state._organization.dailyHealthSurveyCount,
      returnURL: `/organization/${state._organization.id}/list`,
      currentPage: state._organization.dailyHealthSurveyCurrentPageNumber,
      searchFormParameters: state._organization.dailyHealthSurveySearchFormParameters,
      loading: state._organization.loading,
      owner: { type: '_organization', id: state._organization.id, referenceName: 'organization', listName: 'dailyHealthSurveyList', ref:state._organization, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(DailyHealthSurveyCreateForm)
  }
  
  getDailyHealthSurveyUpdateForm = () => {
    const userContext = null
  	const {DailyHealthSurveyUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._organization.selectedRows,
      role: "dailyHealthSurvey",
      currentUpdateIndex: state._organization.currentUpdateIndex,
      owner: { type: '_organization', id: state._organization.id, listName: 'dailyHealthSurveyList', ref:state._organization, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(DailyHealthSurveyUpdateForm)
  }

  getUserDailyAnswerSearch = () => {
    const {UserDailyAnswerSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('user_daily_answer','organization.user_daily_answer_list',false),
      role: "userDailyAnswer",
      data: state._organization.userDailyAnswerList,
      metaInfo: state._organization.userDailyAnswerListMetaInfo,
      count: state._organization.userDailyAnswerCount,
      returnURL: `/organization/${state._organization.id}/dashboard`,
      currentPage: state._organization.userDailyAnswerCurrentPageNumber,
      searchFormParameters: state._organization.userDailyAnswerSearchFormParameters,
      searchParameters: {...state._organization.searchParameters},
      expandForm: state._organization.expandForm,
      loading: state._organization.loading,
      partialList: state._organization.partialList,
      owner: { type: '_organization', id: state._organization.id, 
      referenceName: 'organization', 
      listName: 'userDailyAnswerList', ref:state._organization, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(UserDailyAnswerSearch)
  }
  
  getUserDailyAnswerCreateForm = () => {
   	const {UserDailyAnswerCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "userDailyAnswer",
      data: state._organization.userDailyAnswerList,
      metaInfo: state._organization.userDailyAnswerListMetaInfo,
      count: state._organization.userDailyAnswerCount,
      returnURL: `/organization/${state._organization.id}/list`,
      currentPage: state._organization.userDailyAnswerCurrentPageNumber,
      searchFormParameters: state._organization.userDailyAnswerSearchFormParameters,
      loading: state._organization.loading,
      owner: { type: '_organization', id: state._organization.id, referenceName: 'organization', listName: 'userDailyAnswerList', ref:state._organization, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(UserDailyAnswerCreateForm)
  }
  
  getUserDailyAnswerUpdateForm = () => {
    const userContext = null
  	const {UserDailyAnswerUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._organization.selectedRows,
      role: "userDailyAnswer",
      currentUpdateIndex: state._organization.currentUpdateIndex,
      owner: { type: '_organization', id: state._organization.id, listName: 'userDailyAnswerList', ref:state._organization, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(UserDailyAnswerUpdateForm)
  }

  getWechatUserSearch = () => {
    const {WechatUserSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('wechat_user','organization.wechat_user_list',false),
      role: "wechatUser",
      data: state._organization.wechatUserList,
      metaInfo: state._organization.wechatUserListMetaInfo,
      count: state._organization.wechatUserCount,
      returnURL: `/organization/${state._organization.id}/dashboard`,
      currentPage: state._organization.wechatUserCurrentPageNumber,
      searchFormParameters: state._organization.wechatUserSearchFormParameters,
      searchParameters: {...state._organization.searchParameters},
      expandForm: state._organization.expandForm,
      loading: state._organization.loading,
      partialList: state._organization.partialList,
      owner: { type: '_organization', id: state._organization.id, 
      referenceName: 'organization', 
      listName: 'wechatUserList', ref:state._organization, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(WechatUserSearch)
  }
  
  getWechatUserCreateForm = () => {
   	const {WechatUserCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "wechatUser",
      data: state._organization.wechatUserList,
      metaInfo: state._organization.wechatUserListMetaInfo,
      count: state._organization.wechatUserCount,
      returnURL: `/organization/${state._organization.id}/list`,
      currentPage: state._organization.wechatUserCurrentPageNumber,
      searchFormParameters: state._organization.wechatUserSearchFormParameters,
      loading: state._organization.loading,
      owner: { type: '_organization', id: state._organization.id, referenceName: 'organization', listName: 'wechatUserList', ref:state._organization, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(WechatUserCreateForm)
  }
  
  getWechatUserUpdateForm = () => {
    const userContext = null
  	const {WechatUserUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._organization.selectedRows,
      role: "wechatUser",
      currentUpdateIndex: state._organization.currentUpdateIndex,
      owner: { type: '_organization', id: state._organization.id, listName: 'wechatUserList', ref:state._organization, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(WechatUserUpdateForm)
  }


  

 

  getPageTitle = () => {
    // const { location } = this.props
    // const { pathname } = location
    const title = '健康状态调查平台'
    return title
  }
 
  buildRouters = () =>{
  	const {OrganizationDashboard} = GlobalComponents
  	const {OrganizationPermission} = GlobalComponents
  	const {OrganizationProfile} = GlobalComponents
  	
  	
  	const routers=[
  	{path:"/organization/:id/dashboard", component: OrganizationDashboard},
  	{path:"/organization/:id/profile", component: OrganizationProfile},
  	{path:"/organization/:id/permission", component: OrganizationPermission},
  	
  	
  	
  	{path:"/organization/:id/list/questionList", component: this.getQuestionSearch()},
  	{path:"/organization/:id/list/questionCreateForm", component: this.getQuestionCreateForm()},
  	{path:"/organization/:id/list/questionUpdateForm", component: this.getQuestionUpdateForm()},
   	
  	{path:"/organization/:id/list/dailyHealthSurveyList", component: this.getDailyHealthSurveySearch()},
  	{path:"/organization/:id/list/dailyHealthSurveyCreateForm", component: this.getDailyHealthSurveyCreateForm()},
  	{path:"/organization/:id/list/dailyHealthSurveyUpdateForm", component: this.getDailyHealthSurveyUpdateForm()},
   	
  	{path:"/organization/:id/list/userDailyAnswerList", component: this.getUserDailyAnswerSearch()},
  	{path:"/organization/:id/list/userDailyAnswerCreateForm", component: this.getUserDailyAnswerCreateForm()},
  	{path:"/organization/:id/list/userDailyAnswerUpdateForm", component: this.getUserDailyAnswerUpdateForm()},
   	
  	{path:"/organization/:id/list/wechatUserList", component: this.getWechatUserSearch()},
  	{path:"/organization/:id/list/wechatUserCreateForm", component: this.getWechatUserCreateForm()},
  	{path:"/organization/:id/list/wechatUserUpdateForm", component: this.getWechatUserUpdateForm()},
   	{path:"/organization/:id/ChangeRequestType/:code", component: GlobalComponents.ChangeRequestStepForm},
    	
 	 
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

    const {searchLocalData}=GlobalComponents.OrganizationBase
	
    
     
     
     const layout = (
     <Layout>
 <Header style={{ position: 'fixed', zIndex: 1, width: '100%' }}>
          
        <Row type="flex" justify="start" align="bottom">
        
        <Col {...naviBarResponsiveStyle} >
            <Dropdown overlay= {this.getNavMenuItems(this.props.organization)}>
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

        <div style={{backgroundColor:'black'}}  onClick={()=>hideSearchResult()}  >{searchLocalData(this.props.organization,this.state.searchKeyword)}</div>

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
  organization: state._organization,
  ...state,
}))(OrganizationBizApp)



