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
import styles from './ClassDailyHealthSurvey.profile.less';
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
const internalSummaryOf = (classDailyHealthSurvey, targetComponent) => {
  const userContext = null;
  return (
    <DescriptionList className={styles.headerList} size="small" col="4">
      <Description term="ID">{classDailyHealthSurvey.id}</Description>
      <Description term="名称">{classDailyHealthSurvey.name}</Description>
      <Description term="调查的时间">
        {moment(classDailyHealthSurvey.surveyTime).format('YYYY-MM-DD')}
      </Description>
    </DescriptionList>
  );
};

const renderPermissionSetting = classDailyHealthSurvey => {
  const { ClassDailyHealthSurveyBase } = GlobalComponents;
  return (
    <PermissionSetting
      targetObject={classDailyHealthSurvey}
      targetObjectMeta={ClassDailyHealthSurveyBase}
    />
  );
};

const internalRenderExtraHeader = defaultRenderExtraHeader;

class ClassDailyHealthSurveyPermission extends Component {
  componentDidMount() {}

  render() {
    // eslint-disable-next-line max-len
    const classDailyHealthSurvey = this.props.classDailyHealthSurvey;
    const {
      id,
      displayName,
      dailySurveyQuestionCount,
      studentHealthSurveyCount,
      healthSurveyReportCount,
    } = classDailyHealthSurvey;
    const returnURL = `/classDailyHealthSurvey/${id}/dashboard`;
    const cardsData = {
      cardsName: '每日健康调查',
      cardsFor: 'classDailyHealthSurvey',
      cardsSource: classDailyHealthSurvey,
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
  classDailyHealthSurvey: state._classDailyHealthSurvey,
}))(Form.create()(ClassDailyHealthSurveyPermission));
