
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
  requestCandidateQuestionType,
  requestCandidatePlatform,
  transferToAnotherQuestionType,
  transferToAnotherPlatform, listFunctions, saveRequest, processRequest}
export default QuestionService

