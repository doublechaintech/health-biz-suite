
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}healthSurveyReportManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}healthSurveyReportManager/loadHealthSurveyReport/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateStudent = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}healthSurveyReportManager/requestCandidateStudent/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherStudent = (id, parameters) => {
  const url = `${PREFIX}healthSurveyReportManager/transferToAnotherStudent/id/anotherStudentId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateTeacher = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}healthSurveyReportManager/requestCandidateTeacher/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherTeacher = (id, parameters) => {
  const url = `${PREFIX}healthSurveyReportManager/transferToAnotherTeacher/id/anotherTeacherId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateSurvey = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}healthSurveyReportManager/requestCandidateSurvey/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherSurvey = (id, parameters) => {
  const url = `${PREFIX}healthSurveyReportManager/transferToAnotherSurvey/id/anotherSurveyId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addStudentAnswer = (targetObjectId, parameters) => {
  const url = `${PREFIX}healthSurveyReportManager/addStudentAnswer/healthSurveyReportId/dailyAnswerId/questionTopic/answer/tokensExpr/`
  const healthSurveyReportId = targetObjectId
  const requestParameters = { ...parameters, healthSurveyReportId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateStudentAnswer = (targetObjectId, parameters) => {
  const url = `${PREFIX}healthSurveyReportManager/updateStudentAnswerProperties/healthSurveyReportId/id/questionTopic/answer/tokensExpr/`
  const healthSurveyReportId = targetObjectId
  const requestParameters = { ...parameters, healthSurveyReportId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeStudentAnswerList = (targetObjectId, parameters) => {
  const url = `${PREFIX}healthSurveyReportManager/removeStudentAnswerList/healthSurveyReportId/studentAnswerIds/tokensExpr/`
  const requestParameters = { ...parameters, healthSurveyReportId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}healthSurveyReportService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}healthSurveyReportService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}healthSurveyReportService/process/`,
    data,
  })
}

const HealthSurveyReportService = { view,
  load,
  addStudentAnswer,
  updateStudentAnswer,
  removeStudentAnswerList,
  requestCandidateStudent,
  requestCandidateTeacher,
  requestCandidateSurvey,
  transferToAnotherStudent,
  transferToAnotherTeacher,
  transferToAnotherSurvey, listFunctions, saveRequest, processRequest}
export default HealthSurveyReportService

