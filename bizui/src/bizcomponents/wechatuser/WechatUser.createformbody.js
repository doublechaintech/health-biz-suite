import React, { Component } from 'react'
import { Card, Button, Form, Icon, Col, Row, DatePicker, TimePicker, Input, Select, Popover,Switch } from 'antd'
import { connect } from 'dva'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import SelectObject from '../../components/SelectObject'
import {ImageComponent} from '../../axios/tools'
import FooterToolbar from '../../components/FooterToolbar'
import styles from './WechatUser.createform.less'
import {mapBackToImageValues, mapFromImageValues} from '../../axios/tools'
import GlobalComponents from '../../custcomponents';
import WechatUserBase from './WechatUser.base'
import appLocaleName from '../../common/Locale.tool'
const { Option } = Select
const { RangePicker } = DatePicker
const { TextArea } = Input
const {fieldLabels} = WechatUserBase
const testValues = {};
/*
const testValues = {
  name: '张三',
  addressId: 'L000001',
  userTypeId: 'Teacher',
  platformId: 'P000001',
}
*/

const imageKeys = [
  'avatar',
]


class WechatUserCreateFormBody extends Component {
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
    
    const {WechatUserService} = GlobalComponents
    
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
      return (<div>{linkComp}{appLocaleName(userContext,"CreateNew")}{window.trans('wechat_user')}</div>)
    }
	
	return (
      <div>
        <Card title={!this.props.hideTitle&&appLocaleName(userContext,"BasicInfo")} className={styles.card} bordered={false}>
          <Form >
          	<Row gutter={16}>
           

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.name} {...formItemLayout}>
                  {getFieldDecorator('name', {
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                    <Input size="large"  placeHolder={fieldLabels.name} />
                  )}
                </Form.Item>
              </Col>


       
 
              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.address} {...formItemLayout}>
                  {getFieldDecorator('addressId', {
                  	initialValue: tryinit('address'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                  
                  <SelectObject 
                    disabled={!availableForEdit('address')}
                    targetType={"address"} 
                    requestFunction={WechatUserService.requestCandidateAddress}/>
                  
                 
                  )}
                </Form.Item>
              </Col>

           

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.userType} {...formItemLayout}>
                  {getFieldDecorator('userTypeId', {
                  	initialValue: tryinit('userType'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                  
                  <SelectObject 
                    disabled={!availableForEdit('userType')}
                    targetType={"userType"} 
                    requestFunction={WechatUserService.requestCandidateUserType}/>
                  
                 
                  )}
                </Form.Item>
              </Col>

           

              <Col lg={24} md={24} sm={24}>
                <Form.Item label={fieldLabels.platform} {...formItemLayout}>
                  {getFieldDecorator('platformId', {
                  	initialValue: tryinit('platform'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                  
                  <SelectObject 
                    disabled={!availableForEdit('platform')}
                    targetType={"platform"} 
                    requestFunction={WechatUserService.requestCandidatePlatform}/>
                  
                 
                  )}
                </Form.Item>
              </Col>

           



			 </Row>
          </Form>
        </Card>






       <Card title={<div>{appLocaleName(userContext,"Attachment")} <Popover title={appLocaleName(userContext,"ScanQRCodetoUploadfromSmartPhone")} content={<div><img src='./qrtest.png'/></div>}><Icon type="qrcode" ></Icon></Popover></div>} className={styles.card} bordered={false}>
          <Form >
            <Row gutter={16}>     
           

              <Col lg={6} md={12} sm={24}>
                <ImageComponent
                  buttonTitle={fieldLabels.avatar}
                  handlePreview={this.handlePreview}
                  handleChange={event => this.handleChange(event, 'avatar')}
                  fileList={convertedImagesValues.avatar}
                />
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
}))(Form.create()(WechatUserCreateFormBody))





