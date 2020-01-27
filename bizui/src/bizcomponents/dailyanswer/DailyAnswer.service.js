
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}dailyAnswerManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}dailyAnswerManager/loadDailyAnswer/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateUserDailyAnswer = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}dailyAnswerManager/requestCandidateUserDailyAnswer/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherUserDailyAnswer = (id, parameters) => {
  const url = `${PREFIX}dailyAnswerManager/transferToAnotherUserDailyAnswer/id/anotherUserDailyAnswerId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateUserAnswer = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}dailyAnswerManager/requestCandidateUserAnswer/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherUserAnswer = (id, parameters) => {
  const url = `${PREFIX}dailyAnswerManager/transferToAnotherUserAnswer/id/anotherUserAnswerId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}dailyAnswerService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}dailyAnswerService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}dailyAnswerService/process/`,
    data,
  })
}

const DailyAnswerService = { view,
  load,
  requestCandidateUserDailyAnswer,
  requestCandidateUserAnswer,
  transferToAnotherUserDailyAnswer,
  transferToAnotherUserAnswer, listFunctions, saveRequest, processRequest}
export default DailyAnswerService

