
package com.doublechaintech.health;
// generated by uiform/gen_uiform_base_processor.jsp
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.util.TextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.terapico.caf.DateTime;
import com.terapico.caf.form.ImageInfo;
import com.terapico.utils.CollectionUtils;
import com.terapico.utils.DateTimeUtil;
import com.terapico.utils.DebugUtil;
import com.terapico.utils.MapUtil;
import com.terapico.utils.RequestUtil;

public class BaseFormProcessor {
	
	protected List<Map<String, Object>> actions;
	protected Map<String, Object> params;
	protected String requestBody;
	protected String sceneCode;
	protected String tips;
	
	public void addAction(String title, String code, String linkToUrl) {
		ensureActions();
		Map<String, Object> action = MapUtil.put("title", title).put("code", code).put("linkToUrl", linkToUrl).into_map();
		actions.add(action);
	}
	private Map<String, Object> basicFieldMapping(String paramName, Object defaultValue, Object candidateValues, String type) {
		Map<String, Object> mappingResult = new HashMap<>();
		mappingResult.put("name", paramName);
		mappingResult.put("type", type);
		if (defaultValue != null) {
			mappingResult.put("value", defaultValue);
		}
		if (candidateValues != null) {
			if (candidateValues instanceof BaseCandidateEntity) {
				try {
					mappingResult.put("candidateValues", convertCandidateBaseEntity(candidateValues, defaultValue));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				mappingResult.put("candidateValues", candidateValues);
			}
		}
		return mappingResult;
	}
	private Object convertActionMap() {
		Map<String, Object> result = new HashMap<>();
		if (actions == null) {
			return result;
		}
		for(Map<String, Object> item : actions) {
			result.put((String) item.get("code"), item);
		}
		return result;
	}
	private Object convertCandidateBaseEntity(Object candidateValues, Object defaultValue) throws Exception {
		List<Object> result = new ArrayList<>();
		BaseCandidateEntity list = (BaseCandidateEntity) candidateValues;
		String keyName = list.getDisplayFieldName();
		String valueName = list.getValueFieldName();
		for(Object item : list.getCandidates()) {
			BaseEntity obj = (BaseEntity) item;
			Map<String, Object> itemData = new HashMap<>();
			itemData.put("title", obj.propertyOf(keyName));
			itemData.put("code", obj.propertyOf(valueName));
			if (obj.propertyOf(valueName).equals(defaultValue)) {
				itemData.put("selected", true);
			}
			result.add(itemData);
		}
		return result;
	}

	private void ensureActions() {
		if (actions == null) {
			actions = new ArrayList<>();
		}
		
	}
	public List<Map<String, Object>> getActions() {
		return actions;
	}
	public String getSceneCode() {
		return sceneCode;
	}


	public String getTips() {
		return tips;
	}
	protected boolean isEmpty(Object data) {
		if (data == null) {
			return true;
		}
		if (data instanceof String) {
			return ((String) data).isEmpty(); // 有空格也算是不空
		}
		if (data instanceof Collection) {
			return ((Collection) data).isEmpty();
		}
		if (data.getClass().isArray()) {
			return ((Object[]) data).length == 0;
		}
		if (data instanceof Map) {
			return ((Map) data).isEmpty();
		}
		return false;
	}

	protected void loadRequestBody(String requestBody) throws Exception {
		if (TextUtils.isBlank(requestBody)) {
			throw new Exception("请求参数为空");
		}
		this.requestBody = requestBody;
		params = new ObjectMapper().readValue(requestBody, Map.class);
		this.setSceneCode(this.pickStringParams("sceneCode"));
	}
	protected Map<String, Object> mapBaseEntityFieldIntoUiForm(boolean hidden, String paramName, String defaultValue, Object candidateValues) {
		return basicFieldMapping(paramName, defaultValue, candidateValues, hidden?"hidden":"id");
	}

	protected Map<String, Object> mapBaseEntityFieldIntoUiForm(String paramName, String defaultValue, Object candidateValues) {
		return basicFieldMapping(paramName, defaultValue, candidateValues, "id");
	}
	protected Object mapBigDecimalFieldIntoUiForm(boolean hidden, String paramName, BigDecimal defaultValue) {
		return basicFieldMapping(paramName, defaultValue, null, hidden?"hidden":"number_bigdecimal");
	}
	
	protected Object mapBigDecimalFieldIntoUiForm(String paramName, BigDecimal defaultValue) {
		return basicFieldMapping(paramName, defaultValue, null, "number_bigdecimal");
	}
	protected Map<String, Object> mapBooleanFieldIntoUiForm(boolean hidden, String paramName, Boolean defaultValue) {
		return basicFieldMapping(paramName, defaultValue, null, hidden?"hidden":"boolean");
	}

	protected Map<String, Object> mapBooleanFieldIntoUiForm(String paramName, Boolean defaultValue) {
		return basicFieldMapping(paramName, defaultValue, null, "boolean");
	}
	protected Object mapDateFieldIntoUiForm(boolean hidden, String paramName, Date defaultValue) {
		return basicFieldMapping(paramName, defaultValue, null, hidden?"hidden":"date");
	}
	protected Object mapDateFieldIntoUiForm(String paramName, Date defaultValue) {
		return basicFieldMapping(paramName, defaultValue, null, "date");
	}
	protected Object mapDoubleFieldIntoUiForm(boolean hidden, String paramName, Double defaultValue) {
		return basicFieldMapping(paramName, defaultValue, null, hidden?"hidden":"number_float");
	}
	protected Object mapDoubleFieldIntoUiForm(String paramName, Double defaultValue) {
		return basicFieldMapping(paramName, defaultValue, null, "number_float");
	}
	protected Map<String, Object> mapHiddenFieldIntoUiForm(boolean hidden, String paramName, String defaultValue) {
		return basicFieldMapping(paramName, defaultValue, null, "hidden");
	}

	protected Map<String, Object> mapHiddenFieldIntoUiForm(String paramName, String defaultValue) {
		return basicFieldMapping(paramName, defaultValue, null, "hidden");
	}
	protected Map<String, Object> mapImagesFieldIntoUiForm(boolean hidden, String paramName, List<ImageInfo> defaultValue) {
		return mapImagesFieldIntoUiForm(paramName, defaultValue);
	}

	protected Map<String, Object> mapImagesFieldIntoUiForm(String paramName, List<ImageInfo> defaultValue) {
		Map<String, Object> mappingResult = new HashMap<>();
		mappingResult.put("name", paramName);
		mappingResult.put("type", "image");
		if (defaultValue != null && !defaultValue.isEmpty()) {
			mappingResult.put("value", defaultValue.get(0).getImageUrl());
		}
		return mappingResult;
	}
	protected Map<String, Object> mapImagesListIntoUiForm(boolean hidden, String paramName, List<ImageInfo> defaultValue) {
		return mapImagesListIntoUiForm(paramName, defaultValue);
	}

	protected Map<String, Object> mapImagesListIntoUiForm(String paramName, List<ImageInfo> defaultValue) {
		Map<String, Object> mappingResult = new HashMap<>();
		mappingResult.put("name", paramName);
		mappingResult.put("type", "image");
		if (defaultValue == null || defaultValue.isEmpty()) {
			return mappingResult;
		}
		List<Object> resultList = new ArrayList<>();
		mappingResult.put("value", resultList);
		
		for(ImageInfo image: defaultValue) {
			Map<String, Object> item = new HashMap<>();
			if (image.getId() != null) {
				item.put("id", image.getId());
			}
			if (image.getContent() != null) {
				item.put("content", image.getContent());
			}
			if (image.getImageUrl() != null) {
				item.put("imageUrl", image.getImageUrl());
			}
			if (image.getHeight() > 0) {
				item.put("height", image.getHeight());
			}
			if (image.getWidth() > 0) {
				item.put("width", image.getWidth());
			}
			if (item.isEmpty()) {
				continue;
			}
			resultList.add(item);
		}
		return mappingResult;
	}
	
	protected Object mapIntegerFieldIntoUiForm(boolean hidden, String paramName, Integer defaultValue) {
		return basicFieldMapping(paramName, defaultValue, null, hidden?"hidden":"number_int");
	}

	protected Object mapIntegerFieldIntoUiForm(String paramName, Integer defaultValue) {
		return basicFieldMapping(paramName, defaultValue, null, "number_int");
	}

	protected Map<String, Object> mapObjectListFieldIntoUiForm(boolean hidden, String paramName, List<Object> defaultValue) {
		return basicFieldMapping(paramName, defaultValue, null, "object_list");
	}
	protected Map<String, Object> mapObjectListFieldIntoUiForm(boolean hidden, String paramName, List<Object> defaultValue, Object candidateValue) {
		return basicFieldMapping(paramName, defaultValue, candidateValue, "object_list");
	}

	protected void mappingFormActions(Map<String, Object> result) {
		if (!TextUtils.isBlank(tips)) {
			result.put("tips", tips);
		}
		result.put("actionList", this.getActions());
		result.put("actions", convertActionMap());
	}
	
	protected Map<String, Object> mapStringArrayFieldIntoUiForm(boolean hidden, String paramName, String[] defaultValue) {
		return basicFieldMapping(paramName, defaultValue, null, hidden?"hidden":"text_array");
	}
	
	protected Map<String, Object> mapStringArrayFieldIntoUiForm(String paramName, String[] defaultValue) {
		return basicFieldMapping(paramName, defaultValue, null, "text_array");
	}
	protected Map<String, Object> mapStringFieldIntoUiForm(boolean hidden, String paramName, String defaultValue) {
		return basicFieldMapping(paramName, defaultValue, null, hidden?"hidden":"text");
	}
	protected Map<String, Object> mapStringFieldIntoUiForm(String paramName, String defaultValue) {
		return basicFieldMapping(paramName, defaultValue, null, "text");
	}
	protected Map<String, Object> mapStringSelectionFieldIntoUiForm(boolean hidden, String paramName, String defaultValue, Object candidateValues) {
		return basicFieldMapping(paramName, defaultValue, candidateValues,  hidden?"hidden":"select");
	}
	protected Map<String, Object> mapStringSelectionFieldIntoUiForm(String paramName, String defaultValue, Object candidateValues) {
		return basicFieldMapping(paramName, defaultValue, candidateValues, "select");
	}
	protected Date now() {
		return new Date();
	}
	
	protected BigDecimal pickBigDecimalParams(String paramName) {
		return pickBigDecimalParams(paramName, null);
	}
	protected BigDecimal pickBigDecimalParams(String paramName, BigDecimal defaultValue) {
		return RequestUtil.getBigDecimalInput(params, paramName, defaultValue);
	}
	
	protected Boolean pickBooleanParams(String paramName) {
		return RequestUtil.getBooleanInput(params, paramName, false);
	}
	
	protected Date pickDateParams(String paramName) {
		return pickDateParams(paramName, null);
	}
	protected Date pickDateParams(String paramName, String defaultValueExp) {
		return DateTimeUtil.parseInputDateTime(RequestUtil.getStringInput(params, paramName, defaultValueExp));
	}
	
	protected Double pickDoubleParams(String paramName) {
		return pickDoubleParams(paramName, null);
	}
	protected Double pickDoubleParams(String paramName, Double defaultValue) {
		return RequestUtil.getDoubleInput(params, paramName, defaultValue);
	}
	
	protected List<ImageInfo> pickImagesParams(String paramName) throws Exception {
//		String imagesVal = RequestUtil.getStringInput(params, paramName, "");
//		return imagesVal.split(";");
		List<Object> list = (List<Object>) this.params.get(paramName);
		if(list == null || list.isEmpty()) {
			return new ArrayList<>();
		}
		
		List<ImageInfo> images = new ArrayList<>();
		for(Object item : list) {
			String imagesVal = DebugUtil.getObjectMapper().writeValueAsString(item);
			ImageInfo data = DebugUtil.getObjectMapper().readValue(imagesVal, ImageInfo.class);
			images.add(data);
		}
		return images;
	}
	
	protected Integer pickIntegerParams(String paramName) {
		return pickIntegerParams(paramName, null);
	}
	protected Integer pickIntegerParams(String paramName, Integer defaultValue) {
		return RequestUtil.getIntegerInput(params, paramName, defaultValue);
	}
	
	protected List<Object> pickObjectListParams(String paramName) {
		Object value = params.get(paramName);
		if (value == null) {
			return null;
		}
		if (value.getClass().isArray()) {
			return new ArrayList<Object>(Arrays.asList((Object[])value));
		}
		if (value instanceof List) {
			return (List<Object>) value;
		}
		if (value instanceof String) {
			String strVal = (String) value;
			Object[] strVals = strVal.trim().split("\\s*,\\s*");
			return CollectionUtils.toList(strVals);
		}
		List<Object> rst = new ArrayList<>();
		rst.add(value);
		return rst;
	}
	
	protected String[] pickStringArrayParams(String paramName) {
//		String imagesVal = RequestUtil.getStringInput(params, paramName, "");
		Object value = params.get(paramName);
		if (value == null) {
			return null;
		}
		if (value instanceof List) {
			return ((List<String>) value).toArray(new String[] {});
		}
		if (value.getClass().isArray()) {
			return (String[]) value;
		}
		return new String[] {String.valueOf(value)};
	}
	
	protected String pickStringParams(String paramName) {
		return pickStringParams(paramName, null);
	}
	protected String pickStringParams(String paramName, String defaultValue) {
		return RequestUtil.getStringInput(params, paramName, defaultValue);
	}
	
	public void setActions(List<Map<String, Object>> actions) {
		this.actions = actions;
	}
	public void setSceneCode(String sceneCode) {
		this.sceneCode = sceneCode;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	protected boolean verifyBigDecimalInput(BigDecimal input, Double minValue, Double maxValue) {
		if (input == null) {
			return true;
		}
		if (minValue != null && minValue > input.doubleValue()) {
			return false;
		}
		if (maxValue != null && maxValue < input.doubleValue()) {
			return false;
		}
		return true;
	}
	protected boolean verifyBooleanInput(Boolean val) {
		return true;
	}
	protected boolean verifyDateInput(Date input, Date startDate, Date toDate) {
		if (input == null) {
			return true;
		}
		if (startDate != null && startDate.after(input)) {
			return false;
		}
		if (toDate != null && toDate.before(input)) {
			return false;
		}
		return true;
	}
	protected boolean verifyDoubleInput(Double input, double minValue, double maxValue) {
		if (input == null) {
			return true;
		}
		if (minValue > input) {
			return false;
		}
		if (maxValue < input) {
			return false;
		}
		return true;
	}
	protected boolean verifyImagesInput(List<ImageInfo> images, boolean canbeNull, Integer maxLength) {
		if (images == null && canbeNull) {
			return true;
		}
		if ((images == null || images.size() == 0) && canbeNull) {
			return true;
		}
		if (maxLength != null && images.size() > maxLength) {
			return false;
		}
		return true;
	}
	public void verifyInputs() throws Exception{
		throw new Exception(this.getClass().getSimpleName()+"没有实现自己的verifyInputs()方法");
	}
	protected boolean verifyIntegerInput(Integer input, int minValue, int maxValue) {
		if (input == null) {
			return true;
		}
		if (minValue > input) {
			return false;
		}
		if (maxValue < input) {
			return false;
		}
		return true;
	}

	protected boolean verifyObjectListInput(List<Object> inputData, Integer minCnt, Integer maxCnt) {
		if (inputData == null) {
			return true;
		}
		if (minCnt != null && inputData.size() < minCnt) {
			return false;
		}
		if (maxCnt != null && inputData.size() > maxCnt) {
			return false;
		}
		return true;
	}
	
	protected boolean verifyStringArrayInput(String[] inputData, Integer minCnt, Integer maxCnt) {
		if (inputData == null) {
			return true;
		}
		if (minCnt != null && inputData.length < minCnt) {
			return false;
		}
		if (maxCnt != null && inputData.length > maxCnt) {
			return false;
		}
		return true;
	}
	protected boolean verifyStringInput(String input, Integer minLength, Integer maxLength) {
		if (input == null) {
			return true;
		}
		if (minLength != null && input.length() < minLength) {
			return false;
		}
		if (maxLength != null && input.length() > maxLength) {
			return false;
		}
		return true;
	}
	protected BigDecimal wrapperBigDecimalDataMember(BigDecimal val) {
		return val;
	}
	protected Boolean wrapperBooleanDataMember(Boolean val) {
		return val;
	}
	protected DateTime wrapperDateDataMember(Date val) {
		return DateTime.fromDate(val);
	}
	protected Integer wrapperIntegerDataMember(Integer val) {
		return val;
	}
	protected List<ImageInfo> wrapperListImageInfoDataMember(String image) {
		if (image == null) {
			return null;
		}
		List<ImageInfo> result = new ArrayList<>();
		ImageInfo imageInfo = new ImageInfo();
		imageInfo.setImageUrl(image);
		result.add(imageInfo);
		return result;
	}
	protected String wrapperStringDataMember(BaseEntity data) {
		if (data == null) {
			return null;
		}
		return data.getId();
	}
	protected String wrapperStringDataMember(Object strVal) {
		return String.valueOf(strVal);
	}
	
	
	
	protected String wrapperStringDataMember(String strVal) {
		return strVal;
	}
	
}


