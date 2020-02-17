
import { get,put,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'

const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}platformManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}platformManager/loadPlatform/${targetObjectId}/${parametersExpr}/`,
  })
}







const addProvince = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/addProvince/platformId/name/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateProvince = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/updateProvinceProperties/platformId/id/name/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeProvinceList = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/removeProvinceList/platformId/provinceIds/tokensExpr/`
  const requestParameters = { ...parameters, platformId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addCity = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/addCity/platformId/name/provinceId/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateCity = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/updateCityProperties/platformId/id/name/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeCityList = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/removeCityList/platformId/cityIds/tokensExpr/`
  const requestParameters = { ...parameters, platformId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addDistrict = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/addDistrict/platformId/name/cityId/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateDistrict = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/updateDistrictProperties/platformId/id/name/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeDistrictList = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/removeDistrictList/platformId/districtIds/tokensExpr/`
  const requestParameters = { ...parameters, platformId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addTeacher = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/addTeacher/platformId/name/mobile/school/schoolClass/classSize/userId/changeRequestId/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateTeacher = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/updateTeacherProperties/platformId/id/name/mobile/school/schoolClass/classSize/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeTeacherList = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/removeTeacherList/platformId/teacherIds/tokensExpr/`
  const requestParameters = { ...parameters, platformId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addStudent = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/addStudent/platformId/studentName/studentNumber/studentAvatar/guardianName/guardianMobile/addressId/userId/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateStudent = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/updateStudentProperties/platformId/id/studentName/studentNumber/studentAvatar/guardianName/guardianMobile/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeStudentList = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/removeStudentList/platformId/studentIds/tokensExpr/`
  const requestParameters = { ...parameters, platformId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/addQuestion/platformId/topic/questionTypeId/optionA/optionB/optionC/optionD/creatorId/cqId/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateQuestion = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/updateQuestionProperties/platformId/id/topic/optionA/optionB/optionC/optionD/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeQuestionList = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/removeQuestionList/platformId/questionIds/tokensExpr/`
  const requestParameters = { ...parameters, platformId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addQuestionType = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/addQuestionType/platformId/name/code/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateQuestionType = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/updateQuestionTypeProperties/platformId/id/name/code/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeQuestionTypeList = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/removeQuestionTypeList/platformId/questionTypeIds/tokensExpr/`
  const requestParameters = { ...parameters, platformId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addSurveyStatus = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/addSurveyStatus/platformId/name/code/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateSurveyStatus = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/updateSurveyStatusProperties/platformId/id/name/code/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeSurveyStatusList = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/removeSurveyStatusList/platformId/surveyStatusIds/tokensExpr/`
  const requestParameters = { ...parameters, platformId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addUser = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/addUser/platformId/name/avatar/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateUser = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/updateUserProperties/platformId/id/name/avatar/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeUserList = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/removeUserList/platformId/userIds/tokensExpr/`
  const requestParameters = { ...parameters, platformId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addChangeRequest = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/addChangeRequest/platformId/name/requestTypeId/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateChangeRequest = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/updateChangeRequestProperties/platformId/id/name/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeChangeRequestList = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/removeChangeRequestList/platformId/changeRequestIds/tokensExpr/`
  const requestParameters = { ...parameters, platformId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addChangeRequestType = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/addChangeRequestType/platformId/name/code/icon/displayOrder/bindTypes/stepConfiguration/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateChangeRequestType = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/updateChangeRequestTypeProperties/platformId/id/name/code/icon/displayOrder/bindTypes/stepConfiguration/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeChangeRequestTypeList = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/removeChangeRequestTypeList/platformId/changeRequestTypeIds/tokensExpr/`
  const requestParameters = { ...parameters, platformId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



// Filter this out when no functions

const  listFunctions = () => {
  return get({
    url: `${PREFIX}platformService/listFunctions/`,
  })
}


const  saveRequest = (data) => {

  return put({
    url: `${PREFIX}platformService/save/`,
    data,
  })
}


const  processRequest = (data) => {

  return put({
    url: `${PREFIX}platformService/process/`,
    data,
  })
}

const PlatformService = { view,
  load,
  addProvince,
  addCity,
  addDistrict,
  addTeacher,
  addStudent,
  addQuestion,
  addQuestionType,
  addSurveyStatus,
  addUser,
  addChangeRequest,
  addChangeRequestType,
  updateProvince,
  updateCity,
  updateDistrict,
  updateTeacher,
  updateStudent,
  updateQuestion,
  updateQuestionType,
  updateSurveyStatus,
  updateUser,
  updateChangeRequest,
  updateChangeRequestType,
  removeProvinceList,
  removeCityList,
  removeDistrictList,
  removeTeacherList,
  removeStudentList,
  removeQuestionList,
  removeQuestionTypeList,
  removeSurveyStatusList,
  removeUserList,
  removeChangeRequestList,
  removeChangeRequestTypeList, listFunctions, saveRequest, processRequest}
export default PlatformService

