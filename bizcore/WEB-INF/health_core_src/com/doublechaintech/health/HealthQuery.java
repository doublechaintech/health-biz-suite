package com.doublechaintech.health;

import com.skynet.infrastructure.graphservice.BaseQuery;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class HealthQuery extends BaseQuery {
	
	public HealthQuery(Class startType, String ... pStart) {
        super(startType, pStart);
        super.setProject("health");
  }

  public HealthQuery(Object start){
    this(start.getClass(), getId(start));
  }

  private static String getId(Object pStart) {
      BeanWrapper bw = new BeanWrapperImpl(pStart);
      return String.valueOf(bw.getPropertyValue("id"));
  }
}













