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
import styles from './UserAnswer.app.less'
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




class UserAnswerBizApp extends React.PureComponent {
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
      return ['/userAnswer/']
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
               <Link to={`/userAnswer/${this.props.userAnswer.id}/dashboard`}><Icon type="dashboard" style={{marginRight:"20px"}}/><span>{appLocaleName(userContext,"Dashboard")}</span></Link>
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
  



  getDailyAnswerSearch = () => {
    const {DailyAnswerSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('daily_answer','user_answer.daily_answer_list',false),
      role: "dailyAnswer",
      data: state._userAnswer.dailyAnswerList,
      metaInfo: state._userAnswer.dailyAnswerListMetaInfo,
      count: state._userAnswer.dailyAnswerCount,
      returnURL: `/userAnswer/${state._userAnswer.id}/dashboard`,
      currentPage: state._userAnswer.dailyAnswerCurrentPageNumber,
      searchFormParameters: state._userAnswer.dailyAnswerSearchFormParameters,
      searchParameters: {...state._userAnswer.searchParameters},
      expandForm: state._userAnswer.expandForm,
      loading: state._userAnswer.loading,
      partialList: state._userAnswer.partialList,
      owner: { type: '_userAnswer', id: state._userAnswer.id, 
      referenceName: 'userAnswer', 
      listName: 'dailyAnswerList', ref:state._userAnswer, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(DailyAnswerSearch)
  }
  
  getDailyAnswerCreateForm = () => {
   	const {DailyAnswerCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "dailyAnswer",
      data: state._userAnswer.dailyAnswerList,
      metaInfo: state._userAnswer.dailyAnswerListMetaInfo,
      count: state._userAnswer.dailyAnswerCount,
      returnURL: `/userAnswer/${state._userAnswer.id}/list`,
      currentPage: state._userAnswer.dailyAnswerCurrentPageNumber,
      searchFormParameters: state._userAnswer.dailyAnswerSearchFormParameters,
      loading: state._userAnswer.loading,
      owner: { type: '_userAnswer', id: state._userAnswer.id, referenceName: 'userAnswer', listName: 'dailyAnswerList', ref:state._userAnswer, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(DailyAnswerCreateForm)
  }
  
  getDailyAnswerUpdateForm = () => {
    const userContext = null
  	const {DailyAnswerUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._userAnswer.selectedRows,
      role: "dailyAnswer",
      currentUpdateIndex: state._userAnswer.currentUpdateIndex,
      owner: { type: '_userAnswer', id: state._userAnswer.id, listName: 'dailyAnswerList', ref:state._userAnswer, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(DailyAnswerUpdateForm)
  }

  getAnswerQuestionSearch = () => {
    const {AnswerQuestionSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('answer_question','user_answer.answer_question_list',false),
      role: "answerQuestion",
      data: state._userAnswer.answerQuestionList,
      metaInfo: state._userAnswer.answerQuestionListMetaInfo,
      count: state._userAnswer.answerQuestionCount,
      returnURL: `/userAnswer/${state._userAnswer.id}/dashboard`,
      currentPage: state._userAnswer.answerQuestionCurrentPageNumber,
      searchFormParameters: state._userAnswer.answerQuestionSearchFormParameters,
      searchParameters: {...state._userAnswer.searchParameters},
      expandForm: state._userAnswer.expandForm,
      loading: state._userAnswer.loading,
      partialList: state._userAnswer.partialList,
      owner: { type: '_userAnswer', id: state._userAnswer.id, 
      referenceName: 'userAnswer', 
      listName: 'answerQuestionList', ref:state._userAnswer, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(AnswerQuestionSearch)
  }
  
  getAnswerQuestionCreateForm = () => {
   	const {AnswerQuestionCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "answerQuestion",
      data: state._userAnswer.answerQuestionList,
      metaInfo: state._userAnswer.answerQuestionListMetaInfo,
      count: state._userAnswer.answerQuestionCount,
      returnURL: `/userAnswer/${state._userAnswer.id}/list`,
      currentPage: state._userAnswer.answerQuestionCurrentPageNumber,
      searchFormParameters: state._userAnswer.answerQuestionSearchFormParameters,
      loading: state._userAnswer.loading,
      owner: { type: '_userAnswer', id: state._userAnswer.id, referenceName: 'userAnswer', listName: 'answerQuestionList', ref:state._userAnswer, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(AnswerQuestionCreateForm)
  }
  
  getAnswerQuestionUpdateForm = () => {
    const userContext = null
  	const {AnswerQuestionUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._userAnswer.selectedRows,
      role: "answerQuestion",
      currentUpdateIndex: state._userAnswer.currentUpdateIndex,
      owner: { type: '_userAnswer', id: state._userAnswer.id, listName: 'answerQuestionList', ref:state._userAnswer, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(AnswerQuestionUpdateForm)
  }


  

 

  getPageTitle = () => {
    // const { location } = this.props
    // const { pathname } = location
    const title = '健康状态调查平台'
    return title
  }
 
  buildRouters = () =>{
  	const {UserAnswerDashboard} = GlobalComponents
  	const {UserAnswerPermission} = GlobalComponents
  	const {UserAnswerProfile} = GlobalComponents
  	
  	
  	const routers=[
  	{path:"/userAnswer/:id/dashboard", component: UserAnswerDashboard},
  	{path:"/userAnswer/:id/profile", component: UserAnswerProfile},
  	{path:"/userAnswer/:id/permission", component: UserAnswerPermission},
  	
  	
  	
  	{path:"/userAnswer/:id/list/dailyAnswerList", component: this.getDailyAnswerSearch()},
  	{path:"/userAnswer/:id/list/dailyAnswerCreateForm", component: this.getDailyAnswerCreateForm()},
  	{path:"/userAnswer/:id/list/dailyAnswerUpdateForm", component: this.getDailyAnswerUpdateForm()},
   	
  	{path:"/userAnswer/:id/list/answerQuestionList", component: this.getAnswerQuestionSearch()},
  	{path:"/userAnswer/:id/list/answerQuestionCreateForm", component: this.getAnswerQuestionCreateForm()},
  	{path:"/userAnswer/:id/list/answerQuestionUpdateForm", component: this.getAnswerQuestionUpdateForm()},
   	{path:"/userAnswer/:id/ChangeRequestType/:code", component: GlobalComponents.ChangeRequestStepForm},
    	
 	 
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

    const {searchLocalData}=GlobalComponents.UserAnswerBase
	
    
     
     
     const layout = (
     <Layout>
 <Header style={{ position: 'fixed', zIndex: 1, width: '100%' }}>
          
        <Row type="flex" justify="start" align="bottom">
        
        <Col {...naviBarResponsiveStyle} >
            <Dropdown overlay= {this.getNavMenuItems(this.props.userAnswer)}>
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

        <div style={{backgroundColor:'black'}}  onClick={()=>hideSearchResult()}  >{searchLocalData(this.props.userAnswer,this.state.searchKeyword)}</div>

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
  userAnswer: state._userAnswer,
  ...state,
}))(UserAnswerBizApp)



