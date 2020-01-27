
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}wechatUserManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}wechatUserManager/loadWechatUser/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateAddress = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}wechatUserManager/requestCandidateAddress/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherAddress = (id, parameters) => {
  const url = `${PREFIX}wechatUserManager/transferToAnotherAddress/id/anotherAddressId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateUserType = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}wechatUserManager/requestCandidateUserType/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherUserType = (id, parameters) => {
  const url = `${PREFIX}wechatUserManager/transferToAnotherUserType/id/anotherUserTypeId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}wechatUserManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  const url = `${PREFIX}wechatUserManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addGuardian = (targetObjectId, parameters) => {
  const url = `${PREFIX}wechatUserManager/addGuardian/wechatUserId/name/mobile/addressId/platformId/cqId/tokensExpr/`
  const wechatUserId = targetObjectId
  const requestParameters = { ...parameters, wechatUserId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateGuardian = (targetObjectId, parameters) => {
  const url = `${PREFIX}wechatUserManager/updateGuardianProperties/wechatUserId/id/name/mobile/tokensExpr/`
  const wechatUserId = targetObjectId
  const requestParameters = { ...parameters, wechatUserId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeGuardianList = (targetObjectId, parameters) => {
  const url = `${PREFIX}wechatUserManager/removeGuardianList/wechatUserId/guardianIds/tokensExpr/`
  const requestParameters = { ...parameters, wechatUserId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addClassQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}wechatUserManager/addClassQuestion/wechatUserId/topic/questionTypeId/optionA/optionB/optionC/optionD/questionSourceId/cqId/tokensExpr/`
  const wechatUserId = targetObjectId
  const requestParameters = { ...parameters, wechatUserId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateClassQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}wechatUserManager/updateClassQuestionProperties/wechatUserId/id/topic/optionA/optionB/optionC/optionD/tokensExpr/`
  const wechatUserId = targetObjectId
  const requestParameters = { ...parameters, wechatUserId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeClassQuestionList = (targetObjectId, parameters) => {
  const url = `${PREFIX}wechatUserManager/removeClassQuestionList/wechatUserId/classQuestionIds/tokensExpr/`
  const requestParameters = { ...parameters, wechatUserId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addClassDailyHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}wechatUserManager/addClassDailyHealthSurvey/wechatUserId/name/schoolClassId/surveyTime/cqId/tokensExpr/`
  const wechatUserId = targetObjectId
  const requestParameters = { ...parameters, wechatUserId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateClassDailyHealthSurvey = (targetObjectId, parameters) => {
  const url = `${PREFIX}wechatUserManager/updateClassDailyHealthSurveyProperties/wechatUserId/id/name/surveyTime/tokensExpr/`
  const wechatUserId = targetObjectId
  const requestParameters = { ...parameters, wechatUserId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeClassDailyHealthSurveyList = (targetObjectId, parameters) => {
  const url = `${PREFIX}wechatUserManager/removeClassDailyHealthSurveyList/wechatUserId/classDailyHealthSurveyIds/tokensExpr/`
  const requestParameters = { ...parameters, wechatUserId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addWechatLoginInfo = (targetObjectId, parameters) => {
  const url = `${PREFIX}wechatUserManager/addWechatLoginInfo/wechatUserId/appId/openId/sessionKey/tokensExpr/`
  const wechatUserId = targetObjectId
  const requestParameters = { ...parameters, wechatUserId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateWechatLoginInfo = (targetObjectId, parameters) => {
  const url = `${PREFIX}wechatUserManager/updateWechatLoginInfoProperties/wechatUserId/id/appId/openId/sessionKey/tokensExpr/`
  const wechatUserId = targetObjectId
  const requestParameters = { ...parameters, wechatUserId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeWechatLoginInfoList = (targetObjectId, parameters) => {
  const url = `${PREFIX}wechatUserManager/removeWechatLoginInfoList/wechatUserId/wechatLoginInfoIds/tokensExpr/`
  const requestParameters = { ...parameters, wechatUserId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}wechatUserService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}wechatUserService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}wechatUserService/process/`,
    data,
  })
}

const WechatUserService = { view,
  load,
  addGuardian,
  addClassQuestion,
  addClassDailyHealthSurvey,
  addWechatLoginInfo,
  updateGuardian,
  updateClassQuestion,
  updateClassDailyHealthSurvey,
  updateWechatLoginInfo,
  removeGuardianList,
  removeClassQuestionList,
  removeClassDailyHealthSurveyList,
  removeWechatLoginInfoList,
  requestCandidateAddress,
  requestCandidateUserType,
  requestCandidatePlatform,
  transferToAnotherAddress,
  transferToAnotherUserType,
  transferToAnotherPlatform, listFunctions, saveRequest, processRequest}
export default WechatUserService

