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
  menuName: window.trans('survey_status'),
  menuFor: 'surveyStatus',
  subItems: [
    {
      name: 'studentHealthSurveyList',
      displayName: window.mtrans(
        'student_health_survey',
        'survey_status.student_health_survey_list',
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
  ],
};

const settingMenuData = {
  menuName: window.trans('survey_status'),
  menuFor: 'surveyStatus',
  subItems: [],
};

const fieldLabels = {
  id: window.trans('survey_status.id'),
  name: window.trans('survey_status.name'),
  code: window.trans('survey_status.code'),
  platform: window.trans('survey_status.platform'),
};

const displayColumns = [
  {
    title: fieldLabels.id,
    debugtype: 'string',
    dataIndex: 'id',
    width: '8',
    render: (text, record) => renderTextCell(text, record, 'surveyStatus'),
    sorter: true,
  },
  {
    title: fieldLabels.name,
    debugtype: 'string',
    dataIndex: 'name',
    width: '7',
    render: (text, record) => renderTextCell(text, record),
  },
  {
    title: fieldLabels.code,
    debugtype: 'string',
    dataIndex: 'code',
    width: '16',
    render: (text, record) => renderTextCell(text, record),
  },
  {
    title: fieldLabels.platform,
    dataIndex: 'platform',
    render: (text, record) => renderReferenceCell(text, record),
    sorter: true,
  },
];

const searchLocalData = (targetObject, searchTerm) =>
  defaultSearchLocalData(menuData, targetObject, searchTerm);

const renderItemOfList = (surveyStatus, targetComponent, columCount) => {
  const displayColumnsCount = columCount || 2;
  const userContext = null;
  return (
    <div key={surveyStatus.id}>
      <DescriptionList key={surveyStatus.id} size="small" col="2">
        <Description term={fieldLabels.id} style={{ wordBreak: 'break-all' }}>
          {surveyStatus.id}
        </Description>
        <Description term={fieldLabels.name} style={{ wordBreak: 'break-all' }}>
          {surveyStatus.name}
        </Description>
        <Description term={fieldLabels.code} style={{ wordBreak: 'break-all' }}>
          {surveyStatus.code}
        </Description>
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
  );
};

const packFormValuesToObject = formValuesToPack => {
  const { name, code, platformId } = formValuesToPack;
  const platform = { id: platformId, version: 2 ^ 31 };
  const data = { name, code, platform };
  return data;
};
const unpackObjectToFormValues = objectToUnpack => {
  const { name, code, platform } = objectToUnpack;
  const platformId = platform ? platform.id : null;
  const data = { name, code, platformId };
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
const SurveyStatusBase = {
  menuData,
  displayColumns,
  fieldLabels,
  renderItemOfList,
  stepOf,
  searchLocalData,
};
export default SurveyStatusBase;
