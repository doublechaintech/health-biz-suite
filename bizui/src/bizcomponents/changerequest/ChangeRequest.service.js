
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}changeRequestManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}changeRequestManager/loadChangeRequest/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateRequestType = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}changeRequestManager/requestCandidateRequestType/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherRequestType = (id, parameters) => {
  const url = `${PREFIX}changeRequestManager/transferToAnotherRequestType/id/anotherRequestTypeId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}changeRequestManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  const url = `${PREFIX}changeRequestManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addSchoolClass = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/addSchoolClass/changeRequestId/name/classTeacherId/platformId/schoole/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateSchoolClass = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/updateSchoolClassProperties/changeRequestId/id/name/schoole/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeSchoolClassList = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/removeSchoolClassList/changeRequestId/schoolClassIds/tokensExpr/`
  const requestParameters = { ...parameters, changeRequestId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addTeacher = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/addTeacher/changeRequestId/name/mobile/schoole/platformId/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateTeacher = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/updateTeacherProperties/changeRequestId/id/name/mobile/schoole/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeTeacherList = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/removeTeacherList/changeRequestId/teacherIds/tokensExpr/`
  const requestParameters = { ...parameters, changeRequestId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addGuardian = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/addGuardian/changeRequestId/name/mobile/addressId/wechatUserId/platformId/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateGuardian = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/updateGuardianProperties/changeRequestId/id/name/mobile/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeGuardianList = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/removeGuardianList/changeRequestId/guardianIds/tokensExpr/`
  const requestParameters = { ...parameters, changeRequestId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addClassQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/addClassQuestion/changeRequestId/topic/questionTypeId/optionA/optionB/optionC/optionD/questionSourceId/creatorId/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateClassQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/updateClassQuestionProperties/changeRequestId/id/topic/optionA/optionB/optionC/optionD/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeClassQuestionList = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/removeClassQuestionList/changeRequestId/classQuestionIds/tokensExpr/`
  const requestParameters = { ...parameters, changeRequestId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addClassDailyHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/addClassDailyHealthSurvey/changeRequestId/name/schoolClassId/surveyTime/creatorId/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateClassDailyHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/updateClassDailyHealthSurveyProperties/changeRequestId/id/name/surveyTime/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeClassDailyHealthSurveyList = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/removeClassDailyHealthSurveyList/changeRequestId/classDailyHealthSurveyIds/tokensExpr/`
  const requestParameters = { ...parameters, changeRequestId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addStudent = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/addStudent/changeRequestId/name/gender/guardianId/schoolClassId/studentId/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateStudent = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/updateStudentProperties/changeRequestId/id/name/gender/studentId/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeStudentList = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/removeStudentList/changeRequestId/studentIds/tokensExpr/`
  const requestParameters = { ...parameters, changeRequestId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addStudentHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/addStudentHealthSurvey/changeRequestId/studentId/answerTime/surveyStatusId/schoolClassId/classDailyHealthSurveyId/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateStudentHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/updateStudentHealthSurveyProperties/changeRequestId/id/answerTime/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeStudentHealthSurveyList = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/removeStudentHealthSurveyList/changeRequestId/studentHealthSurveyIds/tokensExpr/`
  const requestParameters = { ...parameters, changeRequestId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addStudentDailyAnswer = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/addStudentDailyAnswer/changeRequestId/studentHealthSurveyId/questionId/answer/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateStudentDailyAnswer = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/updateStudentDailyAnswerProperties/changeRequestId/id/answer/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeStudentDailyAnswerList = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/removeStudentDailyAnswerList/changeRequestId/studentDailyAnswerIds/tokensExpr/`
  const requestParameters = { ...parameters, changeRequestId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}changeRequestService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}changeRequestService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}changeRequestService/process/`,
    data,
  })
}

const ChangeRequestService = { view,
  load,
  addSchoolClass,
  addTeacher,
  addGuardian,
  addClassQuestion,
  addClassDailyHealthSurvey,
  addStudent,
  addStudentHealthSurvey,
  addStudentDailyAnswer,
  updateSchoolClass,
  updateTeacher,
  updateGuardian,
  updateClassQuestion,
  updateClassDailyHealthSurvey,
  updateStudent,
  updateStudentHealthSurvey,
  updateStudentDailyAnswer,
  removeSchoolClassList,
  removeTeacherList,
  removeGuardianList,
  removeClassQuestionList,
  removeClassDailyHealthSurveyList,
  removeStudentList,
  removeStudentHealthSurveyList,
  removeStudentDailyAnswerList,
  requestCandidateRequestType,
  requestCandidatePlatform,
  transferToAnotherRequestType,
  transferToAnotherPlatform, listFunctions, saveRequest, processRequest}
export default ChangeRequestService

