import React, { Component } from 'react'
import { Card, Button, Form, Icon, Col, Row, DatePicker, TimePicker, Input, Select, Popover,Switch,Modal } from 'antd'
import { connect } from 'dva'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import {ImageComponent} from '../../axios/tools'
import FooterToolbar from '../../components/FooterToolbar'
import styles from './StudentHealthSurvey.createform.less'
import {mapBackToImageValues, mapFromImageValues} from '../../axios/tools'
import GlobalComponents from '../../custcomponents';
import StudentHealthSurveyBase from './StudentHealthSurvey.base'
import SelectObject from '../../components/SelectObject'
import appLocaleName from '../../common/Locale.tool'

const { Option } = Select
const { RangePicker } = DatePicker
const { TextArea } = Input

const testValues = {};
/*
const testValues = {
  answerTime: '2020-02-08 16:54:25',
  studentId: 'S000001',
  surveyStatusId: 'UN_SUBMITTED',
  teacherId: 'T000001',
  classDailyHealthSurveyId: 'CDHS000001',
  changeRequestId: 'CR000001',
}
*/


const imageKeys = [
]


class StudentHealthSurveyAssociateForm extends Component {
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
	const { form, dispatch, submitting, role,data,owner,toggleAssociatePaymentVisible,visible,onCancel, onCreate } = this.props
    const { convertedImagesValues } = this.state
    const {StudentHealthSurveyService} = GlobalComponents
    const userContext = null
    
 const {StudentDailyAnswerModalTable} = GlobalComponents;


    const { getFieldDecorator, validateFieldsAndScroll, getFieldsError } = form
    const {fieldLabels} = StudentHealthSurveyBase
    
    const capFirstChar = (value)=>{
    	//const upper = value.replace(/^\w/, c => c.toUpperCase());
  		const upper = value.charAt(0).toUpperCase() + value.substr(1);
  		return upper
  	}
    
    
    

    
    
    const tryinit  = (fieldName, candidates) => {
      
      if(candidates&&candidates.length==1){
          return candidates[0].id
      }
      const { owner } = this.props
      const { referenceName } = owner
      if(referenceName!=fieldName){
        return null
      }
      return owner.id
    }
    
    const availableForEdit= (fieldName) =>{
      const { owner } = this.props
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
      labelCol: { span: 14 },
      wrapperCol: { span: 4 },
    }
   
    return (
 <Modal
          title={appLocaleName(userContext,"CreateNew")}
          visible={visible}
          onOk={onCancel}
          onCancel={onCancel}
          width={920}
          style={{ top: 40}}
        >
        <Card title={appLocaleName(userContext,"BasicInfo")}  className={styles.card} style={{ backgroundColor:"#eee" }}>
          <Form >
            <Row gutter={16}>

              <Col lg={12} md={12} sm={12}>
                <Form.Item label={fieldLabels.answerTime} {...formItemLayout}>
                  {getFieldDecorator('answerTime', {
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                    <DatePicker size="large" showTime format="YYYY-MM-DD HH:mm" minuteStep={5}  placeHolder={fieldLabels.answerTime} />
                  )}
                </Form.Item>
              </Col>

            </Row>


       
        









       
            <Row gutter={16}>

              <Col lg={12} md={12} sm={24}>
                <Form.Item label={fieldLabels.student} {...formItemLayout}>
                  {getFieldDecorator('studentId', {
                  	initialValue: tryinit('student'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                <SelectObject 
                    disabled={!availableForEdit('student')}
                    targetType={"student"} 
                    requestFunction={StudentHealthSurveyService.requestCandidateStudent}/>
  
                  )}
                </Form.Item>
              </Col>

              <Col lg={12} md={12} sm={24}>
                <Form.Item label={fieldLabels.surveyStatus} {...formItemLayout}>
                  {getFieldDecorator('surveyStatusId', {
                  	initialValue: tryinit('surveyStatus'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                <SelectObject 
                    disabled={!availableForEdit('surveyStatus')}
                    targetType={"surveyStatus"} 
                    requestFunction={StudentHealthSurveyService.requestCandidateSurveyStatus}/>
  
                  )}
                </Form.Item>
              </Col>

              <Col lg={12} md={12} sm={24}>
                <Form.Item label={fieldLabels.teacher} {...formItemLayout}>
                  {getFieldDecorator('teacherId', {
                  	initialValue: tryinit('teacher'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                <SelectObject 
                    disabled={!availableForEdit('teacher')}
                    targetType={"teacher"} 
                    requestFunction={StudentHealthSurveyService.requestCandidateTeacher}/>
  
                  )}
                </Form.Item>
              </Col>

              <Col lg={12} md={12} sm={24}>
                <Form.Item label={fieldLabels.classDailyHealthSurvey} {...formItemLayout}>
                  {getFieldDecorator('classDailyHealthSurveyId', {
                  	initialValue: tryinit('classDailyHealthSurvey'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                <SelectObject 
                    disabled={!availableForEdit('classDailyHealthSurvey')}
                    targetType={"classDailyHealthSurvey"} 
                    requestFunction={StudentHealthSurveyService.requestCandidateClassDailyHealthSurvey}/>
  
                  )}
                </Form.Item>
              </Col>

              <Col lg={12} md={12} sm={24}>
                <Form.Item label={fieldLabels.changeRequest} {...formItemLayout}>
                  {getFieldDecorator('changeRequestId', {
                  	initialValue: tryinit('changeRequest'),
                    rules: [{ required: false, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                <SelectObject 
                    disabled={!availableForEdit('changeRequest')}
                    targetType={"changeRequest"} 
                    requestFunction={StudentHealthSurveyService.requestCandidateChangeRequest}/>
  
                  )}
                </Form.Item>
              </Col>

            </Row>
         
       

			</Form>
			
			
			
			
        </Card>
        
	<StudentDailyAnswerModalTable data={data.studentDailyAnswerList} owner={owner} />
        
        
        
      </Modal>)
    
  }
}

export default connect(state => ({
  collapsed: state.global.collapsed,
}))(Form.create()(StudentHealthSurveyAssociateForm))




