
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}userTypeManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}userTypeManager/loadUserType/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}userTypeManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  const url = `${PREFIX}userTypeManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addWechatUser = (targetObjectId, parameters) => {
  const url = `${PREFIX}userTypeManager/addWechatUser/userTypeId/name/avatar/addressId/platformId/tokensExpr/`
  const userTypeId = targetObjectId
  const requestParameters = { ...parameters, userTypeId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateWechatUser = (targetObjectId, parameters) => {
  const url = `${PREFIX}userTypeManager/updateWechatUserProperties/userTypeId/id/name/avatar/tokensExpr/`
  const userTypeId = targetObjectId
  const requestParameters = { ...parameters, userTypeId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeWechatUserList = (targetObjectId, parameters) => {
  const url = `${PREFIX}userTypeManager/removeWechatUserList/userTypeId/wechatUserIds/tokensExpr/`
  const requestParameters = { ...parameters, userTypeId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}userTypeService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}userTypeService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}userTypeService/process/`,
    data,
  })
}

const UserTypeService = { view,
  load,
  addWechatUser,
  updateWechatUser,
  removeWechatUserList,
  requestCandidatePlatform,
  transferToAnotherPlatform, listFunctions, saveRequest, processRequest}
export default UserTypeService

