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
import styles from './StudentDailyAnswer.profile.less';
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
const internalSummaryOf = (studentDailyAnswer, targetComponent) => {
  const userContext = null;
  return (
    <DescriptionList className={styles.headerList} size="small" col="4">
      <Description term="ID">{studentDailyAnswer.id}</Description>
      <Description term="回答">{studentDailyAnswer.answer}</Description>
      <Description term="创建时间">
        {moment(studentDailyAnswer.createTime).format('YYYY-MM-DD')}
      </Description>
      <Description term="最后更新时间">
        {moment(studentDailyAnswer.lastUpdateTime).format('YYYY-MM-DD')}
      </Description>
    </DescriptionList>
  );
};

const renderPermissionSetting = studentDailyAnswer => {
  const { StudentDailyAnswerBase } = GlobalComponents;
  return (
    <PermissionSetting
      targetObject={studentDailyAnswer}
      targetObjectMeta={StudentDailyAnswerBase}
    />
  );
};

const internalRenderExtraHeader = defaultRenderExtraHeader;

class StudentDailyAnswerPermission extends Component {
  componentDidMount() {}

  render() {
    // eslint-disable-next-line max-len
    const studentDailyAnswer = this.props.studentDailyAnswer;
    const { id, displayName, studentAnswerCount } = studentDailyAnswer;
    const returnURL = `/studentDailyAnswer/${id}/dashboard`;
    const cardsData = {
      cardsName: '学生每天回答',
      cardsFor: 'studentDailyAnswer',
      cardsSource: studentDailyAnswer,
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
  studentDailyAnswer: state._studentDailyAnswer,
}))(Form.create()(StudentDailyAnswerPermission));
