
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}userManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}userManager/loadUser/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}userManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  const url = `${PREFIX}userManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addTeacher = (targetObjectId, parameters) => {
  const url = `${PREFIX}userManager/addTeacher/userId/name/mobile/school/schoolClass/classSize/platformId/changeRequestId/tokensExpr/`
  const userId = targetObjectId
  const requestParameters = { ...parameters, userId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateTeacher = (targetObjectId, parameters) => {
  const url = `${PREFIX}userManager/updateTeacherProperties/userId/id/name/mobile/school/schoolClass/classSize/tokensExpr/`
  const userId = targetObjectId
  const requestParameters = { ...parameters, userId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeTeacherList = (targetObjectId, parameters) => {
  const url = `${PREFIX}userManager/removeTeacherList/userId/teacherIds/tokensExpr/`
  const requestParameters = { ...parameters, userId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addStudent = (targetObjectId, parameters) => {
  const url = `${PREFIX}userManager/addStudent/userId/studentName/studentNumber/studentAvatar/guardianName/guardianMobile/addressId/platformId/tokensExpr/`
  const userId = targetObjectId
  const requestParameters = { ...parameters, userId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateStudent = (targetObjectId, parameters) => {
  const url = `${PREFIX}userManager/updateStudentProperties/userId/id/studentName/studentNumber/studentAvatar/guardianName/guardianMobile/tokensExpr/`
  const userId = targetObjectId
  const requestParameters = { ...parameters, userId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeStudentList = (targetObjectId, parameters) => {
  const url = `${PREFIX}userManager/removeStudentList/userId/studentIds/tokensExpr/`
  const requestParameters = { ...parameters, userId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}userManager/addQuestion/userId/topic/questionTypeId/optionA/optionB/optionC/optionD/platformId/cqId/tokensExpr/`
  const userId = targetObjectId
  const requestParameters = { ...parameters, userId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}userManager/updateQuestionProperties/userId/id/topic/optionA/optionB/optionC/optionD/tokensExpr/`
  const userId = targetObjectId
  const requestParameters = { ...parameters, userId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeQuestionList = (targetObjectId, parameters) => {
  const url = `${PREFIX}userManager/removeQuestionList/userId/questionIds/tokensExpr/`
  const requestParameters = { ...parameters, userId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addClassDailyHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}userManager/addClassDailyHealthSurvey/userId/name/teacherId/surveyTime/downloadUrl/changeRequestId/tokensExpr/`
  const userId = targetObjectId
  const requestParameters = { ...parameters, userId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateClassDailyHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}userManager/updateClassDailyHealthSurveyProperties/userId/id/name/surveyTime/downloadUrl/tokensExpr/`
  const userId = targetObjectId
  const requestParameters = { ...parameters, userId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeClassDailyHealthSurveyList = (targetObjectId, parameters) => {
  const url = `${PREFIX}userManager/removeClassDailyHealthSurveyList/userId/classDailyHealthSurveyIds/tokensExpr/`
  const requestParameters = { ...parameters, userId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addWechatLoginInfo = (targetObjectId, parameters) => {
  const url = `${PREFIX}userManager/addWechatLoginInfo/userId/appId/openId/sessionKey/tokensExpr/`
  const userId = targetObjectId
  const requestParameters = { ...parameters, userId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateWechatLoginInfo = (targetObjectId, parameters) => {
  const url = `${PREFIX}userManager/updateWechatLoginInfoProperties/userId/id/appId/openId/sessionKey/tokensExpr/`
  const userId = targetObjectId
  const requestParameters = { ...parameters, userId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeWechatLoginInfoList = (targetObjectId, parameters) => {
  const url = `${PREFIX}userManager/removeWechatLoginInfoList/userId/wechatLoginInfoIds/tokensExpr/`
  const requestParameters = { ...parameters, userId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}userService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}userService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}userService/process/`,
    data,
  })
}

const UserService = { view,
  load,
  addTeacher,
  addStudent,
  addQuestion,
  addClassDailyHealthSurvey,
  addWechatLoginInfo,
  updateTeacher,
  updateStudent,
  updateQuestion,
  updateClassDailyHealthSurvey,
  updateWechatLoginInfo,
  removeTeacherList,
  removeStudentList,
  removeQuestionList,
  removeClassDailyHealthSurveyList,
  removeWechatLoginInfoList,
  requestCandidatePlatform,
  transferToAnotherPlatform, listFunctions, saveRequest, processRequest}
export default UserService

