
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}studentDailyAnswerManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}studentDailyAnswerManager/loadStudentDailyAnswer/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateStudentHealthSurvey = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}studentDailyAnswerManager/requestCandidateStudentHealthSurvey/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherStudentHealthSurvey = (id, parameters) => {
  const url = `${PREFIX}studentDailyAnswerManager/transferToAnotherStudentHealthSurvey/id/anotherStudentHealthSurveyId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateQuestion = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}studentDailyAnswerManager/requestCandidateQuestion/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherQuestion = (id, parameters) => {
  const url = `${PREFIX}studentDailyAnswerManager/transferToAnotherQuestion/id/anotherQuestionId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateCq = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}studentDailyAnswerManager/requestCandidateCq/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherCq = (id, parameters) => {
  const url = `${PREFIX}studentDailyAnswerManager/transferToAnotherCq/id/anotherCqId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}studentDailyAnswerService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}studentDailyAnswerService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}studentDailyAnswerService/process/`,
    data,
  })
}

const StudentDailyAnswerService = { view,
  load,
  requestCandidateStudentHealthSurvey,
  requestCandidateQuestion,
  requestCandidateCq,
  transferToAnotherStudentHealthSurvey,
  transferToAnotherQuestion,
  transferToAnotherCq, listFunctions, saveRequest, processRequest}
export default StudentDailyAnswerService
