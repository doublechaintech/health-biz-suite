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
import styles from './QuestionType.app.less'
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




class QuestionTypeBizApp extends React.PureComponent {
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
      return ['/questionType/']
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
               <Link to={`/questionType/${this.props.questionType.id}/dashboard`}><Icon type="dashboard" style={{marginRight:"20px"}}/><span>{appLocaleName(userContext,"Dashboard")}</span></Link>
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
      name: window.mtrans('question','question_type.question_list',false),
      role: "question",
      data: state._questionType.questionList,
      metaInfo: state._questionType.questionListMetaInfo,
      count: state._questionType.questionCount,
      returnURL: `/questionType/${state._questionType.id}/dashboard`,
      currentPage: state._questionType.questionCurrentPageNumber,
      searchFormParameters: state._questionType.questionSearchFormParameters,
      searchParameters: {...state._questionType.searchParameters},
      expandForm: state._questionType.expandForm,
      loading: state._questionType.loading,
      partialList: state._questionType.partialList,
      owner: { type: '_questionType', id: state._questionType.id, 
      referenceName: 'questionType', 
      listName: 'questionList', ref:state._questionType, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(QuestionSearch)
  }
  
  getQuestionCreateForm = () => {
   	const {QuestionCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "question",
      data: state._questionType.questionList,
      metaInfo: state._questionType.questionListMetaInfo,
      count: state._questionType.questionCount,
      returnURL: `/questionType/${state._questionType.id}/list`,
      currentPage: state._questionType.questionCurrentPageNumber,
      searchFormParameters: state._questionType.questionSearchFormParameters,
      loading: state._questionType.loading,
      owner: { type: '_questionType', id: state._questionType.id, referenceName: 'questionType', listName: 'questionList', ref:state._questionType, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(QuestionCreateForm)
  }
  
  getQuestionUpdateForm = () => {
    const userContext = null
  	const {QuestionUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._questionType.selectedRows,
      role: "question",
      currentUpdateIndex: state._questionType.currentUpdateIndex,
      owner: { type: '_questionType', id: state._questionType.id, listName: 'questionList', ref:state._questionType, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(QuestionUpdateForm)
  }

  getClassQuestionSearch = () => {
    const {ClassQuestionSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('class_question','question_type.class_question_list',false),
      role: "classQuestion",
      data: state._questionType.classQuestionList,
      metaInfo: state._questionType.classQuestionListMetaInfo,
      count: state._questionType.classQuestionCount,
      returnURL: `/questionType/${state._questionType.id}/dashboard`,
      currentPage: state._questionType.classQuestionCurrentPageNumber,
      searchFormParameters: state._questionType.classQuestionSearchFormParameters,
      searchParameters: {...state._questionType.searchParameters},
      expandForm: state._questionType.expandForm,
      loading: state._questionType.loading,
      partialList: state._questionType.partialList,
      owner: { type: '_questionType', id: state._questionType.id, 
      referenceName: 'questionType', 
      listName: 'classQuestionList', ref:state._questionType, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ClassQuestionSearch)
  }
  
  getClassQuestionCreateForm = () => {
   	const {ClassQuestionCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "classQuestion",
      data: state._questionType.classQuestionList,
      metaInfo: state._questionType.classQuestionListMetaInfo,
      count: state._questionType.classQuestionCount,
      returnURL: `/questionType/${state._questionType.id}/list`,
      currentPage: state._questionType.classQuestionCurrentPageNumber,
      searchFormParameters: state._questionType.classQuestionSearchFormParameters,
      loading: state._questionType.loading,
      owner: { type: '_questionType', id: state._questionType.id, referenceName: 'questionType', listName: 'classQuestionList', ref:state._questionType, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(ClassQuestionCreateForm)
  }
  
  getClassQuestionUpdateForm = () => {
    const userContext = null
  	const {ClassQuestionUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._questionType.selectedRows,
      role: "classQuestion",
      currentUpdateIndex: state._questionType.currentUpdateIndex,
      owner: { type: '_questionType', id: state._questionType.id, listName: 'classQuestionList', ref:state._questionType, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ClassQuestionUpdateForm)
  }

  getDailySurveyQuestionSearch = () => {
    const {DailySurveyQuestionSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('daily_survey_question','question_type.daily_survey_question_list',false),
      role: "dailySurveyQuestion",
      data: state._questionType.dailySurveyQuestionList,
      metaInfo: state._questionType.dailySurveyQuestionListMetaInfo,
      count: state._questionType.dailySurveyQuestionCount,
      returnURL: `/questionType/${state._questionType.id}/dashboard`,
      currentPage: state._questionType.dailySurveyQuestionCurrentPageNumber,
      searchFormParameters: state._questionType.dailySurveyQuestionSearchFormParameters,
      searchParameters: {...state._questionType.searchParameters},
      expandForm: state._questionType.expandForm,
      loading: state._questionType.loading,
      partialList: state._questionType.partialList,
      owner: { type: '_questionType', id: state._questionType.id, 
      referenceName: 'questionType', 
      listName: 'dailySurveyQuestionList', ref:state._questionType, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(DailySurveyQuestionSearch)
  }
  
  getDailySurveyQuestionCreateForm = () => {
   	const {DailySurveyQuestionCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "dailySurveyQuestion",
      data: state._questionType.dailySurveyQuestionList,
      metaInfo: state._questionType.dailySurveyQuestionListMetaInfo,
      count: state._questionType.dailySurveyQuestionCount,
      returnURL: `/questionType/${state._questionType.id}/list`,
      currentPage: state._questionType.dailySurveyQuestionCurrentPageNumber,
      searchFormParameters: state._questionType.dailySurveyQuestionSearchFormParameters,
      loading: state._questionType.loading,
      owner: { type: '_questionType', id: state._questionType.id, referenceName: 'questionType', listName: 'dailySurveyQuestionList', ref:state._questionType, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(DailySurveyQuestionCreateForm)
  }
  
  getDailySurveyQuestionUpdateForm = () => {
    const userContext = null
  	const {DailySurveyQuestionUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._questionType.selectedRows,
      role: "dailySurveyQuestion",
      currentUpdateIndex: state._questionType.currentUpdateIndex,
      owner: { type: '_questionType', id: state._questionType.id, listName: 'dailySurveyQuestionList', ref:state._questionType, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(DailySurveyQuestionUpdateForm)
  }


  

 

  getPageTitle = () => {
    // const { location } = this.props
    // const { pathname } = location
    const title = '健康状态调查平台'
    return title
  }
 
  buildRouters = () =>{
  	const {QuestionTypeDashboard} = GlobalComponents
  	const {QuestionTypePermission} = GlobalComponents
  	const {QuestionTypeProfile} = GlobalComponents
  	
  	
  	const routers=[
  	{path:"/questionType/:id/dashboard", component: QuestionTypeDashboard},
  	{path:"/questionType/:id/profile", component: QuestionTypeProfile},
  	{path:"/questionType/:id/permission", component: QuestionTypePermission},
  	
  	
  	
  	{path:"/questionType/:id/list/questionList", component: this.getQuestionSearch()},
  	{path:"/questionType/:id/list/questionCreateForm", component: this.getQuestionCreateForm()},
  	{path:"/questionType/:id/list/questionUpdateForm", component: this.getQuestionUpdateForm()},
   	
  	{path:"/questionType/:id/list/classQuestionList", component: this.getClassQuestionSearch()},
  	{path:"/questionType/:id/list/classQuestionCreateForm", component: this.getClassQuestionCreateForm()},
  	{path:"/questionType/:id/list/classQuestionUpdateForm", component: this.getClassQuestionUpdateForm()},
   	
  	{path:"/questionType/:id/list/dailySurveyQuestionList", component: this.getDailySurveyQuestionSearch()},
  	{path:"/questionType/:id/list/dailySurveyQuestionCreateForm", component: this.getDailySurveyQuestionCreateForm()},
  	{path:"/questionType/:id/list/dailySurveyQuestionUpdateForm", component: this.getDailySurveyQuestionUpdateForm()},
   	{path:"/questionType/:id/ChangeRequestType/:code", component: GlobalComponents.ChangeRequestStepForm},
    	
 	 
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

    const {searchLocalData}=GlobalComponents.QuestionTypeBase
	
    
     
     
     const layout = (
     <Layout>
 <Header style={{ position: 'fixed', zIndex: 1, width: '100%' }}>
          
        <Row type="flex" justify="start" align="bottom">
        
        <Col {...naviBarResponsiveStyle} >
            <Dropdown overlay= {this.getNavMenuItems(this.props.questionType)}>
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

        <div style={{backgroundColor:'black'}}  onClick={()=>hideSearchResult()}  >{searchLocalData(this.props.questionType,this.state.searchKeyword)}</div>

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
  questionType: state._questionType,
  ...state,
}))(QuestionTypeBizApp)



