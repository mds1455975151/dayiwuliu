package com.tianrui.trwl.admin.util;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

/**
 * 
 * @类描述： 通过Spring框架在ServletContext层面注入静态资源根路径信息(asda)
 * @创建人：tank
 * @创建时间：2016年1月17日下午5:11:03
 *
 * @修改人：tank
 * @修改时间：2016年1月17日下午5:11:03
 * @修改备注：
 *
 */
public class ResourcePathExposer implements ServletContextAware {
	
    private ServletContext application;
    private String trRoot;
    /*
     * 初始化方法
     */
    public void init() {
    	getServletContext().setAttribute("trRoot", trRoot);

    	getServletContext().setAttribute("imagesRoot", trRoot+"/images");
        getServletContext().setAttribute("scriptsRoot",trRoot+"/js");
        getServletContext().setAttribute("stylesRoot", trRoot+"/css");
    }

    public void setServletContext(ServletContext servletContext) {
        application = servletContext;

    }

    public ServletContext getServletContext() {
        return this.application;
    }

	public ServletContext getApplication() {
		return application;
	}

	public void setApplication(ServletContext application) {
		this.application = application;
	}
	
	public String getTrRoot() {
		return trRoot;
	}

	public void setTrRoot(String newTrRoot) {
		this.trRoot = newTrRoot;
	}
    
}
