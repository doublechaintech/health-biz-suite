import React, { Component } from 'react';
import { connect } from 'dva';
import moment from 'moment';
import GlobalComponents from '../../custcomponents';
import { Form } from 'antd';
import { Link } from 'dva/router';

import PageHeaderLayout from '../../layouts/PageHeaderLayout';
import styles from './Teacher.profile.less';
import DescriptionList from '../../components/DescriptionList';

import DashboardTool from '../../common/Dashboard.tool';
import appLocaleName from '../../common/Locale.tool';

const { defaultRenderExtraHeader, defaultSubListsOf, defaultRenderSettingList } = DashboardTool;

const { Description } = DescriptionList;

const internalRenderExtraHeader = defaultRenderExtraHeader;

const internalSubListsOf = defaultSubListsOf;

const internalRenderSettingList = defaultRenderSettingList;

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

const internalSummaryOf = (item, targetComponents) => {
  return GlobalComponents.TeacherBase.renderItemOfList(item, targetComponents);
};

class TeacherProfile extends Component {
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
    const renderSettingList = this.props.renderSettingList || internalRenderSettingList;

    return (
      <PageHeaderLayout
        title={internalRenderTitle(cardsData, this)}
        content={summaryOf(cardsData.cardsSource, this)}
        wrapperClassName={styles.advancedForm}
      >
        {renderExtraHeader(cardsData.cardsSource)}
        {renderSettingList(cardsData)}
      </PageHeaderLayout>
    );
  }
}

export default connect(state => ({
  teacher: state._teacher,
}))(Form.create()(TeacherProfile));
