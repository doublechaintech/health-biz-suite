
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}userDailyAnswerManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}userDailyAnswerManager/loadUserDailyAnswer/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateUser = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}userDailyAnswerManager/requestCandidateUser/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherUser = (id, parameters) => {
  const url = `${PREFIX}userDailyAnswerManager/transferToAnotherUser/id/anotherUserId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateOrganization = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}userDailyAnswerManager/requestCandidateOrganization/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherOrganization = (id, parameters) => {
  const url = `${PREFIX}userDailyAnswerManager/transferToAnotherOrganization/id/anotherOrganizationId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateDailyHealthSurvey = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}userDailyAnswerManager/requestCandidateDailyHealthSurvey/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherDailyHealthSurvey = (id, parameters) => {
  const url = `${PREFIX}userDailyAnswerManager/transferToAnotherDailyHealthSurvey/id/anotherDailyHealthSurveyId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addDailyAnswer = (targetObjectId, parameters) => {
  const url = `${PREFIX}userDailyAnswerManager/addDailyAnswer/userDailyAnswerId/name/userAnswerId/tokensExpr/`
  const userDailyAnswerId = targetObjectId
  const requestParameters = { ...parameters, userDailyAnswerId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateDailyAnswer = (targetObjectId, parameters) => {
  const url = `${PREFIX}userDailyAnswerManager/updateDailyAnswerProperties/userDailyAnswerId/id/name/tokensExpr/`
  const userDailyAnswerId = targetObjectId
  const requestParameters = { ...parameters, userDailyAnswerId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeDailyAnswerList = (targetObjectId, parameters) => {
  const url = `${PREFIX}userDailyAnswerManager/removeDailyAnswerList/userDailyAnswerId/dailyAnswerIds/tokensExpr/`
  const requestParameters = { ...parameters, userDailyAnswerId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}userDailyAnswerService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}userDailyAnswerService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}userDailyAnswerService/process/`,
    data,
  })
}

const UserDailyAnswerService = { view,
  load,
  addDailyAnswer,
  updateDailyAnswer,
  removeDailyAnswerList,
  requestCandidateUser,
  requestCandidateOrganization,
  requestCandidateDailyHealthSurvey,
  transferToAnotherUser,
  transferToAnotherOrganization,
  transferToAnotherDailyHealthSurvey, listFunctions, saveRequest, processRequest}
export default UserDailyAnswerService

