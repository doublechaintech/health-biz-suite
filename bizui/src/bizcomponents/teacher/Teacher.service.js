
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}teacherManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}teacherManager/loadTeacher/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}teacherManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  const url = `${PREFIX}teacherManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateCq = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}teacherManager/requestCandidateCq/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherCq = (id, parameters) => {
  const url = `${PREFIX}teacherManager/transferToAnotherCq/id/anotherCqId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addSchoolClass = (targetObjectId, parameters) => {
  const url = `${PREFIX}teacherManager/addSchoolClass/teacherId/name/platformId/schoole/cqId/tokensExpr/`
  const teacherId = targetObjectId
  const requestParameters = { ...parameters, teacherId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateSchoolClass = (targetObjectId, parameters) => {
  const url = `${PREFIX}teacherManager/updateSchoolClassProperties/teacherId/id/name/schoole/tokensExpr/`
  const teacherId = targetObjectId
  const requestParameters = { ...parameters, teacherId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeSchoolClassList = (targetObjectId, parameters) => {
  const url = `${PREFIX}teacherManager/removeSchoolClassList/teacherId/schoolClassIds/tokensExpr/`
  const requestParameters = { ...parameters, teacherId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}teacherService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}teacherService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}teacherService/process/`,
    data,
  })
}

const TeacherService = { view,
  load,
  addSchoolClass,
  updateSchoolClass,
  removeSchoolClassList,
  requestCandidatePlatform,
  requestCandidateCq,
  transferToAnotherPlatform,
  transferToAnotherCq, listFunctions, saveRequest, processRequest}
export default TeacherService

