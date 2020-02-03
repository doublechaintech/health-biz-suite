import { get, put, postForm, PREFIX, joinParameters, joinPostParameters } from '../../axios/tools';

const view = targetObjectId => {
  return get({
    url: `${PREFIX}studentAnswerManager/view/${targetObjectId}/`,
  });
};

const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters);
  return get({
    url: `${PREFIX}studentAnswerManager/loadStudentAnswer/${targetObjectId}/${parametersExpr}/`,
  });
};

const requestCandidateHealthSurveyReport = (ownerClass, id, filterKey, pageNo) => {
  const url = `${PREFIX}studentAnswerManager/requestCandidateHealthSurveyReport/ownerClass/id/filterKey/pageNo/`;
  const requestParameters = { id, ownerClass, filterKey, pageNo };
  return postForm({ url, requestParameters });
};

const transferToAnotherHealthSurveyReport = (id, parameters) => {
  const url = `${PREFIX}studentAnswerManager/transferToAnotherHealthSurveyReport/id/anotherHealthSurveyReportId/`;
  const requestParameters = { id, ...parameters };
  return postForm({ url, requestParameters });
};

const requestCandidateDailyAnswer = (ownerClass, id, filterKey, pageNo) => {
  const url = `${PREFIX}studentAnswerManager/requestCandidateDailyAnswer/ownerClass/id/filterKey/pageNo/`;
  const requestParameters = { id, ownerClass, filterKey, pageNo };
  return postForm({ url, requestParameters });
};

const transferToAnotherDailyAnswer = (id, parameters) => {
  const url = `${PREFIX}studentAnswerManager/transferToAnotherDailyAnswer/id/anotherDailyAnswerId/`;
  const requestParameters = { id, ...parameters };
  return postForm({ url, requestParameters });
};

// Filter this out when no functions

const listFunctions = () => {
  return get({
    url: `${PREFIX}studentAnswerService/listFunctions/`,
  });
};

const saveRequest = data => {
  return put({
    url: `${PREFIX}studentAnswerService/save/`,
    data,
  });
};

const processRequest = data => {
  return put({
    url: `${PREFIX}studentAnswerService/process/`,
    data,
  });
};

const StudentAnswerService = {
  view,
  load,
  requestCandidateHealthSurveyReport,
  requestCandidateDailyAnswer,
  transferToAnotherHealthSurveyReport,
  transferToAnotherDailyAnswer,
  listFunctions,
  saveRequest,
  processRequest,
};
export default StudentAnswerService;
