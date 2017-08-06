package com.shi.rtcp.business.keywords.excelexport;
public class MismatchDetails
{
	public MismatchDetails(String row, String col, String expVal, String actVal) {
		super();
		this.row = row;
		this.col = col;
		this.expVal = expVal;
		this.actVal = actVal;
	}


	public MismatchDetails(String row,String RowData) {
		super();
		this.row = row;
		this.col = "";
		this.expVal = "";
		this.actVal = "Row Not Found In CSV File";
		entireRow=RowData;
	}

	private String row;
	private String col;
	private String expVal;
	private String actVal;
	private String entireRow;
	public String getEntireRow() {
		
		if(entireRow==null)
		{
			entireRow="";
		}
		return entireRow;
	}


	public void setEntireRow(String entireRow) {
		this.entireRow = entireRow;
	}


	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
	}
	public String getCol() {
		return col;
	}
	public void setCol(String col) {
		this.col = col;
	}
	public String getExpVal() {
		return expVal;
	}
	public void setExpVal(String expVal) {
		this.expVal = expVal;
	}
	public String getActVal() {
		return actVal;
	}
	public void setActVal(String actVal) {
		this.actVal = actVal;
	}

}