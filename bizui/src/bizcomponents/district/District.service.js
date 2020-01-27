
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}districtManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}districtManager/loadDistrict/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateCity = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}districtManager/requestCandidateCity/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherCity = (id, parameters) => {
  const url = `${PREFIX}districtManager/transferToAnotherCity/id/anotherCityId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}districtManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  const url = `${PREFIX}districtManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addLocation = (targetObjectId, parameters) => {
  const url = `${PREFIX}districtManager/addLocation/districtId/name/address/provinceId/latitude/longitude/tokensExpr/`
  const districtId = targetObjectId
  const requestParameters = { ...parameters, districtId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateLocation = (targetObjectId, parameters) => {
  const url = `${PREFIX}districtManager/updateLocationProperties/districtId/id/name/address/latitude/longitude/tokensExpr/`
  const districtId = targetObjectId
  const requestParameters = { ...parameters, districtId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeLocationList = (targetObjectId, parameters) => {
  const url = `${PREFIX}districtManager/removeLocationList/districtId/locationIds/tokensExpr/`
  const requestParameters = { ...parameters, districtId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}districtService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}districtService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}districtService/process/`,
    data,
  })
}

const DistrictService = { view,
  load,
  addLocation,
  updateLocation,
  removeLocationList,
  requestCandidateCity,
  requestCandidatePlatform,
  transferToAnotherCity,
  transferToAnotherPlatform, listFunctions, saveRequest, processRequest}
export default DistrictService

