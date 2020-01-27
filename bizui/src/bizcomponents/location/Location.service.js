
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}locationManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}locationManager/loadLocation/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateDistrict = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}locationManager/requestCandidateDistrict/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherDistrict = (id, parameters) => {
  const url = `${PREFIX}locationManager/transferToAnotherDistrict/id/anotherDistrictId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateProvince = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}locationManager/requestCandidateProvince/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherProvince = (id, parameters) => {
  const url = `${PREFIX}locationManager/transferToAnotherProvince/id/anotherProvinceId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addGuardian = (targetObjectId, parameters) => {
  const url = `${PREFIX}locationManager/addGuardian/locationId/name/mobile/wechatUserId/platformId/cqId/tokensExpr/`
  const locationId = targetObjectId
  const requestParameters = { ...parameters, locationId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateGuardian = (targetObjectId, parameters) => {
  const url = `${PREFIX}locationManager/updateGuardianProperties/locationId/id/name/mobile/tokensExpr/`
  const locationId = targetObjectId
  const requestParameters = { ...parameters, locationId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeGuardianList = (targetObjectId, parameters) => {
  const url = `${PREFIX}locationManager/removeGuardianList/locationId/guardianIds/tokensExpr/`
  const requestParameters = { ...parameters, locationId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addWechatUser = (targetObjectId, parameters) => {
  const url = `${PREFIX}locationManager/addWechatUser/locationId/name/avatar/userTypeId/platformId/tokensExpr/`
  const locationId = targetObjectId
  const requestParameters = { ...parameters, locationId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateWechatUser = (targetObjectId, parameters) => {
  const url = `${PREFIX}locationManager/updateWechatUserProperties/locationId/id/name/avatar/tokensExpr/`
  const locationId = targetObjectId
  const requestParameters = { ...parameters, locationId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeWechatUserList = (targetObjectId, parameters) => {
  const url = `${PREFIX}locationManager/removeWechatUserList/locationId/wechatUserIds/tokensExpr/`
  const requestParameters = { ...parameters, locationId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}locationService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}locationService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}locationService/process/`,
    data,
  })
}

const LocationService = { view,
  load,
  addGuardian,
  addWechatUser,
  updateGuardian,
  updateWechatUser,
  removeGuardianList,
  removeWechatUserList,
  requestCandidateDistrict,
  requestCandidateProvince,
  transferToAnotherDistrict,
  transferToAnotherProvince, listFunctions, saveRequest, processRequest}
export default LocationService

