
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}studentHealthSurveyManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}studentHealthSurveyManager/loadStudentHealthSurvey/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateStudent = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}studentHealthSurveyManager/requestCandidateStudent/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherStudent = (id, parameters) => {
  const url = `${PREFIX}studentHealthSurveyManager/transferToAnotherStudent/id/anotherStudentId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateSurveyStatus = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}studentHealthSurveyManager/requestCandidateSurveyStatus/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherSurveyStatus = (id, parameters) => {
  const url = `${PREFIX}studentHealthSurveyManager/transferToAnotherSurveyStatus/id/anotherSurveyStatusId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateSchoolClass = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}studentHealthSurveyManager/requestCandidateSchoolClass/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherSchoolClass = (id, parameters) => {
  const url = `${PREFIX}studentHealthSurveyManager/transferToAnotherSchoolClass/id/anotherSchoolClassId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateClassDailyHealthSurvey = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}studentHealthSurveyManager/requestCandidateClassDailyHealthSurvey/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherClassDailyHealthSurvey = (id, parameters) => {
  const url = `${PREFIX}studentHealthSurveyManager/transferToAnotherClassDailyHealthSurvey/id/anotherClassDailyHealthSurveyId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateCq = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}studentHealthSurveyManager/requestCandidateCq/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherCq = (id, parameters) => {
  const url = `${PREFIX}studentHealthSurveyManager/transferToAnotherCq/id/anotherCqId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addStudentDailyAnswer = (targetObjectId, parameters) => {
  const url = `${PREFIX}studentHealthSurveyManager/addStudentDailyAnswer/studentHealthSurveyId/questionId/answer/cqId/tokensExpr/`
  const studentHealthSurveyId = targetObjectId
  const requestParameters = { ...parameters, studentHealthSurveyId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateStudentDailyAnswer = (targetObjectId, parameters) => {
  const url = `${PREFIX}studentHealthSurveyManager/updateStudentDailyAnswerProperties/studentHealthSurveyId/id/answer/tokensExpr/`
  const studentHealthSurveyId = targetObjectId
  const requestParameters = { ...parameters, studentHealthSurveyId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeStudentDailyAnswerList = (targetObjectId, parameters) => {
  const url = `${PREFIX}studentHealthSurveyManager/removeStudentDailyAnswerList/studentHealthSurveyId/studentDailyAnswerIds/tokensExpr/`
  const requestParameters = { ...parameters, studentHealthSurveyId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}studentHealthSurveyService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}studentHealthSurveyService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}studentHealthSurveyService/process/`,
    data,
  })
}

const StudentHealthSurveyService = { view,
  load,
  addStudentDailyAnswer,
  updateStudentDailyAnswer,
  removeStudentDailyAnswerList,
  requestCandidateStudent,
  requestCandidateSurveyStatus,
  requestCandidateSchoolClass,
  requestCandidateClassDailyHealthSurvey,
  requestCandidateCq,
  transferToAnotherStudent,
  transferToAnotherSurveyStatus,
  transferToAnotherSchoolClass,
  transferToAnotherClassDailyHealthSurvey,
  transferToAnotherCq, listFunctions, saveRequest, processRequest}
export default StudentHealthSurveyService

