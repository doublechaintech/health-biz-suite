import React from 'react';
import pathToRegexp from 'path-to-regexp';
import { routerRedux } from 'dva/router';
import { notification } from 'antd';
import GlobalComponents from '../../custcomponents';
import appLocaleName from '../../common/Locale.tool';
import modeltool from '../../utils/modeltool';
const {
  setupModel,
  hasError,
  handleClientError,
  handleServerError,
  keepValueWithKeySuffix,
} = modeltool;

const notifySuccess = userContext => {
  notification.success({
    message: appLocaleName(userContext, 'Success'),
    description: appLocaleName(userContext, 'Success'),
  });
};

export default {
  namespace: '_teacher',

  state: {},

  subscriptions: {
    setup({ dispatch, history }) {
      history.listen(location => {
        const modelName = 'teacher';
        const parameter = { dispatch, history, location, modelName };
        //console.log("setupModel",setupModel,typeof(setupModel))
        setupModel(parameter);
      });
    },
  },
  effects: {
    *view({ payload }, { call, put, select }) {
      const cachedData = yield select(state => state._teacher);
      //if the data in the cache, just show it, there is no delay
      const link = payload.pathname;
      //if the data in the cache, just show it, there is no delay
      if (cachedData.class) {
        //yield put({ type: 'breadcrumb/gotoLink', payload: { displayName:cachedData.displayName,link }} )
        yield put({ type: 'updateState', payload: cachedData });

        if (payload.useCache) {
          return; //use cache for returning page
        }
      } else {
        yield put({ type: 'showLoading', payload });
      }

      const { TeacherService } = GlobalComponents;
      const data = yield call(TeacherService.view, payload.id);

      const displayName = payload.displayName || data.displayName;

      yield put({ type: 'breadcrumb/gotoLink', payload: { displayName, link } });

      yield put({ type: 'updateState', payload: data });
    },
    *load({ payload }, { call, put }) {
      const { TeacherService } = GlobalComponents;
      //yield put({ type: 'showLoading', payload })
      const data = yield call(TeacherService.load, payload.id, payload.parameters);
      const newPlayload = { ...payload, ...data };

      console.log('this is the data id: ', data.id);
      yield put({ type: 'updateState', payload: newPlayload });
    },

    *doJob({ payload }, { call, put }) {
      const userContext = null;
      const { TaskService } = GlobalComponents;
      //yield put({ type: 'showLoading', payload })
      const { serviceNameToCall, id, parameters } = payload;
      if (!serviceNameToCall) {
        handleClientError(appLocaleName(userContext, 'ServiceNotRegistered'));
        return;
      }
      ('react/dva_object_model.jsp');

      const data = yield call(serviceNameToCall, id, parameters);
      if (handleServerError(data)) {
        return;
      }
      const newPlayload = { ...payload, ...data };

      console.log('this is the data id: ', data.id);
      yield put({ type: 'updateState', payload: newPlayload });
    },

    *gotoCreateForm({ payload }, { put }) {
      const { id, role } = payload;
      yield put(routerRedux.push(`/teacher/${id}/list/${role}CreateForm`));
    },
    *gotoUpdateForm({ payload }, { put }) {
      const { id, role, selectedRows, currentUpdateIndex } = payload;
      const state = { id, role, selectedRows, currentUpdateIndex };
      const location = { pathname: `/teacher/${id}/list/${role}UpdateForm`, state };
      yield put(routerRedux.push(location));
    },
    *goback({ payload }, { put }) {
      const { id, type, listName } = payload;
      yield put(routerRedux.push(`/teacher/${id}/list/${type}List/${listName}`));
    },

    *addClassDailyHealthSurvey({ payload }, { call, put }) {
      const userContext = null;
      const { TeacherService } = GlobalComponents;

      const { id, role, parameters, continueNext } = payload;
      console.log('get form parameters', parameters);
      const data = yield call(TeacherService.addClassDailyHealthSurvey, id, parameters);
      if (hasError(data)) {
        handleServerError(data);
        return;
      }
      const newPlayload = { ...payload, ...data };
      yield put({ type: 'updateState', payload: newPlayload });
      // yield put(routerRedux.push(`/teacher/${id}/list/${role}CreateForm'))
      notifySuccess(userContext);
      if (continueNext) {
        return;
      }
      const partialList = true;
      const newState = { ...data, partialList };
      const location = {
        pathname: `/teacher/${id}/list/ClassDailyHealthSurveyList/每日健康调查+${appLocaleName(
          userContext,
          'List'
        )}`,
        state: newState,
      };
      yield put(routerRedux.push(location));
    },
    *updateClassDailyHealthSurvey({ payload }, { call, put }) {
      const userContext = null;
      const { TeacherService } = GlobalComponents;
      const { id, type, parameters, continueNext, selectedRows, currentUpdateIndex } = payload;
      console.log('get form parameters', parameters);
      const data = yield call(TeacherService.updateClassDailyHealthSurvey, id, parameters);
      if (hasError(data)) {
        handleServerError(data);
        return;
      }
      const partialList = true;

      const newPlayload = { ...payload, ...data, selectedRows, currentUpdateIndex, partialList };
      yield put({ type: 'updateState', payload: newPlayload });
      notifySuccess(userContext);

      if (continueNext) {
        return;
      }
      const location = {
        pathname: `/teacher/${id}/list/ClassDailyHealthSurveyList/每日健康调查列表`,
        state: newPlayload,
      };
      yield put(routerRedux.push(location));
    },
    *gotoNextClassDailyHealthSurveyUpdateRow({ payload }, { call, put }) {
      const { id, type, parameters, continueNext, selectedRows, currentUpdateIndex } = payload;
      const newPlayload = { ...payload, selectedRows, currentUpdateIndex };
      yield put({ type: 'updateState', payload: newPlayload });
    },
    *removeClassDailyHealthSurveyList({ payload }, { call, put }) {
      const userContext = null;
      const { TeacherService } = GlobalComponents;
      const { id, role, parameters, continueNext } = payload;
      console.log('get form parameters', parameters);
      const data = yield call(TeacherService.removeClassDailyHealthSurveyList, id, parameters);
      if (hasError(data)) {
        handleServerError(data);
        return;
      }
      const newPlayload = { ...payload, ...data };

      yield put({ type: 'updateState', payload: newPlayload });
      notifySuccess(userContext);
    },

    *addStudentHealthSurvey({ payload }, { call, put }) {
      const userContext = null;
      const { TeacherService } = GlobalComponents;

      const { id, role, parameters, continueNext } = payload;
      console.log('get form parameters', parameters);
      const data = yield call(TeacherService.addStudentHealthSurvey, id, parameters);
      if (hasError(data)) {
        handleServerError(data);
        return;
      }
      const newPlayload = { ...payload, ...data };
      yield put({ type: 'updateState', payload: newPlayload });
      // yield put(routerRedux.push(`/teacher/${id}/list/${role}CreateForm'))
      notifySuccess(userContext);
      if (continueNext) {
        return;
      }
      const partialList = true;
      const newState = { ...data, partialList };
      const location = {
        pathname: `/teacher/${id}/list/StudentHealthSurveyList/学生健康调查+${appLocaleName(
          userContext,
          'List'
        )}`,
        state: newState,
      };
      yield put(routerRedux.push(location));
    },
    *updateStudentHealthSurvey({ payload }, { call, put }) {
      const userContext = null;
      const { TeacherService } = GlobalComponents;
      const { id, type, parameters, continueNext, selectedRows, currentUpdateIndex } = payload;
      console.log('get form parameters', parameters);
      const data = yield call(TeacherService.updateStudentHealthSurvey, id, parameters);
      if (hasError(data)) {
        handleServerError(data);
        return;
      }
      const partialList = true;

      const newPlayload = { ...payload, ...data, selectedRows, currentUpdateIndex, partialList };
      yield put({ type: 'updateState', payload: newPlayload });
      notifySuccess(userContext);

      if (continueNext) {
        return;
      }
      const location = {
        pathname: `/teacher/${id}/list/StudentHealthSurveyList/学生健康调查列表`,
        state: newPlayload,
      };
      yield put(routerRedux.push(location));
    },
    *gotoNextStudentHealthSurveyUpdateRow({ payload }, { call, put }) {
      const { id, type, parameters, continueNext, selectedRows, currentUpdateIndex } = payload;
      const newPlayload = { ...payload, selectedRows, currentUpdateIndex };
      yield put({ type: 'updateState', payload: newPlayload });
    },
    *removeStudentHealthSurveyList({ payload }, { call, put }) {
      const userContext = null;
      const { TeacherService } = GlobalComponents;
      const { id, role, parameters, continueNext } = payload;
      console.log('get form parameters', parameters);
      const data = yield call(TeacherService.removeStudentHealthSurveyList, id, parameters);
      if (hasError(data)) {
        handleServerError(data);
        return;
      }
      const newPlayload = { ...payload, ...data };

      yield put({ type: 'updateState', payload: newPlayload });
      notifySuccess(userContext);
    },

    *addHealthSurveyReport({ payload }, { call, put }) {
      const userContext = null;
      const { TeacherService } = GlobalComponents;

      const { id, role, parameters, continueNext } = payload;
      console.log('get form parameters', parameters);
      const data = yield call(TeacherService.addHealthSurveyReport, id, parameters);
      if (hasError(data)) {
        handleServerError(data);
        return;
      }
      const newPlayload = { ...payload, ...data };
      yield put({ type: 'updateState', payload: newPlayload });
      // yield put(routerRedux.push(`/teacher/${id}/list/${role}CreateForm'))
      notifySuccess(userContext);
      if (continueNext) {
        return;
      }
      const partialList = true;
      const newState = { ...data, partialList };
      const location = {
        pathname: `/teacher/${id}/list/HealthSurveyReportList/健康调查报告+${appLocaleName(
          userContext,
          'List'
        )}`,
        state: newState,
      };
      yield put(routerRedux.push(location));
    },
    *updateHealthSurveyReport({ payload }, { call, put }) {
      const userContext = null;
      const { TeacherService } = GlobalComponents;
      const { id, type, parameters, continueNext, selectedRows, currentUpdateIndex } = payload;
      console.log('get form parameters', parameters);
      const data = yield call(TeacherService.updateHealthSurveyReport, id, parameters);
      if (hasError(data)) {
        handleServerError(data);
        return;
      }
      const partialList = true;

      const newPlayload = { ...payload, ...data, selectedRows, currentUpdateIndex, partialList };
      yield put({ type: 'updateState', payload: newPlayload });
      notifySuccess(userContext);

      if (continueNext) {
        return;
      }
      const location = {
        pathname: `/teacher/${id}/list/HealthSurveyReportList/健康调查报告列表`,
        state: newPlayload,
      };
      yield put(routerRedux.push(location));
    },
    *gotoNextHealthSurveyReportUpdateRow({ payload }, { call, put }) {
      const { id, type, parameters, continueNext, selectedRows, currentUpdateIndex } = payload;
      const newPlayload = { ...payload, selectedRows, currentUpdateIndex };
      yield put({ type: 'updateState', payload: newPlayload });
    },
    *removeHealthSurveyReportList({ payload }, { call, put }) {
      const userContext = null;
      const { TeacherService } = GlobalComponents;
      const { id, role, parameters, continueNext } = payload;
      console.log('get form parameters', parameters);
      const data = yield call(TeacherService.removeHealthSurveyReportList, id, parameters);
      if (hasError(data)) {
        handleServerError(data);
        return;
      }
      const newPlayload = { ...payload, ...data };

      yield put({ type: 'updateState', payload: newPlayload });
      notifySuccess(userContext);
    },
  },

  reducers: {
    updateState(state, action) {
      const payload = { ...action.payload, loading: true };
      const valueToKeep = keepValueWithKeySuffix(state, 'Parameters');
      return { ...valueToKeep, ...payload };
    },
    showLoading(state, action) {
      // const loading=true
      const payload = { ...action.payload, loading: true };
      const valueToKeep = keepValueWithKeySuffix(state, 'Parameters');
      return { ...valueToKeep, ...payload };
    },
  },
};
