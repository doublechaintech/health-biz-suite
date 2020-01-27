
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}cityManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}cityManager/loadCity/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateProvince = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}cityManager/requestCandidateProvince/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherProvince = (id, parameters) => {
  const url = `${PREFIX}cityManager/transferToAnotherProvince/id/anotherProvinceId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}cityManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  const url = `${PREFIX}cityManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addDistrict = (targetObjectId, parameters) => {
  const url = `${PREFIX}cityManager/addDistrict/cityId/name/platformId/tokensExpr/`
  const cityId = targetObjectId
  const requestParameters = { ...parameters, cityId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateDistrict = (targetObjectId, parameters) => {
  const url = `${PREFIX}cityManager/updateDistrictProperties/cityId/id/name/tokensExpr/`
  const cityId = targetObjectId
  const requestParameters = { ...parameters, cityId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeDistrictList = (targetObjectId, parameters) => {
  const url = `${PREFIX}cityManager/removeDistrictList/cityId/districtIds/tokensExpr/`
  const requestParameters = { ...parameters, cityId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}cityService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}cityService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}cityService/process/`,
    data,
  })
}

const CityService = { view,
  load,
  addDistrict,
  updateDistrict,
  removeDistrictList,
  requestCandidateProvince,
  requestCandidatePlatform,
  transferToAnotherProvince,
  transferToAnotherPlatform, listFunctions, saveRequest, processRequest}
export default CityService

