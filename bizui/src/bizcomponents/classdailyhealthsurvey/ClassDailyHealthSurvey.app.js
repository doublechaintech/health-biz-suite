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
import styles from './ClassDailyHealthSurvey.app.less';
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

class ClassDailyHealthSurveyBizApp extends React.PureComponent {
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
      return ['/classDailyHealthSurvey/'];
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
          <Link to={`/classDailyHealthSurvey/${this.props.classDailyHealthSurvey.id}/dashboard`}>
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

  getDailySurveyQuestionSearch = () => {
    const { DailySurveyQuestionSearch } = GlobalComponents;
    const userContext = null;
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans(
        'daily_survey_question',
        'class_daily_health_survey.daily_survey_question_list',
        false
      ),
      role: 'dailySurveyQuestion',
      data: state._classDailyHealthSurvey.dailySurveyQuestionList,
      metaInfo: state._classDailyHealthSurvey.dailySurveyQuestionListMetaInfo,
      count: state._classDailyHealthSurvey.dailySurveyQuestionCount,
      returnURL: `/classDailyHealthSurvey/${state._classDailyHealthSurvey.id}/dashboard`,
      currentPage: state._classDailyHealthSurvey.dailySurveyQuestionCurrentPageNumber,
      searchFormParameters: state._classDailyHealthSurvey.dailySurveyQuestionSearchFormParameters,
      searchParameters: { ...state._classDailyHealthSurvey.searchParameters },
      expandForm: state._classDailyHealthSurvey.expandForm,
      loading: state._classDailyHealthSurvey.loading,
      partialList: state._classDailyHealthSurvey.partialList,
      owner: {
        type: '_classDailyHealthSurvey',
        id: state._classDailyHealthSurvey.id,
        referenceName: 'classDailyHealthSurvey',
        listName: 'dailySurveyQuestionList',
        ref: state._classDailyHealthSurvey,
        listDisplayName: appLocaleName(userContext, 'List'),
      }, // this is for model namespace and
    }))(DailySurveyQuestionSearch);
  };

  getDailySurveyQuestionCreateForm = () => {
    const { DailySurveyQuestionCreateForm } = GlobalComponents;
    const userContext = null;
    return connect(state => ({
      rule: state.rule,
      role: 'dailySurveyQuestion',
      data: state._classDailyHealthSurvey.dailySurveyQuestionList,
      metaInfo: state._classDailyHealthSurvey.dailySurveyQuestionListMetaInfo,
      count: state._classDailyHealthSurvey.dailySurveyQuestionCount,
      returnURL: `/classDailyHealthSurvey/${state._classDailyHealthSurvey.id}/list`,
      currentPage: state._classDailyHealthSurvey.dailySurveyQuestionCurrentPageNumber,
      searchFormParameters: state._classDailyHealthSurvey.dailySurveyQuestionSearchFormParameters,
      loading: state._classDailyHealthSurvey.loading,
      owner: {
        type: '_classDailyHealthSurvey',
        id: state._classDailyHealthSurvey.id,
        referenceName: 'classDailyHealthSurvey',
        listName: 'dailySurveyQuestionList',
        ref: state._classDailyHealthSurvey,
        listDisplayName: appLocaleName(userContext, 'List'),
      }, // this is for model namespace and
    }))(DailySurveyQuestionCreateForm);
  };

  getDailySurveyQuestionUpdateForm = () => {
    const userContext = null;
    const { DailySurveyQuestionUpdateForm } = GlobalComponents;
    return connect(state => ({
      selectedRows: state._classDailyHealthSurvey.selectedRows,
      role: 'dailySurveyQuestion',
      currentUpdateIndex: state._classDailyHealthSurvey.currentUpdateIndex,
      owner: {
        type: '_classDailyHealthSurvey',
        id: state._classDailyHealthSurvey.id,
        listName: 'dailySurveyQuestionList',
        ref: state._classDailyHealthSurvey,
        listDisplayName: appLocaleName(userContext, 'List'),
      }, // this is for model namespace and
    }))(DailySurveyQuestionUpdateForm);
  };

  getStudentHealthSurveySearch = () => {
    const { StudentHealthSurveySearch } = GlobalComponents;
    const userContext = null;
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans(
        'student_health_survey',
        'class_daily_health_survey.student_health_survey_list',
        false
      ),
      role: 'studentHealthSurvey',
      data: state._classDailyHealthSurvey.studentHealthSurveyList,
      metaInfo: state._classDailyHealthSurvey.studentHealthSurveyListMetaInfo,
      count: state._classDailyHealthSurvey.studentHealthSurveyCount,
      returnURL: `/classDailyHealthSurvey/${state._classDailyHealthSurvey.id}/dashboard`,
      currentPage: state._classDailyHealthSurvey.studentHealthSurveyCurrentPageNumber,
      searchFormParameters: state._classDailyHealthSurvey.studentHealthSurveySearchFormParameters,
      searchParameters: { ...state._classDailyHealthSurvey.searchParameters },
      expandForm: state._classDailyHealthSurvey.expandForm,
      loading: state._classDailyHealthSurvey.loading,
      partialList: state._classDailyHealthSurvey.partialList,
      owner: {
        type: '_classDailyHealthSurvey',
        id: state._classDailyHealthSurvey.id,
        referenceName: 'classDailyHealthSurvey',
        listName: 'studentHealthSurveyList',
        ref: state._classDailyHealthSurvey,
        listDisplayName: appLocaleName(userContext, 'List'),
      }, // this is for model namespace and
    }))(StudentHealthSurveySearch);
  };

  getStudentHealthSurveyCreateForm = () => {
    const { StudentHealthSurveyCreateForm } = GlobalComponents;
    const userContext = null;
    return connect(state => ({
      rule: state.rule,
      role: 'studentHealthSurvey',
      data: state._classDailyHealthSurvey.studentHealthSurveyList,
      metaInfo: state._classDailyHealthSurvey.studentHealthSurveyListMetaInfo,
      count: state._classDailyHealthSurvey.studentHealthSurveyCount,
      returnURL: `/classDailyHealthSurvey/${state._classDailyHealthSurvey.id}/list`,
      currentPage: state._classDailyHealthSurvey.studentHealthSurveyCurrentPageNumber,
      searchFormParameters: state._classDailyHealthSurvey.studentHealthSurveySearchFormParameters,
      loading: state._classDailyHealthSurvey.loading,
      owner: {
        type: '_classDailyHealthSurvey',
        id: state._classDailyHealthSurvey.id,
        referenceName: 'classDailyHealthSurvey',
        listName: 'studentHealthSurveyList',
        ref: state._classDailyHealthSurvey,
        listDisplayName: appLocaleName(userContext, 'List'),
      }, // this is for model namespace and
    }))(StudentHealthSurveyCreateForm);
  };

  getStudentHealthSurveyUpdateForm = () => {
    const userContext = null;
    const { StudentHealthSurveyUpdateForm } = GlobalComponents;
    return connect(state => ({
      selectedRows: state._classDailyHealthSurvey.selectedRows,
      role: 'studentHealthSurvey',
      currentUpdateIndex: state._classDailyHealthSurvey.currentUpdateIndex,
      owner: {
        type: '_classDailyHealthSurvey',
        id: state._classDailyHealthSurvey.id,
        listName: 'studentHealthSurveyList',
        ref: state._classDailyHealthSurvey,
        listDisplayName: appLocaleName(userContext, 'List'),
      }, // this is for model namespace and
    }))(StudentHealthSurveyUpdateForm);
  };

  getHealthSurveyReportSearch = () => {
    const { HealthSurveyReportSearch } = GlobalComponents;
    const userContext = null;
    return connect(state => ({
      rule: state.rule,
      name: window.mtrans(
        'health_survey_report',
        'class_daily_health_survey.health_survey_report_list',
        false
      ),
      role: 'healthSurveyReport',
      data: state._classDailyHealthSurvey.healthSurveyReportList,
      metaInfo: state._classDailyHealthSurvey.healthSurveyReportListMetaInfo,
      count: state._classDailyHealthSurvey.healthSurveyReportCount,
      returnURL: `/classDailyHealthSurvey/${state._classDailyHealthSurvey.id}/dashboard`,
      currentPage: state._classDailyHealthSurvey.healthSurveyReportCurrentPageNumber,
      searchFormParameters: state._classDailyHealthSurvey.healthSurveyReportSearchFormParameters,
      searchParameters: { ...state._classDailyHealthSurvey.searchParameters },
      expandForm: state._classDailyHealthSurvey.expandForm,
      loading: state._classDailyHealthSurvey.loading,
      partialList: state._classDailyHealthSurvey.partialList,
      owner: {
        type: '_classDailyHealthSurvey',
        id: state._classDailyHealthSurvey.id,
        referenceName: 'survey',
        listName: 'healthSurveyReportList',
        ref: state._classDailyHealthSurvey,
        listDisplayName: appLocaleName(userContext, 'List'),
      }, // this is for model namespace and
    }))(HealthSurveyReportSearch);
  };

  getHealthSurveyReportCreateForm = () => {
    const { HealthSurveyReportCreateForm } = GlobalComponents;
    const userContext = null;
    return connect(state => ({
      rule: state.rule,
      role: 'healthSurveyReport',
      data: state._classDailyHealthSurvey.healthSurveyReportList,
      metaInfo: state._classDailyHealthSurvey.healthSurveyReportListMetaInfo,
      count: state._classDailyHealthSurvey.healthSurveyReportCount,
      returnURL: `/classDailyHealthSurvey/${state._classDailyHealthSurvey.id}/list`,
      currentPage: state._classDailyHealthSurvey.healthSurveyReportCurrentPageNumber,
      searchFormParameters: state._classDailyHealthSurvey.healthSurveyReportSearchFormParameters,
      loading: state._classDailyHealthSurvey.loading,
      owner: {
        type: '_classDailyHealthSurvey',
        id: state._classDailyHealthSurvey.id,
        referenceName: 'survey',
        listName: 'healthSurveyReportList',
        ref: state._classDailyHealthSurvey,
        listDisplayName: appLocaleName(userContext, 'List'),
      }, // this is for model namespace and
    }))(HealthSurveyReportCreateForm);
  };

  getHealthSurveyReportUpdateForm = () => {
    const userContext = null;
    const { HealthSurveyReportUpdateForm } = GlobalComponents;
    return connect(state => ({
      selectedRows: state._classDailyHealthSurvey.selectedRows,
      role: 'healthSurveyReport',
      currentUpdateIndex: state._classDailyHealthSurvey.currentUpdateIndex,
      owner: {
        type: '_classDailyHealthSurvey',
        id: state._classDailyHealthSurvey.id,
        listName: 'healthSurveyReportList',
        ref: state._classDailyHealthSurvey,
        listDisplayName: appLocaleName(userContext, 'List'),
      }, // this is for model namespace and
    }))(HealthSurveyReportUpdateForm);
  };

  getPageTitle = () => {
    // const { location } = this.props
    // const { pathname } = location
    const title = '健康状态调查平台';
    return title;
  };

  buildRouters = () => {
    const { ClassDailyHealthSurveyDashboard } = GlobalComponents;
    const { ClassDailyHealthSurveyPermission } = GlobalComponents;
    const { ClassDailyHealthSurveyProfile } = GlobalComponents;

    const routers = [
      { path: '/classDailyHealthSurvey/:id/dashboard', component: ClassDailyHealthSurveyDashboard },
      { path: '/classDailyHealthSurvey/:id/profile', component: ClassDailyHealthSurveyProfile },
      {
        path: '/classDailyHealthSurvey/:id/permission',
        component: ClassDailyHealthSurveyPermission,
      },

      {
        path: '/classDailyHealthSurvey/:id/list/dailySurveyQuestionList',
        component: this.getDailySurveyQuestionSearch(),
      },
      {
        path: '/classDailyHealthSurvey/:id/list/dailySurveyQuestionCreateForm',
        component: this.getDailySurveyQuestionCreateForm(),
      },
      {
        path: '/classDailyHealthSurvey/:id/list/dailySurveyQuestionUpdateForm',
        component: this.getDailySurveyQuestionUpdateForm(),
      },

      {
        path: '/classDailyHealthSurvey/:id/list/studentHealthSurveyList',
        component: this.getStudentHealthSurveySearch(),
      },
      {
        path: '/classDailyHealthSurvey/:id/list/studentHealthSurveyCreateForm',
        component: this.getStudentHealthSurveyCreateForm(),
      },
      {
        path: '/classDailyHealthSurvey/:id/list/studentHealthSurveyUpdateForm',
        component: this.getStudentHealthSurveyUpdateForm(),
      },

      {
        path: '/classDailyHealthSurvey/:id/list/healthSurveyReportList',
        component: this.getHealthSurveyReportSearch(),
      },
      {
        path: '/classDailyHealthSurvey/:id/list/healthSurveyReportCreateForm',
        component: this.getHealthSurveyReportCreateForm(),
      },
      {
        path: '/classDailyHealthSurvey/:id/list/healthSurveyReportUpdateForm',
        component: this.getHealthSurveyReportUpdateForm(),
      },
      {
        path: '/classDailyHealthSurvey/:id/ChangeRequestType/:code',
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

    const { searchLocalData } = GlobalComponents.ClassDailyHealthSurveyBase;

    const layout = (
      <Layout>
        <Header style={{ position: 'fixed', zIndex: 1, width: '100%' }}>
          <Row type="flex" justify="start" align="bottom">
            <Col {...naviBarResponsiveStyle}>
              <Dropdown overlay={this.getNavMenuItems(this.props.classDailyHealthSurvey)}>
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
              {searchLocalData(this.props.classDailyHealthSurvey, this.state.searchKeyword)}
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
  classDailyHealthSurvey: state._classDailyHealthSurvey,
  ...state,
}))(ClassDailyHealthSurveyBizApp);
