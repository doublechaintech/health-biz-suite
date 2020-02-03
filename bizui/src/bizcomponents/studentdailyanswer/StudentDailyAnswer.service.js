import { get, put, postForm, PREFIX, joinParameters, joinPostParameters } from '../../axios/tools';

const view = targetObjectId => {
  return get({
    url: `${PREFIX}studentDailyAnswerManager/view/${targetObjectId}/`,
  });
};

const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters);
  return get({
    url: `${PREFIX}studentDailyAnswerManager/loadStudentDailyAnswer/${targetObjectId}/${parametersExpr}/`,
  });
};

const requestCandidateStudentHealthSurvey = (ownerClass, id, filterKey, pageNo) => {
  const url = `${PREFIX}studentDailyAnswerManager/requestCandidateStudentHealthSurvey/ownerClass/id/filterKey/pageNo/`;
  const requestParameters = { id, ownerClass, filterKey, pageNo };
  return postForm({ url, requestParameters });
};

const transferToAnotherStudentHealthSurvey = (id, parameters) => {
  const url = `${PREFIX}studentDailyAnswerManager/transferToAnotherStudentHealthSurvey/id/anotherStudentHealthSurveyId/`;
  const requestParameters = { id, ...parameters };
  return postForm({ url, requestParameters });
};

const requestCandidateQuestion = (ownerClass, id, filterKey, pageNo) => {
  const url = `${PREFIX}studentDailyAnswerManager/requestCandidateQuestion/ownerClass/id/filterKey/pageNo/`;
  const requestParameters = { id, ownerClass, filterKey, pageNo };
  return postForm({ url, requestParameters });
};

const transferToAnotherQuestion = (id, parameters) => {
  const url = `${PREFIX}studentDailyAnswerManager/transferToAnotherQuestion/id/anotherQuestionId/`;
  const requestParameters = { id, ...parameters };
  return postForm({ url, requestParameters });
};

const requestCandidateChangeRequest = (ownerClass, id, filterKey, pageNo) => {
  const url = `${PREFIX}studentDailyAnswerManager/requestCandidateChangeRequest/ownerClass/id/filterKey/pageNo/`;
  const requestParameters = { id, ownerClass, filterKey, pageNo };
  return postForm({ url, requestParameters });
};

const transferToAnotherChangeRequest = (id, parameters) => {
  const url = `${PREFIX}studentDailyAnswerManager/transferToAnotherChangeRequest/id/anotherChangeRequestId/`;
  const requestParameters = { id, ...parameters };
  return postForm({ url, requestParameters });
};

const addStudentAnswer = (targetObjectId, parameters) => {
  const url = `${PREFIX}studentDailyAnswerManager/addStudentAnswer/studentDailyAnswerId/healthSurveyReportId/questionTopic/answer/tokensExpr/`;
  const studentDailyAnswerId = targetObjectId;
  const requestParameters = { ...parameters, studentDailyAnswerId, tokensExpr: 'none' };
  return postForm({ url, requestParameters });
};

const updateStudentAnswer = (targetObjectId, parameters) => {
  const url = `${PREFIX}studentDailyAnswerManager/updateStudentAnswerProperties/studentDailyAnswerId/id/questionTopic/answer/tokensExpr/`;
  const studentDailyAnswerId = targetObjectId;
  const requestParameters = { ...parameters, studentDailyAnswerId, tokensExpr: 'none' };
  return postForm({ url, requestParameters });
};

const removeStudentAnswerList = (targetObjectId, parameters) => {
  const url = `${PREFIX}studentDailyAnswerManager/removeStudentAnswerList/studentDailyAnswerId/studentAnswerIds/tokensExpr/`;
  const requestParameters = {
    ...parameters,
    studentDailyAnswerId: targetObjectId,
    tokensExpr: 'none',
  };
  return postForm({ url, requestParameters });
};

// Filter this out when no functions

const listFunctions = () => {
  return get({
    url: `${PREFIX}studentDailyAnswerService/listFunctions/`,
  });
};

const saveRequest = data => {
  return put({
    url: `${PREFIX}studentDailyAnswerService/save/`,
    data,
  });
};

const processRequest = data => {
  return put({
    url: `${PREFIX}studentDailyAnswerService/process/`,
    data,
  });
};

const StudentDailyAnswerService = {
  view,
  load,
  addStudentAnswer,
  updateStudentAnswer,
  removeStudentAnswerList,
  requestCandidateStudentHealthSurvey,
  requestCandidateQuestion,
  requestCandidateChangeRequest,
  transferToAnotherStudentHealthSurvey,
  transferToAnotherQuestion,
  transferToAnotherChangeRequest,
  listFunctions,
  saveRequest,
  processRequest,
};
export default StudentDailyAnswerService;
