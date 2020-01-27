
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}surveyStatusManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}surveyStatusManager/loadSurveyStatus/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}surveyStatusManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  const url = `${PREFIX}surveyStatusManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addStudentHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}surveyStatusManager/addStudentHealthSurvey/surveyStatusId/studentId/answerTime/teacherId/classDailyHealthSurveyId/changeRequestId/tokensExpr/`
  const surveyStatusId = targetObjectId
  const requestParameters = { ...parameters, surveyStatusId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateStudentHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}surveyStatusManager/updateStudentHealthSurveyProperties/surveyStatusId/id/answerTime/tokensExpr/`
  const surveyStatusId = targetObjectId
  const requestParameters = { ...parameters, surveyStatusId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeStudentHealthSurveyList = (targetObjectId, parameters) => {
  const url = `${PREFIX}surveyStatusManager/removeStudentHealthSurveyList/surveyStatusId/studentHealthSurveyIds/tokensExpr/`
  const requestParameters = { ...parameters, surveyStatusId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}surveyStatusService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}surveyStatusService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}surveyStatusService/process/`,
    data,
  })
}

const SurveyStatusService = { view,
  load,
  addStudentHealthSurvey,
  updateStudentHealthSurvey,
  removeStudentHealthSurveyList,
  requestCandidatePlatform,
  transferToAnotherPlatform, listFunctions, saveRequest, processRequest}
export default SurveyStatusService

