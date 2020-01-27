
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}dailySurveyQuestionManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}dailySurveyQuestionManager/loadDailySurveyQuestion/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateQuestionType = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}dailySurveyQuestionManager/requestCandidateQuestionType/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherQuestionType = (id, parameters) => {
  const url = `${PREFIX}dailySurveyQuestionManager/transferToAnotherQuestionType/id/anotherQuestionTypeId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateClassDailyHealthSurvey = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}dailySurveyQuestionManager/requestCandidateClassDailyHealthSurvey/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherClassDailyHealthSurvey = (id, parameters) => {
  const url = `${PREFIX}dailySurveyQuestionManager/transferToAnotherClassDailyHealthSurvey/id/anotherClassDailyHealthSurveyId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateClassQuestion = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}dailySurveyQuestionManager/requestCandidateClassQuestion/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherClassQuestion = (id, parameters) => {
  const url = `${PREFIX}dailySurveyQuestionManager/transferToAnotherClassQuestion/id/anotherClassQuestionId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addStudentDailyAnswer = (targetObjectId, parameters) => {
  const url = `${PREFIX}dailySurveyQuestionManager/addStudentDailyAnswer/dailySurveyQuestionId/studentHealthSurveyId/answer/cqId/tokensExpr/`
  const dailySurveyQuestionId = targetObjectId
  const requestParameters = { ...parameters, dailySurveyQuestionId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateStudentDailyAnswer = (targetObjectId, parameters) => {
  const url = `${PREFIX}dailySurveyQuestionManager/updateStudentDailyAnswerProperties/dailySurveyQuestionId/id/answer/tokensExpr/`
  const dailySurveyQuestionId = targetObjectId
  const requestParameters = { ...parameters, dailySurveyQuestionId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeStudentDailyAnswerList = (targetObjectId, parameters) => {
  const url = `${PREFIX}dailySurveyQuestionManager/removeStudentDailyAnswerList/dailySurveyQuestionId/studentDailyAnswerIds/tokensExpr/`
  const requestParameters = { ...parameters, dailySurveyQuestionId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}dailySurveyQuestionService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}dailySurveyQuestionService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}dailySurveyQuestionService/process/`,
    data,
  })
}

const DailySurveyQuestionService = { view,
  load,
  addStudentDailyAnswer,
  updateStudentDailyAnswer,
  removeStudentDailyAnswerList,
  requestCandidateQuestionType,
  requestCandidateClassDailyHealthSurvey,
  requestCandidateClassQuestion,
  transferToAnotherQuestionType,
  transferToAnotherClassDailyHealthSurvey,
  transferToAnotherClassQuestion, listFunctions, saveRequest, processRequest}
export default DailySurveyQuestionService

