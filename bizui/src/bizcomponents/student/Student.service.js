
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



const requestCandidateAddress = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}studentManager/requestCandidateAddress/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherAddress = (id, parameters) => {
  const url = `${PREFIX}studentManager/transferToAnotherAddress/id/anotherAddressId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateUser = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}studentManager/requestCandidateUser/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherUser = (id, parameters) => {
  const url = `${PREFIX}studentManager/transferToAnotherUser/id/anotherUserId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}studentManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  const url = `${PREFIX}studentManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateChangeRequest = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}studentManager/requestCandidateChangeRequest/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherChangeRequest = (id, parameters) => {
  const url = `${PREFIX}studentManager/transferToAnotherChangeRequest/id/anotherChangeRequestId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addStudentHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}studentManager/addStudentHealthSurvey/studentId/answerTime/surveyStatusId/teacherId/classDailyHealthSurveyId/changeRequestId/tokensExpr/`
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
  requestCandidateAddress,
  requestCandidateUser,
  requestCandidatePlatform,
  requestCandidateChangeRequest,
  transferToAnotherAddress,
  transferToAnotherUser,
  transferToAnotherPlatform,
  transferToAnotherChangeRequest, listFunctions, saveRequest, processRequest}
export default StudentService

