import { get, put, postForm, PREFIX, joinParameters, joinPostParameters } from '../../axios/tools';

const view = targetObjectId => {
  return get({
    url: `${PREFIX}classDailyHealthSurveyManager/view/${targetObjectId}/`,
  });
};

const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters);
  return get({
    url: `${PREFIX}classDailyHealthSurveyManager/loadClassDailyHealthSurvey/${targetObjectId}/${parametersExpr}/`,
  });
};

const requestCandidateTeacher = (ownerClass, id, filterKey, pageNo) => {
  const url = `${PREFIX}classDailyHealthSurveyManager/requestCandidateTeacher/ownerClass/id/filterKey/pageNo/`;
  const requestParameters = { id, ownerClass, filterKey, pageNo };
  return postForm({ url, requestParameters });
};

const transferToAnotherTeacher = (id, parameters) => {
  const url = `${PREFIX}classDailyHealthSurveyManager/transferToAnotherTeacher/id/anotherTeacherId/`;
  const requestParameters = { id, ...parameters };
  return postForm({ url, requestParameters });
};

const requestCandidateCreator = (ownerClass, id, filterKey, pageNo) => {
  const url = `${PREFIX}classDailyHealthSurveyManager/requestCandidateCreator/ownerClass/id/filterKey/pageNo/`;
  const requestParameters = { id, ownerClass, filterKey, pageNo };
  return postForm({ url, requestParameters });
};

const transferToAnotherCreator = (id, parameters) => {
  const url = `${PREFIX}classDailyHealthSurveyManager/transferToAnotherCreator/id/anotherCreatorId/`;
  const requestParameters = { id, ...parameters };
  return postForm({ url, requestParameters });
};

const requestCandidateChangeRequest = (ownerClass, id, filterKey, pageNo) => {
  const url = `${PREFIX}classDailyHealthSurveyManager/requestCandidateChangeRequest/ownerClass/id/filterKey/pageNo/`;
  const requestParameters = { id, ownerClass, filterKey, pageNo };
  return postForm({ url, requestParameters });
};

const transferToAnotherChangeRequest = (id, parameters) => {
  const url = `${PREFIX}classDailyHealthSurveyManager/transferToAnotherChangeRequest/id/anotherChangeRequestId/`;
  const requestParameters = { id, ...parameters };
  return postForm({ url, requestParameters });
};

const addDailySurveyQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}classDailyHealthSurveyManager/addDailySurveyQuestion/classDailyHealthSurveyId/topic/questionTypeId/optionA/optionB/optionC/optionD/surveyQuestionId/tokensExpr/`;
  const classDailyHealthSurveyId = targetObjectId;
  const requestParameters = { ...parameters, classDailyHealthSurveyId, tokensExpr: 'none' };
  return postForm({ url, requestParameters });
};

const updateDailySurveyQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}classDailyHealthSurveyManager/updateDailySurveyQuestionProperties/classDailyHealthSurveyId/id/topic/optionA/optionB/optionC/optionD/tokensExpr/`;
  const classDailyHealthSurveyId = targetObjectId;
  const requestParameters = { ...parameters, classDailyHealthSurveyId, tokensExpr: 'none' };
  return postForm({ url, requestParameters });
};

const removeDailySurveyQuestionList = (targetObjectId, parameters) => {
  const url = `${PREFIX}classDailyHealthSurveyManager/removeDailySurveyQuestionList/classDailyHealthSurveyId/dailySurveyQuestionIds/tokensExpr/`;
  const requestParameters = {
    ...parameters,
    classDailyHealthSurveyId: targetObjectId,
    tokensExpr: 'none',
  };
  return postForm({ url, requestParameters });
};

const addStudentHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}classDailyHealthSurveyManager/addStudentHealthSurvey/classDailyHealthSurveyId/studentId/answerTime/surveyStatusId/teacherId/changeRequestId/tokensExpr/`;
  const classDailyHealthSurveyId = targetObjectId;
  const requestParameters = { ...parameters, classDailyHealthSurveyId, tokensExpr: 'none' };
  return postForm({ url, requestParameters });
};

const updateStudentHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}classDailyHealthSurveyManager/updateStudentHealthSurveyProperties/classDailyHealthSurveyId/id/answerTime/tokensExpr/`;
  const classDailyHealthSurveyId = targetObjectId;
  const requestParameters = { ...parameters, classDailyHealthSurveyId, tokensExpr: 'none' };
  return postForm({ url, requestParameters });
};

const removeStudentHealthSurveyList = (targetObjectId, parameters) => {
  const url = `${PREFIX}classDailyHealthSurveyManager/removeStudentHealthSurveyList/classDailyHealthSurveyId/studentHealthSurveyIds/tokensExpr/`;
  const requestParameters = {
    ...parameters,
    classDailyHealthSurveyId: targetObjectId,
    tokensExpr: 'none',
  };
  return postForm({ url, requestParameters });
};

const addHealthSurveyReport = (targetObjectId, parameters) => {
  const url = `${PREFIX}classDailyHealthSurveyManager/addHealthSurveyReport/classDailyHealthSurveyId/surveyName/surveyTime/teacherName/school/schoolClass/studentName/studentNumber/guardianName/guardianMobile/studentId/teacherId/tokensExpr/`;
  const classDailyHealthSurveyId = targetObjectId;
  const requestParameters = { ...parameters, classDailyHealthSurveyId, tokensExpr: 'none' };
  return postForm({ url, requestParameters });
};

const updateHealthSurveyReport = (targetObjectId, parameters) => {
  const url = `${PREFIX}classDailyHealthSurveyManager/updateHealthSurveyReportProperties/classDailyHealthSurveyId/id/surveyName/surveyTime/teacherName/school/schoolClass/studentName/studentNumber/guardianName/guardianMobile/tokensExpr/`;
  const classDailyHealthSurveyId = targetObjectId;
  const requestParameters = { ...parameters, classDailyHealthSurveyId, tokensExpr: 'none' };
  return postForm({ url, requestParameters });
};

const removeHealthSurveyReportList = (targetObjectId, parameters) => {
  const url = `${PREFIX}classDailyHealthSurveyManager/removeHealthSurveyReportList/classDailyHealthSurveyId/healthSurveyReportIds/tokensExpr/`;
  const requestParameters = {
    ...parameters,
    classDailyHealthSurveyId: targetObjectId,
    tokensExpr: 'none',
  };
  return postForm({ url, requestParameters });
};

// Filter this out when no functions

const listFunctions = () => {
  return get({
    url: `${PREFIX}classDailyHealthSurveyService/listFunctions/`,
  });
};

const saveRequest = data => {
  return put({
    url: `${PREFIX}classDailyHealthSurveyService/save/`,
    data,
  });
};

const processRequest = data => {
  return put({
    url: `${PREFIX}classDailyHealthSurveyService/process/`,
    data,
  });
};

const ClassDailyHealthSurveyService = {
  view,
  load,
  addDailySurveyQuestion,
  addStudentHealthSurvey,
  addHealthSurveyReport,
  updateDailySurveyQuestion,
  updateStudentHealthSurvey,
  updateHealthSurveyReport,
  removeDailySurveyQuestionList,
  removeStudentHealthSurveyList,
  removeHealthSurveyReportList,
  requestCandidateTeacher,
  requestCandidateCreator,
  requestCandidateChangeRequest,
  transferToAnotherTeacher,
  transferToAnotherCreator,
  transferToAnotherChangeRequest,
  listFunctions,
  saveRequest,
  processRequest,
};
export default ClassDailyHealthSurveyService;
