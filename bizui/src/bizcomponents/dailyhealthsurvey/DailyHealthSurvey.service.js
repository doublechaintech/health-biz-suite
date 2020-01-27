
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}dailyHealthSurveyManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}dailyHealthSurveyManager/loadDailyHealthSurvey/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateOrganization = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}dailyHealthSurveyManager/requestCandidateOrganization/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherOrganization = (id, parameters) => {
  const url = `${PREFIX}dailyHealthSurveyManager/transferToAnotherOrganization/id/anotherOrganizationId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addSurveyQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}dailyHealthSurveyManager/addSurveyQuestion/dailyHealthSurveyId/name/questionId/tokensExpr/`
  const dailyHealthSurveyId = targetObjectId
  const requestParameters = { ...parameters, dailyHealthSurveyId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateSurveyQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}dailyHealthSurveyManager/updateSurveyQuestionProperties/dailyHealthSurveyId/id/name/tokensExpr/`
  const dailyHealthSurveyId = targetObjectId
  const requestParameters = { ...parameters, dailyHealthSurveyId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeSurveyQuestionList = (targetObjectId, parameters) => {
  const url = `${PREFIX}dailyHealthSurveyManager/removeSurveyQuestionList/dailyHealthSurveyId/surveyQuestionIds/tokensExpr/`
  const requestParameters = { ...parameters, dailyHealthSurveyId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addUserDailyAnswer = (targetObjectId, parameters) => {
  const url = `${PREFIX}dailyHealthSurveyManager/addUserDailyAnswer/dailyHealthSurveyId/name/userId/answerTime/isAnswerSubmitted/organizationId/tokensExpr/`
  const dailyHealthSurveyId = targetObjectId
  const requestParameters = { ...parameters, dailyHealthSurveyId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateUserDailyAnswer = (targetObjectId, parameters) => {
  const url = `${PREFIX}dailyHealthSurveyManager/updateUserDailyAnswerProperties/dailyHealthSurveyId/id/name/answerTime/isAnswerSubmitted/tokensExpr/`
  const dailyHealthSurveyId = targetObjectId
  const requestParameters = { ...parameters, dailyHealthSurveyId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeUserDailyAnswerList = (targetObjectId, parameters) => {
  const url = `${PREFIX}dailyHealthSurveyManager/removeUserDailyAnswerList/dailyHealthSurveyId/userDailyAnswerIds/tokensExpr/`
  const requestParameters = { ...parameters, dailyHealthSurveyId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}dailyHealthSurveyService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}dailyHealthSurveyService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}dailyHealthSurveyService/process/`,
    data,
  })
}

const DailyHealthSurveyService = { view,
  load,
  addSurveyQuestion,
  addUserDailyAnswer,
  updateSurveyQuestion,
  updateUserDailyAnswer,
  removeSurveyQuestionList,
  removeUserDailyAnswerList,
  requestCandidateOrganization,
  transferToAnotherOrganization, listFunctions, saveRequest, processRequest}
export default DailyHealthSurveyService

