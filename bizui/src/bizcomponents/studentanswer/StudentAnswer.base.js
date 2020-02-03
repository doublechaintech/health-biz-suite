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
  menuName: window.trans('student_answer'),
  menuFor: 'studentAnswer',
  subItems: [],
};

const settingMenuData = {
  menuName: window.trans('student_answer'),
  menuFor: 'studentAnswer',
  subItems: [],
};

const fieldLabels = {
  id: window.trans('student_answer.id'),
  healthSurveyReport: window.trans('student_answer.health_survey_report'),
  dailyAnswer: window.trans('student_answer.daily_answer'),
  questionTopic: window.trans('student_answer.question_topic'),
  answer: window.trans('student_answer.answer'),
};

const displayColumns = [
  {
    title: fieldLabels.id,
    debugtype: 'string',
    dataIndex: 'id',
    width: '8',
    render: (text, record) => renderTextCell(text, record, 'studentAnswer'),
    sorter: true,
  },
  {
    title: fieldLabels.healthSurveyReport,
    dataIndex: 'healthSurveyReport',
    render: (text, record) => renderReferenceCell(text, record),
    sorter: true,
  },
  {
    title: fieldLabels.dailyAnswer,
    dataIndex: 'dailyAnswer',
    render: (text, record) => renderReferenceCell(text, record),
    sorter: true,
  },
  {
    title: fieldLabels.questionTopic,
    debugtype: 'string',
    dataIndex: 'questionTopic',
    width: '13',
    render: (text, record) => renderTextCell(text, record),
  },
  {
    title: fieldLabels.answer,
    debugtype: 'string',
    dataIndex: 'answer',
    width: '5',
    render: (text, record) => renderTextCell(text, record),
  },
];

const searchLocalData = (targetObject, searchTerm) =>
  defaultSearchLocalData(menuData, targetObject, searchTerm);

const renderItemOfList = (studentAnswer, targetComponent, columCount) => {
  const displayColumnsCount = columCount || 2;
  const userContext = null;
  return (
    <div key={studentAnswer.id}>
      <DescriptionList key={studentAnswer.id} size="small" col="2">
        <Description term={fieldLabels.id} style={{ wordBreak: 'break-all' }}>
          {studentAnswer.id}
        </Description>
        <Description term={fieldLabels.healthSurveyReport}>
          <div>
            {studentAnswer.healthSurveyReport == null
              ? appLocaleName(userContext, 'NotAssigned')
              : `${studentAnswer.healthSurveyReport.displayName}(${
                  studentAnswer.healthSurveyReport.id
                })`}
          </div>
        </Description>
        <Description term={fieldLabels.dailyAnswer}>
          <div>
            {studentAnswer.dailyAnswer == null
              ? appLocaleName(userContext, 'NotAssigned')
              : `${studentAnswer.dailyAnswer.displayName}(${studentAnswer.dailyAnswer.id})`}
          </div>
        </Description>
        <Description term={fieldLabels.questionTopic} style={{ wordBreak: 'break-all' }}>
          {studentAnswer.questionTopic}
        </Description>
        <Description term={fieldLabels.answer} style={{ wordBreak: 'break-all' }}>
          {studentAnswer.answer}
        </Description>
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
  );
};

const packFormValuesToObject = formValuesToPack => {
  const { questionTopic, answer, healthSurveyReportId, dailyAnswerId } = formValuesToPack;
  const healthSurveyReport = { id: healthSurveyReportId, version: 2 ^ 31 };
  const dailyAnswer = { id: dailyAnswerId, version: 2 ^ 31 };
  const data = { questionTopic, answer, healthSurveyReport, dailyAnswer };
  return data;
};
const unpackObjectToFormValues = objectToUnpack => {
  const { questionTopic, answer, healthSurveyReport, dailyAnswer } = objectToUnpack;
  const healthSurveyReportId = healthSurveyReport ? healthSurveyReport.id : null;
  const dailyAnswerId = dailyAnswer ? dailyAnswer.id : null;
  const data = { questionTopic, answer, healthSurveyReportId, dailyAnswerId };
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
const StudentAnswerBase = {
  menuData,
  displayColumns,
  fieldLabels,
  renderItemOfList,
  stepOf,
  searchLocalData,
};
export default StudentAnswerBase;
