
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}schooleClassManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}schooleClassManager/loadSchooleClass/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateClassTeacher = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}schooleClassManager/requestCandidateClassTeacher/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherClassTeacher = (id, parameters) => {
  const url = `${PREFIX}schooleClassManager/transferToAnotherClassTeacher/id/anotherClassTeacherId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}schooleClassManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  const url = `${PREFIX}schooleClassManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateCq = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}schooleClassManager/requestCandidateCq/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherCq = (id, parameters) => {
  const url = `${PREFIX}schooleClassManager/transferToAnotherCq/id/anotherCqId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addClassDailyHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}schooleClassManager/addClassDailyHealthSurvey/schooleClassId/name/surveyTime/creatorId/cqId/tokensExpr/`
  const schooleClassId = targetObjectId
  const requestParameters = { ...parameters, schooleClassId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateClassDailyHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}schooleClassManager/updateClassDailyHealthSurveyProperties/schooleClassId/id/name/surveyTime/tokensExpr/`
  const schooleClassId = targetObjectId
  const requestParameters = { ...parameters, schooleClassId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeClassDailyHealthSurveyList = (targetObjectId, parameters) => {
  const url = `${PREFIX}schooleClassManager/removeClassDailyHealthSurveyList/schooleClassId/classDailyHealthSurveyIds/tokensExpr/`
  const requestParameters = { ...parameters, schooleClassId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addStudent = (targetObjectId, parameters) => {
  const url = `${PREFIX}schooleClassManager/addStudent/schooleClassId/name/gender/parentId/studentId/cqId/tokensExpr/`
  const schooleClassId = targetObjectId
  const requestParameters = { ...parameters, schooleClassId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateStudent = (targetObjectId, parameters) => {
  const url = `${PREFIX}schooleClassManager/updateStudentProperties/schooleClassId/id/name/gender/studentId/tokensExpr/`
  const schooleClassId = targetObjectId
  const requestParameters = { ...parameters, schooleClassId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeStudentList = (targetObjectId, parameters) => {
  const url = `${PREFIX}schooleClassManager/removeStudentList/schooleClassId/studentIds/tokensExpr/`
  const requestParameters = { ...parameters, schooleClassId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addStudentHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}schooleClassManager/addStudentHealthSurvey/schooleClassId/studentId/answerTime/surveyStatusId/classDailyHealthSurveyId/cqId/tokensExpr/`
  const schooleClassId = targetObjectId
  const requestParameters = { ...parameters, schooleClassId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateStudentHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}schooleClassManager/updateStudentHealthSurveyProperties/schooleClassId/id/answerTime/tokensExpr/`
  const schooleClassId = targetObjectId
  const requestParameters = { ...parameters, schooleClassId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeStudentHealthSurveyList = (targetObjectId, parameters) => {
  const url = `${PREFIX}schooleClassManager/removeStudentHealthSurveyList/schooleClassId/studentHealthSurveyIds/tokensExpr/`
  const requestParameters = { ...parameters, schooleClassId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}schooleClassService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}schooleClassService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}schooleClassService/process/`,
    data,
  })
}

const SchooleClassService = { view,
  load,
  addClassDailyHealthSurvey,
  addStudent,
  addStudentHealthSurvey,
  updateClassDailyHealthSurvey,
  updateStudent,
  updateStudentHealthSurvey,
  removeClassDailyHealthSurveyList,
  removeStudentList,
  removeStudentHealthSurveyList,
  requestCandidateClassTeacher,
  requestCandidatePlatform,
  requestCandidateCq,
  transferToAnotherClassTeacher,
  transferToAnotherPlatform,
  transferToAnotherCq, listFunctions, saveRequest, processRequest}
export default SchooleClassService

