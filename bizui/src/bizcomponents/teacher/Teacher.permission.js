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
import styles from './Teacher.profile.less';
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
const internalSummaryOf = (teacher, targetComponent) => {
  const userContext = null;
  return (
    <DescriptionList className={styles.headerList} size="small" col="4">
      <Description term="ID">{teacher.id}</Description>
      <Description term="名称">{teacher.name}</Description>
      <Description term="手机号码">{teacher.mobile}</Description>
      <Description term="学校">{teacher.school}</Description>
      <Description term="学校类">{teacher.schoolClass}</Description>
      <Description term="班级规模">{teacher.classSize}</Description>
      <Description term="创建时间">{moment(teacher.createTime).format('YYYY-MM-DD')}</Description>
    </DescriptionList>
  );
};

const renderPermissionSetting = teacher => {
  const { TeacherBase } = GlobalComponents;
  return <PermissionSetting targetObject={teacher} targetObjectMeta={TeacherBase} />;
};

const internalRenderExtraHeader = defaultRenderExtraHeader;

class TeacherPermission extends Component {
  componentDidMount() {}

  render() {
    // eslint-disable-next-line max-len
    const teacher = this.props.teacher;
    const {
      id,
      displayName,
      classDailyHealthSurveyCount,
      studentHealthSurveyCount,
      healthSurveyReportCount,
    } = teacher;
    const returnURL = `/teacher/${id}/dashboard`;
    const cardsData = {
      cardsName: '老师',
      cardsFor: 'teacher',
      cardsSource: teacher,
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
  teacher: state._teacher,
}))(Form.create()(TeacherPermission));
