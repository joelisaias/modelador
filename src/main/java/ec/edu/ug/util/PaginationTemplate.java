package ec.edu.ug.util;

import java.util.Arrays;
import java.util.List;

/**
 * Clase util para gestionar la paginacion en las consultas
 * 
 * @author <a href="mailto:joyalt77@gmail.com">Joel Alvarado</a>
 * @since 2016/09/09
 * @version 1.0
 */
public final class PaginationTemplate {
	
	private int pageSize;
	private int firstRow;
	private int rowCount;
	private int totalPages;
	private int currentPage;
	
	public static final RowsPerPage defaultPageSize=RowsPerPage.ROW_15;
	public static final RowsPerPage defaultDetailPageSize=RowsPerPage.ROW_10;
	
	public PaginationTemplate(RowsPerPage pageSize){
		this.pageSize=pageSize.value;		
		this.rowCount=0;
		this.totalPages=0;
		this.currentPage=0;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getFirstRow() {
		return firstRow;
	}
	
	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}
		
	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getTotalPages() {
		if(rowCount>0)
			totalPages=(int)Math.ceil( rowCount/pageSize);
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNextPage() {
		return currentPage+1;
	}
	
	public int getLastPage() {
		return currentPage-1;
	}
	
	public int getNextFirstRow(){
		return pageSize*getNextPage()+1;
	}

	public String getRowsPerPagesString(){
		return RowsPerPage.rowsPerPagesString;
	}
	
	public static RowsPerPage getDefaultDetailPageSize() {
		return defaultDetailPageSize;
	}
	
	public static RowsPerPage getDefaultPageSize() {
		return defaultPageSize;
	}
	

	/**
	 * 
	 * @author Joel Alvarado
	 * @since 2016/09/09
	 * @version 1.0
	 */
	public enum RowsPerPage{
		 ROW_5(5)
		,ROW_10(10)
		,ROW_15(15)
		,ROW_20(20)
		,ROW_25(25)
		,ROW_50(50)
		,ROW_75(75)
		,ROW_100(100)
		;		
		
		private int value;		
		
		private RowsPerPage(int value){
			this.value=value;
		}
		
		public static List<RowsPerPage> LIST=Arrays.asList(RowsPerPage.values());
		public static String rowsPerPagesString=ROW_5.value+","+ROW_10.value+","+","+ROW_15.value+","+ROW_20.value+","+ROW_25.value+","+ROW_50.value+","+ROW_75.value+","+ROW_100.value;
		
		public int getValue() {
			return value;
		}
	}
	

}