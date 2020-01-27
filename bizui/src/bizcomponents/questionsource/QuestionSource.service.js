
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}questionSourceManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}questionSourceManager/loadQuestionSource/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}questionSourceManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  const url = `${PREFIX}questionSourceManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addClassQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}questionSourceManager/addClassQuestion/questionSourceId/topic/questionTypeId/optionA/optionB/optionC/optionD/creatorId/cqId/tokensExpr/`
  const questionSourceId = targetObjectId
  const requestParameters = { ...parameters, questionSourceId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateClassQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}questionSourceManager/updateClassQuestionProperties/questionSourceId/id/topic/optionA/optionB/optionC/optionD/tokensExpr/`
  const questionSourceId = targetObjectId
  const requestParameters = { ...parameters, questionSourceId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeClassQuestionList = (targetObjectId, parameters) => {
  const url = `${PREFIX}questionSourceManager/removeClassQuestionList/questionSourceId/classQuestionIds/tokensExpr/`
  const requestParameters = { ...parameters, questionSourceId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}questionSourceService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}questionSourceService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}questionSourceService/process/`,
    data,
  })
}

const QuestionSourceService = { view,
  load,
  addClassQuestion,
  updateClassQuestion,
  removeClassQuestionList,
  requestCandidatePlatform,
  transferToAnotherPlatform, listFunctions, saveRequest, processRequest}
export default QuestionSourceService

