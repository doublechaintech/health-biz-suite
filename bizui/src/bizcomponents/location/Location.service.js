
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







const addStudent = (targetObjectId, parameters) => {
  const url = `${PREFIX}locationManager/addStudent/locationId/studentName/studentNumber/studentAvatar/guardianName/guardianMobile/userId/platformId/tokensExpr/`
  const locationId = targetObjectId
  const requestParameters = { ...parameters, locationId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateStudent = (targetObjectId, parameters) => {
  const url = `${PREFIX}locationManager/updateStudentProperties/locationId/id/studentName/studentNumber/studentAvatar/guardianName/guardianMobile/tokensExpr/`
  const locationId = targetObjectId
  const requestParameters = { ...parameters, locationId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeStudentList = (targetObjectId, parameters) => {
  const url = `${PREFIX}locationManager/removeStudentList/locationId/studentIds/tokensExpr/`
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
  addStudent,
  updateStudent,
  removeStudentList,
  requestCandidateDistrict,
  requestCandidateProvince,
  transferToAnotherDistrict,
  transferToAnotherProvince, listFunctions, saveRequest, processRequest}
export default LocationService

