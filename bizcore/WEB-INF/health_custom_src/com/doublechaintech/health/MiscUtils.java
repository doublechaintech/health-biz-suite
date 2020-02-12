package com.doublechaintech.health;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.doublechaintech.health.classdailyhealthsurvey.ClassDailyHealthSurvey;
import com.doublechaintech.health.dailysurveyquestion.DailySurveyQuestion;
import com.doublechaintech.health.question.Question;
import com.doublechaintech.health.questiontype.QuestionType;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswer;
import com.doublechaintech.health.studentdailyanswer.StudentDailyAnswerTokens;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurvey;
import com.doublechaintech.health.studenthealthsurvey.StudentHealthSurveyTokens;
import com.terapico.caf.viewpage.SerializeScope;
import com.terapico.utils.CollectionUtils;
import com.terapico.utils.MapUtil;
import com.terapico.utils.TextUtil;

public class MiscUtils extends HealthBaseUtils {

	public static SerializeScope buildEntityScope(String... propertyNames) {
		if (propertyNames == null) {
			return null;
		}
		SerializeScope scope = SerializeScope.INCLUDE();
		for (String propertyname : propertyNames) {
			scope.field(propertyname);
		}
		return scope;
	}

	public static String getEnumValueByKey(List<KeyValuePair> pairs, String key) {
		if (pairs == null || key == null) {
			return null;
		}
		return (String) pairs
				.stream()
					.filter(p -> key.equals(p.getKey()))
					.findFirst()
					.map(KeyValuePair::getValue)
					.orElse(null);
	}

	public static List<DailySurveyQuestion> constructQuestionList(List<DailySurveyQuestion> questions) {
		if (questions == null) {
			return Collections.emptyList();
		}
		questions.forEach(q -> {
			q
					.addItemToValueMap("type",
							QuestionType.SINGLE_SELECT.equals(q.getQuestionType().getId()) ? "radio" : "input");
			if (QuestionType.SINGLE_SELECT.equals(q.getQuestionType().getId())) {
				List<Map<String, Object>> qList = CollectionUtils
						.toList(MapUtil.put("name", q.getOptionA()).put("id", "A").into_map(),
								MapUtil.put("name", q.getOptionB()).put("id", "B").into_map(),
								MapUtil.put("name", q.getOptionC()).put("id", "C").into_map(),
								MapUtil.put("name", q.getOptionD()).put("id", "D").into_map())
							.stream()
							.filter(question -> !TextUtil.isBlank((String) question.get("name")))
							.collect(Collectors.toList());
				q.addItemToValueMap("candidateValues", qList);
			} else if (QuestionType.TEXT_INPUT.equals(q.getQuestionType().getId())) {
				q.addItemToValueMap("placeholder", "");
			}
			q.addItemToValueMap("title", q.getTopic());

		});
		return questions;
	}

	public static List<Question> constructQuestionList2(List<Question> questions) {
		if (questions == null) {
			return Collections.emptyList();
		}
		questions.forEach(q -> {
			q
					.addItemToValueMap("type",
							QuestionType.SINGLE_SELECT.equals(q.getQuestionType().getId()) ? "radio" : "input");
			if (QuestionType.SINGLE_SELECT.equals(q.getQuestionType().getId())) {
				List<Map<String, Object>> qList = CollectionUtils
						.toList(MapUtil.put("name", q.getOptionA()).put("id", "A").into_map(),
								MapUtil.put("name", q.getOptionB()).put("id", "B").into_map(),
								MapUtil.put("name", q.getOptionC()).put("id", "C").into_map(),
								MapUtil.put("name", q.getOptionD()).put("id", "D").into_map())
							.stream()
							.filter(question -> !TextUtil.isBlank((String) question.get("name")))
							.collect(Collectors.toList());
				q.addItemToValueMap("candidateValues", qList);
			} else if (QuestionType.TEXT_INPUT.equals(q.getQuestionType().getId())) {
				q.addItemToValueMap("placeholder", "");
			}
			q.addItemToValueMap("title", q.getTopic());

		});
		return questions;
	}

	public static List<DailySurveyQuestion> constructQuestionList3(List<StudentDailyAnswer> answers) {
		if (answers == null) {
			return Collections.emptyList();
		}
		List<DailySurveyQuestion> result = new ArrayList<>();
		answers.forEach(a -> {
			DailySurveyQuestion q = a.getQuestion();
			q
					.addItemToValueMap("type",
							QuestionType.SINGLE_SELECT.equals(q.getQuestionType().getId()) ? "radio" : "input");
			if (QuestionType.SINGLE_SELECT.equals(q.getQuestionType().getId())) {
				List<Map<String, Object>> qList = CollectionUtils
						.toList(MapUtil
								.put("name", q.getOptionA())
									.put("id", "A")
									.put("selected", a.getAnswer().equals("A"))
									.into_map(),
								MapUtil
										.put("name", q.getOptionB())
											.put("id", "B")
											.put("selected", a.getAnswer().equals("B"))
											.into_map(),
								MapUtil
										.put("name", q.getOptionC())
											.put("id", "C")
											.put("selected", a.getAnswer().equals("C"))
											.into_map(),
								MapUtil
										.put("name", q.getOptionD())
											.put("id", "D")
											.put("selected", a.getAnswer().equals("D"))
											.into_map())
							.stream()
							.filter(question -> !TextUtil.isBlank((String) question.get("name")))
							.collect(Collectors.toList());
				q.addItemToValueMap("candidateValues", qList);
			} else if (QuestionType.TEXT_INPUT.equals(q.getQuestionType().getId())) {
				q.addItemToValueMap("placeholder", "");
			}
			q.addItemToValueMap("title", q.getTopic());
			result.add(q);
		});
		return result;
	}

	public static long getRiskCount(CustomHealthUserContextImpl ctx, ClassDailyHealthSurvey survey) {
		if (survey == null) {
			return 0l;
		}
		try {
			SmartList<StudentHealthSurvey> studentSurveys = ctx
					.getDAOGroup()
						.getClassDailyHealthSurveyDAO()
						.loadOurStudentHealthSurveyList(ctx, CollectionUtils.toList(survey),
								StudentHealthSurveyTokens.all());
			if(studentSurveys==null||studentSurveys.isEmpty()) {
				return 0l;
			}
			ctx.getDAOGroup().getStudentHealthSurveyDAO().loadOurStudentDailyAnswerList(ctx, studentSurveys, StudentDailyAnswerTokens.all());
			return studentSurveys.stream().filter(sSurvey -> {
				return sSurvey.getStudentDailyAnswerList().stream().anyMatch(as->MiscUtils.hasRisk(ctx,as));
			}).count();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0l;
	}

	protected static boolean hasRisk(CustomHealthUserContextImpl ctx,StudentDailyAnswer studentAnswer){
		if(studentAnswer==null) {
			return false;
		}
		List<DailySurveyQuestion> questionList=null;
		try {
			questionList = MiscUtils.collectReferencedObjectWithType(ctx, studentAnswer, DailySurveyQuestion.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ctx.getDAOGroup().getDailySurveyQuestionDAO().enhanceList(questionList);
		DailySurveyQuestion question = studentAnswer.getQuestion();
		String answer = studentAnswer.getAnswer();
		Map<String, String> riskMap = MapUtil
				.put("Q000001", "A")
					.put("Q000002", "A")
					.put("Q000003", "B")
					.put("Q000004", "37.3")
					.into_map(String.class);
		String riskAnswer = (String) riskMap.get(question.getId());

		if (QuestionType.SINGLE_SELECT.equals(question.getQuestionType().getId())) {
			return riskMap.get(question.getId()).equals(answer);
		} else {
			if (StringUtils.isNumeric(riskAnswer) && StringUtils.isNumeric(answer)) {
				return new BigDecimal(answer).doubleValue() - new BigDecimal(riskAnswer).doubleValue() > 0;
			}
		}
		return false;
	}
}
