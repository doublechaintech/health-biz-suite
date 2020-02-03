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
  menuName: window.trans('health_survey_report'),
  menuFor: 'healthSurveyReport',
  subItems: [
    {
      name: 'studentAnswerList',
      displayName: window.mtrans(
        'student_answer',
        'health_survey_report.student_answer_list',
        false
      ),
      type: 'studentAnswer',
      icon: '500px',
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
  menuName: window.trans('health_survey_report'),
  menuFor: 'healthSurveyReport',
  subItems: [],
};

const fieldLabels = {
  id: window.trans('health_survey_report.id'),
  surveyName: window.trans('health_survey_report.survey_name'),
  surveyTime: window.trans('health_survey_report.survey_time'),
  teacherName: window.trans('health_survey_report.teacher_name'),
  school: window.trans('health_survey_report.school'),
  schoolClass: window.trans('health_survey_report.school_class'),
  studentName: window.trans('health_survey_report.student_name'),
  studentNumber: window.trans('health_survey_report.student_number'),
  guardianName: window.trans('health_survey_report.guardian_name'),
  guardianMobile: window.trans('health_survey_report.guardian_mobile'),
  student: window.trans('health_survey_report.student'),
  teacher: window.trans('health_survey_report.teacher'),
  survey: window.trans('health_survey_report.survey'),
};

const displayColumns = [
  {
    title: fieldLabels.id,
    debugtype: 'string',
    dataIndex: 'id',
    width: '8',
    render: (text, record) => renderTextCell(text, record, 'healthSurveyReport'),
    sorter: true,
  },
  {
    title: fieldLabels.surveyName,
    debugtype: 'string',
    dataIndex: 'surveyName',
    width: '26',
    render: (text, record) => renderTextCell(text, record),
  },
  {
    title: fieldLabels.surveyTime,
    dataIndex: 'surveyTime',
    render: (text, record) => renderDateTimeCell(text, record),
    sorter: true,
  },
  {
    title: fieldLabels.teacherName,
    debugtype: 'string',
    dataIndex: 'teacherName',
    width: '6',
    render: (text, record) => renderTextCell(text, record),
  },
  {
    title: fieldLabels.school,
    debugtype: 'string',
    dataIndex: 'school',
    width: '8',
    render: (text, record) => renderTextCell(text, record),
  },
  {
    title: fieldLabels.schoolClass,
    debugtype: 'string',
    dataIndex: 'schoolClass',
    width: '12',
    render: (text, record) => renderTextCell(text, record),
  },
  {
    title: fieldLabels.studentName,
    debugtype: 'string',
    dataIndex: 'studentName',
    width: '7',
    render: (text, record) => renderTextCell(text, record),
  },
  {
    title: fieldLabels.studentNumber,
    debugtype: 'string',
    dataIndex: 'studentNumber',
    width: '7',
    render: (text, record) => renderTextCell(text, record),
  },
  {
    title: fieldLabels.guardianName,
    debugtype: 'string',
    dataIndex: 'guardianName',
    width: '7',
    render: (text, record) => renderTextCell(text, record),
  },
  {
    title: fieldLabels.guardianMobile,
    debugtype: 'string_china_mobile_phone',
    dataIndex: 'guardianMobile',
    width: '15',
    render: (text, record) => renderTextCell(text, record),
  },
  {
    title: fieldLabels.student,
    dataIndex: 'student',
    render: (text, record) => renderReferenceCell(text, record),
    sorter: true,
  },
  {
    title: fieldLabels.teacher,
    dataIndex: 'teacher',
    render: (text, record) => renderReferenceCell(text, record),
    sorter: true,
  },
  {
    title: fieldLabels.survey,
    dataIndex: 'survey',
    render: (text, record) => renderReferenceCell(text, record),
    sorter: true,
  },
];

const searchLocalData = (targetObject, searchTerm) =>
  defaultSearchLocalData(menuData, targetObject, searchTerm);

const renderItemOfList = (healthSurveyReport, targetComponent, columCount) => {
  const displayColumnsCount = columCount || 2;
  const userContext = null;
  return (
    <div key={healthSurveyReport.id}>
      <DescriptionList key={healthSurveyReport.id} size="small" col="2">
        <Description term={fieldLabels.id} style={{ wordBreak: 'break-all' }}>
          {healthSurveyReport.id}
        </Description>
        <Description term={fieldLabels.surveyName} style={{ wordBreak: 'break-all' }}>
          {healthSurveyReport.surveyName}
        </Description>
        <Description term={fieldLabels.surveyTime}>
          <div>{moment(healthSurveyReport.surveyTime).format('YYYY-MM-DD HH:mm')}</div>
        </Description>
        <Description term={fieldLabels.teacherName} style={{ wordBreak: 'break-all' }}>
          {healthSurveyReport.teacherName}
        </Description>
        <Description term={fieldLabels.school} style={{ wordBreak: 'break-all' }}>
          {healthSurveyReport.school}
        </Description>
        <Description term={fieldLabels.schoolClass} style={{ wordBreak: 'break-all' }}>
          {healthSurveyReport.schoolClass}
        </Description>
        <Description term={fieldLabels.studentName} style={{ wordBreak: 'break-all' }}>
          {healthSurveyReport.studentName}
        </Description>
        <Description term={fieldLabels.studentNumber} style={{ wordBreak: 'break-all' }}>
          {healthSurveyReport.studentNumber}
        </Description>
        <Description term={fieldLabels.guardianName} style={{ wordBreak: 'break-all' }}>
          {healthSurveyReport.guardianName}
        </Description>
        <Description term={fieldLabels.guardianMobile} style={{ wordBreak: 'break-all' }}>
          {healthSurveyReport.guardianMobile}
        </Description>
        <Description term={fieldLabels.student}>
          <div>
            {healthSurveyReport.student == null
              ? appLocaleName(userContext, 'NotAssigned')
              : `${healthSurveyReport.student.displayName}(${healthSurveyReport.student.id})`}
          </div>
        </Description>
        <Description term={fieldLabels.teacher}>
          <div>
            {healthSurveyReport.teacher == null
              ? appLocaleName(userContext, 'NotAssigned')
              : `${healthSurveyReport.teacher.displayName}(${healthSurveyReport.teacher.id})`}
          </div>
        </Description>
        <Description term={fieldLabels.survey}>
          <div>
            {healthSurveyReport.survey == null
              ? appLocaleName(userContext, 'NotAssigned')
              : `${healthSurveyReport.survey.displayName}(${healthSurveyReport.survey.id})`}
          </div>
        </Description>
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
  );
};

const packFormValuesToObject = formValuesToPack => {
  const {
    surveyName,
    surveyTime,
    teacherName,
    school,
    schoolClass,
    studentName,
    studentNumber,
    guardianName,
    guardianMobile,
    studentId,
    teacherId,
    surveyId,
  } = formValuesToPack;
  const student = { id: studentId, version: 2 ^ 31 };
  const teacher = { id: teacherId, version: 2 ^ 31 };
  const survey = { id: surveyId, version: 2 ^ 31 };
  const data = {
    surveyName,
    surveyTime: moment(surveyTime).valueOf(),
    teacherName,
    school,
    schoolClass,
    studentName,
    studentNumber,
    guardianName,
    guardianMobile,
    student,
    teacher,
    survey,
  };
  return data;
};
const unpackObjectToFormValues = objectToUnpack => {
  const {
    surveyName,
    surveyTime,
    teacherName,
    school,
    schoolClass,
    studentName,
    studentNumber,
    guardianName,
    guardianMobile,
    student,
    teacher,
    survey,
  } = objectToUnpack;
  const studentId = student ? student.id : null;
  const teacherId = teacher ? teacher.id : null;
  const surveyId = survey ? survey.id : null;
  const data = {
    surveyName,
    surveyTime: moment(surveyTime),
    teacherName,
    school,
    schoolClass,
    studentName,
    studentNumber,
    guardianName,
    guardianMobile,
    studentId,
    teacherId,
    surveyId,
  };
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
const HealthSurveyReportBase = {
  menuData,
  displayColumns,
  fieldLabels,
  renderItemOfList,
  stepOf,
  searchLocalData,
};
export default HealthSurveyReportBase;
