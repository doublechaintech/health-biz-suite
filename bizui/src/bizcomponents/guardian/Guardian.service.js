
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}guardianManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}guardianManager/loadGuardian/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateAddress = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}guardianManager/requestCandidateAddress/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherAddress = (id, parameters) => {
  const url = `${PREFIX}guardianManager/transferToAnotherAddress/id/anotherAddressId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateWechatUser = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}guardianManager/requestCandidateWechatUser/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherWechatUser = (id, parameters) => {
  const url = `${PREFIX}guardianManager/transferToAnotherWechatUser/id/anotherWechatUserId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}guardianManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  const url = `${PREFIX}guardianManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateCq = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}guardianManager/requestCandidateCq/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherCq = (id, parameters) => {
  const url = `${PREFIX}guardianManager/transferToAnotherCq/id/anotherCqId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addStudent = (targetObjectId, parameters) => {
  const url = `${PREFIX}guardianManager/addStudent/guardianId/name/gender/schoolClassId/studentId/cqId/tokensExpr/`
  const guardianId = targetObjectId
  const requestParameters = { ...parameters, guardianId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateStudent = (targetObjectId, parameters) => {
  const url = `${PREFIX}guardianManager/updateStudentProperties/guardianId/id/name/gender/studentId/tokensExpr/`
  const guardianId = targetObjectId
  const requestParameters = { ...parameters, guardianId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeStudentList = (targetObjectId, parameters) => {
  const url = `${PREFIX}guardianManager/removeStudentList/guardianId/studentIds/tokensExpr/`
  const requestParameters = { ...parameters, guardianId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}guardianService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}guardianService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}guardianService/process/`,
    data,
  })
}

const GuardianService = { view,
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
export default GuardianService

