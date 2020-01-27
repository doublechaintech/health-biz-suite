
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}supervisorManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}supervisorManager/loadSupervisor/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}supervisorManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  const url = `${PREFIX}supervisorManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addOrganization = (targetObjectId, parameters) => {
  const url = `${PREFIX}supervisorManager/addOrganization/supervisorId/name/districtId/platformId/tokensExpr/`
  const supervisorId = targetObjectId
  const requestParameters = { ...parameters, supervisorId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateOrganization = (targetObjectId, parameters) => {
  const url = `${PREFIX}supervisorManager/updateOrganizationProperties/supervisorId/id/name/tokensExpr/`
  const supervisorId = targetObjectId
  const requestParameters = { ...parameters, supervisorId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeOrganizationList = (targetObjectId, parameters) => {
  const url = `${PREFIX}supervisorManager/removeOrganizationList/supervisorId/organizationIds/tokensExpr/`
  const requestParameters = { ...parameters, supervisorId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}supervisorService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}supervisorService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}supervisorService/process/`,
    data,
  })
}

const SupervisorService = { view,
  load,
  addOrganization,
  updateOrganization,
  removeOrganizationList,
  requestCandidatePlatform,
  transferToAnotherPlatform, listFunctions, saveRequest, processRequest}
export default SupervisorService

