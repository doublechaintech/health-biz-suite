
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}provinceManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}provinceManager/loadProvince/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}provinceManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  const url = `${PREFIX}provinceManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addCity = (targetObjectId, parameters) => {
  const url = `${PREFIX}provinceManager/addCity/provinceId/name/platformId/tokensExpr/`
  const provinceId = targetObjectId
  const requestParameters = { ...parameters, provinceId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateCity = (targetObjectId, parameters) => {
  const url = `${PREFIX}provinceManager/updateCityProperties/provinceId/id/name/tokensExpr/`
  const provinceId = targetObjectId
  const requestParameters = { ...parameters, provinceId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeCityList = (targetObjectId, parameters) => {
  const url = `${PREFIX}provinceManager/removeCityList/provinceId/cityIds/tokensExpr/`
  const requestParameters = { ...parameters, provinceId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addLocation = (targetObjectId, parameters) => {
  const url = `${PREFIX}provinceManager/addLocation/provinceId/name/address/districtId/latitude/longitude/tokensExpr/`
  const provinceId = targetObjectId
  const requestParameters = { ...parameters, provinceId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateLocation = (targetObjectId, parameters) => {
  const url = `${PREFIX}provinceManager/updateLocationProperties/provinceId/id/name/address/latitude/longitude/tokensExpr/`
  const provinceId = targetObjectId
  const requestParameters = { ...parameters, provinceId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeLocationList = (targetObjectId, parameters) => {
  const url = `${PREFIX}provinceManager/removeLocationList/provinceId/locationIds/tokensExpr/`
  const requestParameters = { ...parameters, provinceId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}provinceService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}provinceService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}provinceService/process/`,
    data,
  })
}

const ProvinceService = { view,
  load,
  addCity,
  addLocation,
  updateCity,
  updateLocation,
  removeCityList,
  removeLocationList,
  requestCandidatePlatform,
  transferToAnotherPlatform, listFunctions, saveRequest, processRequest}
export default ProvinceService

