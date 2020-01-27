
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}userAnswerManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}userAnswerManager/loadUserAnswer/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateQuestion = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}userAnswerManager/requestCandidateQuestion/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherQuestion = (id, parameters) => {
  const url = `${PREFIX}userAnswerManager/transferToAnotherQuestion/id/anotherQuestionId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addDailyAnswer = (targetObjectId, parameters) => {
  const url = `${PREFIX}userAnswerManager/addDailyAnswer/userAnswerId/name/userDailyAnswerId/tokensExpr/`
  const userAnswerId = targetObjectId
  const requestParameters = { ...parameters, userAnswerId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateDailyAnswer = (targetObjectId, parameters) => {
  const url = `${PREFIX}userAnswerManager/updateDailyAnswerProperties/userAnswerId/id/name/tokensExpr/`
  const userAnswerId = targetObjectId
  const requestParameters = { ...parameters, userAnswerId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeDailyAnswerList = (targetObjectId, parameters) => {
  const url = `${PREFIX}userAnswerManager/removeDailyAnswerList/userAnswerId/dailyAnswerIds/tokensExpr/`
  const requestParameters = { ...parameters, userAnswerId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addAnswerQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}userAnswerManager/addAnswerQuestion/userAnswerId/nickName/userId/answer/changeRequestId/tokensExpr/`
  const userAnswerId = targetObjectId
  const requestParameters = { ...parameters, userAnswerId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateAnswerQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}userAnswerManager/updateAnswerQuestionProperties/userAnswerId/id/nickName/answer/tokensExpr/`
  const userAnswerId = targetObjectId
  const requestParameters = { ...parameters, userAnswerId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeAnswerQuestionList = (targetObjectId, parameters) => {
  const url = `${PREFIX}userAnswerManager/removeAnswerQuestionList/userAnswerId/answerQuestionIds/tokensExpr/`
  const requestParameters = { ...parameters, userAnswerId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}userAnswerService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}userAnswerService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}userAnswerService/process/`,
    data,
  })
}

const UserAnswerService = { view,
  load,
  addDailyAnswer,
  addAnswerQuestion,
  updateDailyAnswer,
  updateAnswerQuestion,
  removeDailyAnswerList,
  removeAnswerQuestionList,
  requestCandidateQuestion,
  transferToAnotherQuestion, listFunctions, saveRequest, processRequest}
export default UserAnswerService

