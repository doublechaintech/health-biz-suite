
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}studentManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}studentManager/loadStudent/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateGuardian = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}studentManager/requestCandidateGuardian/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherGuardian = (id, parameters) => {
  const url = `${PREFIX}studentManager/transferToAnotherGuardian/id/anotherGuardianId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateSchoolClass = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}studentManager/requestCandidateSchoolClass/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherSchoolClass = (id, parameters) => {
  const url = `${PREFIX}studentManager/transferToAnotherSchoolClass/id/anotherSchoolClassId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateCq = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}studentManager/requestCandidateCq/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherCq = (id, parameters) => {
  const url = `${PREFIX}studentManager/transferToAnotherCq/id/anotherCqId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addStudentHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}studentManager/addStudentHealthSurvey/studentId/answerTime/surveyStatusId/schoolClassId/classDailyHealthSurveyId/cqId/tokensExpr/`
  const studentId = targetObjectId
  const requestParameters = { ...parameters, studentId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateStudentHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}studentManager/updateStudentHealthSurveyProperties/studentId/id/answerTime/tokensExpr/`
  const studentId = targetObjectId
  const requestParameters = { ...parameters, studentId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeStudentHealthSurveyList = (targetObjectId, parameters) => {
  const url = `${PREFIX}studentManager/removeStudentHealthSurveyList/studentId/studentHealthSurveyIds/tokensExpr/`
  const requestParameters = { ...parameters, studentId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}studentService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}studentService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}studentService/process/`,
    data,
  })
}

const StudentService = { view,
  load,
  addStudentHealthSurvey,
  updateStudentHealthSurvey,
  removeStudentHealthSurveyList,
  requestCandidateGuardian,
  requestCandidateSchoolClass,
  requestCandidateCq,
  transferToAnotherGuardian,
  transferToAnotherSchoolClass,
  transferToAnotherCq, listFunctions, saveRequest, processRequest}
export default StudentService

