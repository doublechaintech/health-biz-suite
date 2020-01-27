
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}parentManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}parentManager/loadParent/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateAddress = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}parentManager/requestCandidateAddress/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherAddress = (id, parameters) => {
  const url = `${PREFIX}parentManager/transferToAnotherAddress/id/anotherAddressId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateWechatUser = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}parentManager/requestCandidateWechatUser/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherWechatUser = (id, parameters) => {
  const url = `${PREFIX}parentManager/transferToAnotherWechatUser/id/anotherWechatUserId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}parentManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  const url = `${PREFIX}parentManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateCq = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}parentManager/requestCandidateCq/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherCq = (id, parameters) => {
  const url = `${PREFIX}parentManager/transferToAnotherCq/id/anotherCqId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addStudent = (targetObjectId, parameters) => {
  const url = `${PREFIX}parentManager/addStudent/parentId/name/gender/schooleClassId/studentId/cqId/tokensExpr/`
  const parentId = targetObjectId
  const requestParameters = { ...parameters, parentId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateStudent = (targetObjectId, parameters) => {
  const url = `${PREFIX}parentManager/updateStudentProperties/parentId/id/name/gender/studentId/tokensExpr/`
  const parentId = targetObjectId
  const requestParameters = { ...parameters, parentId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeStudentList = (targetObjectId, parameters) => {
  const url = `${PREFIX}parentManager/removeStudentList/parentId/studentIds/tokensExpr/`
  const requestParameters = { ...parameters, parentId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}parentService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}parentService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}parentService/process/`,
    data,
  })
}

const ParentService = { view,
  load,
  addStudent,
  updateStudent,
  removeStudentList,
  requestCandidateAddress,
  requestCandidateWechatUser,
  requestCandidatePlatform,
  requestCandidateCq,
  transferToAnotherAddress,
  transferToAnotherWechatUser,
  transferToAnotherPlatform,
  transferToAnotherCq, listFunctions, saveRequest, processRequest}
export default ParentService

