
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}organizationManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}organizationManager/loadOrganization/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateSupervisor = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}organizationManager/requestCandidateSupervisor/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherSupervisor = (id, parameters) => {
  const url = `${PREFIX}organizationManager/transferToAnotherSupervisor/id/anotherSupervisorId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateDistrict = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}organizationManager/requestCandidateDistrict/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherDistrict = (id, parameters) => {
  const url = `${PREFIX}organizationManager/transferToAnotherDistrict/id/anotherDistrictId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}organizationManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  const url = `${PREFIX}organizationManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}organizationManager/addQuestion/organizationId/topic/description/questionTypeId/optionA/optionB/optionC/optionD/platformId/tokensExpr/`
  const organizationId = targetObjectId
  const requestParameters = { ...parameters, organizationId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}organizationManager/updateQuestionProperties/organizationId/id/topic/description/optionA/optionB/optionC/optionD/tokensExpr/`
  const organizationId = targetObjectId
  const requestParameters = { ...parameters, organizationId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeQuestionList = (targetObjectId, parameters) => {
  const url = `${PREFIX}organizationManager/removeQuestionList/organizationId/questionIds/tokensExpr/`
  const requestParameters = { ...parameters, organizationId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addDailyHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}organizationManager/addDailyHealthSurvey/organizationId/name/surveyTime/tokensExpr/`
  const organizationId = targetObjectId
  const requestParameters = { ...parameters, organizationId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateDailyHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}organizationManager/updateDailyHealthSurveyProperties/organizationId/id/name/surveyTime/tokensExpr/`
  const organizationId = targetObjectId
  const requestParameters = { ...parameters, organizationId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeDailyHealthSurveyList = (targetObjectId, parameters) => {
  const url = `${PREFIX}organizationManager/removeDailyHealthSurveyList/organizationId/dailyHealthSurveyIds/tokensExpr/`
  const requestParameters = { ...parameters, organizationId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addUserDailyAnswer = (targetObjectId, parameters) => {
  const url = `${PREFIX}organizationManager/addUserDailyAnswer/organizationId/name/userId/answerTime/isAnswerSubmitted/dailyHealthSurveyId/tokensExpr/`
  const organizationId = targetObjectId
  const requestParameters = { ...parameters, organizationId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateUserDailyAnswer = (targetObjectId, parameters) => {
  const url = `${PREFIX}organizationManager/updateUserDailyAnswerProperties/organizationId/id/name/answerTime/isAnswerSubmitted/tokensExpr/`
  const organizationId = targetObjectId
  const requestParameters = { ...parameters, organizationId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeUserDailyAnswerList = (targetObjectId, parameters) => {
  const url = `${PREFIX}organizationManager/removeUserDailyAnswerList/organizationId/userDailyAnswerIds/tokensExpr/`
  const requestParameters = { ...parameters, organizationId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addWechatUser = (targetObjectId, parameters) => {
  const url = `${PREFIX}organizationManager/addWechatUser/organizationId/name/avatar/addressId/isRegisteredSurvey/platformId/tokensExpr/`
  const organizationId = targetObjectId
  const requestParameters = { ...parameters, organizationId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateWechatUser = (targetObjectId, parameters) => {
  const url = `${PREFIX}organizationManager/updateWechatUserProperties/organizationId/id/name/avatar/isRegisteredSurvey/tokensExpr/`
  const organizationId = targetObjectId
  const requestParameters = { ...parameters, organizationId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeWechatUserList = (targetObjectId, parameters) => {
  const url = `${PREFIX}organizationManager/removeWechatUserList/organizationId/wechatUserIds/tokensExpr/`
  const requestParameters = { ...parameters, organizationId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}organizationService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}organizationService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}organizationService/process/`,
    data,
  })
}

const OrganizationService = { view,
  load,
  addQuestion,
  addDailyHealthSurvey,
  addUserDailyAnswer,
  addWechatUser,
  updateQuestion,
  updateDailyHealthSurvey,
  updateUserDailyAnswer,
  updateWechatUser,
  removeQuestionList,
  removeDailyHealthSurveyList,
  removeUserDailyAnswerList,
  removeWechatUserList,
  requestCandidateSupervisor,
  requestCandidateDistrict,
  requestCandidatePlatform,
  transferToAnotherSupervisor,
  transferToAnotherDistrict,
  transferToAnotherPlatform, listFunctions, saveRequest, processRequest}
export default OrganizationService

