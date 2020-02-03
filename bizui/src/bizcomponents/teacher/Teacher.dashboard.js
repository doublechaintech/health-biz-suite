import React, { Component } from 'react';
import { connect } from 'dva';
import moment from 'moment';
import BooleanOption from '../../components/BooleanOption';
import {
  Button,
  Row,
  Col,
  Icon,
  Card,
  Tabs,
  Table,
  Radio,
  DatePicker,
  Tooltip,
  Menu,
  Dropdown,
  Badge,
  Switch,
  Select,
  Form,
  AutoComplete,
  Modal,
} from 'antd';
import { Link, Route, Redirect } from 'dva/router';
import numeral from 'numeral';
import {
  ChartCard,
  yuan,
  MiniArea,
  MiniBar,
  MiniProgress,
  Field,
  Bar,
  Pie,
  TimelineChart,
} from '../../components/Charts';
import Trend from '../../components/Trend';
import NumberInfo from '../../components/NumberInfo';
import { getTimeDistance } from '../../utils/utils';
import PageHeaderLayout from '../../layouts/PageHeaderLayout';
import styles from './Teacher.dashboard.less';
import DescriptionList from '../../components/DescriptionList';
import ImagePreview from '../../components/ImagePreview';
import GlobalComponents from '../../custcomponents';
import DashboardTool from '../../common/Dashboard.tool';
import appLocaleName from '../../common/Locale.tool';

const {
  aggregateDataset,
  calcKey,
  defaultHideCloseTrans,
  defaultImageListOf,
  defaultSettingListOf,
  defaultBuildTransferModal,
  defaultExecuteTrans,
  defaultHandleTransferSearch,
  defaultShowTransferModel,
  defaultRenderExtraHeader,
  defaultSubListsOf,
  defaultRenderAnalytics,
  defaultRenderExtraFooter,
  renderForTimeLine,
  renderForNumbers,
  defaultQuickFunctions,
  defaultRenderSubjectList,
} = DashboardTool;

const { Description } = DescriptionList;
const { TabPane } = Tabs;
const { RangePicker } = DatePicker;
const { Option } = Select;

const imageList = teacher => {
  return [];
};

const internalImageListOf = teacher => defaultImageListOf(teacher, imageList);

const optionList = teacher => {
  return [];
};

const buildTransferModal = defaultBuildTransferModal;
const showTransferModel = defaultShowTransferModel;
const internalRenderSubjectList = defaultRenderSubjectList;
const internalSettingListOf = teacher => defaultSettingListOf(teacher, optionList);
const internalLargeTextOf = teacher => {
  return null;
};

const internalRenderExtraHeader = defaultRenderExtraHeader;

const internalRenderExtraFooter = defaultRenderExtraFooter;
const internalSubListsOf = defaultSubListsOf;

const renderSettingDropDown = (cardsData, targetComponent) => {
  return (
    <div style={{ float: 'right' }}>
      <Dropdown overlay={renderSettingMenu(cardsData, targetComponent)} placement="bottomRight">
        <Button>
          <Icon type="setting" theme="filled" twoToneColor="#00b" style={{ color: '#3333b0' }} />{' '}
          设置 <Icon type="down" />
        </Button>
      </Dropdown>
    </div>
  );
};

const renderSettingMenuItem = (item, cardsData, targetComponent) => {
  const userContext = null;
  return (
    <Menu.Item key={item.name}>
      <Link
        to={`/teacher/${targetComponent.props.teacher.id}/list/${item.name}/${item.displayName}/`}
      >
        <span>{item.displayName}</span>
      </Link>
    </Menu.Item>
  );
};
const renderSettingMenu = (cardsData, targetComponent) => {
  const userContext = null;
  return (
    <Menu>
      <Menu.Item key="profile">
        <Link to={`/teacher/${targetComponent.props.teacher.id}/permission`}>
          <Icon type="safety-certificate" theme="twoTone" twoToneColor="#52c41a" />
          <span>{appLocaleName(userContext, 'Permission')}</span>
        </Link>
      </Menu.Item>
      <Menu.Divider />
      {cardsData.subSettingItems.map(item =>
        renderSettingMenuItem(item, cardsData, targetComponent)
      )}
    </Menu>
  );
};

const internalRenderTitle = (cardsData, targetComponent) => {
  const linkComp = cardsData.returnURL ? (
    <Link to={cardsData.returnURL}>
      {' '}
      <Icon type="double-left" style={{ marginRight: '10px' }} />{' '}
    </Link>
  ) : null;
  return (
    <div>
      {linkComp}
      {cardsData.cardsName}: {cardsData.displayName}{' '}
      {renderSettingDropDown(cardsData, targetComponent)}
    </div>
  );
};

const internalSummaryOf = (teacher, targetComponent) => {
  const { TeacherService } = GlobalComponents;
  const userContext = null;
  return (
    <DescriptionList className={styles.headerList} size="small" col="4">
      <Description term="ID" style={{ wordBreak: 'break-all' }}>
        {teacher.id}
      </Description>
      <Description term="名称" style={{ wordBreak: 'break-all' }}>
        {teacher.name}
      </Description>
      <Description term="手机号码" style={{ wordBreak: 'break-all' }}>
        {teacher.mobile}
      </Description>
      <Description term="学校" style={{ wordBreak: 'break-all' }}>
        {teacher.school}
      </Description>
      <Description term="学校类" style={{ wordBreak: 'break-all' }}>
        {teacher.schoolClass}
      </Description>
      <Description term="班级规模" style={{ wordBreak: 'break-all' }}>
        {teacher.classSize}
      </Description>
      <Description term="创建时间">
        {moment(teacher.createTime).format('YYYY-MM-DD HH:mm')}
      </Description>
      <Description term="用户">
        {teacher.user == null
          ? appLocaleName(userContext, 'NotAssigned')
          : `${teacher.user.displayName}(${teacher.user.id})`}
        <Icon
          type="swap"
          onClick={() =>
            showTransferModel(
              targetComponent,
              '用户',
              'user',
              TeacherService.requestCandidateUser,
              TeacherService.transferToAnotherUser,
              'anotherUserId',
              teacher.user ? teacher.user.id : ''
            )
          }
          style={{ fontSize: 20, color: 'red' }}
        />
      </Description>
      <Description term="变更请求">
        {teacher.changeRequest == null
          ? appLocaleName(userContext, 'NotAssigned')
          : `${teacher.changeRequest.displayName}(${teacher.changeRequest.id})`}
        <Icon
          type="swap"
          onClick={() =>
            showTransferModel(
              targetComponent,
              '变更请求',
              'changeRequest',
              TeacherService.requestCandidateChangeRequest,
              TeacherService.transferToAnotherChangeRequest,
              'anotherChangeRequestId',
              teacher.changeRequest ? teacher.changeRequest.id : ''
            )
          }
          style={{ fontSize: 20, color: 'red' }}
        />
      </Description>

      {buildTransferModal(teacher, targetComponent)}
    </DescriptionList>
  );
};

const internalQuickFunctions = defaultQuickFunctions;

class TeacherDashboard extends Component {
  state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName: '',
    candidateObjectType: 'city',
    targetLocalName: '',
    transferServiceName: '',
    currentValue: '',
    transferTargetParameterName: '',
    defaultType: 'teacher',
  };
  componentDidMount() {}

  render() {
    // eslint-disable-next-line max-len
    const {
      id,
      displayName,
      classDailyHealthSurveyListMetaInfo,
      studentHealthSurveyListMetaInfo,
      healthSurveyReportListMetaInfo,
      classDailyHealthSurveyCount,
      studentHealthSurveyCount,
      healthSurveyReportCount,
    } = this.props.teacher;
    if (!this.props.teacher.class) {
      return null;
    }
    const returnURL = this.props.returnURL;

    const cardsData = {
      cardsName: '老师',
      cardsFor: 'teacher',
      cardsSource: this.props.teacher,
      returnURL,
      displayName,
      subItems: [
        {
          name: 'classDailyHealthSurveyList',
          displayName: window.mtrans(
            'class_daily_health_survey',
            'teacher.class_daily_health_survey_list',
            false
          ),
          viewGroup: '__no_group',
          type: 'classDailyHealthSurvey',
          count: classDailyHealthSurveyCount,
          addFunction: true,
          role: 'classDailyHealthSurvey',
          metaInfo: classDailyHealthSurveyListMetaInfo,
          renderItem: GlobalComponents.ClassDailyHealthSurveyBase.renderItemOfList,
        },
        {
          name: 'studentHealthSurveyList',
          displayName: window.mtrans(
            'student_health_survey',
            'teacher.student_health_survey_list',
            false
          ),
          viewGroup: '__no_group',
          type: 'studentHealthSurvey',
          count: studentHealthSurveyCount,
          addFunction: true,
          role: 'studentHealthSurvey',
          metaInfo: studentHealthSurveyListMetaInfo,
          renderItem: GlobalComponents.StudentHealthSurveyBase.renderItemOfList,
        },
        {
          name: 'healthSurveyReportList',
          displayName: window.mtrans(
            'health_survey_report',
            'teacher.health_survey_report_list',
            false
          ),
          viewGroup: '__no_group',
          type: 'healthSurveyReport',
          count: healthSurveyReportCount,
          addFunction: true,
          role: 'healthSurveyReport',
          metaInfo: healthSurveyReportListMetaInfo,
          renderItem: GlobalComponents.HealthSurveyReportBase.renderItemOfList,
        },
      ],
      subSettingItems: [],
    };

    const renderExtraHeader = this.props.renderExtraHeader || internalRenderExtraHeader;
    const settingListOf = this.props.settingListOf || internalSettingListOf;
    const imageListOf = this.props.imageListOf || internalImageListOf;
    const subListsOf = this.props.subListsOf || internalSubListsOf;
    const largeTextOf = this.props.largeTextOf || internalLargeTextOf;
    const summaryOf = this.props.summaryOf || internalSummaryOf;
    const renderTitle = this.props.renderTitle || internalRenderTitle;
    const renderExtraFooter = this.props.renderExtraFooter || internalRenderExtraFooter;
    const renderAnalytics = this.props.renderAnalytics || defaultRenderAnalytics;
    const quickFunctions = this.props.quickFunctions || internalQuickFunctions;
    const renderSubjectList = this.props.renderSubjectList || internalRenderSubjectList;

    return (
      <PageHeaderLayout
        title={renderTitle(cardsData, this)}
        content={summaryOf(cardsData.cardsSource, this)}
        wrapperClassName={styles.advancedForm}
      >
        {renderExtraHeader(cardsData.cardsSource)}

        {quickFunctions(cardsData)}
        {imageListOf(cardsData.cardsSource)}
        {renderAnalytics(cardsData.cardsSource)}
        {settingListOf(cardsData.cardsSource)}
        {renderSubjectList(cardsData)}
        {largeTextOf(cardsData.cardsSource)}
        {renderExtraFooter(cardsData.cardsSource)}
      </PageHeaderLayout>
    );
  }
}

export default connect(state => ({
  teacher: state._teacher,
  returnURL: state.breadcrumb.returnURL,
}))(Form.create()(TeacherDashboard));
