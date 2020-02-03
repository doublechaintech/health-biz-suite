import React from 'react';
import PropTypes from 'prop-types';
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
  AutoComplete,
  Row,
  Col,
  Input,
  Button,
} from 'antd';
import TopMenu from '../../launcher/TopMenu';
import DocumentTitle from 'react-document-title';
import { connect } from 'dva';
import { Link, Route, Redirect, Switch } from 'dva/router';
import moment from 'moment';
import groupBy from 'lodash/groupBy';
import { ContainerQuery } from 'react-container-query';
import classNames from 'classnames';
import styles from './User.app.less';
import { sessionObject } from '../../utils/utils';

import HeaderSearch from '../../components/HeaderSearch';
import NoticeIcon from '../../components/NoticeIcon';
import GlobalFooter from '../../components/GlobalFooter';

import GlobalComponents from '../../custcomponents';

import PermissionSettingService from '../../permission/PermissionSetting.service';
import appLocaleName from '../../common/Locale.tool';
import BizAppTool from '../../common/BizApp.tool';

const { Header, Sider, Content } = Layout;
const { SubMenu } = Menu;
const {
  defaultFilteredNoGroupMenuItems,
  defaultFilteredMenuItemsGroup,
  defaultRenderMenuItem,
} = BizAppTool;

const filteredNoGroupMenuItems = defaultFilteredNoGroupMenuItems;
const filteredMenuItemsGroup = defaultFilteredMenuItemsGroup;
const renderMenuItem = defaultRenderMenuItem;

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
};

class UserBizApp extends React.PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      openKeys: this.getDefaultCollapsedSubMenus(props),
      showSearch: false,
      searchKeyword: '',
    };
  }

  componentDidMount() {}
  componentWillUnmount() {
    clearTimeout(this.resizeTimeout);
  }
  onCollapse = collapsed => {
    this.props.dispatch({
      type: 'global/changeLayoutCollapsed',
      payload: collapsed,
    });
  };

  getDefaultCollapsedSubMenus = props => {
    const currentMenuSelectedKeys = [...this.getCurrentMenuSelectedKeys(props)];
    currentMenuSelectedKeys.splice(-1, 1);
    if (currentMenuSelectedKeys.length === 0) {
      return ['/user/'];
    }
    return currentMenuSelectedKeys;
  };
  getCurrentMenuSelectedKeys = props => {
    const {
      location: { pathname },
    } =
      props || this.props;
    const keys = pathname.split('/').slice(1);
    if (keys.length === 1 && keys[0] === '') {
      return [this.menus[0].key];
    }
    return keys;
  };

  getNavMenuItems = targetObject => {
    const menuData = sessionObject('menuData');
    const targetApp = sessionObject('targetApp');
    const { objectId } = targetApp;
    const userContext = null;
    return (
      <Menu
        theme="dark"
        mode="inline"
        onOpenChange={this.handleOpenChange}
        defaultOpenKeys={['firstOne']}
        style={{ width: '456px' }}
      >
        <Menu.Item key="dashboard">
          <Link to={`/user/${this.props.user.id}/dashboard`}>
            <Icon type="dashboard" style={{ marginRight: '20px' }} />
            <span>{appLocaleName(userContext, 'Dashboard')}</span>
          </Link>
        </Menu.Item>

        {filteredNoGroupMenuItems(targetObject, this).map(item => renderMenuItem(item))}
        {filteredMenuItemsGroup(targetObject, this).map((groupedMenuItem, index) => {
          return (
            <SubMenu
              key={`vg${index}`}
              title={
                <span>
                  <Icon type="folder" style={{ marginRight: '20px' }} />
                  <span>{`${groupedMenuItem.viewGroup}`}</span>
                </span>
              }
            >
              {groupedMenuItem.subItems.map(item => renderMenuItem(item))}
            </SubMenu>
          );
        })}
      </Menu>
    );
  };

  getTeacherSearch = () => {
    const { TeacherSearch } = GlobalComponents;
    const userContext = null;
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('teacher', 'user.teacher_list', false),
      role: 'teacher',
      data: state._user.teacherList,
      metaInfo: state._user.teacherListMetaInfo,
      count: state._user.teacherCount,
      returnURL: `/user/${state._user.id}/dashboard`,
      currentPage: state._user.teacherCurrentPageNumber,
      searchFormParameters: state._user.teacherSearchFormParameters,
      searchParameters: { ...state._user.searchParameters },
      expandForm: state._user.expandForm,
      loading: state._user.loading,
      partialList: state._user.partialList,
      owner: {
        type: '_user',
        id: state._user.id,
        referenceName: 'user',
        listName: 'teacherList',
        ref: state._user,
        listDisplayName: appLocaleName(userContext, 'List'),
      }, // this is for model namespace and
    }))(TeacherSearch);
  };

  getTeacherCreateForm = () => {
    const { TeacherCreateForm } = GlobalComponents;
    const userContext = null;
    return connect(state => ({
      rule: state.rule,
      role: 'teacher',
      data: state._user.teacherList,
      metaInfo: state._user.teacherListMetaInfo,
      count: state._user.teacherCount,
      returnURL: `/user/${state._user.id}/list`,
      currentPage: state._user.teacherCurrentPageNumber,
      searchFormParameters: state._user.teacherSearchFormParameters,
      loading: state._user.loading,
      owner: {
        type: '_user',
        id: state._user.id,
        referenceName: 'user',
        listName: 'teacherList',
        ref: state._user,
        listDisplayName: appLocaleName(userContext, 'List'),
      }, // this is for model namespace and
    }))(TeacherCreateForm);
  };

  getTeacherUpdateForm = () => {
    const userContext = null;
    const { TeacherUpdateForm } = GlobalComponents;
    return connect(state => ({
      selectedRows: state._user.selectedRows,
      role: 'teacher',
      currentUpdateIndex: state._user.currentUpdateIndex,
      owner: {
        type: '_user',
        id: state._user.id,
        listName: 'teacherList',
        ref: state._user,
        listDisplayName: appLocaleName(userContext, 'List'),
      }, // this is for model namespace and
    }))(TeacherUpdateForm);
  };

  getStudentSearch = () => {
    const { StudentSearch } = GlobalComponents;
    const userContext = null;
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('student', 'user.student_list', false),
      role: 'student',
      data: state._user.studentList,
      metaInfo: state._user.studentListMetaInfo,
      count: state._user.studentCount,
      returnURL: `/user/${state._user.id}/dashboard`,
      currentPage: state._user.studentCurrentPageNumber,
      searchFormParameters: state._user.studentSearchFormParameters,
      searchParameters: { ...state._user.searchParameters },
      expandForm: state._user.expandForm,
      loading: state._user.loading,
      partialList: state._user.partialList,
      owner: {
        type: '_user',
        id: state._user.id,
        referenceName: 'user',
        listName: 'studentList',
        ref: state._user,
        listDisplayName: appLocaleName(userContext, 'List'),
      }, // this is for model namespace and
    }))(StudentSearch);
  };

  getStudentCreateForm = () => {
    const { StudentCreateForm } = GlobalComponents;
    const userContext = null;
    return connect(state => ({
      rule: state.rule,
      role: 'student',
      data: state._user.studentList,
      metaInfo: state._user.studentListMetaInfo,
      count: state._user.studentCount,
      returnURL: `/user/${state._user.id}/list`,
      currentPage: state._user.studentCurrentPageNumber,
      searchFormParameters: state._user.studentSearchFormParameters,
      loading: state._user.loading,
      owner: {
        type: '_user',
        id: state._user.id,
        referenceName: 'user',
        listName: 'studentList',
        ref: state._user,
        listDisplayName: appLocaleName(userContext, 'List'),
      }, // this is for model namespace and
    }))(StudentCreateForm);
  };

  getStudentUpdateForm = () => {
    const userContext = null;
    const { StudentUpdateForm } = GlobalComponents;
    return connect(state => ({
      selectedRows: state._user.selectedRows,
      role: 'student',
      currentUpdateIndex: state._user.currentUpdateIndex,
      owner: {
        type: '_user',
        id: state._user.id,
        listName: 'studentList',
        ref: state._user,
        listDisplayName: appLocaleName(userContext, 'List'),
      }, // this is for model namespace and
    }))(StudentUpdateForm);
  };

  getQuestionSearch = () => {
    const { QuestionSearch } = GlobalComponents;
    const userContext = null;
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('question', 'user.question_list', false),
      role: 'question',
      data: state._user.questionList,
      metaInfo: state._user.questionListMetaInfo,
      count: state._user.questionCount,
      returnURL: `/user/${state._user.id}/dashboard`,
      currentPage: state._user.questionCurrentPageNumber,
      searchFormParameters: state._user.questionSearchFormParameters,
      searchParameters: { ...state._user.searchParameters },
      expandForm: state._user.expandForm,
      loading: state._user.loading,
      partialList: state._user.partialList,
      owner: {
        type: '_user',
        id: state._user.id,
        referenceName: 'creator',
        listName: 'questionList',
        ref: state._user,
        listDisplayName: appLocaleName(userContext, 'List'),
      }, // this is for model namespace and
    }))(QuestionSearch);
  };

  getQuestionCreateForm = () => {
    const { QuestionCreateForm } = GlobalComponents;
    const userContext = null;
    return connect(state => ({
      rule: state.rule,
      role: 'question',
      data: state._user.questionList,
      metaInfo: state._user.questionListMetaInfo,
      count: state._user.questionCount,
      returnURL: `/user/${state._user.id}/list`,
      currentPage: state._user.questionCurrentPageNumber,
      searchFormParameters: state._user.questionSearchFormParameters,
      loading: state._user.loading,
      owner: {
        type: '_user',
        id: state._user.id,
        referenceName: 'creator',
        listName: 'questionList',
        ref: state._user,
        listDisplayName: appLocaleName(userContext, 'List'),
      }, // this is for model namespace and
    }))(QuestionCreateForm);
  };

  getQuestionUpdateForm = () => {
    const userContext = null;
    const { QuestionUpdateForm } = GlobalComponents;
    return connect(state => ({
      selectedRows: state._user.selectedRows,
      role: 'question',
      currentUpdateIndex: state._user.currentUpdateIndex,
      owner: {
        type: '_user',
        id: state._user.id,
        listName: 'questionList',
        ref: state._user,
        listDisplayName: appLocaleName(userContext, 'List'),
      }, // this is for model namespace and
    }))(QuestionUpdateForm);
  };

  getClassDailyHealthSurveySearch = () => {
    const { ClassDailyHealthSurveySearch } = GlobalComponents;
    const userContext = null;
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans(
        'class_daily_health_survey',
        'user.class_daily_health_survey_list',
        false
      ),
      role: 'classDailyHealthSurvey',
      data: state._user.classDailyHealthSurveyList,
      metaInfo: state._user.classDailyHealthSurveyListMetaInfo,
      count: state._user.classDailyHealthSurveyCount,
      returnURL: `/user/${state._user.id}/dashboard`,
      currentPage: state._user.classDailyHealthSurveyCurrentPageNumber,
      searchFormParameters: state._user.classDailyHealthSurveySearchFormParameters,
      searchParameters: { ...state._user.searchParameters },
      expandForm: state._user.expandForm,
      loading: state._user.loading,
      partialList: state._user.partialList,
      owner: {
        type: '_user',
        id: state._user.id,
        referenceName: 'creator',
        listName: 'classDailyHealthSurveyList',
        ref: state._user,
        listDisplayName: appLocaleName(userContext, 'List'),
      }, // this is for model namespace and
    }))(ClassDailyHealthSurveySearch);
  };

  getClassDailyHealthSurveyCreateForm = () => {
    const { ClassDailyHealthSurveyCreateForm } = GlobalComponents;
    const userContext = null;
    return connect(state => ({
      rule: state.rule,
      role: 'classDailyHealthSurvey',
      data: state._user.classDailyHealthSurveyList,
      metaInfo: state._user.classDailyHealthSurveyListMetaInfo,
      count: state._user.classDailyHealthSurveyCount,
      returnURL: `/user/${state._user.id}/list`,
      currentPage: state._user.classDailyHealthSurveyCurrentPageNumber,
      searchFormParameters: state._user.classDailyHealthSurveySearchFormParameters,
      loading: state._user.loading,
      owner: {
        type: '_user',
        id: state._user.id,
        referenceName: 'creator',
        listName: 'classDailyHealthSurveyList',
        ref: state._user,
        listDisplayName: appLocaleName(userContext, 'List'),
      }, // this is for model namespace and
    }))(ClassDailyHealthSurveyCreateForm);
  };

  getClassDailyHealthSurveyUpdateForm = () => {
    const userContext = null;
    const { ClassDailyHealthSurveyUpdateForm } = GlobalComponents;
    return connect(state => ({
      selectedRows: state._user.selectedRows,
      role: 'classDailyHealthSurvey',
      currentUpdateIndex: state._user.currentUpdateIndex,
      owner: {
        type: '_user',
        id: state._user.id,
        listName: 'classDailyHealthSurveyList',
        ref: state._user,
        listDisplayName: appLocaleName(userContext, 'List'),
      }, // this is for model namespace and
    }))(ClassDailyHealthSurveyUpdateForm);
  };

  getWechatLoginInfoSearch = () => {
    const { WechatLoginInfoSearch } = GlobalComponents;
    const userContext = null;
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans('wechat_login_info', 'user.wechat_login_info_list', false),
      role: 'wechatLoginInfo',
      data: state._user.wechatLoginInfoList,
      metaInfo: state._user.wechatLoginInfoListMetaInfo,
      count: state._user.wechatLoginInfoCount,
      returnURL: `/user/${state._user.id}/dashboard`,
      currentPage: state._user.wechatLoginInfoCurrentPageNumber,
      searchFormParameters: state._user.wechatLoginInfoSearchFormParameters,
      searchParameters: { ...state._user.searchParameters },
      expandForm: state._user.expandForm,
      loading: state._user.loading,
      partialList: state._user.partialList,
      owner: {
        type: '_user',
        id: state._user.id,
        referenceName: 'user',
        listName: 'wechatLoginInfoList',
        ref: state._user,
        listDisplayName: appLocaleName(userContext, 'List'),
      }, // this is for model namespace and
    }))(WechatLoginInfoSearch);
  };

  getWechatLoginInfoCreateForm = () => {
    const { WechatLoginInfoCreateForm } = GlobalComponents;
    const userContext = null;
    return connect(state => ({
      rule: state.rule,
      role: 'wechatLoginInfo',
      data: state._user.wechatLoginInfoList,
      metaInfo: state._user.wechatLoginInfoListMetaInfo,
      count: state._user.wechatLoginInfoCount,
      returnURL: `/user/${state._user.id}/list`,
      currentPage: state._user.wechatLoginInfoCurrentPageNumber,
      searchFormParameters: state._user.wechatLoginInfoSearchFormParameters,
      loading: state._user.loading,
      owner: {
        type: '_user',
        id: state._user.id,
        referenceName: 'user',
        listName: 'wechatLoginInfoList',
        ref: state._user,
        listDisplayName: appLocaleName(userContext, 'List'),
      }, // this is for model namespace and
    }))(WechatLoginInfoCreateForm);
  };

  getWechatLoginInfoUpdateForm = () => {
    const userContext = null;
    const { WechatLoginInfoUpdateForm } = GlobalComponents;
    return connect(state => ({
      selectedRows: state._user.selectedRows,
      role: 'wechatLoginInfo',
      currentUpdateIndex: state._user.currentUpdateIndex,
      owner: {
        type: '_user',
        id: state._user.id,
        listName: 'wechatLoginInfoList',
        ref: state._user,
        listDisplayName: appLocaleName(userContext, 'List'),
      }, // this is for model namespace and
    }))(WechatLoginInfoUpdateForm);
  };

  getPageTitle = () => {
    // const { location } = this.props
    // const { pathname } = location
    const title = '健康状态调查平台';
    return title;
  };

  buildRouters = () => {
    const { UserDashboard } = GlobalComponents;
    const { UserPermission } = GlobalComponents;
    const { UserProfile } = GlobalComponents;

    const routers = [
      { path: '/user/:id/dashboard', component: UserDashboard },
      { path: '/user/:id/profile', component: UserProfile },
      { path: '/user/:id/permission', component: UserPermission },

      { path: '/user/:id/list/teacherList', component: this.getTeacherSearch() },
      { path: '/user/:id/list/teacherCreateForm', component: this.getTeacherCreateForm() },
      { path: '/user/:id/list/teacherUpdateForm', component: this.getTeacherUpdateForm() },

      { path: '/user/:id/list/studentList', component: this.getStudentSearch() },
      { path: '/user/:id/list/studentCreateForm', component: this.getStudentCreateForm() },
      { path: '/user/:id/list/studentUpdateForm', component: this.getStudentUpdateForm() },

      { path: '/user/:id/list/questionList', component: this.getQuestionSearch() },
      { path: '/user/:id/list/questionCreateForm', component: this.getQuestionCreateForm() },
      { path: '/user/:id/list/questionUpdateForm', component: this.getQuestionUpdateForm() },

      {
        path: '/user/:id/list/classDailyHealthSurveyList',
        component: this.getClassDailyHealthSurveySearch(),
      },
      {
        path: '/user/:id/list/classDailyHealthSurveyCreateForm',
        component: this.getClassDailyHealthSurveyCreateForm(),
      },
      {
        path: '/user/:id/list/classDailyHealthSurveyUpdateForm',
        component: this.getClassDailyHealthSurveyUpdateForm(),
      },

      { path: '/user/:id/list/wechatLoginInfoList', component: this.getWechatLoginInfoSearch() },
      {
        path: '/user/:id/list/wechatLoginInfoCreateForm',
        component: this.getWechatLoginInfoCreateForm(),
      },
      {
        path: '/user/:id/list/wechatLoginInfoUpdateForm',
        component: this.getWechatLoginInfoUpdateForm(),
      },
      {
        path: '/user/:id/ChangeRequestType/:code',
        component: GlobalComponents.ChangeRequestStepForm,
      },
    ];

    const { extraRoutesFunc } = this.props;
    const extraRoutes = extraRoutesFunc ? extraRoutesFunc() : [];
    const finalRoutes = routers.concat(extraRoutes);

    return (
      <Switch>
        {finalRoutes.map(item => (
          <Route key={item.path} path={item.path} component={item.component} />
        ))}
      </Switch>
    );
  };

  handleOpenChange = openKeys => {
    const latestOpenKey = openKeys.find(key => this.state.openKeys.indexOf(key) === -1);
    this.setState({
      openKeys: latestOpenKey ? [latestOpenKey] : [],
    });
  };
  toggle = () => {
    const { collapsed } = this.props;
    this.props.dispatch({
      type: 'global/changeLayoutCollapsed',
      payload: !collapsed,
    });
  };
  logout = () => {
    console.log('log out called');
    this.props.dispatch({ type: 'launcher/signOut' });
  };
  render() {
    // const { collapsed, fetchingNotices,loading } = this.props
    const { collapsed } = this.props;

    const targetApp = sessionObject('targetApp');
    const currentBreadcrumb = targetApp ? sessionObject(targetApp.id) : [];
    const userContext = null;
    const renderBreadcrumbText = value => {
      if (value == null) {
        return '...';
      }
      if (value.length < 10) {
        return value;
      }

      return value.substring(0, 10) + '...';
    };
    const menuProps = collapsed
      ? {}
      : {
          openKeys: this.state.openKeys,
        };
    const renderBreadcrumbMenuItem = breadcrumbMenuItem => {
      return (
        <Menu.Item key={breadcrumbMenuItem.link}>
          <Link
            key={breadcrumbMenuItem.link}
            to={`${breadcrumbMenuItem.link}`}
            className={styles.breadcrumbLink}
          >
            <Icon type="heart" style={{ marginRight: '10px', color: 'red' }} />
            {renderBreadcrumbText(breadcrumbMenuItem.name)}
          </Link>
        </Menu.Item>
      );
    };
    const breadcrumbMenu = () => {
      const currentBreadcrumb = targetApp ? sessionObject(targetApp.id) : [];
      return (
        <Menu mode="vertical">{currentBreadcrumb.map(item => renderBreadcrumbMenuItem(item))}</Menu>
      );
    };

    const { Search } = Input;
    const showSearchResult = () => {
      this.setState({ showSearch: true });
    };
    const searchChange = evt => {
      this.setState({ searchKeyword: evt.target.value });
    };
    const hideSearchResult = () => {
      this.setState({ showSearch: false });
    };

    const { searchLocalData } = GlobalComponents.UserBase;

    const layout = (
      <Layout>
        <Header style={{ position: 'fixed', zIndex: 1, width: '100%' }}>
          <Row type="flex" justify="start" align="bottom">
            <Col {...naviBarResponsiveStyle}>
              <Dropdown overlay={this.getNavMenuItems(this.props.user)}>
                <a className={styles.menuLink}>
                  <Icon type="unordered-list" style={{ fontSize: '20px', marginRight: '10px' }} />{' '}
                  菜单
                </a>
              </Dropdown>
              <Dropdown overlay={breadcrumbMenu()}>
                <a className={styles.menuLink}>
                  <Icon type="down" style={{ fontSize: '20px', marginRight: '10px' }} /> 快速转到
                </a>
              </Dropdown>
            </Col>
            <Col className={styles.searchBox} {...searchBarResponsiveStyle}>
              <Search
                size="default"
                placeholder="请输入搜索条件, 查找功能，数据和词汇解释，关闭请点击搜索结果空白处"
                enterButton
                onFocus={() => showSearchResult()}
                onChange={evt => searchChange(evt)}
                style={{ marginLeft: '10px', marginTop: '7px', width: '100%' }}
              />
            </Col>
            <Col {...userBarResponsiveStyle}>
              <Dropdown overlay={<TopMenu {...this.props} />} className={styles.right}>
                <a className={styles.menuLink}>
                  <Icon type="user" style={{ fontSize: '20px', marginRight: '10px' }} /> 账户
                </a>
              </Dropdown>
            </Col>
          </Row>
        </Header>
        <Layout style={{ marginTop: 44 }}>
          {this.state.showSearch && (
            <div style={{ backgroundColor: 'black' }} onClick={() => hideSearchResult()}>
              {searchLocalData(this.props.user, this.state.searchKeyword)}
            </div>
          )}

          <Layout>
            <Content style={{ margin: '24px 24px 0', height: '100%' }}>
              {this.buildRouters()}
            </Content>
          </Layout>
        </Layout>
      </Layout>
    );
    return (
      <DocumentTitle title={this.getPageTitle()}>
        <ContainerQuery query={query}>
          {params => <div className={classNames(params)}>{layout}</div>}
        </ContainerQuery>
      </DocumentTitle>
    );
  }
}

export default connect(state => ({
  collapsed: state.global.collapsed,
  fetchingNotices: state.global.fetchingNotices,
  notices: state.global.notices,
  user: state._user,
  ...state,
}))(UserBizApp);
