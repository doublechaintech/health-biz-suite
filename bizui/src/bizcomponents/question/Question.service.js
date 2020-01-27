
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}questionManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}questionManager/loadQuestion/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateQuestionType = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}questionManager/requestCandidateQuestionType/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherQuestionType = (id, parameters) => {
  const url = `${PREFIX}questionManager/transferToAnotherQuestionType/id/anotherQuestionTypeId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}questionManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  const url = `${PREFIX}questionManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateCreator = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}questionManager/requestCandidateCreator/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherCreator = (id, parameters) => {
  const url = `${PREFIX}questionManager/transferToAnotherCreator/id/anotherCreatorId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateCq = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}questionManager/requestCandidateCq/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherCq = (id, parameters) => {
  const url = `${PREFIX}questionManager/transferToAnotherCq/id/anotherCqId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addDailySurveyQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}questionManager/addDailySurveyQuestion/questionId/topic/questionTypeId/optionA/optionB/optionC/optionD/classDailyHealthSurveyId/tokensExpr/`
  const questionId = targetObjectId
  const requestParameters = { ...parameters, questionId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateDailySurveyQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}questionManager/updateDailySurveyQuestionProperties/questionId/id/topic/optionA/optionB/optionC/optionD/tokensExpr/`
  const questionId = targetObjectId
  const requestParameters = { ...parameters, questionId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeDailySurveyQuestionList = (targetObjectId, parameters) => {
  const url = `${PREFIX}questionManager/removeDailySurveyQuestionList/questionId/dailySurveyQuestionIds/tokensExpr/`
  const requestParameters = { ...parameters, questionId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}questionService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}questionService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}questionService/process/`,
    data,
  })
}

const QuestionService = { view,
  load,
  addDailySurveyQuestion,
  updateDailySurveyQuestion,
  removeDailySurveyQuestionList,
  requestCandidateQuestionType,
  requestCandidatePlatform,
  requestCandidateCreator,
  requestCandidateCq,
  transferToAnotherQuestionType,
  transferToAnotherPlatform,
  transferToAnotherCreator,
  transferToAnotherCq, listFunctions, saveRequest, processRequest}
export default QuestionService

