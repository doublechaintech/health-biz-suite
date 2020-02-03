import React, { Component } from 'react';
import { connect } from 'dva';
import moment from 'moment';
import BooleanOption from '../../components/BooleanOption';
import {
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

import DashboardTool from '../../common/Dashboard.tool';
import PageHeaderLayout from '../../layouts/PageHeaderLayout';
import styles from './HealthSurveyReport.profile.less';
import DescriptionList from '../../components/DescriptionList';

import GlobalComponents from '../../custcomponents';
import PermissionSetting from '../../permission/PermissionSetting';
import appLocaleName from '../../common/Locale.tool';
const { Description } = DescriptionList;
const { defaultRenderExtraHeader } = DashboardTool;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
      {cardsData.cardsName}: {cardsData.displayName}
    </div>
  );
};
const internalSummaryOf = (healthSurveyReport, targetComponent) => {
  const userContext = null;
  return (
    <DescriptionList className={styles.headerList} size="small" col="4">
      <Description term="ID">{healthSurveyReport.id}</Description>
      <Description term="调查的名字">{healthSurveyReport.surveyName}</Description>
      <Description term="调查的时间">
        {moment(healthSurveyReport.surveyTime).format('YYYY-MM-DD')}
      </Description>
      <Description term="老师的名字">{healthSurveyReport.teacherName}</Description>
      <Description term="学校">{healthSurveyReport.school}</Description>
      <Description term="学校类">{healthSurveyReport.schoolClass}</Description>
      <Description term="学生的名字">{healthSurveyReport.studentName}</Description>
      <Description term="学生数量">{healthSurveyReport.studentNumber}</Description>
      <Description term="监护人姓名">{healthSurveyReport.guardianName}</Description>
      <Description term="监护人手机">{healthSurveyReport.guardianMobile}</Description>
    </DescriptionList>
  );
};

const renderPermissionSetting = healthSurveyReport => {
  const { HealthSurveyReportBase } = GlobalComponents;
  return (
    <PermissionSetting
      targetObject={healthSurveyReport}
      targetObjectMeta={HealthSurveyReportBase}
    />
  );
};

const internalRenderExtraHeader = defaultRenderExtraHeader;

class HealthSurveyReportPermission extends Component {
  componentDidMount() {}

  render() {
    // eslint-disable-next-line max-len
    const healthSurveyReport = this.props.healthSurveyReport;
    const { id, displayName, studentAnswerCount } = healthSurveyReport;
    const returnURL = `/healthSurveyReport/${id}/dashboard`;
    const cardsData = {
      cardsName: '健康调查报告',
      cardsFor: 'healthSurveyReport',
      cardsSource: healthSurveyReport,
      displayName,
      returnURL,
      subItems: [],
    };
    const renderExtraHeader = this.props.renderExtraHeader || internalRenderExtraHeader;
    const summaryOf = this.props.summaryOf || internalSummaryOf;

    return (
      <PageHeaderLayout
        title={internalRenderTitle(cardsData, this)}
        content={summaryOf(cardsData.cardsSource, this)}
        wrapperClassName={styles.advancedForm}
      >
        {renderExtraHeader(cardsData.cardsSource)}
        {renderPermissionSetting(cardsData.cardsSource)}
      </PageHeaderLayout>
    );
  }
}

export default connect(state => ({
  healthSurveyReport: state._healthSurveyReport,
}))(Form.create()(HealthSurveyReportPermission));
