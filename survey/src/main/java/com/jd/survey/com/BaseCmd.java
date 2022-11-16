package com.jd.survey.com;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.NumberFormat;

import com.jd.survey.com.util.BeanUtils;
import com.jd.survey.com.util.DateUtils;
import com.jd.survey.com.util.FieldUtils;
import com.jd.survey.com.util.ObjectUtils;
import com.jd.survey.com.util.StringUtils;

public abstract class BaseCmd<E> extends BaseBean {

	//~ Static fields/initializers =========================================================
	
	private static final long serialVersionUID = 1L;

	/** 기본 페이지 번호 */
	private static final int DEFAULT_PAGE = 1;
	
	/** 기본 페이지 출력건수 */
	private static final int DEFAULT_PAGE_SIZE = 10;

	/** 기본 페이지블럭 사이즈 */
	private static final int DEFAULT_MAX_LINKED_PAGES = 10;

	/** 페이지 정렬옵션 */
	public static enum SortOrder { asc, desc; }
	/** 기본 정렬필드 */
	private String DEFAULT_SORT;
	
	/** 정렬필드 목록 */
	private String[] BASE_SORTS;
	
	/** 파라메터 제외 */
	private static final String[] EXCLUDE_PARAMS = {"maxLinkedPages", "source", "total"};
	
	/** 커맨드 파라메터 제외 */
	private static final String[] EXCLUDE_COMMANDS = {"page", "pageSize", "sort", "sortOrder"};
	
	
	//~ Instance fields ====================================================================
	
	/** 검색건수 */
	@NumberFormat(pattern="#,###")
	private int total;
	
	/** 검색결과 */
	private List<E> source;
	
	/** 페이지 번호 */
	private int page = DEFAULT_PAGE;
	
	/** 페이지 출력건수 */
	private int pageSize = DEFAULT_PAGE_SIZE;

	/** 페이지블럭 사이즈 */
	private int maxLinkedPages = DEFAULT_MAX_LINKED_PAGES;
	
	/** 정렬필드 */
	private String sort;
	
	/** 정렬방식 */
	private SortOrder sortOrder = SortOrder.desc;
	
	/** 기본검색어 */
	private String keywords;
	
	/** 기본검색필드 */
	private String field;
	
	/** 직전 커맨드 세선 저장유무 : 커맨드를 2개이상 사용해야할 경우, 또는 세션 저장기능을 사용하지 않을 경우  */
	private boolean saveRedirect;

	//~ Constructors =======================================================================
	
	public BaseCmd(String[] sorts, boolean saveRedirect) {
		this.BASE_SORTS = sorts;
		this.DEFAULT_SORT = sorts[0];
		this.sort = this.DEFAULT_SORT;
		this.saveRedirect = saveRedirect;
	}
	
	//~ Override Methods ===================================================================
	
	public int getPage() {
		if(page < 1) page = DEFAULT_PAGE;
		else if(page > getPageCount()) page = getPageCount();
		return page;
	}
	
	public String[] getKeywordList() {
		if(StringUtils.hasText(keywords)){
			return keywords.trim().split("[\\s+|,\\s*]");
		}
		return null;
	}
	
	public void setSort(String sort) {
		if(ObjectUtils.containsElement(this.BASE_SORTS, sort)) {
			this.sort = sort;
		}
		else if(this.sort == null) {
			this.sort = DEFAULT_SORT;
		}
	}
	
	public String getSortDbStyle() {
		return StringUtils.replaceCamelToUnderscoer(this.sort);
	}

	//~ Pagging Methods ===================================================================

	/**
	 * 전체 페이지수
	 */
	public int getPageCount() {
		float nrOfPages = (float) getTotal() / getPageSize();
		return (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages);
	}
	
	/**
	 * 첫 페이지 유무
	 */
	public boolean isFirstPage() {
		return getPage() == 1;
	}
	
	/**
	 * 마지막 페이지 유무 
	 */
	public boolean isLastPage() {
		return getPage() == getPageCount();
	}
	
	/**
	 * 요청페이지 첫 엘리먼트 번호 
	 */
	public int getFirstElementOnPage() {
		return (getPageSize() * (getPage() - 1)) + 1;
	}
	
	/**
	 * 요청된 페이지 첫번째 엘리먼트의 번호를 조회합니다. (역순) 
	 * @return 엘리먼트 번호
	 */
	public int getFirstElementOnPageReverse() {
		return getTotal() - ((getPageSize() * (getPage() - 1)));
	}
	
	/**
	 * 요청페이지 마지막 엘리먼트 번호 
	 */
	public int getLastElementOnPage() {
		int endIndex = getPageSize() * (getPage());
		int size = getTotal();
		return (endIndex > size ? size : endIndex);
	}
	
	/**
	 * 이전 페이지블럭의 마지막 페이지번호
	 */
	public int getFirstLinkedPage() {
		return Math.max(1, ((getPage() -1) / getMaxLinkedPages()) * getMaxLinkedPages() + 1);
	}

	/**
	 * 이후 페이지블럭의 첫번째 페이지번호
	 */
	public int getLastLinkedPage() {
		return Math.min(getFirstLinkedPage() + getMaxLinkedPages() - 1, getPageCount());
	}
	
	/**
	 * 요청페이지 데이터 목록
	 */
	public List<E> getPageList() {
		if(getTotal() == 0 || getSource().size() <= getPageSize()) return source;
		else return getSource().subList(getFirstElementOnPage()-1, getLastElementOnPage());
	}
	
	//~ Pagging URL Methods ===================================================================
	
	private List<String[]> getParameters() {
		
		List<String[]> parameters = new ArrayList<String[]>();
		PropertyDescriptor props[] = BeanUtils.getPropertyDescriptors(this.getClass());
		
		for(PropertyDescriptor prop : props) {
			
			if(prop.getWriteMethod() != null && prop.getReadMethod() != null) {
				
				if(!ObjectUtils.containsElement(EXCLUDE_PARAMS, prop.getName())) {

					Object fieldValue = null;
					try {
						fieldValue = FieldUtils.getFieldValue(this, prop.getName());
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				
					// 배열 형식의 원시형변수 미처리
					if(fieldValue == null) {
						parameters.add(new String[]{prop.getName(), null});
					}
					else if(ObjectUtils.isArray(fieldValue)) {
						for(Object obj : (Object[])fieldValue) {
							parameters.add(new String[]{prop.getName(), obj.toString()});
						}
					}
					else if(fieldValue instanceof Date) {
						parameters.add(new String[]{prop.getName(), DateUtils.format((Date)fieldValue)});
					}
					else {
						parameters.add(new String[]{prop.getName(), fieldValue.toString()});
					}
				}
			}
		}
		return parameters;
	}
	
	/**
	 * 실제 사용된 커맨드 파라메터
	 */
	private List<String[]> getNotEmpytCommandParameters() {
		List<String[]> commandParameters = new ArrayList<String[]>();
		for(String[] param : getParameters()) {
			if(!ObjectUtils.containsElement(EXCLUDE_COMMANDS, param[0]) && StringUtils.hasText(param[1])) commandParameters.add(param);
		}
		return commandParameters;
	}
	
	/**
	 * 커맨드 파라메터 URL
	 */
	private String getCommandParameterUrl() {
		String url = null;
		for(String[] param : getNotEmpytCommandParameters()) {
			String encodeParam = null;
			try {
				encodeParam = URLEncoder.encode(param[1], "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			url = (url == null) ? "?" + param[0] + "=" + encodeParam : url + "&" + param[0] + "=" + encodeParam;
		}
		return url;
	}
	
	/**
	 * 커맨드 파라메터 사용여부 (검색어 사용 유무 판별)
	 */
	public boolean isEmptyCommandParameter() {
		return getNotEmpytCommandParameters().size() == 0;
	}
	
	/**
	 * 페이지 URL
	 */
	public String getParameterUrl() {
		String url = getCommandParameterUrl();
		return ((url == null) ? "?" : url + "&")
				.concat("sort=" + getSort())
				.concat("&sortOrder=" + getSortOrder())
				.concat("&pageSize=" + getPageSize())
				.concat("&page=" + getPage());
	}
	
	/**
	 * 페이지 URL
	 */
	public String getPageParameterUrl() {
		String url = getCommandParameterUrl();
		return ((url == null) ? "?" : url + "&")
				.concat("sort=" + getSort())
				.concat("&sortOrder=" + getSortOrder())
				.concat("&pageSize=" + getPageSize())
				.concat("&page=");
	}
	
	/**
	 * 페이지 사이즈 URL
	 */
	public String getPageSizeParameterUrl() {
		String url = getCommandParameterUrl();
		return ((url == null) ? "?" : url + "&")
				.concat("sort=" + getSort())
				.concat("&sortOrder=" + getSortOrder())
				.concat("&pageSize=");
	}
	
	/**
	 * 정렬 URL
	 */
	public String getSortParameterUrl() {
		String url = getCommandParameterUrl();
		return ((url == null) ? "?" : url + "&")
				.concat("page=" + getPage())
				.concat("&pageSize=" + getPageSize())
				.concat("&sort=");
	}
	
	/**
	 * 정렬(INVERSE) URL
	 */
	public String getSortInverseParameterUrl() {
		String url = getCommandParameterUrl();
		return ((url == null) ? "?" : url + "&")
				.concat("page=" + getPage())
				.concat("&pageSize=" + getPageSize())
				.concat("&sortOrder=" + (SortOrder.asc.equals(getSortOrder()) ? SortOrder.desc : SortOrder.asc))
				.concat("&sort=");
	}

	//~ Default Methods ===================================================================
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<E> getSource() {
		return source;
	}

	public void setSource(List<E> source) {
		this.source = source;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getMaxLinkedPages() {
		return maxLinkedPages;
	}

	public void setMaxLinkedPages(int maxLinkedPages) {
		this.maxLinkedPages = maxLinkedPages;
	}

	public String getSort() {
		return sort;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void setSaveRedirect(boolean saveRedirect) {
		this.saveRedirect = saveRedirect;
	}
	
}
