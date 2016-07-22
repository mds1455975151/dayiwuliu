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
    private String imagesRoot;
    private String scriptsRoot;
    private String stylesRoot;
    private String newsUpload;
    private String nginxRoot;
    private String mhImagesRoot;
    private String mhScriptsRoot;
    private String mhStylesRoot;
    private String localScripts;
    private String docStoreDir;
    private String upYun;
    private String trRoot;
    /*
     * 初始化方法
     */
    public void init() {
    	if("/".equals(getServletContext().getContextPath())){
    		getServletContext().setAttribute("imagesRoot", imagesRoot);
            getServletContext().setAttribute("scriptsRoot",scriptsRoot);
            getServletContext().setAttribute("stylesRoot", stylesRoot);
            getServletContext().setAttribute("newsUpload", newsUpload);
            getServletContext().setAttribute("nginxRoot", nginxRoot);
            getServletContext().setAttribute("contextPath", getServletContext().getContextPath());
            getServletContext().setAttribute("mhImagesRoot", mhImagesRoot);
            getServletContext().setAttribute("mhScriptsRoot",mhScriptsRoot);
            getServletContext().setAttribute("mhStylesRoot", mhStylesRoot);
            getServletContext().setAttribute("localScripts", localScripts);
            getServletContext().setAttribute("upYun", upYun);
            getServletContext().setAttribute("trRoot", trRoot);
    	}else {
    		getServletContext().setAttribute("imagesRoot", imagesRoot);
            getServletContext().setAttribute("scriptsRoot", scriptsRoot);
            getServletContext().setAttribute("stylesRoot", stylesRoot);
            getServletContext().setAttribute("newsUpload", getServletContext().getContextPath()+ newsUpload);
            getServletContext().setAttribute("nginxRoot", nginxRoot);
            getServletContext().setAttribute("contextPath", getServletContext().getContextPath());
            getServletContext().setAttribute("mhImagesRoot", mhImagesRoot);
            getServletContext().setAttribute("mhScriptsRoot",mhScriptsRoot);
            getServletContext().setAttribute("mhStylesRoot", mhStylesRoot);
            getServletContext().setAttribute("localScripts", localScripts);
            getServletContext().setAttribute("upYun", upYun);
            getServletContext().setAttribute("trRoot", trRoot);
		}
        
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

	public String getImagesRoot() {
		return imagesRoot;
	}

	public void setImagesRoot(String imagesRoot) {
		this.imagesRoot = imagesRoot;
	}

	public String getScriptsRoot() {
		return scriptsRoot;
	}

	public void setScriptsRoot(String scriptsRoot) {
		this.scriptsRoot = scriptsRoot;
	}

	public String getStylesRoot() {
		return stylesRoot;
	}

	public void setStylesRoot(String stylesRoot) {
		this.stylesRoot = stylesRoot;
	}

	public String getNewsUpload() {
		return newsUpload;
	}

	public void setNewsUpload(String newsUpload) {
		this.newsUpload = newsUpload;
	}

	public String getNginxRoot() {
		return nginxRoot;
	}

	public void setNginxRoot(String nginxRoot) {
		this.nginxRoot = nginxRoot;
	}

	public String getMhImagesRoot() {
		return mhImagesRoot;
	}

	public void setMhImagesRoot(String mhImagesRoot) {
		this.mhImagesRoot = mhImagesRoot;
	}

	public String getMhScriptsRoot() {
		return mhScriptsRoot;
	}

	public void setMhScriptsRoot(String mhScriptsRoot) {
		this.mhScriptsRoot = mhScriptsRoot;
	}

	public String getMhStylesRoot() {
		return mhStylesRoot;
	}

	public void setMhStylesRoot(String mhStylesRoot) {
		this.mhStylesRoot = mhStylesRoot;
	}

	public String getLocalScripts() {
		return localScripts;
	}

	public void setLocalScripts(String localScripts) {
		this.localScripts = localScripts;
	}

	public String getDocStoreDir() {
		return docStoreDir;
	}

	public void setDocStoreDir(String docStoreDir) {
		this.docStoreDir = docStoreDir;
	}

	public String getUpYun() {
		return upYun;
	}

	public void setUpYun(String upYun) {
		this.upYun = upYun;
	}
	
	public String getTrRoot() {
		return trRoot;
	}

	public void setTrRoot(String newTrRoot) {
		this.trRoot = newTrRoot;
	}
    
}
