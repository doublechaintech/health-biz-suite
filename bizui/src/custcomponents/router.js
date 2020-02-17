

import React from 'react'
import { Router, Route, Switch } from 'dva/router'
import { ConfigProvider } from 'antd'
import zhCN from 'antd/lib/locale-provider/zh_CN'
// import enUS from 'antd/lib/locale-provider/en_US'
import Launcher from '../launcher/Launcher'
import ForgetPasswordForm from '../launcher/ForgetPasswordForm'

import GlobalComponents from './'


function RouterConfig({ history }) {

	const {PlatformBizApp} = GlobalComponents
	const {ProvinceBizApp} = GlobalComponents
	const {CityBizApp} = GlobalComponents
	const {DistrictBizApp} = GlobalComponents
	const {LocationBizApp} = GlobalComponents
	const {TeacherBizApp} = GlobalComponents
	const {StudentBizApp} = GlobalComponents
	const {QuestionBizApp} = GlobalComponents
	const {QuestionTypeBizApp} = GlobalComponents
	const {ClassDailyHealthSurveyBizApp} = GlobalComponents
	const {DailySurveyQuestionBizApp} = GlobalComponents
	const {StudentHealthSurveyBizApp} = GlobalComponents
	const {StudentDailyAnswerBizApp} = GlobalComponents
	const {SurveyStatusBizApp} = GlobalComponents
	const {HealthSurveyReportBizApp} = GlobalComponents
	const {StudentAnswerBizApp} = GlobalComponents
	const {UserBizApp} = GlobalComponents
	const {WechatLoginInfoBizApp} = GlobalComponents
	const {ChangeRequestBizApp} = GlobalComponents
	const {ChangeRequestTypeBizApp} = GlobalComponents
	const {UserDomainBizApp} = GlobalComponents
	const {UserWhiteListBizApp} = GlobalComponents
	const {SecUserBizApp} = GlobalComponents
	const {UserAppBizApp} = GlobalComponents
	const {QuickLinkBizApp} = GlobalComponents
	const {ListAccessBizApp} = GlobalComponents
	const {ObjectAccessBizApp} = GlobalComponents
	const {LoginHistoryBizApp} = GlobalComponents
	const {GenericFormBizApp} = GlobalComponents
	const {FormMessageBizApp} = GlobalComponents
	const {FormFieldMessageBizApp} = GlobalComponents
	const {FormFieldBizApp} = GlobalComponents
	const {FormActionBizApp} = GlobalComponents
	const {CandidateContainerBizApp} = GlobalComponents
	const {CandidateElementBizApp} = GlobalComponents
	const {WechatWorkappIdentifyBizApp} = GlobalComponents
	const {WechatMiniappIdentifyBizApp} = GlobalComponents



  return (
    <ConfigProvider locale={zhCN}>
      <Router history={history}>
        <Switch>
          <Route path="/home" component={Launcher} />
          <Route path="/forgetpass" component={ForgetPasswordForm} />
          <Route path="/platform/" component={PlatformBizApp} />
          <Route path="/province/" component={ProvinceBizApp} />
          <Route path="/city/" component={CityBizApp} />
          <Route path="/district/" component={DistrictBizApp} />
          <Route path="/location/" component={LocationBizApp} />
          <Route path="/teacher/" component={TeacherBizApp} />
          <Route path="/student/" component={StudentBizApp} />
          <Route path="/question/" component={QuestionBizApp} />
          <Route path="/questionType/" component={QuestionTypeBizApp} />
          <Route path="/classDailyHealthSurvey/" component={ClassDailyHealthSurveyBizApp} />
          <Route path="/dailySurveyQuestion/" component={DailySurveyQuestionBizApp} />
          <Route path="/studentHealthSurvey/" component={StudentHealthSurveyBizApp} />
          <Route path="/studentDailyAnswer/" component={StudentDailyAnswerBizApp} />
          <Route path="/surveyStatus/" component={SurveyStatusBizApp} />
          <Route path="/healthSurveyReport/" component={HealthSurveyReportBizApp} />
          <Route path="/studentAnswer/" component={StudentAnswerBizApp} />
          <Route path="/user/" component={UserBizApp} />
          <Route path="/wechatLoginInfo/" component={WechatLoginInfoBizApp} />
          <Route path="/changeRequest/" component={ChangeRequestBizApp} />
          <Route path="/changeRequestType/" component={ChangeRequestTypeBizApp} />
          <Route path="/userDomain/" component={UserDomainBizApp} />
          <Route path="/userWhiteList/" component={UserWhiteListBizApp} />
          <Route path="/secUser/" component={SecUserBizApp} />
          <Route path="/userApp/" component={UserAppBizApp} />
          <Route path="/quickLink/" component={QuickLinkBizApp} />
          <Route path="/listAccess/" component={ListAccessBizApp} />
          <Route path="/objectAccess/" component={ObjectAccessBizApp} />
          <Route path="/loginHistory/" component={LoginHistoryBizApp} />
          <Route path="/genericForm/" component={GenericFormBizApp} />
          <Route path="/formMessage/" component={FormMessageBizApp} />
          <Route path="/formFieldMessage/" component={FormFieldMessageBizApp} />
          <Route path="/formField/" component={FormFieldBizApp} />
          <Route path="/formAction/" component={FormActionBizApp} />
          <Route path="/candidateContainer/" component={CandidateContainerBizApp} />
          <Route path="/candidateElement/" component={CandidateElementBizApp} />
          <Route path="/wechatWorkappIdentify/" component={WechatWorkappIdentifyBizApp} />
          <Route path="/wechatMiniappIdentify/" component={WechatMiniappIdentifyBizApp} />
          <Route path="/" component={Launcher} />
        </Switch>
      </Router>
    </ConfigProvider>
  )
}

export default RouterConfig










