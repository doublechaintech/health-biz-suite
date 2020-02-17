import React, { Component } from 'react'
import { Card, Button, Form, Icon, Col, Row, DatePicker, TimePicker, Input, Select, Popover,Switch } from 'antd'
import { connect } from 'dva'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import SelectObject from '../../components/SelectObject'
import {ImageComponent} from '../../axios/tools'
import FooterToolbar from '../../components/FooterToolbar'
import styles from './HealthSurveyReport.createform.less'
import {mapBackToImageValues, mapFromImageValues} from '../../axios/tools'
import GlobalComponents from '../../custcomponents';
import HealthSurveyReportBase from './HealthSurveyReport.base'
import appLocaleName from '../../common/Locale.tool'
const { Option } = Select
const { RangePicker } = DatePicker
const { TextArea } = Input
const {fieldLabels} = HealthSurveyReportBase
const testValues = {};
/*
const testValues = {
  surveyName: '2020年1月25日益州小学学生健康调查问卷',
  surveyTime: '2020-02-09 07:17:14',
  teacherName: '张三',
  school: '益州小学',
  schoolClass: '教科院一年级5班',
  studentName: '刘婵',
  studentNumber: 'A01',
  guardianName: '刘备',
  guardianMobile: '18012341234',
  studentId: 'S000001',
  teacherId: 'T000001',
  surveyId: 'CDHS000001',
}
*/

const imageKeys = [
]


class HealthSurveyReportCreateFormBody extends Component {
  state = {
    previewVisible: false,
    previewImage: '',
    convertedImagesValues: {},
  }

  componentDidMount() {
	
    
    
  }

  handlePreview = (file) => {
    console.log('preview file', file)
    this.setState({
      previewImage: file.url || file.thumbUrl,
      previewVisible: true,
    })
  }

 



  handleChange = (event, source) => {
    console.log('get file list from change in update change:', source)

    const { fileList } = event
    const { convertedImagesValues } = this.state

    convertedImagesValues[source] = fileList
    this.setState({ convertedImagesValues })
    console.log('/get file list from change in update change:', source)
  }
	
  

  render() {
    const { form, dispatch, submitting, role } = this.props
    const { convertedImagesValues } = this.state
	const userContext = null
    const { getFieldDecorator, validateFieldsAndScroll, getFieldsError } = form
    
    const {HealthSurveyReportService} = GlobalComponents
    
    const capFirstChar = (value)=>{
    	//const upper = value.replace(/^\w/, c => c.toUpperCase());
  		const upper = value.charAt(0).toUpperCase() + value.substr(1);
  		return upper
  	}
    
    
    const tryinit  = (fieldName) => {
      const { owner } = this.props
      if(!owner){
      	return null
      }
      const { referenceName } = owner
      if(referenceName!=fieldName){
        return null
      }
      return owner.id
    }
    
    const availableForEdit= (fieldName) =>{
      const { owner } = this.props
      if(!owner){
      	return true
      }
      const { referenceName } = owner
      if(referenceName!=fieldName){
        return true
      }
      return false
    
    }
	const formItemLayout = {
      labelCol: { span: 6 },
      wrapperCol: { span: 12 },
    }
    const switchFormItemLayout = {

      labelCol: { span: 6 },
      wrapperCol: { span: 12 },

    }
    
    const internalRenderTitle = () =>{
      const linkComp=<a onClick={goback}  > <Icon type="double-left" style={{marginRight:"10px"}} /> </a>
      return (<div>{linkComp}{appLocaleName(userContext,"CreateNew")}{window.trans('health_survey_report')}</div>)
    }
	
	return (
      <div>
        <Card title={!this.props.hideTitle&&appLocaleName(userContext,"BasicInfo")} className={styles.card} bordered={false}>
          <Form >
          	<Row gutter={16}>
           

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.surveyName} {...formItemLayout}>
                  {getFieldDecorator('surveyName', {
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                    <Input size="large"  placeHolder={fieldLabels.surveyName} />
                  )}
                </Form.Item>
              </Col>

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.surveyTime} {...formItemLayout}>
                  {getFieldDecorator('surveyTime', {
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                    <DatePicker size="large" showTime format="YYYY-MM-DD HH:mm" minuteStep={5}  placeHolder={fieldLabels.surveyTime} />
                  )}
                </Form.Item>
              </Col>

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.teacherName} {...formItemLayout}>
                  {getFieldDecorator('teacherName', {
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                    <Input size="large"  placeHolder={fieldLabels.teacherName} />
                  )}
                </Form.Item>
              </Col>

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.school} {...formItemLayout}>
                  {getFieldDecorator('school', {
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                    <Input size="large"  placeHolder={fieldLabels.school} />
                  )}
                </Form.Item>
              </Col>

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.schoolClass} {...formItemLayout}>
                  {getFieldDecorator('schoolClass', {
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                    <Input size="large"  placeHolder={fieldLabels.schoolClass} />
                  )}
                </Form.Item>
              </Col>

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.studentName} {...formItemLayout}>
                  {getFieldDecorator('studentName', {
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                    <Input size="large"  placeHolder={fieldLabels.studentName} />
                  )}
                </Form.Item>
              </Col>

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.studentNumber} {...formItemLayout}>
                  {getFieldDecorator('studentNumber', {
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                    <Input size="large"  placeHolder={fieldLabels.studentNumber} />
                  )}
                </Form.Item>
              </Col>

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.guardianName} {...formItemLayout}>
                  {getFieldDecorator('guardianName', {
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                    <Input size="large"  placeHolder={fieldLabels.guardianName} />
                  )}
                </Form.Item>
              </Col>

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.guardianMobile} {...formItemLayout}>
                  {getFieldDecorator('guardianMobile', {
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                    <Input size="large"  placeHolder={fieldLabels.guardianMobile} />
                  )}
                </Form.Item>
              </Col>


       
 
              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.student} {...formItemLayout}>
                  {getFieldDecorator('studentId', {
                  	initialValue: tryinit('student'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                  
                  <SelectObject 
                    disabled={!availableForEdit('student')}
                    targetType={"student"} 
                    requestFunction={HealthSurveyReportService.requestCandidateStudent}/>
                  
                 
                  )}
                </Form.Item>
              </Col>

           

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.teacher} {...formItemLayout}>
                  {getFieldDecorator('teacherId', {
                  	initialValue: tryinit('teacher'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                  
                  <SelectObject 
                    disabled={!availableForEdit('teacher')}
                    targetType={"teacher"} 
                    requestFunction={HealthSurveyReportService.requestCandidateTeacher}/>
                  
                 
                  )}
                </Form.Item>
              </Col>

           

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.survey} {...formItemLayout}>
                  {getFieldDecorator('surveyId', {
                  	initialValue: tryinit('survey'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                  
                  <SelectObject 
                    disabled={!availableForEdit('survey')}
                    targetType={"survey"} 
                    requestFunction={HealthSurveyReportService.requestCandidateSurvey}/>
                  
                 
                  )}
                </Form.Item>
              </Col>

           



			 </Row>
          </Form>
        </Card>








      
       </div>
    )
  }
}

export default connect(state => ({
  collapsed: state.global.collapsed,
}))(Form.create()(HealthSurveyReportCreateFormBody))





