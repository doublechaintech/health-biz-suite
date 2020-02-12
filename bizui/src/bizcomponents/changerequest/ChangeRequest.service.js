
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}changeRequestManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}changeRequestManager/loadChangeRequest/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateRequestType = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}changeRequestManager/requestCandidateRequestType/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherRequestType = (id, parameters) => {
  const url = `${PREFIX}changeRequestManager/transferToAnotherRequestType/id/anotherRequestTypeId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}changeRequestManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  const url = `${PREFIX}changeRequestManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addTeacher = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/addTeacher/changeRequestId/name/mobile/school/schoolClass/classSize/platformId/userId/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateTeacher = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/updateTeacherProperties/changeRequestId/id/name/mobile/school/schoolClass/classSize/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeTeacherList = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/removeTeacherList/changeRequestId/teacherIds/tokensExpr/`
  const requestParameters = { ...parameters, changeRequestId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/addQuestion/changeRequestId/topic/questionTypeId/optionA/optionB/optionC/optionD/platformId/creatorId/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/updateQuestionProperties/changeRequestId/id/topic/optionA/optionB/optionC/optionD/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeQuestionList = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/removeQuestionList/changeRequestId/questionIds/tokensExpr/`
  const requestParameters = { ...parameters, changeRequestId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addClassDailyHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/addClassDailyHealthSurvey/changeRequestId/name/teacherId/surveyTime/creatorId/downloadUrl/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateClassDailyHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/updateClassDailyHealthSurveyProperties/changeRequestId/id/name/surveyTime/downloadUrl/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeClassDailyHealthSurveyList = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/removeClassDailyHealthSurveyList/changeRequestId/classDailyHealthSurveyIds/tokensExpr/`
  const requestParameters = { ...parameters, changeRequestId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addStudentHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/addStudentHealthSurvey/changeRequestId/studentId/answerTime/surveyStatusId/teacherId/classDailyHealthSurveyId/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateStudentHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/updateStudentHealthSurveyProperties/changeRequestId/id/answerTime/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeStudentHealthSurveyList = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/removeStudentHealthSurveyList/changeRequestId/studentHealthSurveyIds/tokensExpr/`
  const requestParameters = { ...parameters, changeRequestId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}changeRequestService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}changeRequestService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}changeRequestService/process/`,
    data,
  })
}

const ChangeRequestService = { view,
  load,
  addTeacher,
  addQuestion,
  addClassDailyHealthSurvey,
  addStudentHealthSurvey,
  updateTeacher,
  updateQuestion,
  updateClassDailyHealthSurvey,
  updateStudentHealthSurvey,
  removeTeacherList,
  removeQuestionList,
  removeClassDailyHealthSurveyList,
  removeStudentHealthSurveyList,
  requestCandidateRequestType,
  requestCandidatePlatform,
  transferToAnotherRequestType,
  transferToAnotherPlatform, listFunctions, saveRequest, processRequest}
export default ChangeRequestService

