import React, { Component } from 'react'
import { Card, Button, Form, Icon, Col, Row, DatePicker, TimePicker, Input, Select, Popover,Switch } from 'antd'
import { connect } from 'dva'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import SelectObject from '../../components/SelectObject'
import {ImageComponent} from '../../axios/tools'
import FooterToolbar from '../../components/FooterToolbar'
import styles from './DailySurveyQuestion.createform.less'
import {mapBackToImageValues, mapFromImageValues} from '../../axios/tools'
import GlobalComponents from '../../custcomponents';
import DailySurveyQuestionBase from './DailySurveyQuestion.base'
import appLocaleName from '../../common/Locale.tool'
const { Option } = Select
const { RangePicker } = DatePicker
const { TextArea } = Input
const {fieldLabels} = DailySurveyQuestionBase
const testValues = {};
/*
const testValues = {
  topic: '节假日是否到过武汉',
  optionA: '没有',
  optionB: '有',
  optionC: '不确定',
  optionD: '不知道',
  questionTypeId: 'SINGLE_SELECT',
  classDailyHealthSurveyId: 'CDHS000001',
  surveyQuestionId: 'Q000001',
}
*/

const imageKeys = [
]


class DailySurveyQuestionCreateFormBody extends Component {
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
    
    const {DailySurveyQuestionService} = GlobalComponents
    
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
      return (<div>{linkComp}{appLocaleName(userContext,"CreateNew")}{window.trans('daily_survey_question')}</div>)
    }
	
	return (
      <div>
        <Card title={!this.props.hideTitle&&appLocaleName(userContext,"BasicInfo")} className={styles.card} bordered={false}>
          <Form >
          	<Row gutter={16}>
           

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.topic} {...formItemLayout}>
                  {getFieldDecorator('topic', {
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                    <Input size="large"  placeHolder={fieldLabels.topic} />
                  )}
                </Form.Item>
              </Col>

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.optionA} {...formItemLayout}>
                  {getFieldDecorator('optionA', {
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                    <Input size="large"  placeHolder={fieldLabels.optionA} />
                  )}
                </Form.Item>
              </Col>

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.optionB} {...formItemLayout}>
                  {getFieldDecorator('optionB', {
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                    <Input size="large"  placeHolder={fieldLabels.optionB} />
                  )}
                </Form.Item>
              </Col>

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.optionC} {...formItemLayout}>
                  {getFieldDecorator('optionC', {
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                    <Input size="large"  placeHolder={fieldLabels.optionC} />
                  )}
                </Form.Item>
              </Col>

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.optionD} {...formItemLayout}>
                  {getFieldDecorator('optionD', {
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                    <Input size="large"  placeHolder={fieldLabels.optionD} />
                  )}
                </Form.Item>
              </Col>


       
 
              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.questionType} {...formItemLayout}>
                  {getFieldDecorator('questionTypeId', {
                  	initialValue: tryinit('questionType'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                  
                  <SelectObject 
                    disabled={!availableForEdit('questionType')}
                    targetType={"questionType"} 
                    requestFunction={DailySurveyQuestionService.requestCandidateQuestionType}/>
                  
                 
                  )}
                </Form.Item>
              </Col>

           

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.classDailyHealthSurvey} {...formItemLayout}>
                  {getFieldDecorator('classDailyHealthSurveyId', {
                  	initialValue: tryinit('classDailyHealthSurvey'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                  
                  <SelectObject 
                    disabled={!availableForEdit('classDailyHealthSurvey')}
                    targetType={"classDailyHealthSurvey"} 
                    requestFunction={DailySurveyQuestionService.requestCandidateClassDailyHealthSurvey}/>
                  
                 
                  )}
                </Form.Item>
              </Col>

           

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.surveyQuestion} {...formItemLayout}>
                  {getFieldDecorator('surveyQuestionId', {
                  	initialValue: tryinit('surveyQuestion'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                  
                  <SelectObject 
                    disabled={!availableForEdit('surveyQuestion')}
                    targetType={"surveyQuestion"} 
                    requestFunction={DailySurveyQuestionService.requestCandidateSurveyQuestion}/>
                  
                 
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
}))(Form.create()(DailySurveyQuestionCreateFormBody))





