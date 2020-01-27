
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}schoolClassManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}schoolClassManager/loadSchoolClass/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateClassTeacher = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}schoolClassManager/requestCandidateClassTeacher/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherClassTeacher = (id, parameters) => {
  const url = `${PREFIX}schoolClassManager/transferToAnotherClassTeacher/id/anotherClassTeacherId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}schoolClassManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  const url = `${PREFIX}schoolClassManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateCq = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}schoolClassManager/requestCandidateCq/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherCq = (id, parameters) => {
  const url = `${PREFIX}schoolClassManager/transferToAnotherCq/id/anotherCqId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addClassDailyHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}schoolClassManager/addClassDailyHealthSurvey/schoolClassId/name/surveyTime/creatorId/cqId/tokensExpr/`
  const schoolClassId = targetObjectId
  const requestParameters = { ...parameters, schoolClassId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateClassDailyHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}schoolClassManager/updateClassDailyHealthSurveyProperties/schoolClassId/id/name/surveyTime/tokensExpr/`
  const schoolClassId = targetObjectId
  const requestParameters = { ...parameters, schoolClassId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeClassDailyHealthSurveyList = (targetObjectId, parameters) => {
  const url = `${PREFIX}schoolClassManager/removeClassDailyHealthSurveyList/schoolClassId/classDailyHealthSurveyIds/tokensExpr/`
  const requestParameters = { ...parameters, schoolClassId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addStudent = (targetObjectId, parameters) => {
  const url = `${PREFIX}schoolClassManager/addStudent/schoolClassId/name/gender/guardianId/studentId/cqId/tokensExpr/`
  const schoolClassId = targetObjectId
  const requestParameters = { ...parameters, schoolClassId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateStudent = (targetObjectId, parameters) => {
  const url = `${PREFIX}schoolClassManager/updateStudentProperties/schoolClassId/id/name/gender/studentId/tokensExpr/`
  const schoolClassId = targetObjectId
  const requestParameters = { ...parameters, schoolClassId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeStudentList = (targetObjectId, parameters) => {
  const url = `${PREFIX}schoolClassManager/removeStudentList/schoolClassId/studentIds/tokensExpr/`
  const requestParameters = { ...parameters, schoolClassId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addStudentHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}schoolClassManager/addStudentHealthSurvey/schoolClassId/studentId/answerTime/surveyStatusId/classDailyHealthSurveyId/cqId/tokensExpr/`
  const schoolClassId = targetObjectId
  const requestParameters = { ...parameters, schoolClassId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateStudentHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}schoolClassManager/updateStudentHealthSurveyProperties/schoolClassId/id/answerTime/tokensExpr/`
  const schoolClassId = targetObjectId
  const requestParameters = { ...parameters, schoolClassId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeStudentHealthSurveyList = (targetObjectId, parameters) => {
  const url = `${PREFIX}schoolClassManager/removeStudentHealthSurveyList/schoolClassId/studentHealthSurveyIds/tokensExpr/`
  const requestParameters = { ...parameters, schoolClassId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}schoolClassService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}schoolClassService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}schoolClassService/process/`,
    data,
  })
}

const SchoolClassService = { view,
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
export default SchoolClassService

