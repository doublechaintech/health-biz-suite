
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}classQuestionManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}classQuestionManager/loadClassQuestion/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateQuestionType = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}classQuestionManager/requestCandidateQuestionType/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherQuestionType = (id, parameters) => {
  const url = `${PREFIX}classQuestionManager/transferToAnotherQuestionType/id/anotherQuestionTypeId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateQuestionSource = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}classQuestionManager/requestCandidateQuestionSource/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherQuestionSource = (id, parameters) => {
  const url = `${PREFIX}classQuestionManager/transferToAnotherQuestionSource/id/anotherQuestionSourceId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateCreator = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}classQuestionManager/requestCandidateCreator/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherCreator = (id, parameters) => {
  const url = `${PREFIX}classQuestionManager/transferToAnotherCreator/id/anotherCreatorId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateCq = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}classQuestionManager/requestCandidateCq/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherCq = (id, parameters) => {
  const url = `${PREFIX}classQuestionManager/transferToAnotherCq/id/anotherCqId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addDailySurveyQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}classQuestionManager/addDailySurveyQuestion/classQuestionId/topic/questionTypeId/optionA/optionB/optionC/optionD/classDailyHealthSurveyId/tokensExpr/`
  const classQuestionId = targetObjectId
  const requestParameters = { ...parameters, classQuestionId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateDailySurveyQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}classQuestionManager/updateDailySurveyQuestionProperties/classQuestionId/id/topic/optionA/optionB/optionC/optionD/tokensExpr/`
  const classQuestionId = targetObjectId
  const requestParameters = { ...parameters, classQuestionId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeDailySurveyQuestionList = (targetObjectId, parameters) => {
  const url = `${PREFIX}classQuestionManager/removeDailySurveyQuestionList/classQuestionId/dailySurveyQuestionIds/tokensExpr/`
  const requestParameters = { ...parameters, classQuestionId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}classQuestionService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}classQuestionService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}classQuestionService/process/`,
    data,
  })
}

const ClassQuestionService = { view,
  load,
  addDailySurveyQuestion,
  updateDailySurveyQuestion,
  removeDailySurveyQuestionList,
  requestCandidateQuestionType,
  requestCandidateQuestionSource,
  requestCandidateCreator,
  requestCandidateCq,
  transferToAnotherQuestionType,
  transferToAnotherQuestionSource,
  transferToAnotherCreator,
  transferToAnotherCq, listFunctions, saveRequest, processRequest}
export default ClassQuestionService

