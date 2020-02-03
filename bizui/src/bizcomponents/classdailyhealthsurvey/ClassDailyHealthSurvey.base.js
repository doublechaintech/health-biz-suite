import React from 'react';
import { Icon, Divider, Avata, Card, Col } from 'antd';

import { Link } from 'dva/router';
import moment from 'moment';
import ImagePreview from '../../components/ImagePreview';
import appLocaleName from '../../common/Locale.tool';
import BaseTool from '../../common/Base.tool';
import GlobalComponents from '../../custcomponents';
import DescriptionList from '../../components/DescriptionList';
const { Description } = DescriptionList;

const {
  defaultRenderReferenceCell,
  defaultRenderBooleanCell,
  defaultRenderMoneyCell,
  defaultRenderDateTimeCell,
  defaultRenderImageCell,
  defaultRenderAvatarCell,
  defaultRenderDateCell,
  defaultRenderIdentifier,
  defaultRenderTextCell,
  defaultSearchLocalData,
} = BaseTool;

const renderTextCell = defaultRenderTextCell;
const renderIdentifier = defaultRenderIdentifier;
const renderDateCell = defaultRenderDateCell;
const renderDateTimeCell = defaultRenderDateTimeCell;
const renderImageCell = defaultRenderImageCell;
const renderAvatarCell = defaultRenderAvatarCell;
const renderMoneyCell = defaultRenderMoneyCell;
const renderBooleanCell = defaultRenderBooleanCell;
const renderReferenceCell = defaultRenderReferenceCell;

const menuData = {
  menuName: window.trans('class_daily_health_survey'),
  menuFor: 'classDailyHealthSurvey',
  subItems: [
    {
      name: 'dailySurveyQuestionList',
      displayName: window.mtrans(
        'daily_survey_question',
        'class_daily_health_survey.daily_survey_question_list',
        false
      ),
      type: 'dailySurveyQuestion',
      icon: 'question',
      readPermission: false,
      createPermission: false,
      deletePermission: false,
      updatePermission: false,
      executionPermission: false,
      viewGroup: '__no_group',
    },
    {
      name: 'studentHealthSurveyList',
      displayName: window.mtrans(
        'student_health_survey',
        'class_daily_health_survey.student_health_survey_list',
        false
      ),
      type: 'studentHealthSurvey',
      icon: 'th',
      readPermission: false,
      createPermission: false,
      deletePermission: false,
      updatePermission: false,
      executionPermission: false,
      viewGroup: '__no_group',
    },
    {
      name: 'healthSurveyReportList',
      displayName: window.mtrans(
        'health_survey_report',
        'class_daily_health_survey.health_survey_report_list',
        false
      ),
      type: 'healthSurveyReport',
      icon: 'th',
      readPermission: false,
      createPermission: false,
      deletePermission: false,
      updatePermission: false,
      executionPermission: false,
      viewGroup: '__no_group',
    },
  ],
};

const settingMenuData = {
  menuName: window.trans('class_daily_health_survey'),
  menuFor: 'classDailyHealthSurvey',
  subItems: [],
};

const fieldLabels = {
  id: window.trans('class_daily_health_survey.id'),
  name: window.trans('class_daily_health_survey.name'),
  teacher: window.trans('class_daily_health_survey.teacher'),
  surveyTime: window.trans('class_daily_health_survey.survey_time'),
  creator: window.trans('class_daily_health_survey.creator'),
  changeRequest: window.trans('class_daily_health_survey.change_request'),
};

const displayColumns = [
  {
    title: fieldLabels.id,
    debugtype: 'string',
    dataIndex: 'id',
    width: '8',
    render: (text, record) => renderTextCell(text, record, 'classDailyHealthSurvey'),
    sorter: true,
  },
  {
    title: fieldLabels.name,
    debugtype: 'string',
    dataIndex: 'name',
    width: '26',
    render: (text, record) => renderTextCell(text, record),
  },
  {
    title: fieldLabels.teacher,
    dataIndex: 'teacher',
    render: (text, record) => renderReferenceCell(text, record),
    sorter: true,
  },
  {
    title: fieldLabels.surveyTime,
    dataIndex: 'surveyTime',
    render: (text, record) => renderDateTimeCell(text, record),
    sorter: true,
  },
  {
    title: fieldLabels.creator,
    dataIndex: 'creator',
    render: (text, record) => renderReferenceCell(text, record),
    sorter: true,
  },
  {
    title: fieldLabels.changeRequest,
    dataIndex: 'changeRequest',
    render: (text, record) => renderReferenceCell(text, record),
    sorter: true,
  },
];

const searchLocalData = (targetObject, searchTerm) =>
  defaultSearchLocalData(menuData, targetObject, searchTerm);

const renderItemOfList = (classDailyHealthSurvey, targetComponent, columCount) => {
  const displayColumnsCount = columCount || 2;
  const userContext = null;
  return (
    <div key={classDailyHealthSurvey.id}>
      <DescriptionList key={classDailyHealthSurvey.id} size="small" col="2">
        <Description term={fieldLabels.id} style={{ wordBreak: 'break-all' }}>
          {classDailyHealthSurvey.id}
        </Description>
        <Description term={fieldLabels.name} style={{ wordBreak: 'break-all' }}>
          {classDailyHealthSurvey.name}
        </Description>
        <Description term={fieldLabels.teacher}>
          <div>
            {classDailyHealthSurvey.teacher == null
              ? appLocaleName(userContext, 'NotAssigned')
              : `${classDailyHealthSurvey.teacher.displayName}(${
                  classDailyHealthSurvey.teacher.id
                })`}
          </div>
        </Description>
        <Description term={fieldLabels.surveyTime}>
          <div>{moment(classDailyHealthSurvey.surveyTime).format('YYYY-MM-DD HH:mm')}</div>
        </Description>
        <Description term={fieldLabels.creator}>
          <div>
            {classDailyHealthSurvey.creator == null
              ? appLocaleName(userContext, 'NotAssigned')
              : `${classDailyHealthSurvey.creator.displayName}(${
                  classDailyHealthSurvey.creator.id
                })`}
          </div>
        </Description>
        <Description term={fieldLabels.changeRequest}>
          <div>
            {classDailyHealthSurvey.changeRequest == null
              ? appLocaleName(userContext, 'NotAssigned')
              : `${classDailyHealthSurvey.changeRequest.displayName}(${
                  classDailyHealthSurvey.changeRequest.id
                })`}
          </div>
        </Description>
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
  );
};

const packFormValuesToObject = formValuesToPack => {
  const { name, surveyTime, teacherId, creatorId, changeRequestId } = formValuesToPack;
  const teacher = { id: teacherId, version: 2 ^ 31 };
  const creator = { id: creatorId, version: 2 ^ 31 };
  const changeRequest = { id: changeRequestId, version: 2 ^ 31 };
  const data = { name, surveyTime: moment(surveyTime).valueOf(), teacher, creator, changeRequest };
  return data;
};
const unpackObjectToFormValues = objectToUnpack => {
  const { name, surveyTime, teacher, creator, changeRequest } = objectToUnpack;
  const teacherId = teacher ? teacher.id : null;
  const creatorId = creator ? creator.id : null;
  const changeRequestId = changeRequest ? changeRequest.id : null;
  const data = { name, surveyTime: moment(surveyTime), teacherId, creatorId, changeRequestId };
  return data;
};
const stepOf = (targetComponent, title, content, position, index) => {
  return {
    title,
    content,
    position,
    packFunction: packFormValuesToObject,
    unpackFunction: unpackObjectToFormValues,
    index,
  };
};
const ClassDailyHealthSurveyBase = {
  menuData,
  displayColumns,
  fieldLabels,
  renderItemOfList,
  stepOf,
  searchLocalData,
};
export default ClassDailyHealthSurveyBase;
