import { get, put, postForm, PREFIX, joinParameters, joinPostParameters } from '../../axios/tools';

const view = targetObjectId => {
  return get({
    url: `${PREFIX}teacherManager/view/${targetObjectId}/`,
  });
};

const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters);
  return get({
    url: `${PREFIX}teacherManager/loadTeacher/${targetObjectId}/${parametersExpr}/`,
  });
};

const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
  const url = `${PREFIX}teacherManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`;
  const requestParameters = { id, ownerClass, filterKey, pageNo };
  return postForm({ url, requestParameters });
};

const transferToAnotherPlatform = (id, parameters) => {
  const url = `${PREFIX}teacherManager/transferToAnotherPlatform/id/anotherPlatformId/`;
  const requestParameters = { id, ...parameters };
  return postForm({ url, requestParameters });
};

const requestCandidateUser = (ownerClass, id, filterKey, pageNo) => {
  const url = `${PREFIX}teacherManager/requestCandidateUser/ownerClass/id/filterKey/pageNo/`;
  const requestParameters = { id, ownerClass, filterKey, pageNo };
  return postForm({ url, requestParameters });
};

const transferToAnotherUser = (id, parameters) => {
  const url = `${PREFIX}teacherManager/transferToAnotherUser/id/anotherUserId/`;
  const requestParameters = { id, ...parameters };
  return postForm({ url, requestParameters });
};

const requestCandidateChangeRequest = (ownerClass, id, filterKey, pageNo) => {
  const url = `${PREFIX}teacherManager/requestCandidateChangeRequest/ownerClass/id/filterKey/pageNo/`;
  const requestParameters = { id, ownerClass, filterKey, pageNo };
  return postForm({ url, requestParameters });
};

const transferToAnotherChangeRequest = (id, parameters) => {
  const url = `${PREFIX}teacherManager/transferToAnotherChangeRequest/id/anotherChangeRequestId/`;
  const requestParameters = { id, ...parameters };
  return postForm({ url, requestParameters });
};

const addClassDailyHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}teacherManager/addClassDailyHealthSurvey/teacherId/name/surveyTime/creatorId/changeRequestId/tokensExpr/`;
  const teacherId = targetObjectId;
  const requestParameters = { ...parameters, teacherId, tokensExpr: 'none' };
  return postForm({ url, requestParameters });
};

const updateClassDailyHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}teacherManager/updateClassDailyHealthSurveyProperties/teacherId/id/name/surveyTime/tokensExpr/`;
  const teacherId = targetObjectId;
  const requestParameters = { ...parameters, teacherId, tokensExpr: 'none' };
  return postForm({ url, requestParameters });
};

const removeClassDailyHealthSurveyList = (targetObjectId, parameters) => {
  const url = `${PREFIX}teacherManager/removeClassDailyHealthSurveyList/teacherId/classDailyHealthSurveyIds/tokensExpr/`;
  const requestParameters = { ...parameters, teacherId: targetObjectId, tokensExpr: 'none' };
  return postForm({ url, requestParameters });
};

const addStudentHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}teacherManager/addStudentHealthSurvey/teacherId/studentId/answerTime/surveyStatusId/classDailyHealthSurveyId/changeRequestId/tokensExpr/`;
  const teacherId = targetObjectId;
  const requestParameters = { ...parameters, teacherId, tokensExpr: 'none' };
  return postForm({ url, requestParameters });
};

const updateStudentHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}teacherManager/updateStudentHealthSurveyProperties/teacherId/id/answerTime/tokensExpr/`;
  const teacherId = targetObjectId;
  const requestParameters = { ...parameters, teacherId, tokensExpr: 'none' };
  return postForm({ url, requestParameters });
};

const removeStudentHealthSurveyList = (targetObjectId, parameters) => {
  const url = `${PREFIX}teacherManager/removeStudentHealthSurveyList/teacherId/studentHealthSurveyIds/tokensExpr/`;
  const requestParameters = { ...parameters, teacherId: targetObjectId, tokensExpr: 'none' };
  return postForm({ url, requestParameters });
};

const addHealthSurveyReport = (targetObjectId, parameters) => {
  const url = `${PREFIX}teacherManager/addHealthSurveyReport/teacherId/surveyName/surveyTime/teacherName/school/schoolClass/studentName/studentNumber/guardianName/guardianMobile/studentId/surveyId/tokensExpr/`;
  const teacherId = targetObjectId;
  const requestParameters = { ...parameters, teacherId, tokensExpr: 'none' };
  return postForm({ url, requestParameters });
};

const updateHealthSurveyReport = (targetObjectId, parameters) => {
  const url = `${PREFIX}teacherManager/updateHealthSurveyReportProperties/teacherId/id/surveyName/surveyTime/teacherName/school/schoolClass/studentName/studentNumber/guardianName/guardianMobile/tokensExpr/`;
  const teacherId = targetObjectId;
  const requestParameters = { ...parameters, teacherId, tokensExpr: 'none' };
  return postForm({ url, requestParameters });
};

const removeHealthSurveyReportList = (targetObjectId, parameters) => {
  const url = `${PREFIX}teacherManager/removeHealthSurveyReportList/teacherId/healthSurveyReportIds/tokensExpr/`;
  const requestParameters = { ...parameters, teacherId: targetObjectId, tokensExpr: 'none' };
  return postForm({ url, requestParameters });
};

// Filter this out when no functions

const listFunctions = () => {
  return get({
    url: `${PREFIX}teacherService/listFunctions/`,
  });
};

const saveRequest = data => {
  return put({
    url: `${PREFIX}teacherService/save/`,
    data,
  });
};

const processRequest = data => {
  return put({
    url: `${PREFIX}teacherService/process/`,
    data,
  });
};

const TeacherService = {
  view,
  load,
  addClassDailyHealthSurvey,
  addStudentHealthSurvey,
  addHealthSurveyReport,
  updateClassDailyHealthSurvey,
  updateStudentHealthSurvey,
  updateHealthSurveyReport,
  removeClassDailyHealthSurveyList,
  removeStudentHealthSurveyList,
  removeHealthSurveyReportList,
  requestCandidatePlatform,
  requestCandidateUser,
  requestCandidateChangeRequest,
  transferToAnotherPlatform,
  transferToAnotherUser,
  transferToAnotherChangeRequest,
  listFunctions,
  saveRequest,
  processRequest,
};
export default TeacherService;
