
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}surveyQuestionManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}surveyQuestionManager/loadSurveyQuestion/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateSurvey = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}surveyQuestionManager/requestCandidateSurvey/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherSurvey = (id, parameters) => {
  const url = `${PREFIX}surveyQuestionManager/transferToAnotherSurvey/id/anotherSurveyId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateQuestion = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}surveyQuestionManager/requestCandidateQuestion/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherQuestion = (id, parameters) => {
  const url = `${PREFIX}surveyQuestionManager/transferToAnotherQuestion/id/anotherQuestionId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}surveyQuestionService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}surveyQuestionService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}surveyQuestionService/process/`,
    data,
  })
}

const SurveyQuestionService = { view,
  load,
  requestCandidateSurvey,
  requestCandidateQuestion,
  transferToAnotherSurvey,
  transferToAnotherQuestion, listFunctions, saveRequest, processRequest}
export default SurveyQuestionService

