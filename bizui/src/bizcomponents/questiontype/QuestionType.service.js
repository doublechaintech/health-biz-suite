
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}questionTypeManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}questionTypeManager/loadQuestionType/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}questionTypeManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  const url = `${PREFIX}questionTypeManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}questionTypeManager/addQuestion/questionTypeId/topic/optionA/optionB/optionC/optionD/platformId/tokensExpr/`
  const questionTypeId = targetObjectId
  const requestParameters = { ...parameters, questionTypeId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}questionTypeManager/updateQuestionProperties/questionTypeId/id/topic/optionA/optionB/optionC/optionD/tokensExpr/`
  const questionTypeId = targetObjectId
  const requestParameters = { ...parameters, questionTypeId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeQuestionList = (targetObjectId, parameters) => {
  const url = `${PREFIX}questionTypeManager/removeQuestionList/questionTypeId/questionIds/tokensExpr/`
  const requestParameters = { ...parameters, questionTypeId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addClassQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}questionTypeManager/addClassQuestion/questionTypeId/topic/optionA/optionB/optionC/optionD/questionSourceId/creatorId/cqId/tokensExpr/`
  const questionTypeId = targetObjectId
  const requestParameters = { ...parameters, questionTypeId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateClassQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}questionTypeManager/updateClassQuestionProperties/questionTypeId/id/topic/optionA/optionB/optionC/optionD/tokensExpr/`
  const questionTypeId = targetObjectId
  const requestParameters = { ...parameters, questionTypeId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeClassQuestionList = (targetObjectId, parameters) => {
  const url = `${PREFIX}questionTypeManager/removeClassQuestionList/questionTypeId/classQuestionIds/tokensExpr/`
  const requestParameters = { ...parameters, questionTypeId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addDailySurveyQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}questionTypeManager/addDailySurveyQuestion/questionTypeId/topic/optionA/optionB/optionC/optionD/classDailyHealthSurveyId/classQuestionId/tokensExpr/`
  const questionTypeId = targetObjectId
  const requestParameters = { ...parameters, questionTypeId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateDailySurveyQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}questionTypeManager/updateDailySurveyQuestionProperties/questionTypeId/id/topic/optionA/optionB/optionC/optionD/tokensExpr/`
  const questionTypeId = targetObjectId
  const requestParameters = { ...parameters, questionTypeId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeDailySurveyQuestionList = (targetObjectId, parameters) => {
  const url = `${PREFIX}questionTypeManager/removeDailySurveyQuestionList/questionTypeId/dailySurveyQuestionIds/tokensExpr/`
  const requestParameters = { ...parameters, questionTypeId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}questionTypeService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}questionTypeService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}questionTypeService/process/`,
    data,
  })
}

const QuestionTypeService = { view,
  load,
  addQuestion,
  addClassQuestion,
  addDailySurveyQuestion,
  updateQuestion,
  updateClassQuestion,
  updateDailySurveyQuestion,
  removeQuestionList,
  removeClassQuestionList,
  removeDailySurveyQuestionList,
  requestCandidatePlatform,
  transferToAnotherPlatform, listFunctions, saveRequest, processRequest}
export default QuestionTypeService

